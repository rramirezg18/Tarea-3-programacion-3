/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tarea3programacion3;

/**
 *
 * @author ianto
 */
import java.util.*;

public class Tarea3Programacion3 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese el grado del árbol B: ");
        int grado = entrada.nextInt();
        ArbolB arbol = new ArbolB(grado);
        int opcion = 0;
        int salir = 0;
        while(salir != 1){
            Menu();
            opcion = entrada.nextInt();
            switch(opcion){
                case 1:
                    //ingresar claves al Árbol b
                    boolean continuarIngresando = true;
                    while (continuarIngresando) {
                        System.out.print("Ingrese una clave: ");
                        int clave = entrada.nextInt();
                        arbol.ingresarClave(clave);
                        System.out.print("¿Desea ingresar otra clave? (Sí/No): ");
                        String respuesta = entrada.next();
                        if (!respuesta.equalsIgnoreCase("Sí") && !respuesta.equalsIgnoreCase("Si")) {
                            continuarIngresando = false;
                        }
                    }
                    System.out.println("Claves En El Árbol B:");
                    arbol.mostrar();
                    break;
                case 2:
                    //Ingresaar clave para eliminar
                    System.out.println("Claves En El Árbol B:");
                    arbol.mostrar();
                    System.out.println("Ingrese la clave para eliminar: ");
                    int claveEliminar = entrada.nextInt();
                    arbol.eliminarClave(claveEliminar);
                    System.out.println("Árbol B Nuevo:");
                    arbol.mostrar();
                    break;
                case 3:
                    //Busqueda de clave
                    System.out.println("Claves En El Árbol B:");
                    arbol.mostrar();
                    System.out.println("Ingrese claves para buesqueda");
                    int claveBuscar = entrada.nextInt();
                    boolean encontrado = arbol.busqueda(claveBuscar);
                    if (encontrado) {
                        System.out.println("La clave " + claveBuscar + " se encuentra en el árbol");
                    } else {
                        System.out.println("La clave " + claveBuscar + " no se encuentra en el árbol");
                    }
                    break;
                case 4:
                    salir = 1;
                    break;
            }
        }
    }
    
    public static void Menu(){
        System.out.println("\n1. Ingresar Claves");
        System.out.println("2. Eliminar Clave");
        System.out.println("3. Busqueda");
        System.out.println("4. Salir \n");
        System.out.println("Seleccione una opcion\n");
        
    }
}
