/*
 Copyright (C) 2020. Doug Estep -- All Rights Reserved.
 Copyright Registration Number: TXU002159309.

 This file is part of the Tag My Code application.

 This application is protected under copyright laws and cannot be used, distributed, or copied without prior written
 consent from Doug Estep.  Unauthorized distribution or use is strictly prohibited and punishable by domestic and
 international law.

 Proprietary and confidential.
 */
package edu.institution;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import edu.institution.asn2.LinkedInUser;


/**
 * Contains static helper methods to aid with the command line user interface.
 */
public class ApplicationHelper {

	/**
	 * Displays the supplied message to the console.
	 * 
	 * @param message the message.
	 */
	
	
	//The Collection will hold the type of a TreeMap<String, Integer>.
	//We will use this Collection in a statistical manner.
	//		We will record data in the form of: "How many elements belong in which groups?"
	//The skillset (in String type) will represent the Hashmap key
	//		in order to represent them as unreplicable intersecting groups.
	//The amount of people (in Integer type) will represent the Hashmap value.
	//		Two different groups can have the same amount of people.
	//In addition, we will be using Treemaps to automatically sort elements into 
	//		alphabetical order.
	private static TreeMap<String, Integer> contraPoints = new TreeMap<>();
	
	public static void showMessage(String message) {
		System.out.println("\n" + message);
	}
	
	//this method answers the question: "how many users contain the supplied skillset?"
	public static void incrementSkillsetCount(String skillset) {
		//key: skillset
		//value: number of users with that skillset
		
		//Q: "if this skillset doesn't exist before input, add a new Hashmap of that skillset."
		//		first, ask the boolean question if the skillset already exists. if (contraPoints.containsKey(skillset));
		//			if it already exists, simply increment its respective Integer by one. contraPoints.replace(skillset, contraPoints.get(skillset) + 1);
		//			if it doesn't already exist, initialize a new Hashmap under that skillset with its default value of 1. contraPoints.put(skillset, 1);
		if (!contraPoints.containsKey(skillset)) {
			contraPoints.put(skillset, 1);
		}
		else
			contraPoints.replace(skillset, contraPoints.get(skillset) + 1);
		
	}
	
	public static void decrementSkillsetCount(String skillset) {
		//Q: "if a skillset reaches zero, remove it from the Collection of Hashmaps under that skillset."
		//		first, decrement the skillset's Integer by 1. contraPoints.replace(skillset, contraPoints.get(skillset)-1);
		//		then, ask the boolean question if a skillset's Integer reached to zero.
		//			if Integer == 0, remove it from the Collection.
		//			if Integer > 0, leave it be.
		
		if (contraPoints.containsKey(skillset)) {
			contraPoints.replace(skillset, contraPoints.get(skillset) - 1);
			if (contraPoints.get(skillset) == 0) {
				//System.out.println("Now no user has this skill.");
				contraPoints.remove(skillset);
			}
		}
	}
	
	public static int retrieveSkillsetCount(String skillset) {
		//Q: "How many people have this skillset?"
		//		First, ask the boolean question if the skillset exists in the Collection. if (contraPoints.containsKey(skillset))
		//			If not, return -1;
		//			If so, return contraPoints.get(skillset);
		if (!contraPoints.containsKey(skillset))
			return -1;
		else {
			return contraPoints.get(skillset);
		}
	}
	
	public static void initSkillsetUsages(List<LinkedInUser> users) {
		//Q: take this list of LinkedInUsers and record/increment their skillsets onto a list.
		
		//for (LinkedInUser i : users) {
		//		for (String j : i.getSkillsets())
		//				incrementSkillsetCount(j);
		//}
		try {
			for (LinkedInUser i : users) {
				for (String j : i.getSkillsets())
					incrementSkillsetCount(j);
			}
		} catch (Exception e) {
			System.out.println("hey beter");
		}
	}
	
	
	
	
}
