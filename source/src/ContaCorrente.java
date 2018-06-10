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
	
    public boolean saca(Connection con, double valor) {
      valor = super.getSaldo(con) - valor;
        if (valor > limite) {
            // To-do: Mensagem de erro;
            return false;
        } else {
            super.saca(con, valor);
            // To-do: decrementa, limite - valor;
            return true;
        }
    }

    public void remunera(Connection con) {
        double saldo = getSaldo(con) + (getSaldo(con) / 100.0);
        // Aplicar remuneração de 1% ao saldo da conta.
        super.atualizaSaldo(con, saldo, super.getCliente().getId());
    }
}
