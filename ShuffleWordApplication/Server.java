import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static BufferedReader in;
    public static PrintWriter out;
    
  public static void main(String[] args) throws Exception {

      ServerSocket ss = new ServerSocket(3377);      
      Socket s; 
      
      while (true)  {     
         s = ss.accept(); // Accept the incoming request 
         in = new BufferedReader(new InputStreamReader(s.getInputStream(),"utf-8"));
         out = new PrintWriter(new BufferedOutputStream(s.getOutputStream()));                     
        
          // Create a new handler object for handling this request. 
          Client mtch = new Client(s, in, out); 

          // Create a new Thread with this object. 
          Thread t = new Thread(mtch); 
                    
          // start the thread. 
          t.start();        
      }         
  }
}
