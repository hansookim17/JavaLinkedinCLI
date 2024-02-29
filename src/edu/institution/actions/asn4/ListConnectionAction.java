package edu.institution.actions.asn4;

import java.util.*;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class ListConnectionAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		//loop through the logged in user's connections and display the 
		//			usernames of each and every LinkedInUser in their 
		//			connections list.
		//if connections.size() == 0, say "You have no connections.
		//return true.
		
		try {
			// if getConnections().size() == 0, it's going to throw an exception.
			// [TEST] System.out.println("loggedInUser.getUsername() == "
			// +userRepository.retrieve(loggedInUser.getUsername()).getUsername());

			for (int i = 0; i < userRepository.retrieve(loggedInUser.getUsername()).getConnections().size(); i++)
				System.out.println(
						userRepository.retrieve(loggedInUser.getUsername()).getConnections().get(i).getUsername());

		} catch (LinkedInException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

}
