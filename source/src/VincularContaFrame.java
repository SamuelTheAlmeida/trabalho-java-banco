import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VincularContaFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtNumeroConta;
	private JTextField txtDepositoInicial;
	private JTextField txtLimite;
	private JTextField txtDepositoMinimo;
	private JTextField txtMontanteMinimo;
	private JComboBox comboClientes;
	private JLabel lblNomeCliente;
	private JComboBox comboTiposConta;
	private JLabel lblTipoContaSelected;
	private JLabel lblMontanteMnimo;
	private JLabel lblDepsitoMnimo;
	private JLabel lblLimite;
	private JLabel lblDepsitoInicial;
	private int contaSelecionada;
	private TipoConta tipoConta;
	private Cliente clienteSelecionado;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VincularContaFrame frame = new VincularContaFrame();
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
	public VincularContaFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblVincularConta = new JLabel("Vincular Conta");
		lblVincularConta.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblVincularConta.setBounds(193, 11, 166, 49);
		contentPane.add(lblVincularConta);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(30, 75, 39, 14);
		contentPane.add(lblCliente);
		
		JLabel lblTipoConta = new JLabel("Tipo Conta");
		lblTipoConta.setBounds(30, 108, 57, 14);
		contentPane.add(lblTipoConta);
		
		JPanel panel = new JPanel();
		panel.setBounds(30, 148, 499, 131);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblContaN = new JLabel("Conta N\u00BA");
		lblContaN.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblContaN.setBounds(20, 10, 72, 14);
		panel.add(lblContaN);
		
		lblDepsitoInicial = new JLabel("Dep\u00F3sito Inicial");
		lblDepsitoInicial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDepsitoInicial.setBounds(20, 41, 81, 14);
		panel.add(lblDepsitoInicial);
		
		lblLimite = new JLabel("Limite");
		lblLimite.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLimite.setBounds(20, 69, 72, 14);
		panel.add(lblLimite);
		
		txtNumeroConta = new JTextField();
		txtNumeroConta.setColumns(10);
		txtNumeroConta.setBounds(110, 8, 123, 20);
		panel.add(txtNumeroConta);
		
		txtDepositoInicial = new JTextField();
		txtDepositoInicial.setColumns(10);
		txtDepositoInicial.setBounds(110, 39, 123, 20);
		panel.add(txtDepositoInicial);
		
		txtLimite = new JTextField();
		txtLimite.setColumns(10);
		txtLimite.setBounds(110, 67, 123, 20);
		panel.add(txtLimite);
		
		lblDepsitoMnimo = new JLabel("Dep\u00F3sito M\u00EDnimo");
		lblDepsitoMnimo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDepsitoMnimo.setBounds(258, 12, 100, 14);
		panel.add(lblDepsitoMnimo);
		
		txtDepositoMinimo = new JTextField();
		txtDepositoMinimo.setColumns(10);
		txtDepositoMinimo.setBounds(355, 8, 123, 20);
		panel.add(txtDepositoMinimo);
		
		lblMontanteMnimo = new JLabel("Montante M\u00EDnimo");
		lblMontanteMnimo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMontanteMnimo.setBounds(258, 45, 100, 14);
		panel.add(lblMontanteMnimo);
		
		txtMontanteMinimo = new JTextField();
		txtMontanteMinimo.setColumns(10);
		txtMontanteMinimo.setBounds(355, 41, 123, 20);
		panel.add(txtMontanteMinimo);
		

		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(333, 295, 91, 23);
		contentPane.add(btnVoltar);
		
		lblNomeCliente = new JLabel("");
		lblNomeCliente.setBounds(196, 75, 326, 14);
		contentPane.add(lblNomeCliente);
		
		comboClientes = new JComboBox();
		comboClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idCliente = (int)comboClientes.getSelectedItem();
				clienteSelecionado = Cliente.consultarCliente(idCliente);
				lblNomeCliente.setText(clienteSelecionado.getNome() + " " + clienteSelecionado.getSobrenome());
			}
		});;
		ArrayList<Cliente> listaDeClientes = Cliente.buscarClientes();
		for (Cliente cliente : listaDeClientes) {
			comboClientes.addItem(cliente.getId());
		}

		
		comboClientes.setToolTipText("");
		comboClientes.setBounds(101, 71, 85, 22);
		contentPane.add(comboClientes);
		
		lblTipoContaSelected = new JLabel("");
		lblTipoContaSelected.setBounds(196, 108, 326, 14);
		contentPane.add(lblTipoContaSelected);
		
		
		comboTiposConta = new JComboBox();
		ArrayList<TipoConta> listaDeTiposConta = TipoConta.buscarTiposConta();
		for (TipoConta tipoConta : listaDeTiposConta) {
			comboTiposConta.addItem(tipoConta.getIdTipoConta());
		}
		comboTiposConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idTipoConta = (int)comboTiposConta.getSelectedItem();
				tipoConta = TipoConta.consultarTipoConta(idTipoConta);
				lblTipoContaSelected.setText(tipoConta.getNomeConta());
				if (tipoConta.getIdTipoConta() == 1) {
					contaSelecionada = 1;
					lblDepsitoMnimo.setVisible(false);
					txtDepositoMinimo.setVisible(false);
					lblMontanteMnimo.setVisible(false);
					txtMontanteMinimo.setVisible(false);
					lblLimite.setVisible(true);
					txtLimite.setVisible(true);
				} else {
					contaSelecionada = 2;
					lblDepsitoMnimo.setVisible(true);
					txtDepositoMinimo.setVisible(true);
					lblMontanteMnimo.setVisible(true);
					txtMontanteMinimo.setVisible(true);
					lblLimite.setVisible(false);
					txtLimite.setVisible(false);
				}
			}
		});;
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = clienteSelecionado;
				int numConta = Integer.parseInt(txtNumeroConta.getText());
				double depositoInicial = Double.parseDouble(txtDepositoInicial.getText());
				switch(contaSelecionada) {
				case 1:
					double limite = Double.parseDouble(txtLimite.getText());
					ContaCorrente contaCorrente = new ContaCorrente(numConta, tipoConta, 
							depositoInicial, limite);
					contaCorrente.setDono(cliente);
					ContaCorrente.CriaContaCorrente(contaCorrente);
					cliente.setIdConta(numConta);
					break;
				case 2:
					double montanteMinimo = Double.parseDouble(txtMontanteMinimo.getText());
					double depositoMinimo = Double.parseDouble(txtDepositoMinimo.getText());
					ContaInvestimento contaInvestimento = new ContaInvestimento(numConta, tipoConta, montanteMinimo, 
							depositoMinimo, depositoInicial);
					contaInvestimento.setDono(cliente);
					ContaInvestimento.CriaContaInvestimento(contaInvestimento);
					cliente.setIdConta(numConta);
					break;
				}
				
			}
		});
		btnSalvar.setBounds(438, 295, 91, 23);
		contentPane.add(btnSalvar);

		comboTiposConta.setBounds(101, 104, 85, 22);
		contentPane.add(comboTiposConta);
		comboTiposConta.setSelectedIndex(0);
		
		inicializaCampos();
	}
	
	public void inicializaCampos() {
		int numeroConta = Conta.getNumeracaoConta();
		txtNumeroConta.setText(String.valueOf(numeroConta));
		txtNumeroConta.setEditable(false);
	}
}
