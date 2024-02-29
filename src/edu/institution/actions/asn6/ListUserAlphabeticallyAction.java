package edu.institution.actions.asn6;

import java.util.Scanner;
import java.util.*;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class ListUserAlphabeticallyAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		List<LinkedInUser> sortUsers = userRepository.retrieveAll();
		Collections.sort(sortUsers, new Comparator<LinkedInUser>() {
			public int compare(LinkedInUser LiUser1, LinkedInUser LiUser2){
				return LiUser1.getUsername().compareToIgnoreCase(LiUser2.getUsername());
			}
		});
		for (LinkedInUser i : sortUsers) {
			System.out.println(i.getUsername());
		}
		return true;
	}

}
