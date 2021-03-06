package net.it_tim.dude_of_ping3.database;

// Generated 30 трав 2011 16:21:40 by Hibernate Tools 3.3.0.GA

import java.util.Date;

/**
 * UserActionHistory generated by hbm2java
 */
public class UserActionHistory implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Users users;
	private String action;
	private Date time;

	public UserActionHistory() {
	}

	public UserActionHistory(int id) {
		this.id = id;
	}

	public UserActionHistory(int id, Users users, String action, Date time) {
		this.id = id;
		this.users = users;
		this.action = action;
		this.time = time;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
