package vista;

import java.util.Scanner;

public class BibliotecaVista {
	
	final static int GESTIONAR_USUARIOS = 1; 
	final static int GESTIONAR_LIBROS = 2; 
	final static int GESTIONAR_PRESTAMOS = 3; 
	final static int  SALIR = 0; 
	
	public void menuBiblioteca(){
	
		int opcion; 
		Scanner lector = new Scanner(System.in); 
		
		do{
			System.out.println("------------BIBLIOTECA--------");
			System.out.println(GESTIONAR_USUARIOS + " gestionar usuarios");
			System.out.println(GESTIONAR_LIBROS + " gestionar libros");
			System.out.println(GESTIONAR_PRESTAMOS + " gestionar prestamos");
			System.out.println(SALIR + " salir");
		
		opcion = Integer.parseInt(lector.nextLine()); 
		
		switch (opcion) {
		case GESTIONAR_USUARIOS:
			UsuarioVista usuarioVista = new UsuarioVista(); 
			usuarioVista.menuUsuario();
			break;
		
		case GESTIONAR_LIBROS:
			LibroVista libroVista = new LibroVista(); 
			libroVista.menuLibro();
			
		case GESTIONAR_PRESTAMOS:
			PrestamoVista prestamoVista = new PrestamoVista(); 
			prestamoVista.menuPrestamo();
			
		case SALIR: 
			System.out.println("Saliendo...");

		default:
			break;
			
		}
		
		
	}while(opcion != SALIR); 
		
	}

}
