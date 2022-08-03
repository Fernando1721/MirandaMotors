package view;


import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		addWindowListener(new WindowAdapter() {
			// evento ativar janela
			@Override
			public void windowActivated(WindowEvent e) {
				Date data = new Date();
				DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
				lblData.setText(formatador.format(data));
			}
		});
		setTitle("Miranda Motors");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/logomoto.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 440);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(Principal.class.getResource("/img/produto.png")));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setToolTipText("Produtos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produtos produto = new Produtos();
				produto.setVisible(true);
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 307, 64, 64);
		contentPane.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/img/moto.png")));
		btnNewButton.setBounds(369, 11, 128, 128);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fornecedores fornecedor = new Fornecedores();
				fornecedor.setVisible(true);
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Principal.class.getResource("/img/fornecedor2.png")));
		btnNewButton_1.setToolTipText("Fonecedores");
		btnNewButton_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_1.setBounds(202, 11, 128, 128);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			// evento clicar no botão
			public void actionPerformed(ActionEvent e) {
				// link para o JDialog
				Usuarios usuarios = new Usuarios();
				usuarios.setVisible(true);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(Principal.class.getResource("/img/usuario.png")));
		btnNewButton_2.setToolTipText("Usu\u00E1rios");
		btnNewButton_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_2.setBounds(40, 11, 128, 128);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_4.setToolTipText("Clientes");
		btnNewButton_4.setIcon(new ImageIcon(Principal.class.getResource("/img/cliente.png")));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clientes clientes = new Clientes();
				clientes.setVisible(true);
				
			}
		});
		btnNewButton_4.setBounds(537, 11, 128, 128);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_4_1 = new JButton("");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pdv pdv = new Pdv();
				pdv.setVisible(true);
			}
		});
		btnNewButton_4_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_4_1.setToolTipText("PDV");
		btnNewButton_4_1.setIcon(new ImageIcon(Principal.class.getResource("/img/PDV.png")));
		btnNewButton_4_1.setBounds(40, 160, 128, 128);
		contentPane.add(btnNewButton_4_1);
		
		JButton btnNewButton_4_2 = new JButton("");
		btnNewButton_4_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorio relatorio = new Relatorio();
				relatorio.setVisible(true);
			}
		});
		btnNewButton_4_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_4_2.setToolTipText("Relat\u00F3rio");
		btnNewButton_4_2.setIcon(new ImageIcon(Principal.class.getResource("/img/relatorio.png")));
		btnNewButton_4_2.setBounds(202, 160, 128, 128);
		contentPane.add(btnNewButton_4_2);
		
		JButton btnNewButton_4_3 = new JButton("");
		btnNewButton_4_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ferramenta ferramenta = new Ferramenta();
				ferramenta.setVisible(true);
			}
		});
		btnNewButton_4_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_4_3.setToolTipText("Ferramenta");
		btnNewButton_4_3.setIcon(new ImageIcon(Principal.class.getResource("/img/ferramenta.png")));
		btnNewButton_4_3.setBounds(369, 160, 128, 128);
		contentPane.add(btnNewButton_4_3);
		
		JButton btnNewButton_4_3_1 = new JButton("");
		btnNewButton_4_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Ajuda ajuda = new Ajuda();
				ajuda.setVisible(true);
			}
		});
		btnNewButton_4_3_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton_4_3_1.setToolTipText("Ajuda");
		btnNewButton_4_3_1.setIcon(new ImageIcon(Principal.class.getResource("/img/ajuda.png")));
		btnNewButton_4_3_1.setBounds(537, 160, 128, 128);
		contentPane.add(btnNewButton_4_3_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaptionBorder);
		panel.setBounds(0, 348, 701, 53);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblData = new JLabel("");
		lblData.setBounds(420, 11, 281, 30);
		panel.add(lblData);
		lblData.setForeground(SystemColor.desktop);
		lblData.setFont(new Font("Tahoma", Font.BOLD, 14));
	}// fim do construtor
}
