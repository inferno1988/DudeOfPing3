package net.it_tim.dude_of_ping3.database;

// Generated 30 трав 2011 16:21:40 by Hibernate Tools 3.3.0.GA

import java.util.Date;

/**
 * DoorHistory generated by hbm2java
 */
public class DoorHistory implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private Hosts hosts;
	private Boolean isopened;
	private Date time;

	public DoorHistory() {
	}

	public DoorHistory(int id) {
		this.id = id;
	}

	public DoorHistory(int id, Hosts hosts, Boolean isopened, Date time) {
		this.id = id;
		this.hosts = hosts;
		this.isopened = isopened;
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

	public Boolean getIsopened() {
		return this.isopened;
	}

	public void setIsopened(Boolean isopened) {
		this.isopened = isopened;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
