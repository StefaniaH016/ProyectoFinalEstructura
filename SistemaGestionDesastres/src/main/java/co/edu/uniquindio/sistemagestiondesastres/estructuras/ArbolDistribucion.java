package co.edu.uniquindio.sistemagestiondesastres.estructuras;

public class ArbolDistribucion {
    private NodoDistribucion raiz;

    public ArbolDistribucion(NodoDistribucion raiz) {
        this.raiz = raiz;
    }

    public ArbolDistribucion(){
        raiz = null;
    }

    public NodoDistribucion getRaiz() {
        return raiz;
    }

    public void agregarNodo(NodoDistribucion padre, NodoDistribucion hijo) {
        if (padre != null) {
            padre.agregarHijo(hijo);
        }
    }

    public void mostrarDistribucion() {
        if (raiz != null) {
            raiz.mostrarArbol("");
        } else {
            System.out.println("El árbol está vacío.");
        }
    }
}