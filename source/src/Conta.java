public class Conta {

    public boolean deposita(double valor) {
        if(valor < 1) {
            // To-do: Mensagem de erro
            return false;
        } else {
            // To-do: Incrementa, conta.saldo + valor
            return true;
        }
    }

    public boolean saca(double valor) {
        if(valor < 1) {
            // To-do: Mensagem de erro
            return false;
        } else {
            // To-do: Reduz, conta.saldo - valor
            return true;
        }
    }
}
