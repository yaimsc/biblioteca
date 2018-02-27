package comparador;

import java.util.Comparator;

import modelo.Prestamo;

public class NombreComparator<T> implements Comparator<Prestamo>{

	@Override
	public int compare(Prestamo o1, Prestamo o2) {
		
		return o1.getUsuario().getNombre().compareToIgnoreCase(o2.getUsuario().getNombre());
	}

}
