package co.edu.uniquindio.sistemagestiondesastres.logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ruta {

    String idRuta;
    String origen;
    String destino;
    Date tiempo;
    double distancia;
    List<ZonaAfectada> zonasAfectadas;

    public Ruta(String idRuta, String origen, String destino, Date tiempo, double dist) {
        this.idRuta = idRuta;
        this.origen = origen;
        this.destino = destino;
        this.tiempo = tiempo;
        this.distancia = dist;
        zonasAfectadas = new ArrayList<ZonaAfectada>();
    }

    public Ruta() {

    }

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

    public void setZonasAfectadas(List<ZonaAfectada> zonasAfectadas) {
        this.zonasAfectadas = zonasAfectadas;
    }

}
