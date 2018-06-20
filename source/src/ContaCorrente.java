import java.sql.*;

public class ContaCorrente extends Conta {
	private double limite;
	

	   /** 
	    * Construtor completo da classe
	    */  
	public ContaCorrente(int idConta, TipoConta tipoConta, Cliente clienteConta, double depositoInicial, double limite) {
		super(idConta, tipoConta, clienteConta, depositoInicial);
		this.limite = limite;
	}
	
	   /** 
	    * M�todo para cria��o de uma conta corrente no Banco de Dados
	    * @param c Objeto conta corrente
	    */  
	public static void CriaContaCorrente(ContaCorrente c) {
	Connection con = Conexao.getConexaoMySQL();
	try {
		PreparedStatement stm = con.prepareStatement("INSERT INTO Conta(idConta, tipoConta, cliente, depositoInicial, saldo) "
				+ "VALUES (?, ?, ?, ?, ?)");
		stm.setInt(1, c.getNumero());
		stm.setInt(2, c.getTipoConta().getIdTipoConta());
		stm.setInt(3, c.getCliente().getId());
		stm.setDouble(4, c.getDepositoInicial());
		stm.setDouble(5, c.getDepositoInicial());
		stm.executeUpdate();
		stm = con.prepareStatement("INSERT INTO ContaCorrente(idConta, limite)"
				+ "VALUES(?, ?)");
		stm.setInt(1, c.getNumero());
		stm.setDouble(2, c.getLimite());
		stm.executeUpdate();
	} catch (SQLException e) {
		System.out.println("erro ao criar conta corrente no BD");
		e.printStackTrace();
	}
}
	
    public static void excluirContaCorrente(int id) {
    	Connection con = Conexao.getConexaoMySQL();
    	try {
        	PreparedStatement stm = con.prepareStatement("DELETE FROM ContaCorrente WHERE idConta = ?");	
        	stm.setInt(1, id);
        	stm.executeUpdate();
        	stm = con.prepareStatement("DELETE FROM Conta WHERE idConta = ?");
        	stm.setInt(1, id);
        	stm.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
	
    
	   /** 
	    * M�todo para atualiza��o de uma contaCorrente no Banco de Dados. Recebe um obj contaCorrente e substitui no banco.
	    * @param contaCorrente
	    */
	public static void atualizarContaCorrente(ContaCorrente contaCorrente){
		Connection con = Conexao.getConexaoMySQL();
		try {
	  	PreparedStatement stm = con.prepareStatement("UPDATE ContaCorrente SET limite = ?  WHERE idConta = ?");	
	  	stm.setDouble(1, contaCorrente.getLimite());
	  	stm.setInt(2, contaCorrente.getNumero());
	  	stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


   /** 
    * M�todo de saque espec�fico da conta corrente
    * @param valor do saque
    * @return true ou exce��o para tratamento de erro espec�fico
    */  
	@Override
	public boolean saca(double valor) {
		double valorFinal = getSaldo()-valor;
		if (valorFinal < limite) {
			throw new RuntimeException("Valor ap�s o saque n�o pode ser menor que o limite da conta.");
		}
		super.saca(valor);
		return true;
	}
	
   /** 
    * M�todo de remunera��o, n�o utilizado na classe CC por�m obrigat�rio implementar por conta da interface ContaI
    */  
    @Override
    public void remunera() {
    	// m�todo vazio
    }

    /** 
     * M�todo de dep�sito. Sem regra espec�fica, chama o m�todo da classe pai
     */  
	@Override
	public boolean deposita(double valor) {
		return super.deposita(valor);
	}

	
	/* Getters e setters */
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
	
	public double getLimite() {
		return limite;
	}
	


}
