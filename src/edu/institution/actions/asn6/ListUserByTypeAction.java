package edu.institution.actions.asn6;

import java.util.*;

import edu.institution.UserRepository;
import edu.institution.actions.MenuAction;
import edu.institution.asn2.*;

public class ListUserByTypeAction implements MenuAction {

	@Override
	public boolean process(Scanner scanner, UserRepository userRepository, LinkedInUser loggedInUser) {
		List<LinkedInUser> sortUsers = userRepository.retrieveAll();
		List<LinkedInUser> sortUsersP = new ArrayList<>();
		List<LinkedInUser> sortUsersS = new ArrayList<>();
		for (LinkedInUser i : sortUsers) {
			if (i.getType().equals("P")) 
				sortUsersP.add(i);
			else if (i.getType().equals("S"))
				sortUsersS.add(i);
			else {
				try {
					throw new LinkedInException(i.getUsername()+ "has a Null/Empty type");
				} catch (LinkedInException e){
					System.out.println(e.getMessage());
				}
			}
		}
		
		Collections.sort(sortUsersP, new Comparator<LinkedInUser>() {
			public int compare(LinkedInUser LiUser1, LinkedInUser LiUser2){
				return LiUser1.getUsername().compareToIgnoreCase(LiUser2.getUsername());
			}
		});
		Collections.sort(sortUsersS, new Comparator<LinkedInUser>() {
			public int compare(LinkedInUser LiUser1, LinkedInUser LiUser2){
				return LiUser1.getUsername().compareToIgnoreCase(LiUser2.getUsername());
			}
		});
		
		for (LinkedInUser i : sortUsersP) {
			System.out.println(i.getUsername()+ "; type = " +i.getType());
		}
		for (LinkedInUser i : sortUsersS) {
			System.out.println(i.getUsername()+ "; type = " +i.getType());
		}
		return true;
	}

}
