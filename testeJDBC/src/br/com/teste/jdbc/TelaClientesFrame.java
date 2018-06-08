package br.com.teste.jdbc;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaClientesFrame extends JFrame {

	private JPanel contentPane;
	private static Connection conn = Conexao.getConexaoMySQL();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaClientesFrame frame = new TelaClientesFrame();
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
	public TelaClientesFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtrTeste = new JTextArea();
		txtrTeste.setText("");
		txtrTeste.setBounds(40, 21, 368, 171);
		contentPane.add(txtrTeste);
		
		JButton btnNewButton = new JButton("Carregar Clientes");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "SELECT * FROM Cliente";
				try {
					java.sql.Statement st = conn.createStatement();
					java.sql.ResultSet rs = st.executeQuery(query);
					while (rs.next()) {
						int idCliente = rs.getInt("idCliente");
						String nome = rs.getString("nome");
						String sobrenome = rs.getString("sobrenome");
						txtrTeste.append(idCliente + " - " + nome + " " + sobrenome + "\n");
					}
					st.close();
					
				} catch(SQLException exc) {
					System.out.println("erro: " + exc.getMessage());
				}
			}
		});
		btnNewButton.setBounds(120, 213, 196, 30);
		contentPane.add(btnNewButton);
	}

}
