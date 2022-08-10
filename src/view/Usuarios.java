package view;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;

public class Usuarios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuId;
	private JTextField txtUsuNome;
	private JTextField txtUsuLogin;
	private JPasswordField txtUsuSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Usuarios dialog = new Usuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Usuarios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/img/usuario.png")));
		setModal(true);
		setResizable(false);
		setTitle("Usu\u00E1rios");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 261);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(23, 28, 16, 14);
		contentPanel.add(lblNewLabel);

		txtUsuId = new JTextField();
		txtUsuId.setEditable(false);
		txtUsuId.setBounds(65, 25, 86, 20);
		contentPanel.add(txtUsuId);
		txtUsuId.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Usu\u00E1rio");
		lblNewLabel_1.setBounds(19, 67, 58, 14);
		contentPanel.add(lblNewLabel_1);

		txtUsuNome = new JTextField();
		txtUsuNome.setEditable(false);
		txtUsuNome.setBounds(65, 64, 216, 20);
		contentPanel.add(txtUsuNome);
		txtUsuNome.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setBounds(19, 103, 32, 14);
		contentPanel.add(lblNewLabel_2);

		txtUsuLogin = new JTextField();
		txtUsuLogin.setBounds(65, 100, 105, 20);
		contentPanel.add(txtUsuLogin);
		txtUsuLogin.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Senha");
		lblNewLabel_3.setBounds(19, 139, 46, 14);
		contentPanel.add(lblNewLabel_3);

		txtUsuSenha = new JPasswordField();
		txtUsuSenha.setEditable(false);
		txtUsuSenha.setBounds(65, 136, 216, 20);
		contentPanel.add(txtUsuSenha);

		JLabel lblNewLabel_4 = new JLabel("Perfil");
		lblNewLabel_4.setBounds(246, 103, 46, 14);
		contentPanel.add(lblNewLabel_4);

		cboUsuPerfil = new JComboBox();
		cboUsuPerfil.setModel(new DefaultComboBoxModel(new String[] { "", "admin", "user" }));
		cboUsuPerfil.setBounds(282, 99, 68, 22);
		contentPanel.add(cboUsuPerfil);

		JButton btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarUsuario();
			}
		});
		btnPesquisar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/pesquisa.png")));
		btnPesquisar.setToolTipText("Pesquisar ");
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setContentAreaFilled(false);
		btnPesquisar.setBorderPainted(false);
		btnPesquisar.setAutoscrolls(true);
		btnPesquisar.setBounds(180, 93, 32, 32);
		contentPanel.add(btnPesquisar);

		btnAdicionar = new JButton("");
		btnAdicionar.setEnabled(false);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarUsuario();
			}
		});
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/adduser.png")));
		btnAdicionar.setBounds(119, 191, 32, 32);
		contentPanel.add(btnAdicionar);

		btnAlterar = new JButton("");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarUsuario();
			}
		});
		btnAlterar.setBorderPainted(false);
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/trocauser.png")));
		btnAlterar.setToolTipText("Editar Usu\u00E1rio");
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setBounds(161, 191, 32, 32);
		contentPanel.add(btnAlterar);

		btnExcluir = new JButton("");
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/img/delete.png")));
		btnExcluir.setToolTipText("Deletar Usu\u00E1rio");
		btnExcluir.setBorderPainted(false);
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setBounds(203, 191, 32, 32);
		contentPanel.add(btnExcluir);

//-----------------------------------------------------------------------------------------------------//

		// Validação com o uso da biblioteca (framework) Atxy2K
		// txtUsuId para cada caixa de texto criar um objeto

		RestrictedTextField validarid = new RestrictedTextField(txtUsuId);
		validarid.setOnlyNums(true);
		validarid.setLimit(4);
		// txtUsoNome
		RestrictedTextField validarNome = new RestrictedTextField(txtUsuNome);
		validarNome.setLimit(40);
		// txtUsoLogin
		RestrictedTextField validarLogin = new RestrictedTextField(txtUsuLogin);
		validarLogin.setLimit(40);
		// txtUsuSenha
		RestrictedTextField validarSenha = new RestrictedTextField(txtUsuSenha);
		validarSenha.setLimit(255);

	}// fim do construtor

	DAO dao = new DAO();
	private JComboBox cboUsuPerfil;
	private JButton btnAdicionar;
	private JButton btnAlterar;
	private JButton btnExcluir;

	/**
	 * Método responsável pela pesquisa de usuários
	 */
	private void pesquisarUsuario() {
		// validação
		if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o login do usuário");
			txtUsuLogin.requestFocus();
		} else {
			// lógica principal
			// Query (Instrução SQL)
			String read = "select * from usuarios where login = ?";
			// tratar exceções sempre que lidar com o banco
			try {
				// Estabelecer a conexão com o banco

				Connection con = dao.conectar();
				// Preparar a execução da query

				PreparedStatement pst = con.prepareStatement(read);
				// Setar o argumento (id)

				pst.setString(1, txtUsuLogin.getText());
				// Executar a query e exibir o resultado no formulário

				ResultSet rs = pst.executeQuery();
				// Validação (Existência de usuário)

				if (rs.next()) {
					// Preencher(setar) os campos do formulário
					// rs.next -> Existência de Usuário
					txtUsuId.setText(rs.getString(1));
					txtUsuNome.setText(rs.getString(2));
					txtUsuLogin.setText(rs.getString(3));
					cboUsuPerfil.setSelectedItem(rs.getString(5));
					txtUsuSenha.setText(rs.getString(4));
					txtUsuNome.setEditable(true);
					cboUsuPerfil.setEnabled(true);
					txtUsuSenha.setEditable(true);
					btnAlterar.setEnabled(true);
					btnExcluir.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Usuário Inexistente");
					txtUsuLogin.setEditable(false);
					txtUsuNome.setEditable(true);
					cboUsuPerfil.setEnabled(true);
					txtUsuSenha.setEditable(true);
					txtUsuNome.requestFocus();
					btnAdicionar.setEnabled(true);
					
					
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	/**
	 * Método responsável por adicionar um novo usuário no banco
	 */
	private void adicionarUsuario() {
		// validação
		// validação da Senha (captura segura)
		String capturarSenha = new String(txtUsuSenha.getPassword());

		if (txtUsuNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Usuário");
			txtUsuNome.requestFocus();
		} else if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login");
			txtUsuLogin.requestFocus();
		} else if (cboUsuPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o perfil do usuário");
			cboUsuPerfil.requestFocus();
		} else if (txtUsuSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a senha");
			txtUsuSenha.requestFocus();
		} else {
			// Lógica Principal
			String create = "insert into usuarios(usuario,login,senha,perfil)values(?,?,md5(?),?)";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir os ???? pelo conteúdo das caixas de texto
				pst.setString(1, txtUsuNome.getText());
				pst.setString(2, txtUsuLogin.getText());
				pst.setString(3, capturarSenha);
				pst.setString(4, cboUsuPerfil.getSelectedItem().toString());
				// Executar a query e inserir o usuário no banco
				pst.executeUpdate();
				// Encerrar a conexão
				JOptionPane.showMessageDialog(null, "Usuário Cadastrado com Sucesso!");
				limparCampos();
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login em uso.\nEscolha outro login.");
				txtUsuLogin.setText(null);
				txtUsuLogin.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

	/**
	 * Método responsável por alterar o usuário no banco
	 */
	private void alterarUsuario() {
		// validação
		// validação da Senha (captura segura)
		String capturarSenha = new String(txtUsuSenha.getPassword());

		if (txtUsuNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Usuário");
			txtUsuNome.requestFocus();
		} else if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Login");
			txtUsuLogin.requestFocus();
		} else if (cboUsuPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione o perfil do usuário");
			cboUsuPerfil.requestFocus();
		} else if (txtUsuSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a senha");
			txtUsuSenha.requestFocus();
		} else {
			// Lógica Principal
			String update = "update usuarios set usuario=?, login=?,senha=md5(?),perfil=? where idusu=?";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(update);
				// Substituir os ???? pelo conteúdo das caixas de texto
				pst.setString(1, txtUsuNome.getText());
				pst.setString(2, txtUsuLogin.getText());
				pst.setString(3, capturarSenha);
				pst.setString(4, cboUsuPerfil.getSelectedItem().toString());
				pst.setString(5, txtUsuId.getText());
				// Executar a query e inserir o usuário no banco
				pst.executeUpdate();
				// Encerrar a conexão
				JOptionPane.showMessageDialog(null, "Usuário alterado com Sucesso!");
				limparCampos();
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Login em uso.\nEscolha outro login.");
				txtUsuLogin.setText(null);
				txtUsuLogin.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}
	/**
	 * Método responsável por deletar um usuário no banco
	 */
	private void excluirUsuario() {
		// validação (confirmação de exclusão)
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exlusão do usuario?","Atenção!", JOptionPane.YES_NO_OPTION);
		if(confirma == JOptionPane.YES_OPTION) {
			// Lógica Principal 
			String delete = "delete from usuarios where idusu=?";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a query(comando SQL) substituindo a ? pelo idusu
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtUsuId.getText());
				// Executar a query
				pst.executeUpdate();
				// confirmação
				limparCampos();
				JOptionPane.showMessageDialog(null, "Usuário excluido com Sucesso!");
				// encerrar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	/**
	 * Método usado para limpar e validar os campos e objetos do formulário
	 */

	private void limparCampos() {
		txtUsuId.setText(null);
		txtUsuNome.setText(null);
		txtUsuLogin.setText(null);
		txtUsuSenha.setText(null);
		cboUsuPerfil.setSelectedItem("");
		btnAdicionar.setEnabled(false);
		btnAlterar.setEnabled(false);
		btnExcluir.setEnabled(false);
		txtUsuNome.setEditable(false);
		txtUsuLogin.setEditable(true);
		cboUsuPerfil.setEnabled(false);
		txtUsuSenha.setEditable(false);
	}
}// fim do código


