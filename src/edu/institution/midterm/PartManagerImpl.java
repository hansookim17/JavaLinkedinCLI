package edu.institution.midterm;

import java.util.*;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class PartManagerImpl implements PartManager {

	private Part[] partsArray;
	private List<Part> partsList = new ArrayList<>();
	
	@Override
	public int importPartStore(String filePath) {
		// initialization phase
		// reads the contents of the bom.son file and writes them into String
		// "open the file that's at the filePath"
		// "create a string out of it and feed that string and class to the gson.fromJson method"
		
		
		FileReader jsonData;
		try {
			jsonData = new FileReader(filePath);
			Gson gson = new Gson();
			partsArray = gson.fromJson(jsonData, Part[].class);
			
			//make another list that contains no duplicates
			//items with the same part numbers constitutes as a duplicate
			//[TEST] System.out.println("making duplicate-free list");
			for (int i = 0; i < partsArray.length; i++) {
				int j;
				
				if (i == 0)
					partsList.add(partsArray[i]);
				else {
					for (j = 0; j < partsList.size(); j++) {
						if (partsArray[i].getPartNumber().equals(partsList.get(j).getPartNumber())){
							/* [TEST]
							System.out.println("Duplicate; partsArray[" + i + "].partNumber == "
									+ partsArray[i].getPartNumber() + ",  partsList.get[" + j + "].partNumber == "
									+ partsList.get(j).getPartNumber()); */
							break;
						}
						else if ((j == partsList.size() - 1) && !(partsArray[i].getPartNumber().equals(partsList.get(j).getPartNumber()))) {
							partsList.add(partsArray[i]);
						}
					}
				}
			}
			
			
			return partsList.size();
		} catch (FileNotFoundException e) {
			System.out.println("Invalid File name/location.");
		}
		return -1;
	}

	//most difficult method
	//recursive
	@Override
	public Part costPart(String partNumber) {
		/* [USES costPart1()]*/
		//System.out.println(retrievePart(partNumber));
		if (retrievePart(partNumber) == (null)) {
			System.out.println("'"+partNumber+ "' is an invalid part number.");
			return null;
		} else {
			retrievePart(partNumber).setPrice(costPart1(partNumber));
			System.out.println("partNumber: " +partNumber+ ". Cost: $" +retrievePart(partNumber).getPrice());
			return retrievePart(partNumber);
		}
	}
	private float costPart1(String partNumber) {
		List<BomEntry> bomList = retrievePart(partNumber).getBillOfMaterial();
		float captPrice = retrievePart(partNumber).getPrice();
		
		// BASE CASE: if the part already has a set price
		//System.out.println(captPrice != 0.0f);
		if (captPrice != 0.0f) {
			//System.out.println("price: " +captPrice);
			return captPrice;
		} else {
			for (BomEntry j : bomList) {
				//System.out.println("j.getQuantity() == " + j.getQuantity()+ ". ");
				captPrice +=  (j.getQuantity() * costPart1(j.getPartNumber()));
				//System.out.println("price: " +captPrice);
			}
		}
		return captPrice;
	}

	@Override
	public Part retrievePart(String partNumber) {
		// Q: "How can I use the partNumber in order to access the associated part?"
		// iterate over parts array, compare each part's number to supplied partNumber
		//			returns matched part number.
		for (Part i : partsList) {
			if (i.getPartNumber().equals(partNumber))
				return i;
		}
		return null;
	}

	@Override
	public List<Part> getFinalAssemblies() {
		List<Part> assembledList = new ArrayList<>();
		for (Part i : partsList) {
			if (i.getPartType().equals("ASSEMBLY"))
				assembledList.add(i);
		}
		
		Collections.sort(assembledList, new Comparator<Part>() {

			@Override
			public int compare(Part o1, Part o2) {
				try {
					if (o1.getPartNumber().equals(null) || o2.getPartNumber().equals(null))
						throw new Exception();
					else {
						return o1.getPartNumber().compareTo(o2.getPartNumber());
					}
				} catch (Exception e) {
					System.out.println("null part numbers");
				}
				return -500;
			}
		});
		
		return assembledList;
	}

	@Override
	public List<Part> getPurchasePartsByPrice() {
		List <Part> purchasedList = new ArrayList<>();
		for (Part i : partsList) {
			if (i.getPartType().equals("PURCHASE"))
				purchasedList.add(i);
		}
		
		Collections.sort(purchasedList, new Comparator<Part>() {

			@Override
			public int compare(Part o1, Part o2) {
				try {
					if (o1.getPrice() == 0.0f || o2.getPrice() == 0.0f)
						throw new Exception();
					else {
						if ((o2.getPrice() - o1.getPrice()) > 0.0)
							return 1;
						else if ((o2.getPrice() - o1.getPrice()) == 0.0)
							return 0;
						else
							return -1;
					}
				} catch (Exception e) {
					System.out.println("null prices");
				}
				return -500;
			}
		});
		return purchasedList;
	}
	
	public Part[] getPartArray() {
		return partsArray;
	}
	
	public List <Part> getPartList(){
		return partsList;
	}

}
