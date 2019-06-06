
// Java implementation for multithreaded chat client
// Save file as Client.java

import java.awt.Point;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Client
{
	final static int ServerPort = 7777;
	static Player player;
	static int clientnumber;
	public static ArrayList<Point> playerPos = new ArrayList<Point>();

	public static void main(String args[]) throws UnknownHostException, IOException
	{
		Game game = new Game();
		player = Game.player;

		Scanner scn = new Scanner(System.in);

		// getting localhost ip
		InetAddress ip = InetAddress.getByName("localhost");

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
						for(int i =0;i<=size-playerPos.size();i++){
							playerPos.add(new Point());
						}
						for(int i = 0; i<size;i++){
							playerPos.set(i,new Point((int)dis.readDouble(),(int)dis.readDouble()));
						}
						// for(int i=0;i<size;i++){
						// 	System.out.println(playerPos.get(i).getX()+","+ playerPos.get(i).getY());
						// }
						
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
