package VIEW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ENTIDADE.Usuario;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Panel;
import java.awt.Font;
import java.awt.ScrollPane;
import javax.swing.border.TitledBorder;
import org.h2.tools.Server;

import DAO.PerfilDAO;

import javax.swing.JScrollPane;
import java.awt.List;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

public class TelaPrincipalFrame extends JFrame {

	private JPanel contentPane;
	private Usuario usuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipalFrame frame = new TelaPrincipalFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipalFrame() {
		setUndecorated(true);
		setLocationByPlatform(true);
		setTitle("CHAT ECOLOGICO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		ImageIcon icone = new ImageIcon("src/Imagens/eco.png");
		setIconImage(icone.getImage());

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArquivo = new JMenu("Cadastros");
		menuBar.add(mnArquivo);

		JMenuItem mntmUsuarios = new JMenuItem("Usuarios");
		mntmUsuarios.setIcon(new ImageIcon(TelaPrincipalFrame.class.getResource("/Imagens/User_16x16.png")));
		mntmUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsuarioFrame u = new UsuarioFrame();
				u.setVisible(true);
			}
		});
		mnArquivo.add(mntmUsuarios);

		JMenuItem mntmBatePapo = new JMenuItem("Bate Papo");
		mntmBatePapo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteFrame clienteFrame = new ClienteFrame();
				JDialog dl = new JDialog();
				dl.getContentPane().add(clienteFrame);

			}
		});
		
		JMenuItem mntmConfigurarBanco = new JMenuItem("Configurar banco");
		mntmConfigurarBanco.setIcon(new ImageIcon(TelaPrincipalFrame.class.getResource("/Imagens/Hard Disk_16x16.png")));
		mnArquivo.add(mntmConfigurarBanco);
		mntmBatePapo.setIcon(new ImageIcon(TelaPrincipalFrame.class.getResource("/Imagens/Send_16x16.png")));
		mnArquivo.add(mntmBatePapo);

		JMenu mnArquivos = new JMenu("Arquivos");
		menuBar.add(mnArquivos);

		JMenuItem mntmNewMenuItem = new JMenuItem("Sobre");
		mntmNewMenuItem.setIcon(new ImageIcon(TelaPrincipalFrame.class.getResource("/Imagens/Search_16x16.png")));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LeiaFrame l = new LeiaFrame();
				l.setVisible(true);
			}
		});
		mnArquivos.add(mntmNewMenuItem);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.setIcon(new ImageIcon(TelaPrincipalFrame.class.getResource("/Imagens/Cancel1.png")));
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null,
						"Deseja realmente sair ?") == JOptionPane.YES_OPTION) {
					
					System.exit(0);
				}
			}
		});

		JMenuItem mntmBancoDeDado = new JMenuItem("Banco de dados");
		mntmBancoDeDado.setIcon(new ImageIcon(TelaPrincipalFrame.class.getResource("/Imagens/Hard Disk_16x16.png")));
		mntmBancoDeDado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Server.openBrowser("http://localhost:8082");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		mnArquivos.add(mntmBancoDeDado);
		mnArquivos.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Panel Inicial = new Panel();
		Inicial.setBackground(new Color(45, 118, 232));
		Inicial.setBounds(0, 0, 1000, 221);
		contentPane.add(Inicial);
		Inicial.setLayout(null);

		JLabel lblChatEcologico = new JLabel("Tiet\u00EA ");
		lblChatEcologico.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 36));
		lblChatEcologico.setIcon(new ImageIcon(TelaPrincipalFrame.class.getResource("/Imagens/ecology.png")));
		lblChatEcologico.setBounds(6, 25, 238, 175);
		Inicial.add(lblChatEcologico);

		JLabel lblBemVindo = new JLabel("Bem Vindo");
		lblBemVindo.setFont(new Font("Berlin Sans FB", Font.BOLD | Font.ITALIC, 36));
		lblBemVindo.setBounds(535, 148, 221, 56);
		Inicial.add(lblBemVindo);

		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null,
						"Deseja realmente sair ?") == JOptionPane.YES_OPTION) {
					
					System.exit(0);
				}
			}

		});
		label_1.setIcon(new ImageIcon(TelaPrincipalFrame.class.getResource("/Imagens/cross.png")));
		label_1.setBounds(939, 6, 40, 38);
		Inicial.add(label_1);
		
		JLabel lblAquiNasceO = new JLabel("Aqui nasce o tiet\u00EA e muito mais !");
		lblAquiNasceO.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 36));
		lblAquiNasceO.setBounds(319, 50, 553, 86);
		Inicial.add(lblAquiNasceO);

		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					URI link = new URI("http://www.salesopolis.sp.gov.br/site/index.php?option=com_content&view=article&id=167&Itemid=1268");
					Desktop.getDesktop().browse(link);
				} catch (Exception ex) {
					System.out.println(ex);
				
				}
			}
		});
		panel.setBackground(UIManager.getColor("scrollbar"));
		panel.setBounds(104, 266, 194, 146);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaPrincipalFrame.class.getResource("/Imagens/house (1).png")));
		lblNewLabel.setBounds(62, 44, 70, 58);
		panel.add(lblNewLabel);

		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblHome.setForeground(new Color(45, 118, 232));
		lblHome.setBounds(72, 114, 55, 16);
		panel.add(lblHome);

		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ( e.getClickCount() == 1) {
				ClienteFrame cf = new ClienteFrame();
				cf.setVisible(true);
				
				}
			}
		});
		panel_1.setBackground(UIManager.getColor("scrollbar"));
		panel_1.setBounds(402, 266, 194, 146);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel label_6 = new JLabel("");
		label_6.setIcon(new ImageIcon(TelaPrincipalFrame.class.getResource("/Imagens/group.png")));
		label_6.setBounds(61, 35, 70, 58);
		panel_1.add(label_6);

		JLabel lblBatePapo = new JLabel("Bate Papo");
		lblBatePapo.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBatePapo.setForeground(new Color(45, 118, 232));
		lblBatePapo.setBounds(59, 105, 82, 16);
		panel_1.add(lblBatePapo);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(UIManager.getColor("scrollbar"));
		panel_2.setBounds(700, 266, 194, 146);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel label_8 = new JLabel("");
		label_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ( e.getClickCount() == 1) {
					try {
						URI link = new URI("http://www.salesopolis.sp.gov.br/site/index.php?option=com_content&view=category&id=104&Itemid=496");
						Desktop.getDesktop().browse(link);
					} catch (Exception ex) {
						System.out.println(ex);
					
					}
			}
			}
		});
		label_8.setIcon(new ImageIcon(TelaPrincipalFrame.class.getResource("/Imagens/settings.png")));
		label_8.setBounds(58, 39, 70, 58);
		panel_2.add(label_8);

		JLabel lblServidor = new JLabel("+ Ecologia ");
		lblServidor.setFont(new Font("Dialog", Font.BOLD, 15));
		lblServidor.setForeground(new Color(45, 118, 232));
		lblServidor.setBounds(54, 109, 85, 31);
		panel_2.add(lblServidor);

		JPanel panel_3 = new JPanel();
//		
//		PerfilDAO uDAO = new PerfilDAO();
//		usuario = uDAO.verificaTipoUsuario(usuario.getCod_perfil().toString(), usuario.getCod_usuario());
//		if (usuario == null) {
//			JOptionPane.showMessageDialog(null, "Usuario é administrador!");
//		} else {
//			JOptionPane.showMessageDialog(null, "Usuario é basico");
//		}
//		
		panel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ( e.getClickCount() == 1) {
				try {
					UsuarioFrame u = new UsuarioFrame();
					u.setVisible(true);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Banco de dados não encontrado");
				}
				
				}
			}
		});
		panel_3.setBackground(UIManager.getColor("scrollbar"));
		panel_3.setBounds(104, 469, 194, 146);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TelaPrincipalFrame.class.getResource("/Imagens/user.png")));
		label.setBounds(62, 44, 70, 58);
		panel_3.add(label);

		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(57, 114, 79, 16);
		lblUsuarios.setForeground(new Color(45, 118, 232));
		lblUsuarios.setFont(new Font("Dialog", Font.BOLD, 15));
		panel_3.add(lblUsuarios);

		JPanel panel_4 = new JPanel();
		panel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					try {
						Server.openBrowser("http://localhost:8082");
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(null, "Banco de dados não encontrado");
					}
				}
			}
		});
		panel_4.setBackground(UIManager.getColor("scrollbar"));
		panel_4.setBounds(402, 469, 194, 146);
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(TelaPrincipalFrame.class.getResource("/Imagens/database.png")));
		label_2.setBounds(62, 30, 70, 72);
		panel_4.add(label_2);

		JLabel lblBancoDeDados = new JLabel("Banco de dados");
		lblBancoDeDados.setBounds(40, 114, 114, 16);
		lblBancoDeDados.setForeground(new Color(45, 118, 232));
		lblBancoDeDados.setFont(new Font("Dialog", Font.BOLD, 15));
		panel_4.add(lblBancoDeDados);

		JPanel panel_5 = new JPanel();
		panel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					LeiaFrame l = new LeiaFrame();
					l.setVisible(true);
				}
			}
		});
		panel_5.setBackground(UIManager.getColor("scrollbar"));
		panel_5.setBounds(700, 469, 194, 146);
		contentPane.add(panel_5);
		panel_5.setLayout(null);

		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(TelaPrincipalFrame.class.getResource("/Imagens/question (1).png")));
		label_4.setBounds(62, 38, 70, 64);
		panel_5.add(label_4);

		JLabel lblSair = new JLabel("Sobre");
		lblSair.setBounds(71, 114, 52, 16);
		lblSair.setForeground(new Color(45, 118, 232));
		lblSair.setFont(new Font("Dialog", Font.BOLD, 15));
		panel_5.add(lblSair);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(834, 655, 132, 16);
		contentPane.add(lblNewLabel_1);
		try {
		lblNewLabel_1.setText("IP : "+InetAddress.getLocalHost().getHostAddress());
		
		JLabel lbFundo = new JLabel("");
		lbFundo.setIcon(new ImageIcon("C:\\Users\\Rafael Pimenta\\Desktop\\Login APS\\Tiete.jpg"));
		lbFundo.setBounds(0, 217, 1000, 477);
		contentPane.add(lbFundo);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
