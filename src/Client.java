import java.awt.Point;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Client Class contains information and starts the Game for each indivdual
 * client It communicates with the server on separate threads (one for sending
 * data and one for reading data)
 * 
 */
public class Client {
	final static int ServerPort = 7777;
	static int clientnumber = 0;

	/**
	 * main method of the client Creates new game instance and two separate threads
	 * for each client
	 */
	public static void main(String args[]) throws UnknownHostException, IOException {
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
		Thread sendData = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) { 
					try {
						synchronized(Game.player){
							oos.reset();
							// write on the output stream
							oos.writeObject(Game.player);
							if(Game.player.isWon()){
								break;
							}
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		// readData thread
		Thread readData = new Thread(new Runnable() {
			@Override
			public void run() {

				while (true) {
					try {
						clientnumber = ois.readInt();
						// synchronized(Game.player){
							// read the data sent to this client
							int size = ois.readInt();
							int currsize = Game.players.size();
							for (int i = 0; i < size - currsize; i++) {
								Game.players.add(new Player());
							}
							for (int i = 0; i < size; i++) {
								Player p = (Player) ois.readObject();
								Game.players.set(i, p);
								// System.out.println("Client "+clientnumber+" player "+i+" "+Game.players.get(i).getX());
							}
						
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
