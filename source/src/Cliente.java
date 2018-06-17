import java.sql.*;
import java.util.*;

public class Cliente {
	private int id;
	private String nome;
	private String sobrenome;
	private char sexo;
    private String cpf;
    private String rg;
    private String estado;
    private String cidade;
    private String endereco;
    

	   /** 
	    * Construtor para criação do cliente sem ID
	    */
    public Cliente(String nome, String sobrenome, char sexo, String cpf, String rg, 
    		String estado, String cidade, String endereco){
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.rg = rg;
        this.cpf = cpf;
        this.estado = estado;
        this.cidade = cidade;
        this.endereco = endereco;
    }
    
	   /** 
	    * Construtor para criação do cliente com ID
	    */
    public Cliente(int id, String nome, String sobrenome, char sexo, String cpf, String rg, 
    		String estado, String cidade, String endereco){
    	this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.rg = rg;
        this.cpf = cpf;
        this.estado = estado;
        this.cidade = cidade;
        this.endereco = endereco;
    }
    
	   /** 
	    * Método para buscar todos os clientes cadastrados no BD. 
	    * @return Retorna um ArrayList contendo todos os clientes.
	    */
    public static ArrayList<Cliente> buscarClientes() {
    	ArrayList<Cliente> clientes = new ArrayList();
    	Connection con = Conexao.getConexaoMySQL();
    	try {
    		Statement stm = con.createStatement();
        	ResultSet rs = stm.executeQuery("SELECT idCliente, nome, sobrenome, sexo, rg, cpf, estado, cidade, endereco FROM Cliente");
        	while (rs.next()) {
        		int id = rs.getInt(1);
        		String nome = rs.getString(2);
        		String sobrenome = rs.getString(3);
        		char sexo = rs.getString(4).charAt(0);
        		String rg = rs.getString(5);
        		String cpf = rs.getString(6); 
        		String estado = rs.getString(7);
        		String cidade = rs.getString(8);
        		String endereco = rs.getString(9);
        		Cliente c = new Cliente(id, nome, sobrenome, sexo, rg, cpf, estado, cidade, endereco);
        		clientes.add(c);
        	}
    	} catch (SQLException e) {
    		System.out.println("deu ruim ao buscar clientes");
    	}
    	
    	return clientes;
    }
    
	   /** 
	    * Método para adicionar um cliente no Banco de Dados. Recebe um obj cliente e insere no banco.
	    * @param cliente
	    */
    public static void adicionarCliente(Cliente cliente) {
		Connection con = Conexao.getConexaoMySQL();
		int id = 0;
        String nome = cliente.getNome();
        String sobrenome = cliente.getSobrenome();
        char sexo = cliente.getSexo();
        String rg = cliente.getRg();
        String cpf = cliente.getCpf();
        String estado = cliente.getEstado();
        String cidade = cliente.getCidade();
        String endereco = cliente.getEndereco();
        PreparedStatement stm = null;

        try {
        	Statement stmId = con.createStatement();
        	ResultSet rs = stmId.executeQuery("SELECT MAX(idCliente) AS id FROM Cliente");
        	while (rs.next()) {
        		id = rs.getInt("id")+1;
        	}
        	cliente.setId(id);
        	
            stm = con.prepareStatement("INSERT INTO Cliente VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
            stm.setInt(1, id);
            stm.setString(2, nome);
            stm.setString(3, sobrenome);
            stm.setString(4, rg);
            stm.setString(5, cpf);
            stm.setString(6, String.valueOf(sexo));
            stm.setString(7, estado);
            stm.setString(8, cidade);
            stm.setString(9, endereco);
            stm.executeUpdate();
            System.out.println("top");
        } catch (SQLException e) {
        	System.out.println("deu ruim");
        	System.out.println(e.getMessage());
        	e.printStackTrace();
        	System.out.println(stm);
        }
    }
    
	   /** 
	    * Método para atualização de um cliente no Banco de Dados. Recebe um obj cliente e substitui no banco.
	    * @param cliente
	    */
    public static void atualizarCliente(Cliente cliente){
    	Connection con = Conexao.getConexaoMySQL();
    	try {
        	PreparedStatement stm = con.prepareStatement("UPDATE Cliente SET nome = ?, sobrenome = ?, sexo = ?,"
        			+ " rg = ?, cpf = ?, estado = ?, cidade = ?, endereco = ? WHERE idCliente = ?");	
        	stm.setString(1, cliente.getNome());
        	stm.setString(2, cliente.getSobrenome());
        	stm.setString(3, String.valueOf(cliente.getSexo()));
        	stm.setString(4, cliente.getRg());
        	stm.setString(5, cliente.getCpf());
        	stm.setString(6, cliente.getEstado());
        	stm.setString(7, cliente.getCidade());
        	stm.setString(8, cliente.getEndereco());
        	stm.setInt(9, cliente.getId());
        	stm.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}

    }

    public void consultarCliente() {

    }

	   /** 
	    * Método para excluir um cliente do Banco de Dados, através de seu ID.
	    * @param
	    */
    public static void excluirCliente(int id) {
    	Connection con = Conexao.getConexaoMySQL();
    	try {
        	PreparedStatement stm = con.prepareStatement("DELETE FROM Cliente WHERE idCliente = ?");	
        	stm.setInt(1, id);
        	stm.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public int getId() {
    	return this.id;
    }

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public char getSexo() {
		return sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}

	public String getEstado() {
		return estado;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEndereco() {
		return endereco;
	}
	
	public void setId(int id) {
		this.id = id;
	}
    
    
    
    
}
