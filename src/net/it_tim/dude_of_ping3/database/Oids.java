package net.it_tim.dude_of_ping3.database;

// Generated 30 трав 2011 16:21:40 by Hibernate Tools 3.3.0.GA

/**
 * Oids generated by hbm2java
 */
public class Oids implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String description;
	private String oid;
	private String type;

	public Oids() {
	}

	public Oids(int id) {
		this.id = id;
	}

	public Oids(int id, String description, String oid, String type) {
		this.id = id;
		this.description = description;
		this.oid = oid;
		this.type = type;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOid() {
		return this.oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
