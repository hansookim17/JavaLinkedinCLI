package edu.institution.actions.asn4;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn10.TypesOfActions;
import edu.institution.actions.asn10.UndoAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;

public class RemoveConnectionAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		//prompt for user name of the connection to remove.
		//if user does not exist at all, say "There 
		//			is no user with that user name."
		//if user isn't connected with loggedInUser,
		//			throw an Exception from asn 2.
		//if user IS connected with loggedInUser,
		//			perform it and say, 
		//			"The connection was removed successfully."
		//return true afterwards.
		TypesOfActions inAction = new TypesOfActions();
		
			
		System.out.println("Input username of whom to disconnect:");
		String inUsername = scanner.nextLine();
		if (userRepository.retrieve(inUsername).equals(null))
			System.out.println("There is no user with that username.");
		else {
			try {
				userRepository.retrieve(loggedInUser.getUsername()).removeConnection(userRepository.retrieve(inUsername));
				userRepository.retrieve(inUsername).removeConnection(userRepository.retrieve(loggedInUser.getUsername()));
				
				System.out.println("The connection was removed successfully.");
				
				//pushes action to actionHistory
				inAction.setActionType(TypesOfActions.actionType.REMOVE_CONNECTION);
				inAction.setUserOfAction(userRepository.retrieve(inUsername));
				UndoAction.actionHistory.push(inAction);
				
				userRepository.saveAll();
			} catch (LinkedInException ex) {
				ex.getMessage();
			}
		}
		return true;
	}
}
