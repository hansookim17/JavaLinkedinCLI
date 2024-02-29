package edu.institution.midterm;

import java.util.*;


public interface PartManager {
	// 1) imports the part store from an external file
	// 2) accepts the file path to the file which contains the part to import
	// 3) returns the number of parts imported
	int importPartStore(String filePath);
	
	// 1) calculates the cost to manufacture the part associated with the inputted partNumber
	// 2) accepts a part number which identifies the part to compute the cost for
	Part costPart(String partNumber);
	
	// 1) retrieves the part associate with the supplied part number from the part store
	// 2) accepts a part number which identifies the part to retrieve
	// 3) returns the part instance associated with the supplied part number or null if not found
	Part retrievePart(String partNumber);
	
	// 1) returns all final assembly parts sorted alphetically by their part number
	// 2) final assemblies have a part type of "ASSEMBLY"
	List<Part> getFinalAssemblies();
	
	// 1) returns all purchased parts sorted by their price, highest price to lowest
	// 2) purchase parts have a part type of "PURCHASE"
	List<Part> getPurchasePartsByPrice();
}
