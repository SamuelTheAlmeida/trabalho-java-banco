public class Conta {
    public int idConta;
    public int tipoConta;
    public int idCliente;
    public double saldo;

    public boolean deposita(double valor) {
        if(valor < 1) {
            // To-do: Mensagem de erro
            return false;
        } else {
            atualizaSaldo(valor, id);
            return true;
        }
    }

    public boolean saca(double valor) {
        if(valor < 1) {
            // To-do: Mensagem de erro
            return false;
        } else {
            atualizaSaldo(valor, id);
            return true;
        }
    }

    public int getNumero(int id){
        //isso provavelmente vai vir do front, onde o cara vai selecionar qual
        //conta ele quer a partir da listagem

    }

    public Cliente getDono(){
        Statement stmt = null;
        String query = "select nome, sobrenome, rg, cpf, sexo, estado, cidade, endereco from cliente, conta where cliente.idCliente =" + idCliente;

        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        String nome = rs.getString("nome");
        String sobrenome = rs.getString("sobrenome");
        String rg = rs.getString("rg");
        String cpf = rs.getString("cpf");
        char sexo = rs.getString("sexo");
        String estado = rs.getString("estado");
        String cidade = rs.getString("cidade");
        String endereco = rs.getString("endereco");

        Cliente cliente = new Cliente(rg, cpf, sexo, estado, cidade, endereco);

        return cliente;
    }

    public boolean atualizaSaldo(double valor, int id){
      // Fazer Try-catch aqui

        String insertTableSQL = "UPDATE conta SET saldo = ? where idConta = ?";
        PreparedStatement preparedStatement = dbConnection.prepareStatement(insertTableSQL);
        preparedStatement.setDouble(1, valor);
        preparedStatement.setInt(2, id);

        preparedStatement.executeUpdate();
        return true;
    }

    public void remunera();

    public double getSaldo(){
        Statement stmt = null;
        String query = "select saldo from conta where idConta =" + id;

        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        double saldo = rs.getDouble("saldo");

        return saldo;
    }
}
