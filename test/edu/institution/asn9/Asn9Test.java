package edu.institution.asn9;

import org.junit.Test;

import java.util.List;

import org.junit.Assert;

public class Asn9Test {
	final String FILE_PATH = "src/edu/institution/asn9/asn9-numbers.txt";
	
	@Test
	public void testYourSorts() {
		SortAlgorithmMetrics philosophyTube = new SortAlgorithmMetrics();
		List<MetricData> hBomberGuy = philosophyTube.retrieveMetrics(FILE_PATH);
		
		//checks if all five sorting algorithms exist in the MetricData List (named hBomberGuy)
		Assert.assertEquals(5, hBomberGuy.size());
		
		//checks if all items in hBomberGuy is in ascending order.
		boolean ascendingOrder = true;
		for (int i = 0; i < hBomberGuy.size(); i++) {
			if (i > 0) {
				//In order for the list to fail being in ascending order, one item must be less than the item preceding it.
				//		If this for-loop detects that, it sets ascendingOrder to false and breaks the for-loop.
				if (hBomberGuy.get(i).getExecutionTime() < hBomberGuy.get(i - 1).getExecutionTime()) {
					ascendingOrder = false;
					break;
				}
			}
		}
		Assert.assertEquals(true, ascendingOrder);
		
		//checks if all sorting methods have correct worst-case Big-O Notations
		for (MetricData i : hBomberGuy) {
			if (i.getSortAlgorithm() == SortAlgorithm.BUBBLE_SORT)
				Assert.assertEquals(TimeComplexity.QUADRATIC, i.getTimeComplexity());
			else if (i.getSortAlgorithm() == SortAlgorithm.INSERTION_SORT)
				Assert.assertEquals(TimeComplexity.QUADRATIC, i.getTimeComplexity());
			else if (i.getSortAlgorithm() == SortAlgorithm.MERGE_SORT)
				Assert.assertEquals(TimeComplexity.LOGARITHMIC, i.getTimeComplexity());
			else if (i.getSortAlgorithm() == SortAlgorithm.QUICK_SORT)
				Assert.assertEquals(TimeComplexity.QUADRATIC, i.getTimeComplexity());
			else if (i.getSortAlgorithm() == SortAlgorithm.HEAP_SORT)
				Assert.assertEquals(TimeComplexity.LOGARITHMIC, i.getTimeComplexity());
		}
	}
}
