package VIEW;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.LineBorder;

import org.h2.tools.Server;

import DAO.UsuarioDAO;
import ENTIDADE.Usuario;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Window.Type;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario;
	private UsuarioDAO uDAO = new UsuarioDAO();
	private JPasswordField tfSenha;
	private JButton btnLogin, btnSair;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setTitle("CHAT ECOLOGICO");
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 560);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 15));
		contentPane.setBackground(new Color(255, 255, 255));

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		ImageIcon icone = new ImageIcon("src/Imagens/Log Out_16x16.png");
		setIconImage(icone.getImage());

		JLabel lblCopyright = new JLabel("Copyright \u00A9 2018 Aps, Inc.");
		lblCopyright.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblCopyright.setBounds(665, 508, 150, 23);
		contentPane.add(lblCopyright);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(45, 118, 232));
		panel_1.setBounds(0, 0, 512, 531);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(LoginFrame.class.getResource("/Imagens/eco.png")));
		label_1.setBounds(0, 6, 512, 541);
		panel_1.add(label_1);

		JLabel lblEntrarOuCadastrar = new JLabel("Entrar ou cadastrar");
		lblEntrarOuCadastrar.setIcon(new ImageIcon(LoginFrame.class.getResource("/Imagens/water.png")));
		lblEntrarOuCadastrar.setFont(new Font("Arial", Font.PLAIN, 17));
		lblEntrarOuCadastrar.setBounds(578, 55, 301, 75);
		contentPane.add(lblEntrarOuCadastrar);

		JLabel lblIcone = new JLabel("");
		lblIcone.setBounds(562, 174, 32, 58);
		contentPane.add(lblIcone);
		lblIcone.setIcon(new ImageIcon(LoginFrame.class.getResource("/Imagens/User_32x32.png")));

		tfUsuario = new JTextField();
		tfUsuario.setBounds(608, 174, 301, 43);
		contentPane.add(tfUsuario);
		tfUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		tfUsuario.setForeground(new Color(211, 211, 211));
		tfUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				tfUsuario.setText("");
				tfUsuario.setForeground(new Color(0, 0, 0));
			}
		});
		tfUsuario.setColumns(10);
		tfUsuario.setText("Nome do Usuario...");

		JLabel label = new JLabel("");
		label.setBounds(562, 278, 32, 52);
		contentPane.add(label);
		label.setIcon(new ImageIcon(LoginFrame.class.getResource("/Imagens/key.png")));

		tfSenha = new JPasswordField();
		tfSenha.setBounds(611, 287, 301, 43);
		contentPane.add(tfSenha);
		tfSenha.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfSenha.setText("");
				tfSenha.setForeground(new Color(0, 0, 0));
			}
		});
		tfSenha.setForeground(new Color(211, 211, 211));
		tfSenha.setText("Entre com sua senha ...");

		btnSair = new JButton("SAIR");
		btnSair.setBounds(582, 422, 129, 52);
		contentPane.add(btnSair);
		btnSair.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnSair.setIcon(new ImageIcon(LoginFrame.class.getResource("/Imagens/exit (1).png")));

		btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(768, 422, 129, 52);
		contentPane.add(btnLogin);
		btnLogin.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnLogin.setIcon(new ImageIcon(LoginFrame.class.getResource("/Imagens/login.png")));
		btnLogin.setEnabled(false);

		final JCheckBox ckRobo = new JCheckBox("N\u00E3o sou Rob\u00F4");
		ckRobo.setBounds(578, 366, 116, 23);
		contentPane.add(ckRobo);
		ckRobo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ckRobo.isSelected()) {
					btnLogin.setEnabled(true);
				} else {
					btnLogin.setEnabled(false);
				}
			}
		});
		ckRobo.setBackground(new Color(255, 255, 255));

		JLabel lblNovoUsuario = new JLabel("Cadastro");
		lblNovoUsuario.setFont(new Font("SansSerif", Font.ITALIC, 14));
		lblNovoUsuario.setBounds(834, 367, 75, 19);
		contentPane.add(lblNovoUsuario);

		JLabel lblNewLabel = new JLabel("E-MAIL");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel.setBounds(562, 143, 55, 16);
		contentPane.add(lblNewLabel);

		JLabel lblSenha = new JLabel("SENHA");
		lblSenha.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblSenha.setBounds(562, 259, 55, 16);
		contentPane.add(lblSenha);
		lblNovoUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				UsuarioPanel cp = new UsuarioPanel();
				JDialog dlg = new JDialog();
				dlg.getContentPane().add(cp);
				dlg.setSize(480, 340);
				dlg.setModal(true);
				dlg.setLocationRelativeTo(null);
				dlg.setVisible(true);

			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = tfUsuario.getText();
				String senha = new String(tfSenha.getPassword());

				Usuario usu = null;
				usu = uDAO.buscarUsuario(usuario, senha);
				if (usu == null) {
					JOptionPane.showMessageDialog(null, "Usuario ou senha ivalidos!");
				} else {
					TelaPrincipalFrame tf = new TelaPrincipalFrame();
					tf.setVisible(true);
					
					contentPane.setVisible(false);

				}
			}
		});
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
