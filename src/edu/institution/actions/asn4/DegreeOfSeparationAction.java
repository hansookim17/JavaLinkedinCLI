package edu.institution.actions.asn4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class DegreeOfSeparationAction implements MenuAction {

	ArrayList<String> arr = new ArrayList<>();

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		System.out.println("Enter the user to find degree of separation");
		String endUsername = scanner.nextLine();
		// validate start user has connections
		try {
			if (loggedInUser.getConnections().size() > 0) {
				arr.add(loggedInUser.getUsername());
				// validate end user exists
				if (userRepository.retrieve(endUsername) == null) {
					System.out.println("That user does not exist");
					return true;
				} else {
					recursion(loggedInUser, userRepository, endUsername);
					System.out.println(
							"There is " + (arr.size() - 1) + " degrees of seperation between you and " + endUsername);
					System.out.print(loggedInUser.toString() + " -> ");
					for (int i = 0; i < arr.size(); i++) {
						if (arr.indexOf(arr.get(i)) == arr.size() - 1) {
							System.out.println(arr.get(i));
						} else {
							System.out.print(arr.get(i) + " -> ");
						}
					}
				}
			}
		} catch (LinkedInException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public void recursion(LinkedInUser linkedInUser, UserRepository userRepository, String endUsername) {
		// base case
		if (linkedInUser.toString().equals(endUsername)) {
			return;
		}
		try {
			for (int i = 0; i < linkedInUser.getConnections().size(); i++) {
				LinkedInUser subUser = userRepository.retrieve(linkedInUser.getConnections().get(i).toString());
				if (!arr.contains(subUser.getUsername())) {
					arr.add(subUser.getUsername());
					recursion(subUser, userRepository, endUsername);
				}
			}
		} catch (LinkedInException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
