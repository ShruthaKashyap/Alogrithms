package algo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class LCS2 {
	static ArrayList<seq> coords = new ArrayList<seq>();
	static int[] s1;
	static int[] s2;
	static int[] s3;
	static ArrayList<Integer> as1 = new ArrayList<Integer>();
	static ArrayList<Integer> as2 = new ArrayList<Integer>();
	static ArrayList<Integer> as3 = new ArrayList<Integer>();

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		// READING FILES
		// READING FILE1
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter 1st file ");
		String f1 = scan.nextLine();
		File file1 = new File(f1);
		Scanner sc1 = new Scanner(file1);
		sc1.useDelimiter(" ");
		while (sc1.hasNextInt()) {
			as1.add(sc1.nextInt());
		}
		sc1.close();
		//System.out.println("as1 size"+as1.size());
		// READING FILE2
		System.out.println("Enter 2nd file ");
		String f2 = scan.nextLine();
		File file2 = new File(f2);
		Scanner sc2 = new Scanner(file2);
		sc2.useDelimiter(" ");
		while (sc2.hasNextInt()) {
			as2.add(sc2.nextInt());
		}
		//System.out.println("as2 size"+as2.size());

		sc2.close();
		// READING FILE3
		System.out.println("Enter 3rd file ");
		String f3 = scan.nextLine();
		File file3 = new File(f3);
		Scanner sc3 = new Scanner(file3);
		sc3.useDelimiter(" ");
		while (sc3.hasNextInt()) {
			as3.add(sc3.nextInt());
		}
		sc3.close();
		//System.out.println("as3 size"+as3.size());

		scan.close();
		int max3=Collections.max(as3);
		int l1 = as1.size();
		int l2 = as2.size();
		int l3 = as3.size();
		int max = Math.max(Math.max(l1, l2), l3);
		//System.out.println(max);
		// INITIALIZING THE ARRAYS
		s1 = new int[max];
		s2 = new int[max];
		s3 = new int[max];
		int[] stemp=new int[as3.size()];
		//STORING IN TEMPORARY ARRAYS
        for(int i=0;i<as3.size();i++){
        	stemp[i]=as3.get(i);
        }
        int[] stemp1=new int[as1.size()];
        for(int i=0;i<as1.size();i++){
        	stemp1[i]=as1.get(i);
        }
        int[] stemp2=new int[as2.size()];
        for(int i=0;i<as2.size();i++){
        	stemp2[i]=as2.get(i);
        	
        }
		// 1st ARRAY
		for (int i = 0; i < max; i++) {
			if (i <= l1 - 1) {
				s1[i] = as1.get(i);
			} else {
				s1[i] = -999;
			}
		}
		// 2ND ARRAY
		for (int i = 0; i < max; i++) {
			if (i <= l2 - 1) {
				s2[i] = as2.get(i);
			} else {
				s2[i] = -999;
			}
		}
		// 3RD ARRAY
		for (int i = 0; i < max; i++) {
			if (i <= l3 - 1) {
				s3[i] = as3.get(i);
			} else {
				s3[i] = -999;
			}
		}
		//PRINTING ARRAYS
		/*
		for (int i = 0; i < max; i++) {
			System.out.print(s1[i] + " ");
		}
		System.out.println("\n");
		for (int i = 0; i < max; i++) {
			System.out.print(s2[i] + " ");
		}
		System.out.println("\n");
		for (int i = 0; i < max; i++) {
		System.out.print(s3[i] + " ");
		}
		*/
		System.out.println("\n");
		int[] outlcs=new int[max];
		int[] sortlcs=new int[max];
        //BEFORE SORTING
		System.out.println("Before sorting inputs");
		outlcs=lcs(s1, s2, s3, max);
		for(int i=outlcs.length-1;i>=0;i--){
			System.out.print(outlcs[i]+ " ");
		}
		//WRITING TO FILE
		PrintWriter output = new PrintWriter("LCS_in.txt", "UTF-8");
		output.println("Longest common subsequence of unsorted inputs is");
		for (int i = outlcs.length-1; i >= 0; i--) {
			output.print(outlcs[i] + " ");
		}
		output.close();
		System.out.println("\n");
		int[] s33=new int[stemp.length];
		int[] s11=new int[stemp1.length];
		int[] s22=new int[stemp2.length];
		//CALLING SORT FUNCTIONS ON ARRAYS
		isort sort1=new isort();
		s11=sort1.insertionsort(stemp1);
		PrintWriter insort = new PrintWriter("n2SORT.txt", "UTF-8");
		insort.println("Input sorted using insertion sort");
		for (int i =0;i< s11.length; i++) {
			insort.print(s11[i] + " ");
		}
		insort.close();
		msort sort2=new msort();
		s22=sort2.sort(stemp2, 0, stemp2.length-1);
		PrintWriter mersort = new PrintWriter("nlognSORT.txt", "UTF-8");
		mersort.println("Input sorted using merge sort");
		for (int i =0;i< s22.length; i++) {
			mersort.print(s22[i] + " ");
		}
		mersort.close();
		for(int i=0;i<s22.length;i++){
//			System.out.print(s22[i]+ " ");
		}
		csort sort=new csort();
        s33=sort.countsort(stemp, max3+1);
        PrintWriter cosort = new PrintWriter("nSORT.txt", "UTF-8");
		cosort.println("Input sorted using merge sort");
		for (int i =0;i< s33.length; i++) {
			cosort.print(s33[i] + " ");
		}
		cosort.close();
      //  System.out.println(s33.length);
        Arrays.fill(s1,0);
        Arrays.fill(s2,0);
        Arrays.fill(s3,0);
       //REFILLING THE ARRAYS WITH NEW VALUES
	   int[] s34=new int[max];
       int[] s14=new int[max];
       int[] s24=new int[max];
    	   for (int i = 0; i < max; i++) {
   			if (i < s33.length) {
   				s34[i] = s33[i];
   			} else {
   				s34[i] = -999;
   			}
    	   }
   			
   			for (int i = 0; i < max; i++) {
   	   			if (i < s11.length) {
   	   				s14[i] = s11[i];
   	   			} else {
   	   				s14[i] = -999;
   	   			}
   	    	   }
   	   			
   	   		for (int i = 0; i < max; i++) {
   	   			if (i < s22.length) {
   	   				s24[i] = s22[i];
   	   			} else {
   	   				s24[i] = -999;
   	   			}
   	    	   }
   	   		for(int i=0;i<max;i++){
   	   			s1[i]=s14[i];
   	   			s2[i]=s24[i];
   	   			s3[i]=s34[i];
   	   		}
   	   		//PRINTING  ARRAYS 
   	   		/*
   	   	for(int i=0;i<s14.length;i++){
		System.out.print(s14[i]+ " ");
		}
   	   	System.out.println();
   	 for(int i=0;i<s24.length;i++){
		System.out.print(s24[i]+ " ");
		}
   	 System.out.println();
   	for(int i=0;i<s34.length;i++){
		System.out.print(s34[i]+ " ");
	}
	*/
   	System.out.println();
   	    coords.clear();
   	    //WRITING TO FILE 
    	//System.out.println("sorted");
		System.out.println("After sorting inputs");

		sortlcs=lcs(s14, s24, s34, max);
		for(int i=sortlcs.length-1;i>=0;i--){
			System.out.print(sortlcs[i]+ " ");
		}
		PrintWriter output1 = new PrintWriter("LCS_out.txt", "UTF-8");
		output1.println("Longest common subsequence of sorted inputs is");
		for (int i = sortlcs.length-1; i >= 0; i--) {
			output1.print(sortlcs[i] + " ");
		}
		
		output1.close();
	}

	public static int[] lcs(int[] s1, int[] s2, int s3[], int max) {
		int[] inlcs=new int[max];
		int al = max + 1;
		int[][][] lcs = new int[al][al][al];
		int[][][] temp = new int[al][al][al];

		// INITIALIZING LCS MATRIX
		for (int i = 0; i < al; i++) {
			for (int j = 0; j < al; j++) {
				for (int k = 0; k < al; k++) {
					lcs[i][j][k] = 0;
					temp[i][j][k] = 0;

				}
			}
		}
		// FILLING UP THE MATRIX
		for (int i = 1; i < al; i++) {
			for (int j = 1; j < al; j++) {
				for (int k = 1; k < al; k++) {
					if (s1[i - 1] == s2[j - 1] && s1[i - 1] == s3[k - 1]) {
						lcs[i][j][k] = lcs[i - 1][j - 1][k - 1] + 1;
						temp[i][j][k] = lcs[i][j][k];
						//System.out.print(temp[i][j][k]+" ");

					} else {
						lcs[i][j][k] = Math.max(Math.max(lcs[i - 1][j][k], lcs[i][j - 1][k]), lcs[i][j][k - 1]);
					}
				}
			}
		}
		int length = lcs[al - 1][al - 1][al - 1];
		// System.out.println(length);
		// System.out.println(temp.length);
		//PRINTING LCS AND TEMP ARRAYS
		/*
		for (int i = 1; i < al; i++) {
			for (int j = 1; j < al; j++) {
				for (int k = 1; k < al; k++) {
					if (temp[i][j][k] != 0) {
					//	System.out.println(i+" "+ j+ " "+k);
					//	System.out.println(temp[i][j][k]);
					}
				}

			}
			
		}
		*/
		inlcs=track(temp, length, al);
        return inlcs;
	}

	public static int[] track(int[][][] temp, int length, int max) {

		int[] lcseq = new int[length];
		int[] xcords = new int[max];
		int p = 0;
		// System.out.println(max);
		for (int i = max - 1; i >= 0; i--) {
			for (int j = max - 1; j >= 0; j--) {
				for (int k = max - 1; k >= 0; k--) {
					// System.out.println(temp[i][j][k]);

					if (temp[i][j][k] <= length && temp[i][j][k] > 0) {
						// System.out.println(i + " " + j + " " + k);
						coords.add(new seq(i, j, k, temp[i][j][k]));
						//System.out.println(coords.size());
						//System.out.println(i+" "+ j+" "+ k);
						//System.out.println(temp[i][j][k]);

					}
				}
			}
		}
		// System.out.println("size of arraylist ");
		// System.out.println(coords.size());
		System.out.println("\n");
		/*
		System.out.println("loop");
		for (int l = coords.size() - 1; l >= 0; l--) {
			System.out.println(coords.get(l).x + " "+ coords.get(l).y+" "+coords.get(l).z+" "+ coords.get(l).slen);
		}
		*/
		int xl = as1.size();
		int yl = as2.size();
		int zl = as3.size();
		//System.out.println("input sizes "+xl+" "+yl+" "+zl);
		int flag = 0;
		for(int i=0;i<xcords.length;i++){
			xcords[i]=-999;
		//System.out.println(xcords[i]);
		}
		for (int l = 0; l < coords.size(); l++) {
			flag=0;
			if (coords.get(l).slen == length) {
				if (coords.get(l).x == xl || coords.get(l).y == yl || coords.get(l).z == zl) {
					for (int i = 0; i < xcords.length; i++) {
						if (coords.get(l).x == xcords[i]) {
						//	System.out.println(xcords[i]);
							flag = 1;
		                   // System.out.println("Already visited");

						}
					}
					if (flag == 1) {
						continue;
					} else {
						xcords[p] = coords.get(l).x;
						lcseq[p] = s1[coords.get(l).x -1];
	                    //System.out.println("equal but not visited");
                        
						// System.out.println(coords.get(l).x);
						// System.out.println(xl+" "+yl +" "+zl+" "+length);
						xl=coords.get(l).x-1;
						yl=coords.get(l).y-1;
						zl=coords.get(l).z-1;

					
						p++;
						length--;

					}

				} else if (coords.get(l).x < xl && coords.get(l).y < yl && coords.get(l).z < zl) {
					xcords[p] = coords.get(l).x;
					lcseq[p] = s1[coords.get(l).x -1];
					//System.out.println(coords.get(l).x);
					//System.out.println(lcseq[p]);
                    //System.out.println("all less");
					//System.out.println(xl+" "+yl +" "+zl+" "+length);
					xl=coords.get(l).x-1;
					yl=coords.get(l).y-1;
					zl=coords.get(l).z-1;
					
					p++;
					length--;
				}
			}
		}
		System.out.println("Longest Common Subsequence is");
/*
		for (int q = lcseq.length - 1; q >= 0; q--) {
			System.out.print(lcseq[q] + " ");
		}
		*/
      return lcseq;
	}
}