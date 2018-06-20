import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class ManipularContaFrame extends JFrame {

	private JPanel contentPane;
	private String cpfClienteSelecionado;
	private Cliente clienteSelecionado;
	private JLabel lblCpfDoCliente;
	private Conta contaSelecionada = null;
	private JLabel lblTipoConta;
	private JLabel lblNomeCliente;
	private JButton btnVerSaldo;
	private JButton btnSaque;
	private JButton btnRemunerar;
	private JButton btnDeposito;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManipularContaFrame frame = new ManipularContaFrame();
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
	public ManipularContaFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				dispose();
			}
			@Override
			public void windowOpened(WindowEvent e) {
				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 422, 278);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTipoConta = new JLabel("");
		lblTipoConta.setHorizontalAlignment(SwingConstants.LEFT);
		lblTipoConta.setBounds(157, 133, 233, 14);
		contentPane.add(lblTipoConta);
		
		
		lblNomeCliente = new JLabel("");
		lblNomeCliente.setHorizontalAlignment(SwingConstants.LEFT);
		lblNomeCliente.setBounds(157, 106, 233, 14);
		contentPane.add(lblNomeCliente);
		
		lblCpfDoCliente = new JLabel("CPF do Cliente");
		lblCpfDoCliente.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblCpfDoCliente.setBounds(38, 82, 107, 14);
		contentPane.add(lblCpfDoCliente);
		
		btnVerSaldo = new JButton("Saldo Atual");
		btnVerSaldo.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnVerSaldo.setBackground(new Color(255, 255, 255));
		btnVerSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double saldo = contaSelecionada.getSaldo();
				JOptionPane.showMessageDialog(getParent(), "Saldo atual é de " + saldo);
			}
		});
		btnVerSaldo.setBounds(68, 194, 123, 23);
		contentPane.add(btnVerSaldo);
		
		btnSaque = new JButton("Saque");
		btnSaque.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnSaque.setBackground(new Color(255, 255, 255));
		btnSaque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String input = JOptionPane.showInputDialog("Digite o valor a ser sacado: ");
				double valor = Double.parseDouble(input);
				try {
					contaSelecionada.saca(valor);
					JOptionPane.showMessageDialog(getParent(), "Saque efetuado com sucesso");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(getParent(), e.getMessage());
				}
			}
		});
		btnSaque.setBounds(68, 159, 123, 23);
		contentPane.add(btnSaque);
		
		btnDeposito = new JButton("Dep\u00F3sito");
		btnDeposito.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnDeposito.setBackground(new Color(255, 255, 255));
		btnDeposito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = JOptionPane.showInputDialog("Digite o valor a ser depositado: ");
				double valor = Double.parseDouble(input);
				try {
					contaSelecionada.deposita(valor);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(getParent(), ex.getMessage());
				}
			}
		});
		btnDeposito.setBounds(227, 159, 123, 23);
		contentPane.add(btnDeposito);
		

		
		btnRemunerar = new JButton("Remunerar");
		btnRemunerar.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnRemunerar.setBackground(new Color(255, 255, 255));
		btnRemunerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (contaSelecionada.getTipoConta().getIdTipoConta() == 1) {
					JOptionPane.showMessageDialog(getParent(), "A conta não é uma Conta Investimento.");
				} else {
					contaSelecionada.remunera();
				}
				
			}
		});
		btnRemunerar.setBounds(227, 194, 123, 23);
		contentPane.add(btnRemunerar);
		
		JComboBox comboCpfCliente = new JComboBox();
		comboCpfCliente.setBackground(new Color(255, 255, 255));
		comboCpfCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habilitaCampos();
				cpfClienteSelecionado = (String)comboCpfCliente.getSelectedItem();
				clienteSelecionado = Cliente.consultarCliente(cpfClienteSelecionado);
					lblNomeCliente.setText(clienteSelecionado.getNome() + " " + clienteSelecionado.getSobrenome());
					contaSelecionada = Conta.consultarConta(clienteSelecionado);
					if (contaSelecionada != null) {
						if (contaSelecionada.getTipoConta().getIdTipoConta() == 1) {
							ContaCorrente conta = (ContaCorrente)contaSelecionada;
						} else {
							ContaInvestimento conta = (ContaInvestimento)contaSelecionada;
							
						}
						lblTipoConta.setText(contaSelecionada.getTipoConta().getNomeConta());			
					} else {
						JOptionPane.showMessageDialog(getParent(), "Cliente não possui conta");
						desabilitaCampos();
					}
			}
		});
		
		ArrayList<Cliente> listaDeClientes = Cliente.buscarClientes();
		for (Cliente cliente : listaDeClientes) {
			comboCpfCliente.addItem(cliente.getCpf());
		}
		
		comboCpfCliente.setBounds(157, 73, 234, 23);
		contentPane.add(comboCpfCliente);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				comboCpfCliente.setSelectedIndex(0);
			}
		});
		

		JLabel lblGerenciarConta = new JLabel("Gerenciar Conta");
		lblGerenciarConta.setHorizontalAlignment(SwingConstants.CENTER);
		lblGerenciarConta.setFont(new Font("Franklin Gothic Book", Font.PLAIN, 24));
		lblGerenciarConta.setBounds(42, 6, 336, 54);
		contentPane.add(lblGerenciarConta);

		
	}
	
	public void habilitaCampos() {
		btnVerSaldo.setEnabled(true);
		btnDeposito.setEnabled(true);
		btnRemunerar.setEnabled(true);
		btnSaque.setEnabled(true);
	}
	
	public void desabilitaCampos() {
		btnVerSaldo.setEnabled(false);
		btnDeposito.setEnabled(false);
		btnRemunerar.setEnabled(false);
		btnSaque.setEnabled(false);
	}
}
