import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
public class randomNumGenerator {
	
	public void RandNumGen(int N)
	{
        int[] arr = new int[N];
        
        BufferedWriter bw1 = null;
        BufferedWriter bw2 = null;
        BufferedWriter bw3 = null;
        
        try {
                //Specify the file name and path here
       	 File file1 = new File("input_rand.txt");
       	 File file2 = new File("input_asc.txt");
       	 File file3 = new File("input_desc.txt");
       	 /* This logic will make sure that the file 
       	  * gets created if it is not present at the
       	  * specified location*/
       	  if (!file1.exists()) {
       	     file1.createNewFile();
       	  }
       	  
       	  if (!file2.exists()) {
        	     file2.createNewFile();
        	  }
       	  
       	  if (!file3.exists()) {
        	     file3.createNewFile();
        	  }

       	  FileWriter fw1 = new FileWriter(file1);
       	  FileWriter fw2 = new FileWriter(file2);
       	  FileWriter fw3 = new FileWriter(file3);
       	  
       	  bw1 = new BufferedWriter(fw1);
       	  bw2 = new BufferedWriter(fw2);
       	  bw3 = new BufferedWriter(fw3);
       	  
       	  
                 //System.out.println("File written Successfully");
       	  //my content here
          
          //random nos
       	  for(int i = 0; i <  arr.length; i++)
          {
              arr[i] = (int)(Math.random() * 1000000);
              bw1.write(arr[i]+"\n");
          }
       	  
       	  //ascending order
       	  Arrays.sort(arr);
       	for(int i = 0; i <  arr.length; i++)
        {
            //arr[i] = (int)(Math.random() * 100);
            bw2.write(arr[i]+"\n");
        }
       	
       	//Descending Order
       	for(int i = arr.length-1; i>=0; i--)
        {
            //arr[i] = (int)(Math.random() * 100);
            bw3.write(arr[i]+"\n");
        }

             } catch (IOException ioe) {
       	   ioe.printStackTrace();
       	}
       	finally
       	{ 
       	   try{
       	      if(bw1!=null)
       		 bw1.close();
       	      
       	   if(bw2!=null)
         		 bw2.close();
       	   
       	if(bw3!=null)
      		 bw3.close();     	           	      
       	      
       	   }catch(Exception ex){
       	       System.out.println("Error in closing the BufferedWriter"+ex);
       	    }
       	}
	}

}
