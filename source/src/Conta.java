import java.sql.*;

public abstract class Conta implements ContaI { // classe abstrata, conta precisa ser corrente ou investimento
    private int idConta;

    private TipoConta tipoConta; 
    // tipoConta como objeto, para facilidade no acesso do nome da conta
    
    private Cliente cliente;
    // cliente como objeto, para facilidade no acesso de seus atributos
    
    private double saldo;
    private double depositoInicial;
    
    public Conta(int idConta, TipoConta tipo, Cliente cliente, double saldo, double depositoInicial) {
    	this.idConta = idConta;
    	this.tipoConta = tipo;
    	this.cliente = cliente;
    	this.saldo = saldo;
    	this.depositoInicial = depositoInicial;
    }
    
    public Conta(int idConta, TipoConta tipoConta, double depositoInicial) {
    	this.idConta = idConta;
    	this.tipoConta = tipoConta;
    	this.depositoInicial = depositoInicial;
    }


    public boolean deposita(double valor) {
    	Connection con = Conexao.getConexaoMySQL();
        if(valor < 1) {
            // To-do: Mensagem de erro
            return false;
        } else {
            atualizaSaldo(valor, this.idConta);
            return true;
        }
    }

    public boolean saca(double valor) {
        if(valor < 1) {
            // To-do: Mensagem de erro
            return false;
        } else {
        	atualizaSaldo(valor, this.idConta);
        	return true;
        }
    }

    public int getNumero(){
        return this.idConta;
    }
    
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
    		 switch (idTipo) {
    		 case 1:
    			 conta = new ContaCorrente(idConta, tipoConta, clienteConta, saldo, depositoInicial);
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

    public boolean atualizaSaldo(double valor, int id){
    	Connection con = Conexao.getConexaoMySQL();
    	try {
            String query = "UPDATE Conta SET saldo = ? where idConta = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setDouble(1, valor);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
            return true;		
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    		e.printStackTrace();
    		return false;
    	}

    }

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
