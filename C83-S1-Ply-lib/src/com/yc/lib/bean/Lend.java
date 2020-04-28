package com.yc.lib.bean;

import java.sql.*;

public class Lend implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer bookid;
	private String client;
	private Timestamp outtime;
	private Timestamp rettime;
	private Integer empid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBookid() {
		return bookid;
	}
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Timestamp getOuttime() {
		return outtime;
	}
	public void setOuttime(Timestamp outtime) {
		this.outtime = outtime;
	}
	public Timestamp getRettime() {
		return rettime;
	}
	public void setRettime(Timestamp rettime) {
		this.rettime = rettime;
	}
	public Integer getEmpid() {
		return empid;
	}
	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lend other = (Lend) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Lend [id=" + id + ", bookid=" + bookid + ", client=" + client + ", outtime=" + outtime + ", rettime="
				+ rettime + ", empid=" + empid + "]";
	}

}
