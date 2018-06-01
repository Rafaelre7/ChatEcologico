package VIEW;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DAO.PerfilDAO;
import DAO.SituacaoDAO;
import DAO.UsuarioDAO;
import ENTIDADE.Perfil;
import ENTIDADE.Situacao;
import ENTIDADE.Usuario;
import MODEL.PerfilComboBoxModel;
import MODEL.SituacaoComboBoxModel;
import MODEL.UsuarioTableModel;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

public class UsuarioPanel extends JPanel {
	private JTextField tfCodigo, tfNome, tfEmail, tfEmpresa;
	private JComboBox cbPerfil, cbSituacao;
	private JButton btnSalvar, btnCancelar;
	private PerfilDAO perfilDAO = new PerfilDAO();
	private SituacaoDAO situacaoDAO = new SituacaoDAO();
	private JPasswordField tfSenha;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private Usuario usuarioEdicao;
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioPanel frame = new UsuarioPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the panel.
	 */
	public UsuarioPanel() {
		setBounds(100, 100, 453, 274);
		setLayout(null);

		JLabel lblCodigo = new JLabel("Codigo..:");
		lblCodigo.setBounds(10, 11, 63, 14);
		add(lblCodigo);

		JLabel lblNome = new JLabel("Nome..:");
		lblNome.setBounds(10, 43, 46, 14);
		add(lblNome);

		JLabel lblEmail = new JLabel("E-mail..:");
		lblEmail.setBounds(10, 77, 46, 14);
		add(lblEmail);

		JLabel lblSenha = new JLabel("Senha..:");
		lblSenha.setBounds(10, 112, 46, 14);
		add(lblSenha);

		JLabel lblEmpresa = new JLabel("Empresa..:");
		lblEmpresa.setBounds(10, 153, 63, 14);
		add(lblEmpresa);

		JLabel lblPerfil = new JLabel("Perfil..:");
		lblPerfil.setBounds(10, 186, 46, 14);
		add(lblPerfil);

		JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o..:");
		lblSituao.setBounds(262, 186, 63, 14);
		add(lblSituao);

		tfCodigo = new JTextField();
		tfCodigo.setEditable(false);
		tfCodigo.setBounds(77, 8, 46, 28);
		add(tfCodigo);
		tfCodigo.setColumns(10);

		tfNome = new JTextField();
		tfNome.setColumns(10);
		tfNome.setBounds(77, 40, 363, 28);
		add(tfNome);

		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(77, 74, 363, 28);
		add(tfEmail);

		tfEmpresa = new JTextField();
		tfEmpresa.setColumns(10);
		tfEmpresa.setBounds(77, 146, 363, 28);
		add(tfEmpresa);

		cbPerfil = new JComboBox();
		cbPerfil.setEditable(true);
		cbPerfil.setBounds(77, 179, 114, 28);
		add(cbPerfil);
		cbPerfil.setModel(new PerfilComboBoxModel(perfilDAO.select()));
		
		cbSituacao = new JComboBox();
		cbSituacao.setEditable(true);
		cbSituacao.setBounds(326, 179, 114, 28);
		add(cbSituacao);
		cbSituacao.setModel(new SituacaoComboBoxModel(situacaoDAO.select()));

		btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(UsuarioPanel.class.getResource("/Imagens/Save_24x24.png")));
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

						} else { // Se False
							JOptionPane.showMessageDialog(null, "PROBLEMAS NA ALTERAÇÃO!");
						}
				
						btnSalvar.setEnabled(false);
						btnSalvar.setEnabled(false);
					
					}
				
			}
		});
		btnSalvar.setBounds(75, 219, 114, 42);
		add(btnSalvar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(UsuarioPanel.class.getResource("/Imagens/Cancel_24x24.png")));
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

		
				btnSalvar.setEnabled(false);
				btnCancelar.setEnabled(false);
				
			}
		});
		btnCancelar.setBounds(264, 219, 114, 42);
		add(btnCancelar);

		tfSenha = new JPasswordField();
		tfSenha.setBounds(77, 105, 363, 28);
		add(tfSenha);

	}
}
