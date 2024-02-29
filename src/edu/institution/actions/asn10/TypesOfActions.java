package edu.institution.actions.asn10;

import edu.institution.asn2.*;

public class TypesOfActions {
	
	public enum actionType {
		ADD_CONNECTION, REMOVE_CONNECTION,	//involves linkedInUsers
		ADD_SKILLSET, REMOVE_SKILLSET,		//involves Strings
		SIGNUP_USER, DELETE_USER,			//involves linkedInUsers
	};
	
	actionType currentAction;
	private String actionDesc;
	private LinkedInUser userOfAction;
	private String skillsetOfAction;
	
	
	//"What type does this action represent?"
	public actionType getActionType() {
		return currentAction;
	}
	public String getActionDesc() {
		return actionDesc;
	}
	public void setActionType(actionType inAction) {
		currentAction = inAction;
		
		if (inAction.equals(actionType.ADD_CONNECTION))
			actionDesc = "Add Connection";
		else if (inAction.equals(actionType.REMOVE_CONNECTION))
			actionDesc = "Remove Connection";
		else if (inAction.equals(actionType.ADD_SKILLSET))
			actionDesc = "Add Skillset";
		else if (inAction.equals(actionType.REMOVE_SKILLSET))
			actionDesc = "Remove Skillset";
		else if (inAction.equals(actionType.SIGNUP_USER))
			actionDesc = "Signup User";
		else if (inAction.equals(actionType.DELETE_USER))
			actionDesc = "Delete User";
	}
	
	
	//"Which LinkedInUser does the action involve, if any?"
	public LinkedInUser getUserOfAction() {
		return userOfAction;
	}
	public void setUserOfAction(LinkedInUser inUser) {
		userOfAction = inUser;
	}
	
	//"Which Skillset does the action involve, if any?"
	public String getSkillsetOfAction() {
		return skillsetOfAction;
	}
	public void setSkillsetOfAction(String inSkillset) {
		skillsetOfAction = inSkillset;
	}
	
}
