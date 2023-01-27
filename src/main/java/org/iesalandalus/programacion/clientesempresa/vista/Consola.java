package org.iesalandalus.programacion.clientesempresa.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	public Consola() {

	}

	public static void mostrarMenu() {
		System.out.println("------------------------------");
		System.out.println("1.- Insertar cliente.");
		System.out.println("2.- Buscar cliente");
		System.out.println("3.- Borrar cliente");
		System.out.println("4.- Mostrar todos los clientes");
		System.out.println("5.- Mostrar clientes fecha exacta");
		System.out.println("6.- Salir");
	}

	public static Opcion elegirOpcion() {
		int opcionElegida;
		Opcion opcion = null;
		do {
			System.out.println("------------------------------");
			System.out.println("Elige una de las siguientes opciones:");
			opcionElegida = Entrada.entero();
		} while (opcionElegida < 1 || opcionElegida > 6);

		switch (opcionElegida) {
		case 1: {
			opcion = Opcion.INSERTAR_CLIENTE;
			break;
		}
		case 2: {
			opcion = Opcion.BUSCAR_CLIENTE;
			break;
		}
		case 3: {
			opcion = Opcion.BORRAR_CLIENTE;
			break;
		}
		case 4: {
			opcion = Opcion.MOSTRAR_CLIENTES;
			break;
		}
		case 5: {
			opcion = Opcion.MOSTRAR_CLIENTES_FECHA;
			break;
		}
		case 6: {
			opcion = Opcion.SALIR;
			break;
		}
		}

		return opcion;

	}

	public static Cliente leerCliente() {
		String ER_FECHA = "^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})$";
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNacimiento;
		String nombre = "";
		String dni = "";
		String correo = "";
		String telefono = "";
		String fecha;

		System.out.println("Introduzca el nombre del cliente: ");
		nombre = Entrada.cadena();
		System.out.println("Introduzca el dni del cliente: ");
		dni = Entrada.cadena();
		System.out.println("Introduzca el correo del cliente: ");
		correo = Entrada.cadena();
		System.out.println("Introduzca el telefono del cliente: ");
		telefono = Entrada.cadena();
		System.out.println("Introduzca la fecha del cliente: ");
		fecha = Entrada.cadena();

		if (!fecha.matches(ER_FECHA)) {
			throw new IllegalArgumentException("ERROR: La fecha introducida no tiene un formato válido.");
		}

		fechaNacimiento = LocalDate.parse(fecha, formato);
		return new Cliente(nombre, dni, correo, telefono, fechaNacimiento);
	}

	public static Cliente leerClienteDni() {
		String dni;
		Cliente cliente;

		System.out.println("Introduzca el dni del cliente:");
		dni = Entrada.cadena();
		dni = dni.toUpperCase();
		cliente = new Cliente("gandalf", dni, "oussama@gmail.com", "666666666", LocalDate.now());

		return cliente;
	}

	public static LocalDate leerFechaNacimiento() {
		String ER_FECHA = "^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})$";
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fecha = "";
		do {
			System.out.println("Introduzca la fecha del cliente: ");
			fecha = Entrada.cadena();
		} while (!fecha.matches(ER_FECHA));

		LocalDate fechaNacimiento = LocalDate.parse(fecha, formato);

		return fechaNacimiento;
	}

}
