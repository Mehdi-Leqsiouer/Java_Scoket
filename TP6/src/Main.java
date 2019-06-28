import java.io.IOException;
import java.net.ServerSocket;

public class Main {

   public static void main(String[] args) {
    
      String host = "192.168.0.135";
      //int port[] = {700,701,702,703,704,705,706,707} ;
      int port = 700;
      
      int numWorkers = 1;
      int listPort[] = new int[numWorkers];
      
      for(int i = 0; i< numWorkers; i++){
    	  Thread ts = new Thread(new Serveur(host, port));    	  
    	  listPort[i] = port;
    	  port++;
    	  ts.start();
         // System.out.println("Serveur initialisé.");
    	 
      }
    	
      Thread client = new Thread(new Client(host,listPort,numWorkers));
	  client.start();
      


   }
	   /*for(int port = 1; port <= 65535; port++){
	         try {
	        	 ServerSocket sSocket = new ServerSocket(port);
	         } catch (IOException e) {
	            System.err.println("Le port " + port + " est déjà utilisé ! ");
	         }
	      }
		}*/
}