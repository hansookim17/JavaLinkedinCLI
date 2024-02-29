package edu.institution.actions.asn3;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn10.TypesOfActions;
import edu.institution.actions.asn10.UndoAction;
import edu.institution.asn2.*;

public class DeleteUserAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		TypesOfActions inAction = new TypesOfActions();
		
		userRepository.init(userRepository.getFilePath(), userRepository.getFileName());
		
		String inUsername = null;
		String inPassword = null;
		Boolean stop = false;
		
		//"while there exists a LinkedInUser with the given inUsername, AND the respective password matches inPassword"
		while (!stop) {
			System.out.println("Enter username to delete:");
			inUsername = scanner.nextLine();
			
			//if no one in userRepository holds inUsername, then the outer-most while-loop reiterates.
			if (!userRepository.retrieveAll().contains(userRepository.retrieve(inUsername))) {
				System.out.println("Invalid name.");
				inUsername = null;
			}
			//otherwise, it now asks you for the user's password
			else {
				System.out.println("Input password for " + userRepository.retrieve(inUsername).getUsername());
				inPassword = scanner.nextLine();
				//if the inputted password is wrong, the audience must start all over at the outer-most while-loop
				if (!inPassword.equals(userRepository.retrieve(inUsername).getPassword())) {
					System.out.println("Invalid Password.");
					inUsername = null;
					inPassword = null;
				}
				//otherwise, it will now call to remove the LinkedInUser from the Repository, and finally leave the while-loop (via assigning the Boolean stop as true.
				else {
					stop = true;
					
					try {
						if (!userRepository.retrieveAll().contains(userRepository.retrieve(inUsername)))
							throw new LinkedInException();
						else {
							LinkedInUser scapegoat = userRepository.retrieve(inUsername);
							for (LinkedInUser i : userRepository.retrieveAll()) {
								if (userRepository.retrieve(i.getUsername()).getConnections().contains(userRepository.retrieve(inUsername)))
									userRepository.retrieve(i.getUsername()).removeConnection(userRepository.retrieve(inUsername));
							}
							userRepository.delete(userRepository.retrieve(inUsername));
							System.out.println("Removed " + inUsername + " from the list.");
							userRepository.saveAll();
							
							//this action is pushed to actionHistory.
							inAction.setActionType(TypesOfActions.actionType.DELETE_USER);
							inAction.setUserOfAction(scapegoat);
							UndoAction.actionHistory.push(inAction);
						}
					} catch (LinkedInException e) {
						e.getMessage();
					}
					
				}
			}
		}
		//if the audience deleted a logged-in user, we must now sign the user off.
		if (inUsername.equals(loggedInUser.getUsername())) {
			System.out.println("Logging off...");
			
			return false;
		}
		else
			return true;
	}

}
