package co.edu.unbosque.model;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

public class Llamada {

	public enum Tipo {
		LOCAL, LARGA_DISTANCIA, CELULAR
	}

	private final Tipo TIPO;
	private Instant inicio;
	private Instant fin;
	private int opcionUno;
	private int opcionDos;

	public Tipo getTIPO() {
		return TIPO;
	}

	public Instant getInicio() {
		return inicio;
	}

	public void setInicioPublic(Instant inicio) {
		this.inicio = inicio;
	}

	public Instant getFin() {
		return fin;
	}

	public void setFinPublic(Instant fin) {
		this.fin = fin;
	}

	public int getOpcionUno() {
		return opcionUno;
	}

	public void setOpcionUno(int opcionUno) {
		this.opcionUno = opcionUno;
	}

	public int getOpcionDos() {
		return opcionDos;
	}

	public void setOpcionDos(int opcionDos) {
		this.opcionDos = opcionDos;
	}

	public Llamada(Tipo tipo) {
		this.TIPO = Objects.requireNonNull(tipo);
	}

	public void comenzarLlamada() {
		if (this.inicio == null) {
			this.inicio = Instant.now();
		}
	}

	public void terminarLlamada() {
		if (this.inicio != null && this.fin == null) {
			this.fin = Instant.now();
		}
	}

	public void setInicio(Instant inicio) {
		this.inicio = inicio;
	}

	public void setFin(Instant fin) {
		this.fin = fin;
	}

	public Duration obtenerDuracion() {
		if (inicio == null || fin == null)
			return null;
		return Duration.between(inicio, fin);
	}

	public int getMinutosParaCobro() {
		Duration d = obtenerDuracion();
		if (d == null)
			return 0;
		long segundos = d.getSeconds();
		if (segundos <= 0)
			return 0;
		long minutos = (segundos + 59) / 60;
		return (int) minutos;
	}

	// Getters y Setters generados

	@Override
	public String toString() {
		return "Llamada{" + "tipo=" + TIPO + ", inicio=" + inicio + ", fin=" + fin + ", minutos="
				+ getMinutosParaCobro() + '}';
	}
}
