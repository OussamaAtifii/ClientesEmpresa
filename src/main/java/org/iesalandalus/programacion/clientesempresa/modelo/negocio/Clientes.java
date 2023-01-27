package org.iesalandalus.programacion.clientesempresa.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;

public class Clientes {
	private static int capacidad;
	private static int tamano;
	private static Cliente[] coleccionClientes;

	public Clientes(int capacidad) {
		if (capacidad <= 0) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		Clientes.capacidad = capacidad;
		coleccionClientes = new Cliente[capacidad];
		tamano = 0;

	}

	private static boolean capacidadSuperada(int indice) {
		return capacidad >= indice;

	}

	private static boolean tamanoSuperado(int indice) {
		return indice <= tamano;

	}

	private static int buscarIndice(Cliente cliente) {
		int indice = tamano + 1;
		for (int i = 0; i < coleccionClientes.length; i++) {
			if (coleccionClientes[i] != null && coleccionClientes[i].equals(cliente)) {
				indice = i;
				break;
			}
		}
		return indice;
	}

	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		}
		int indice = buscarIndice(cliente);

		if (!capacidadSuperada(indice)) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más clientes.");
		}

		for (int i = 0; i < coleccionClientes.length; i++) {
			if (coleccionClientes[i] == null) {
				coleccionClientes[i] = new Cliente(cliente);
				tamano++;
				break;
			}
			if (coleccionClientes[i].equals(cliente)) {
				throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese dni.");
			}
		}
	}

	public static Cliente buscar(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No es posible buscar un cliente nulo.");
		}

		Cliente cliente1 = null;
		for (int i = 0; i < coleccionClientes.length; i++) {
			if (coleccionClientes[i].equals(cliente)) {
				cliente1 = new Cliente(coleccionClientes[i]);
				break;
			}
		}
		return cliente1;
	}

	private static void desplazarUnaPosicionHaciaIzquierda(int indice) {
		for (int i = indice; i < coleccionClientes.length - 1 && coleccionClientes[i] != null; i++) {
			coleccionClientes[i] = coleccionClientes[i + 1];
		}
	}

	public static void borrar(Cliente cliente) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		}

		boolean comprobador = false;
		for (int i = 0; i < coleccionClientes.length; i++) {
			if (coleccionClientes[i] != null && coleccionClientes[i].equals(cliente)) {
				comprobador = true;
				coleccionClientes[i] = null;
				desplazarUnaPosicionHaciaIzquierda(i);
				tamano--;
				break;
			}
		}
		if (comprobador == false) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente como el indicado.");
		}

	}

	public Cliente[] get() {
		return copiaProfundaClientes();

	}

	private static Cliente[] copiaProfundaClientes() {
		Cliente[] copiaClientes = new Cliente[capacidad];
		if (coleccionClientes[0] == null) {
			throw new NullPointerException("ERROR: No existen clientes aún.");
		}

		for (int i = 0; i < coleccionClientes.length; i++) {
			if (coleccionClientes[i] != null) {
				copiaClientes[i] = new Cliente(coleccionClientes[i]);
			}
		}
		return copiaClientes;

	}

	// Getters
	public int getCapacidad() {
		return capacidad;
	}

	public int getTamano() {
		return tamano;
	}
}