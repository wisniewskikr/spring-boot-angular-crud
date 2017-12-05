package pl.kwi.springboot.commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pl.kwi.springboot.db.entities.UserEntity;

public class ListCommand implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<UserEntity> users = new ArrayList<UserEntity>();

	public List<UserEntity> getUsers() {
		return users;
	}
	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
		
}
