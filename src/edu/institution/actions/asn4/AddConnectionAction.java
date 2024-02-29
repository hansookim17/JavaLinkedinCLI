package edu.institution.actions.asn4;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;
import edu.institution.actions.asn10.*;

public class AddConnectionAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		//Prompt for the username of the user to connect with.
		//If the user doesn't exists, display the message 
		//			"There is no user with that username"
		//If it does exist, add the user in connection list
		//			and say "The connection was added successfully"
		//If it already exists, throw the Exception implemented 
		//			from assignment 2.
		TypesOfActions inAction = new TypesOfActions();
		
		System.out.println("Input the username of whom to connect with:");
		String inUsername = scanner.nextLine();
		
		//Program searches in the repository for the user that has this username.
		if (userRepository.retrieve(inUsername).equals(null)) {
			System.out.println("There is no user with that username.");
		}
		else {
			try {
				//loggedInUser.addConnection(userRepository.retrieve(inUsername));
				//userRepository.retrieve(loggedInUser.getUsername()).setConnections(loggedInUser.getConnections());
				
				//loggedInUser connects to targetUser, and targetUser reciprocates the action.
				userRepository.retrieve(loggedInUser.getUsername()).addConnection(userRepository.retrieve(inUsername));
				userRepository.retrieve(inUsername).addConnection(userRepository.retrieve(loggedInUser.getUsername()));
				
				//LinkedInUser inUserAccount = userRepository.retrieve(inUsername);
				//userRepository.retrieve(loggedInUser.getUsername()).addConnection(inUserAccount);
				
				boolean help = userRepository.retrieve(loggedInUser.getUsername()).getConnections()
						.contains(userRepository.retrieve(inUsername));

				System.out.println("The connection was added successfully.");
				
				//this action is pushed to actionHistory.
				inAction.setActionType(TypesOfActions.actionType.ADD_CONNECTION);
				inAction.setUserOfAction(userRepository.retrieve(inUsername));
				UndoAction.actionHistory.push(inAction);

			} catch (LinkedInException e) {
				System.out.println(e.getMessage());
			}
		}
		userRepository.saveAll();
		return true;
	}

}
