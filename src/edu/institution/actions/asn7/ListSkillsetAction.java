package edu.institution.actions.asn7;

import java.util.Scanner;

import edu.institution.ApplicationHelper;
import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;

public class ListSkillsetAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		if (userRepository.retrieve(loggedInUser.getUsername()).getSkillsets().size() > 0) {
			System.out.println("Here are your skill sets: \n");

			for (String i : userRepository.retrieve(loggedInUser.getUsername()).getSkillsets()) {
				System.out.print(i + " (" + ApplicationHelper.retrieveSkillsetCount(i) + " user)");
				//grammar mechanic
				if (ApplicationHelper.retrieveSkillsetCount(i) > 1)
					System.out.print("s");
				System.out.print(". \n");
			}
		} else
			System.out.println("There exists no skillsets.");
		return true;
	}

}
