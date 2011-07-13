package net.it_tim.dude_of_ping3;

import net.it_tim.dude_of_ping3.database.Users;
import net.it_tim.dude_of_ping3.database.UsersHome;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class Server {

	public static void main(String[] args) {
		UsersHome uh = new UsersHome();
		if (uh.checkPassword(Users.class, "inferno", "asd")) {
			System.out.println("GOOD!");
		} else {
			System.out.println("BAD!");
		}
		String strIPAddress = "192.168.33.178";
		String[] oids = { "1.3.6.1.4.1.35160.1.3.0", "1.3.6.1.4.1.35160.1.7.0",
				"1.3.6.1.4.1.35160.1.8.0", "1.3.6.1.4.1.35160.1.1.0",
				"1.3.6.1.4.1.35160.1.16.1.13.1" };
		Server.snmpGet(strIPAddress, "public", oids);
	}

	public static String snmpGet(String strAddress, String commy,
			String[] strOID) {
		String str = "";
		try {
			OctetString community = new OctetString(commy);
			strAddress = strAddress + "/" + Const.SNMP_PORT;
			Address targetaddress = new UdpAddress(strAddress);
			TransportMapping transport = new DefaultUdpTransportMapping();
			transport.listen();
			CommunityTarget comtarget = new CommunityTarget();
			comtarget.setCommunity(community);
			comtarget.setVersion(SnmpConstants.version2c);
			comtarget.setAddress(targetaddress);
			comtarget.setRetries(2);
			comtarget.setTimeout(5000);
			PDU pdu = new PDU();
			ResponseEvent response;
			Snmp snmp;
			for (int i = 0; i < strOID.length; i++)
				pdu.add(new VariableBinding(new OID(strOID[i])));
			pdu.setType(PDU.GET);
			snmp = new Snmp(transport);
			response = snmp.get(pdu, comtarget);
			if (response != null) {
				if (response.getResponse().getErrorStatusText()
						.equalsIgnoreCase("Success")) {
					PDU pduresponse = response.getResponse();
					for (int i = 0; i < pduresponse.getVariableBindings()
							.size(); i++) {
						str = pduresponse.getVariableBindings().get(i)
								.toString();

						if (str.contains("=")) {
							int len = str.indexOf("=");
							str = str.substring(len + 2, str.length());
						}
						System.out.println("Response=" + str);
					}
				}
			} else {
				System.out.println("Feeling like a TimeOut occured ");
			}
			snmp.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static void snmpSet(String host, String community, String strOID,
			int Value) {
		host = host + "/" + "161";
		Address tHost = GenericAddress.parse(host);
		Snmp snmp;
		try {
			TransportMapping transport = new DefaultUdpTransportMapping();
			snmp = new Snmp(transport);
			transport.listen();
			CommunityTarget target = new CommunityTarget();
			target.setCommunity(new OctetString(community));
			target.setAddress(tHost);
			target.setRetries(2);
			target.setTimeout(5000);
			target.setVersion(SnmpConstants.version2c);
			PDU pdu = new PDU();
			pdu.add(new VariableBinding(new OID(strOID), new Integer32(Value)));
			pdu.setType(PDU.SET);
			ResponseListener listener = new ResponseListener() {
				public void onResponse(ResponseEvent event) {
					PDU strResponse;
					String result;
					((Snmp) event.getSource()).cancel(event.getRequest(), this);
					strResponse = event.getResponse();
					if (strResponse != null) {
						result = strResponse.getErrorStatusText();
						System.out.println("Set Status is: " + result);
					}
				}
			};
			snmp.send(pdu, target, null, listener);
			snmp.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
