package com.gev.auth.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private String password;
	
	@NotNull
	private String username;

	private boolean enable = true;
	
	private String permissions;
	
	private String roles;
	
	public User(@NotNull String username, @NotNull String password, String permissions, String roles) {
		this.password = password;
		this.username = username;
		this.permissions = permissions;
		this.roles = roles;
	}
	
	public List<String> getRolesList(){
		
		if(roles.length() > 0) {
			return  Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<String>();
	}
	
	public List<String> getPermissionsList(){
		
		if(roles.length() > 0) {
			return  Arrays.asList(this.permissions.split(","));
		}
		return new ArrayList<String>();
	}
	
}
