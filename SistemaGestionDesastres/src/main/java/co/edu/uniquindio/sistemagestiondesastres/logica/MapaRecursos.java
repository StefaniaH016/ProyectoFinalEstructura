package co.edu.uniquindio.sistemagestiondesastres.logica;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MapaRecursos {
    Map<String,Map<String,Integer>> inventario;
    LinkedList<Recurso> listRecursos;

    public MapaRecursos() {
        inventario = new HashMap<>();
        listRecursos = new LinkedList<>();
    }
}
