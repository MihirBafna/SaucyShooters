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
	static ArrayList<Point> playerPos = new ArrayList<Point>();

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

			playerPos.add(new Point());
			// obtain input and output streams
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());

			System.out.println("Creating a new handler for this client...");

			// Create a new handler object for handling this request.
			ClientHandler mtch = new ClientHandler(s,i, dis, dos);

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
	final DataInputStream dis;
	final DataOutputStream dos;
	Socket s;
	boolean isloggedin;
	public static ArrayList<Integer> deadPlayers = new ArrayList<Integer>();


	/**
	 * Constructor
	 * @param Socket
	 * @param int
	 * @param DataInputStream
	 * @param DataOutputStream
	 */
	public ClientHandler(Socket s, int i,
							DataInputStream dis, DataOutputStream dos) {
		this.dis = dis;
		this.dos = dos;
		this.i = i;
		this.s = s;
		this.isloggedin=true;
	}

	/** 
	 * this run method creates a infinite loop that handles all the data transfer between the other clients
	*/
	@Override
	public void run() {
		double receivedX;
		double receivedY;
		int deadsize = 0;
		while (true)
		{
			try
			{
				receivedX = dis.readDouble();
				receivedY = dis.readDouble();
				
				Server.playerPos.set(i,new Point((int)receivedX,(int)receivedY));
				deadsize = dis.readInt();
				for(int k = 0; k<deadsize;k++){
					deadPlayers.add(dis.readInt());
				}
				this.dos.writeInt(Server.i);
				this.dos.writeInt(this.i);
				for(int j =0; j<Server.i;j++){
					this.dos.writeDouble(Server.playerPos.get(j).getX());
					this.dos.writeDouble(Server.playerPos.get(j).getY());
				}
				this.dos.writeInt(deadPlayers.size());
				for (int k = 0; k < deadPlayers.size(); k++) {
					this.dos.writeInt(deadPlayers.get(k).intValue());
				}
				if(receivedX == -1){
					break;
				}

			} catch (IOException e) {

				e.printStackTrace();
			}

		}
		try
		{
			// closing resources
			this.dis.close();
			this.dos.close();

		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
