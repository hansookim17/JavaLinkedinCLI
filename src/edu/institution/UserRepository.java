package edu.institution;

import java.util.*;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public interface UserRepository {
	void init(String filePath, String fileName);
	String getFilePath();
	String getFileName();
	void add(LinkedInUser user) throws LinkedInException;
	void saveAll();
	void delete(LinkedInUser user);
	LinkedInUser retrieve(String username);
	List<LinkedInUser> retrieveAll();
}
