import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Client implements Runnable {
	 final BufferedReader in; 
	 final PrintWriter out;
	 int intType;
	 String question;
	 String answer;

	 Socket s; 
	    
	 // constructor 
	 public Client(Socket s, BufferedReader in, PrintWriter out) {  
	     this.in = in; 
	     this.out = out; 
	     this.s = s; 
	     question = "";
	     answer = "";
	     intType=0;
	 }
	 
	 //TODO: grab from database
	 //FORNOW: hardcode
	 public String getWord(int type) {
		  String word = "";
		   
		  switch (type)
		  {
		    case 1:  // fruit
		    	word = "Apple";
		    	return word;
		    case 2:  // flower
		    	word =  "Sunflower";
		    	return word;
		    case 3:  // animal
		    	word = "Dog";
		    	return word;
		  }
		  
		return word;
	}
	 
	 public String shuffleWord(String word) {
		 String sWord = "";
		 
		 //split word into a list of letters
		 List<String> shuffleTemp = Arrays.asList(word.split(""));
		 //shuffle the list of letters
		 Collections.shuffle(shuffleTemp);
		 
		 //concatenate the shuffled letters into a shuffled word
		 for(int i=0; i<shuffleTemp.size(); i++) {
			 sWord+=shuffleTemp.get(i);
		 }
		 
		 return sWord;
	}
	

	@Override
	public void run() {
		String received=""; 
	    while (true)
	    { 
	    
    	 String msg = null;
    	 try 
    	 {   
    		 received = this.in.readLine();
    		 System.out.println("received : "+received);    // see log file             
    	 }
    	 catch (IOException e)
    	 {
    		 e.printStackTrace();
    	 }
    	 
         if(received==null)
         {
        	 break;
         }
         //grab intType from mobile
         //getWord and set it to answer
         //shuffleWord and send it as question back to mobile
         else if(received.equals("1")) 
         {
        	intType = Integer.parseInt("1");
        	answer = getWord(intType);
        	question = shuffleWord(answer);
	        sendMessageToMobile("?"+ question);
         }
         else if(received.equals("2"))
         {
        	intType = Integer.parseInt("2");
        	answer = getWord(intType);
        	question = shuffleWord(answer);
	        sendMessageToMobile("?"+ question);
         }
         else if(received.contentEquals("3"))
         {
        	intType = Integer.parseInt("3");
        	answer = getWord(intType);
        	question = shuffleWord(answer);
	        sendMessageToMobile("?"+ question);
         }
         else if(received.startsWith("="))
         {  
    		// you received user’s answer, eg: =Flower
	        String userAnswer = received.substring(1);	        
			if (answer.equals(userAnswer))
			 {
				 sendMessageToMobile("#0"); // correct
			 }
			else {
				sendMessageToMobile("#1");  // wrong
			}
     	 }
         else
         {                          	             
	         try
	         { 
		         // closing resources 
		         this.in.close(); 
		         this.out.close(); 
		         this.s.close(); 
	         }
	         catch(IOException e)
	         { 
	        	 e.printStackTrace(); 
	         }
     	 }         
	    }
	}
	
	public void sendMessageToMobile(final String str) {

		new Thread(new Runnable() {

		         @Override
		         public void run() {
		           
		         try {       
		         PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), "utf-8"), true);
		                
		           if (!str.isEmpty()){
		              out.println(str);
		              out.flush();
		              System.out.println("sent to mobile: "+ str);  // see log file
		           }                          
		          }
		          catch (IOException e) {   
		         }
		 
		        }
		     }).start();
		 }
		 
}
