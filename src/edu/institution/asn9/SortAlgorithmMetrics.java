package edu.institution.asn9;

import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.institution.asn2.LinkedInUser;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.time.Duration;
import java.time.LocalTime;

public class SortAlgorithmMetrics {
	public List<MetricData> retrieveMetrics(String filePath) {
		List<MetricData> metricRoster = new ArrayList<>();
		
		LocalTime start, end;
		long elapsedMilliseconds;
		
		//make a list of numbers
		List<Integer> numberList = new ArrayList<>();
		Integer[] numberArray;
		
		File eigenFile;
		
		try {
			eigenFile = new File(filePath);
			//reading a file
			Scanner scan = new Scanner(eigenFile);
			//System.out.println("File read successfully.");
			
			//copying values from the file
			while (scan.hasNextLine()) {
				int sub = scan.nextInt();
				//System.out.println("sub = " +sub);
				numberList.add(sub);
			}
			//System.out.println("Values read successfully.");
			
			//shuffling numberArray0
			Collections.shuffle(numberList);
			//System.out.println("numberList[0,1,2]: " +numberList.get(0)+ ", " +numberList.get(1)+ ", " +numberList.get(2)+ ". \n" + "numberList shuffled.");
			
			//numberArray copying numberList
			numberArray = new Integer[numberList.size()];
			for (int i = 0; i < numberList.size(); i++)
				numberArray[i] = numberList.get(i);
			//System.out.println("numberList copied to numberArray.");
			
			//making instances and setting their time complexities
			MetricData sort0 = new MetricData(SortAlgorithm.BUBBLE_SORT);
			sort0.setTimeComplexity(TimeComplexity.QUADRATIC);
			start = LocalTime.now();
			BubbleSort.bubbleSort(numberArray);
			end = LocalTime.now();
			elapsedMilliseconds = Duration.between(start, end).toMillis();
			sort0.setExecutionTime(elapsedMilliseconds);
			metricRoster.add(sort0);
			//System.out.println("numberArray0[0,1,2]: " +numberArray[0]+ ", " +numberArray[1]+ ", " +numberArray[2]+ ".");
			
			//numberArray recycling between sorts
			numberArray = new Integer[numberList.size()];
			for (int i = 0; i < numberList.size(); i++)
				numberArray[i] = numberList.get(i);
			
			MetricData sort1 = new MetricData(SortAlgorithm.INSERTION_SORT);
			sort1.setTimeComplexity(TimeComplexity.QUADRATIC);
			start = LocalTime.now();
			InsertionSort.insertionSort(numberArray);
			end = LocalTime.now();
			elapsedMilliseconds = Duration.between(start, end).toMillis();
			sort1.setExecutionTime(elapsedMilliseconds);
			metricRoster.add(sort1);
			//System.out.println("numberArray1[0,1,2]: " +numberArray[0]+ ", " +numberArray[1]+ ", " +numberArray[2]+ ".");
			
			numberArray = new Integer[numberList.size()];
			for (int i = 0; i < numberList.size(); i++)
				numberArray[i] = numberList.get(i);
			
			MetricData sort2 = new MetricData(SortAlgorithm.MERGE_SORT);
			sort2.setTimeComplexity(TimeComplexity.LOGARITHMIC);
			start = LocalTime.now();
			MergeSort.mergeSort(numberArray);
			end = LocalTime.now();
			elapsedMilliseconds = Duration.between(start, end).toMillis();
			sort2.setExecutionTime(elapsedMilliseconds);
			metricRoster.add(sort2);
			//System.out.println("numberArray2[0,1,2]: " +numberArray[0]+ ", " +numberArray[1]+ ", " +numberArray[2]+ ".");
			
			numberArray = new Integer[numberList.size()];
			for (int i = 0; i < numberList.size(); i++)
				numberArray[i] = numberList.get(i);
			
			MetricData sort3 = new MetricData(SortAlgorithm.QUICK_SORT);
			sort3.setTimeComplexity(TimeComplexity.QUADRATIC);
			start = LocalTime.now();
			QuickSort.quickSort(numberArray);
			end = LocalTime.now();
			elapsedMilliseconds = Duration.between(start, end).toMillis();
			sort3.setExecutionTime(elapsedMilliseconds);
			metricRoster.add(sort3);
			//System.out.println("numberArray3[0,1,2]: " +numberArray[0]+ ", " +numberArray[1]+ ", " +numberArray[2]+ ".");
			
			numberArray = new Integer[numberList.size()];
			for (int i = 0; i < numberList.size(); i++)
				numberArray[i] = numberList.get(i);
			
			MetricData sort4 = new MetricData(SortAlgorithm.HEAP_SORT);
			sort4.setTimeComplexity(TimeComplexity.LOGARITHMIC);
			start = LocalTime.now();
			HeapSort.heapSort(numberArray);
			end = LocalTime.now();
			elapsedMilliseconds = Duration.between(start, end).toMillis();
			sort4.setExecutionTime(elapsedMilliseconds);
			metricRoster.add(sort4);
			//System.out.println("numberArray4[0,1,2]: " +numberArray[0]+ ", " +numberArray[1]+ ", " +numberArray[2]+ ".");
			
			//sorting the roster in respect of elapsed time (shortest to longest)
			Collections.sort(metricRoster, new Comparator<MetricData>() {
				public int compare(MetricData M1, MetricData M2){
					return (int) M1.getExecutionTime() - (int) M2.getExecutionTime();
				}
			});
			
			for (MetricData i : metricRoster) {
				System.out.println("Sorting Algorithm: "+i.getSortAlgorithm().display()+ ". Time elapsed: " +i.getExecutionTime()+ " milliseconds.");
			}
			
		} catch (Exception e){
			System.out.println("Something definitely went wrong.");
		}
		return metricRoster;
	}
}
