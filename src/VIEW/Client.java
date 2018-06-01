package VIEW;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.StringReader;

public class Client {

  private String host;
  private int port;
private static Scanner sc;

  public static void main(String[] args) throws UnknownHostException, IOException {
	  sc = new Scanner(System.in);
	
	  String ip;
	  System.out.println("Seu ip: "+InetAddress.getLocalHost().getHostAddress());
	  System.out.println("Digite o ip do Servidor: ");
	  ip = sc.nextLine();
	  new Client(ip, 12345).run();
  }

  public Client(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public void run() throws UnknownHostException, IOException {
    // conecta cliente no servidor
    Socket client = new Socket(host, port);
    System.out.println("Cliente conectado com sucesso ao servidor!");

    //Obter fluxo de saída do soquete (onde o cliente envia a mesg)
    PrintStream output = new PrintStream(client.getOutputStream());

    // Pede seu nome
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter com seu nome: ");
    String nickname = sc.nextLine();

    // Envia nome ao servidor
    output.println(nickname);

    // Criar um nova Thread para Manipulação de Mensagens do Servidor
    new Thread(new ReceivedMessagesHandler(client.getInputStream())).start();

    // ler mensagens do teclado e enviar para o servidor
    System.out.println("Messages: \n");

    // busca novas mensagens
    while (sc.hasNextLine()) {
      output.println(sc.nextLine());
    }

    // end ctrl D
    output.close();
    sc.close();
    client.close();
  }
}

class ReceivedMessagesHandler implements Runnable {

  private InputStream server;

  public ReceivedMessagesHandler(InputStream server) {
    this.server = server;
  }

  public void run() {
    // receber mensagens do servidor e imprimir na tela
    Scanner s = new Scanner(server);
    String tmp = "";
    while (s.hasNextLine()) {
      tmp = s.nextLine();
      if (tmp.charAt(0) == '[') {
        tmp = tmp.substring(1, tmp.length()-1);
        System.out.println(
            "\nUSERS LIST: " +
            new ArrayList<String>(Arrays.asList(tmp.split(","))) + "\n"
            );
      }else{
        try {
          System.out.println("\n" + getTagValue(tmp));
        } catch(Exception ignore){}
      }
    }
    s.close();
  }

  // pode ser implentador xml para melhorae design
  public static String getTagValue(String xml){
    return  xml.split(">")[2].split("<")[0] + xml.split("<span>")[1].split("</span>")[0];
  }

}
