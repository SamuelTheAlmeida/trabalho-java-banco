import java.sql.*;

public class ContaCorrente extends Conta {
	private double limite;
	
	public ContaCorrente(int idConta, TipoConta tipoConta, 
		double depositoInicial, double limite) {
		super.setIdConta(idConta);
		super.setTipoConta(tipoConta);
		super.setDepositoInicial(depositoInicial);
		this.limite = limite;
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
