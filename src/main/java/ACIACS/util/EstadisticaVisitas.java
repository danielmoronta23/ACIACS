package ACIACS.util;

import java.util.Map;

public class EstadisticaVisitas {
    private Map<String, Object> visitasNormalesAceptadas;
    private Map<String, Object> visitasNormalesDenegadas;
    private Map<String, Object> visitasPrioritariaAceptada;
    private Map<String, Object> visitasPrioritariaDenegada;

    public EstadisticaVisitas(Map<String, Object> visitasNormalesAceptadas, Map<String, Object> visitasNormalesDenegadas, Map<String, Object> visitasPrioritariaAceptada, Map<String, Object> visitaPrioritariaDenegada) {
        this.visitasNormalesAceptadas = visitasNormalesAceptadas;
        this.visitasNormalesDenegadas = visitasNormalesDenegadas;
        this.visitasPrioritariaAceptada = visitasPrioritariaAceptada;
        this.visitasPrioritariaDenegada = visitaPrioritariaDenegada;
    }

    public Map<String, Object> getVisitasNormalesAceptadas() {
        return visitasNormalesAceptadas;
    }

    public void setVisitasNormalesAceptadas(Map<String, Object> visitasNormalesAceptadas) {
        this.visitasNormalesAceptadas = visitasNormalesAceptadas;
    }

    public Map<String, Object> getVisitasNormalesDenegadas() {
        return visitasNormalesDenegadas;
    }

    public void setVisitasNormalesDenegadas(Map<String, Object> visitasNormalesDenegadas) {
        this.visitasNormalesDenegadas = visitasNormalesDenegadas;
    }

    public Map<String, Object> getVisitasPrioritariaAceptada() {
        return visitasPrioritariaAceptada;
    }

    public void setVisitasPrioritariaAceptada(Map<String, Object> visitasPrioritariaAceptada) {
        this.visitasPrioritariaAceptada = visitasPrioritariaAceptada;
    }

    public Map<String, Object> getVisitasPrioritariaDenegada() {
        return visitasPrioritariaDenegada;
    }

    public void setVisitasPrioritariaDenegada(Map<String, Object> visitasPrioritariaDenegada) {
        this.visitasPrioritariaDenegada = visitasPrioritariaDenegada;
    }
}
