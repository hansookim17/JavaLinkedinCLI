package edu.institution.actions.asn7;

import java.util.Scanner;

import edu.institution.ApplicationHelper;
import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn10.TypesOfActions;
import edu.institution.actions.asn10.UndoAction;
import edu.institution.asn2.LinkedInUser;

public class RemoveSkillsetAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		TypesOfActions inAction = new TypesOfActions();
		
		String inSkillset;
		System.out.println("Input skill to remove:");
		inSkillset = scanner.nextLine();
		if (userRepository.retrieve(loggedInUser.getUsername()).getSkillsets().contains(inSkillset)) {
			userRepository.retrieve(loggedInUser.getUsername()).removeSkillset(inSkillset);
			System.out.println(inSkillset+ " has been removed from your set.");
			ApplicationHelper.decrementSkillsetCount(inSkillset);
			
			userRepository.saveAll();
			
			inAction.setActionType(TypesOfActions.actionType.REMOVE_SKILLSET);
			inAction.setSkillsetOfAction(inSkillset);
			UndoAction.actionHistory.push(inAction);
		}
		else 
			System.out.println(inSkillset+ " does not exist in your set.");
		
		return true;
	}

}
