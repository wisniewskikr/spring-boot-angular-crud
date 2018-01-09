package pl.kwi.springboot.commands;

import java.io.Serializable;

public class DeleteCommand implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String cn;
	private String name;
		
	public String getCn() {
		return cn;
	}
	public void setId(String cn) {
		this.cn = cn;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
}
