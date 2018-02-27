package vista;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import comparador.FechaComparator;
import comparador.NombreComparator;
import comparador.TituloComparator;
import modelo.Conector;
import modelo.Libro;
import modelo.LibroModelo;
import modelo.Prestamo;
import modelo.PrestamoModelo;
import modelo.Usuario;
import modelo.UsuarioModelo;

public class PrestamoVista extends Conector{
	
	final static int PRESTAR = 1; 
	final static int ENTREGAR = 2; 
	final static int LISTAR = 	3; 
	final static int SALIR = 0; 
	
		final static int TITULO = 1; 
		final static int NOMBRE = 2; 
		final static int FECHA_INICIO = 3; 
		final static int SALLIR = 0; 

		public void menuPrestamo(){
			
			int opcion; 
			Scanner lector = new Scanner (System.in); 
			
			do{ //crear menu
				System.out.println("--------PRESTAMOS-----------");
				System.out.println(PRESTAR + " tomar por prestado un libro"); 
				System.out.println(ENTREGAR + " entregar un libro "); 
				System.out.println(LISTAR + " listar prestamos");
				
				opcion = Integer.parseInt(lector.nextLine()); 
				
				
				switch (opcion) {
				case PRESTAR:
					realizarPrestamo(lector); 
					break;

				case ENTREGAR: 
					entregarLibro(lector); 
					break; 
					
				case LISTAR: 
					PrestamoModelo prestamoModelo = new PrestamoModelo(); 
					ArrayList<Prestamo> prestamos = prestamoModelo.selectAll(); 
					listarPrestamo(prestamos); 
					menuOrdenar(); 
					break; 
					
				case SALIR:
					System.out.println("Saliendo.."); 
					break; 
					
				default:
					break;
				}
				
			}while(opcion != SALIR); 
		}
		
		public void menuOrdenar(){
			
			int opcion; 
			Scanner lector = new Scanner (System.in);  
			
			do{
				System.out.println("--Ordenar--"); 
				System.out.print(TITULO + " titulo ");
				System.out.print(NOMBRE + " nombre ");
				System.out.println(FECHA_INICIO + " fecha_inicio");
				
				opcion = Integer.parseInt(lector.nextLine()); 
				
				//seleccionar todos los prestamos
					PrestamoModelo prestamoModelo = new PrestamoModelo(); 
					ArrayList<Prestamo> prestamos = prestamoModelo.selectAll(); 
					
					
				switch (opcion) {
				case TITULO:		
				//comparar alfabeticamente 
					TituloComparator<Prestamo> tc = new TituloComparator(); 
					prestamos.sort(tc);
					listarPrestamo(prestamos); 
					break;

				case NOMBRE: 
					
					//selecciuonar todos los usuarios
					
					NombreComparator<Prestamo> nc = new NombreComparator(); 
					prestamos.sort(nc); 

					listarPrestamo(prestamos); 
					break; 
					
				case FECHA_INICIO: 
					
					FechaComparator fc = new FechaComparator(); 
					prestamos.sort(fc);
					
					listarPrestamo(prestamos); 
				
					
				default:
					break;
				}
				
			}while( opcion != SALIR); 
		}
		

		public void realizarPrestamo(Scanner lector){
			
			System.out.println("Introduce el titulo del libro");
			String titulo = lector.nextLine(); 
			
			LibroModelo libroModelo = new LibroModelo();  //el modelo sabe acceder a la base de datos, y c on esto se pueden ejecutar los metodos de su clase
			Libro libro = libroModelo.select(titulo); 
			
			if(libro != null){ //si libro no es null es que existe
				System.out.println("Introduce el dni");
				String dni = lector.nextLine(); 
				
				//ceramos un modelo de usuario para acceder al usuario
				UsuarioModelo usuarioModelo = new UsuarioModelo(); 
				Usuario usuario = usuarioModelo.selectPorDNI(dni); 
				
				
				Date fechaIni = new Date(); 
				
				Calendar calendario = Calendar.getInstance(); 
				calendario.setTime(fechaIni);
				calendario.add(Calendar.DATE, 21);
				Date fechaFin = calendario.getTime(); 
				
				

				//crear el objeto prestamo
				Prestamo prestamo = new Prestamo(); 
				
				
				//insertar todos los dtaos en prestamo
				
				
				prestamo.setLibro(libroModelo.select(libro.getId())); 
				prestamo.setUsuario(usuarioModelo.select(usuario.getId()));
				prestamo.setFechaIni(fechaIni);
				prestamo.setFechaFin(fechaFin);
				prestamo.setEntregado(false);				
				
				//crear el objeto modelo prestamo
				//insertar prestamo utilizando modelo prestamo
				
				PrestamoModelo prestamoModelo = new PrestamoModelo(); 
				
				prestamoModelo.insert(prestamo);
				
				System.out.println("Prestamo realizado");
			}else{ //si no existe
				System.out.println("Prestamo no realizado"); 
			}
		}
		
		
		private void entregarLibro(Scanner lector) {
			
			//pidiendole el dni
			
			System.out.println("Introduce el dni"); 
			String dni = lector.nextLine(); 
			
			//conseguir usuario
			UsuarioModelo usuarioModelo =  new UsuarioModelo(); 
			Usuario usuario = usuarioModelo.selectPorDNI(dni); 
		
			//pedir titulo
			System.out.println("Introduce el titulo del libro"); 
			String titulo = lector.nextLine(); 
				
			//conseguir libro
			LibroModelo libroModelo = new LibroModelo();
			Libro libro = libroModelo.select(titulo);
			
			//seleccionarPrestamo
			
			PrestamoModelo prestamoModelo = new PrestamoModelo(); 
			Prestamo prestamo = prestamoModelo.selectPorLibroUsuario(libro, usuario);
			
			//cambiar objeto prestamo a entregado
			
			if(prestamo != null){
				prestamo.setEntregado(true);
			}
			
			//hacer el update
			
			prestamoModelo.update(prestamo);
			
			System.out.println("El libro " + libro.getTitulo() + " ha sido entregado ");
			
			
		}
		
		public void listarPrestamo(ArrayList<Prestamo> prestamos){
			//recorrer el array y listar los prestamos
			Iterator<Prestamo> i = prestamos.iterator(); 
			while(i.hasNext()){
				Prestamo prestamo = i.next();
				
				System.out.print(prestamo.getId() + " " + prestamo.getLibro().getTitulo() + " - " + prestamo.getLibro().getAutor() + " " + prestamo.getUsuario().getNombre() + " " 
				+ prestamo.getUsuario().getApellido() + " " + prestamo.getFechaIni() + " " +  
						prestamo.getFechaFin() + " = ");
					
				if(prestamo.isEntregado() == true){
					System.out.println("Entregado");
				}else{
					System.out.println("No entregado");
				}
				
			}
		}
}
