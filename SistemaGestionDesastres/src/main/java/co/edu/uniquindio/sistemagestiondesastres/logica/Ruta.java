package co.edu.uniquindio.sistemagestiondesastres.logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ruta {

    private String idRuta;
    private String origen;
    private String destino;
    private Date tiempo;
    private double distancia;
    private List<ZonaAfectada> zonasAfectadas;

    public Ruta(String idRuta, String origen, String destino, Date tiempo, double distancia) {
        this.idRuta = idRuta;
        this.origen = origen;
        this.destino = destino;
        this.tiempo = tiempo;
        this.distancia = distancia;
        this.zonasAfectadas = new ArrayList<>();
    }

    // ===== Getters y Setters =====
    public String getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(String idRuta) {
        this.idRuta = idRuta;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getTiempo() {
        return tiempo;
    }

    public void setTiempo(Date tiempo) {
        this.tiempo = tiempo;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public List<ZonaAfectada> getZonasAfectadas() {
        return zonasAfectadas;
    }

    // ===== Métodos funcionales =====

    /** Agrega una zona afectada a la ruta **/
    public void agregarZonaAfectada(ZonaAfectada zona) {
        if (!zonasAfectadas.contains(zona)) {
            zonasAfectadas.add(zona);
            System.out.println("Zona afectada " + zona.getNombre() + " agregada a la ruta " + idRuta);
        }
    }

    /** Elimina una zona afectada de la ruta **/
    public void eliminarZonaAfectada(ZonaAfectada zona) {
        zonasAfectadas.remove(zona);
    }

    /** Calcula el tiempo estimado total de evacuación en base a las zonas **/
    public double calcularTiempoEstimado() {
        double tiempoBase = distancia / 50.0; // ejemplo: velocidad promedio 50 km/h
        double penalizacionZonas = zonasAfectadas.size() * 0.5; // 0.5 horas por zona afectada
        return tiempoBase + penalizacionZonas;
    }

    /** Verifica si la ruta pasa por una zona específica **/
    public boolean pasaPorZona(String nombreZona) {
        return zonasAfectadas.stream()
                .anyMatch(z -> z.getNombre().equalsIgnoreCase(nombreZona));
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "idRuta='" + idRuta + '\'' +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", distancia=" + distancia +
                ", zonasAfectadas=" + zonasAfectadas.size() +
                '}';
    }
}
