package org.iesalandalus.programacion.clientesempresa.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.StringTokenizer;

public class Cliente {
	private final String ER_CORREO = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private final String ER_DNI = "[0-9]{8}[A-Za-z]";
	private final String ER_TELEFONO = "^[6789]\\d{8}$";
	public final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String nombre;
	private String dni;
	private String correo;
	private String telefono;
	private LocalDate fechaNacimiento;

	// Metodo para formatear nombre.
	private String formateaNombre(String nombre) {
		nombre = nombre.trim();
		String[] arrayNombre = nombre.toLowerCase().split("\\s+");
		String letra;
		String nombreFormateado = "";
		String minus;
		String palabra;
		String resultado;

		for (int i = 0; i < arrayNombre.length; i++) {
			palabra = arrayNombre[i];
			letra = palabra.charAt(0) + "";
			minus = palabra.substring(1, arrayNombre[i].length());
			resultado = letra.toUpperCase() + minus + " ";
			nombreFormateado = nombreFormateado + resultado;
		}
		return nombreFormateado;
	}

	// Metodo para comprobar la letra del DNI
	private static boolean comprobarLetraDni(String dni) {
		boolean dniCorrecto;
		String letrasValidas = "TRWAGMYFPDXBNJZSQVHLCKE";
		char letra = Character.toUpperCase(dni.charAt(8));

		if (letra == letrasValidas.charAt(Integer.parseInt(dni.substring(0, 8)) % 23)) {
			dniCorrecto = true;
		} else {
			dniCorrecto = false;
		}
		return dniCorrecto;
	}

	// Constructor con parámetros
	public Cliente(String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
		setNombre(nombre);
		setDni(dni);
		setCorreo(correo);
		setTelefono(telefono);
		setFechaNacimiento(fechaNacimiento);
	}

	// Constructor copia
	public Cliente(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		} else {
			setNombre(cliente.getNombre());
			setDni(cliente.getDni());
			setCorreo(cliente.getCorreo());
			setTelefono(cliente.getTelefono());
			setFechaNacimiento(cliente.getFechaNacimiento());
		}
	}

	// Metodo para obtener las iniciales.
	private String getIniciales() {
		String inicialesNombre = "";
		String separarInicialies = "";
		String iniciales = "";
		StringTokenizer separacionNombre = new StringTokenizer(nombre);

		while (separacionNombre.hasMoreTokens()) {
			separarInicialies = separacionNombre.nextToken();
			inicialesNombre = separarInicialies.substring(0, 1);
			iniciales = iniciales + inicialesNombre;
		}
		return iniciales;
	}

	// Metodo to string que devuelve toda la informacion del cliente
	@Override
	public String toString() {
		return "nombre=" + nombre + "(" + getIniciales() + ")" + ", DNI=" + dni + ", correo=" + correo + ", teléfono="
				+ telefono + ", fecha nacimiento=" + fechaNacimiento.format(FORMATO_FECHA);
	}

	// HashCode y equals
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}

	// GETTERS Y SETTERS DE NOMBRE
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre de un cliente no puede ser nulo.");
		}
		if (nombre.trim().isEmpty()) {
			throw new IllegalArgumentException("ERROR: El nombre de un cliente no puede estar vacío.");
		}
		nombre = formateaNombre(nombre);
		this.nombre = nombre;

	}

	// GETTERS Y SETTERS DE DNI
	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if (dni == null) {
			throw new NullPointerException("ERROR: El dni de un cliente no puede ser nulo.");
		}
		if (dni.trim().isEmpty() || !dni.matches(ER_DNI)) {
			throw new IllegalArgumentException("ERROR: El dni del cliente no tiene un formato válido.");
		}
		if (!comprobarLetraDni(dni)) {
			throw new IllegalArgumentException("ERROR: La letra del dni del cliente no es correcta.");
		}
		this.dni = dni.toUpperCase();

	}

	// GETTERS Y SETTERS DE CORREO
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		if (correo == null) {
			throw new NullPointerException("ERROR: El correo de un cliente no puede ser nulo.");
		}
		if (!correo.matches(ER_CORREO)) {
			throw new IllegalArgumentException("ERROR: El correo del cliente no tiene un formato válido.");
		}
		this.correo = correo;
	}

	// GETTERS Y SETTERS DE TELEFONO
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono == null) {
			throw new NullPointerException("ERROR: El teléfono de un cliente no puede ser nulo.");
		}
		if (!telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("ERROR: El teléfono del cliente no tiene un formato válido.");
		}
		this.telefono = telefono;

	}

	// GETTERS Y SETTERS DE FECHA NACIMIENTO
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		if (fechaNacimiento == null) {
			throw new NullPointerException("ERROR: La fecha de nacimiento de un cliente no puede ser nula.");
		}

		if ((fechaNacimiento.getYear() > 2023)) {
			if (fechaNacimiento.getMonthValue() > 2) {
				throw new IllegalArgumentException(
						"ERROR: El cliente no puede haber nacido despues de febrero del 2023.");
			}
		}

		this.fechaNacimiento = fechaNacimiento;

	}

}