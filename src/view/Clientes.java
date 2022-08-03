package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class Clientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Clientes dialog = new Clientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Clientes() {
		setTitle("Clientes");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 604, 475);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel contentPanel_1 = new JPanel();
		contentPanel_1.setLayout(null);
		contentPanel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel_1.setBounds(0, 0, 588, 436);
		contentPanel.add(contentPanel_1);
		
		JLabel lblIdcli = new JLabel("IDCLI");
		lblIdcli.setBounds(10, 137, 46, 14);
		contentPanel_1.add(lblIdcli);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(178, 48, 169, 20);
		contentPanel_1.add(textField);
		
		JLabel lblNewLabel_1 = new JLabel("CPF");
		lblNewLabel_1.setBounds(354, 137, 46, 14);
		contentPanel_1.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(50, 134, 46, 20);
		contentPanel_1.add(textField_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cliente");
		lblNewLabel_2.setBounds(121, 51, 68, 14);
		contentPanel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nome");
		lblNewLabel_3.setBounds(106, 137, 46, 14);
		contentPanel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Data de Nascimento");
		lblNewLabel_4.setBounds(10, 178, 123, 14);
		contentPanel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("UF");
		lblNewLabel_5.setBounds(252, 326, 24, 14);
		contentPanel_1.add(lblNewLabel_5);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(143, 134, 188, 20);
		contentPanel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(397, 134, 115, 20);
		contentPanel_1.add(textField_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		comboBox.setBounds(286, 323, 69, 20);
		contentPanel_1.add(comboBox);
		
		JLabel lblNewLabel_7 = new JLabel("CEP");
		lblNewLabel_7.setBounds(268, 244, 32, 14);
		contentPanel_1.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Endere\u00E7o");
		lblNewLabel_8.setBounds(10, 247, 57, 14);
		contentPanel_1.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Complemento");
		lblNewLabel_9.setBounds(10, 286, 86, 14);
		contentPanel_1.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("N\u00FAmero");
		lblNewLabel_10.setBounds(204, 288, 46, 14);
		contentPanel_1.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Bairro");
		lblNewLabel_11.setBounds(10, 326, 46, 14);
		contentPanel_1.add(lblNewLabel_11);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(302, 241, 86, 20);
		contentPanel_1.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(73, 244, 188, 20);
		contentPanel_1.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(252, 283, 68, 20);
		contentPanel_1.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(55, 323, 184, 20);
		contentPanel_1.add(textField_8);
		
		JLabel lblNewLabel_14 = new JLabel("E-mail");
		lblNewLabel_14.setBounds(10, 213, 46, 14);
		contentPanel_1.add(lblNewLabel_14);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(62, 210, 157, 20);
		contentPanel_1.add(textField_11);
		
		JLabel lblNewLabel_15 = new JLabel("Telefone");
		lblNewLabel_15.setBounds(271, 178, 60, 14);
		contentPanel_1.add(lblNewLabel_15);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(328, 175, 139, 20);
		contentPanel_1.add(textField_12);
		
		JLabel lblNewLabel_16 = new JLabel("Marketing");
		lblNewLabel_16.setBounds(256, 213, 58, 14);
		contentPanel_1.add(lblNewLabel_16);
		
		JButton btnNewButton = new JButton("Buscar CEP");
		btnNewButton.setBounds(398, 240, 114, 23);
		contentPanel_1.add(btnNewButton);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(94, 283, 100, 20);
		contentPanel_1.add(textField_15);
		
		JLabel lblNewLabel_11_1 = new JLabel("Cidade");
		lblNewLabel_11_1.setBounds(328, 286, 46, 14);
		contentPanel_1.add(lblNewLabel_11_1);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(384, 283, 131, 20);
		contentPanel_1.add(textField_16);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setBounds(597, 388, 32, 32);
		contentPanel_1.add(btnNewButton_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(133, 175, 117, 20);
		contentPanel_1.add(textField_4);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "SIM", "N\u00C3O"}));
		comboBox_1.setBounds(328, 208, 46, 22);
		contentPanel_1.add(comboBox_1);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setIcon(new ImageIcon(Clientes.class.getResource("/img/pesquisa.png")));
		btnNewButton_1.setBounds(357, 48, 32, 32);
		contentPanel_1.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setIcon(new ImageIcon(Clientes.class.getResource("/img/adduser.png")));
		btnNewButton_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1_1.setContentAreaFilled(false);
		btnNewButton_1_1.setBorderPainted(false);
		btnNewButton_1_1.setBounds(433, 375, 32, 32);
		contentPanel_1.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("");
		btnNewButton_1_2.setIcon(new ImageIcon(Clientes.class.getResource("/img/trocauser.png")));
		btnNewButton_1_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1_2.setContentAreaFilled(false);
		btnNewButton_1_2.setBorderPainted(false);
		btnNewButton_1_2.setBounds(475, 375, 32, 32);
		contentPanel_1.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_3 = new JButton("");
		btnNewButton_1_3.setIcon(new ImageIcon(Clientes.class.getResource("/img/delete.png")));
		btnNewButton_1_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1_3.setContentAreaFilled(false);
		btnNewButton_1_3.setBorderPainted(false);
		btnNewButton_1_3.setBounds(517, 375, 32, 32);
		contentPanel_1.add(btnNewButton_1_3);
	}
}
