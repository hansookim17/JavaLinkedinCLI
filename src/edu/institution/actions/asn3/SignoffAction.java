package edu.institution.actions.asn3;

import java.util.Scanner;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.LinkedInUser;
import edu.institution.actions.asn10.*;

public class SignoffAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		// TODO Auto-generated method stub
		
		//empties action history.
		while (!UndoAction.actionHistory.isEmpty()) {
			UndoAction.actionHistory.pop();
		}
			
		return false;
	}

}
