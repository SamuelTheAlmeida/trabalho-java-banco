import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;

// Tablemodel ainda sem utilização do BD
// Modificar as funções de adicionar, remover e atualizar para utilização do Banco de Dados

public class ModeloCliente extends AbstractTableModel {
	private List<Cliente> clientes = new ArrayList();
	
	public Cliente getCliente(int row) {
		return clientes.get(row);
	}
	
	@Override
	public int getColumnCount() {
		return 6;
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
			return cliente.getCpf();
		case 4:
			return cliente.getRg();
		case 5:
			return cliente.getEndereco();
		default:
			return "";
		}
	}
	
	public void adicionar(Cliente cliente) {
		clientes.add(cliente);
		fireTableRowsInserted(clientes.size()-1, clientes.size()-1);
	}
	
	public void remover(Cliente cliente) {
		clientes.remove(cliente);
		fireTableRowsDeleted(clientes.size()-1, clientes.size()-1);
	}
	
	public void atualizar(int index, Cliente cliente) {
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
			return "CPF";
		case 4:
			return "RG";
		case 5:
			return "Endereco";
		default:
			return "";
		}
	}

}
