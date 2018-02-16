package vista;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import modelo.Libro;
import modelo.LibroModelo;

public class LibroVista {

	final static int INSERTAR = 1; 
	final static int LISTAR = 2; 
	final static int MODIFICAR = 3;
	final static int ELIMINAR = 4; 
	final static int SALIR = 0; 
	
	public void menuLibro(){
		
		LibroModelo libroModelo = new LibroModelo(); 
		Scanner lector = new Scanner (System.in);
		int opcion; 
		
		do{
			
			System.out.println("-------------MENU----------");
			System.out.println( INSERTAR + " insertar libro");
			System.out.println(LISTAR + " listar libros");
			System.out.println(MODIFICAR + " modificar libros");
			
			opcion = Integer.parseInt(lector.nextLine());
			
			switch (opcion) {
			
			case INSERTAR:
				
				Libro libro = new Libro(); 
				
				System.out.println("Insterte un titulo"); 
				String titulo = lector.nextLine(); 
				System.out.println("Inserte autor");
				String autor = lector.nextLine();
				
				libro.setTitulo(titulo);
				libro.setAutor(autor);
				
				libroModelo.insert(libro);
				
				break;
				
			case LISTAR: 
				
				ArrayList <Libro> libros = libroModelo.selectAll(); 
				this.mostrarLibros(libros);
				
				break; 

			case MODIFICAR: 
				
				ArrayList <Libro> libros1 = libroModelo.selectAll(); 
				this.mostrarLibros(libros1); 
				
				System.out.println("Dime el id del libro que quieres modificar");
				
				int id = lector.nextInt(); 
				
				libroModelo.update(id); 
				
				this.mostrarLibros(libros1);
				
				break; 
				
			case ELIMINAR: 
				
				ArrayList <Libro> libros2 = libroModelo.selectAll(); 
				mostrarLibros(libros2);
				
				System.out.println("Libro a eliminar, selecciona id");
				
				
				break; 
				
				
			default:
				break;
			}
			
		}while( opcion != SALIR);
	}
	
	public void mostrarLibros(ArrayList<Libro> libros){
		Iterator<Libro> i = libros.iterator();
		while(i.hasNext()){
			Libro libro = i.next();
			System.out.println(libro.getTitulo() + " " + libro.getAutor());
		}
		
		
		
	}
}
