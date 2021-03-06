import javax.swing.table.AbstractTableModel;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

// Tablemodel ainda sem utiliza��o do BD
// Modificar as fun��es de adicionar, remover e atualizar para utiliza��o do Banco de Dados

public class ModeloCliente extends AbstractTableModel {
	private List<Cliente> clientes = Cliente.buscarClientes();
	private static int qtdColunas = 10; // num de colunas da tabela cliente
	
	public Cliente getCliente(int row) {
		return clientes.get(row);
		
	}
	
	public void ordenaNome() {
		Collections.sort(clientes, new NomeComparator());
		fireTableDataChanged();
	}
	
	public void ordenaSobrenome() {
		Collections.sort(clientes, new SobrenomeComparator());
		fireTableDataChanged();
	}
	
	@Override
	public int getColumnCount() {
		return qtdColunas;
	}

	@Override
	public int getRowCount() {
		return clientes.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Cliente cliente = clientes.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return cliente.getId();
		case 1:
			return cliente.getNome();
		case 2:
			return cliente.getSobrenome();
		case 3:
			return cliente.getSexo();
		case 4:
			return cliente.getCpf();
		case 5:
			return cliente.getRg();
		case 6:
			return cliente.getEstado();
		case 7:
			return cliente.getCidade();
		case 8:
			return cliente.getEndereco();
		case 9:
			return cliente.getIdConta();
		default:
			return "";
		}
	}
	
	public void adicionar(Cliente cliente) {
		Cliente.adicionarCliente(cliente);
		clientes.add(cliente);
		fireTableRowsInserted(clientes.size()-1, clientes.size()-1);
	}
	
	public void remover(Cliente cliente) {
		System.out.println("idConta: " + cliente.getIdConta());
		if (cliente.getIdConta() > 0) {
			System.out.println("Cliente com conta.");
			Conta conta = Conta.consultarConta(cliente);
			System.out.println("Conta id: " + conta.getNumero());
			if (conta.getTipoConta().getIdTipoConta() == 1) {
				System.out.println("Tipo conta 1: " + conta.getTipoConta().getIdTipoConta());
				ContaCorrente.excluirContaCorrente(cliente.getIdConta());
			} else if (conta.getTipoConta().getIdTipoConta() == 2) {
				System.out.println("Tipo conta 2: " + conta.getTipoConta().getIdTipoConta());
				ContaInvestimento.excluirContaInvestimento(cliente.getIdConta());
			}
		}
		System.out.println("Chamando excluirCliente");
		Cliente.excluirCliente(cliente.getId());
		clientes.remove(cliente);
		fireTableRowsDeleted(clientes.size()-1, clientes.size()-1);
	}
	
	public void atualizar(int index, Cliente cliente) {
		Cliente.atualizarCliente(cliente);
		clientes.set(index, cliente);
		fireTableRowsUpdated(clientes.size()-1, clientes.size()-1);
		fireTableDataChanged();
	}
	
	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "ID";
		case 1:
			return "Nome";
		case 2:
			return "Sobrenome";
		case 3:
			return "Sexo";
		case 4:
			return "CPF";
		case 5:
			return "RG";
		case 6:
			return "Estado";
		case 7:
			return "Cidade";
		case 8:
			return "Endereco";
		case 9:
			return "idConta";
		default:
			return "";
		}
	}

}
