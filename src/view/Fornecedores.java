package view;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

public class Fornecedores extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPesquisarFornecedor;
	private JTextField txtForId;
	private JTextField txtForCNPJ;
	private JTextField txtForIE;
	private JTextField txtForIM;
	private JTextField txtForCep;
	private JTextField txtEndereco;
	private JTextField txtForNumero;
	private JTextField txtForBairro;
	private JTextField txtForRazao;
	private JTextField txtForFantasia;
	private JTextField txtForSite;
	private JTextField txtForFone;
	private JTextField txtForContato;
	private JTextField txtForEmail;
	private JTextField txtForComplemento;
	private JTextField txtForCidade;
	private JTable tblFornecedores;
	private JComboBox cboForUF;

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
		lblNewLabel.setBounds(25, 137, 46, 14);
		contentPanel.add(lblNewLabel);

		txtPesquisarFornecedor = new JTextField();
		txtPesquisarFornecedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// evento digita��o
				pesquisarFornecedorTabela();
			}

		});
		txtPesquisarFornecedor.setBounds(81, 8, 169, 20);
		contentPanel.add(txtPesquisarFornecedor);
		txtPesquisarFornecedor.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("CNPJ");
		lblNewLabel_1.setBounds(151, 137, 46, 14);
		contentPanel.add(lblNewLabel_1);

		txtForId = new JTextField();
		txtForId.setEnabled(false);
		txtForId.setEditable(false);
		txtForId.setBounds(81, 134, 46, 20);
		contentPanel.add(txtForId);
		txtForId.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Fornecedor");
		lblNewLabel_2.setBounds(10, 11, 68, 14);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("IE");
		lblNewLabel_3.setBounds(364, 137, 24, 14);
		contentPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("IM");
		lblNewLabel_4.setBounds(498, 137, 30, 14);
		contentPanel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("UF");
		lblNewLabel_5.setBounds(490, 313, 24, 14);
		contentPanel.add(lblNewLabel_5);

		txtForCNPJ = new JTextField();
		txtForCNPJ.setBounds(207, 134, 111, 20);
		contentPanel.add(txtForCNPJ);
		txtForCNPJ.setColumns(10);

		txtForIE = new JTextField();
		txtForIE.setBounds(390, 134, 98, 20);
		contentPanel.add(txtForIE);
		txtForIE.setColumns(10);

		txtForIM = new JTextField();
		txtForIM.setBounds(518, 134, 111, 20);
		contentPanel.add(txtForIM);
		txtForIM.setColumns(10);

		cboForUF = new JComboBox();
		cboForUF.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboForUF.setBounds(524, 310, 69, 20);
		contentPanel.add(cboForUF);

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

		txtForCep = new JTextField();
		txtForCep.setBounds(302, 241, 86, 20);
		contentPanel.add(txtForCep);
		txtForCep.setColumns(10);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(70, 273, 226, 20);
		contentPanel.add(txtEndereco);
		txtEndereco.setColumns(10);

		txtForNumero = new JTextField();
		txtForNumero.setBounds(368, 273, 68, 20);
		contentPanel.add(txtForNumero);
		txtForNumero.setColumns(10);

		txtForBairro = new JTextField();
		txtForBairro.setBounds(66, 310, 184, 20);
		contentPanel.add(txtForBairro);
		txtForBairro.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("Raz\u00E3o Social");
		lblNewLabel_12.setBounds(10, 168, 83, 14);
		contentPanel.add(lblNewLabel_12);

		txtForRazao = new JTextField();
		txtForRazao.setBounds(91, 165, 181, 20);
		contentPanel.add(txtForRazao);
		txtForRazao.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("Nome de Fantasia");
		lblNewLabel_13.setBounds(298, 168, 110, 14);
		contentPanel.add(lblNewLabel_13);

		txtForFantasia = new JTextField();
		txtForFantasia.setBounds(400, 165, 151, 20);
		contentPanel.add(txtForFantasia);
		txtForFantasia.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("Site");
		lblNewLabel_14.setBounds(10, 206, 46, 14);
		contentPanel.add(lblNewLabel_14);

		txtForSite = new JTextField();
		txtForSite.setBounds(44, 203, 157, 20);
		contentPanel.add(txtForSite);
		txtForSite.setColumns(10);

		JLabel lblNewLabel_15 = new JLabel("Fone");
		lblNewLabel_15.setBounds(211, 206, 46, 14);
		contentPanel.add(lblNewLabel_15);

		txtForFone = new JTextField();
		txtForFone.setBounds(245, 203, 139, 20);
		contentPanel.add(txtForFone);
		txtForFone.setColumns(10);

		JLabel lblNewLabel_16 = new JLabel("Contato");
		lblNewLabel_16.setBounds(390, 206, 46, 14);
		contentPanel.add(lblNewLabel_16);

		txtForContato = new JTextField();
		txtForContato.setColumns(10);
		txtForContato.setBounds(442, 203, 151, 20);
		contentPanel.add(txtForContato);

		JLabel lblNewLabel_17 = new JLabel("E-mail");
		lblNewLabel_17.setBounds(10, 244, 46, 14);
		contentPanel.add(lblNewLabel_17);

		txtForEmail = new JTextField();
		txtForEmail.setColumns(10);
		txtForEmail.setBounds(50, 241, 211, 20);
		contentPanel.add(txtForEmail);

		JButton btnBuscarCep = new JButton("Buscar CEP");
		btnBuscarCep.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscarCep.setToolTipText("Buscar CEP");
		btnBuscarCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// bot�o buscar CEP
				if (txtForCep.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o CEP");
					txtForCep.requestFocus();
				} else {
					buscarCEP();
				}
			}
		});
		btnBuscarCep.setBounds(398, 240, 114, 23);
		contentPanel.add(btnBuscarCep);

		txtForComplemento = new JTextField();
		txtForComplemento.setColumns(10);
		txtForComplemento.setBounds(529, 273, 100, 20);
		contentPanel.add(txtForComplemento);

		JLabel lblNewLabel_11_1 = new JLabel("Cidade");
		lblNewLabel_11_1.setBounds(268, 313, 46, 14);
		contentPanel.add(lblNewLabel_11_1);

		txtForCidade = new JTextField();
		txtForCidade.setColumns(10);
		txtForCidade.setBounds(310, 310, 157, 20);
		contentPanel.add(txtForCidade);

		JLabel lblNewLabel_9_1 = new JLabel("Observa\u00E7\u00E3o");
		lblNewLabel_9_1.setBounds(10, 377, 86, 14);
		contentPanel.add(lblNewLabel_9_1);

		JTextArea txtForObs = new JTextArea();
		txtForObs.setBounds(88, 357, 226, 64);
		contentPanel.add(txtForObs);

		btnAdicionar = new JButton("");
		btnAdicionar.setToolTipText("Adicionar Fornecedor");
		btnAdicionar.setEnabled(false);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarFornecedor();
			}
		});
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setBorderPainted(false);
		btnAdicionar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/adduser.png")));
		btnAdicionar.setBounds(513, 388, 32, 32);
		contentPanel.add(btnAdicionar);

		btnAlterar = new JButton("");
		btnAlterar.setToolTipText("Alterar Fornecedor");
		btnAlterar.setEnabled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarFornecedor();
			}
		});
		btnAlterar.setBorderPainted(false);
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/trocauser.png")));
		btnAlterar.setBounds(555, 388, 32, 32);
		contentPanel.add(btnAlterar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 53, 619, 78);
		contentPanel.add(scrollPane);

		tblFornecedores = new JTable();
		tblFornecedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// evento clicar com o mouse na tabela
				setarCaixasTexto();
			}
		});
		scrollPane.setViewportView(tblFornecedores);

		JButton btnPesquisar = new JButton("Buscar");
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setToolTipText("Buscar Fornecedor");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarFornecedores();
			}
		});
		btnPesquisar.setBounds(260, 8, 83, 23);
		contentPanel.add(btnPesquisar);

		// Valida��o com o uso da biblioteca (framework) Atxy2K
		// txtUsuId para cada caixa de texto criar um objeto

		RestrictedTextField validarid = new RestrictedTextField(txtForId);
		validarid.setOnlyNums(true);
		validarid.setLimit(4);
		// txtForCNPJ
		RestrictedTextField validarCNPJ = new RestrictedTextField(txtForCNPJ);
		validarCNPJ.setOnlyNums(true);
		validarCNPJ.setLimit(40);
		// txtForIE
		RestrictedTextField validarIE = new RestrictedTextField(txtForIE);
		validarIE.setOnlyNums(true);
		validarIE.setLimit(20);
		// txtForIM
		RestrictedTextField validarIM = new RestrictedTextField(txtForIM);
		validarIM.setOnlyNums(true);
		validarIM.setLimit(20);
		// txtFor
		RestrictedTextField validarRazao = new RestrictedTextField(txtForRazao);
		validarRazao.setLimit(40);
		// txtForFantasia
		RestrictedTextField validarFantasia = new RestrictedTextField(txtForFantasia);
		validarFantasia.setLimit(30);
		// txtForSite
		RestrictedTextField validarSite = new RestrictedTextField(txtForSite);
		validarSite.setLimit(40);
		// txtForFone
		RestrictedTextField validarFone = new RestrictedTextField(txtForFone);
		validarFone.setLimit(20);
		// txtForContato
		RestrictedTextField validarContato = new RestrictedTextField(txtForContato);
		validarContato.setLimit(40);
		// txtForEmail
		RestrictedTextField validarEmail = new RestrictedTextField(txtForEmail);
		validarEmail.setLimit(30);
		// txtForCEP
		RestrictedTextField validarCEP = new RestrictedTextField(txtForCep);
		validarCEP.setLimit(20);
		// txtEndereco
		RestrictedTextField validarEndereco = new RestrictedTextField(txtEndereco);
		validarEndereco.setLimit(40);
		// txtComplemento
		RestrictedTextField validarComplemento = new RestrictedTextField(txtForComplemento);
		validarComplemento.setLimit(30);
		// txtForbairro
		RestrictedTextField validarBairro = new RestrictedTextField(txtForBairro);
		validarBairro.setLimit(40);
		// txtForCidade
		RestrictedTextField validarCidade = new RestrictedTextField(txtForCidade);

		btnExcluir = new JButton("");
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setToolTipText("Excluir Fornecedor");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirFornecedor();
			}
		});
		btnExcluir.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/delete.png")));
		btnExcluir.setEnabled(false);
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorderPainted(false);
		btnExcluir.setBounds(597, 389, 32, 32);
		contentPanel.add(btnExcluir);
		validarCidade.setLimit(40);

	}// fim do construtor

	DAO dao = new DAO();
	private JButton btnAlterar;
	private JButton btnAdicionar;
	private JButton btnExcluir;

	/**
	 * M�todo respons�vel pela pesquisa avan�ada do fornecedor usando o nome de
	 * fantasia e a biblioteca rs2xml
	 */

	private void pesquisarFornecedorTabela() {
		String readT = "select idfor as ID,fantasia as fornecedor,fone,contato from fornecedores where fantasia like ?";
		try {
			// Estabelecer a conex�o
			Connection con = dao.conectar();
			// Preparar a execu��o da query
			PreparedStatement pst = con.prepareStatement(readT);
			// Substituir os ???? pelo conte�do das caixas de texto
			pst.setString(1, txtPesquisarFornecedor.getText() + "%");
			ResultSet rs = pst.executeQuery();
			// uso da biblioteca rs2xml para "popular" a tebala
			tblFornecedores.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * M�todo Respons�vel por setar as caixas de texto de acordo com os campos da
	 * tabela
	 */
	private void setarCaixasTexto() {
		// criar uma vari�vel para receber a linha da tabela
		int setar = tblFornecedores.getSelectedRow();
		txtForId.setText(tblFornecedores.getModel().getValueAt(setar, 0).toString());
		txtForFantasia.setText(tblFornecedores.getModel().getValueAt(setar,1).toString());
		txtForFone.setText(tblFornecedores.getModel().getValueAt(setar,2).toString());
		txtForContato.setText(tblFornecedores.getModel().getValueAt(setar,3).toString());
		txtPesquisarFornecedor.setText(tblFornecedores.getModel().getValueAt(setar,1).toString());
		
		
	}

	/**
	 * M�todo respons�vel pela pesquisa de fornecedores
	 */

	private void pesquisarFornecedores() {
		if (txtPesquisarFornecedor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome fantasia do Fornecedor");
			txtPesquisarFornecedor.requestFocus();
		} else {
			// l�gica principal
			// Query (Instru��o SQL)
			String read = "select * from fornecedores where fantasia=?";
			// tratar exce��es sempre que lidar com o banco
			try {
				// Estabelecer a conex�o com o banco

				Connection con = dao.conectar();
				// Preparar a execu��o da query

				PreparedStatement pst = con.prepareStatement(read);
				// Setar o argumento (id)

				pst.setString(1, txtPesquisarFornecedor.getText());
				// Executar a query e exibir o resultado no formul�rio

				ResultSet rs = pst.executeQuery();
				// Valida��o (Exist�ncia de usu�rio)
				if (rs.next()) {
					// Preencher(setar) os campos do formul�rio
					txtForId.setText(rs.getString(1));
					txtForCNPJ.setText(rs.getString(2));
					txtForIE.setText(rs.getString(3));
					txtForIM.setText(rs.getString(4));
					txtForRazao.setText(rs.getString(5));
					txtForFantasia.setText(rs.getString(6));
					txtForSite.setText(rs.getString(7));
					txtForFone.setText(rs.getString(8));
					txtForContato.setText(rs.getString(9));
					txtForEmail.setText(rs.getString(10));
					txtForCep.setText(rs.getString(11));
					txtEndereco.setText(rs.getString(12));
					txtForNumero.setText(rs.getString(13));
					txtForComplemento.setText(rs.getString(14));
					txtForBairro.setText(rs.getString(15));
					txtForCidade.setText(rs.getString(16));
					cboForUF.setSelectedItem(rs.getString(17));

					btnAlterar.setEnabled(true);
					btnExcluir.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Fornecedor n�o cadastrado");
					limparCampos();
					btnAdicionar.setEnabled(true);
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * M�todo respons�vel por adicionar um fornecedor no banco
	 */
	private void adicionarFornecedor() {
		// valida��o
		if (txtForCNPJ.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CNPJ do fornecedor");
			txtForCNPJ.requestFocus();
		} else if (txtForRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome a raz�o social do fornecedor");
			txtForRazao.requestFocus();
		} else if (txtForFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o nome fantasia do fornecedor");
			txtForFantasia.requestFocus();
		} else if (txtForCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o CEP do fornecedor");
			txtForCep.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o endereco fornecedor");
			txtEndereco.requestFocus();
		} else if (txtForBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o bairro do fornecedor");
			txtForBairro.requestFocus();
		} else if (txtForCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o nome fantasia do fornecedor");
			txtForCidade.requestFocus();
		} else if (cboForUF.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Infome o UF do fornecedor");
			cboForUF.requestFocus();
		} else {

			String create = "insert into fornecedores(cnpj,ie,im,razao,fantasia,site,fone,contato,email,cep,endereco,numero,complemento,bairro,cidade,uf) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir os ???? pelo conte�do das caixas de texto
				pst.setString(1, txtForCNPJ.getText());
				pst.setString(2, txtForIE.getText());
				pst.setString(3, txtForIM.getText());
				pst.setString(4, txtForRazao.getText());
				pst.setString(5, txtForFantasia.getText());
				pst.setString(6, txtForSite.getText());
				pst.setString(7, txtForFone.getText());
				pst.setString(8, txtForContato.getText());
				pst.setString(9, txtForEmail.getText());
				pst.setString(10, txtForCep.getText());
				pst.setString(11, txtEndereco.getText());
				pst.setString(12, txtForNumero.getText());
				pst.setString(13, txtForComplemento.getText());
				pst.setString(14, txtForBairro.getText());
				pst.setString(15, txtForCidade.getText());
				pst.setString(16, cboForUF.getSelectedItem().toString());
				// Executar a query e inserir o usu�rio no banco
				pst.executeUpdate();
				// Encerrar a conex�o
				limparCampos();
				LimparCamposFornecedor();
				JOptionPane.showMessageDialog(null, "Fornecedor Cadastrado com Sucesso!");
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "CNPJ ou IE ou IM em uso.\nDigite outro");
				txtForCNPJ.setText(null);
				txtForCNPJ.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	/**
	 * M�todo responsavel por alterar os dados de um fornecedor do banco
	 */

	private void alterarFornecedor() {
		// valida��o
		if (txtForCNPJ.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o CNPJ");
			txtForCNPJ.requestFocus();
		} else if (txtForRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a raz�o social do fornecedor");
			txtForRazao.requestFocus();
		} else if (txtForFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o nome fantasia do fornecedor");
			txtForFantasia.requestFocus();
		} else if (txtForFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o telefone do fornecedor");
			txtForFone.requestFocus();
		} else if (txtForCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o CEP do fornecedor");
			txtForCep.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o endere�o do fornecedor");
			txtEndereco.requestFocus();
		} else if (txtForBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe o bairro do fornecedor");
			txtForBairro.requestFocus();
		} else if (txtForCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Informe a cidade do fornecedor");
			txtForCidade.requestFocus();
		} else {
			// l�gica principal
			String update = "update fornecedores set cnpj=?, ie=?,im=?,razao=?,fantasia=?,site=?,fone=?,contato=?,email=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,uf=? where idfor=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(update);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtForCNPJ.getText());
				pst.setString(2, txtForIE.getText());
				pst.setString(3, txtForIM.getText());
				pst.setString(4, txtForRazao.getText());
				pst.setString(5, txtForFantasia.getText());
				pst.setString(6, txtForSite.getText());
				pst.setString(7, txtForFone.getText());
				pst.setString(8, txtForContato.getText());
				pst.setString(9, txtForEmail.getText());
				pst.setString(10, txtForCep.getText());
				pst.setString(11, txtEndereco.getText());
				pst.setString(12, txtForNumero.getText());
				pst.setString(13, txtForComplemento.getText());
				pst.setString(14, txtForBairro.getText());
				pst.setString(15, txtForCidade.getText());
				pst.setString(16, cboForUF.getSelectedItem().toString());
				pst.setString(17, txtForId.getText());
				// Executar a query e alterar o fornecedor no banco
				pst.executeUpdate();
				// confirma��o
				limparCampos();
				LimparCamposFornecedor();
				JOptionPane.showMessageDialog(null, "Fornecedor alterado com sucesso");
				// Encerrar a conex�o
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "CNPJ ou IE ou IM em uso.\nDigite outro");
				txtForCNPJ.setText(null);
				txtForCNPJ.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	/**
	 * M�todo responsavel por excluir um fornecedor do banco
	 */

	private void excluirFornecedor() {
		// valida��o (confirma��o da exclus�o)
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclus�o do fornecedor?", "Aten��o!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from fornecedores where idfor=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(delete);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtForId.getText());
				// Executar a query e excluir o cliente do banco
				pst.executeUpdate();
				// confirma��o
				limparCampos();
				LimparCamposFornecedor();
				JOptionPane.showMessageDialog(null, "Fornecedor excluido com sucesso");
				// Encerrar a conex�o
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	private void buscarCEP() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtForCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtForCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtForBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboForUF.setSelectedItem(element.getText());
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
						JOptionPane.showMessageDialog(null, "CEP n�o encontrado");
					}
				}

			}
			// Setar Campo Endere�o
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Limpar campos
	 */

	private void LimparCamposFornecedor() {
		// Limpar tabela
		((DefaultTableModel) tblFornecedores.getModel()).setRowCount(0);
	}

	private void limparCampos() {
		txtForCNPJ.setText(null);
		txtForIE.setText(null);
		txtForIM.setText(null);
		txtForRazao.setText(null);
		txtForFantasia.setText(null);
		txtForSite.setText(null);
		txtForFone.setText(null);
		txtForContato.setText(null);
		txtForEmail.setText(null);
		txtForCep.setText(null);
		txtEndereco.setText(null);
		txtForNumero.setText(null);
		txtForComplemento.setText(null);
		txtForBairro.setText(null);
		txtForCidade.setText(null);
		cboForUF.setSelectedItem("");
		txtForId.setText(null);
		btnAdicionar.setEnabled(false);
		btnAlterar.setEnabled(false);
		btnExcluir.setEnabled(false);
		txtPesquisarFornecedor.setText(null);

	}
}// fim do c�digo