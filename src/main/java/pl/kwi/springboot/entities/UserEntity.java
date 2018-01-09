package pl.kwi.springboot.entities;

public class UserEntity {
	
	
	private String cn;
	
	private String name;
	
	
	public UserEntity() {}
	
	public UserEntity(String name) {
		this.name = name;
	}	
	
	public UserEntity(String cn, String name) {
		this.cn = cn;
		this.name = name;
	}


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
