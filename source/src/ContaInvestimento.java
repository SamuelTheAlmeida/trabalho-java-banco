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
	
    public boolean deposita(Connection con, double valor) {
        valor = valor + super.getSaldo(con);

        if (valor >= depositoMinimo) {
            super.deposita(con, valor);

            return true;
        } else {
            // To-do: mensagem de erro;

            return false;
        }
    }


    public boolean saca(Connection con, double valor) {
        valor = super.getSaldo(con) - valor;

        if (valor >= montanteMinimo) {
            super.saca(con, valor);

            return true;
        } else {
            // To-do: mensagem de erro;

            return false;
        }
    }

    public void remunera(Connection con) {
        double saldo = getSaldo(con) + ((getSaldo(con) / 100.0) * 2);
        super.atualizaSaldo(con, saldo, super.getCliente().getId());
        // Aplicar remuneração de 2% ao saldo da conta.
    }
}
