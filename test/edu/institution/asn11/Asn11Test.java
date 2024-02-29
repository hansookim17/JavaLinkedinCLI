package edu.institution.asn11;

import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.Assert;

public class Asn11Test {
	
	@Test
	public void breadthFirstTest() {
		System.out.print("breadthFirstTest():	");
		
		BST<Integer> alexMason = new BST<Integer>();
		
		/*
		 * 					5
		 * 				/		\
		 * 			  3			  10
		 * 			/	\		 /	 \
		 * 		   2	 4		7	  11
		 *  
		 *  breadth first traversal should go:
		 *  		5 3 10 2 4 7 11
		 */
		
		alexMason.insert(5);
		//System.out.println(5);
		alexMason.insert(3);
		//System.out.println(4);
		alexMason.insert(10);
		//System.out.println(10);
		alexMason.insert(7);
		//System.out.println(7);
		alexMason.insert(2);
		//System.out.println(2);
		alexMason.insert(11);
		//System.out.println(11);
		alexMason.insert(4);
		//System.out.println(3);
		List<Integer> exportList = alexMason.breadthFirstTraversal();
		/*
		for (Integer i : exportList) {
			System.out.print(i+ " ");
		}*/
		
		//breadthFirstTraversal list manually inputted as a test for the method's efficacy.
		List<Integer> testList = new ArrayList<>();
		testList.add(5);
		testList.add(3);
		testList.add(10);
		testList.add(2);
		testList.add(4);
		testList.add(7);
		testList.add(11);
		alexMason.inorder();
		System.out.print("\n");
		
		Assert.assertEquals(testList, exportList);
	}
	
	@Test
	public void getHeightTest() {
		System.out.print("getHeightTest():	");
		
		BST<String> frankWoods = new BST<String>();
		/*
		 * 					  HanSoo
		 * 				/				\
		 * 			  Alice			  Odette
		 * 			/		\	 	/		 \
		 * 		  Alex		Gauss Horace 	Walter
		 * 							  \
		 * 							Jordan
		 * 
		 * height: 3
		 *  
		 */
		frankWoods.insert("HanSoo");
		frankWoods.insert("Alice");
		frankWoods.insert("Odette");
		frankWoods.insert("Alex");
		frankWoods.insert("Walter");
		frankWoods.insert("Horace");
		frankWoods.insert("Gauss");
		frankWoods.insert("Jordan");
		
		//System.out.print("Height: " +frankWoods.getHeight()+ "\n");
		frankWoods.inorder();
		System.out.print("\n");

		Assert.assertEquals(3, frankWoods.getHeight());
	}
	
	@Test
	public void nonRecursiveTest() {
		System.out.print("nonRecursiveTest():	");
		
		BST<Integer> jasonHudson = new BST<Integer>();
		//level 0
		jasonHudson.insert(5);
		//level 1
		jasonHudson.insert(3);	//3 is left of 5
		jasonHudson.insert(9);	//9 is right of 5
		//level 2
		jasonHudson.insert(1);	//1 is left of 3
		jasonHudson.insert(4);	//4 is right of 3
		jasonHudson.insert(7);	//7 is left of 9
		jasonHudson.insert(11);	//11 is right of 9
		//level 3
		jasonHudson.insert(2);	//2 is right of 1
		jasonHudson.insert(6);	//6 is left of 7
		jasonHudson.insert(8);	//8 is right of 7
		jasonHudson.insert(10);	//10 is left of 11
		
		
		List<Integer> victorReznov  = jasonHudson.nonRecursiveInOrder();
		
		for (Integer i : victorReznov) {
			System.out.print(i+ " ");
		}
		
		//answerKey is the List that victorReznov aims to mimic 
		//		not by taking answerKey's values,
		//		but by using the nonRecursiveInOrder() method
		List<Integer> answerKey = new ArrayList<>();
		for (Integer i = 1; i <= 11; i++) {
			answerKey.add(i);
		}
		System.out.print("\n");
		
		Assert.assertEquals(answerKey, victorReznov);
	}
}
