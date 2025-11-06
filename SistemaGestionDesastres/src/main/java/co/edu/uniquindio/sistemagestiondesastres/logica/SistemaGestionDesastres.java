package co.edu.uniquindio.sistemagestiondesastres.logica;

import co.edu.uniquindio.sistemagestiondesastres.estructuras.ArbolDistribucion;
import co.edu.uniquindio.sistemagestiondesastres.estructuras.ColaPrioridadEvacuaciones;
import co.edu.uniquindio.sistemagestiondesastres.estructuras.GrafoDirigido;
import co.edu.uniquindio.sistemagestiondesastres.estructuras.NodoGrafo;
import co.edu.uniquindio.sistemagestiondesastres.logica.enums.EstadoEvacuacion;

import java.time.LocalDate;
import java.util.*;

/**
 * Clase principal del Sistema de Gestión de Desastres Naturales.
 * Coordina la gestión de recursos, rutas, evacuaciones, usuarios y reportes.
 */
public class SistemaGestionDesastres {

    private GrafoDirigido grafoRutas;
    private ArbolDistribucion arbolRecursos;
    private ColaPrioridadEvacuaciones colaEvacuaciones;

    private Map<String, ZonaAfectada> zonasAfectadas;
    private Map<String, Recurso> recursosDisponibles;
    private List<Reporte> reportes;
    private Administrador administrador;
    private LinkedList<Usuario> listaUsuarios;

    public SistemaGestionDesastres(Administrador administrador) {
        this.grafoRutas = new GrafoDirigido();
        this.arbolRecursos = new ArbolDistribucion();
        this.colaEvacuaciones = new ColaPrioridadEvacuaciones();

        this.zonasAfectadas = new HashMap<>();
        this.recursosDisponibles = new HashMap<>();
        this.reportes = new ArrayList<>();
        this.administrador = administrador;
        this.listaUsuarios = new LinkedList<>();
    }

    // ============================================================
    // ============== GESTIÓN DE USUARIOS =========================
    // ============================================================

    public void registrarUsuario(Usuario usuario) {
        if (!listaUsuarios.contains(usuario)) {
            listaUsuarios.add(usuario);
            System.out.println("✅ Usuario " + usuario.getNombre() + " registrado correctamente.");
        } else {
            System.out.println("⚠️ El usuario ya está registrado.");
        }
    }

    public Usuario autenticarUsuario(String id, String contrasena) {
        for (Usuario u : listaUsuarios) {
            if (u.getId().equals(id) && u.getPasswd().equals(contrasena)) {
                System.out.println("🔓 Autenticación exitosa: " + u.getNombre());
                return u;
            }
        }
        System.out.println("❌ Error de autenticación. Usuario o contraseña incorrectos.");
        return null;
    }

    // ============================================================
    // ============== GESTIÓN DE ZONAS AFECTADAS ==================
    // ============================================================

    public void registrarZonaAfectada(ZonaAfectada zona) {
        zonasAfectadas.put(zona.getNombre(), zona);
        System.out.println("🌍 Zona afectada registrada: " + zona.getNombre());
    }

    public ZonaAfectada obtenerZona(String nombre) {
        return zonasAfectadas.get(nombre);
    }

    public void actualizarEstadoZona(String nombreZona, int nuevoNivelRiesgo, int nuevasPersonasAfectadas) {
        ZonaAfectada zona = zonasAfectadas.get(nombreZona);
        if (zona != null) {
            zona.setNivelRiesgo(nuevoNivelRiesgo);
            zona.setPersonasAfectadas(nuevasPersonasAfectadas);
            zona.actualizarEstado();
            System.out.println("🟡 Zona actualizada: " + zona);
        } else {
            System.out.println("⚠️ No se encontró la zona especificada.");
        }
    }

    public List<ZonaAfectada> obtenerZonasEnRiesgoAlto() {
        List<ZonaAfectada> zonasCriticas = new ArrayList<>();
        for (ZonaAfectada z : zonasAfectadas.values()) {
            if (z.getNivelRiesgo() >= 7) zonasCriticas.add(z);
        }
        return zonasCriticas;
    }

    // ============================================================
    // ============== GESTIÓN DE RECURSOS =========================
    // ============================================================

    public void registrarRecurso(Recurso recurso) {
        recursosDisponibles.put(recurso.getId(), recurso);
        arbolRecursos.insertarRecurso(recurso);
        System.out.println("🧰 Recurso registrado: " + recurso);
    }

    public void asignarRecursoAZona(String idRecurso, String nombreZona) {
        Recurso recurso = recursosDisponibles.get(idRecurso);
        ZonaAfectada zona = zonasAfectadas.get(nombreZona);
        if (recurso != null && zona != null) {
            zona.asignarRecurso(recurso);
            arbolRecursos.eliminarRecurso(idRecurso); // simula su distribución
            System.out.println("🚚 Recurso " + idRecurso + " asignado a la zona " + nombreZona);
        } else {
            System.out.println("⚠️ No se pudo asignar el recurso. Verifique los datos.");
        }
    }

    public void listarRecursosDisponibles() {
        System.out.println("=== Recursos disponibles ===");
        for (Recurso r : recursosDisponibles.values()) {
            System.out.println(r);
        }
    }

    // ============================================================
    // ============== GESTIÓN DE EVACUACIONES =====================
    // ============================================================

    public void registrarZonaEvacuacion(ZonaEvacuacion z) {
        colaEvacuaciones.agregarZonaEvacuacion(z);
        System.out.println("🚨 Zona agregada a cola de evacuaciones: " + z.getNombreZona());
    }

    public void ejecutarEvacuaciones() {
        System.out.println("\n🏃 Ejecutando evacuaciones prioritarias...");
        while (!colaEvacuaciones.estaVacia()) {
            ZonaEvacuacion zona = colaEvacuaciones.obtenerZonaPrioritaria();
            System.out.println("🚨 Evacuando zona: " + zona);
        }
        System.out.println("✅ Evacuaciones completadas.");
    }

    // ============================================================
    // ============== GESTIÓN DE RUTAS =============================
    // ============================================================

    public void agregarRuta(String origen, String destino, double distancia, Date tiempo) {
        Ruta nuevaRuta = new Ruta(UUID.randomUUID().toString(), origen, destino, tiempo, distancia);
        grafoRutas.agregarRuta(origen, destino, distancia);
        System.out.println("🛣️ Ruta agregada: " + origen + " → " + destino + " (" + distancia + " km)");
    }

    public List<NodoGrafo> calcularRutaMasCorta(String origen, String destino) {
        System.out.println("🗺️ Calculando ruta más corta entre " + origen + " y " + destino + "...");
        return grafoRutas.obtenerRutaMasCorta(origen, destino);
    }

    public void mostrarGrafoRutas() {
        grafoRutas.mostrarGrafo();
    }

    // ============================================================
    // ============== REPORTES Y ESTADÍSTICAS =====================
    // ============================================================

    public void generarReporte(LocalDate fecha, String contenido, Usuario usuario) {
        Reporte reporte = new Reporte(fecha,contenido, usuario);
        reportes.add(reporte);
        System.out.println("📝 Reporte generado: " + contenido);
    }

    public void listarReportes() {
        System.out.println("=== Reportes Generados ===");
        for (Reporte r : reportes) {
            System.out.println(r);
        }
    }

    public void generarEstadisticas() {
        System.out.println("\n📊 Estadísticas del Sistema:");
        System.out.println("Zonas afectadas: " + zonasAfectadas.size());
        System.out.println("Recursos disponibles: " + recursosDisponibles.size());
        System.out.println("Reportes emitidos: " + reportes.size());
        System.out.println("Usuarios registrados: " + listaUsuarios.size());
    }

    // ============================================================
    // ============== SIMULACIÓN GLOBAL ============================
    // ============================================================

    public void simularGestionDesastre() {
        System.out.println("\n🌎 Iniciando simulación de gestión de desastre...");

        System.out.println("\n→ Evaluando zonas afectadas...");
        for (ZonaAfectada zona : zonasAfectadas.values()) {
            zona.actualizarEstado();
            System.out.println("Zona: " + zona.getNombre() + " | Estado: " + zona.getEstado());
            if (zona.necesitaEvacuacion()) {
                ZonaEvacuacion zEv = new ZonaEvacuacion(
                        "EV-" + zona.getNombre(),
                        zona.getNombre(),
                        zona.getPersonasAfectadas(),
                        zona.getNivelRiesgo()
                );
                registrarZonaEvacuacion(zEv);
            }
        }

        System.out.println("\n→ Ejecutando evacuaciones...");
        ejecutarEvacuaciones();

        System.out.println("\n→ Distribuyendo recursos...");
        for (ZonaAfectada zona : zonasAfectadas.values()) {
            if (zona.getRecursosAsignados().isEmpty() && !recursosDisponibles.isEmpty()) {
                Recurso recurso = recursosDisponibles.values().iterator().next();
                asignarRecursoAZona(recurso.getId(), zona.getNombre());
            }
        }

        System.out.println("\n✅ Simulación finalizada.");
        generarEstadisticas();
    }
}
