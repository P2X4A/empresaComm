package co.edu.unbosque.model;

public class LineaTelefonica {

    public enum TipoLinea {
        LOCAL, NACIONAL, CORPORATIVA
    }

    private int numeroLinea;
    private int minutosPlan;
    private int minutosUsadosPlan;
    private int totalLlamadas;
    private int duracionTotalMin;
    private long costoTotal;
    private final TipoLinea TIPO_LINEA;

    private final int MINUTO_LLAMADA_LOCAL = 35;
    private final int MINUTO_LARGA_DISTANCIA = 380;
    private final int MINUTO_CORPORATIVO = 999;

    public LineaTelefonica(int numeroLinea, int minutosPlan, TipoLinea tipoLinea) {
        this.numeroLinea = numeroLinea;
        this.minutosPlan = Math.max(0, minutosPlan);
        this.TIPO_LINEA = tipoLinea;
        this.minutosUsadosPlan = 0;
        this.totalLlamadas = 0;
        this.duracionTotalMin = 0;
        this.costoTotal = 0;
    }

    public LineaTelefonica() {
        this(0, 0, TipoLinea.LOCAL);
    }

    public boolean permite(Llamada.Tipo tipo){
        switch (TIPO_LINEA) {
            case LOCAL:
                return tipo == Llamada.Tipo.LOCAL;
            case NACIONAL:
                return tipo == Llamada.Tipo.LOCAL || tipo == Llamada.Tipo.LARGA_DISTANCIA;
            case CORPORATIVA:
                return tipo == Llamada.Tipo.LOCAL || tipo == Llamada.Tipo.LARGA_DISTANCIA
                        || tipo == Llamada.Tipo.CELULAR;
            default:
                return false;
        }
    }

    public int tarifaPorMinuto(Llamada.Tipo tipo) {
        switch (tipo) {
            case LOCAL: return MINUTO_LLAMADA_LOCAL;
            case LARGA_DISTANCIA: return MINUTO_LARGA_DISTANCIA;
            case CELULAR: return MINUTO_CORPORATIVO;
            default: return 0;
        }
    }

    public long aplicarLlamada(int minutosLlamada, Llamada.Tipo tipo) {
        if (minutosLlamada < 0) throw new IllegalArgumentException("minutosLlamada debe ser >= 0");
        if (!permite(tipo)) throw new IllegalArgumentException("Tipo de llamada no permitido en esta línea");

        int minutosRestantesPlan = Math.max(0, minutosPlan - minutosUsadosPlan);
        int minutosGratis = Math.min(minutosLlamada, minutosRestantesPlan);
        int minutosExcedentes = minutosLlamada - minutosGratis;

        minutosUsadosPlan += minutosGratis;
        duracionTotalMin += minutosLlamada;
        totalLlamadas++;

        long costo = (long) minutosExcedentes * tarifaPorMinuto(tipo);
        costoTotal += costo;
        return costo;
    }


    public int getNumeroLinea() {
        return numeroLinea;
    }

    public void setNumeroLinea(int numeroLinea) {
        this.numeroLinea = numeroLinea;
    }

    public int getMinutosPlan() {
        return minutosPlan;
    }

    public void setMinutosPlan(int minutosPlan) {
        this.minutosPlan = minutosPlan;
    }

    public int getMinutosUsadosPlan() {
        return minutosUsadosPlan;
    }

    public void setMinutosUsadosPlan(int minutosUsadosPlan) {
        this.minutosUsadosPlan = minutosUsadosPlan;
    }

    public int getTotalLlamadas() {
        return totalLlamadas;
    }

    public void setTotalLlamadas(int totalLlamadas) {
        this.totalLlamadas = totalLlamadas;
    }

    public int getDuracionTotalMin() {
        return duracionTotalMin;
    }

    public void setDuracionTotalMin(int duracionTotalMin) {
        this.duracionTotalMin = duracionTotalMin;
    }

    public long getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(long costoTotal) {
        this.costoTotal = costoTotal;
    }

    public TipoLinea getTIPO_LINEA() {
        return TIPO_LINEA;
    }

    public int getMINUTO_LLAMADA_LOCAL() {
        return MINUTO_LLAMADA_LOCAL;
    }

    public int getMINUTO_LARGA_DISTANCIA() {
        return MINUTO_LARGA_DISTANCIA;
    }

    public int getMINUTO_CORPORATIVO() {
        return MINUTO_CORPORATIVO;
    }
}
