package net.it_tim.dude_of_ping3.database;

// Generated 30 трав 2011 16:21:40 by Hibernate Tools 3.3.0.GA

import java.util.Date;

/**
 * KnockHistory generated by hbm2java
 */
public class KnockHistory implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Hosts hosts;
	private Boolean knocked;
	private Date time;

	public KnockHistory() {
	}

	public KnockHistory(int id) {
		this.id = id;
	}

	public KnockHistory(int id, Hosts hosts, Boolean knocked, Date time) {
		this.id = id;
		this.hosts = hosts;
		this.knocked = knocked;
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

	public Boolean getKnocked() {
		return this.knocked;
	}

	public void setKnocked(Boolean knocked) {
		this.knocked = knocked;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
