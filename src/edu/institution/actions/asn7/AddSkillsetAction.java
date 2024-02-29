package edu.institution.actions.asn7;

import java.util.Scanner;

import edu.institution.ApplicationHelper;
import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.actions.asn10.TypesOfActions;
import edu.institution.actions.asn10.UndoAction;
import edu.institution.asn2.LinkedInUser;

public class AddSkillsetAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		
		TypesOfActions inAction = new TypesOfActions();
		
		String inSkillset;
		System.out.println("Add skill:");
		inSkillset = scanner.nextLine();
		if (!userRepository.retrieve(loggedInUser.getUsername()).getSkillsets().contains(inSkillset)) {
			userRepository.retrieve(loggedInUser.getUsername()).addSkillset(inSkillset);
			System.out.println(inSkillset+ " has been added to your set.");
			ApplicationHelper.incrementSkillsetCount(inSkillset);
			
			userRepository.saveAll();
			
			//this action is pushed to actionHistory.
			inAction.setActionType(TypesOfActions.actionType.ADD_SKILLSET);
			inAction.setSkillsetOfAction(inSkillset);
			UndoAction.actionHistory.push(inAction);
		}
		else 
			System.out.println(inSkillset+ " already exists in your set.");
		
		
		return true;
	}

}
