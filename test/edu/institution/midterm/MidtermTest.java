package edu.institution.midterm;

import java.util.*;
import org.junit.Test;
import org.junit.Assert;
import edu.institution.midterm.*;

public class MidtermTest {

	PartManagerImpl nice = new PartManagerImpl();
	final String FILE_PATH = "src/edu/institution/midterm/bom.json";
	
	@Test
	public void importPartStoreTest() {
		System.out.println("***ASSEMBLED LIST TEST***");
		//nice.importPartStore(FILE_PATH); returns the size of the list of non-duplicated parts.
		//there are 79 unduplicated parts in the master roster.
		int listSize = nice.importPartStore(FILE_PATH);
		System.out.println("listSize: "+listSize);
		Assert.assertEquals(79, listSize);
		
		//using importPartStore() with an invalid file path
		int invalidSize = nice.importPartStore(FILE_PATH+"s");
		Assert.assertEquals(-1, invalidSize);
		System.out.println();
	}
	
	@Test
	public void assembledListTest() {
		System.out.println("***ASSEMBLED LIST TEST***");
		int assemblyCount = 0;
		nice.importPartStore(FILE_PATH);
		
		//pulls up a list of Assembly Parts
		List <Part> assemblyList = nice.getFinalAssemblies();
		
		for (Part i : nice.getPartArray()) {
			if (i.getPartType().equals("ASSEMBLY"))
				assemblyCount++;
		}
		
		/* [TEST]*/
		System.out.println("assemblyCount == " + assemblyCount + ",  assemblyList.size() == "
				+ assemblyList.size()); 
		for (Part j : assemblyList) {
			System.out.println(j.getPartType()+ ", " +j.getPartNumber());
		}
		System.out.println();
		Assert.assertEquals(assemblyCount, assemblyList.size());
	}
	
	@Test
	public void decreasingPriceListTest() {
		System.out.println("***DECREASING PRICE LIST TEST***");
		nice.importPartStore(FILE_PATH);
		List <Part> pricey = nice.getPurchasePartsByPrice();
		/* [TEST]*/
		
		//
		boolean decreasing = true;
		for (Part i : pricey) {
			System.out.println(i.getPartNumber()+ ", " +i.getPartType()+ ", " +i.getPrice());
		}
		for (int i = 1; i < pricey.size(); i++) {
			//if one of the entries is out of order, then it's no longer in a properly descending order.
			if (!(pricey.get(i).getPrice() <= pricey.get(i - 1).getPrice())) {
				decreasing = false;
				break;
			}
		}
		//System.out.println("size == " +pricey.size());
		System.out.println();
		Assert.assertTrue(decreasing);
	}
	
	@Test
	public void retrievePartTest() {
		System.out.println("***RETRIEVE PART TEST***");
		nice.importPartStore(FILE_PATH);
		
		//
		Part inPart = nice.retrievePart("290B7266J1");
		System.out.println("inPart.getPartNumber(): " +inPart.getPartNumber()+ ".");
		Assert.assertEquals(nice.getPartList().get(nice.getPartList().indexOf(nice.retrievePart("290B7266J1"))), inPart);
		
		//inputting an invalid Part
		Part invalidPart = nice.retrievePart("peasdfabe");
		Assert.assertEquals(null, invalidPart);
		
		System.out.println();
	}
	
	@Test
	public void costPartsTest() {
		System.out.println("***COST PARTS TEST***");
		nice.importPartStore(FILE_PATH);
		
		//The third parametre of the assertEquals test signifies margin of error. it's set 
		//		just below 0.01 to disregard negligible decimal places.
		//partNumber 290B7266J1 costs $415.16
		Assert.assertEquals(415.16f, nice.costPart("290B7266J1").getPrice(), 0.009f);
		//partNumber 290B7266J2 costs $532.20
		Assert.assertEquals(532.2f, nice.costPart("290B7266J2").getPrice(), 0.009f);
		//partNumber 290B7266J6 costs $334.10
		Assert.assertEquals(334.1f, nice.costPart("290B7266J6").getPrice(), 0.009f);
		//partNumber 290B2350P2 costs $54.75
		Assert.assertEquals(54.75f, nice.costPart("290B2350P2").getPrice(), 0.009f);
		//partNumber 290B3810T1 costs $10.95
		Assert.assertEquals(10.95f, nice.costPart("290B3810T1").getPrice(), 0.009f);
		//partNumber 20-0001 costs $96.39
		Assert.assertEquals(96.39f, nice.costPart("20-0001").getPrice(), 0.009f);
		//partNumber 20-0015 costs $70.46
		Assert.assertEquals(70.46f, nice.costPart("20-0015").getPrice(), 0.009f);
		
		//inserting invalid partNumber
		Assert.assertEquals(null, nice.costPart("asdfbnwhf"));
		System.out.println();
		
	}
}
