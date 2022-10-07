package view;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

public class Produtos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtPesquisarFor;
	private JTextField txtIdFor;
	private JTable tblFornecedor;
	private JTextField txtProduto;
	private JTextField txtFabricante;
	private JTextField txtEstoque;
	private JTextField txtEstoqueMin;
	private JTextField txtlocal;
	private JTextField txtCusto;
	private JTextField txtLucro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Produtos dialog = new Produtos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Produtos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Produtos.class.getResource("/img/produto.png")));
		setTitle("Produtos");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 788, 559);
		getContentPane().setLayout(null);
		contentPanel.setBounds(-2, 0, 772, 523);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Produtos.class.getResource("/img/barcode.png")));
		lblNewLabel.setBounds(218, 182, 59, 45);
		contentPanel.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0))), "Fornecedor",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(350, 192, 392, 148);
		contentPanel.add(panel);
		panel.setLayout(null);

		txtPesquisarFor = new JTextField();
		txtPesquisarFor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarFornecedorTabela();
			}
		});
		txtPesquisarFor.setBounds(10, 24, 140, 20);
		panel.add(txtPesquisarFor);
		txtPesquisarFor.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(154, 11, 32, 37);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setIcon(new ImageIcon(Produtos.class.getResource("/img/pesquisa.png")));

		JLabel lblNewLabel_3 = new JLabel("IDFOR");
		lblNewLabel_3.setBounds(213, 27, 43, 14);
		panel.add(lblNewLabel_3);

		txtIdFor = new JTextField();
		txtIdFor.setBounds(262, 24, 105, 20);
		panel.add(txtIdFor);
		txtIdFor.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 355, 85);
		panel.add(scrollPane);

		tblFornecedor = new JTable();
		tblFornecedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCaixasTexto();
			}
		});
		scrollPane.setViewportView(tblFornecedor);

		JLabel lblNewLabel_4 = new JLabel("Produto");
		lblNewLabel_4.setBounds(19, 250, 46, 14);
		contentPanel.add(lblNewLabel_4);

		txtProduto = new JTextField();
		txtProduto.setBounds(67, 247, 108, 20);
		contentPanel.add(txtProduto);
		txtProduto.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Descri\u00E7\u00E3o");
		lblNewLabel_5.setBounds(20, 305, 71, 14);
		contentPanel.add(lblNewLabel_5);

		btnAdicionarProd = new JButton("");
		btnAdicionarProd.setEnabled(false);
		btnAdicionarProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarProduto();
			}
		});
		btnAdicionarProd.setContentAreaFilled(false);
		btnAdicionarProd.setBorderPainted(false);
		btnAdicionarProd.setIcon(new ImageIcon(Produtos.class.getResource("/img/createP.png")));
		btnAdicionarProd.setBounds(472, 427, 64, 64);
		contentPanel.add(btnAdicionarProd);

		btnAtualizarProd = new JButton("");
		btnAtualizarProd.setEnabled(false);
		btnAtualizarProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarFornecedor();
			}
		});
		btnAtualizarProd.setContentAreaFilled(false);
		btnAtualizarProd.setBorderPainted(false);
		btnAtualizarProd.setIcon(new ImageIcon(Produtos.class.getResource("/img/updateP.png")));
		btnAtualizarProd.setBounds(546, 425, 64, 64);
		contentPanel.add(btnAtualizarProd);

		btnExcluirProd = new JButton("");
		btnExcluirProd.setEnabled(false);
		btnExcluirProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirProduto();
			}
		});
		btnExcluirProd.setContentAreaFilled(false);
		btnExcluirProd.setBorderPainted(false);
		btnExcluirProd.setIcon(new ImageIcon(Produtos.class.getResource("/img/deleteProdutos.png")));
		btnExcluirProd.setBounds(621, 426, 64, 64);
		contentPanel.add(btnExcluirProd);

		JLabel lblNewLabel_6 = new JLabel("Fabricante");
		lblNewLabel_6.setBounds(17, 375, 71, 14);
		contentPanel.add(lblNewLabel_6);

		txtFabricante = new JTextField();
		txtFabricante.setBounds(86, 371, 144, 20);
		contentPanel.add(txtFabricante);
		txtFabricante.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Estoque");
		lblNewLabel_7.setBounds(19, 449, 46, 14);
		contentPanel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Estoque min\u00EDmo");
		lblNewLabel_8.setBounds(153, 449, 96, 14);
		contentPanel.add(lblNewLabel_8);

		txtEstoque = new JTextField();
		txtEstoque.setBounds(81, 445, 51, 20);
		contentPanel.add(txtEstoque);
		txtEstoque.setColumns(10);

		txtEstoqueMin = new JTextField();
		txtEstoqueMin.setBounds(253, 445, 59, 20);
		contentPanel.add(txtEstoqueMin);
		txtEstoqueMin.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Local");
		lblNewLabel_10.setBounds(18, 484, 39, 14);
		contentPanel.add(lblNewLabel_10);

		txtlocal = new JTextField();
		txtlocal.setBounds(69, 481, 210, 20);
		contentPanel.add(txtlocal);
		txtlocal.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Entrada");
		lblNewLabel_11.setBounds(342, 369, 51, 14);
		contentPanel.add(lblNewLabel_11);

		dataEntrada = new JDateChooser();
		dataEntrada.setBounds(391, 364, 154, 20);
		contentPanel.add(dataEntrada);

		txtAreaDesc = new JTextArea();
		txtAreaDesc.setBounds(82, 284, 228, 73);
		contentPanel.add(txtAreaDesc);

		JLabel lblNewLabel_12 = new JLabel("Custo");
		lblNewLabel_12.setBounds(563, 367, 39, 14);
		contentPanel.add(lblNewLabel_12);

		txtCusto = new JTextField();
		txtCusto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (somente números ao digitar)
				String caracteres = "0987654321.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtCusto.setBounds(610, 364, 86, 20);
		contentPanel.add(txtCusto);
		txtCusto.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("Lucro(%)");
		lblNewLabel_13.setBounds(342, 399, 59, 14);
		contentPanel.add(lblNewLabel_13);

		txtLucro = new JTextField();
		txtLucro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// validação (somente números ao digitar)
				String caracteres = "0987654321.";
				if (!caracteres.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtLucro.setBounds(404, 395, 86, 20);
		contentPanel.add(txtLucro);
		txtLucro.setColumns(10);

		txtAnoModelo = new JTextField();
		txtAnoModelo.setBounds(213, 247, 86, 20);
		contentPanel.add(txtAnoModelo);
		txtAnoModelo.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("Ano");
		lblNewLabel_14.setBounds(186, 249, 46, 14);
		contentPanel.add(lblNewLabel_14);

		JLabel lblNewLabel_15 = new JLabel("Chassi");
		lblNewLabel_15.setBounds(18, 203, 46, 14);
		contentPanel.add(lblNewLabel_15);

		txtChassi = new JTextField();
		txtChassi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					PesquisarProdutoCodigodeBarras();
				}
			}
		});
		txtChassi.setBounds(63, 199, 144, 20);
		contentPanel.add(txtChassi);
		txtChassi.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Quantidade");
		lblNewLabel_9.setBounds(18, 411, 86, 20);
		contentPanel.add(lblNewLabel_9);

		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(88, 411, 86, 20);
		contentPanel.add(txtQuantidade);
		txtQuantidade.setColumns(10);

		// txtProduto
		RestrictedTextField validarProduto = new RestrictedTextField(txtProduto);
		validarProduto.setLimit(20);
		// txtForIE
		RestrictedTextField validarAnoModelo = new RestrictedTextField(txtAnoModelo);
		validarAnoModelo.setOnlyNums(true);
		validarAnoModelo.setLimit(9);
		// txtChassi
		RestrictedTextField validarChassi = new RestrictedTextField(txtChassi);
		validarChassi.setOnlyNums(true);
		validarChassi.setLimit(15);
		// txtAreaDesc
		// RestrictedTextField validarRazao = new RestrictedTextField(txtAreaDesc);
		// validarRazao.setLimit(100);

		// txtFabricante
		RestrictedTextField validarFabricante = new RestrictedTextField(txtFabricante);
		validarFabricante.setLimit(30);
		// txtQuantidade
		RestrictedTextField validarQuantidade = new RestrictedTextField(txtQuantidade);
		validarQuantidade.setOnlyNums(true);
		validarQuantidade.setLimit(6);
		// txtEstoque
		RestrictedTextField validarEstoque = new RestrictedTextField(txtEstoque);
		validarEstoque.setOnlyNums(true);
		validarEstoque.setLimit(6);
		// txtEstoqueMin
		RestrictedTextField validarEstoqueMin = new RestrictedTextField(txtEstoqueMin);
		validarEstoqueMin.setOnlyNums(true);
		validarEstoqueMin.setLimit(6);
		// txtlocal
		RestrictedTextField validarLocal = new RestrictedTextField(txtlocal);
		validarLocal.setLimit(30);
		// txtPesquisarFor
		RestrictedTextField validarPesquisarFor = new RestrictedTextField(txtPesquisarFor);
		validarPesquisarFor.setLimit(30);
		// txtIdFor
		RestrictedTextField validarIdFor = new RestrictedTextField(txtIdFor);
		validarIdFor.setOnlyNums(true);
		validarIdFor.setLimit(4);
		// txtCusto
		RestrictedTextField validarCusto = new RestrictedTextField(txtCusto);

		validarCusto.setLimit(7);

	
		RestrictedTextField validarLucro = new RestrictedTextField(txtLucro);
		validarLucro.setLimit(7);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0))), "Produtos",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(22, 11, 720, 158);
		contentPanel.add(panel_1);

		txtPesquisarProd = new JTextField();
		txtPesquisarProd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarProdutosTabela();
			}
		});
		txtPesquisarProd.setColumns(10);
		txtPesquisarProd.setBounds(10, 24, 326, 20);
		panel_1.add(txtPesquisarProd);

		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setBounds(154, 11, 32, 37);
		panel_1.add(lblNewLabel_2_1);

		JLabel lblNewLabel_3_1 = new JLabel("IDPROD");
		lblNewLabel_3_1.setBounds(410, 25, 51, 14);
		panel_1.add(lblNewLabel_3_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 52, 700, 94);
		panel_1.add(scrollPane_1);

		tblProdutos = new JTable();
		tblProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCaixasTexto2();
			}
		});
		scrollPane_1.setViewportView(tblProdutos);

		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(470, 22, 108, 20);
		panel_1.add(txtCodigo);
		txtCodigo.setColumns(10);

		// TxtCodigoID
		RestrictedTextField validarid = new RestrictedTextField(txtCodigo);
		validarid.setOnlyNums(true);
		validarid.setLimit(4);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBounds(592, 20, 100, 23);
		panel_1.add(btnPesquisar);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PesquisarProdutoCodigo();
			}
		});

	}// fim dos construtor

	// modelo da classe DAO
	DAO dao = new DAO();
	private JTextField txtAnoModelo;
	private JTextField txtChassi;
	private JTextArea txtAreaDesc;
	private JTextField txtQuantidade;
	private JButton btnAdicionarProd;
	private JButton btnAtualizarProd;
	private JButton btnExcluirProd;
	private JDateChooser dataEntrada;
	private JTextField txtPesquisarProd;
	private JTable tblProdutos;

	private void pesquisarFornecedorTabela() {
		String readT = "select idfor as ID,fantasia as fornecedor,fone,contato from fornecedores where fantasia like ?";
		try {
			// Estabelecer a conexão
			Connection con = dao.conectar();
			// Preparar a execução da query
			PreparedStatement pst = con.prepareStatement(readT);
			// Substituir os ???? pelo conteúdo das caixas de texto
			pst.setString(1, txtPesquisarFor.getText() + "%");
			ResultSet rs = pst.executeQuery();
			// uso da biblioteca rs2xml para "popular" a tebala
			tblFornecedor.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Pesquisar Produtos Tabela
	 */

	private void pesquisarProdutosTabela() {
		String readT = "select id as ID,nomemoto as Veículo,chassi as Chassi,fabricante as Fabricante, custo as Custo from produtos where nomemoto like ?";
		try {
			// Estabelecer a conexão
			Connection con = dao.conectar();
			// Preparar a execução da query
			PreparedStatement pst = con.prepareStatement(readT);
			// Substituir os ???? pelo conteúdo das caixas de texto
			pst.setString(1, txtPesquisarProd.getText() + "%");
			ResultSet rs = pst.executeQuery();
			// uso da biblioteca rs2xml para "popular" a tebala
			tblProdutos.setModel(DbUtils.resultSetToTableModel(rs));
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void setarCaixasTexto2() {
		// criar uma variável para receber a linha da tabela
		int setar = tblProdutos.getSelectedRow();
		txtCodigo.setText(tblProdutos.getModel().getValueAt(setar, 0).toString());
		txtPesquisarProd.setText(tblProdutos.getModel().getValueAt(setar, 1).toString());
		

	}

	/**
	 * Método Responsável por setar as caixas de texto de acordo com os campos da
	 * tabela
	 */

	private void setarCaixasTexto() {
		// criar uma variável para receber a linha da tabela
		int setar = tblFornecedor.getSelectedRow();
		txtIdFor.setText(tblFornecedor.getModel().getValueAt(setar, 0).toString());
		txtPesquisarFor.setText(tblFornecedor.getModel().getValueAt(setar, 1).toString());
		
	}

	// pesquisar produto por código
	private void PesquisarProdutoCodigo() {
		String read = "select * from produtos where nomemoto = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, txtPesquisarProd.getText());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				txtProduto.setText(rs.getString(2));
				txtAnoModelo.setText(rs.getString(3));
				txtChassi.setText(rs.getString(4));
				txtAreaDesc.setText(rs.getString(5));
				txtFabricante.setText(rs.getString(6));
				txtQuantidade.setText(rs.getString(8));
				txtEstoque.setText(rs.getString(9));
				txtEstoqueMin.setText(rs.getString(10));
				txtlocal.setText(rs.getString(11));
				txtCusto.setText(rs.getString(12));
				txtLucro.setText(rs.getString(13));
				
				txtIdFor.setText(rs.getString(15));
				// JCalendar - formatação para exibição
				String setarDataCad = rs.getString(7);
				// apoio a lógica
				// System.out.println(setarDataCad);
				Date dataVal = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataCad);
				dataEntrada.setDate(dataVal);
				btnAtualizarProd.setEnabled(true);
				btnExcluirProd.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "Produto não cadastrado");
				limparCampos();
				LimparCamposFornecedor();
				txtCodigo.setText(null);
				btnAdicionarProd.setEnabled(true);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// pesquisar produto por código de barras
	private void PesquisarProdutoCodigodeBarras() {
		String read2 = "select * from produtos where chassi = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, txtChassi.getText());
			ResultSet rs = pst.executeQuery();
			limparCamposCodigo();
			if (rs.next()) {
				txtCodigo.setText(rs.getString(1));
				txtProduto.setText(rs.getString(2));
				txtAnoModelo.setText(rs.getString(3));
				txtChassi.setText(rs.getString(4));
				txtAreaDesc.setText(rs.getString(5));
				txtFabricante.setText(rs.getString(6));
				txtQuantidade.setText(rs.getString(8));
				txtEstoque.setText(rs.getString(9));
				txtEstoqueMin.setText(rs.getString(10));
				txtlocal.setText(rs.getString(11));
				txtCusto.setText(rs.getString(12));
				txtLucro.setText(rs.getString(13));
				
				// JCalendar - formatação para exibição
				String setarDataCad = rs.getString(7);
				// apoio a lógica
				// System.out.println(setarDataCad);
				Date dataVal = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataCad);
				dataEntrada.setDate(dataVal);
				btnAtualizarProd.setEnabled(true);
				btnExcluirProd.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "Produto não cadastrado");
				limparCamposCodigo();
				LimparCamposFornecedor();

				btnAdicionarProd.setEnabled(true);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Método responsável por adicionar um produto no banco
	 */
	private void adicionarProduto() {
		// validação
		if (txtProduto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome do Produto");
			txtProduto.requestFocus();
		} else if (txtAnoModelo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o ano modelo da moto");
			txtAnoModelo.requestFocus();
		} else if (txtChassi.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o chassi da moto");
			txtChassi.requestFocus();
		} else if (txtAreaDesc.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome a descrição da moto");
			txtAreaDesc.requestFocus();
		} else if (txtFabricante.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o fabricante da moto");
			txtFabricante.requestFocus();
		} else if (txtQuantidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome a quantidade da moto");
			txtQuantidade.requestFocus();
		} else if (txtEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o estoque da moto");
			txtEstoque.requestFocus();
		} else if (txtEstoqueMin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o estoque minímo da moto");
			txtEstoqueMin.requestFocus();
		} else if (txtLucro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o valor do lucro da moto");
			txtLucro.requestFocus();
		} else if (txtCusto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "informe o  custo da moto");
			txtCusto.requestFocus();
		} else if (txtIdFor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o ID do fornecedor");
			txtIdFor.requestFocus();
		} else {

			String create = "insert into produtos(nomemoto,anomodelo,chassi,descricao,fabricante,quantidade,estoque,estoquemin,localizacao,custo,lucro,idfor) values (?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir os ???? pelo conteúdo das caixas de texto
				pst.setString(1, txtProduto.getText());
				pst.setString(2, txtAnoModelo.getText());
				pst.setString(3, txtChassi.getText());
				pst.setString(4, txtAreaDesc.getText());
				pst.setString(5, txtFabricante.getText());
				pst.setString(6, txtQuantidade.getText());
				pst.setString(7, txtEstoque.getText());
				pst.setString(8, txtEstoqueMin.getText());
				pst.setString(9, txtlocal.getText());
				pst.setString(10, txtCusto.getText());
				pst.setString(11, txtLucro.getText());
				
				pst.setString(12, txtIdFor.getText());
				// Executar a query e inserir o usuário no banco
				pst.executeUpdate();
				// Encerrar a conexão
				limparCampos();
				LimparCamposFornecedor();
				LimparCamposProdutos();
				txtPesquisarProd.setText(null);
				JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso!");
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Chassi já exixtente.\nDigite outro");
				txtChassi.setText(null);
				txtChassi.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Método responsavel por alterar os dados de um fornecedor do banco
	 */

	private void alterarFornecedor() {
		// validação
		if (txtProduto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Nome do Produto");
			txtProduto.requestFocus();
		} else if (txtAnoModelo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o ano modelo da moto");
			txtAnoModelo.requestFocus();
		} else if (txtChassi.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o chassi da moto");
			txtChassi.requestFocus();
		} else if (txtAreaDesc.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome a descrição da moto");
			txtAreaDesc.requestFocus();
		} else if (txtFabricante.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o fabricante da moto");
			txtFabricante.requestFocus();
		} else if (txtQuantidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome a quantidade da moto");
			txtQuantidade.requestFocus();
		} else if (txtEstoque.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o estoque da moto");
			txtEstoque.requestFocus();
		}  else if (txtLucro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o valor do lucro da moto");
			txtLucro.requestFocus();
		} else if (txtEstoqueMin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o estoque minímo da moto");
			txtEstoqueMin.requestFocus();
		} else if (txtCusto.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "informe o  custo da moto");
			txtCusto.requestFocus();
		} else if (txtIdFor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Infome o ID do fornecedor");
			txtIdFor.requestFocus();
		} else {
			// lógica principal
			String update = "update produtos set nomemoto=?,anomodelo=?,chassi=?,descricao=?,fabricante=?,datacad=?,quantidade=?,estoque=?,estoquemin=?,localizacao=?,custo=?,lucro=?,idfor=? where id=?";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da Query
				PreparedStatement pst = con.prepareStatement(update);
				// Substituir o ? pelo conteúdo da caixa de texto
				SimpleDateFormat formatador = new SimpleDateFormat("yyyyMMdd");
				String dataVal = formatador.format(dataEntrada.getDate());
				pst.setString(1, txtProduto.getText());
				pst.setString(2, txtAnoModelo.getText());
				pst.setString(3, txtChassi.getText());
				pst.setString(4, txtAreaDesc.getText());
				pst.setString(5, txtFabricante.getText());
				pst.setString(6, dataVal);

				pst.setString(7, txtQuantidade.getText());
				pst.setString(8, txtEstoque.getText());
				pst.setString(9, txtEstoqueMin.getText());
				pst.setString(10, txtlocal.getText());
				pst.setString(11, txtCusto.getText());
				pst.setString(12, txtLucro.getText());
				
				pst.setString(13, txtIdFor.getText());
				pst.setString(14, txtCodigo.getText());
				// Executar a query e alterar o fornecedor no banco
				pst.executeUpdate();
				// confirmação
				limparCampos();
				LimparCamposFornecedor();
				LimparCamposProdutos();
				txtPesquisarProd.setText(null);
				JOptionPane.showMessageDialog(null, "Produto alterado com sucesso");
				// Encerrar a conexão
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Chassi já exixtente.\nDigite outro");
				txtChassi.setText(null);
				txtChassi.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	private void excluirProduto() {
		// validação (confirmação da exclusão)
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão do produto?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from produtos where id=?";
			try {
				// Estabelecer a conexão
				Connection con = dao.conectar();
				// Preparar a execução da Query
				PreparedStatement pst = con.prepareStatement(delete);
				// Substituir o ? pelo conteúdo da caixa de texto
				pst.setString(1, txtCodigo.getText());
				// Executar a query e excluir o cliente do banco
				pst.executeUpdate();
				// confirmação
				limparCampos();
				LimparCamposFornecedor();
				LimparCamposProdutos();
				txtPesquisarProd.setText(null);
				JOptionPane.showMessageDialog(null, "Produto excluido com sucesso");
				// Encerrar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	private void LimparCamposFornecedor() {
		// Limpar tabela
		((DefaultTableModel) tblFornecedor.getModel()).setRowCount(0);
	}

	private void LimparCamposProdutos() {
		// Limpar tabela
		((DefaultTableModel) tblProdutos.getModel()).setRowCount(0);
	}

	private void limparCampos() {
		txtProduto.setText(null);
		txtAnoModelo.setText(null);
		txtChassi.setText(null);
		txtAreaDesc.setText(null);
		txtFabricante.setText(null);
		txtQuantidade.setText(null);
		txtEstoque.setText(null);
		txtEstoqueMin.setText(null);
		txtlocal.setText(null);
		txtCusto.setText(null);
		txtLucro.setText(null);
		
		txtIdFor.setText(null);
		txtCodigo.setText(null);
		dataEntrada.setDate(null);
		btnAdicionarProd.setEnabled(false);
		btnAtualizarProd.setEnabled(false);
		btnExcluirProd.setEnabled(false);
	}

	private void limparCamposCodigo() {
		txtProduto.setText(null);
		txtAnoModelo.setText(null);
		txtAreaDesc.setText(null);
		txtFabricante.setText(null);
		txtQuantidade.setText(null);
		txtEstoque.setText(null);
		txtEstoqueMin.setText(null);
		txtlocal.setText(null);
		txtCusto.setText(null);
		txtLucro.setText(null);
	
		txtIdFor.setText(null);
		txtCodigo.setText(null);
		dataEntrada.setDate(null);
		btnAdicionarProd.setEnabled(false);
		btnAtualizarProd.setEnabled(false);
		btnExcluirProd.setEnabled(false);
	}
}// fim do código
