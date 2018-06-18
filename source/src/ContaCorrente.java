import java.sql.*;

public class ContaCorrente extends Conta {
	private double limite;
	
	public double getLimite() {
		return limite;
	}

	public ContaCorrente(int idConta, TipoConta tipoConta, 
		double depositoInicial, double limite) {
		super(idConta, tipoConta, depositoInicial);
		this.limite = limite;
	}
	
	public ContaCorrente(int idConta, TipoConta tipoConta, Cliente clienteConta, double saldo, double depositoInicial) {
		super(idConta, tipoConta, clienteConta, saldo, depositoInicial);
	}
	
	public static void CriaContaCorrente(ContaCorrente c) {
	Connection con = Conexao.getConexaoMySQL();
	try {
		PreparedStatement stm = con.prepareStatement("INSERT INTO Conta(idConta, tipoConta, cliente, depositoInicial) "
				+ "VALUES (?, ?, ?, ?)");
		stm.setInt(1, c.getNumero());
		stm.setInt(2, c.getTipoConta().getIdTipoConta());
		stm.setInt(3, c.getCliente().getId());
		stm.setDouble(4, c.getDepositoInicial());
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



	@Override
    public boolean saca(double valor) {
    	Connection con = Conexao.getConexaoMySQL();
    	valor = super.getSaldo() - valor;
        if (valor > limite) {
            // To-do: Mensagem de erro;
            return false;
        } else {
            super.saca(valor);
            // To-do: decrementa, limite - valor;
            return true;
        }
    }
    
    @Override
    public void remunera() {
    	Connection con = Conexao.getConexaoMySQL();
        double saldo = getSaldo() + (getSaldo() / 100.0);
        // Aplicar remuneração de 1% ao saldo da conta.
        super.atualizaSaldo(saldo, super.getCliente().getId());
    }

	@Override
	public boolean deposita(double valor) {
		return super.deposita(valor);
	}

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

}
