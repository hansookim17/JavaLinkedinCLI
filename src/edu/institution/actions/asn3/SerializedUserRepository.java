package edu.institution.actions.asn3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;
import edu.institution.*;

public class SerializedUserRepository implements UserRepository {
	private String filePath;
	private String fileName;
	private List<LinkedInUser> users;

	public String getFilePath() {
		return filePath;
	}
	public String getFileName() {
		return fileName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void init(String filePath, String fileName) {
		this.filePath = filePath;
		this.fileName = fileName;

		// Deserializing code (from Binary code to Java code)
		try {
			File file = new File(this.filePath + this.fileName);

			//[TEST] System.out.println("file.exists() == " + file.exists());
			if (!file.exists()) {
				users = new ArrayList<LinkedInUser>();
				return;
			}
			if (file.exists()) {
				// deserializing part
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				this.users = (List<LinkedInUser>) ois.readObject();
				//System.out.println("beme0");
				ApplicationHelper.initSkillsetUsages(users);
			}
		} catch (FileNotFoundException exception) {
			System.out.println(exception.getMessage());
		} catch (IOException exception1) {
			System.out.println(exception1.getMessage());
		} catch (ClassNotFoundException exception2) {
			System.out.println(exception2.getMessage());
		}
		
	}
	

	@Override
	public void add(LinkedInUser user) throws LinkedInException {

		try {
			if (user.getUsername() == null || user.getType() == null)
				throw new LinkedInException("The user name and type are required to add a new user.");
			if (!(user.getType().equalsIgnoreCase("P") || user.getType().equalsIgnoreCase("S")))
				throw new LinkedInException("Invalid user type. Valid types are P or S");
			if (this.users.contains(user))
				throw new LinkedInException("A user already exists with that user name.");
			else
				this.users.add(user);
		} catch (LinkedInException e) {
			e.getMessage();
		}
		// make sure to call saveAll() to retain the changes.
		saveAll();
	}

	@Override
	public void delete(LinkedInUser user) {
		if (!this.users.contains(user))
			System.out.print("No user exists with that username.");
		else
			this.users.remove(user);
		// make sure to call saveAll() to retain the changes.
		saveAll();
	}
	

	// serializes List<LinkedInUser> users to fileName at filePath
	@Override
	public void saveAll() {
		File file = new File(this.filePath + this.fileName);
		if (file.exists()) {
			//[TEST] System.out.println("file exists");
			file.delete();
		}

		new File(this.filePath).mkdirs();

		/*
		 * file is read to fout, fout is SERIALIZED to oos.
		 */
		try (FileOutputStream fout = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fout);) {
			oos.writeObject(this.users);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/*
	// input a name, and the method returns a user from the userlist
	@Override
	public LinkedInUser retrieve(String username) {
		File file = new File(this.filePath + this.fileName);
		if (file.exists()) {
			try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
				this.users = (List<LinkedInUser>) ois.readObject();
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
		int j = -1;
		for (int i = 0; i < this.users.size(); i++) {
			if (this.users.get(i).getUsername().equals(username)) {
				j = i;
				break;
			}
		}
		if (j != -1)
			return this.users.get(j);
		else
			return null;
	}
	*/
	
	@Override
	//version2
	public LinkedInUser retrieve(String username) {
		int j = -1;
		for (int i = 0; i < this.users.size(); i++) {
			if (this.users.get(i).getUsername().equals(username)) {
				j = i;
				break;
			}
		}
		if (j != -1)
			return this.users.get(j);
		else
			return null;
	}

	// try to return a copy of an Array list
	@SuppressWarnings("unchecked")
	@Override
	public List<LinkedInUser> retrieveAll() {
		return this.users;
	}
}
