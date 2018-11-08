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
				//in the first, automatically move everything down one, and keep checking the list
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
		int N = input.size();
		
		while(true) {
			boolean sorted = true;
			
			//check each item one at a time starting with the first item
			for (int i=1; i<N; i++) {
			
				int focus = input.get(i);
				int k = i-1;
				int current = input.get(k);
				
				
				/*
				 * set up a loop for all the items before it
				 * compare the focus to the thing behind it
				 * if the focus smaller, move it down one position
				 */
				if (k >= 0 && current > focus) {
					current = input.get(k);
					
					//if the thing behind focus is larger, pull it out
					//and move the focus up one position
					int temp = current;
					input.set(k, focus);
					input.set(i, temp);
					
					/*
					 * check to see if the focus is smaller
					 * than the new thing before it.
					 * if yes, move it down until it's behind a
					 * value smaller than focus, or it's at the
					 * front of the list. 
					 */
					
					while (k > 0 && input.get(k-1) > focus) {
						int temp2 = input.get(k-1);
						input.set(k-1, focus);
						input.set(k, temp2);
						k--;
					}
					k --;
					}
			
				//if current isn't bigger than focus, move to the next
				//list item
				focus = input.get(k+1);
				}
					
			//break out of while loop when the list is sorted
			if(sorted) {
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
	 * 
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

	/*
	 * Take a list passed in, split it in half, and call self until the passed list is 1 or 0 values long.
	 * Take the mini lists and mergeSort them repeatedly until you've built the list back up to
	 * its original length.
	 * 
	 * I got help from: Drozdeck, Geeks for Geeks, and Barb during lab.
	 * https://www.geeksforgeeks.org/merge-sort/
	 */
	public static void recursiveMergeSort(List<Integer> input) {		
		//base case: len(array) = 0 or 1
		//split input into two arrays
		//pass the arrays back to themselves so you can get it down to 0 or 1 in length
		//call MergeSort to sort them back out again
		//return sorted list
		
		//base Case:
		if (input.size() == 1 || input.size() == 0) {
			return;
		}
		
		//split the passed list in half
		ArrayList<Integer> One = new ArrayList<>(input.subList(0, input.size()/2));
		ArrayList<Integer> Two = new ArrayList<>(input.subList(input.size()/2, input.size()));

		//pass the two halves back to merge sort,
		//eventually getting them down to the base case
		recursiveMergeSort(One);
		recursiveMergeSort(Two);
		
		//merge sort the split lists back into a bigger, better, sorted list
		recursiveMergeCall2(One, Two);
	}

	/*
	 * A version of mergeSort that takes two lists
	 * so that I can use it in recursion
	 * 
	 * I mostly just copy-pasted my mergeSort but also got help from Barb in lab
	 * who suggested I just pass a version of mergeSort two lists
	 */
	public static void recursiveMergeCall2(List<Integer> input, List<Integer> input2) {
		System.out.println("called");
		System.out.println(input);
		System.out.println(input2);
		
		//find sizes of two subarrays to be merged
		int n1 = input.size();
		int n2 = input2.size();
		
		/*sort the sub arrays into new lists*/
		
		//1
		ArrayList<Integer> Ls = new ArrayList<>(n1);
		for (int l = 0; l <n1; l++) {
			Ls.add(0);
		}
		
		for (int i = 0; i < n1; i++) {
			if (input.get(i) < Ls.get(Ls.size()-i-1)) {
				Ls.set(Ls.size()-i-1, input.get(i));
			}
		}
		
		System.out.println("Ls = "+Ls);
		
		//2
		ArrayList<Integer> Rs = new ArrayList<>(input2.size());
		for (int l = 0; l < n2; l++) {
			Rs.add(0);
		}
		
		for (int i = 0; i < n2; i++) {
			if (input2.get(i) > Rs.get(Rs.size()-i-1)) {
				Rs.set(Rs.size()-i-1, input2.get(i));
			}
		}
		System.out.println("Rs = "+Rs);
		
		//merge the sorted arrays!!
		
		int i = 0;
		int j = 0;
		int length = Ls.size() + Rs.size();
		System.out.print("ordered size: "+length);
 		
		ArrayList<Integer> ordered = new ArrayList<>();
		for (int c = 0; c<length; c++) {
			ordered.add(0);
		}
		System.out.print("initial ordered: "+ordered);
		
		
		//Loop through both sorted arrays, 
		//adding their data to the new, bigger list
		int k = 0;
		System.out.print("k= "+k);
		while (i < n1 && j < n2) {
			
			System.out.print("Ls= "+Ls.get(i));
			System.out.print("Rs= "+Rs.get(j));
			
			//if the item in Ls is bigger, add that to the sorted list
			if(Ls.get(i) <= Rs.get(j)) {
				ordered.set(k, Ls.get(i));
				i++;
			} else if (Ls.get(i)>= Rs.get(j)) {
				ordered.set(k, Rs.get(j));
				j++;
			} 
			
			k++;
		}
		System.out.println("ordered1 = "+ordered);
		
		//sort in any values that have been missed
		//(for example, length differences)
		while (i < n1) {
			/*int focus = Ls.get(i);
			for (int c=0; c < ordered.size();) {
				if (Ls.get(c) < ordered.get(c)) {
					ordered.set(i-1, Ls.get(c));
				}
			}*/
			ordered.set(k, Ls.get(i));
			i++;
			k++;
		}
		
		while (j < n2) {
			ordered.set(k, Rs.get(j));
			j++;
			k++;
		}
		
		/*//overwright the original list with the new sorted merged list
		System.out.println("ordered = "+ordered);
		for (int f = 0; f < ordered.size(); f++) {
			input.set(f, ordered.get(f));
			System.out.println("input = "+input);
		}*/
		System.out.println("Final: "+ordered);

	}

	/*public static void recursiveMergeCall(List<Integer> input, int p, int q, int r) {
		System.out.println("called");
		//find sizes of two subarrays to be merged
		int n1 = q - p + 1;
		int n2 = r - q;
		
		//create temporary arrays to funnel the subs into
		int L[] = new int [n1];
		int R[] = new int [n2];
		
		//copy arrays into new ones
		for (int i=0; i<n1; i++) {
			L[i] = input.get(p+i);
		}
		
		for (int j=0; j<n2; j++) {
			R[j] = input.get(q + j + 1);
		}
		*/
		/*sort the sub arrays into new lists*/
		
	/*
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
		
		System.out.println("Ls = "+Ls);
		
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
		System.out.println("Rs = "+Rs);
		
		//merge the arrays!!
		
		int i = 0;
		int j = 0;
		int length = Ls.size() + Rs.size();
		System.out.print("ordered size: "+length);
 		
		ArrayList<Integer> ordered = new ArrayList<>();
		for (int c = 0; c<length; c++) {
			ordered.add(0);
		}
		System.out.print("initial ordered: "+ordered);
		
		
		//Loop through both sorted arrays, 
		//adding their data to the new list
		int k = p;
		System.out.print("k= "+k);
		while (i < n1 && j < n2) {
			
			System.out.print("Ls= "+Ls.get(i));
			System.out.print("Rs= "+Rs.get(j));
			//if the item in Ls is bigger, add that to the sorted list
			if(Ls.get(i) <= Rs.get(j)) {
				ordered.set(k, Ls.get(i));
				i++;
			} else {
				ordered.set(k, Rs.get(j));
				j++;
			}
			
			k++;
		}
		//System.out.println("ordered1 = "+ordered);
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
		System.out.println("ordered = "+ordered);
		for (int f = 0; f < ordered.size(); f++) {
			input.set(f, ordered.get(f));
			System.out.println("input = "+input);
		}

	}*/
}
