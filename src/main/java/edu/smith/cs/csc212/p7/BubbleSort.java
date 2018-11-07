package edu.smith.cs.csc212.p7;

import java.util.ArrayList;
import java.util.List;

/**
 * BubbleSort is a simple sort that is O(n^2).
 * @author jfoley
 *
 */
public class BubbleSort {

	/**
	 * Swap items in a list!
	 * @param items the list.
	 * @param i swap the item at this index with the one at j.
	 * @param j swap the item at this index with the one at i.
	 */
	public static void swap(List<Integer> items, int i, int j) {
		assert(i != j);
		int tmp = items.get(i);
		items.set(i,  items.get(j));
		items.set(j, tmp);
	}
	
	/**
	 * Bubble Sort is done! And it is easy to do in-place.
	 * @param input - the list to be sorted.
	 */
	public static void bubbleSort(List<Integer> input) {
		int N = input.size();
		
		while(true) {
			boolean sorted = true;
			for (int i=0; i<N-1; i++) {
				if (input.get(i) > input.get(i+1)) {
					swap(input, i, i+1);
					sorted = false;
				}
			}
			if (sorted) {
				break;
			}
			N = N - 1;
		}
	}
	
	/*
	 * Insertion Sort sorts the list by checking over the whole list
	 * and comparing each item to the current smallest item its found.
	 * if the item in the list is smaller than the current smallest, swap them
	 * 
	 * Wildly inspired by the video, also Drozdeck ch9 though I didn't directly implement any of that code
	 */
	public static void selectionSort(List<Integer> input) {
		int N = input.size();
		
		//set up a while loop so I can break later and make things easy
		while(true) {
			boolean sorted = true;
			for (int i=1; i<N; i++) {
				//check each item one at a time starting with the first item
				int smallest = input.get(0);
				
				//if you find a smaller item, put the previous smallest
				//in the first, move everything down one, and keep checking the list
				if (input.get(i) < smallest) {
					swap(input, i, i-1);
					sorted = false;
				} 
			}
			
			//break out of while loop when the list is done
			if(sorted) {
				break;
			}
			
			N = N-1;
			
		}
	}
	
	/*
	 * sort the list by comparing the focus data point to each value
	 * in the list before it
	 */
	public static void insertionSort(List<Integer> input) {
		
		int N = input.size();
		ArrayList<Integer> ordered = new ArrayList<Integer>(input.size());
		System.out.println(ordered);
		
		while(true) {
			boolean sorted = true;
			
			for (int i=0; i<N; i++) {
				//check each item one at a time starting with the first item
				int focus = input.get(i);
				System.out.println("focus = "+focus);
				
				if (i == 0) {
					ordered.add(focus);
					System.out.println(input+"\n"+ordered);
					sorted = false;
				}
				
				System.out.println("current: "+ordered.get(i));
				System.out.println("i: "+i);
				if (input.get(i) < ordered.get(i-1)) {
					ordered.add(focus);
					System.out.println(ordered);
					sorted = false;
					
				} else if (input.get(i) > ordered.get(i-1)) {
					ordered.add(focus);
					System.out.println(ordered);
					sorted = false;
							
				} else if (input.get(i) == ordered.get(i-1)) {
					System.out.println("Here");;
					
				} else {
					System.out.println("what the ever loving fuck");
				}
				
				
				/*
				//set up a loop for all the items before it
				for (int k = i+1; k >= 0; k--) {
					int current = input.get(k);
					System.out.println("current = "+current);
					
					//if the thing you're evaluating =/= the thing you're comparing to,
					//do something
					if (k == i) {
						k--;
					}
					
					if (current != focus && k != i) {
						if (current < focus) {
							if (k > i) {
								swap(input, k, i);
								System.out.println(input+"<- modified");
							}
						} else if (current > focus) {
							if (k < i) {
								swap(input, k, i);
							}
						}
					} else if (current == focus){
						System.out.print("Current "+current+" and Focus "+focus+" are equal \n");
					} else {
						System.out.println("Something is wrong! Current: "+current+" Focus: "+focus+" i: "+i+" k: "+k);
					}
				}*/
			}
			
			//break out of while loop when the list is done
			if(sorted) {
				System.out.println("done");
				break;
			}
			
			N = N-1;
			System.out.println("u done fucked up");
		}
	}
}
