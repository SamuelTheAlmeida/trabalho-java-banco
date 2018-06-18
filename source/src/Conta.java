import java.sql.*;

public abstract class Conta implements ContaI { // classe abstrata, conta precisa ser corrente ou investimento
    private int idConta;

    private TipoConta tipoConta; 
    // tipoConta como objeto, para facilidade no acesso do nome da conta
    
    private Cliente cliente;
    // cliente como objeto, para facilidade no acesso de seus atributos
    
    private double saldo;
    private double depositoInicial;
    
	   /** 
	    * Construtor de uma conta com saldo definido
	    */
    public Conta(int idConta, TipoConta tipo, Cliente cliente, double saldo, double depositoInicial) {
    	this.idConta = idConta;
    	this.tipoConta = tipo;
    	this.cliente = cliente;
    	this.saldo = saldo;
    	this.depositoInicial = depositoInicial;
    }
   
	   /** 
	    * Construtor de uma conta sem saldo definido
	    */
    public Conta(int idConta, TipoConta tipoConta, Cliente clienteConta, double depositoInicial) {
    	this.idConta = idConta;
    	this.tipoConta = tipoConta;
    	this.cliente = clienteConta;
    	this.depositoInicial = depositoInicial;
    }


	   /** 
	    * Método de depósito (sobrescrito nas classes filhas CC e CI)
	    * @param valor do depósito
	    * @return true ou joga exceção (para tratamento de erro específico)
	    */
    public boolean deposita(double valor) {
        if(valor < 1) {
        	throw new RuntimeException("Valor do deposito precisa ser positivo");
        } else {
            atualizaSaldo(valor+getSaldo(), this.idConta);
            return true;
        }
    }

	   /** 
	    * Método de saque (sobrescrito nas classes filhas CC e CI)
	    * @param valor do saque
	    * @return true ou joga exceção (para tratamento de erro específico)
	    */    
    public boolean saca(double valor) {
    	double valorFinal = getSaldo()-valor;
        if(valor < 1) {
        	throw new RuntimeException("Valor do saque precisa ser positivo");
        } else {
            atualizaSaldo(valorFinal, this.idConta);
            return true;
        }
    }

    public int getNumero(){
        return this.idConta;
    }
    
	   /** 
	    * Método para obter o próximo valor de número de conta disponível para uso
	    * @return inteiro com o número da conta
	    */  
    public static int getNumeracaoConta() {
    	Connection con = Conexao.getConexaoMySQL();
    	int numeracaoConta = 0;
    	try {
        	Statement stm = con.createStatement();
        	ResultSet rs = stm.executeQuery("SELECT MAX(idConta) FROM Conta");
        	while (rs.next()) {
        		numeracaoConta = rs.getInt(1)+1;
        	}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return numeracaoConta;
    } 
    
    public void setDono(Cliente cliente) {
    	this.cliente = cliente;
    }
    
	   /** 
	    * Método para consultar o dono da conta
	    * @return Objeto cliente vinculado à conta
	    */  
    public Cliente getDono(){
    	Connection con = Conexao.getConexaoMySQL();
        Statement stmt = null;
        int idCliente = cliente.getId();
        String query = "select nome, sobrenome, rg, cpf, sexo, estado, cidade, endereco from cliente, conta where cliente.idCliente =" + idCliente;

        try {
	        stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        String nome = rs.getString("nome");
	        String sobrenome = rs.getString("sobrenome");
	        String rg = rs.getString("rg");
	        String cpf = rs.getString("cpf");
	        char sexo = rs.getString("sexo").charAt(0);
	        String estado = rs.getString("estado");
	        String cidade = rs.getString("cidade");
	        String endereco = rs.getString("endereco");
	        Cliente cliente = new Cliente(nome, sobrenome, sexo, cpf, rg, 
	        		estado, cidade, endereco);
	        
	        return cliente;
        } catch (SQLException e) {
        	return null;
        }
    }
    
	   /** 
	    * Método para consultar uma conta específica através de um cliente
	    * @param cliente para consultar
	    * @return Objeto conta
	    */  
    public static Conta consultarConta(Cliente cliente) {
    	Connection con = Conexao.getConexaoMySQL();
		 int idConta = 0;
		 int idTipo = 0;
		 TipoConta tipoConta = null;
		 int idCliente = 0;
		 Cliente clienteConta = null;
		 double saldo = 0;
		 double depositoInicial = 0;
		 Conta conta = null;
		 double limite = 0;
		 
    	try {
    		Statement stm = con.createStatement();
    		ResultSet rs = stm.executeQuery("SELECT idConta, tipoConta, cliente, saldo, depositoInicial"
    				+ " FROM Conta WHERE cliente = " + cliente.getId());
    		 while (rs.next()) {
    			 idConta = rs.getInt(1);
    			 idTipo = rs.getInt(2);
    			 tipoConta = TipoConta.consultarTipoConta(idTipo);
    			 idCliente = rs.getInt(3);
    			 clienteConta = Cliente.consultarCliente(idCliente);
    			 saldo = rs.getDouble(4);
    			 depositoInicial = rs.getDouble(5);
    		 }
     		stm = con.createStatement();
     		rs = stm.executeQuery("SELECT limite FROM ContaCorrente WHERE idConta = " + cliente.getId());
     		 while (rs.next()) {
     			 limite = rs.getInt(1);
     		 }
    		 switch (idTipo) {
    		 case 1:
    			 conta = new ContaCorrente(idConta, tipoConta, clienteConta, depositoInicial, limite);
    			 break;
    		 case 2:
    			 conta = new ContaInvestimento(idConta, tipoConta, clienteConta, saldo, depositoInicial);
    			 break;
    		 }
    	} catch (SQLException e) {
    		System.out.println("Erro ao consultar cliente: " + e.getMessage());
    	}
    	return conta;
    }

	   /** 
	    * Método para atualização de saldo (utilizado em saque, depósito, remuneração)
	    * @param valor do saque
	    * @param id da Conta
	    * @return true ou false de acordo com o retorno da query
	    */  
    public boolean atualizaSaldo(double valor, int id){
    	Connection con = Conexao.getConexaoMySQL();
    	try {
            PreparedStatement stm = con.prepareStatement("UPDATE Conta SET saldo = ? where idConta = ?");
            stm.setDouble(1, valor);
            stm.setInt(2, id);
            stm.executeUpdate();
            this.saldo = valor;
            return true;		
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    		e.printStackTrace();
    		return false;
    	}
    }

	   /** 
	    * Método para obter o saldo atual no banco de dados
	    * @return valor do saldo
	    */  
    public double getSaldo() {
    	Connection con = Conexao.getConexaoMySQL();
    	double saldo = -1;
    	try {
            Statement stmt = null;
            String query = "select saldo from conta where idConta =" + this.cliente.getId();
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
            	saldo = rs.getDouble("saldo");
            }
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return saldo;  
    }
    
    /* getters e setters */
    
    public Cliente getCliente() {
    	return this.cliente;
    }

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}
	
	public double getDepositoInicial() {
		return this.depositoInicial;
	}
	
	public void setDepositoInicial(double depositoInicial) {
		this.depositoInicial = depositoInicial;
	}
    
}
