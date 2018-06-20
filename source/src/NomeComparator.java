import java.util.Comparator;

public class NomeComparator implements Comparator<Cliente> {

	@Override
	public int compare(Cliente a, Cliente b) {
		 return a.getNome().compareToIgnoreCase(b.getNome());
	}

}
