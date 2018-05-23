public class ContaInvestimento extends Conta {
    public boolean deposita(double valor) {
        if (valor >= depositoMinimo) {
            super.deposita(valor);

            return true;
        } else {
            // To-do: mensagem de erro;

            return false;
        }
    }


    public boolean saca(double valor) {
        if (valor >= montanteMinimo) {
            super.saca(valor);

            return true;
        } else {
            // To-do: mensagem de erro;

            return false;
        }
    }

    public void remunera() {
        // Aplicar remuneração de 2% ao saldo da conta.
    }
}
