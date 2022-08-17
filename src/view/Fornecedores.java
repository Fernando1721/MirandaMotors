package view;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
		lblNewLabel.setBounds(10, 137, 46, 14);
		contentPanel.add(lblNewLabel);

		txtPesquisarFornecedor = new JTextField();
		txtPesquisarFornecedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// evento digitação
				pesquisarFornecedorTabela();
			}

		});
		txtPesquisarFornecedor.setBounds(103, 22, 169, 20);
		contentPanel.add(txtPesquisarFornecedor);
		txtPesquisarFornecedor.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("CNPJ");
		lblNewLabel_1.setBounds(204, 137, 46, 14);
		contentPanel.add(lblNewLabel_1);

		txtForId = new JTextField();
		txtForId.setBounds(50, 134, 46, 20);
		contentPanel.add(txtForId);
		txtForId.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Fornecedor");
		lblNewLabel_2.setBounds(25, 25, 68, 14);
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
		txtForCNPJ.setBounds(243, 134, 111, 20);
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

		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/pesquisa.png")));
		lblNewLabel_6.setBounds(282, 10, 32, 32);
		contentPanel.add(lblNewLabel_6);

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
		txtForRazao.setBounds(88, 168, 181, 20);
		contentPanel.add(txtForRazao);
		txtForRazao.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("Nome de Fantasia");
		lblNewLabel_13.setBounds(278, 173, 110, 14);
		contentPanel.add(lblNewLabel_13);

		txtForFantasia = new JTextField();
		txtForFantasia.setBounds(390, 170, 203, 20);
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

		JButton btnAdicionar = new JButton("");
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

		JButton btnAlterar = new JButton("");
		btnAlterar.setBorderPainted(false);
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAlterar.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/trocauser.png")));
		btnAlterar.setBounds(555, 388, 32, 32);
		contentPanel.add(btnAlterar);

		JButton bntExcluir = new JButton("");
		bntExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bntExcluir.setContentAreaFilled(false);
		bntExcluir.setBorderPainted(false);
		bntExcluir.setIcon(new ImageIcon(Fornecedores.class.getResource("/img/delete.png")));
		bntExcluir.setBounds(597, 388, 32, 32);
		contentPanel.add(bntExcluir);

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
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarFornecedores();
			}
		});
		btnPesquisar.setBounds(111, 133, 83, 23);
		contentPanel.add(btnPesquisar);
		
		// Validação com o uso da biblioteca (framework) Atxy2K
		// txtUsuId para cada caixa de texto criar um objeto

				RestrictedTextField validarid = new RestrictedTextField(txtForId);
				validarid.setOnlyNums(true);
				validarid.setLimit(4);
				// txtForCNPJ
				RestrictedTextField validarCNPJ= new RestrictedTextField(txtForCNPJ);
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
				//txtFor
				RestrictedTextField validarRazao = new RestrictedTextField(txtForRazao);
				validarRazao.setLimit(40);
				//txtForFantasia
				RestrictedTextField validarFantasia = new RestrictedTextField(txtForFantasia);
				validarFantasia.setLimit(30);
				//txtForSite
				RestrictedTextField validarSite = new RestrictedTextField(txtForSite);
				validarSite.setLimit(40);
				//txtForFone
				RestrictedTextField validarFone= new RestrictedTextField(txtForFone);
				validarFone.setLimit(20);
				//txtForContato
				RestrictedTextField validarContato = new RestrictedTextField(txtForContato);
				validarContato.setLimit(40);
				//txtForEmail
				RestrictedTextField validarEmail = new RestrictedTextField(txtForEmail);
				validarEmail.setLimit(30);
				//txtForCEP
				RestrictedTextField validarCEP = new RestrictedTextField(txtForCep);
				validarCEP.setLimit(20);
				//txtEndereco
				RestrictedTextField validarEndereco = new RestrictedTextField(txtEndereco);
				validarEndereco.setLimit(40);
				//txtComplemento
				RestrictedTextField validarComplemento = new RestrictedTextField(txtForComplemento);
				validarComplemento.setLimit(30);
				//txtForbairro
				RestrictedTextField validarBairro = new RestrictedTextField(txtForBairro);
				validarBairro.setLimit(40);
				//txtForCidade
				RestrictedTextField validarCidade = new RestrictedTextField(txtForCidade);
				validarCidade.setLimit(40);
				
				
		
	}// fim do construtor

	DAO dao = new DAO();

	/**
	 * Método responsável pela pesquisa avançada do fornecedor usando o nome de
	 * fantasia e a biblioteca rs2xml
	 */

	private void pesquisarFornecedorTabela() {
		String readT = "select idfor as ID,fantasia as fornecedor,fone,contato from fornecedores where fantasia like ?";
		try {
			// Estabelecer a conexão
			Connection con = dao.conectar();
			// Preparar a execução da query
			PreparedStatement pst = con.prepareStatement(readT);
			// Substituir os ???? pelo conteúdo das caixas de texto
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
	 * Método Responsável por setar as caixas de texto de acordo com os campos da
	 * tabela
	 */
	private void setarCaixasTexto() {
		// criar uma variável para receber a linha da tabela
		int setar = tblFornecedores.getSelectedRow();
		txtForId.setText(tblFornecedores.getModel().getValueAt(setar,0).toString());
		//txtForFantasia.setText(tblFornecedores.getModel().getValueAt(setar,1).toString());
	}
	

	/**
	 * Método responsável pela pesquisa de fornecedores
	 */

	private void pesquisarFornecedores() {
		if (txtForId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o ID do Fornecedor");
			txtForId.requestFocus();
		} else {
			// lógica principal
			// Query (Instrução SQL)
			String read = "select * from fornecedores where idfor=?";
			// tratar exceções sempre que lidar com o banco
			try {
				// Estabelecer a conexão com o banco

				Connection con = dao.conectar();
				// Preparar a execução da query

				PreparedStatement pst = con.prepareStatement(read);
				// Setar o argumento (id)

				pst.setString(1, txtForId.getText());
				// Executar a query e exibir o resultado no formulário

				ResultSet rs = pst.executeQuery();
				// Validação (Existência de usuário)
				if (rs.next()) {
					// Preencher(setar) os campos do formulário
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
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Método responsável por adicionar um fornecedor no banco
	 */
	private void adicionarFornecedor() {
		// validação
		if (txtForCNPJ.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CNPJ do fornecedor");
			txtForCNPJ.requestFocus();
		} else if (txtForRazao.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome a razão social do fornecedor");
			txtForRazao.requestFocus();
		}else if (txtForFantasia.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o nome fantasia do fornecedor");
			txtForFantasia.requestFocus();
		}else if (txtForCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o CEP do fornecedor");
			txtForCep.requestFocus();
		}else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o endereco fornecedor");
			txtEndereco.requestFocus();
		}else if (txtForBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o bairro do fornecedor");
			txtForBairro.requestFocus();
		}else if (txtForCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o nome fantasia do fornecedor");
			txtForCidade.requestFocus();
		}else if (cboForUF.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Infome o UF do fornecedor");
			cboForUF.requestFocus();
		} else {
			 
			String create = "insert into fornecedores(cnpj,ie,im,razao,fantasia,site,fone,contato,email,cep,endereco,numero,complemento,bairro,cidade,uf) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir os ???? pelo conteúdo das caixas de texto
				pst.setString(1, txtForCNPJ.getText());
				pst.setString(2,txtForIE.getText());
				pst.setString(3,txtForIM.getText());
				pst.setString(4, txtForRazao.getText());
				pst.setString(5,txtForFantasia.getText());
				pst.setString(6, txtForSite.getText());
				pst.setString(7,txtForFone.getText());
				pst.setString(8,txtForContato.getText());
				pst.setString(9,txtForEmail.getText());
				pst.setString(10,txtForCep.getText());
				pst.setString(11,txtEndereco.getText());
				pst.setString(12,txtForNumero.getText());
				pst.setString(13,txtForComplemento.getText());
				pst.setString(14,txtForBairro.getText());
				pst.setString(15,txtForCidade.getText());
				pst.setString(16,cboForUF.getSelectedItem().toString());
				// Executar a query e inserir o usuário no banco
				pst.executeUpdate();
				// Encerrar a conexão
				JOptionPane.showMessageDialog(null, "Usuário Cadastrado com Sucesso!");
				con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	
		}		
	}
	
	
	
	/**
	 * Limpar campos
	 */
	
	private void LimparCamposFornecedor() {
		// Limpar tabela
		((DefaultTableModel) tblFornecedores.getModel()).setRowCount(0);
	}

}// fim do código