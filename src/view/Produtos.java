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
	private JTextField txtBarCode;
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
		setBounds(100, 100, 778, 479);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 762, 440);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Produtos.class.getResource("/img/barcode.png")));
		lblNewLabel.setBounds(10, 20, 59, 45);
		contentPanel.add(lblNewLabel);
		
		txtBarCode = new JTextField();
		txtBarCode.setBounds(79, 35, 228, 20);
		contentPanel.add(txtBarCode);
		txtBarCode.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("C\u00F3digo");
		lblNewLabel_1.setBounds(23, 76, 46, 14);
		contentPanel.add(lblNewLabel_1);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(79, 73, 108, 20);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PesquisarProdutoCodigo();
			}
		});
		btnPesquisar.setBounds(207, 72, 100, 23);
		contentPanel.add(btnPesquisar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0))), "Fornecedor", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(360, 20, 392, 148);
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
		
		JLabel lblNewLabel_3 = new JLabel("ID");
		lblNewLabel_3.setBounds(213, 27, 32, 14);
		panel.add(lblNewLabel_3);
		
		txtIdFor = new JTextField();
		txtIdFor.setBounds(236, 24, 105, 20);
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
		lblNewLabel_4.setBounds(10, 116, 46, 14);
		contentPanel.add(lblNewLabel_4);
		
		txtProduto = new JTextField();
		txtProduto.setBounds(66, 113, 108, 20);
		contentPanel.add(txtProduto);
		txtProduto.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Descri\u00E7\u00E3o");
		lblNewLabel_5.setBounds(10, 211, 71, 14);
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
		btnAdicionarProd.setBounds(479, 353, 64, 64);
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
		btnAtualizarProd.setBounds(553, 353, 64, 64);
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
		btnExcluirProd.setBounds(627, 353, 64, 64);
		contentPanel.add(btnExcluirProd);
		
		JLabel lblNewLabel_6 = new JLabel("Fabricante");
		lblNewLabel_6.setBounds(10, 266, 71, 14);
		contentPanel.add(lblNewLabel_6);
		
		txtFabricante = new JTextField();
		txtFabricante.setBounds(79, 263, 144, 20);
		contentPanel.add(txtFabricante);
		txtFabricante.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Estoque");
		lblNewLabel_7.setBounds(10, 329, 46, 14);
		contentPanel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Estoque min\u00EDmo");
		lblNewLabel_8.setBounds(127, 329, 96, 14);
		contentPanel.add(lblNewLabel_8);
		
		txtEstoque = new JTextField();
		txtEstoque.setBounds(66, 326, 51, 20);
		contentPanel.add(txtEstoque);
		txtEstoque.setColumns(10);
		
		txtEstoqueMin = new JTextField();
		txtEstoqueMin.setBounds(217, 326, 59, 20);
		contentPanel.add(txtEstoqueMin);
		txtEstoqueMin.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Local");
		lblNewLabel_10.setBounds(10, 360, 39, 14);
		contentPanel.add(lblNewLabel_10);
		
		txtlocal = new JTextField();
		txtlocal.setBounds(66, 357, 210, 20);
		contentPanel.add(txtlocal);
		txtlocal.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Entrada");
		lblNewLabel_11.setBounds(339, 197, 51, 14);
		contentPanel.add(lblNewLabel_11);
		
		dataEntrada = new JDateChooser();
		dataEntrada.setBounds(389, 197, 123, 20);
		contentPanel.add(dataEntrada);
		
		txtAreaDesc = new JTextArea();
		txtAreaDesc.setBounds(66, 182, 228, 73);
		contentPanel.add(txtAreaDesc);
		
		JLabel lblNewLabel_12 = new JLabel("Custo");
		lblNewLabel_12.setBounds(536, 197, 39, 14);
		contentPanel.add(lblNewLabel_12);
		
		txtCusto = new JTextField();
		txtCusto.setBounds(594, 194, 86, 20);
		contentPanel.add(txtCusto);
		txtCusto.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Lucro(%)");
		lblNewLabel_13.setBounds(536, 251, 59, 14);
		contentPanel.add(lblNewLabel_13);
		
		txtLucro = new JTextField();
		txtLucro.setBounds(594, 248, 86, 20);
		contentPanel.add(txtLucro);
		txtLucro.setColumns(10);
		
		txtAnoModelo = new JTextField();
		txtAnoModelo.setBounds(221, 113, 86, 20);
		contentPanel.add(txtAnoModelo);
		txtAnoModelo.setColumns(10);
		
		JLabel lblNewLabel_14 = new JLabel("Ano");
		lblNewLabel_14.setBounds(184, 116, 46, 14);
		contentPanel.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Chassi");
		lblNewLabel_15.setBounds(10, 154, 46, 14);
		contentPanel.add(lblNewLabel_15);
		
		txtChassi = new JTextField();
		txtChassi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					PesquisarProdutoCodigodeBarras();
				}
			}
		});
		txtChassi.setBounds(64, 151, 144, 20);
		contentPanel.add(txtChassi);
		txtChassi.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("Venda");
		lblNewLabel_16.setBounds(339, 251, 46, 14);
		contentPanel.add(lblNewLabel_16);
		
		txtVenda = new JTextField();
		txtVenda.setBounds(389, 248, 96, 20);
		contentPanel.add(txtVenda);
		txtVenda.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Quantidade");
		lblNewLabel_9.setBounds(10, 291, 86, 20);
		contentPanel.add(lblNewLabel_9);
		
		txtQuantidade = new JTextField();
		txtQuantidade.setBounds(89, 294, 86, 20);
		contentPanel.add(txtQuantidade);
		txtQuantidade.setColumns(10);
		
		// TxtCodigoID
		RestrictedTextField validarid = new RestrictedTextField(txtCodigo);
		validarid.setOnlyNums(true);
		validarid.setLimit(4);
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
		//RestrictedTextField validarRazao = new RestrictedTextField(txtAreaDesc);
		//validarRazao.setLimit(100);
		
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
		validarCusto.setOnlyNums(true);
		validarCusto.setLimit(7);
		// txtVenda
		RestrictedTextField validarVenda = new RestrictedTextField(txtVenda);
		validarVenda.setOnlyNums(true);
		validarVenda.setLimit(7);
		// txtLucro
		RestrictedTextField validarLucro = new RestrictedTextField(txtLucro);
		validarLucro.setOnlyNums(true);
		validarLucro.setLimit(7);
		
		
	}// fim dos construtor
		
	
	// modelo da classe DAO
	DAO dao = new DAO();
	private JTextField txtAnoModelo;
	private JTextField txtChassi;
	private JTextArea txtAreaDesc;
	private JTextField txtVenda;
	private JTextField txtQuantidade;
	private JButton btnAdicionarProd;
	private JButton btnAtualizarProd;
	private JButton btnExcluirProd;
	private JDateChooser dataEntrada;
	
	private void pesquisarFornecedorTabela() {
		String readT = "select idfor as ID,fantasia as fornecedor,fone,contato from fornecedores where fantasia like ?";
		try {
			// Estabelecer a conex�o
			Connection con = dao.conectar();
			// Preparar a execu��o da query
			PreparedStatement pst = con.prepareStatement(readT);
			// Substituir os ???? pelo conte�do das caixas de texto
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
	 * M�todo Respons�vel por setar as caixas de texto de acordo com os campos da
	 * tabela
	 */
	
	private void setarCaixasTexto() {
		// criar uma vari�vel para receber a linha da tabela
		int setar = tblFornecedor.getSelectedRow();
		txtIdFor.setText(tblFornecedor.getModel().getValueAt(setar, 0).toString());
	}
	
	// pesquisar produto por c�digo
	private void PesquisarProdutoCodigo() {
		String read = "select * from produtos where id = ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, txtCodigo.getText());		
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
				txtVenda.setText(rs.getString(14));
				//JCalendar - formata��o para exibi��o
				String setarDataCad = rs.getString(7);
				// apoio a l�gica
				//System.out.println(setarDataCad);
				Date dataVal = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataCad);
				dataEntrada.setDate(dataVal);
				btnAtualizarProd.setEnabled(true);
				btnExcluirProd.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "Produto n�o cadastrado");
				limparCampos();
				btnAdicionarProd.setEnabled(true);
			}
			con.close();
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	
	// pesquisar produto por c�digo de barras
		private void PesquisarProdutoCodigodeBarras() {
			String read2 = "select * from produtos where chassi = ?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(read2);
				pst.setString(1, txtChassi.getText());		
				ResultSet rs = pst.executeQuery();
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
					txtVenda.setText(rs.getString(14));
					//JCalendar - formata��o para exibi��o
					String setarDataCad = rs.getString(7);
					// apoio a l�gica
					//System.out.println(setarDataCad);
					Date dataVal = new SimpleDateFormat("yyyy-MM-dd").parse(setarDataCad);
					dataEntrada.setDate(dataVal);
					btnAtualizarProd.setEnabled(true);
					btnExcluirProd.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Produto n�o cadastrado");
					
					btnAdicionarProd.setEnabled(true);
				}
				con.close();
			}catch (Exception e) {
				System.out.println(e);
			}
		}
	
	/**
	 * M�todo respons�vel por adicionar um produto no banco
	 */
	private void adicionarProduto() {
		// valida��o
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
			JOptionPane.showMessageDialog(null, "Infome a descri��o da moto");
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
			JOptionPane.showMessageDialog(null, "Infome o estoque min�mo da moto");
			txtEstoqueMin.requestFocus();
		}else if (txtCusto.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "informe o  custo da moto");
				txtCusto.requestFocus();
		}else if (txtIdFor.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Infome o ID do fornecedor");
					txtIdFor.requestFocus();
		} else {

			String create = "insert into produtos(nomemoto,anomodelo,chassi,descricao,fabricante,quantidade,estoque,estoquemin,localizacao,custo,lucro,venda,idfor) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da query
				PreparedStatement pst = con.prepareStatement(create);
				// Substituir os ???? pelo conte�do das caixas de texto
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
				pst.setString(12, txtVenda.getText());
				pst.setString(13, txtIdFor.getText());
				// Executar a query e inserir o usu�rio no banco
				pst.executeUpdate();
				// Encerrar a conex�o
				limparCampos();
				//LimparCamposFornecedor();
				JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso!");
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Chassi j� exixtente.\nDigite outro");
				txtChassi.setText(null);
				txtChassi.requestFocus();
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
			JOptionPane.showMessageDialog(null, "Infome a descri��o da moto");
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
			JOptionPane.showMessageDialog(null, "Infome o estoque min�mo da moto");
			txtEstoqueMin.requestFocus();
		}else if (txtCusto.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "informe o  custo da moto");
				txtCusto.requestFocus();
		}else if (txtIdFor.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Infome o ID do fornecedor");
					txtIdFor.requestFocus();
		} else {
			// l�gica principal
			String update = "update produtos set nomemoto=?,anomodelo=?,chassi=?,descricao=?,fabricante=?,quantidade=?,estoque=?,estoquemin=?,localizacao=?,custo=?,lucro=?,venda=?,idfor=? where id=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(update);
				// Substituir o ? pelo conte�do da caixa de texto
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
				pst.setString(12, txtVenda.getText());
				pst.setString(13, txtIdFor.getText());
				pst.setString(14, txtCodigo.getText());
				// Executar a query e alterar o fornecedor no banco
				pst.executeUpdate();
				// confirma��o
				limparCampos();
				//LimparCamposFornecedor();
				JOptionPane.showMessageDialog(null, "Produto alterado com sucesso");
				// Encerrar a conex�o
				con.close();
			} catch (SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Chassi j� exixtente.\nDigite outro");
				txtChassi.setText(null);
				txtChassi.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}

	private void excluirProduto() {
		// valida��o (confirma��o da exclus�o)
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclus�o do produto?", "Aten��o!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from produtos where id=?";
			try {
				// Estabelecer a conex�o
				Connection con = dao.conectar();
				// Preparar a execu��o da Query
				PreparedStatement pst = con.prepareStatement(delete);
				// Substituir o ? pelo conte�do da caixa de texto
				pst.setString(1, txtCodigo.getText());
				// Executar a query e excluir o cliente do banco
				pst.executeUpdate();
				// confirma��o
				limparCampos();
				// LimparCamposFornecedor();
				JOptionPane.showMessageDialog(null, "Produto excluido com sucesso");
				// Encerrar a conex�o
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

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
	    txtVenda.setText(null);
	    txtIdFor.setText(null);
	    txtCodigo.setText(null);
	    dataEntrada.setDate(null);
	    btnAdicionarProd.setEnabled(false);
	    btnAtualizarProd.setEnabled(false);
		btnExcluirProd.setEnabled(false);
	}
}// fim do c�digo
