package co.edu.uniquindio.sistemagestiondesastres.logica;

public class ZonaEvacuacion implements Comparable<ZonaEvacuacion> {
    private String idZona;
    private String nombreZona;
    private int personasAevacuar;
    private int nivelUrgencia; // 1 = bajo, 10 = crítico

    // --- Constructor ---
    public ZonaEvacuacion(String idZona, String nombreZona, int personasAevacuar, int nivelUrgencia) {
        this.idZona = idZona;
        this.nombreZona = nombreZona;
        this.personasAevacuar = personasAevacuar;
        this.nivelUrgencia = nivelUrgencia;
    }

    // --- Getters y Setters ---
    public String getIdZona() {
        return idZona;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public int getPersonasAevacuar() {
        return personasAevacuar;
    }

    public void setPersonasAevacuar(int personasAevacuar) {
        this.personasAevacuar = personasAevacuar;
    }

    public int getNivelEmergencia() {
        return nivelUrgencia;
    }

    public void setNivelUrgencia(int nivelUrgencia) {
        this.nivelUrgencia = nivelUrgencia;
    }

    // --- Método para comparación (para PriorityQueue) ---
    @Override
    public int compareTo(ZonaEvacuacion otra) {
        // Invertimos el orden para que mayor urgencia = mayor prioridad
        return Integer.compare(otra.nivelUrgencia, this.nivelUrgencia);
    }

    @Override
    public String toString() {
        return "Zona: " + nombreZona +
                " | Personas a evacuar: " + personasAevacuar +
                " | Nivel de urgencia: " + nivelUrgencia;
    }


}