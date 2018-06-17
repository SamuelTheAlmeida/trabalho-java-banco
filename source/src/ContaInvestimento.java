import java.sql.*;

public class ContaInvestimento extends Conta {
	private double montanteMinimo;
	private double depositoMinimo;
	
	
	public ContaInvestimento(int idConta, TipoConta tipoConta, double montanteMinimo, 
			double depositoMinimo, double depositoInicial) {
		super.setIdConta(idConta);
		super.setTipoConta(tipoConta);
		super.setDepositoInicial(depositoInicial);
		this.montanteMinimo = montanteMinimo;
		this.depositoMinimo = depositoMinimo;
		
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
