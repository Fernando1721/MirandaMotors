package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.Cursor;

public class Fornecedores extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Fornecedores dialog = new Fornecedores();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Fornecedores() {
		setResizable(false);
		setModal(true);
		setTitle("Fornecedores");
		setBounds(100, 100, 655, 483);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 639, 444);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IDFOR");
		lblNewLabel.setBounds(10, 137, 46, 14);
		contentPanel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(103, 37, 169, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CNPJ");
		lblNewLabel_1.setBounds(128, 137, 46, 14);
		contentPanel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(50, 134, 68, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Fornecedor");
		lblNewLabel_2.setBounds(25, 40, 68, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("IE");
		lblNewLabel_3.setBounds(290, 137, 24, 14);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("IM");
		lblNewLabel_4.setBounds(437, 137, 30, 14);
		contentPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("UF");
		lblNewLabel_5.setBounds(490, 313, 24, 14);
		contentPanel.add(lblNewLabel_5);
		
		textField_2 = new JTextField();
		textField_2.setBounds(169, 134, 111, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(310, 134, 117, 20);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(466, 134, 127, 20);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/pesquisa.png")));
		lblNewLabel_6.setBounds(282, 35, 32, 32);
		contentPanel.add(lblNewLabel_6);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		comboBox.setBounds(524, 310, 69, 20);
		contentPanel.add(comboBox);
		
		JLabel lblNewLabel_7 = new JLabel("CEP");
		lblNewLabel_7.setBounds(268, 244, 32, 14);
		contentPanel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Endere\u00E7o");
		lblNewLabel_8.setBounds(10, 276, 57, 14);
		contentPanel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Complemento");
		lblNewLabel_9.setBounds(442, 276, 86, 14);
		contentPanel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("N\u00FAmero");
		lblNewLabel_10.setBounds(310, 276, 46, 14);
		contentPanel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Bairro");
		lblNewLabel_11.setBounds(10, 313, 46, 14);
		contentPanel.add(lblNewLabel_11);
		
		textField_5 = new JTextField();
		textField_5.setBounds(302, 241, 86, 20);
		contentPanel.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(70, 273, 226, 20);
		contentPanel.add(textField_6);
		textField_6.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(368, 273, 68, 20);
		contentPanel.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(66, 310, 184, 20);
		contentPanel.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Raz\u00E3o Social");
		lblNewLabel_12.setBounds(10, 168, 83, 14);
		contentPanel.add(lblNewLabel_12);
		
		textField_10 = new JTextField();
		textField_10.setBounds(88, 168, 181, 20);
		contentPanel.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Nome de Fantasia");
		lblNewLabel_13.setBounds(278, 173, 110, 14);
		contentPanel.add(lblNewLabel_13);
		
		textField_11 = new JTextField();
		textField_11.setBounds(390, 170, 203, 20);
		contentPanel.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("Site");
		lblNewLabel_14.setBounds(10, 206, 46, 14);
		contentPanel.add(lblNewLabel_14);
		
		textField_12 = new JTextField();
		textField_12.setBounds(44, 203, 157, 20);
		contentPanel.add(textField_12);
		textField_12.setColumns(10);
		
		JLabel lblNewLabel_15 = new JLabel("Fone");
		lblNewLabel_15.setBounds(211, 206, 46, 14);
		contentPanel.add(lblNewLabel_15);
		
		textField_13 = new JTextField();
		textField_13.setBounds(245, 203, 139, 20);
		contentPanel.add(textField_13);
		textField_13.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("Contato");
		lblNewLabel_16.setBounds(390, 206, 46, 14);
		contentPanel.add(lblNewLabel_16);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(442, 203, 151, 20);
		contentPanel.add(textField_14);
		
		JLabel lblNewLabel_17 = new JLabel("E-mail");
		lblNewLabel_17.setBounds(10, 244, 46, 14);
		contentPanel.add(lblNewLabel_17);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(50, 241, 211, 20);
		contentPanel.add(textField_15);
		
		JButton btnNewButton = new JButton("Buscar CEP");
		btnNewButton.setBounds(398, 240, 114, 23);
		contentPanel.add(btnNewButton);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(529, 273, 100, 20);
		contentPanel.add(textField_16);
		
		JLabel lblNewLabel_11_1 = new JLabel("Cidade");
		lblNewLabel_11_1.setBounds(268, 313, 46, 14);
		contentPanel.add(lblNewLabel_11_1);
		
		textField_17 = new JTextField();
		textField_17.setColumns(10);
		textField_17.setBounds(310, 310, 157, 20);
		contentPanel.add(textField_17);
		
		JLabel lblNewLabel_9_1 = new JLabel("Observa\u00E7\u00E3o");
		lblNewLabel_9_1.setBounds(10, 377, 86, 14);
		contentPanel.add(lblNewLabel_9_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(88, 357, 226, 64);
		contentPanel.add(textArea);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/adduser.png")));
		btnNewButton_1.setBounds(513, 388, 32, 32);
		contentPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/trocauser.png")));
		btnNewButton_2.setBounds(555, 388, 32, 32);
		contentPanel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/delete.png")));
		btnNewButton_3.setBounds(597, 388, 32, 32);
		contentPanel.add(btnNewButton_3);
	}// fim do construtor
}
