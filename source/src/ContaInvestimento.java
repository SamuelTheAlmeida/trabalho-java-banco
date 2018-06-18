import java.sql.*;

public class ContaInvestimento extends Conta {
	private double montanteMinimo;
	private double depositoMinimo;
	

	   /** 
	    * Construtor incompleto da classe
	    */  
	public ContaInvestimento(int idConta, TipoConta tipoConta, Cliente clienteConta, double montanteMinimo, 
			double depositoMinimo, double depositoInicial) {
		super(idConta, tipoConta, clienteConta, depositoInicial);
		this.montanteMinimo = montanteMinimo;
		this.depositoMinimo = depositoMinimo;
	}
	
	   /** 
	    * Construtor completo
	    */  
	public ContaInvestimento(int idConta, TipoConta tipoConta, Cliente clienteConta, double saldo, double depositoInicial) {
		super(idConta, tipoConta, clienteConta, saldo, depositoInicial);
	}
	
	   /** 
	    * M�todo para criar uma Conta Investimento no Banco de Dados
	    * @param c Objeto conta investimento
	    */  
	public static void CriaContaInvestimento(ContaInvestimento c) {
	Connection con = Conexao.getConexaoMySQL();
	try {
		PreparedStatement stm = con.prepareStatement("INSERT INTO Conta(idConta, tipoConta, cliente, depositoInicial) "
				+ "VALUES (?, ?, ?, ?)");
		stm.setInt(1, c.getNumero());
		stm.setInt(2, c.getTipoConta().getIdTipoConta());
		stm.setInt(3, c.getCliente().getId());
		stm.setDouble(4, c.getDepositoInicial());
		stm.executeUpdate();
		stm = con.prepareStatement("INSERT INTO ContaInvestimento(idConta, montanteMinimo, depositoMinimo)"
				+ "VALUES(?, ?, ?)");
		stm.setInt(1, c.getNumero());
		stm.setDouble(2, c.getMontanteMinimo());
		stm.setDouble(3, c.getDepositoMinimo());
		stm.executeUpdate();
	} catch (SQLException e) {
		System.out.println("erro ao criar conta investimento no BD");
		e.printStackTrace();
	}
}
	   /** 
	    * M�todo de dep�sito sobrescrevendo o m�todo da classe pai
	    * @param valor do dep�sito
	    * @return true ou exce��o para tratamento de erro espec�fico
	    */  
	@Override
    public boolean deposita(double valor) {
    	Connection con = Conexao.getConexaoMySQL();
        valor = valor + super.getSaldo();
        if (valor >= depositoMinimo) {
            super.deposita(valor);
            return true;
        } else {
            throw new RuntimeException("Valor abaixo do dep�sito m�nimo da conta");
        }
    }

	   /** 
	    * M�todo de saque sobrescrevendo o m�todo da classe pai
	    * @param valor do saque
	    * @return true ou exce��o para tratamento de erro espec�fico
	    */  
	@Override
	public boolean saca(double valor) {
		double valorFinal = getSaldo()-valor;
		if (valorFinal < montanteMinimo) {
			throw new RuntimeException("Valor ap�s o saque nao pode ser menor que o montante minimo");
		}
		super.saca(valor);
		return true;
	}
	
	   /** 
	    * M�todo de remunera��o da conta conforme regra
	    */  
	@Override
	public void remunera() {
		double novoValor = getSaldo()*1.02;
		atualizaSaldo(novoValor, super.getNumero());
	}
    
	/* getters e setters */

	@Override
	public Cliente getDono() {
		return super.getDono();
	}

	@Override
	public int getNumero() {
		return super.getNumero();
	}

	@Override
	public double getSaldo() {
		return super.getSaldo();
	}

	public double getMontanteMinimo() {
		return montanteMinimo;
	}

	public double getDepositoMinimo() {
		return depositoMinimo;
	}

}
