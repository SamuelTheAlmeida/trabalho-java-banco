import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TipoConta {
	private int idTipoConta;
	private String nomeConta;
	
	public static TipoConta consultarTipoConta(int id) {
    	Connection con = Conexao.getConexaoMySQL();
    	Statement stm = null;
    	TipoConta tipo = null;
    	try {
    		stm = con.createStatement();
        	ResultSet rs = stm.executeQuery("SELECT idTipoConta, nomeConta "
    				+ "FROM TiposConta WHERE idTipoConta = " + id);
        	while (rs.next()) {
        		int idTipo = rs.getInt(1);
        		String nome = rs.getString(2);
        		tipo = new TipoConta(id, nome);
        	}
    	} catch (SQLException e) {
    		System.out.println("deu ruim ao consultar cliente");
    	}
    	
    	return tipo;
	}
	
    public static ArrayList<TipoConta> buscarTiposConta() {
    	ArrayList<TipoConta> tiposConta = new ArrayList();
    	Connection con = Conexao.getConexaoMySQL();
    	try {
    		Statement stm = con.createStatement();
        	ResultSet rs = stm.executeQuery("SELECT idTipoConta, nomeConta FROM TiposConta");
        	while (rs.next()) {
        		int idTipoConta = rs.getInt(1);
        		String nomeConta = rs.getString(2);
        		TipoConta tipo = new TipoConta(idTipoConta, nomeConta);
        		tiposConta.add(tipo);
        	}
    	} catch (SQLException e) {
    		System.out.println("deu ruim ao buscar clientes");
    	}
    	
    	return tiposConta;
    }
	
	public TipoConta(int idTipoConta, String nomeConta) {
		this.idTipoConta = idTipoConta;
		this.nomeConta = nomeConta;
	}

	public int getIdTipoConta() {
		return idTipoConta;
	}

	public void setIdTipoConta(int idTipoConta) {
		this.idTipoConta = idTipoConta;
	}

	public String getNomeConta() {
		return nomeConta;
	}

	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}
	

}
