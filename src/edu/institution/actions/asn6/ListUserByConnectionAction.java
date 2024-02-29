package edu.institution.actions.asn6;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.*;

public class ListUserByConnectionAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		List<LinkedInUser> sortUsers = userRepository.retrieveAll();
		
		Collections.sort(sortUsers, new Comparator<LinkedInUser>() {
			public int compare(LinkedInUser LiUser1, LinkedInUser LiUser2){
				try {
					if (LiUser1.getConnections().equals(null) || LiUser2.getConnections().equals(null)) 
						throw new LinkedInException("Null Connections.");
					 else {
						return LiUser2.getConnections().size() - LiUser1.getConnections().size();
					 }
				} catch (LinkedInException e) {
					System.out.println(e.getMessage());
				}
				return -500;
			}
		});
		try {
			for (LinkedInUser i : sortUsers) {
				if (i.getConnections().equals(null))
					throw new LinkedInException("Null Connection(s).");
				System.out.println(i.getUsername() + "; connections size = " + i.getConnections().size());
			}
		} catch (LinkedInException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}
}
