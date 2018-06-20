import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class PrincipalFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalFrame frame = new PrincipalFrame();
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
	public PrincipalFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 288);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBanco = new JLabel("FakeBank");
		lblBanco.setForeground(SystemColor.textHighlightText);
		lblBanco.setHorizontalAlignment(SwingConstants.CENTER);
		lblBanco.setFont(new Font("Impact", Font.PLAIN, 26));
		lblBanco.setBounds(33, 11, 373, 47);
		contentPane.add(lblBanco);
		
		JButton btnVisualizarAtualizarCriar = new JButton("Gerenciar Clientes");
		btnVisualizarAtualizarCriar.setBackground(new Color(255, 255, 255));
		btnVisualizarAtualizarCriar.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnVisualizarAtualizarCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ClientesFrame().setVisible(true);
			}
		});
		btnVisualizarAtualizarCriar.setBounds(48, 83, 358, 37);
		contentPane.add(btnVisualizarAtualizarCriar);
		
		JButton btnVisualizarCriarOu = new JButton("Visualizar, Criar ou Editar uma Conta");
		btnVisualizarCriarOu.setBackground(new Color(255, 255, 255));
		btnVisualizarCriarOu.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnVisualizarCriarOu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VincularContaFrame().setVisible(true);
			}
		});
		btnVisualizarCriarOu.setBounds(48, 131, 358, 37);
		contentPane.add(btnVisualizarCriarOu);
		
		JButton btnVerSaldoSacar = new JButton("Fazer opera\u00E7\u00F5es na Conta");
		btnVerSaldoSacar.setBackground(new Color(255, 255, 255));
		btnVerSaldoSacar.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnVerSaldoSacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManipularContaFrame().setVisible(true);
			}
		});
		btnVerSaldoSacar.setBounds(48, 179, 358, 37);
		contentPane.add(btnVerSaldoSacar);
	}
}
