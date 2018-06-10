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
    

    // construtor do Cliente. Como terá a tela para vincular um cliente a uma conta, 
    //	não é necessário ainda definir tipoConta
    public Cliente(String nome, String sobrenome, char sexo, String cpf, String rg, 
    		String estado, String cidade, String endereco){
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.cpf = cpf;
        this.rg = rg;
        this.estado = estado;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    public void atualizaCliente(){

    }

    public void consultaCliente() {

    }

    public void excluiCliente() {

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
    
    
    
    
}
