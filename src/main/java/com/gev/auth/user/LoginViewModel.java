package com.gev.auth.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginViewModel {

	private String username;
	private String password;
	
	public LoginViewModel() {}
}
