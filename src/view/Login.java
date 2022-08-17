package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				status();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/moto.png")));
		setResizable(false);
		setTitle("Loja - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 316, 233);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usu\u00E1rio");
		lblNewLabel.setBounds(22, 72, 46, 14);
		contentPane.add(lblNewLabel);

		txtLogin = new JTextField();
		txtLogin.setBounds(73, 69, 137, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(22, 105, 46, 14);
		contentPane.add(lblNewLabel_1);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(73, 102, 137, 20);
		contentPane.add(txtSenha);

		JButton btnLogar = new JButton("Acessar");
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();

			}
		});
		btnLogar.setBounds(121, 135, 89, 23);
		contentPane.add(btnLogar);

		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setBounds(132, 31, 40, 14);
		contentPane.add(lblNewLabel_2);

		lblStatus = new JLabel("New label");
		lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/database_off.png")));
		lblStatus.setBounds(252, 146, 48, 48);
		contentPane.add(lblStatus);

		RestrictedTextField validarLogin = new RestrictedTextField(txtLogin);
		validarLogin.setLimit(15);

		RestrictedTextField validarSenha = new RestrictedTextField(txtSenha);
		validarSenha.setLimit(255);
		
		// Usar o Enter ao inv�s de 'clicar' no bot�o para logar
		getRootPane().setDefaultButton(btnLogar);

	}// fim do construtor

	// Cria��o de um objeto para acessar a camada model
	DAO dao = new DAO();
	private JLabel lblStatus;

	/**
	 * M�todo usado para verificar o status do servidor
	 */

	private void status() {
		try {
			// Abrir a conex�o
			Connection con = dao.conectar();
			if (con == null) {
				// escolher a imagem database_off
				lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/database_off.png")));
			} else {
				lblStatus.setIcon(new ImageIcon(Login.class.getResource("/img/database_on.png")));
			}
			// N�o Esquecer de Fechar a conex�o
			con.close();
		} catch (Exception e) {
			System.out.println(e);

		}

	}

	/**
	 * M�todo usado para autenticar um usu�rio
	 */

	private void logar() {
		// valida��o de senha (captura segura)
		String capturaSenha = new String(txtSenha.getPassword());

		if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o seu Usu�rio");
			txtLogin.requestFocus();
		} else if (txtSenha.getPassword().length == 0) {
			JOptionPane.showMessageDialog(null, "Digite a sua senha");
			txtSenha.requestFocus();
		} else {
			// l�gica principal
			String read = "select * from usuarios where login=? and senha=md5(?)";

			try {
				// Abrir a conex�o
				Connection con = dao.conectar();
				// preparar execu��o da query
				PreparedStatement pst = con.prepareStatement(read);
				// Setar o argumento (id)

				pst.setString(1, txtLogin.getText());
				pst.setString(2, capturaSenha);

				// Executar a query e exibir o resultado no formul�rio
				ResultSet rs = pst.executeQuery();
				// Valida��o (autentica��o do usu�rio)
				// rs.next() -> exist�ncia de login e senha correspondente
				if (rs.next()) {
					// Verificar o perfil do usu�rio
					String perfil = rs.getString(5);
					System.out.println(perfil);
					Principal principal = new Principal();
					if (perfil.equals("admin")) {
						principal.setVisible(true);
						// habilitar recursos
						principal.btnRelatorios.setEnabled(true);
						principal.btnUsuarios.setEnabled(true);
						
						// Personalizar recurso
						principal.panelUsuario.setBackground(Color.red);
						
						// Setar o nome do usuario na tela principal
						principal.lblUsuario.setText("Usu�rio: " + rs.getString(2));
						
					} else {
						principal.setVisible(true);
						principal.lblUsuario.setText("Usu�rio: " + rs.getString(2));
					}
					// encerrar a conex�o
					con.close();
					// fechar a tela de login
					this.dispose();

				} else {
					JOptionPane.showMessageDialog(null, "Login e/ou senha invalido(s)");

					txtLogin.setText(null);

				}

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}
} // fim do c�digo
