package co.edu.unbosque.model;

import java.time.Instant;

public class LogiComm {

	public static final int T_LOCAL = 1;
	public static final int T_LARGA_DISTANCIA = 2;
	public static final int T_CELULAR = 3;

	private LineaTelefonica lineaLocal;
	private LineaTelefonica lineaNacional;
	private LineaTelefonica lineaCorporativa;

	public LogiComm() {
	}

	public void configurarLineas(int numeroLocal, int minutosLocal, int numeroNacional, int minutosNacional,
			int numeroCorporativa, int minutosCorporativa) {
		lineaLocal = new LineaTelefonica(numeroLocal, minutosLocal, LineaTelefonica.TipoLinea.LOCAL);
		lineaNacional = new LineaTelefonica(numeroNacional, minutosNacional, LineaTelefonica.TipoLinea.NACIONAL);
		lineaCorporativa = new LineaTelefonica(numeroCorporativa, minutosCorporativa,
				LineaTelefonica.TipoLinea.CORPORATIVA);
	}

	/**
	 * Registra una llamada y devuelve datos en crudo separados por comas:
	 * numeroLinea,tipo,minutos,costo Si no se puede registrar (por opción inválida
	 * o tipo inválido) devuelve cadena vacía.
	 */
	public String registrarLlamadaPorDuracion(int opcionLinea, int tipoCodigo, int duracionMinutos) {
		// normalizar duración
		int dur = Math.max(0, duracionMinutos);

		LineaTelefonica linea = switch (opcionLinea) {
		case 1 -> lineaLocal;
		case 2 -> lineaNacional;
		case 3 -> lineaCorporativa;
		default -> null;
		};
		if (linea == null)
			return "";

		Llamada.Tipo tipo = switch (tipoCodigo) {
		case T_LOCAL -> Llamada.Tipo.LOCAL;
		case T_LARGA_DISTANCIA -> Llamada.Tipo.LARGA_DISTANCIA;
		case T_CELULAR -> Llamada.Tipo.CELULAR;
		default -> null;
		};
		if (tipo == null)
			return "";

		// construir Llamada con instantes simples
		Instant inicio = Instant.now();
		Instant fin = inicio.plusSeconds((long) dur * 60L);

		Llamada llamada = new Llamada(tipo);
		llamada.setInicio(inicio);
		llamada.setFin(fin);

		int minutos = llamada.getMinutosParaCobro();
		long costo = linea.aplicarLlamada(minutos, tipo);

		// devolver datos crudos: numeroLinea,tipo,minutos,costo
		return linea.getNumeroLinea() + "," + tipo + "," + minutos + "," + costo;
	}

	public String resumenLineas() {
		StringBuilder sb = new StringBuilder();
		if (lineaLocal != null)
			sb.append(lineaLocal.toString()).append(System.lineSeparator());
		if (lineaNacional != null)
			sb.append(lineaNacional.toString()).append(System.lineSeparator());
		if (lineaCorporativa != null)
			sb.append(lineaCorporativa.toString()).append(System.lineSeparator());
		return sb.toString();
	}

	public String detalleLineaPorOpcion(int opcionLinea) {
		LineaTelefonica linea = switch (opcionLinea) {
		case 1 -> lineaLocal;
		case 2 -> lineaNacional;
		case 3 -> lineaCorporativa;
		default -> null;
		};
		if (linea == null)
			return "";
		StringBuilder sb = new StringBuilder();
		sb.append(linea.getNumeroLinea()).append(",").append(linea.getTIPO_LINEA()).append(",")
				.append(linea.getTotalLlamadas()).append(",").append(linea.getDuracionTotalMin()).append(",")
				.append(linea.getMinutosPlan()).append(",").append(linea.getMinutosUsadosPlan()).append(",")
				.append(Math.max(0, linea.getMinutosPlan() - linea.getMinutosUsadosPlan())).append(",")
				.append(linea.getCostoTotal());
		return sb.toString();
	}

	public LineaTelefonica getLineaLocal() {
		return lineaLocal;
	}

	public LineaTelefonica getLineaNacional() {
		return lineaNacional;
	}

	public LineaTelefonica getLineaCorporativa() {
		return lineaCorporativa;
	}
}
