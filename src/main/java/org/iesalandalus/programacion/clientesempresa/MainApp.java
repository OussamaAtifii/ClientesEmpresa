package org.iesalandalus.programacion.clientesempresa;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.clientesempresa.modelo.negocio.Clientes;
import org.iesalandalus.programacion.clientesempresa.vista.Consola;
import org.iesalandalus.programacion.clientesempresa.vista.Opcion;

public class MainApp {
	private final static int NUM_MAX_CLIENTES = 3;
	private static Clientes clientes = new Clientes(NUM_MAX_CLIENTES);

	public static void main(String[] args) {
		Opcion opcion;
		do {
			Consola.mostrarMenu();
			opcion = Consola.elegirOpcion();
			if (opcion == Opcion.SALIR) {
				System.out.println("Que tenga un buen dia.");
			}
			ejecutarOpcion(opcion);

		} while (opcion != Opcion.SALIR);

	}

	private static void insertarCliente() {
		try {
			clientes.insertar(Consola.leerCliente());
		} catch (OperationNotSupportedException | IllegalArgumentException | DateTimeParseException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void buscarCliente() {
		Cliente cliente = null;
		try {
			cliente = Clientes.buscar(Consola.leerClienteDni());
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		if (cliente != null) {
			System.out.println(cliente);
			;
		}
	}

	private static void borrarCliente() {
		try {
			Clientes.borrar(Consola.leerClienteDni());
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void mostrarClientes() {
		try {
			Cliente[] clientes1 = clientes.get();

			for (int i = 0; i < clientes1.length; i++) {
				if (clientes1[i] != null) {
					System.out.println(clientes1[i]);
				}
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void mostrarClientesFecha() {
		try {
			LocalDate fecha = Consola.leerFechaNacimiento();

			Cliente[] clientes1 = clientes.get();

			for (int i = 0; i < clientes1.length; i++) {
				if (clientes1[i] != null && clientes1[i].getFechaNacimiento().equals(fecha)) {
					System.out.println(clientes1[i]);
				}
			}
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void ejecutarOpcion(Opcion opcion) {
		switch (opcion) {
		case INSERTAR_CLIENTE: {
			insertarCliente();
			break;
		}
		case BUSCAR_CLIENTE: {
			buscarCliente();
			break;
		}
		case BORRAR_CLIENTE: {
			borrarCliente();
			break;
		}
		case MOSTRAR_CLIENTES: {
			mostrarClientes();
			break;
		}
		case MOSTRAR_CLIENTES_FECHA: {
			mostrarClientesFecha();
			break;
		}
		case SALIR: {
			break;
		}
		}
	}
}