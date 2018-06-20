import java.util.Comparator;

public class SobrenomeComparator implements Comparator<Cliente> {

	@Override
	public int compare(Cliente a, Cliente b) {
		 return a.getSobrenome().compareToIgnoreCase(b.getSobrenome());
	}

}
