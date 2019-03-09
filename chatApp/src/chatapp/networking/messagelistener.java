
package chatapp.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class messagelistener extends Thread{
    ServerSocket server;
    int port=8877;
    //port current node 
    writegui gui;
    //for gui print
    public static ArrayList<String>mess=new ArrayList<>(); //array instid of queue
    
    public messagelistener (writegui gui,int port){
        this.port=port;
        this.gui=gui;
        try {
            server=new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(messagelistener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public messagelistener (){
        try {
            server=new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(messagelistener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @Override
    public void  run(){
        Socket clientSocket;
        try {
            while((clientSocket=server.accept())!=null){
                InputStream is=clientSocket.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String line=br.readLine();
                mess.add(line);
                //gui.write(line);
                if(line != null){
                    gui.write(line);
                   if(mess.size()>10 )
                   {
                       break;
                   }
                  //System.out.println("heeikekeke");
                  System.out.println("message queue........");
                for (int j = 0; j < mess.size(); j++) {
                    System.out.println(mess.get(j));
                }
                }
            }
             
        } catch (IOException ex) {
            Logger.getLogger(messagelistener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        public boolean capacity(){
    if(mess.size()>20)
    {
        return false;
    }
    else
        return true;
    }
}
