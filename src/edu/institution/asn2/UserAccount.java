package edu.institution.asn2;

import java.io.Serializable;

public abstract class UserAccount implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 141367511349930271L;
	private String username;
	private String password;

	public abstract void setType(String type);
	
	public UserAccount(String inUsername, String inPassword) {
		setUsername(inUsername);
		setPassword(inPassword);
	}
	
	public boolean isPasswordCorrect(String password) {
		if (this.getPassword().equals(password))
			return true;
		else 
			return false;
	}

	@Override
	public String toString() {
		return username;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		UserAccount other = (UserAccount) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
