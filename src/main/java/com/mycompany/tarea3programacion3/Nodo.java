/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tarea3programacion3;

/**
 *
 * @author ianto
 */
import java.util.*;
public class Nodo {
    ArrayList<Integer> claves;
    ArrayList<Nodo> hijos;
    boolean esHoja;
    //constructor
    public Nodo(boolean esHoja){
        this.esHoja = esHoja;
        claves = new ArrayList<>();
        hijos = new ArrayList<>();
    }
}
