package view;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import java.awt.Cursor;

public class Relatorio extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Relatorio dialog = new Relatorio();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Relatorio() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Relatorio.class.getResource("/img/relatorio.png")));
		setTitle("Relat\u00F3rios");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 261);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JButton btnRelClientes = new JButton("");
		btnRelClientes.setToolTipText("Relat\u00F3rio de Clientes");
		btnRelClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelClientes.setContentAreaFilled(false);
		btnRelClientes.setBorderPainted(false);
		btnRelClientes.setIcon(new ImageIcon(Relatorio.class.getResource("/img/clientesrelatorio.png")));
		btnRelClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioClientes();
			}
		});
		btnRelClientes.setBounds(248, 24, 64, 64);
		contentPanel.add(btnRelClientes);
		
		JButton btnRelInvetario = new JButton("");
		btnRelInvetario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelInvetario.setToolTipText("Relat\u00F3rio de Invent\u00E1rio");
		btnRelInvetario.setContentAreaFilled(false);
		btnRelInvetario.setBorderPainted(false);
		btnRelInvetario.setIcon(new ImageIcon(Relatorio.class.getResource("/img/inventariorelatorio.png")));
		btnRelInvetario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioInventario();
			}
		});
		btnRelInvetario.setBounds(26, 180, 64, 64);
		contentPanel.add(btnRelInvetario);
		
		JButton btnRelFornecedores = new JButton("");
		btnRelFornecedores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelFornecedores.setToolTipText("Relat\u00F3rio de Fornecedores");
		btnRelFornecedores.setContentAreaFilled(false);
		btnRelFornecedores.setBorderPainted(false);
		btnRelFornecedores.setIcon(new ImageIcon(Relatorio.class.getResource("/img/fornecedoresrelatorio.png")));
		btnRelFornecedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioFornecedores();
			}
		});
		btnRelFornecedores.setBounds(26, 96, 64, 64);
		contentPanel.add(btnRelFornecedores);
		
		JButton btnRelReposicao = new JButton("");
		btnRelReposicao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelReposicao.setToolTipText("Relat\u00F3rio de Reposi\u00E7ao");
		btnRelReposicao.setContentAreaFilled(false);
		btnRelReposicao.setBorderPainted(false);
		btnRelReposicao.setIcon(new ImageIcon(Relatorio.class.getResource("/img/reposicaorelatorio.png")));
		btnRelReposicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioRepEstoque();
			}
		});
		btnRelReposicao.setBounds(26, 11, 64, 64);
		contentPanel.add(btnRelReposicao);
		
		JButton btnRelMarketing = new JButton("");
		btnRelMarketing.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelMarketing.setToolTipText("Relat\u00F3rio de Marketing");
		btnRelMarketing.setContentAreaFilled(false);
		btnRelMarketing.setBorderPainted(false);
		btnRelMarketing.setIcon(new ImageIcon(Relatorio.class.getResource("/img/email_18961.png")));
		btnRelMarketing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorioEmailMkt();
			}
		});
		btnRelMarketing.setBounds(248, 119, 64, 64);
		contentPanel.add(btnRelMarketing);
		
		JLabel lblNewLabel = new JLabel("Reposi\u00E7\u00E3o de Estoque");
		lblNewLabel.setBounds(115, 42, 141, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fornecedores");
		lblNewLabel_1.setBounds(113, 122, 98, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Invent\u00E1rio");
		lblNewLabel_2.setBounds(120, 207, 70, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("E-mail Marketing");
		lblNewLabel_3.setBounds(332, 154, 115, 14);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Clientes");
		lblNewLabel_4.setBounds(332, 55, 46, 14);
		contentPanel.add(lblNewLabel_4);
	}// fim do construtor
	
	//acesso ao banco de dados
	DAO dao = new DAO();
	
	//método responsável pela impressão do relatório de clientes
	
	private void relatorioClientes() {
		//criar objeto para construir a página pdf
		Document document = new Document();
		//gerar o documento pdf
		try {
			//cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("clientes.pdf"));
			document.open();
			//gerar o conteúdo do documento
			Date data = new Date();			
	        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Clientes cadastrados"));
			document.add(new Paragraph(" "));
			//... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("CPF"));
			PdfPCell col4 = new PdfPCell(new Paragraph("E-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			// Acessar o banco de dados
			 
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { //executa o código independente do resultado OK ou não
			document.close();
		}
		
		//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("clientes.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//método responsável pela impressão do relatório de fornecedores
	
	private void relatorioFornecedores() {
		//criar objeto para construir a página pdf
		Document document = new Document();
		//gerar o documento pdf
		try {
			//cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("fornecedores.pdf"));
			document.open();
			//gerar o conteúdo do documento
			Date data = new Date();			
	        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Fornecedores cadastrados"));
			document.add(new Paragraph(" "));
			//... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("Empresa"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("CNPJ"));
			PdfPCell col4 = new PdfPCell(new Paragraph("E-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			// Acessar o banco de dados
			 
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { //executa o código independente do resultado OK ou não
			document.close();
		}
		
		//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("fornecedores.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
		
		//método responsável pela impressão do relatório de reposição de estoque
		
	private void relatorioRepEstoque() {
		//criar objeto para construir a página pdf
		Document document = new Document();
		//gerar o documento pdf
		try {
			//cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("reposicao.pdf"));
			document.open();
			//gerar o conteúdo do documento
			Date data = new Date();			
	        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Reposicão cadastradas"));
			document.add(new Paragraph(" "));
			//... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("Produto"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Estoque"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Estoque Minímo"));
			PdfPCell col4 = new PdfPCell(new Paragraph("Prateleira"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			// Acessar o banco de dados
			 
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { //executa o código independente do resultado OK ou não
			document.close();
		}
		
		//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("reposicao.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
			
			//método responsável pela impressão do relatório de invetário
			
	private void relatorioInventario() {
		//criar objeto para construir a página pdf
		Document document = new Document();
		//gerar o documento pdf
		try {
			//cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("inventario.pdf"));
			document.open();
			//gerar o conteúdo do documento
			Date data = new Date();			
	        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Inventário cadastrado"));
			document.add(new Paragraph(" "));
			//... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("CPF"));
			PdfPCell col4 = new PdfPCell(new Paragraph("E-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			// Acessar o banco de dados
			 
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { //executa o código independente do resultado OK ou não
			document.close();
		}
		
		//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("inventario.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
			
//método responsável pela impressão do relatório de invetário
			
	private void relatorioEmailMkt() {
		//criar objeto para construir a página pdf
		Document document = new Document();
		//gerar o documento pdf
		try {
			//cria um documento pdf em branco de nome clientes.pdf
			PdfWriter.getInstance(document, new FileOutputStream("marketing.pdf"));
			document.open();
			//gerar o conteúdo do documento
			Date data = new Date();			
	        	DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
			document.add(new Paragraph(new Paragraph(formatador.format(data))));
			document.add(new Paragraph(" "));
			document.add(new Paragraph("Marketing cadastrado"));
			document.add(new Paragraph(" "));
			//... Demais conteúdos (imagem, tabela, gráfico, etc)
			PdfPTable tabela = new PdfPTable(4);
			PdfPCell col1 = new PdfPCell(new Paragraph("SIM"));
			PdfPCell col2 = new PdfPCell(new Paragraph("NÃO"));
			PdfPCell col3 = new PdfPCell(new Paragraph(" "));
			PdfPCell col4 = new PdfPCell(new Paragraph(" "));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			tabela.addCell(col4);
			// Acessar o banco de dados
			 
			document.add(tabela);
		} catch (Exception e) {
			System.out.println(e);
		} finally { //executa o código independente do resultado OK ou não
			document.close();
		}
		
		//abrir o documento que foi gerado no leitor padrão de pdf do sistema (PC)
		try {
			Desktop.getDesktop().open(new File("marketing.pdf"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}//fim do código
