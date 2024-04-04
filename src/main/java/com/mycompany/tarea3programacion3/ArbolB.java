/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea3programacion3;

/**
 *
 * @author ianto
 */
public class ArbolB {
    private Nodo raiz;
    private int grado;

    public ArbolB(int grado) {
        this.raiz = null;
        this.grado = grado;
    }
    //Metodos para buscar las claves en los nodos
    public boolean buscar(int clave){
        return buscarEnNodo(raiz, clave);
    }
    
    private boolean buscarEnNodo(Nodo nodo, int clave){
        if(nodo == null){
            return false;
        }
        int i = 0;
        while(i < nodo.claves.size() & clave > nodo.claves.get(i)){
            i++;
        }
        if(i < nodo.claves.size() && clave == nodo.claves.get(i)){
            return true;
        }
        if(nodo.esHoja){
            return false;
        }
        return buscarEnNodo(nodo.hijos.get(i), clave);
    }
    //Metodo para ingresar claves
    public void ingresarClave(int clave){
        if(raiz == null){
            raiz = new Nodo(true);
            raiz.claves.add(clave);
        }else{
            int orden = 0;
            if(raiz.claves.size() == 2 * orden - 1){
                Nodo nuevaRaiz = new Nodo(false);
                nuevaRaiz.hijos.add(raiz);
                dividirNodo(nuevaRaiz, 0, raiz);
                int i = 0;
                if(nuevaRaiz.claves.get(0) < clave){
                    i++;
                }
                ingresarEnNoLleno(nuevaRaiz.hijos.get(i), clave);
                raiz = nuevaRaiz;
            }else{
                ingresarEnNoLleno(raiz, clave);
            }
        }
    }
    //Metodo para ingresar una clave en un nodo con espacios correspondientes
    private void ingresarEnNoLleno(Nodo nodo, int clave){
        int i = nodo.claves.size() - 1;
        if(nodo.esHoja){
            nodo.claves.add(0);
            while(i >= 0 && nodo.claves.get(i) > clave){
                nodo.claves.set(i + 1, nodo.claves.get(i));
                i--;
            }
            nodo.claves.set(i + 1, clave);
        }else {
            while (i >= 0 && nodo.claves.get(i) > clave) {
                i--;
            }
            int j = i + 1;
            int orden = 0;
            if (nodo.hijos.get(j).claves.size() == 2 * orden - 1) {
                dividirNodo(nodo, j, nodo.hijos.get(j));
                if (nodo.claves.get(j) < clave) {
                    j++;
                }
            }
            ingresarEnNoLleno(nodo.hijos.get(j), clave);
        }
    }
    //Metodo para dividir un nodo cuando corresponde
    private void dividirNodo(Nodo padre, int indiceHijo, Nodo hijo) {
        Nodo nuevoHijo = new Nodo(hijo.esHoja);
        int orden = 0;
        for (int i = 0; i < orden - 1; i++) {
            nuevoHijo.claves.add(hijo.claves.get(orden + i));
        }
        if (!hijo.esHoja) {
            for (int i = 0; i < orden; i++) {
                nuevoHijo.hijos.add(hijo.hijos.get(orden + i));
            }
        }
        hijo.claves.subList(orden - 1, 2 * orden - 1).clear();
        if (!hijo.esHoja) {
            hijo.hijos.subList(orden, 2 * orden).clear();
        }
        padre.claves.add(indiceHijo, hijo.claves.get(orden - 1));
        padre.hijos.add(indiceHijo + 1, nuevoHijo);
        hijo.claves.remove(orden - 1);
    }
    //metodos para mostrar las claves en pantalla
    public void mostrar() {
        mostrarNodo(raiz);
    }

    private void mostrarNodo(Nodo nodo) {
        if (nodo != null) {
            for (int i = 0; i < nodo.claves.size(); i++) {
                System.out.print(nodo.claves.get(i) + " ");
            }
            System.out.println();
            if (!nodo.esHoja) {
                for (int i = 0; i < nodo.hijos.size(); i++) {
                    mostrarNodo(nodo.hijos.get(i));
                }
            }
        }
    }
    //metodos para eliminar una clave ingresadda por el usuario
    public void eliminarClave(int clave) {
        eliminarEnNodo(raiz, clave);
    }

    private void eliminarEnNodo(Nodo nodo, int clave) {
        if (nodo == null) {
            return;
        }
        int i = 0;
        while (i < nodo.claves.size() && clave > nodo.claves.get(i)) {
            i++;
        }

        if (i < nodo.claves.size() && clave == nodo.claves.get(i)) {
            if (nodo.esHoja) {
                nodo.claves.remove(i);
            } else {
                boolean esBorrado = false;
                if (nodo.hijos.get(i).claves.size() >= grado) {
                    int predecesor = obtenerPredecesorIzquierdo(nodo, i);
                    nodo.claves.set(i, predecesor);
                    eliminarEnNodo(nodo.hijos.get(i), predecesor);
                    esBorrado = true;
                } else if (nodo.hijos.get(i + 1).claves.size() >= grado) {
                    int sucesor = obtenerSucesorDerecho(nodo, i);
                    nodo.claves.set(i, sucesor);
                    eliminarEnNodo(nodo.hijos.get(i + 1), sucesor);
                    esBorrado = true;
                }
                if (!esBorrado) {
                    fusionarNodo(nodo, i);
                    eliminarEnNodo(nodo.hijos.get(i), clave);
                }
            }
        } else {
            if (nodo.esHoja) {
                System.out.println("La clave no se encuentra en el árbol.");
                return;
            }
            boolean esBorrado = (i == nodo.claves.size());
            if (nodo.hijos.get(i).claves.size() < grado) {
                llenarNodo(nodo, i);
            }
            if (esBorrado && i > nodo.claves.size()) {
                eliminarEnNodo(nodo.hijos.get(i - 1), clave);
            } else {
                eliminarEnNodo(nodo.hijos.get(i), clave);
            }
        }
    }
    //Metodo para que cuando se elimine una clave se elimine, su lugar lo toma el prodecesor izquierdo que este mas a la izquierda
    private int obtenerPredecesorIzquierdo(Nodo nodo, int indice) {
        Nodo actual = nodo.hijos.get(indice);
        while (!actual.esHoja) {
            actual = actual.hijos.get(actual.claves.size());
        }
        return actual.claves.get(actual.claves.size() - 1);
    }
    //Si el predecesor izquierdo no aplica, se obtiene el prodecesor derecho mas a la derecha
    private int obtenerSucesorDerecho(Nodo nodo, int indice) {
        Nodo actual = nodo.hijos.get(indice + 1);
        while (!actual.esHoja) {
            actual = actual.hijos.get(0);
        }
        return actual.claves.get(0);
    }
    //metodo para fusionar dos nodos
    private void fusionarNodo(Nodo nodo, int indice) {
        Nodo hijo = nodo.hijos.get(indice);
        Nodo hermano = nodo.hijos.get(indice + 1);

        hijo.claves.add(nodo.claves.get(indice));
        for (int i = 0; i < hermano.claves.size(); i++) {
            hijo.claves.add(hermano.claves.get(i));
        }
        if (!hijo.esHoja) {
            for (int i = 0; i <= hermano.claves.size(); i++) {
                hijo.hijos.add(hermano.hijos.get(i));
            }
        }
        nodo.claves.remove(indice);
        nodo.hijos.remove(indice + 1);
    }
    //metodo para llenar un nodo cuando se fusionan
    private void llenarNodo(Nodo nodo, int indice) {
        int grado = this.grado;
        if (indice != 0 && nodo.hijos.get(indice - 1).claves.size() >= grado) {
            prestarDeAnterior(nodo, indice);
        } else if (indice != nodo.claves.size() && nodo.hijos.get(indice + 1).claves.size() >= grado) {
            prestarDeSiguiente(nodo, indice);
        } else {
            if (indice != nodo.claves.size()) {
                fusionarNodo(nodo, indice);
            } else {
                fusionarNodo(nodo, indice - 1);
            }
        }
    }
    //metodo para prestar del nodo izquierdo
    private void prestarDeAnterior(Nodo nodo, int indice) {
        Nodo hijo = nodo.hijos.get(indice);
        Nodo hermano = nodo.hijos.get(indice - 1);
        for (int i = hijo.claves.size() - 1; i >= 0; i--) {
            hijo.claves.set(i + 1, hijo.claves.get(i));
        }
        if (!hijo.esHoja) {
            for (int i = hijo.hijos.size() - 1; i >= 0; i--) {
                hijo.hijos.set(i + 1, hijo.hijos.get(i));
            }
        }
        hijo.claves.set(0, nodo.claves.get(indice - 1));
        if (!hermano.esHoja) {
            hijo.hijos.set(0, hermano.hijos.get(hermano.hijos.size() - 1));
            hermano.hijos.remove(hermano.hijos.size() - 1);
        }
        nodo.claves.set(indice - 1, hermano.claves.get(hermano.claves.size() - 1));
        hermano.claves.remove(hermano.claves.size() - 1);
    }
    //metodo para prestar del nodo derecho
    private void prestarDeSiguiente(Nodo nodo, int indice) {
        Nodo hijo = nodo.hijos.get(indice);
        Nodo hermano = nodo.hijos.get(indice + 1);
        hijo.claves.add(nodo.claves.get(indice));
        if (!hijo.esHoja) {
            hijo.hijos.add(hermano.hijos.get(0));
            hermano.hijos.remove(0);
        }
        nodo.claves.set(indice, hermano.claves.get(0));
        hermano.claves.remove(0);
    }
    //metodo para realizar la busqueda de la clave ingresada por el usuario
    public boolean busqueda(int clave) {
        return buscar(clave);
    }

}
