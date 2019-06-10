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
	static int clientnumber;

	/** 
	 * main method of the client
	 * Creates new game instance and two separate threads for each client
	*/
	public static void main(String args[]) throws UnknownHostException, IOException
	{
		Game game = new Game();
		Game.players.add(Game.player);

		// getting localhost ip
		InetAddress ip = InetAddress.getByName("localhost");

		// establish the connection
		Socket s = new Socket(ip, ServerPort);

		// obtaining input and out streams
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());


		// sendData thread
		Thread sendData = new Thread(new Runnable()
		{
			@Override
			public void run() {
				while (true) {
					try {
						// boolean exit = false;
						// if (Game.players.get(clientnumber).getHp() <= 0) {
						// 	exit = true;
						// }
						// oos.writeBoolean(exit);
						// write on the output stream
						oos.reset();
						oos.writeObject(Game.player);

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		// readData thread
		Thread readData = new Thread(new Runnable()
		{
			@Override
			public void run() {

				while (true) {
					try {
						// read the data sent to this client
						int size = ois.readInt();
						int currsize = Game.players.size();
						for(int i = 0; i<size-currsize;i++){
							Game.players.add(new Player());
						}
						for(int i = 0; i<size;i++){
							Player p = (Player) ois.readObject();
							Game.players.set(i,p);
							// System.out.println(i+" "+Game.players.get(i).getX());
						}
						// System.out.println(Game.players);
						// Thread.sleep((long)100);
						// Game.players = (ArrayList<Player>) ois.readObject();

					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});

		sendData.start();
		readData.start();

	}
}
