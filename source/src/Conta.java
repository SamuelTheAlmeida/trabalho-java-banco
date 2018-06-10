import java.sql.*;

public abstract class Conta { // classe abstrata, conta precisa ser corrente ou investimento
    private int idConta;

    private TipoConta tipoConta; 
    // tipoConta como objeto, para facilidade no acesso do nome da conta
    
    private Cliente cliente;
    // cliente como objeto, para facilidade no acesso de seus atributos
    
    private double saldo;
    private double depositoInicial;
    

    public boolean deposita(Connection con, double valor) {
        if(valor < 1) {
            // To-do: Mensagem de erro
            return false;
        } else {
            atualizaSaldo(con, valor, this.idConta);
            return true;
        }
    }

    public boolean saca(Connection con, double valor) {
        if(valor < 1) {
            // To-do: Mensagem de erro
            return false;
        } else {
            atualizaSaldo(con, valor, this.idConta);
            return true;
        }
    }

    public int getNumero(int id){
        //isso provavelmente vai vir do front, onde o cara vai selecionar qual
        //conta ele quer a partir da listagem
    	return 0;
    }

    public Cliente getDono(Connection con){
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

    public boolean atualizaSaldo(Connection con, double valor, int id){
      // Fazer Try-catch aqui
    	try {
            String insertTableSQL = "UPDATE conta SET saldo = ? where idConta = ?";
            PreparedStatement preparedStatement = con.prepareStatement(insertTableSQL);
            preparedStatement.setDouble(1, valor);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
            return true;		
    	} catch (SQLException e) {
    		return false;
    	}

    }

    // método da conta investimento
    //public abstract void remunera();

    public double getSaldo(Connection con){
    	try {
            Statement stmt = null;
            String query = "select saldo from conta where idConta =" + this.cliente.getId();

            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            double saldo = rs.getDouble("saldo");
            return saldo;    		
    	} catch (SQLException e) {
    		return -1;
    	}
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
	
	public void setDepositoInicial(double depositoInicial) {
		this.depositoInicial = depositoInicial;
	}
    
}
