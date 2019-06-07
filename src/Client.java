import java.awt.Point;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

/**  
 * The Client Class contains information and starts the Game for each indivdual client
 * It communicates with the server on separate threads (one for sending data and one for reading data)
 * 
 */
public class Client
{
	final static int ServerPort = 7777;
	static Player player;
	static int clientnumber;
	int numDead = 0;
	public static ArrayList<Integer> deadPlayers = new ArrayList<Integer>();
	public static ArrayList<Point> playerPos = new ArrayList<Point>();

	/** 
	 * main method of the client
	 * Creates new game instance and two separate threads for each client
	*/
	public static void main(String args[]) throws UnknownHostException, IOException
	{
		Game game = new Game();
		player = Game.player;

		Scanner scn = new Scanner(System.in);

		// getting localhost ip
		InetAddress ip = InetAddress.getByName("172.20.10.8");

		// establish the connection
		Socket s = new Socket(ip, ServerPort);

		// obtaining input and out streams
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());


		// sendMessage thread
		Thread sendMessage = new Thread(new Runnable()
		{
			@Override
			public void run() {
				while (true) {

					// read the message to deliver.
					// String msg = scn.nextLine();

					try {
						// write on the output stream
						dos.writeDouble(player.getX());
						dos.writeDouble(player.getY());
						// int deadsize = deadPlayers.size();
						// dos.writeInt(deadsize);
						// for(int k = 0; k<deadsize;k++){
						// 	dos.writeInt(deadPlayers.get(k).intValue());
						// }
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		// readMessage thread
		Thread readMessage = new Thread(new Runnable()
		{
			@Override
			public void run() {

				while (true) {
					try {
						// read the message sent to this client
						int size = dis.readInt();
						clientnumber = dis.readInt();
						int arraysize = playerPos.size();
						for(int i =0;i<size-arraysize;i++){
							playerPos.add(new Point());
						}
						for(int i = 0; i<size;i++){
							playerPos.set(i,new Point((int)dis.readDouble(),(int)dis.readDouble()));
						}
						int num = dis.readInt();
						for(int k = 0; k<num;k++){
							if(dis.readInt()==clientnumber){
								System.out.println(clientnumber+" is dead");
								player.setDead(true);
							}
						}
	
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
			}
		});

		sendMessage.start();
		readMessage.start();

	}
}
