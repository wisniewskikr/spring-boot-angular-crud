package pl.kwi.springboot.commands;

import java.io.Serializable;

public class EditCommand implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String cn;
	private String name;
		
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
}
