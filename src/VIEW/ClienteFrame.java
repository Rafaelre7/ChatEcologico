package VIEW;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.*;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.html.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

public class ClienteFrame extends JPanel {

	final JTextPane jtextFilDiscu = new JTextPane();
	final JTextPane jtextListUsers = new JTextPane();
	final JTextField jtextInputChat = new JTextField();
	private JButton jsbtn = null;
	private String oldMsg = "";
	private Thread read;
	private String serverName;
	private int PORT;
	private String name;
	BufferedReader input;
	PrintWriter output;
	Socket server;

	public ClienteFrame() {
		this.serverName = "localhost";
		this.PORT = 12345;
		this.name = "Seu nome...";

		String fontfamily = "Arial, sans-serif";
		Font font = new Font(fontfamily, Font.PLAIN, 15);

		final JFrame jfr = new JFrame("Chat Ecologico");
		jfr.setTitle("Chat Ecologico");
		jfr.getContentPane().setLayout(null);
		jfr.setSize(789, 518);
		jfr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jfr.setLocationRelativeTo(null);
		ImageIcon icone = new ImageIcon("src/Imagens/eco.png");
		jfr.setIconImage(icone.getImage());

		jtextFilDiscu.setBounds(25, 25, 490, 320);
		jtextFilDiscu.setFont(font);
		jtextFilDiscu.setMargin(new Insets(6, 6, 6, 6));
		jtextFilDiscu.setEditable(false);
		JScrollPane jtextFilDiscuSP = new JScrollPane(jtextFilDiscu);
		jtextFilDiscuSP.setBounds(25, 25, 490, 320);

		jtextFilDiscu.setContentType("text/html");
		jtextFilDiscu.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);

		jtextListUsers.setBounds(520, 25, 156, 320);
		jtextListUsers.setEditable(true);
		jtextListUsers.setFont(font);
		jtextListUsers.setMargin(new Insets(6, 6, 6, 6));
		jtextListUsers.setEditable(false);
		JScrollPane jsplistuser = new JScrollPane(jtextListUsers);
		jsplistuser.setBounds(520, 25, 156, 320);

		jtextListUsers.setContentType("text/html");
		jtextListUsers.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);

		jtextInputChat.setBounds(0, 350, 400, 50);
		jtextInputChat.setFont(font);
		jtextInputChat.setMargin(new Insets(6, 6, 6, 6));
		final JScrollPane jtextInputChatSP = new JScrollPane(jtextInputChat);
		jtextInputChatSP.setBounds(25, 350, 650, 50);

		 jsbtn = new JButton("Enviar");
		jsbtn.setFont(font);
		jsbtn.setBounds(600, 410, 160, 35);
		jsbtn.setIcon(new ImageIcon(ClienteFrame.class.getResource("/Imagens/right-arrow.png")));
		
		
		// Evento para desconectar do chat
		final JButton jsbtndeco = new JButton("Desconectar");
		jsbtndeco.setFont(font);
		jsbtndeco.setBounds(25, 410, 160, 35);
		jsbtndeco.setIcon(new ImageIcon(ClienteFrame.class.getResource("/Imagens/exit (2).png")));
		
		

		jtextInputChat.addKeyListener(new KeyAdapter() {
			// Evento para enviar mensagem com botaão enter
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sendMessage();
				}

				// obter a última mensagem digitada
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					String currentMessage = jtextInputChat.getText().trim();
					jtextInputChat.setText(oldMsg);
					oldMsg = currentMessage;
				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					String currentMessage = jtextInputChat.getText().trim();
					jtextInputChat.setText(oldMsg);
					oldMsg = currentMessage;
				}
			}
		});

		final JLabel Lingua = new JLabel("");
		Lingua.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtextInputChat.setText(":P");
				jsbtn.doClick();
			}
		});
		Lingua.setIcon(new ImageIcon(ClienteFrame.class.getResource("/Imagens/Lingua.png")));
		Lingua.setBounds(692, 293, 21, 16);
		jfr.getContentPane().add(Lingua);

		final JLabel Oh = new JLabel("");
		Oh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtextInputChat.setText(":O");
				jsbtn.doClick();
			}
		});
		Oh.setIcon(new ImageIcon(ClienteFrame.class.getResource("/Imagens/Oh.png")));
		Oh.setBounds(692, 321, 21, 16);
		jfr.getContentPane().add(Oh);

		final JLabel Triste = new JLabel("");
		Triste.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtextInputChat.setText(":(");
				jsbtn.doClick();
			}
		});
		Triste.setIcon(new ImageIcon(ClienteFrame.class.getResource("/Imagens/Triste.png")));
		Triste.setBounds(732, 293, 21, 16);
		jfr.getContentPane().add(Triste);

		final JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtextInputChat.setText(":D");
				jsbtn.doClick();
			}
		});
		label.setIcon(new ImageIcon(ClienteFrame.class.getResource("/Imagens/Smile.png")));
		label.setBounds(732, 321, 21, 16);
		jfr.getContentPane().add(label);

		// Clique no botão enviar
		jsbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				sendMessage();
			}
		});

		// Conexão
		final JTextField tfNome = new JTextField(this.name);
		tfNome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfNome.setText("");
			}
		});
		final JTextField tfPorta = new JTextField(Integer.toString(this.PORT));
		tfPorta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfPorta.setText("");
			}
		});
		final JTextField tfEndereco = new JTextField(this.serverName);
		tfEndereco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfEndereco.setText("");
			}
		});
		final JButton btnConectar = new JButton("Conectar");
		btnConectar.setIcon(new ImageIcon(ClienteFrame.class.getResource("/Imagens/local-network (1).png")));

		// verifique se esses campos não estão vazios
		tfNome.getDocument().addDocumentListener(new TextListener(tfNome, tfPorta, tfEndereco, btnConectar));
		tfPorta.getDocument().addDocumentListener(new TextListener(tfNome, tfPorta, tfEndereco, btnConectar));
		tfEndereco.getDocument().addDocumentListener(new TextListener(tfNome, tfPorta, tfEndereco, btnConectar));

		// posição dos módulos
		btnConectar.setFont(new Font("Dialog", Font.BOLD, 15));
		tfEndereco.setBounds(25, 380, 135, 40);
		tfNome.setBounds(375, 380, 135, 40);
		tfPorta.setBounds(200, 380, 135, 40);
		btnConectar.setBounds(541, 380, 226, 74);

		// cor padrão dos módulos de thread e lista de usuários
		jtextFilDiscu.setBackground(Color.LIGHT_GRAY);
		jtextListUsers.setBackground(Color.LIGHT_GRAY);
		
				JLabel lblNewLabel = new JLabel("");
				lblNewLabel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Emoticons",
						TitledBorder.LEADING, TitledBorder.TOP, null, null));
				lblNewLabel.setBounds(682, 277, 85, 68);
				jfr.getContentPane().add(lblNewLabel);
		
		JLabel cores = new JLabel("");
		
		cores.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Cores", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		cores.setBounds(682, 25, 85, 245);
		jfr.getContentPane().add(cores);
		
		JLabel black = new JLabel("");
		black.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtextInputChat.setText("#000000");
				jsbtn.doClick();
				
				
			}
		});
		black.setIcon(new ImageIcon("C:\\Users\\Rafael Pimenta\\Desktop\\Login APS\\Cores\\Black.png"));
		black.setBounds(698, 49, 55, 16);
		jfr.getContentPane().add(black);
		
		JLabel red = new JLabel("");
		red.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtextInputChat.setText("#e15258");
				jsbtn.doClick();
			}
		});
		red.setIcon(new ImageIcon("C:\\Users\\Rafael Pimenta\\Desktop\\Login APS\\Cores\\red.png"));
		red.setBounds(698, 77, 55, 16);
		jfr.getContentPane().add(red);
		
		JLabel green = new JLabel("");
		green.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtextInputChat.setText("#51b46d");
				jsbtn.doClick();
			}
		});
		green.setIcon(new ImageIcon("C:\\Users\\Rafael Pimenta\\Desktop\\Login APS\\Cores\\green.png"));
		green.setBounds(698, 105, 55, 16);
		jfr.getContentPane().add(green);
		
		JLabel mostard = new JLabel("");
		mostard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtextInputChat.setText("#8b4513");
				jsbtn.doClick();
			}
		});
		mostard.setIcon(new ImageIcon("C:\\Users\\Rafael Pimenta\\Desktop\\Login APS\\Cores\\Brown.png"));
		mostard.setBounds(698, 133, 55, 16);
		jfr.getContentPane().add(mostard);
		
		JLabel Orange = new JLabel("");
		Orange.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtextInputChat.setText("#c71585");
				jsbtn.doClick();
				
			}
		});
		Orange.setIcon(new ImageIcon("C:\\Users\\Rafael Pimenta\\Desktop\\Login APS\\Cores\\purple.png"));
		Orange.setBounds(698, 161, 55, 16);
		jfr.getContentPane().add(Orange);
		
		JLabel blue = new JLabel("");
		blue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtextInputChat.setText("#00008b");
				jsbtn.doClick();
			}
		});
		blue.setIcon(new ImageIcon("C:\\Users\\Rafael Pimenta\\Desktop\\Login APS\\Cores\\Blue.png"));
		blue.setBounds(698, 193, 55, 16);
		jfr.getContentPane().add(blue);
		
		JLabel cian = new JLabel("");
		cian.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtextInputChat.setText("#008b8b");
				jsbtn.doClick();
			}
		});
		cian.setIcon(new ImageIcon("C:\\Users\\Rafael Pimenta\\Desktop\\Login APS\\Cores\\Cian.png"));
		cian.setBounds(698, 243, 55, 16);
		jfr.getContentPane().add(cian);
		
		JLabel gray = new JLabel("");
		gray.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtextInputChat.setText("#696969");
				jsbtn.doClick();
			}
		});
		gray.setIcon(new ImageIcon("C:\\Users\\Rafael Pimenta\\Desktop\\Login APS\\Cores\\Gray.png"));
		gray.setBounds(698, 221, 55, 16);
		jfr.getContentPane().add(gray);

		// adicionando elementos
		jfr.getContentPane().add(btnConectar);
		jfr.getContentPane().add(jtextFilDiscuSP);
		jfr.getContentPane().add(jsplistuser);
		jfr.getContentPane().add(tfNome);
		jfr.getContentPane().add(tfPorta);
		jfr.getContentPane().add(tfEndereco);
		jfr.setVisible(true);

		// Informações para usuario
		appendToPane(jtextFilDiscu, "<h4>Os comandos possiveis no chat são :</h4>" + "<ul>"
				+ "<li><b>@Nome</b> Pode ser enviado uma mensagem privada usando '@NomeUsuario'</li>"
				+ "<li><b>#d3961b</b> Para mudar a cor de seu nome na exibição coloque o codigo hexadecimal desejado</li>"
				+ "<li><b>;)</b>Possivel usar alguns Emoticons</li>"
				+ "<li><b>Seta para cima</b> Para pegar a ultima mensagem digitada</li>" + "</ul><br/>");

		final JLabel lbIP = new JLabel("IP...:");
		lbIP.setFont(new Font("SansSerif", Font.BOLD, 15));
		lbIP.setBounds(25, 357, 67, 16);
		jfr.getContentPane().add(lbIP);

		final JLabel lbPortaServidor = new JLabel("Porta Servidor...:");
		lbPortaServidor.setFont(new Font("SansSerif", Font.BOLD, 15));
		lbPortaServidor.setBounds(200, 357, 135, 16);
		jfr.getContentPane().add(lbPortaServidor);

		final JLabel lbNome = new JLabel("Nome...:");
		lbNome.setFont(new Font("SansSerif", Font.BOLD, 15));
		lbNome.setBounds(375, 357, 135, 16);
		jfr.getContentPane().add(lbNome);

		// On connect
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					name = tfNome.getText();
					String port = tfPorta.getText();
					serverName = tfEndereco.getText();
					PORT = Integer.parseInt(port);

					appendToPane(jtextFilDiscu,
							"<span>Conectando à " + serverName + " na porta " + PORT + "...</span>");
					server = new Socket(serverName, PORT);
		

					appendToPane(jtextFilDiscu, "<span>Conectado à " + server.getRemoteSocketAddress() + "</span>");

					input = new BufferedReader(new InputStreamReader(server.getInputStream()));
					output = new PrintWriter(server.getOutputStream(), true);

					// Envia seu nome ao servidor
					output.println(name);

					// criar novo thread
					read = new Read();
					read.start();
					jfr.remove(tfNome);
					jfr.remove(tfPorta);
					jfr.remove(tfEndereco);
					jfr.remove(btnConectar);
					jfr.remove(lbIP);
					jfr.remove(lbNome);
					jfr.remove(lbPortaServidor);
					jfr.getContentPane().add(jsbtn);
					jfr.getContentPane().add(jtextInputChatSP);
					jfr.getContentPane().add(jsbtndeco);

					jfr.revalidate();
					jfr.repaint();
					jtextFilDiscu.setBackground(Color.WHITE);
					jtextListUsers.setBackground(Color.WHITE);
				} catch (Exception ex) {
					appendToPane(jtextFilDiscu, "<span>Não pode conectar ao servidor</span>");
					JOptionPane.showMessageDialog(jfr, ex.getMessage());
				}
			}

		});

		jsbtndeco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				jfr.getContentPane().add(tfNome);
				jfr.getContentPane().add(tfPorta);
				jfr.getContentPane().add(tfEndereco);
				jfr.getContentPane().add(btnConectar);
				jfr.remove(jsbtn);
				jfr.remove(jtextInputChatSP);
				jfr.remove(jsbtndeco);
				jfr.revalidate();
				jfr.repaint();
				read.interrupt();
				jtextListUsers.setText(null);
				jtextFilDiscu.setBackground(Color.LIGHT_GRAY);
				jtextListUsers.setBackground(Color.LIGHT_GRAY);
				appendToPane(jtextFilDiscu, "<span>Conexão encerrada.</span>");
				output.close();
			}
		});

	}

	// verifique se todos os campos não estão vazios
	public class TextListener implements DocumentListener {
		JTextField jtf1;
		JTextField jtf2;
		JTextField jtf3;
		JButton jcbtn;

		public TextListener(JTextField jtf1, JTextField jtf2, JTextField jtf3, JButton jcbtn) {
			this.jtf1 = jtf1;
			this.jtf2 = jtf2;
			this.jtf3 = jtf3;
			this.jcbtn = jcbtn;
		}

		public void changedUpdate(DocumentEvent e) {
		}

		public void removeUpdate(DocumentEvent e) {
			if (jtf1.getText().trim().equals("") || jtf2.getText().trim().equals("")
					|| jtf3.getText().trim().equals("")) {
				jcbtn.setEnabled(false);
			} else {
				jcbtn.setEnabled(true);
			}
		}

		public void insertUpdate(DocumentEvent e) {
			if (jtf1.getText().trim().equals("") || jtf2.getText().trim().equals("")
					|| jtf3.getText().trim().equals("")) {
				jcbtn.setEnabled(false);
			} else {
				jcbtn.setEnabled(true);
			}
		}

	}

	// enviando mensagens
	public void sendMessage() {
		try {
			String message = jtextInputChat.getText().trim();
			if (message.equals("")) {
				return;
			}
			this.oldMsg = message;
			output.println(message);
			jtextInputChat.requestFocus();
			jtextInputChat.setText(null);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			System.exit(0);
		}
	}

	public static void main(String[] args) throws Exception {
		ClienteFrame client = new ClienteFrame();
	}

	// ler novas mensagens recebidas
	class Read extends Thread {
		public void run() {
			String message;
			while (!Thread.currentThread().isInterrupted()) {
				try {
					message = input.readLine();
					if (message != null) {
						if (message.charAt(0) == '[') {
							message = message.substring(1, message.length() - 1);
							ArrayList<String> ListUser = new ArrayList<String>(Arrays.asList(message.split(", ")));
							jtextListUsers.setText(null);
							for (String user : ListUser) {
								appendToPane(jtextListUsers, "@" + user);

							}
						} else {
							appendToPane(jtextFilDiscu, message);
						}
					}
				} catch (IOException ex) {
					System.err.println("Não foi possível reconhecer a mensagem recebida");
				}
			}
		}
	}

	// send html to pane
	private void appendToPane(JTextPane tp, String msg) {
		HTMLDocument doc = (HTMLDocument) tp.getDocument();
		HTMLEditorKit editorKit = (HTMLEditorKit) tp.getEditorKit();
		try {
			editorKit.insertHTML(doc, doc.getLength(), msg, 0, 0, null);
			tp.setCaretPosition(doc.getLength());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
