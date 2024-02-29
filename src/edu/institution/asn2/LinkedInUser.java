package edu.institution.asn2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class LinkedInUser extends UserAccount implements Comparable<LinkedInUser>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5323380888033456568L;
	private String type;
	private List<LinkedInUser> connections;
	private Set<String> skillsets = new TreeSet<String>();
	
	@Override
	public int compareTo(LinkedInUser other) {
	    return this.getUsername().compareToIgnoreCase(other.getUsername());
	}
	
	public LinkedInUser(String inUsername, String inPassword) {
		super(inUsername, inPassword);
	}
	public LinkedInUser(String username, String password, String type) {
		super(username, password);
		this.type = type;
	}
	public LinkedInUser(String username, String password, List<LinkedInUser> connections) {
		super(username,password);
		this.connections = new ArrayList<>(connections);
	}
	public LinkedInUser(String username, String password, String type, List<LinkedInUser> connections) {
        super(username, password);
        this.type = type;
        this.connections = new ArrayList<>(connections);
    }
	

	public void setType(String type){
		this.type = type;
	}
	public String getType() {
		return this.type;
	}
	
	
	//adds a user from a list of connections
	public void addConnection(LinkedInUser user) throws LinkedInException {
		if (this.connections == null)
			this.connections = new ArrayList<>();
		if (this.connections.contains(user))
			throw new LinkedInException("You are already connected with this user");
		else {
			//System.out.println("added");
			this.connections.add(user);
		}
	}
	
	//removes a user from a list of connections
	public void removeConnection(LinkedInUser user) throws LinkedInException{
		try {
			if (this.connections.size() == 0 || this.connections.equals(null))
				throw new LinkedInException("You are NOT connected with anyone");
			else if (!this.connections.contains(user))
				throw new LinkedInException("You are NOT connected with this user");
			else
				this.connections.remove(user);
		} catch (LinkedInException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<LinkedInUser> getConnections() throws LinkedInException{
		List<LinkedInUser> copy;
		if (this.connections == null)
			this.connections = new ArrayList<>();
		copy = this.connections;
		/*
		try {
			if (this.connections.size() == 0) {
				//System.out.println("You are NOT connected with anyone");
				throw new LinkedInException("You are NOT connected with anyone");
			} else if (this.connections == null) {
				//System.out.println("Your connection list is null");
				throw new LinkedInException("You are NOT connected with anyone");
			}
		} catch (LinkedInException e) {
			e.getMessage();
		}
		*/
		
		if (this.connections == null) {
			throw new LinkedInException("You are NOT connected with anyone");
		}
		
		return this.connections;
	}
	
	public void setConnections(List<LinkedInUser> newConnectionList) {
		this.connections = newConnectionList;
	}

	//must be in alphabetical order
	public Set<String> getSkillsets() {
		return this.skillsets;
	}

	public void addSkillset(String skillset) {
		this.skillsets.add(skillset);
	}
	
	public void removeSkillset(String skillset) {
		this.skillsets.remove(skillset);
	}
}