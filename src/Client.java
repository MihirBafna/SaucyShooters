import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class Client implements Runnable {
    //Client fields
    private String host; // ip address of the server
    private int port; // port on which the server is running on
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private boolean running = false;

    public Client(String host, int port){
        this.host = host;
        this.port = port;
    }

    //connect to server
    public void connect(){
        try{
            socket = new Socket(host,port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            new Thread(this).start();
        }catch(ConnectException e){
            System.out.println("Unable to connect to the server");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //close the connection
    public void close(){
        try{
            running = false;
            in.close();
            out.close();
            socket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }
    
}