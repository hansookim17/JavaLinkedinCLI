package edu.institution.asn3;

import java.io.File;

import org.junit.Test;
import org.junit.Assert;

import edu.institution.*;
import edu.institution.actions.asn3.*;
import edu.institution.asn2.*;

public class LinkedInTest {
	private static final String PATH = System.getProperty("user.home") + File.separator + "Java2" + File.separator;
	private static final String FILE_NAME = "policies.dat";
	
	@Test
	public void testSerializeAndDeserialize() {
		File file = new File(PATH + FILE_NAME);
		if (file.exists())
			file.delete();
		LinkedInUser horace = new LinkedInUser("Horace","1");
		LinkedInUser alice = new LinkedInUser("Alice","1");
		horace.setType("S");
		alice.setType("P");
		
		SerializedUserRepository sur = new SerializedUserRepository();
		
		sur.init(PATH, FILE_NAME);
		
		try {
			sur.add(alice);
			sur.add(horace);
		} catch (LinkedInException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sur.saveAll();
		for (int i = 0; i < sur.retrieveAll().size(); i++)
			System.out.println(sur.retrieveAll().get(i));
		Assert.assertTrue(file.exists());
		Assert.assertEquals(2, sur.retrieveAll().size());
		Assert.assertEquals("Alice", sur.retrieve("Alice").getUsername());
		Assert.assertEquals("Horace", sur.retrieve("Horace").getUsername());
		
	}
}
