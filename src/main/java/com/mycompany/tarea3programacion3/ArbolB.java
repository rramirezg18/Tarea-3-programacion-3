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
    
    public ArbolB(){
        this.raiz = null;
    }
    
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
}
