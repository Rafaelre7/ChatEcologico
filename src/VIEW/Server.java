package VIEW;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;


import java.util.regex.Matcher;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

public class Server {

	private int port;
	private List<User> clients;
	private ServerSocket server;
	private static Scanner sc;
	static int porta = 12345;
	static String aux;

	public static void main(String[] args) throws IOException {
		
		aux = JOptionPane.showInputDialog(null, "Entre com a porta : \n"+"Seu ip :"+InetAddress.getLocalHost().getHostAddress());
		porta = Integer.parseInt(aux);
		new Server(porta).run();
		

		
	}

	
	public Server(int port) {
		this.port = port;
		this.clients = new ArrayList<User>();
	}

	public void run() throws IOException {
		server = new ServerSocket(port) {
			protected void finalize() throws IOException {
				this.close();
			}
		};
		JOptionPane.showMessageDialog(null,"Servidor conectado na porta: " + port + " , Ip: " + InetAddress.getLocalHost().getHostAddress());
		System.out.println(
				"Servidor conectado na porta: " + port + " ,Ip: " + InetAddress.getLocalHost().getHostAddress());

		while (true) {
			// Aceita novos clientes
			Socket client = server.accept();

			// Pega nome no usuario
			String nickname = (new Scanner(client.getInputStream())).nextLine();
			nickname = nickname.replace(",", ""); // ',' use for serialisation
			nickname = nickname.replace(" ", "_");
			System.out.println(
					"Novo Usuario : \"" + nickname + "\"\n\t     Host:" + client.getInetAddress().getHostAddress());

			// Cria novo usuario
			User newUser = new User(client, nickname);

			// Adc novo usuario a lista
			this.clients.add(newUser);

			// Mensagem de bem vindo
			newUser.getOutStream().println("<b>Bem Vindo</b> " + newUser.toString());

			// criar um novo segmento para manipulação de novas mensagens recebidas pelo
			// usuário
			new Thread(new UserHandler(this, newUser)).start();
		}
	}

	// excluir um usuário da lista
	public void removeUser(User user) {
		this.clients.remove(user);
	}

	// enviar mensagens recebidas para todos os usuários
	public void broadcastMessages(String msg, User userSender) {
		for (User client : this.clients) {
			client.getOutStream().println(userSender.toString() + "<span>: " + msg + "</span>");
		}
	}

	// public void inicia() throws IOException {
	//
	// s.run();
	// }

	// enviar lista de clientes para todos os usuários
	public void broadcastAllUsers() {
		for (User client : this.clients) {
			client.getOutStream().println(this.clients);
		}
	}

	// enviar mensagem para um usuário (String)
	public void sendMessageToUser(String msg, User userSender, String user) {
		boolean find = false;
		for (User client : this.clients) {
			if (client.getNickname().equals(user) && client != userSender) {
				find = true;
				userSender.getOutStream().println(userSender.toString() + " -> " + client.toString() + ": " + msg);
				client.getOutStream()
						.println("(<b>Private</b>)" + userSender.toString() + "<span>: " + msg + "</span>");
			}
		}
		if (!find) {
			userSender.getOutStream().println(userSender.toString() + " -> (<b>no one!</b>): " + msg);
		}
	}
}

class UserHandler implements Runnable {

	private Server server;
	private User user;

	public UserHandler(Server server, User user) {
		this.server = server;
		this.user = user;
		this.server.broadcastAllUsers();
	}

	public void run() {
		String message;

		// quando há uma nova mensagem, transmitida para todos
		Scanner sc = new Scanner(this.user.getInputStream());
		while (sc.hasNextLine()) {
			message = sc.nextLine();

			// Emoticon
			message = message.replace(":)",
					"<img src='http://4.bp.blogspot.com/-ZgtYQpXq0Yo/UZEDl_PJLhI/AAAAAAAADnk/2pgkDG-nlGs/s1600/facebook-smiley-face-for-comments.png'>");
			message = message.replace(":D",
					"<img src='http://2.bp.blogspot.com/-OsnLCK0vg6Y/UZD8pZha0NI/AAAAAAAADnY/sViYKsYof-w/s1600/big-smile-emoticon-for-facebook.png'>");
			message = message.replace(":d",
					"<img src='http://2.bp.blogspot.com/-OsnLCK0vg6Y/UZD8pZha0NI/AAAAAAAADnY/sViYKsYof-w/s1600/big-smile-emoticon-for-facebook.png'>");
			message = message.replace(":(",
					"<img src='http://2.bp.blogspot.com/-rnfZUujszZI/UZEFYJ269-I/AAAAAAAADnw/BbB-v_QWo1w/s1600/facebook-frown-emoticon.png'>");
			message = message.replace("-_-",
					"<img src='http://3.bp.blogspot.com/-wn2wPLAukW8/U1vy7Ol5aEI/AAAAAAAAGq0/f7C6-otIDY0/s1600/squinting-emoticon.png'>");
			message = message.replace(";)",
					"<img src='http://1.bp.blogspot.com/-lX5leyrnSb4/Tv5TjIVEKfI/AAAAAAAAAi0/GR6QxObL5kM/s400/wink%2Bemoticon.png'>");
			message = message.replace(":P",
					"<img src='http://4.bp.blogspot.com/-bTF2qiAqvi0/UZCuIO7xbOI/AAAAAAAADnI/GVx0hhhmM40/s1600/facebook-tongue-out-emoticon.png'>");
			message = message.replace(":p",
					"<img src='http://4.bp.blogspot.com/-bTF2qiAqvi0/UZCuIO7xbOI/AAAAAAAADnI/GVx0hhhmM40/s1600/facebook-tongue-out-emoticon.png'>");
			message = message.replace(":o",
					"<img src='http://1.bp.blogspot.com/-MB8OSM9zcmM/TvitChHcRRI/AAAAAAAAAiE/kdA6RbnbzFU/s400/surprised%2Bemoticon.png'>");
			message = message.replace(":O",
					"<img src='http://1.bp.blogspot.com/-MB8OSM9zcmM/TvitChHcRRI/AAAAAAAAAiE/kdA6RbnbzFU/s400/surprised%2Bemoticon.png'>");
			
			// Gerenciamento de mensagens privadas
			if (message.charAt(0) == '@') {
				if (message.contains(" ")) {
					System.out.println("private msg : " + message);
					int firstSpace = message.indexOf(" ");
					String userPrivate = message.substring(1, firstSpace);
					server.sendMessageToUser(message.substring(firstSpace + 1, message.length()), user, userPrivate);
				}

			} else if (message.charAt(0) == '#') {
				user.changeColor(message);
				// atualizar cor para todos os outros usuários
				this.server.broadcastAllUsers();
			} else {
				// atualizar lista de usuários
				server.broadcastMessages(message, user);
			}
		}
		// end of Thread
		server.removeUser(user);
		this.server.broadcastAllUsers();
		sc.close();
	}
}

class User {
	private static int nbUser = 0;
	private int userId;
	private PrintStream streamOut;
	private InputStream streamIn;
	private String nickname;
	private Socket client;
	private String color;

	// constructor
	public User(Socket client, String name) throws IOException {
		this.streamOut = new PrintStream(client.getOutputStream());
		this.streamIn = client.getInputStream();
		this.client = client;
		this.nickname = name;
		this.userId = nbUser;
		this.color = ColorInt.getColor(this.userId);
		nbUser += 1;
	}

	// mudar de cor usuário
	public void changeColor(String hexColor) {
		// verifique se é uma cor hexadecimal válida
		Pattern colorPattern = Pattern.compile("#([0-9a-f]{3}|[0-9a-f]{6}|[0-9a-f]{8})");
		Matcher m = colorPattern.matcher(hexColor);
		if (m.matches()) {
			Color c = Color.decode(hexColor);
			double luma = 0.2126 * c.getRed() + 0.7152 * c.getGreen() + 0.0722 * c.getBlue();
			if (luma > 160) {
				this.getOutStream().println("<b>Cor muito clara</b>");
				return;
			}
			this.color = hexColor;
			this.getOutStream().println("<b>Cor alterada com sucesso</b> " + this.toString());
			return;
		}
		this.getOutStream().println("<b>Não foi possível alterar a cor por não esta em nosso banco de cores</b>");
	}

	public PrintStream getOutStream() {
		return this.streamOut;
	}

	public InputStream getInputStream() {
		return this.streamIn;
	}

	public String getNickname() {
		return this.nickname;
	}

	// print user with his color
	public String toString() {

		return "<u><span style='color:" + this.color + "'>" + this.getNickname() + "</span></u>";

	}
}

class ColorInt {
	public static String[] mColors = { "#3079ab", // dark blue
			"#e15258", // red
			"#f9845b", // orange
			"#7d669e", // purple
			"#53bbb4", // aqua
			"#51b46d", // green
			"#e0ab18", // mustard
			"#f092b0", // pink
			"#e8d174", // yellow
			"#e39e54", // orange
			"#d64d4d", // red
			"#4d7358", // green
	};

	public static String getColor(int i) {
		return mColors[i % mColors.length];
	}
}
