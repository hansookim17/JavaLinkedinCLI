package edu.institution.actions.asn3;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.*;
import edu.institution.actions.asn10.*;

public class AddUserAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		userRepository.init(userRepository.getFilePath(), userRepository.getFileName());
		
		TypesOfActions inAction = new TypesOfActions();
		
		//initializes inUsername, inPassword, and inType
		System.out.print("Enter username: ");
		String inUsername = scanner.nextLine();
		System.out.print("Enter password: ");
		String inPassword = scanner.nextLine();
		System.out.print("Enter type: ");
		String inType = scanner.nextLine();
		while (!(inType.equals("P") || inType.equals("S"))) {
			System.out.println("Invalid user type. Valid types are P or S");
			inType = scanner.nextLine();
			if (inType.equalsIgnoreCase("P"))
				inType = "P";
			else if (inType.equalsIgnoreCase("S"))
				inType = "S";
		}
		
		LinkedInUser newUser = new LinkedInUser(inUsername,inPassword);
		newUser.setType(inType);
		
		try {
			userRepository.add(newUser);
			System.out.println("Added " +newUser.getUsername()+ " to the list.");
			userRepository.saveAll();
			
			//this action is pushed to actionHistory.
			inAction.setActionType(TypesOfActions.actionType.SIGNUP_USER);
			inAction.setUserOfAction(userRepository.retrieve(inUsername));
			UndoAction.actionHistory.push(inAction);
		} catch (LinkedInException e) {
			e.getMessage();
		}
		
		return true;
	}

}
