package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Clientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPesquisarClientes;
	private JTextField txtCliId;
	private JTextField txtCliNome;
	private JTextField txtCliCPF;
	private JTextField txtCliCEP;
	private JTextField txtCliEndereco;
	private JTextField txtCliNumero;
	private JTextField txtCliBairro;
	private JTextField txtCliEmail;
	private JTextField txtCliFone;
	private JTextField txtCliComplemento;
	private JTextField txtCliCidade;
	private JTextField txtCliData;
	private JTable tblClientes;

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
		contentPanel_1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPanel_1.setLayout(null);
		contentPanel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel_1.setBounds(0, 0, 588, 436);
		contentPanel.add(contentPanel_1);
		
		JLabel lblIdcli = new JLabel("IDCLI");
		lblIdcli.setBounds(20, 137, 46, 14);
		contentPanel_1.add(lblIdcli);
		
		txtPesquisarClientes = new JTextField();
		txtPesquisarClientes.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtPesquisarClientes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarClientesTabela();
			}
		});
		txtPesquisarClientes.setColumns(10);
		txtPesquisarClientes.setBounds(50, 9, 169, 20);
		contentPanel_1.add(txtPesquisarClientes);
		
		JLabel lblNewLabel_1 = new JLabel("CPF");
		lblNewLabel_1.setBounds(354, 137, 46, 14);
		contentPanel_1.add(lblNewLabel_1);
		
		txtCliId = new JTextField();
		txtCliId.setEnabled(false);
		txtCliId.setEditable(false);
		txtCliId.setColumns(10);
		txtCliId.setBounds(56, 134, 46, 20);
		contentPanel_1.add(txtCliId);
		
		JLabel lblNewLabel_2 = new JLabel("Cliente");
		lblNewLabel_2.setBounds(10, 12, 68, 14);
		contentPanel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Nome");
		lblNewLabel_3.setBounds(112, 137, 46, 14);
		contentPanel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Data de Nascimento");
		lblNewLabel_4.setBounds(36, 178, 123, 14);
		contentPanel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("UF");
		lblNewLabel_5.setBounds(252, 326, 24, 14);
		contentPanel_1.add(lblNewLabel_5);
		
		txtCliNome = new JTextField();
		txtCliNome.setColumns(10);
		txtCliNome.setBounds(156, 134, 188, 20);
		contentPanel_1.add(txtCliNome);
		
		txtCliCPF = new JTextField();
		txtCliCPF.setColumns(10);
		txtCliCPF.setBounds(397, 134, 115, 20);
		contentPanel_1.add(txtCliCPF);
		
		cboCliUF = new JComboBox();
		cboCliUF.setModel(new DefaultComboBoxModel(new String[] {"", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		cboCliUF.setBounds(286, 323, 69, 20);
		contentPanel_1.add(cboCliUF);
		
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
		
		txtCliCEP = new JTextField();
		txtCliCEP.setColumns(10);
		txtCliCEP.setBounds(302, 241, 86, 20);
		contentPanel_1.add(txtCliCEP);
		
		txtCliEndereco = new JTextField();
		txtCliEndereco.setColumns(10);
		txtCliEndereco.setBounds(73, 244, 188, 20);
		contentPanel_1.add(txtCliEndereco);
		
		txtCliNumero = new JTextField();
		txtCliNumero.setColumns(10);
		txtCliNumero.setBounds(252, 283, 68, 20);
		contentPanel_1.add(txtCliNumero);
		
		txtCliBairro = new JTextField();
		txtCliBairro.setColumns(10);
		txtCliBairro.setBounds(55, 323, 184, 20);
		contentPanel_1.add(txtCliBairro);
		
		JLabel lblNewLabel_14 = new JLabel("E-mail");
		lblNewLabel_14.setBounds(36, 216, 46, 14);
		contentPanel_1.add(lblNewLabel_14);
		
		txtCliEmail = new JTextField();
		txtCliEmail.setColumns(10);
		txtCliEmail.setBounds(92, 213, 157, 20);
		contentPanel_1.add(txtCliEmail);
		
		JLabel lblNewLabel_15 = new JLabel("Telefone");
		lblNewLabel_15.setBounds(314, 178, 60, 14);
		contentPanel_1.add(lblNewLabel_15);
		
		txtCliFone = new JTextField();
		txtCliFone.setColumns(10);
		txtCliFone.setBounds(373, 175, 139, 20);
		contentPanel_1.add(txtCliFone);
		
		JLabel lblNewLabel_16 = new JLabel("Marketing");
		lblNewLabel_16.setBounds(286, 213, 58, 14);
		contentPanel_1.add(lblNewLabel_16);
		
		btnBuscarCEP = new JButton("Buscar CEP");
		btnBuscarCEP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscarCEP.setToolTipText("Buscar CEP");
		btnBuscarCEP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnBuscarCEP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// botao buscar cep

				if (btnBuscarCEP.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CEP");
					btnBuscarCEP.requestFocus();
				} else {
					buscarCEP();
				}
			}
		});
		btnBuscarCEP.setBounds(413, 240, 114, 23);
		contentPanel_1.add(btnBuscarCEP);
		
		txtCliComplemento = new JTextField();
		txtCliComplemento.setColumns(10);
		txtCliComplemento.setBounds(94, 283, 100, 20);
		contentPanel_1.add(txtCliComplemento);
		
		JLabel lblNewLabel_11_1 = new JLabel("Cidade");
		lblNewLabel_11_1.setBounds(328, 286, 46, 14);
		contentPanel_1.add(lblNewLabel_11_1);
		
		txtCliCidade = new JTextField();
		txtCliCidade.setColumns(10);
		txtCliCidade.setBounds(384, 283, 131, 20);
		contentPanel_1.add(txtCliCidade);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setBounds(597, 388, 32, 32);
		contentPanel_1.add(btnNewButton_3);
		
		txtCliData = new JTextField();
		txtCliData.setColumns(10);
		txtCliData.setBounds(156, 175, 117, 20);
		contentPanel_1.add(txtCliData);
		
		cbomkt = new JComboBox();
		cbomkt.setModel(new DefaultComboBoxModel(new String[] {"", "SIM", "N\u00C3O"}));
		cbomkt.setBounds(354, 209, 68, 22);
		contentPanel_1.add(cbomkt);
		
		btnAdicionar = new JButton("");
		btnAdicionar.setToolTipText("Adicionar Cliente");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarClientes();
			}
		});
		btnAdicionar.setEnabled(false);
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/img/adduser.png")));
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setBounds(433, 375, 32, 32);
		contentPanel_1.add(btnAdicionar);
		
		btnAlterar = new JButton("");
		btnAlterar.setToolTipText("Alterar Cliente");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarCliente();
				
			}
		});
		btnAlterar.setEnabled(false);
		btnAlterar.setIcon(new ImageIcon(Clientes.class.getResource("/img/trocauser.png")));
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setBorderPainted(false);
		btnAlterar.setBounds(475, 375, 32, 32);
		contentPanel_1.add(btnAlterar);
		
		btnExcluir = new JButton("");
		btnExcluir.setToolTipText("Excluir Cliente");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(Clientes.class.getResource("/img/delete.png")));
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setBounds(517, 375, 32, 32);
		contentPanel_1.add(btnExcluir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 568, 76);
		contentPanel_1.add(scrollPane);
		
		tblClientes = new JTable();
		tblClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCaixasTexto();
			}
		});
		scrollPane.setViewportView(tblClientes);
		
		//txtCliData
		
		
		// Validação com o uso da biblioteca Atxy2k
		
		// txtCliNome
		RestrictedTextField validarNascimento = new RestrictedTextField(txtCliData);
		validarNascimento.setOnlyNums(true);
		validarNascimento.setLimit(10);
		// txtCliNome
		RestrictedTextField validarID = new RestrictedTextField(txtCliId);
		validarID.setOnlyNums(true);
		validarID.setLimit(4);
		// txtCliNome
	
		RestrictedTextField validarNome = new RestrictedTextField(txtCliNome);
		validarNome.setLimit(50);
		// txtCliFone
		RestrictedTextField validarFone = new RestrictedTextField(txtCliFone);
		validarFone.setOnlyNums(true);
		validarFone.setLimit(20);
		// txtCliCPF
		RestrictedTextField validarCPF = new RestrictedTextField(txtCliCPF);
		validarCPF.setOnlyNums(true);
		validarCPF.setLimit(15);
		// txtCliEmail
		RestrictedTextField validarEmail = new RestrictedTextField(txtCliEmail);
		validarEmail.setLimit(50);
		// txtCliCEP
		RestrictedTextField validarCEP = new RestrictedTextField(txtCliCEP);
		validarCEP.setOnlyNums(true);
		validarCEP.setLimit(11);
		// txtCliEndereco
		RestrictedTextField validarEndereco = new RestrictedTextField(txtCliEndereco);
		validarEndereco.setLimit(50);
		// txtCliNumero
		RestrictedTextField validarNumero = new RestrictedTextField(txtCliNumero);
		validarNumero.setOnlyNums(true);
		validarNumero.setLimit(10);
		// txtCliComplemento
		RestrictedTextField validarComplemento = new RestrictedTextField(txtCliComplemento);
		validarComplemento.setLimit(100);
		// txtCliBairro
		RestrictedTextField validarBairro = new RestrictedTextField(txtCliBairro);
		validarBairro.setLimit(50);
		// txtCliCidade
		RestrictedTextField validarCidade = new RestrictedTextField(txtCliCidade);
		
		btnBuscarCli = new JButton("Pesquisar");
		btnBuscarCli.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscarCli.setToolTipText("Pesquisar Cliente");
		btnBuscarCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarClientes();
			}
		});
		btnBuscarCli.setBounds(229, 8, 100, 23);
		contentPanel_1.add(btnBuscarCli);
		validarCidade.setLimit(50);
		
	}// fim do construtor
	
	DAO dao = new DAO();
	private JComboBox cbomkt;
	private JComboBox cboCliUF;
	private JButton btnAdicionar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnBuscarCEP;
	private JButton btnBuscarCli;
	
	/**
	 * Método responsável pela pesquisa avançada do cliente usando o nome e a
	 * biblioteca rs2xml
	 */

	private void pesquisarClientesTabela() {
		String readT = "select idcli as ID, nome as Cliente, fone as Telefone, cpf as CPF from clientes where nome like ?";
		try {
			// Estabelecer a conexão
			Connection con = dao.conectar();
			// Preparar a execução da Query
			PreparedStatement pst = con.prepareStatement(readT);
			// Setar o argumento (fantasia)
			// Substituir o ? pelo conteúdo da caixa de texto
			pst.setString(1, txtPesquisarClientes.getText() + "%");
			ResultSet rs = pst.executeQuery();
			rs = pst.executeQuery();
			// uso da biblioteca rs2xml para "popular" a tabela
			tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Método responsável por setar os campos da tabela de acordo com a tabela
	 */

	private void setarCaixasTexto() {
		int setar = tblClientes.getSelectedRow();
		txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
		txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
		txtCliFone.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
		txtCliCPF.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
		txtPesquisarClientes.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
		

	}
	
	/**
	 * Método responsável por setar os campos da tabela de acordo com o id dos
	 * clientes
	 */

	private void pesquisarClientes() {
		if (txtPesquisarClientes.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Nome do cliente");
			txtPesquisarClientes.requestFocus();
		} else {
			// lógica principal
			// query principal ( Instrução SQL)
			String read = "select * from clientes where nome = ?";
			// tratar excessões sempre que lidar com o banco
			try {
				// estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da Query
				PreparedStatement pst = con.prepareStatement(read);
				// Setar o argumento (id)
				// Substituir o ? pelo conteúdo da caixa de texto
				pst.setString(1, txtPesquisarClientes.getText());
				// Executar a query e exibir o resultado no formulário
				ResultSet rs = pst.executeQuery();
				// Validação (existência de clientes)
				// rs.next() -> existência de clientes
				//limparCampos();
				if (rs.next()) {
					// preencher(setar) os campos do formulario
					txtCliId.setText(rs.getString(1));
					txtCliNome.setText(rs.getString(2));
					txtCliData.setText(rs.getString(3));
					txtCliFone.setText(rs.getString(4));
					txtCliCPF.setText(rs.getString(5));
					txtCliEmail.setText(rs.getString(6));
					cbomkt.setSelectedItem(rs.getString(7));
					txtCliCEP.setText(rs.getString(8));
					txtCliEndereco.setText(rs.getString(9));
					txtCliNumero.setText(rs.getString(10));
					txtCliComplemento.setText(rs.getString(11));
					txtCliBairro.setText(rs.getString(12));
					txtCliCidade.setText(rs.getString(13));
					cboCliUF.setSelectedItem(rs.getString(14));
					
					btnAdicionar.setEnabled(false);
					btnAlterar.setEnabled(true);
					btnExcluir.setEnabled(true);
					
				} else {
					JOptionPane.showMessageDialog(null, "Cliente não cadastrado");
					limparCampos();
					btnAdicionar.setEnabled(true);
					btnAlterar.setEnabled(false);
					btnExcluir.setEnabled(false);
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Método responsável por adicionar um cliente ao banco
	 */

	private void adicionarClientes() {

		if (txtCliNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o nome");
			txtCliNome.requestFocus();
		} else if (txtCliData.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a data de nascimento do cliente");
			txtCliData.requestFocus();
		} else if (txtCliFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o telefone do cliente");
			txtCliFone.requestFocus();
		} else if (txtCliCPF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o CPF do cliente");
			txtCliCPF.requestFocus();
		} else if (cbomkt.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Informe se o cliente quer Marketing");
			cbomkt.requestFocus();
		} else {
			// lógica principal
			String create = "insert into clientes(nome,nascimento,fone,cpf,email,marketing,cep,endereco,numero,complemento,bairro,cidade,uf) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da Query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir o ? pelo conteúdo da caixa de texto
				pst.setString(1, txtCliNome.getText());
				pst.setString(2, txtCliData.getText());
				pst.setString(3, txtCliFone.getText());
				pst.setString(4, txtCliCPF.getText());
				pst.setString(5, txtCliEmail.getText());
				pst.setString(6, cbomkt.getSelectedItem().toString());
				pst.setString(7, txtCliCEP.getText());
				pst.setString(8, txtCliEndereco.getText());
				pst.setString(9, txtCliNumero.getText());
				pst.setString(10, txtCliComplemento.getText());
				pst.setString(11, txtCliBairro.getText());
				pst.setString(12, txtCliCidade.getText());
				pst.setString(13, cboCliUF.getSelectedItem().toString());
				// Executar a query e inserir o cliente no banco
				pst.executeUpdate();
				// limpar campos
				limparCampos();
				LimparCamposFornecedor();
				// confirmação
				JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");
				// Encerrar a conexão
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "CPF em uso.\nDigite outro CPF");
				txtCliCPF.setText(null);
				txtCliCPF.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	/**
	 * Método responsavel por alterar os dados de um cliente do banco
	 */

	private void alterarCliente() {
		// validação
		if (txtCliNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o nome");
			txtCliNome.requestFocus();
		} else if (txtCliData.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a data de nascimento do cliente");
			txtCliData.requestFocus();
		} else if (txtCliFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o telefone do cliente");
			txtCliFone.requestFocus();
		} else if (txtCliCPF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o CPF do cliente");
			txtCliCPF.requestFocus();
		} else if (cbomkt.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Informe se o cliente quer Marketing");
			cbomkt.requestFocus();
		} else {
			// lógica principal
			String update = "update clientes set nome=?, nascimento=?, fone=?, cpf=?, email=?, marketing=?, cep=?, endereco=?, numero=?, complemento=?, bairro=?, cidade=?, uf=? where idCli=?";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da Query
				PreparedStatement pst = con.prepareStatement(update);
				// Substituir o ? pelo conteúdo da caixa de texto
				pst.setString(1, txtCliNome.getText());
				pst.setString(2, txtCliData.getText());
				pst.setString(3, txtCliFone.getText());
				pst.setString(4, txtCliCPF.getText());
				pst.setString(5, txtCliEmail.getText());
				pst.setString(6, cbomkt.getSelectedItem().toString());
				pst.setString(7, txtCliCEP.getText());
				pst.setString(8, txtCliEndereco.getText());
				pst.setString(9, txtCliNumero.getText());
				pst.setString(10, txtCliComplemento.getText());
				pst.setString(11, txtCliBairro.getText());
				pst.setString(12, txtCliCidade.getText());
				pst.setString(13, cboCliUF.getSelectedItem().toString());
				pst.setString(14, txtCliId.getText());
				// Executar a query e alterar o cliente no banco
				pst.executeUpdate();
				// confirmação
				limparCampos();
				LimparCamposFornecedor();
				JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso");
				// Encerrar a conexão
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "CPF em uso.\nEscolha outro CPF");
				txtCliCPF.setText(null);
				txtCliCPF.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	/**
	 * Método responsavel por excluir um cliente do banco
	 */

	private void excluirCliente() {
		// validação (confirmação da exclusão)
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do cliente?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from clientes where idCli=?";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da Query
				PreparedStatement pst = con.prepareStatement(delete);
				// Substituir o ? pelo conteúdo da caixa de texto
				pst.setString(1, txtCliId.getText());
				// Executar a query e excluir o cliente do banco
				pst.executeUpdate();
				// confirmação
				limparCampos();
				LimparCamposFornecedor();
				JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso");
				// Encerrar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	/**
	 * Método responsavel por buscar CEP
	*/ 
	private void buscarCEP() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCliCEP.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCliCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtCliBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboCliUF.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {

					} else {
						JOptionPane.showMessageDialog(null, "CEP nÃ£o encontrado");
					}
				}

			}
			
			// Setar Campo Endereço
			txtCliEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void LimparCamposFornecedor() {
		// Limpar tabela
		((DefaultTableModel) tblClientes.getModel()).setRowCount(0);
	}
			
	
	/**
	 * Método responsavel por Limpar campos
	 */
	
	private void limparCampos() {
		txtCliId.setText(null);
		txtCliNome.setText(null);
		txtCliData.setText(null);
		txtCliFone.setText(null);
		txtCliCPF.setText(null);
		txtCliEmail.setText(null);
		cbomkt.setSelectedItem("");
		txtCliCEP.setText(null);
		txtCliEndereco.setText(null);
		txtCliNumero.setText(null);
		txtCliComplemento.setText(null);
		txtCliBairro.setText(null);
		txtCliCidade.setText(null);
		cboCliUF.setSelectedItem("");
		btnAdicionar.setEnabled(false);
		btnAlterar.setEnabled(false);
		btnExcluir.setEnabled(false);
		txtPesquisarClientes.setText(null);
	}

}// fim do código
