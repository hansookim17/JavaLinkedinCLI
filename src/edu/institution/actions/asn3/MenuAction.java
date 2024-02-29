package edu.institution.actions.asn3;
import java.util.*;

import edu.institution.UserRepository;
import edu.institution.asn2.*;

public interface MenuAction {
	/*
	 * @param scanner:			the scanner accepting user input.
	 * @param userRepository:	the user repository.
	 * @param loggedInUser:		the logged in user.
	 * @return:	returns true if the logged in user should remain logged in; false if they should be logged out.
	 * 
	 */
	
	boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser);
}
