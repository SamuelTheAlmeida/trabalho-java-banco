public class ContaCorrente extends Conta {
    public boolean saca(double valor) {
        if (valor > limite) {
            // To-do: Mensagem de erro;
            return false;
        } else {
            // To-do: decrementa, limite - valor;
            return true;
        }
    }

    public void remunera() {
        // Aplicar remuneração de 1% ao saldo da conta.
    }
}
