package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;

public class Ajuda extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Ajuda dialog = new Ajuda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Ajuda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ajuda.class.getResource("/img/ajuda.png")));
		setTitle("Ajuda");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("@Author Fernando Miranda");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(23, 27, 176, 21);
		contentPanel.add(lblNewLabel);
		
		JTextPane txtpnMitALicena = new JTextPane();
		txtpnMitALicena.setEditable(false);
		txtpnMitALicena.setText("MIT. A licen\u00E7a MIT \u00E9 semelhante a BSD. Ela elimina qualquer tipo de restri\u00E7\u00E3o ao uso, modifica\u00E7\u00E3o e distribui\u00E7\u00E3o do c\u00F3digo fonte de um programa. No entanto, ela tamb\u00E9m d\u00E1 a liberdade para que o developer possa utilizar o c\u00F3digo em um projeto propriet\u00E1rio.");
		txtpnMitALicena.setBounds(20, 124, 289, 108);
		contentPanel.add(txtpnMitALicena);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Ajuda.class.getResource("/img/mit.png")));
		lblNewLabel_1.setBounds(20, 58, 64, 64);
		contentPanel.add(lblNewLabel_1);
	}
}
