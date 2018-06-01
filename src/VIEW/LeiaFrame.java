package VIEW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.Color;

public class LeiaFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeiaFrame frame = new LeiaFrame();
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
	public LeiaFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Bslsn\u00E7s\\Desktop\\Programa\u00E7\u00E3o\\Novo Projeto\\icones\\master.jpg"));
		setTitle("Sobre RJ.Software");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 444, 229);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Vers\u00E3o : 1.0  Para Windows");
		lblNewLabel.setBounds(200, 27, 193, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblNewLabel);
		
		JLabel lblCopyrightcombr = new JLabel("\u00A92018  Copyright ");
		lblCopyrightcombr.setBounds(167, 169, 145, 25);
		lblCopyrightcombr.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(lblCopyrightcombr);
		
		JLabel label = new JLabel("");
		label.setBounds(15, 11, 88, 77);
		label.setIcon(new ImageIcon("C:\\Users\\Bslsn\u00E7s\\Desktop\\Programa\u00E7\u00E3o\\Novo Projeto\\icones\\logomastermais3.jpg"));
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(LeiaFrame.class.getResource("/Imagens/ecology.png")));
		label_1.setBounds(15, 27, 151, 139);
		contentPane.add(label_1);
		
		JLabel lblDesenvolvedores = new JLabel("Desenvolvedores");
		lblDesenvolvedores.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblDesenvolvedores.setBounds(220, 52, 139, 20);
		contentPane.add(lblDesenvolvedores);
		
		JLabel lblRafaelEduardoDias = new JLabel("Rafael Eduardo Dias Pimenta");
		lblRafaelEduardoDias.setBounds(200, 84, 215, 25);
		contentPane.add(lblRafaelEduardoDias);
		
		JLabel lblJoeberFerreiraFachinetti = new JLabel("Joeber Ferreira Fachinetti");
		lblJoeberFerreiraFachinetti.setBounds(200, 117, 215, 25);
		contentPane.add(lblJoeberFerreiraFachinetti);
	}
}
