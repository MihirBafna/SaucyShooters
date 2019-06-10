import java.awt.Point;
import java.io.*;
import java.util.*;
import java.net.*;

/**
 * Server.java contains a Server class and a ClientHandler class
 * Server opens up the socket for communication between clients (also on a separate thread)
 */
public class Server
{
	static ArrayList<Player> players = new ArrayList<Player>();
	// Vector to store active clients
	static Vector<ClientHandler> ar = new Vector<>();
	private static final Random random = new Random();

	// counter for clients
	static int i = 0;

	/**
	 * main method plays game music, starts the server socket, and waits for client connection to establish the input/outputstreams
	 */

	public static void main(String[] args) throws IOException{
		if (random.nextBoolean()) {
			SoundEffect.cooldrums.play();
		} else {
			SoundEffect.vibeybeat.play();
		}
		ServerSocket ss = new ServerSocket(7777);

		Socket s;

		// running infinite loop for getting
		// client request
		while (true)
		{
			// Accept the incoming request
			s = ss.accept();

			System.out.println("New client request received : " + s);

			// obtain input and output streams
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

			System.out.println("Creating a new handler for this client...");

			// Create a new handler object for handling this request.
			players.add(new Player()); 
			ClientHandler mtch = new ClientHandler(s,i, ois, oos);
			// Create a new Thread with this object.
			Thread t = new Thread(mtch);

			System.out.println("Adding this client to active client list");

			// add this client to active clients list
			ar.add(mtch);
			// start the thread.
			t.start();

			// increment i for new client.
			// i is used for naming only, and can be replaced
			// by any naming scheme
			i++;

		}
	}
}

/**
 * ClientHandler is the class responsible for handling the data transfer of any number of clients on multiple threads
 */
class ClientHandler implements Runnable
{
	Scanner scn = new Scanner(System.in);
	int i;
	final ObjectInputStream ois;
	final ObjectOutputStream oos;
	Socket s;
	boolean isloggedin;


	/**
	 * Constructor
	 * @param Socket
	 * @param int
	 * @param ObjectInputStream
	 * @param ObjectOutputStream
	 */
	public ClientHandler(Socket s, int i,
							ObjectInputStream ois, ObjectOutputStream oos) {
		this.ois = ois;
		this.oos = oos;
		this.i = i;
		this.s = s;
		this.isloggedin=true;
	}

	/** 
	 * this run method creates a infinite loop that handles all the data transfer between the other clients
	*/
	@Override
	public void run() {
		
		while (true)
		{
			try
			{	
				// if the player wants to exit
				// boolean exit = this.ois.readBoolean();
				boolean exit = false;
				if (exit){
					break;
				}
				oos.writeInt(i) ;
				Player p = (Player)this.ois.readObject();
				Server.players.set(i, p); 
				// System.out.println(i+" "+Server.players.get(i).getX());
				int size = Server.players.size();
				this.oos.writeInt(size);
				for(int j=0;j<size;j++){
					this.oos.reset();
					this.oos.writeObject(Server.players.get(j));
					// System.out.println(j+" "+Server.players.get(j).getX());
				}
				// System.out.println(Server.players);
				// this.oos.writeObject(Server.players);



			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
		try
		{
			// closing resources
			this.ois.close();
			this.oos.close();

		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
