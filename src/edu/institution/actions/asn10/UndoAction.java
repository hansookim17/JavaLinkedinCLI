package edu.institution.actions.asn10;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import edu.institution.*;
import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInException;
import edu.institution.asn2.LinkedInUser;
import edu.institution.actions.asn10.*;

public class UndoAction implements MenuAction {
	public static GenericStack<TypesOfActions> actionHistory = new GenericStack<>();

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		if (!actionHistory.isEmpty()) {
			String inputString = "";
			while (!inputString.equals("Y") && !inputString.equals("N")) {
				System.out.print(
						"The last option selected was '" + actionHistory.peek().getActionDesc() + "' involving ");

				if (actionHistory.peek().getActionType().equals(TypesOfActions.actionType.ADD_CONNECTION)
						|| actionHistory.peek().getActionType().equals(TypesOfActions.actionType.REMOVE_CONNECTION)
						|| actionHistory.peek().getActionType().equals(TypesOfActions.actionType.SIGNUP_USER)
						|| actionHistory.peek().getActionType().equals(TypesOfActions.actionType.DELETE_USER)) {
					System.out.print(actionHistory.peek().getUserOfAction().getUsername());
				} else {
					System.out.print(actionHistory.peek().getSkillsetOfAction());
				}

				System.out.println(". Undo? (Y/N)");

				inputString = scanner.nextLine();
				if (!inputString.equalsIgnoreCase("Y") && !inputString.equalsIgnoreCase("N")) {
					System.out.println("Invalid Response; try again.");
					inputString = "0";
				}
			}
			if (inputString.equalsIgnoreCase("Y")) {
				// UNDOING addConnection -> removeConnection basically
				if (actionHistory.peek().getActionType().equals(TypesOfActions.actionType.ADD_CONNECTION)) {
					try {
						userRepository.retrieve(loggedInUser.getUsername()).removeConnection(userRepository.retrieve(actionHistory.peek().getUserOfAction().getUsername()));
						userRepository.retrieve(actionHistory.peek().getUserOfAction().getUsername()).removeConnection(userRepository.retrieve(loggedInUser.getUsername()));
						System.out.println("The connection has been removed again successfully");
						actionHistory.pop();
					} catch (LinkedInException e) {
						e.printStackTrace();
					}
				// UNDOING removeConnection -> addConnection basically
				} else if (actionHistory.peek().getActionType().equals(TypesOfActions.actionType.REMOVE_CONNECTION)) {
					try {
						userRepository.retrieve(loggedInUser.getUsername()).addConnection(userRepository.retrieve(actionHistory.peek().getUserOfAction().getUsername()));
						userRepository.retrieve(actionHistory.peek().getUserOfAction().getUsername()).addConnection(userRepository.retrieve(loggedInUser.getUsername()));
						System.out.println("The connection was restored successfully.");
						actionHistory.pop();
					} catch (LinkedInException e) {
						e.printStackTrace();
					}
				// UNDOING addSkillset -> removeSkillset basically
				} else if (actionHistory.peek().getActionType().equals(TypesOfActions.actionType.ADD_SKILLSET)) {
					userRepository.retrieve(loggedInUser.getUsername()).removeSkillset(actionHistory.peek().getSkillsetOfAction());
					ApplicationHelper.decrementSkillsetCount(actionHistory.peek().getSkillsetOfAction());
					System.out.println(actionHistory.peek().getSkillsetOfAction()+ " has been removed back from your set.");
					actionHistory.pop();
				// UNDOING removeSkillset -> addSkillset basically
				} else if (actionHistory.peek().getActionType().equals(TypesOfActions.actionType.REMOVE_SKILLSET)) {
					userRepository.retrieve(loggedInUser.getUsername()).addSkillset(actionHistory.peek().getSkillsetOfAction());
					ApplicationHelper.incrementSkillsetCount(actionHistory.peek().getSkillsetOfAction());
					System.out.println(actionHistory.peek().getSkillsetOfAction()+ " has been added back to your set.");
					actionHistory.pop();
				// UNDOING addUser -> deleteUser basically
				} else if (actionHistory.peek().getActionType().equals(TypesOfActions.actionType.SIGNUP_USER)) {
					// since there are no actions between creating this brand new user and undoing
					// it, we can assume this user has zero connections.
					userRepository.delete(userRepository.retrieve(actionHistory.peek().getUserOfAction().getUsername()));
					System.out.println("Removed " +actionHistory.peek().getUserOfAction().getUsername()+ " back from the list.");
					actionHistory.pop();

				// UNDOING deleteUser -> addUser basically
				} else if (actionHistory.peek().getActionType().equals(TypesOfActions.actionType.DELETE_USER)) {
					try {
						userRepository.add(actionHistory.peek().getUserOfAction());
						// reconnecting with everyone who were in their connections list
						for (LinkedInUser i : actionHistory.peek().getUserOfAction().getConnections()) {
							userRepository.retrieve(i.getUsername()).addConnection(userRepository.retrieve(actionHistory.peek().getUserOfAction().getUsername()));
						}
						System.out.println("Added " +actionHistory.peek().getUserOfAction().getUsername()+ " back to the list.");
						actionHistory.pop();
					} catch (LinkedInException e) {
						System.out.println(e.getMessage());
					}
				}
			} else {
				System.out.println("No action reverted.");
			}

			userRepository.saveAll();
		} else {
			System.out.println("There are no actions performed...yet!");
		}
		return true;
	}

}
