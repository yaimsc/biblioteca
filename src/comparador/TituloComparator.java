package comparador;

import java.util.Comparator;

import modelo.Prestamo;

public class TituloComparator<T> implements Comparator<Prestamo>{

	@Override
	public int compare(Prestamo p1, Prestamo p2) {
		
		return p1.getLibro().getTitulo().compareToIgnoreCase(p2.getLibro().getTitulo());
	}

}
