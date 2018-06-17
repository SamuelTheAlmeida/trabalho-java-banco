import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ManipularContaFrame extends JFrame {

	private JPanel contentPane;

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
		
		JLabel lblGerenciarConta = new JLabel("Gerenciar Conta");
		lblGerenciarConta.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblGerenciarConta.setBounds(137, 11, 123, 54);
		contentPane.add(lblGerenciarConta);
		
		JComboBox comboCpfCliente = new JComboBox();
		comboCpfCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		ArrayList<Cliente> listaDeClientes = Cliente.buscarClientes();
		for (Cliente cliente : listaDeClientes) {
			comboCpfCliente.addItem(cliente.getCpf());
		}
		
		comboCpfCliente.setBounds(136, 76, 234, 20);
		contentPane.add(comboCpfCliente);
		
		JLabel lblCpfDoCliente = new JLabel("CPF do Cliente");
		lblCpfDoCliente.setBounds(49, 79, 107, 14);
		contentPane.add(lblCpfDoCliente);
		
		JButton btnSaque = new JButton("Saque");
		btnSaque.setBounds(102, 138, 89, 23);
		contentPane.add(btnSaque);
		
		JButton btnDeposito = new JButton("Dep\u00F3sito");
		btnDeposito.setBounds(227, 138, 89, 23);
		contentPane.add(btnDeposito);
		
		JButton btnVerSaldo = new JButton("Saldo Atual");
		btnVerSaldo.setBounds(102, 172, 89, 23);
		contentPane.add(btnVerSaldo);
		
		JButton btnRemunerar = new JButton("Remunerar");
		btnRemunerar.setBounds(227, 172, 89, 23);
		contentPane.add(btnRemunerar);
	}
}
