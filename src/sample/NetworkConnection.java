package sample;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class NetworkConnection {
	
	private ConnThread connthread = new ConnThread();
	private Consumer<Serializable> callback;

	// List of Clients
	ArrayList<ClientThread> threads;
	boolean clientOne, clientTwo;
	String dataOne, dataTwo;
	int score1 = 0;
	int score2 = 0;
	
	public NetworkConnection(Consumer<Serializable> callback) {
		this.callback = callback;
		connthread.setDaemon(true);
		threads = new ArrayList<ClientThread>();
		clientOne = false;
		clientTwo = false;
		
	}
	
	public void startConn() throws Exception{
		connthread.start();
	}
	
	/*public void send(Serializable data) throws Exception{
		for(ClientThread x : threads){
			x.output.writeObject(data);
		}

	}*/
	public String compare(){
		int player1 = 0;
		int player2 = 0;
		String player1Hand = dataOne;
		String player2Hand = dataTwo;
		if(player1Hand.equals("Exit")){
			return ("Player1 Left the Game\n");
		}
		if(player2Hand.equals("Exit")){
			return ("Player2 Left the Game\n");
		}
		if (player1Hand.equals("Play Again") && player2Hand.equals("Play Again")){
			score1=0;
			score2=0;
			return ("Game Restarted!! Good Luck\n");
		}
		else if (player1Hand.equals("Play Again") && !player2Hand.equals("Play Again")){
			return ("Player2 Left!! Please get an opponent to play with\n");

		}
		else if (!player1Hand.equals("Play Again") && player2Hand.equals("Play Again")){
			return ("Player1 Left!! Please get an opponent to play with\n");

		}

		int maxScore = score1;
		if (score2 > maxScore){
			maxScore = score2;
		}

		while (maxScore<2){
			// get player 1 hand
			if (player1Hand.equals("User Played Rock")){
				player1 = 10;
			}
			else if(player1Hand.equals("User Played Paper")){
				player1 = 20;
			}
			else if(player1Hand.equals("User Played Scissors")){
				player1 = 30;
			}
			else if(player1Hand.equals("User Played Lizard")){
				player1 = 40;
			}
			else if(player1Hand.equals("User Played Spock")){
				player1 = 50;
			}

			// get player 2 hand
			if (player2Hand.equals("User Played Rock")){
				player2 = 10;
			}
			else if(player2Hand.equals("User Played Paper")){
				player2 = 20;
			}
			else if(player2Hand.equals("User Played Scissors")){
				player2 = 30;
			}
			else if(player2Hand.equals("User Played Lizard")){
				player2 = 40;
			}
			else if(player2Hand.equals("User Played Spock")){
				player2 = 50;
			}

			if(player1 == player2){
				return ("Tie since both had same hand: " + dataOne + "\nPlayer1 Score: " + score1 + " Player2 Score: " + score2);
			}

			// Compare to Rock
			else if(player1 == 20 && player2 == 10){
				score1++;
				return ("Player1 Wins: " + dataOne + "\nPlayer1 Score: " + score1 + " Player2 Score: " + score2);

			}
			else if(player1 == 30 && player2 == 10){
				score2++;
				return ("Player2 Wins: " + dataTwo + "\nPlayer1 Score: " + score1 + " Player2 Score: " + score2);

			}
			else if(player1 == 40 && player2 == 10){
				score2++;
				return ("Player2 Wins: " + dataTwo + "\nPlayer1 Score: " + score1 + " Player2 Score: " + score2);
			}
			else if(player1 == 50 && player2 == 10){
				score1++;
				return ("Player1 Wins: " + dataOne + "\nPlayer1 Score: " + score1 + " Player2 Score" + score2);
			}

			// Compare to paper
			else if(player1 == 10 && player2 == 20){
				score2++;
				return ("Player2 Wins: " + dataTwo + "\nPlayer1 Score: " + score1 + " Player2 Score: " + score2);

			}
			else if(player1 == 30 && player2 == 20){
				score1++;
				return ("Player1 Wins: " + dataOne + "\nPlayer1 Score: " + score1 + " Player2 Score:" + score2);

			}
			else if(player1 == 40 && player2 == 20){
				score1++;
				return ("Player1 Wins: " + dataOne + "\nPlayer1 Score: " + score1 + " Player2 Score: " + score2);

			}
			else if(player1 == 50 && player2 == 20){
				score2++;
				return ("Player2 Wins: " + dataTwo + "\nPlayer1 Score: " + score1 + " Player2 Score: " + score2);

			}

			// Compare to Scissors
			else if(player1 == 10 && player2 == 30){
				score1++;
				return ("Player1 Wins: " + dataOne + "\nPlayer1 Score: " + score1 + " Player2 Score: " + score2);

			}
			else if(player1 == 20 && player2 == 30){
				score2++;
				return ("Player2 Wins: " + dataTwo + "\nPlayer1 Score: " + score1 + " Player2 Score: " + score2);

			}
			else if(player1 == 40 && player2 == 30){
				score2++;
				return ("Player2 Wins: " + dataTwo + "\nPlayer1 Score: " + score1 + " Player2 Score: " + score2);

			}
			else if(player1 == 50 && player2 == 30){
				score1++;
				return ("Player1 Wins: " + dataOne + "\nPlayer1 Score: " + score1 + " Player2 Score: " + score2);

			}

			// Compare to Lizard
			else if(player1 == 10 && player2 == 40){
				score1++;
				return ("Player1 Wins: " + dataOne + "\nPlayer1 Score: " + score1 + " Player2 Score: " + score2);

			}
			else if(player1 == 20 && player2 == 40){
				score2++;
				return ("Player2 Wins: " + dataTwo + "\nPlayer1 Score: " + score1 + " Player2 Score: " + score2);

			}
			else if(player1 == 30 && player2 == 40){
				score1++;
				return ("Player1 Wins: " + dataOne + "\nPlayer1 Score: " + score1 + " Player2 Score: " + score2);

			}
			else if(player1 == 50 && player2 == 40){
				score2++;
				return ("Player2 Wins: " + dataTwo + "\nPlayer1 Score: " + score1 + " Player2 Score: " + score2);

			}

			// Compare to Spock
			else if(player1 == 10 && player2 == 50){
				score2++;
				return ("Player2 Wins: " + dataTwo + "\nPlayer1 Score: " + score1 + " Player2 Score: " + score2);

			}
			else if(player1 == 20 && player2 == 50){
				score1++;
				return ("Player1 Wins: " + dataOne + "\nPlayer1 Score: " + score1 + " Player2 Score: " + score2);

			}
			else if(player1 == 30 && player2 == 50){
				score2++;
				return ("Player2 Wins: " + dataTwo + "\nPlayer1 Score: " + score1 + " Player2 Score: " + score2);

			}
			else if(player1 == 40 && player2 == 50){
				score1++;
				return ("Player1 Wins: " + dataOne + "\nPlayer1 Score: " + score1 + " Player2 Score: " + score2);

			}

		}
		if (score2>score1){
			return ("Player 2 Wins!! You can exit or play again!!\n");
		}
		else{
			return ("Player 1 Wins!! You can exit or play again!!\n");
		}

		//return ("Something very bad has occurred\n");

	}
	public void send(Serializable data) throws Exception{
		if(isServer() && clientOne && clientTwo){
			String message = compare();
			threads.forEach(t -> {try{t.output.writeObject(message);
				clientOne = false; clientTwo = false;}
			catch(Exception e){e.printStackTrace();}
			});

		}
		else{
			connthread.out.writeObject(data);
		}

	}
	
	public void closeConn() throws Exception{
		connthread.socket.close();
	}
	
	abstract protected boolean isServer();
	abstract protected String getIP();
	abstract protected int getPort();

	public class ClientThread extends Thread {
		Socket connection;
		int number;
		ObjectOutputStream output;
		ObjectInputStream input;
		ClientThread(Socket socket, int num){
			this.connection = socket;
			this.number = num;
		}

		public void run() {
			/*try(ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
					Socket socket = isServer() ? server.accept() : new Socket(getIP(), getPort());
					ObjectOutputStream out = new ObjectOutputStream( socket.getOutputStream());
					ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){

				this.socket = socket;
				this.out = out;
				socket.setTcpNoDelay(true);

				while(true) {
					Serializable data = (Serializable) in.readObject();
					callback.accept(data);
				}

			}
			catch(Exception e) {
				callback.accept("connection Closed");
			}*/
			try( ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
				 ObjectInputStream in = new ObjectInputStream(connection.getInputStream())) {
				connection.setTcpNoDelay(true);
				this.output = out;
				this.input = in;
				output.writeObject("Welcome Player: " + number);
				while(true){
					Serializable data = (Serializable) in.readObject();
					if(number == 1){
						clientOne= true;
						dataOne = data.toString();
					}
					else{
						clientTwo = true;
						dataTwo = data.toString();

					}
					callback.accept("Player " + number + " : " + data);
				}
			}
			catch (Exception e){
				callback.accept("Connection Closed");
			}
		}
	}

	class ConnThread extends Thread{
		private Socket socket;
		private ObjectOutputStream out;


		public void run() {
			int number = 1;
			/*try(ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
					Socket socket = isServer() ? server.accept() : new Socket(getIP(), getPort());
					ObjectOutputStream out = new ObjectOutputStream( socket.getOutputStream());
					ObjectInputStream in = new ObjectInputStream(socket.getInputStream())){
				
				this.socket = socket;
				this.out = out;
				socket.setTcpNoDelay(true);
				
				while(true) {
					Serializable data = (Serializable) in.readObject();
					callback.accept(data);
				}
				
			}
			catch(Exception e) {
				callback.accept("connection Closed");
			}*/
			if(isServer()){
				try{
					ServerSocket server = new ServerSocket(getPort());
					while(true) {
						ClientThread t1 = new ClientThread(server.accept(), number);
						number++;
						threads.add(t1);
						t1.start();
					}
				}
				catch (Exception e){

				}
			}
			else{
				try{
					Socket socket = new Socket(getIP(), getPort());
					ObjectOutputStream out = new ObjectOutputStream( socket.getOutputStream());
					ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
					this.socket = socket;
					this.out = out;
					socket.setTcpNoDelay(true);

					while(true) {
						Serializable data = (Serializable) in.readObject();
						callback.accept(data);
					}

				}
				catch(Exception e) {
					callback.accept("connection Closed");
				}

			}
		}


	}


	
}	

