package edu.institution.actions.asn3;

import java.util.Scanner;
import java.util.*;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.*;

public class ListUserAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		userRepository.init(userRepository.getFilePath(), userRepository.getFileName());
		List<LinkedInUser> users = userRepository.retrieveAll();
		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).getUsername());
			//[TEST] System.out.println(users.get(i).getPassword());
		}
		
		return true;
	}

}
