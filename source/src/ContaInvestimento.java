public class ContaInvestimento extends Conta {
    public boolean deposita(double valor) {
        valor = valor + super.getSaldo();

        if (valor >= depositoMinimo) {
            super.deposita(valor);

            return true;
        } else {
            // To-do: mensagem de erro;

            return false;
        }
    }


    public boolean saca(double valor) {
        valor = super.getSaldo() - valor;

        if (valor >= montanteMinimo) {
            super.saca(valor);

            return true;
        } else {
            // To-do: mensagem de erro;

            return false;
        }
    }

    public void remunera() {
        double saldo = getSaldo() + ((getSaldo() / 100.0) * 2);
        super.atualizaSaldo(saldo, idCliente);
        // Aplicar remuneração de 2% ao saldo da conta.
    }
}
