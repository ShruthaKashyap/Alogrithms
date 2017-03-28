import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Mergesort {
	
	// Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
	
	static BufferedWriter bw =null;
	static FileWriter csvForGraph;
	
    static void merge(int arr[], int l, int m, int r)
    {
        
    	System.out.println("Inside merge");
    	// Find sizes of two subarrays to be merged
        int a = m - l + 1;
        int b = r - m;
 
        // Create temp arrays
        int Left[] = new int [a];
        int Right[] = new int [b];
 
        //Copy data to temp arrays
        for (int i=0; i<a; ++i)
            Left[i] = arr[l + i];
        for (int j=0; j<b; ++j)
            Right[j] = arr[m + 1+ j];
 
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarry array
        int k = l;
        while (i < a && j < b)
        {
            if (Left[i] <= Right[j])
            {
                arr[k] = Left[i];
                i++;
            }
            else
            {
                arr[k] = Right[j];
                j++;
            }
            k++;
        }
 
        // Copy remaining elements of L[] if any
        while (i < a)
        {
            arr[k] = Left[i];
            i++;
            k++;
        }
 
        // Copy remaining elements of L[] if any
        while (j < b)
        {
            arr[k] = Right[j];
            j++;
            k++;
        }
        
        System.out.println("done with merge");
    }
 
    // Main function that sorts arr[l..r] using
    // merge()
  static void sort(int arr[], int l, int r)
    {
	  System.out.println("Inside sort");
	  if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;
 
            // Sort first and second halves
            sort(arr, l, m);
            sort(arr , m+1, r);
 
            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }
 
 
    /* A utility function to print array of size n */
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    
    /* A utility function to print array of size n */
    static void writeArray(int arr[])
    {
        int n = arr.length;
        
        for (int i=0; i<n; ++i){
            System.out.print(arr[i] + " ");
            try {
				bw.write(arr[i]+" ");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

    }
 
    // Driver method
    public static void main(String args[]) throws IOException
    {
        int N;//=100000;
        String inputFileName;
        System.out.println("Enter the input file name:\nEnter 'input_rand.txt' for Random order input\n'input_asc.txt' for Ascending order input\n'input_desc.txt' for Descending order input:");
        Scanner scanIn = new Scanner(System.in);
        inputFileName = scanIn.nextLine();
        
        for(N=10000;N<=1000000;N=N+10000)
        {
        	randomNumGenerator r= new randomNumGenerator();
        	r.RandNumGen(N);
        	int[] arr=new int[N];
        	String fileReader="";
        	BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        	//BufferedWriter bw =null;
        	
        	
        	try {
        	    String line;
        	    
        	    File file = new File("output.txt");
        	    
        	    
        	    csvForGraph = new FileWriter("csvForGraph.csv",true);
        	    
        	    if (!file.exists()) {
              	     file.createNewFile();
              	  }

        	    
        	    System.out.println("started from the top");
        	    
        	    FileWriter fw = new FileWriter(file,true);
        	    bw = new BufferedWriter(fw);
        	    int i=0;
        	    while ((line = br.readLine())!= null && i<N) {
        	     
        	    	arr[i]=Integer.parseInt(line.trim());
        	    	System.out.println(arr[i]);
        	    	i++;
        	    }
        	    
        	    System.out.println("here we are");
        	 
        	    
        	    bw.write("Input size: "+N);
        	    bw.newLine();
        	    bw.newLine();
        	    
        	    
        	    System.out.println("done writing to file!");
        	    
        	    
        	    System.out.println("Given Array");
               
         
                long startTime = System.currentTimeMillis();
            
                sort(arr,0,arr.length-1);
                
                long endTime = System.currentTimeMillis();
                
              
                System.out.println("started writing to file.... again!");
                bw.write("Sorted Array: ");
                bw.newLine();
                writeArray(arr);
                
                bw.newLine();
                bw.newLine();
                bw.write("Total Runtime is: "+(endTime-startTime)+" milliseconds");
                
                bw.newLine();
                bw.newLine();
                bw.newLine();
                System.out.println("done writing to file again!");
                
                System.out.println("\nSorted array");
                //printArray(arr);
        	    
                System.out.println("Total Runtime is: "+(endTime-startTime)+" milliseconds");
                System.out.println(N+","+(endTime-startTime)+"\n");
                csvForGraph.write(N+","+(endTime-startTime)+"\n");
                
        	} catch (IOException e) {
                e.printStackTrace();
            } 
        	finally {
        	    br.close();
        	    if(bw!=null)
              		 bw.close();
        	    	
        	    csvForGraph.close();
        	    
        	    
        	}
        }
 
 
    }
}

