/*_############################################################################
  _## 
  _##  SNMP4J - MultiThreadedTrapReceiver.java  
  _## 
  _##  Copyright (C) 2003-2009  Frank Fock and Jochen Katz (SNMP4J.org)
  _##  
  _##  Licensed under the Apache License, Version 2.0 (the "License");
  _##  you may not use this file except in compliance with the License.
  _##  You may obtain a copy of the License at
  _##  
  _##      http://www.apache.org/licenses/LICENSE-2.0
  _##  
  _##  Unless required by applicable law or agreed to in writing, software
  _##  distributed under the License is distributed on an "AS IS" BASIS,
  _##  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  _##  See the License for the specific language governing permissions and
  _##  limitations under the License.
  _##  
  _##########################################################################*/

package net.it_tim.dude_of_ping3;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.snmp4j.Snmp;
import org.snmp4j.smi.Address;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.TransportMapping;
import org.snmp4j.smi.OctetString;
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.mp.MPv3;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.security.USM;
import org.snmp4j.smi.GenericAddress;
import java.net.UnknownHostException;
import java.io.IOException;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;
import org.snmp4j.MessageDispatcherImpl;
import org.snmp4j.smi.TcpAddress;
import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.mp.MPv1;
import org.snmp4j.mp.MPv2c;

public class MultiThreadedTrapReceiver implements CommandResponder {

	// initialize Log4J logging
	/*
	 * static { LogFactory.setLogFactory(new Log4jLogFactory());
	 * BER.setCheckSequenceLength(false); }
	 */
	private MultiThreadedMessageDispatcher dispatcher;
	private Snmp snmp = null;
	private Address listenAddress;
	private ThreadPool threadPool;

	public MultiThreadedTrapReceiver() {
	}

	private void init() throws UnknownHostException, IOException {
		threadPool = ThreadPool.create("Trap", 10);
		dispatcher = new MultiThreadedMessageDispatcher(threadPool,
				new MessageDispatcherImpl());
		listenAddress = GenericAddress.parse(System.getProperty(
				"snmp4j.listenAddress", "udp:0.0.0.0/162"));
		TransportMapping transport;
		if (listenAddress instanceof UdpAddress) {
			transport = new DefaultUdpTransportMapping(
					(UdpAddress) listenAddress);
		} else {
			transport = new DefaultTcpTransportMapping(
					(TcpAddress) listenAddress);
		}
		snmp = new Snmp(dispatcher, transport);
		snmp.getMessageDispatcher().addMessageProcessingModel(new MPv1());
		snmp.getMessageDispatcher().addMessageProcessingModel(new MPv2c());
		snmp.getMessageDispatcher().addMessageProcessingModel(new MPv3());
		USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(MPv3
				.createLocalEngineID()), 0);
		SecurityModels.getInstance().addSecurityModel(usm);
		snmp.listen();
	}

	public void run() {
		try {
			init();
			snmp.addCommandResponder(this);
			System.out.println("Terminal power manager started");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MultiThreadedTrapReceiver multithreadedtrapreceiver = new MultiThreadedTrapReceiver();
		multithreadedtrapreceiver.run();
		
	}

	public void processPdu(CommandResponderEvent event) {
		Thread t = Thread.currentThread();
	    String name = t.getName();
	    PropertiesConfiguration dopConfig;
		String str, ping3_ip, host_ip = new String();
		long resetTime = 3000;
		
		str = event.getPDU().getVariableBindings().get(1).toString();
		System.out.println("Thread:" + name + " recieved trap:" + str);
		if (str.contains("=")) {
			int len = str.indexOf("=");
			str = str.substring(len + 2, str.length());
		}
		System.out.println("After substr: " + str);
		ping3_ip = event.getPeerAddress().toString().split("/")[0];
		System.out.println("Trap from IP: " + ping3_ip);
		
	    try {
			dopConfig = new PropertiesConfiguration("dop.properties");
			host_ip = dopConfig.getString(ping3_ip, "127.0.0.1");
			resetTime = dopConfig.getLong("reset.time", 3000);
		} catch (ConfigurationException e2) {
			System.out.println(e2.getMessage());
		}
		System.out.println("Host IP: " + host_ip);
		if (str.equals("1.3.6.1.4.1.35160.1.0.12")) {
			System.out.println("Power on trap!");
			try {
				Ping ping = new Ping(host_ip, 500, (short)100);
				System.out.println("Ping status:" + ping.isOnline());
				if (!ping.isOnline()) {
					System.out.println("Setting always off mode on: " + ping3_ip);
					Server.snmpSet(ping3_ip, "public",
							"1.3.6.1.4.1.35160.1.11.1.4.1", 1);
					Thread.sleep(resetTime);
					System.out.println("Setting always on mode on: " + ping3_ip);
					Server.snmpSet(ping3_ip, "public",
							"1.3.6.1.4.1.35160.1.11.1.4.1", 0);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
