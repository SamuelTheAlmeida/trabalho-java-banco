public class ContaCorrente extends Conta {
    public boolean saca(double valor) {
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

    public void remunera() {
        double saldo = getSaldo() + (getSaldo() / 100.0);
        // Aplicar remuneração de 1% ao saldo da conta.
        super.atualizaSaldo(saldo, idCliente);
    }
}
