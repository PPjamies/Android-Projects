import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class Client implements Runnable {
	 final BufferedReader in; 
	 final PrintWriter out;
	 String question;
	 int answer;
	 int num1;
	 int num2;

	 Socket s; 
	    
	 // constructor 
	 public Client(Socket s, BufferedReader in, PrintWriter out) {  
	     this.in = in; 
	     this.out = out; 
	     this.s = s; 
	     question = "";
	     answer = 0;
	 }
	 
	 //randomly generates two numbers
	 //creates the question
	 public void genNumbers() {
		 Random rand = new Random();
		 num1 = rand.nextInt(101);
		 num2 = rand.nextInt(101);
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
         else if(received.equals("+")) 
         {
        	 genNumbers();
    		 question = num1 + " + " + num2 + " = ?";
    		 answer = num1 + num2;
	        sendMessageToMobile("?"+ question);
         }
         else if(received.equals("-"))
         {
        	 genNumbers();
    		 question = num1 + " - " + num2 + " = ?";
    		 answer = num1 - num2;
	        sendMessageToMobile("?"+ question);
         }
         else if(received.contentEquals("*"))
         {
        	 genNumbers();
    		 question = num1 + " * " + num2 + " = ?";
    		 answer = num1 * num2;
	        sendMessageToMobile("?"+ question);
         }
         else if(received.contentEquals("/"))
         {
        	 genNumbers();
    		 question = num1 + " / " + num2 + " = ?";
    		 answer = num1 / num2;
	        sendMessageToMobile("?"+ question);
         }
         else if(received.startsWith("="))
         {  
    		// you received user’s answer, eg: =3
	        String ans=received.substring(1);
	        int userAnswer= Integer.parseInt(ans);
	        
			if (answer == userAnswer)
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
