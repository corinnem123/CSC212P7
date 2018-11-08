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
	 * I got help from Drozdek ch9 and Geeksforgeeks
	 * https://www.geeksforgeeks.org/insertion-sort/
	 */
	public static void insertionSort(List<Integer> input) {
		/*
		 * Why does it replace everything with 88???
		 */
		
		int N = input.size();
		ArrayList<Integer> ordered = new ArrayList<Integer>(input.size());
		//System.out.println(ordered);
		
		while(true) {
			boolean sorted = true;
			
			for (int i=1; i<N; i++) {
				/*int j = i;
				
				Comparable<Integer> tmp = (Comparable)input.get(i);
				for (j = i; j > 0 && tmp.compareTo(input.get(j-1))<0; j--)
						input.set(j, input.get(j-1));
				tmp = input.get(j);
				System.out.println(input+"<- modified");
			}*/
			
			//check each item one at a time starting with the first item
			int focus = input.get(i);
			int k = i-1;
			int current = input.get(k);
			
			//System.out.println("focus = "+focus);
			
			//set up a loop for all the items before it
			while (k >= 0 && current > focus) {
				//System.out.println("current = "+current);
				
				//if the thing you're evaluating =/= the thing you're comparing to,
				//do something
				input.set(k+1, current);
				k --;
				//System.out.println(input+"<-moved down");
				}
			
			//if current isn't bigger than focus, move to the next
			//list item
			focus = input.get(k+1);
			//System.out.println("focus2 = "+focus);
			}
					
			//break out of while loop when the list is done
			if(sorted) {
				//System.out.println("done");
				break;
			}
			
			N = N-1;
		}
	}
	/*
	 * Cut the input list in half, sort the two halves seperately,
	 * then merge them together into one big sorted list.
	 * 
	 * Had help from: drozdek, and geeksforgeeks.com
	 * https://www.geeksforgeeks.org/merge-sort/
	 */
	public static void mergeSort(List<Integer> input) {
		
		//split the input list into two sub-arrays
		ArrayList<Integer> L1 = new ArrayList<>();
		ArrayList<Integer> L2 = new ArrayList<>();
		
		int n = input.size();
		int o = n/2;

		for (int i = 0; i<n; i++) {
			if (i<= o) {
				L1.add(input.get(i));
			} else {
				L2.add(input.get(i));	
			}
	
		}
		
		//find sizes of two subarrays to be merged
		int n1 = L1.size();
		int n2 = L2.size();
		
		//create temporary arrays to funnel the subs into
		int L[] = new int [n1];
		int R[] = new int [n2];
		
		//copy arrays into new ones
		for (int i=0; i<n1; i++) {
			L[i] = input.get(0+i);
		}
		
		for (int j=0; j<n2; j++) {
			R[j] = input.get(n1 + j);
		}
		
		/*sort the sub arrays into new lists*/
		
		//L
		ArrayList<Integer> Ls = new ArrayList<>(L.length);
		for (int l = 0; l < L.length; l++) {
			Ls.add(0);
		}
		
		for (int i = 0; i < L.length; i++) {
			if (L[i] > Ls.get(Ls.size()-i-1)) {
				Ls.set(Ls.size()-i-1, L[i]);
			}
		}
		
		//R
		ArrayList<Integer> Rs = new ArrayList<>(R.length);
		for (int l = 0; l < R.length; l++) {
			Rs.add(0);
		}
		
		for (int i = 0; i < R.length; i++) {
			if (R[i] > Rs.get(Rs.size()-i-1)) {
				Rs.set(R.length-i-1, R[i]);
			}
		}
		
		//merge the arrays!!
		
		int i = 0;
		int j = 0;
		int length = input.size();
		
		ArrayList<Integer> ordered = new ArrayList<>();
		for (int c = 0; c<length; c++) {
			ordered.add(0);
		}
		
		//Loop through both sorted arrays, 
		//adding their data to the new list
		int k = 0;
		while (i < n1 && j < n2) {
			if(Ls.get(i) <= Rs.get(j)) {
				ordered.set(k, Ls.get(i));
				i++;
			} else {
				ordered.set(k, Rs.get(j));
				j++;
			}
			
			k++;
		}
		
		while (i < n1) {
			ordered.set(k, Ls.get(i));
			i++;
			k++;
		}
		
		while (j < n2) {
			ordered.set(k, Rs.get(j));
			j++;
			k++;
		}
		
		//overwright the original list with the new sorted merged list
		for (int f = 0; f < ordered.size(); f++) {
			input.set(f, ordered.get(f));
		}

	}
}
