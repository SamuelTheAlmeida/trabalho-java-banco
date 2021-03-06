

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.RowSorter;
import javax.swing.table.*;
import javax.swing.RowFilter;
import java.awt.Color;

public class ClientesFrame extends JFrame {

	private Cliente selecionado = null;

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JComboBox comboSexo;
	private JTextField txtRG;
	private JTextField txtCPF;
	private JTextField txtId;
	private JTable table;
	
	private ModeloCliente modelo = new ModeloCliente();
	 TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);

	          
	private JComboBox comboEstado;
	private JTextField txtEndereco;
	private JTextField txtCidade;
	private JButton btnNovoCliente;
	private JButton btnAdicionar;
	private JButton btnRemover;
	private JButton btnSalvar;
	private final ButtonGroup buttonGroupOrdenacao = new ButtonGroup();
	private JTextField txtCampoPesquisa;
	private final ButtonGroup buttonGroupPesquisa = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientesFrame frame = new ClientesFrame();
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
	public ClientesFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				System.out.println("abriu");
				limparCampos();
				txtId.setEditable(false);
			}
			@Override
			public void windowClosed(WindowEvent arg0) {
				dispose();
			}
		});
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 731, 600);
		contentPane = new JPanel();
		contentPane.setToolTipText("M");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNome.setBounds(166, 59, 46, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(222, 57, 198, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblSobrenome = new JLabel("Sobrenome");
		lblSobrenome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSobrenome.setBounds(430, 59, 64, 14);
		contentPane.add(lblSobrenome);
		
		txtSobrenome = new JTextField();
		txtSobrenome.setColumns(10);
		txtSobrenome.setBounds(504, 57, 198, 20);
		contentPane.add(txtSobrenome);
		
		JLabel lblRg = new JLabel("RG");
		lblRg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRg.setBounds(430, 88, 64, 14);
		contentPane.add(lblRg);
		
		txtRG = new JTextField();
		txtRG.setColumns(10);
		txtRG.setBounds(504, 86, 198, 20);
		contentPane.add(txtRG);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCpf.setBounds(166, 88, 64, 14);
		contentPane.add(lblCpf);
		
		txtCPF = new JTextField();
		txtCPF.setColumns(10);
		txtCPF.setBounds(222, 86, 198, 20);
		contentPane.add(txtCPF);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblId.setBounds(22, 59, 46, 14);
		contentPane.add(lblId);
		
		txtId = new JTextField();
		txtId.setColumns(10);
		txtId.setBounds(78, 57, 46, 20);
		contentPane.add(txtId);
		
		JLabel lblEndereco = new JLabel("Endereco");
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEndereco.setBounds(430, 119, 64, 14);
		contentPane.add(lblEndereco);
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(504, 117, 198, 20);
		contentPane.add(txtEndereco);
		
		JScrollPane scrollPane = new JScrollPane();


		scrollPane.setBounds(22, 244, 680, 297);
		contentPane.add(scrollPane);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSexo.setBounds(22, 88, 46, 14);
		contentPane.add(lblSexo);
		
		comboSexo = new JComboBox();
		comboSexo.setBackground(new Color(255, 255, 255));
		comboSexo.setToolTipText("M");
		comboSexo.setModel(new DefaultComboBoxModel(new String[] {"M", "F"}));
		comboSexo.setBounds(77, 85, 53, 22);
		contentPane.add(comboSexo);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEstado.setBounds(22, 120, 46, 14);
		contentPane.add(lblEstado);
		
		comboEstado = new JComboBox();
		comboEstado.setBackground(new Color(255, 255, 255));
		comboEstado.setModel(new DefaultComboBoxModel(new String[] {"AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"}));
		comboEstado.setBounds(78, 116, 53, 22);
		contentPane.add(comboEstado);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCidade.setBounds(166, 119, 64, 14);
		contentPane.add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(222, 117, 198, 20);
		contentPane.add(txtCidade);
		
		JLabel lblManterCliente = new JLabel("Manter Cliente");
		lblManterCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblManterCliente.setFont(new Font("Franklin Gothic Book", Font.BOLD, 20));
		lblManterCliente.setBounds(22, 12, 680, 20);
		contentPane.add(lblManterCliente);
		
		btnAdicionar = new JButton("Cadastrar");
		btnAdicionar.setBackground(new Color(255, 255, 255));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
 
				String nome = txtNome.getText();
				String sobrenome = txtSobrenome.getText();
				char sexo = comboSexo.getSelectedItem().toString().toUpperCase().charAt(0);
				String CPF = txtCPF.getText();
				String RG = txtRG.getText();
				String estado = comboEstado.getSelectedItem().toString();
				String cidade = txtCidade.getText();
				String endereco = txtEndereco.getText();
				if (nome != null && sobrenome != null & String.valueOf(sexo) != null & CPF != null & RG != null & estado != null
						& cidade != null & endereco != null) {
					Cliente c = new Cliente(nome, sobrenome, sexo, CPF, RG, estado, cidade, endereco);
					modelo.adicionar(c);
					limparCampos();
				} else {
					JOptionPane.showInternalMessageDialog(getParent(), "Digite todos os campos");
				}

			}
		});
		btnAdicionar.setBounds(22, 169, 91, 23);
		contentPane.add(btnAdicionar);
		
		table = new JTable();
		table.setRowSorter(sorter);

		scrollPane.setViewportView(table);
		table.setFillsViewportHeight(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnAdicionar.setEnabled(false);
				btnSalvar.setEnabled(true);
				btnRemover.setEnabled(true);
				Cliente c = modelo.getCliente(table.getSelectedRow());
				txtId.setText(Integer.toString(c.getId()));
				txtNome.setText(c.getNome());
				txtSobrenome.setText(c.getSobrenome());
				comboSexo.setSelectedItem(String.valueOf(c.getSexo()));
				txtCPF.setText(c.getCpf());
				txtRG.setText(c.getRg());
				comboEstado.setSelectedItem(c.getEstado());
				txtCidade.setText(c.getCidade());
				txtEndereco.setText(c.getEndereco());
				selecionado = c;
			}

		});
		table.setModel(modelo);
		table.setBorder(new CompoundBorder());
		
		btnRemover = new JButton("Remover");
		btnRemover.setBackground(new Color(255, 255, 255));
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selecionado != null) {
					int op = JOptionPane.showConfirmDialog(getParent(), "Se o cliente possuir uma conta, ela ser� tamb�m apagada. "
							+ "Deseja confirmar a exclus�o?");
					System.out.println("op =" + op);
					if (op == 0) {
						modelo.remover(selecionado);
						limparCampos();
					}
				}
			}
		});
		btnRemover.setBounds(238, 169, 91, 23);
		contentPane.add(btnRemover);
		
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBackground(new Color(255, 255, 255));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = Integer.parseInt(txtId.getText());
				String nome = txtNome.getText();
				String sobrenome = txtSobrenome.getText();
				char sexo = comboSexo.getSelectedItem().toString().charAt(0);
				String CPF = txtCPF.getText();
				String RG = txtRG.getText();
				String estado = comboEstado.getSelectedItem().toString();
				String cidade = txtCidade.getText();
				String endereco = txtEndereco.getText();
				if (nome != null && sobrenome != null & String.valueOf(sexo) != null & CPF != null & RG != null & estado != null
						& cidade != null & endereco != null) {
					Cliente c = new Cliente(id, nome, sobrenome, sexo, CPF, RG, estado, cidade, endereco);
					int index = table.getSelectedRow();
					modelo.atualizar(index, c);
					limparCampos();
				} else {
					JOptionPane.showInternalMessageDialog(getParent(), "Digite todos os campos");
				}
			}
		});
		btnSalvar.setBounds(130, 169, 91, 23);
		contentPane.add(btnSalvar);
		

		

		
		btnNovoCliente = new JButton("Novo Cliente");
		btnNovoCliente.setBackground(new Color(255, 255, 255));
		btnNovoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limparCampos();
				btnSalvar.setEnabled(false);
				btnRemover.setEnabled(false);
				btnAdicionar.setEnabled(true);
			}
		});
		btnNovoCliente.setBounds(349, 169, 119, 23);
		contentPane.add(btnNovoCliente);
		
		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblPesquisar.setBounds(22, 215, 78, 14);
		contentPane.add(lblPesquisar);
		
		txtCampoPesquisa = new JTextField();
		txtCampoPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String itemPesquisado = "";
				itemPesquisado = txtCampoPesquisa.getText();
				if (itemPesquisado.trim().length() == 0) {
				     sorter.setRowFilter(null);
				  } else {
				     sorter.setRowFilter(RowFilter.regexFilter("(?i)" + itemPesquisado));
				  }
			}
		});

		txtCampoPesquisa.setBounds(110, 212, 330, 20);
		contentPane.add(txtCampoPesquisa);
		txtCampoPesquisa.setColumns(10);
		
		JRadioButton radioOrdenaSobrenome = new JRadioButton("Sobrenome");
		radioOrdenaSobrenome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelo.ordenaSobrenome();
			}
		});
		buttonGroupOrdenacao.add(radioOrdenaSobrenome);
		radioOrdenaSobrenome.setBounds(593, 214, 109, 23);
		contentPane.add(radioOrdenaSobrenome);
		
		JRadioButton radioOrdenaNome = new JRadioButton("Nome");
		radioOrdenaNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modelo.ordenaNome();
			}
		});
		buttonGroupOrdenacao.add(radioOrdenaNome);
		radioOrdenaNome.setBounds(465, 214, 109, 23);
		contentPane.add(radioOrdenaNome);
		
		JLabel lblOrdenarPor = new JLabel("Ordenar por:");
		lblOrdenarPor.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblOrdenarPor.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdenarPor.setBounds(465, 198, 211, 16);
		contentPane.add(lblOrdenarPor);
		
		//modelo.
	}
	
	public void limparCampos() {
		btnAdicionar.setEnabled(true);
		btnSalvar.setEnabled(true);
		btnRemover.setEnabled(true);
		btnNovoCliente.setEnabled(true);
		int id = table.getRowCount()+1;
		txtId.setText(String.valueOf(id));
		txtNome.setText("");
		txtSobrenome.setText("");
		comboSexo.setSelectedIndex(0);
		txtCPF.setText("");
		txtRG.setText("");
		comboEstado.setSelectedIndex(0);
		txtCidade.setText("");
		txtEndereco.setText("");
	}
	
}
