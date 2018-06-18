import java.sql.*;

public class ContaInvestimento extends Conta {
	private double montanteMinimo;
	private double depositoMinimo;
	
	public double getMontanteMinimo() {
		return montanteMinimo;
	}

	public double getDepositoMinimo() {
		return depositoMinimo;
	}

	public ContaInvestimento(int idConta, TipoConta tipoConta, double montanteMinimo, 
			double depositoMinimo, double depositoInicial) {
		super(idConta, tipoConta, depositoInicial);
		this.montanteMinimo = montanteMinimo;
		this.depositoMinimo = depositoMinimo;
	}
	
	public ContaInvestimento(int idConta, TipoConta tipoConta, Cliente clienteConta, double saldo, double depositoInicial) {
		super(idConta, tipoConta, clienteConta, saldo, depositoInicial);
	}
	
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
	
	@Override
    public boolean deposita(double valor) {
    	Connection con = Conexao.getConexaoMySQL();
        valor = valor + super.getSaldo();

        if (valor >= depositoMinimo) {
            super.deposita(valor);

            return true;
        } else {
            // To-do: mensagem de erro;

            return false;
        }
    }

    @Override
    public boolean saca(double valor) {
    	Connection con = Conexao.getConexaoMySQL();
        valor = super.getSaldo() - valor;
        if (valor >= montanteMinimo) {
            super.saca(valor);

            return true;
        } else {
            // To-do: mensagem de erro;

            return false;
        }
    }

    @Override
    public void remunera() {
    	Connection con = Conexao.getConexaoMySQL();
        double saldo = getSaldo() + ((getSaldo() / 100.0) * 2);
        super.atualizaSaldo(saldo, super.getCliente().getId());
        // Aplicar remuneração de 2% ao saldo da conta.
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
