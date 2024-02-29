package edu.institution.asn5;
import edu.institution.actions.asn5.Utilities;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import edu.institution.asn2.*;

public class Asn5Test {
	@Test
	public void removeDuplicateTest() {
		Utilities utilities = new Utilities();
		//TEST 1: STRINGS
		
		List<String> inStrings = new ArrayList<>();
		inStrings.add("Walter");
		inStrings.add("Alice");
		inStrings.add("Alice");
		inStrings.add("Arlene");
		inStrings.add("Arlene");
		inStrings.add("Horace");
		
		List<String> outStrings = new ArrayList<>();
		outStrings.add("Walter");
		outStrings.add("Alice");
		outStrings.add("Horace");
		outStrings.add("Arlene");
		
		inStrings = utilities.removeDuplicates(inStrings);
		
		Collections.sort(inStrings);
		Collections.sort(outStrings);
		
		Assert.assertTrue(inStrings.equals(outStrings));
		
		
		//TEST 2: LINKEDINUSERS
		LinkedInUser imran = new LinkedInUser("Imran","imran", "S");
		LinkedInUser jerrod = new LinkedInUser("Jerrod","jerrod", "S");
		LinkedInUser kathryn = new LinkedInUser("Kathryn","kathryn","S");
		
		List<LinkedInUser> inUsers = new ArrayList<>();
		inUsers.add(imran);
		inUsers.add(imran);
		inUsers.add(kathryn);
		inUsers.add(jerrod);
		
		List<LinkedInUser> outUsers = new ArrayList<>();
		outUsers.add(jerrod);
		outUsers.add(imran);
		outUsers.add(kathryn);
		
		//[TEST] System.out.println("input before: \n" +input);
		inUsers = utilities.removeDuplicates(inUsers);
		//[TEST] System.out.println("input after: \n" +input);
		
		Collections.sort(inUsers);
		Collections.sort(outUsers);
		
		//[TEST] System.out.println("Sorted names: " +input);
		//[TEST] System.out.println("Sorted output: " +output);
		
		Assert.assertTrue(inUsers.equals(outUsers));
		
		
		//TEST 3: NUMERICAL VALUES
		List<Integer> inNumbers = new ArrayList<>();
		inNumbers.add(1);
		inNumbers.add(2);
		inNumbers.add(1);
		inNumbers.add(1);
		inNumbers.add(1);
		inNumbers.add(1);
		inNumbers.add(1);
		
		List<Integer> outNumbers = new ArrayList<>();
		outNumbers.add(1);
		outNumbers.add(2);
		
		inNumbers = utilities.removeDuplicates(inNumbers);
		
		Collections.sort(inNumbers);
		Collections.sort(outNumbers);
		
		Assert.assertTrue(inNumbers.equals(outNumbers));
	}
	
	@Test
	public void test2() {
		Utilities utilities = new Utilities();
		
		//TEST 1: STRINGS
		List<String> inStrings2 = new ArrayList<>();
		
		String outString2;
		
		inStrings2.add("Walter");
		inStrings2.add("Arlene");
		inStrings2.add("Horace");
		inStrings2.add("Alice");
		
		outString2 = utilities.linearSearch(inStrings2, "Walter");
		
		Assert.assertEquals("Walter", outString2);
		
		
		//TEST 2: LINKEDINUSERS
		List<LinkedInUser> inUsers2 = new ArrayList<>();
		
		LinkedInUser hansoo = new LinkedInUser("HanSoo","hansoo", "S");
		LinkedInUser hangil = new LinkedInUser("HanGil","hangil", "S");
		LinkedInUser shane = new LinkedInUser("Shane","shane","S");
		LinkedInUser deborah = new LinkedInUser("Deborah","deborah","S");
		LinkedInUser outUser2;
		
		inUsers2.add(hansoo);
		inUsers2.add(deborah);
		inUsers2.add(shane);
		inUsers2.add(hangil);
		
		outUser2 = utilities.linearSearch(inUsers2,hansoo);
		
		//System.out.println("output = " +output);
		
		Assert.assertEquals(hansoo, outUser2);
		
		
		//TEST 3: NUMERICAL VALUES
		List<Integer> inNumbers2 = new ArrayList<>();
		
		int outNumbers2;
		
		inNumbers2.add(1);
		inNumbers2.add(2);
		inNumbers2.add(3);
		inNumbers2.add(4);
		inNumbers2.add(5);
		inNumbers2.add(6);
		
		outNumbers2 = utilities.linearSearch(inNumbers2, 5);
		
		Assert.assertEquals(outNumbers2, 5);
		
	}
}
