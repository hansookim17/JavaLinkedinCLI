package edu.institution.actions.asn5;

import java.util.*;
import edu.institution.asn2.*;

public class Utilities {
	
	public <E> List<E> removeDuplicates(List<E> inputList) {
		
		if (inputList == null || inputList.size() == 0) {
			System.out.println("null list");
			return null;
		}
		
		List<E> tempList = new ArrayList<E>();
		
		//[TEST] System.out.println("String: "  +inputList.stream().allMatch(String.class::isInstance));
		//[TEST] System.out.println("Integer: " +inputList.stream().allMatch(Integer.class::isInstance));
		//[TEST] System.out.println("Double: "  +inputList.stream().allMatch(Double.class::isInstance));
		
		//checks if Generic type is a String
		if (inputList.stream().allMatch(String.class::isInstance)){
			for (int i = 0; i < inputList.size(); i++) {
				//index j for tempList
				int j; 
				
				//we add our first element of inputItems since tempList starts off empty.
				if (i == 0) 
					tempList.add(inputList.get(i));
				else {
					for (j = 0; j < tempList.size(); j++) {
						//if one of inputList's values is equal to one of tempList's value, it stops the for-loop and moves on to
						//		the next of inputList's values.
						if (((String) inputList.get(i)).equalsIgnoreCase((String) tempList.get(j)))
							break;
						//if we're at tempList's last value and the last value does not equal to inputList[i], then we can 
						//		assume that inputList[i] is not a duplicate.
						else if ((j == tempList.size() - 1) && !(inputList.get(i).equals(tempList.get(j))))
							tempList.add(inputList.get(i));
					}
				}
			}
		}
		//checks if Generic type is a LinkedInUser
		else if (inputList.stream().allMatch(LinkedInUser.class::isInstance)) {
			for (int i = 0; i < inputList.size(); i++) {
				//index j for tempList
				int j; 
				
				//we add our first element of inputItems since tempList starts off empty.
				if (i == 0) 
					tempList.add(inputList.get(i));
				else {
					for (j = 0; j < tempList.size(); j++) {
						//if one of inputList's values is equal to one of tempList's value, it stops the for-loop and moves on to
						//		the next value of inputList. It checks equivalence via reading their usernames.
						if (((LinkedInUser) inputList.get(i)).getUsername()
								.equals(((LinkedInUser) tempList.get(j)).getUsername()))
							break;
						//if we're at tempList's last value and the last value does not equal to inputList[i], then we can 
						//		assume that inputList[i] is not a duplicate.
						else if ((j == tempList.size() - 1) && !(((LinkedInUser) inputList.get(i)).getUsername()
								.equals(((LinkedInUser) tempList.get(j)).getUsername())))
							tempList.add(inputList.get(i));
					}
				}
			}
		}
		//checks if Generic type is a numeric value (or anything else)
		else if (inputList.stream().allMatch(Integer.class::isInstance) || inputList.stream().allMatch(Double.class::isInstance)) {
			for (int i = 0; i < inputList.size(); i++) {
				//index j for tempList
				int j; 
				
				//we add our first element of inputItems since tempList starts off empty.
				if (i == 0) 
					tempList.add(inputList.get(i));
				else {
					for (j = 0; j < tempList.size(); j++) {
						//if one of inputList's values is equal to one of tempList's value, it stops the for-loop and moves on to
						//		the next of inputList's values.
						if (inputList.get(i).equals(tempList.get(j)))
							break;
						//if we're at tempList's last value and the last value does not equal to inputList[i], then we can 
						//		assume that inputList[i] is not a duplicate.
						else if ((j == tempList.size() - 1) && !(inputList.get(i).equals(tempList.get(j))))
							tempList.add(inputList.get(i));
					}
				}
			}
		}
		return tempList;
	}
	
	public <E> E linearSearch(List<E> inputList, E key) {
		
		//checks if inputList is empty or null
		if (inputList == null || inputList.size() == 0) {
			System.out.println("null list.");
			return null;
		}
		//next, it checks if inputList's typing is the same as key's.
		else if (!key.getClass().equals(inputList.get(0).getClass())) {
			System.out.println("Generic typings do not match.");
			return null;
		}
		//
		else {
			// checks if Generic type is a String
			if (inputList.stream().allMatch(String.class::isInstance)) {
				if (inputList.contains(key))
					return inputList.get(inputList.indexOf(key));
				else
					return null;
			}
			// checks if Generic type is a LinkedInUser
			else if (inputList.stream().allMatch(LinkedInUser.class::isInstance)) {
				if (inputList.contains((LinkedInUser) key))
					return inputList.get(inputList.indexOf((LinkedInUser) key));
				else
					return null;
			}
			// checks if Generic type is a numeric value
			else if (inputList.stream().allMatch(Integer.class::isInstance)
					|| inputList.stream().allMatch(Double.class::isInstance)) {
				if (inputList.contains((Integer) key) || inputList.contains((Double) key))
					return inputList.get(inputList.indexOf(key));
				else
					return null;
			}
		}
		//returns the location(?) of key in inputList
		return null;
	}
}
