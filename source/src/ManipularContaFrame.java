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

public class ManipularContaFrame extends JFrame {

	private JPanel contentPane;
	private String cpfClienteSelecionado;
	private Cliente clienteSelecionado;
	private JLabel lblCpfDoCliente;
	private Conta contaSelecionada = null;
	private JLabel lblTipoConta;
	private JLabel lblNomeCliente;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 422, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTipoConta = new JLabel("");
		lblTipoConta.setBounds(137, 132, 233, 14);
		contentPane.add(lblTipoConta);
		
		
		lblNomeCliente = new JLabel("");
		lblNomeCliente.setBounds(137, 107, 233, 14);
		contentPane.add(lblNomeCliente);
		
		lblCpfDoCliente = new JLabel("CPF do Cliente");
		lblCpfDoCliente.setBounds(49, 79, 107, 14);
		contentPane.add(lblCpfDoCliente);
		
		JComboBox comboCpfCliente = new JComboBox();
		comboCpfCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cpfClienteSelecionado = (String)comboCpfCliente.getSelectedItem();
				clienteSelecionado = Cliente.consultarCliente(cpfClienteSelecionado);
				lblNomeCliente.setText(clienteSelecionado.getNome() + " " + clienteSelecionado.getSobrenome());
				contaSelecionada = Conta.consultarConta(clienteSelecionado);
				if (contaSelecionada.getTipoConta().getIdTipoConta() == 1) {
					ContaCorrente conta = (ContaCorrente)contaSelecionada;
				} else {
					ContaInvestimento conta = (ContaInvestimento)contaSelecionada;
					
				}
				lblTipoConta.setText(contaSelecionada.getTipoConta().getNomeConta());
			}
		});
		
		ArrayList<Cliente> listaDeClientes = Cliente.buscarClientes();
		for (Cliente cliente : listaDeClientes) {
			comboCpfCliente.addItem(cliente.getCpf());
		}
		
		comboCpfCliente.setBounds(136, 76, 234, 20);
		contentPane.add(comboCpfCliente);
		

		
		JButton btnSaque = new JButton("Saque");
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
		btnSaque.setBounds(68, 163, 123, 23);
		contentPane.add(btnSaque);
		
		JButton btnDeposito = new JButton("Dep\u00F3sito");
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
		btnDeposito.setBounds(227, 163, 123, 23);
		contentPane.add(btnDeposito);
		

		
		JButton btnRemunerar = new JButton("Remunerar");
		btnRemunerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (contaSelecionada.getTipoConta().getIdTipoConta() == 1) {
					JOptionPane.showMessageDialog(getParent(), "A conta não é uma Conta Investimento.");
				} else {
					contaSelecionada.remunera();
				}
				
			}
		});
		btnRemunerar.setBounds(227, 197, 123, 23);
		contentPane.add(btnRemunerar);
		

		
		JLabel lblGerenciarConta = new JLabel("Gerenciar Conta");
		lblGerenciarConta.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblGerenciarConta.setBounds(154, 11, 123, 54);
		contentPane.add(lblGerenciarConta);
		

		JButton btnVerSaldo = new JButton("Saldo Atual");
		btnVerSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double saldo = contaSelecionada.getSaldo();
				JOptionPane.showMessageDialog(getParent(), "Saldo atual é de " + saldo);
			}
		});
		btnVerSaldo.setBounds(68, 197, 123, 23);
		contentPane.add(btnVerSaldo);
		
	}
}
