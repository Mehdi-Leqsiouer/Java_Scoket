import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

public class Client implements Runnable{

   private Socket[] connexion ;
   private PrintWriter writer = null;
   private BufferedInputStream reader = null;

   //Notre liste de commandes. Le serveur nous répondra différemment selon la commande utilisée.
   private String commands = "Calcul";
   private static int count = 0;
   private String name = "Client-";   
   ArrayList <Long> tableRes = new ArrayList<Long>();
   long total = 0;
   int totalCount = 16000000;
   int numWorkers = 5;
   
   public Client(String host, int port[]){
      name += count;
      connexion = new Socket[5];
      try {    	  
    	  for (int i = 0; i< port.length;i++){
    		  connexion[i] = new Socket(host, port[i]);
    	  }
    		  
      }
       catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   
   public void run(){

      //nous n'allons faire que 10 demandes par thread...

         try {
            Thread.currentThread().sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         try {

            for(int i = 0; i< connexion.length;i++ ){
            writer = new PrintWriter(connexion[i].getOutputStream(), true);
            reader = new BufferedInputStream(connexion[i].getInputStream());
            
            //On envoie la commande au serveur
            
            String commande = getCommand();
            writer.write(commande);
            //TOUJOURS UTILISER flush() POUR ENVOYER RÉELLEMENT DES INFOS AU SERVEUR
            writer.flush();  
            
           // System.out.println("Commande " + commande + " envoyée au serveur");
            
            //On attend la réponse
            String response = read();
            System.out.println("\t * " + name + " : Réponse reçue " + response);
            tableRes.add(Long.parseLong(response));
            
        
         
         try {
            Thread.currentThread().sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }

      
      writer.write("CLOSE");
      writer.flush();
      writer.close();
         
         }
         } catch (IOException e1) {
             e1.printStackTrace();
          }
         
      for (int i = 0; i < tableRes.size();i++){
   	 	  total += tableRes.get(i);
   	   }
      double pi = 4.0* total/ totalCount/5;
      System.out.println("Pi:" + pi);
   }

   
   
   
   //Méthode qui permet d'envoyer des commandeS de façon aléatoire
   private String getCommand(){
      Random rand = new Random();
      return commands;
   }
   
   //Méthode pour lire les réponses du serveur
   private String read() throws IOException{      
      String response = "";
      int stream;
      byte[] b = new byte[4096];
      stream = reader.read(b);
      response = new String(b, 0, stream);      
      return response;
   }   
}