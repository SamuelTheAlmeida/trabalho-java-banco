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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.SystemColor;

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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				comboClientes.setSelectedIndex(0);
			}
			@Override
			public void windowClosed(WindowEvent e) {
				dispose();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 355);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblVincularConta = new JLabel("Vincular Conta");
		lblVincularConta.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 25));
		lblVincularConta.setBounds(193, 6, 166, 49);
		contentPane.add(lblVincularConta);
		
		JLabel lblCliente = new JLabel("ID Cliente");
		lblCliente.setBounds(40, 72, 57, 14);
		contentPane.add(lblCliente);
		
		JLabel lblTipoConta = new JLabel("Tipo Conta");
		lblTipoConta.setBounds(40, 108, 72, 14);
		contentPane.add(lblTipoConta);
		
		JPanel panel = new JPanel();
		panel.setBounds(16, 138, 487, 152);
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
		btnVoltar.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnVoltar.setBackground(new Color(255, 255, 255));
		btnVoltar.setBounds(154, 123, 91, 23);
		panel.add(btnVoltar);
		
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSalvar.setBackground(new Color(255, 255, 255));
		btnSalvar.setBounds(258, 123, 91, 23);
		panel.add(btnSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = clienteSelecionado;
				int numConta = Integer.parseInt(txtNumeroConta.getText());
				double depositoInicial = Double.parseDouble(txtDepositoInicial.getText());
				switch(contaSelecionada) {
				case 1:
					double limite = Double.parseDouble(txtLimite.getText());
					ContaCorrente contaCorrente = new ContaCorrente(numConta, tipoConta, 
							cliente, depositoInicial, limite);
					contaCorrente.setDono(cliente);
					if (Conta.consultarConta(cliente) == null) {
						ContaCorrente.CriaContaCorrente(contaCorrente);
					} else {
						ContaCorrente.atualizarContaCorrente(contaCorrente);
					}
					
					cliente.setIdConta(numConta);
					break;
				case 2:
					double montanteMinimo = Double.parseDouble(txtMontanteMinimo.getText());
					double depositoMinimo = Double.parseDouble(txtDepositoMinimo.getText());
					ContaInvestimento contaInvestimento = new ContaInvestimento(numConta, tipoConta, cliente, montanteMinimo, 
							depositoMinimo, depositoInicial);
					contaInvestimento.setDono(cliente);
					if (Conta.consultarConta(cliente) == null) {
						ContaInvestimento.CriaContaInvestimento(contaInvestimento);
					} else {
						ContaInvestimento.atualizarContaInvestimento(contaInvestimento);
					}
					cliente.setIdConta(numConta);
					break;
				}
				inicializaCampos();
				
			}
		});
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		lblNomeCliente = new JLabel("");
		lblNomeCliente.setBounds(258, 72, 209, 14);
		contentPane.add(lblNomeCliente);
		
		comboTiposConta = new JComboBox();
		comboTiposConta.setBackground(new Color(255, 255, 255));
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
		
		comboClientes = new JComboBox();
		comboClientes.setBackground(new Color(255, 255, 255));
		comboClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtDepositoInicial.setEditable(true);
				int idCliente = (int)comboClientes.getSelectedItem();
				clienteSelecionado = Cliente.consultarCliente(idCliente);
				lblNomeCliente.setText(clienteSelecionado.getNome() + " " + clienteSelecionado.getSobrenome());
				Conta conta = Conta.consultarConta(clienteSelecionado);
				
				if (conta != null) {
					comboTiposConta.setEnabled(false);
					txtNumeroConta.setText(String.valueOf(conta.getNumero()));
					int tipo = conta.getTipoConta().getIdTipoConta();
					txtDepositoInicial.setEditable(false);
					if (tipo == 1) {
						ContaCorrente contaCorrente = (ContaCorrente)conta; 
						txtDepositoInicial.setText(Double.toString(contaCorrente.getDepositoInicial()));
						txtLimite.setText(Double.toString(contaCorrente.getLimite()));
					} else if (tipo == 2) {
						ContaInvestimento contaInvestimento = (ContaInvestimento)conta; 
						txtDepositoInicial.setText(Double.toString(contaInvestimento.getDepositoInicial()));
						txtDepositoMinimo.setText(Double.toString(contaInvestimento.getDepositoMinimo()));
						txtMontanteMinimo.setText(Double.toString(contaInvestimento.getMontanteMinimo()));
					}
				} else {
					inicializaCampos();
				}

				
			}
		});;
		ArrayList<Cliente> listaDeClientes = Cliente.buscarClientes();
		for (Cliente cliente : listaDeClientes) {
			comboClientes.addItem(cliente.getId());
		}

		
		comboClientes.setToolTipText("");
		comboClientes.setBounds(126, 72, 120, 22);
		contentPane.add(comboClientes);
		
		lblTipoContaSelected = new JLabel("");
		lblTipoContaSelected.setBounds(258, 108, 209, 14);
		contentPane.add(lblTipoContaSelected);

		comboTiposConta.setBounds(126, 104, 120, 22);
		contentPane.add(comboTiposConta);
		comboTiposConta.setSelectedIndex(0);
		
		inicializaCampos();
	}
	
	public void inicializaCampos() {
		int numeroConta = Conta.getNumeracaoConta();
		txtNumeroConta.setText(String.valueOf(numeroConta));
		txtNumeroConta.setEditable(false);
		txtDepositoInicial.setText("");
		txtLimite.setText("");
		txtDepositoMinimo.setText("");
		txtMontanteMinimo.setText("");
		comboTiposConta.setEnabled(true);
	}
}
