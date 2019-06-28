import java.io.IOException;
import java.net.ServerSocket;

public class Main {

   public static void main(String[] args) {
    
      String host = "192.168.0.136";
      int port[] = {700,701,702,703,704,705} ;
      
      
      
      for(int i = 0; i< port.length; i++){
    	  Thread ts = new Thread(new Serveur(host, port[i]));
    	  ts.start();
          System.out.println("Serveur initialisé.");
    	 
      }
    	
      Thread client = new Thread(new Client(host,port));
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