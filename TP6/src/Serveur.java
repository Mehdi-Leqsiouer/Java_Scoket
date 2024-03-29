import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Serveur implements Runnable{

	//On initialise des valeurs par d�faut
	   private int port = 2345;
	   private String host = "127.0.0.1";
	   private ServerSocket server = null;
	   private boolean isRunning = true;
	   
	   public Serveur(){
		      try {
		         server = new ServerSocket(port, 100, InetAddress.getByName(host));
		      } catch (UnknownHostException e) {
		         e.printStackTrace();
		      } catch (IOException e) {
		         e.printStackTrace();
		      }
		   }
		   
		   public Serveur(String pHost, int pPort){
		      host = pHost;
		      port = pPort;
		      try {
		         server = new ServerSocket(port, 100, InetAddress.getByName(host));
		      } catch (UnknownHostException e) {
		         e.printStackTrace();
		      } catch (IOException e) {
		         e.printStackTrace();
		      }
		   }
		   
		   public void open(){
			     
			      //Toujours dans un thread � part vu qu'il est dans une boucle infinie
			      Thread t = new Thread(new Runnable(){
			         public void run(){
			            while(isRunning == true){
			               
			               try {
			                  //On attend une connexion d'un client
			                  Socket client = server.accept();
			                  
			                  //Une fois re�ue, on la traite dans un thread s�par�
			                  //System.out.println("Connexion cliente re�ue.");                  
			                  Thread t = new Thread(new ClientProcessor(client));
			                  t.start();
			                  
			               } catch (IOException e) {
			                  e.printStackTrace();
			               }
			            }
			            
			            try {
			               server.close();
			            } catch (IOException e) {
			               e.printStackTrace();
			               server = null;
			            }
			         }
			      });
			      
			      t.start();
			   }
			   
			   public void close(){
			      isRunning = false;
			   }

			@Override
			public void run() {
				Thread t = new Thread(new Runnable(){
			         public void run(){
			            while(isRunning == true){
			               
			               try {
			                  //On attend une connexion d'un client
			                  Socket client = server.accept();
			                  
			                  //Une fois re�ue, on la traite dans un thread s�par�
			                  //System.out.println("Connexion cliente re�ue.");                  
			                  Thread t = new Thread(new ClientProcessor(client));
			                  t.start();
			                  
			               } catch (IOException e) {
			                  e.printStackTrace();
			               }
			            }
			            
			            try {
			               server.close();
			            } catch (IOException e) {
			               e.printStackTrace();
			               server = null;
			            }
			         }
			      });
			      
			      t.start();
				
			}   
}
