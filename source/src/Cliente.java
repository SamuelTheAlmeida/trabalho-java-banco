public class Cliente {

    private char(11) cpf;
    private char(9) rg;
    private String endereco;

    public insereCliente(String nome, char(11) cpf, char(9) rg, String endereco, int tipoConta){
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;

        if(tipoConta == 0){
          Conta conta = new ContaCorrente();
        }else{
          Conta conta = new ContaInvestimento();
        }
    }

    public atualizaCliente(){

    }

    public consultaCliente() {

    }

    public excluiCliente() {

    }
}
