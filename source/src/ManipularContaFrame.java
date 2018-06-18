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
	private Conta contaSelecionada;

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
		
		lblCpfDoCliente = new JLabel("CPF do Cliente");
		lblCpfDoCliente.setBounds(49, 79, 107, 14);
		contentPane.add(lblCpfDoCliente);
		
		JButton btnSaque = new JButton("Saque");
		btnSaque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String input = JOptionPane.showInputDialog("Digite o valor a ser sacado: ");
				double valor = Double.parseDouble(input);
				//a
				contaSelecionada.saca(valor);
			}
		});
		btnSaque.setBounds(68, 138, 123, 23);
		contentPane.add(btnSaque);
		
		JButton btnDeposito = new JButton("Dep\u00F3sito");
		btnDeposito.setBounds(227, 138, 123, 23);
		contentPane.add(btnDeposito);
		
		JButton btnVerSaldo = new JButton("Saldo Atual");
		btnVerSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double saldo = contaSelecionada.getSaldo();
				JOptionPane.showMessageDialog(getParent(), "Saldo atual é de " + saldo);
			}
		});
		btnVerSaldo.setBounds(68, 172, 123, 23);
		contentPane.add(btnVerSaldo);
		
		JButton btnRemunerar = new JButton("Remunerar");
		btnRemunerar.setBounds(227, 172, 123, 23);
		contentPane.add(btnRemunerar);
		
		JLabel lblNomeCliente = new JLabel("");
		lblNomeCliente.setBounds(137, 107, 233, 14);
		contentPane.add(lblNomeCliente);
		
		JLabel lblGerenciarConta = new JLabel("Gerenciar Conta");
		lblGerenciarConta.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblGerenciarConta.setBounds(154, 11, 123, 54);
		contentPane.add(lblGerenciarConta);
		
		JComboBox comboCpfCliente = new JComboBox();
		comboCpfCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cpfClienteSelecionado = (String)comboCpfCliente.getSelectedItem();
				clienteSelecionado = Cliente.consultarCliente(cpfClienteSelecionado);
				lblNomeCliente.setText(clienteSelecionado.getNome() + " " + clienteSelecionado.getSobrenome());
				contaSelecionada = Conta.consultarConta(clienteSelecionado);
			}
		});
		
		ArrayList<Cliente> listaDeClientes = Cliente.buscarClientes();
		for (Cliente cliente : listaDeClientes) {
			comboCpfCliente.addItem(cliente.getCpf());
		}
		
		comboCpfCliente.setBounds(136, 76, 234, 20);
		contentPane.add(comboCpfCliente);
		

	}
}
