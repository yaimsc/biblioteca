package vista;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;


import modelo.Usuario;
import modelo.UsuarioModelo;

public class UsuarioVista {
	
	final static int INSERTAR = 1; 
	final static int LISTAR = 2; 
	final static int ACTUALIZAR = 3; 
	final static int  ELIMINAR = 4; 
	final static int SALIR = 0; 

	public void menuUsuario(){
		UsuarioModelo usuarioModelo = new UsuarioModelo(); 
		Scanner lector = new Scanner (System.in); 
		int opcion; 
		
		do{
			System.out.println("------------MENU-----------");
			System.out.println(INSERTAR + " insertar usuario");
			System.out.println(LISTAR + " listar usuarios"); 
			
			opcion = Integer.parseInt(lector.nextLine());
			
			switch (opcion) {
			
			case INSERTAR:
				
				Usuario usuario = new Usuario(); 
				
				System.out.println("Dime un nombre de usuario");
				String nombre = lector.nextLine(); 
				System.out.println("Dime un apellido de usuario");
				String apellido = lector.nextLine(); 
				System.out.println("Dime una edad");
				int edad = Integer.parseInt(lector.nextLine()); 
				System.out.println("Dime el DNI");
				String dni = lector.nextLine();
				System.out.println("Dime la fecha de nacimiento"); 
				String fecha = lector.nextLine(); 
				
				try {
					
				SimpleDateFormat formato = new SimpleDateFormat("dd-mm-yyyy"); //PARA PONER LA FECHA
				
				
				usuario.setNombre(nombre);
				usuario.setApellido(apellido);
				usuario.setEdad(edad);
				usuario.setDni(dni);
				usuario.setFecha_nac(formato.parse(fecha)); // PARA PASAR LA FECHA DE STRING A DATE
				
				usuarioModelo.insert(usuario);
				
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break; 

			case LISTAR: 
				
				ArrayList<Usuario> usuarios = usuarioModelo.selectAll();  
				this.mostrarUsuarios(usuarios);
				break;	
				
			default:
				break;
			}
		}while( opcion != SALIR); 
		
		
	}

	
	public void mostrarUsuarios(ArrayList<Usuario> usuarios){
		Iterator<Usuario> i = usuarios.iterator(); 
		while(i.hasNext()){
			Usuario usuario = i.next();
			System.out.println(usuario.getNombre() + " " + 
			usuario.getApellido() + " " + 
			usuario.getEdad() + " " + 
			usuario.getDni() + " " + usuario.getFecha_nac());
		}
	}
}
