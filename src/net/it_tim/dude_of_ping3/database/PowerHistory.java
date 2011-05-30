package net.it_tim.dude_of_ping3.database;

// Generated 30 трав 2011 16:21:40 by Hibernate Tools 3.3.0.GA

import java.util.Date;

/**
 * PowerHistory generated by hbm2java
 */
public class PowerHistory implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Hosts hosts;
	private Boolean ispowered;
	private Date time;

	public PowerHistory() {
	}

	public PowerHistory(int id) {
		this.id = id;
	}

	public PowerHistory(int id, Hosts hosts, Boolean ispowered, Date time) {
		this.id = id;
		this.hosts = hosts;
		this.ispowered = ispowered;
		this.time = time;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Hosts getHosts() {
		return this.hosts;
	}

	public void setHosts(Hosts hosts) {
		this.hosts = hosts;
	}

	public Boolean getIspowered() {
		return this.ispowered;
	}

	public void setIspowered(Boolean ispowered) {
		this.ispowered = ispowered;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}