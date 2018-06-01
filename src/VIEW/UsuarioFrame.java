package VIEW;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import DAO.PerfilDAO;
import DAO.SituacaoDAO;
import DAO.UsuarioDAO;
import ENTIDADE.Perfil;
import ENTIDADE.Situacao;
import ENTIDADE.Usuario;
import MODEL.PerfilComboBoxModel;
import MODEL.SituacaoComboBoxModel;
import MODEL.UsuarioTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class UsuarioFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tfCodigo,tfNome,tfEmail,tfEmpresa;
	private JTable table;
	private JComboBox cbPerfil,cbSituacao;
	private JButton btnNovo,btnExcluir,btnSalvar,btnCancelar;
	private Usuario usuarioEdicao;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private PerfilDAO perfilDAO = new PerfilDAO();
	private SituacaoDAO situacaoDAO = new SituacaoDAO();
	private JPasswordField tfSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioFrame frame = new UsuarioFrame();
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
	public UsuarioFrame() {
		setTitle("Cadastro de Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 485, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		//Adiciona icone ao frame
		ImageIcon icone = new ImageIcon("src/Imagens/User_16x16.png");
		setIconImage(icone.getImage());
		setResizable(false);

		JLabel lblCodigo = new JLabel("Codigo..:");
		lblCodigo.setBounds(10, 17, 57, 14);
		contentPane.add(lblCodigo);

		JLabel lblNome = new JLabel("Nome..:");
		lblNome.setBounds(10, 48, 57, 14);
		contentPane.add(lblNome);

		JLabel lblEmail = new JLabel("E-mail..:");
		lblEmail.setBounds(10, 79, 57, 14);
		contentPane.add(lblEmail);

		JLabel lblEmpresa = new JLabel("Empresa..:");
		lblEmpresa.setBounds(10, 141, 73, 14);
		contentPane.add(lblEmpresa);

		JLabel lblSenha = new JLabel("Senha..:");
		lblSenha.setBounds(10, 110, 57, 14);
		contentPane.add(lblSenha);

		tfCodigo = new JTextField();
		tfCodigo.setEnabled(false);
		tfCodigo.setEditable(false);
		tfCodigo.setBounds(77, 12, 50, 23);
		contentPane.add(tfCodigo);
		tfCodigo.setColumns(10);

		tfNome = new JTextField();
		tfNome.setEnabled(false);
		tfNome.setColumns(10);
		tfNome.setBounds(77, 43, 367, 23);
		contentPane.add(tfNome);

		tfEmail = new JTextField();
		tfEmail.setEnabled(false);
		tfEmail.setColumns(10);
		tfEmail.setBounds(77, 76, 367, 23);
		contentPane.add(tfEmail);

		tfEmpresa = new JTextField();
		tfEmpresa.setEnabled(false);
		tfEmpresa.setColumns(10);
		tfEmpresa.setBounds(77, 136, 367, 23);
		contentPane.add(tfEmpresa);

		cbPerfil = new JComboBox();
		cbPerfil.setEnabled(false);
		cbPerfil.setBounds(67, 185, 116, 23);
		contentPane.add(cbPerfil);
		cbPerfil.setModel(new PerfilComboBoxModel(perfilDAO.select()));

		JLabel lblSituao = new JLabel("Perfil..:");
		lblSituao.setBounds(11, 192, 56, 14);
		contentPane.add(lblSituao);

		JLabel label = new JLabel("Situa\u00E7\u00E3o..:");
		label.setBounds(243, 192, 65, 23);
		contentPane.add(label);

		cbSituacao = new JComboBox();
		cbSituacao.setEnabled(false);
		cbSituacao.setBounds(318, 185, 116, 23);
		contentPane.add(cbSituacao);
		cbSituacao.setModel(new SituacaoComboBoxModel(situacaoDAO.select()));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 232, 442, 176);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					//Cast
					UsuarioTableModel modelo = (UsuarioTableModel) table.getModel();


					usuarioEdicao = modelo.getUsuarioPorLinha(table.getSelectedRow());


					tfCodigo.setText(String.valueOf(usuarioEdicao.getCod_usuario()));
					tfNome.setText(usuarioEdicao.getNome());
					tfSenha.setText(usuarioEdicao.getSenha());
					tfEmail.setText(usuarioEdicao.getEmail());
					tfEmpresa.setText(usuarioEdicao.getEmpresa());
					cbPerfil.setSelectedItem(usuarioEdicao.getPerfil());
					cbPerfil.repaint();
					cbSituacao.setSelectedItem(usuarioEdicao.getSituacao());
					cbSituacao.repaint();
					

					btnNovo.setEnabled(false);
					btnSalvar.setEnabled(true);
					btnCancelar.setEnabled(true);
					btnExcluir.setEnabled(true);
					
					tfNome.setEnabled(true);
					tfEmail.setEnabled(true);
					tfSenha.setEnabled(true);
					tfEmpresa.setEnabled(true);
					cbPerfil.setEnabled(true);
					cbSituacao.setEnabled(true);
					
				}
			}
		});
		scrollPane.setViewportView(table);
		table.setModel(new UsuarioTableModel(usuarioDAO.select()));
		btnNovo = new JButton("Novo");
		btnNovo.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnNovo.setIcon(new ImageIcon(UsuarioFrame.class.getResource("/Imagens/Add1.png")));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfCodigo.setText("");
				tfNome.setText("");
				tfEmail.setText("");
				tfSenha.setText("");
				tfNome.requestFocus();
				cbPerfil.setSelectedItem(null);
				cbPerfil.repaint();
				cbSituacao.setSelectedItem(null);
				cbSituacao.repaint();

				btnNovo.setEnabled(false);
				btnSalvar.setEnabled(true);
				btnCancelar.setEnabled(true);
				btnExcluir.setEnabled(false);

				tfNome.setEnabled(true);
				tfEmail.setEnabled(true);
				tfSenha.setEnabled(true);
				tfEmpresa.setEnabled(true);
				cbPerfil.setEnabled(true);
				cbSituacao.setEnabled(true);
			}
		});
		btnNovo.setBounds(14, 445, 99, 28);
		contentPane.add(btnNovo);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnSalvar.setIcon(new ImageIcon(UsuarioFrame.class.getResource("/Imagens/Save1.png")));
		btnSalvar.setEnabled(false);
		btnSalvar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (tfNome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe o Usuario!");
					tfNome.requestFocus();
					return;
				}
				if (tfEmail.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe o email!");
					tfEmail.requestFocus();
					return;
				}

				if (tfSenha.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Informe a senha!");
					tfSenha.requestFocus();
					return;

				} // Novo registro
				if (usuarioEdicao == null) {
					usuarioEdicao = new Usuario();
					usuarioEdicao.setNome(tfNome.getText());
					usuarioEdicao.setEmail(tfEmail.getText());
					usuarioEdicao.setSenha(tfSenha.getText());
					usuarioEdicao.setEmpresa(tfEmpresa.getText());
					usuarioEdicao.setPerfil((Perfil) cbPerfil.getSelectedItem());
					cbPerfil.repaint();
					usuarioEdicao.setSituacao((Situacao) cbSituacao.getSelectedItem());
					cbSituacao.repaint();
					
					if (usuarioDAO.insert(usuarioEdicao)) { // Se True
						JOptionPane.showMessageDialog(null, "USUARIO INSERIDO COM SUCESSO!");
						
						usuarioEdicao = null;
						table.setModel(new UsuarioTableModel(usuarioDAO.select()));
						tfCodigo.setText("");
						tfNome.setText("");
						tfEmail.setText("");
						tfSenha.setText("");
						tfNome.requestFocus();
						tfEmpresa.setText("");
						cbPerfil.setSelectedItem(null);
						cbPerfil.repaint();
						cbSituacao.setSelectedItem(null);
						cbSituacao.repaint();
						UsuarioFrame.this.dispose();
					} else { // Se False
						JOptionPane.showMessageDialog(null, "PROBLEMAS NA INSERÇÃO!");

					}
				} else {// Altera Registro
					// usuarioEdicao.setCodigo(Integer.parseInt(tfCodigo.getText()));
					usuarioEdicao.setNome(tfNome.getText());
					usuarioEdicao.setEmail(tfEmail.getText());
					usuarioEdicao.setSenha(tfSenha.getText());
					usuarioEdicao.setEmpresa(tfEmpresa.getText());
					usuarioEdicao.setPerfil((Perfil) cbPerfil.getSelectedItem());
					cbPerfil.repaint();
					usuarioEdicao.setSituacao((Situacao) cbSituacao.getSelectedItem());
					cbSituacao.repaint();

					if (usuarioDAO.update(usuarioEdicao)) { // Se True
						JOptionPane.showMessageDialog(null, "ALTERADO COM SUCESSO");
						usuarioEdicao = null;
						tfCodigo.setText("");
						tfNome.setText("");
						tfEmail.setText("");
						tfSenha.setText("");
						tfNome.requestFocus();
						tfEmpresa.setText("");
						cbPerfil.setSelectedItem(null);
						cbPerfil.repaint();
						cbSituacao.setSelectedItem(null);
						cbSituacao.repaint();
						table.setModel(new UsuarioTableModel(usuarioDAO.select()));

					} else { // Se False
						JOptionPane.showMessageDialog(null, "PROBLEMAS NA ALTERAÇÃO!");
					}
					btnNovo.setEnabled(true);
					btnSalvar.setEnabled(false);
					btnSalvar.setEnabled(false);
					btnExcluir.setEnabled(false);
				}
			}
		});
		btnSalvar.setBounds(127, 445, 99, 28);
		contentPane.add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnCancelar.setIcon(new ImageIcon(UsuarioFrame.class.getResource("/Imagens/Cancel1.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfCodigo.setText("");
				tfNome.setText("");
				tfEmail.setText("");
				tfSenha.setText("");
				tfNome.requestFocus();
				tfEmpresa.setText("");
				cbPerfil.setSelectedItem(null);
				cbPerfil.repaint();
				cbSituacao.setSelectedItem(null);
				cbSituacao.repaint();

				btnNovo.setEnabled(true);
				btnSalvar.setEnabled(false);
				btnCancelar.setEnabled(false);
				btnExcluir.setEnabled(false);
			}
		});
		btnCancelar.setBounds(240, 445, 108, 28);
		contentPane.add(btnCancelar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnExcluir.setIcon(new ImageIcon(UsuarioFrame.class.getResource("/Imagens/Delete1.png")));
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null,
						"Deseja realmente excluir este usuario?") == JOptionPane.YES_OPTION) {

					if (usuarioDAO.delete(usuarioEdicao.getCod_usuario())) {
						JOptionPane.showMessageDialog(null, "USUARIO EXCLUÍDO COM SUCESSO");
						usuarioEdicao = null;
						tfCodigo.setText("");
						tfNome.setText("");
						tfEmail.setText("");
						tfSenha.setText("");
						tfNome.requestFocus();
						tfEmpresa.setText("");
						cbPerfil.setSelectedItem(null);
						cbPerfil.repaint();
						cbSituacao.setSelectedItem(null);
						cbSituacao.repaint();
						table.setModel(new UsuarioTableModel(usuarioDAO.select()));
					} else {
						JOptionPane.showMessageDialog(null, "PROBLEMAS NA EXCLUSÃO");
					}
				}
				btnNovo.setEnabled(true);
				btnSalvar.setEnabled(false);
				btnCancelar.setEnabled(false);
				btnExcluir.setEnabled(false);
			}
		});
		btnExcluir.setBounds(362, 445, 99, 28);
		contentPane.add(btnExcluir);
		
		tfSenha = new JPasswordField();
		tfSenha.setEnabled(false);
		tfSenha.setBounds(77, 106, 367, 23);
		contentPane.add(tfSenha);
	}
}
