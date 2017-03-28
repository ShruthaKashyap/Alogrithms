import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class countingsort {

	static BufferedWriter bw =null;
	static FileWriter csvForGraph;
	
	public static void countsort(int[] a, int k) {
		// COUNT SORT ALGORITHM IMPLEMENTATION
		//long start = System.nanoTime();
		int[] b = new int[a.length];
		int[] c = new int[k];
		for (int i = 0; i < k; i++) {
			c[i] = 0;
		}
		for (int j = 0; j < a.length; j++) {
			c[a[j]] = c[a[j]] + 1;
		}
		for (int i = 1; i < k; i++) {
			c[i] = c[i] + c[i - 1];
		}
		for (int j = a.length - 1; j >= 0; j--) {
			b[c[a[j]] - 1] = a[j];
			c[a[j]] = c[a[j]] - 1;
		}
		System.out.println("sorting done!");
		
		for(int m=0;m<b.length;m++)
		{
			a[m]=b[m];
		}
		
	}
	
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
	        int range;
	        String inputFileName;
	        System.out.println("Enter the input file name:\nEnter 'input_rand.txt' for Random order input\n'input_asc.txt' for Ascending order input\n'input_desc.txt' for Descending order input:");
	        Scanner scanIn = new Scanner(System.in);
	        inputFileName = scanIn.nextLine();
	        
	        for(N=10000;N<=1000000;N=N+10000)
	        {
	        	range=N/100; //setting range to 1% of input size
	        	randomNumGenerator r= new randomNumGenerator();
	        	r.RandNumGen(N,range);
	        	//int arr[] = {12, 11, 13, 5, 6, 7};
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
	        	    String toPrint="";
	        	    
	        	    
	        	    bw.write("Input size: "+N);
	        	    bw.newLine();
	        	    bw.newLine();

	        	    
	        	    System.out.println("done writing to file!");

	        	    
	        	    System.out.println("Given Array");
	                //printArray(arr);
	         
	                long startTime = System.currentTimeMillis();
	            
	                countsort(arr,range);
	                
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
