package models;

import java.util.Vector;
import models.Role;

public class Journalist {
	
	private String name;
	private Vector<Role> permissions;
	
	Journalist(String name){
		this.setName(name);
	}
	
	public Vector<Role> getPermissions() {
		return permissions;
	}
	
	public void addPermission(Role newPermission) {
		this.permissions.add(newPermission);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
