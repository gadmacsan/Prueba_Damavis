package Prueba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EjercicioPruebaCentro {

    public static int resolverLaberinto(List<List<String>> laberinto, int[][] visitado, int fila, int columna, int pasos, int centroFila, int centroColumna) {
        int filas = laberinto.size();
        int columnas = laberinto.get(0).size();

        // Verificar si se alcanzó la esquina inferior derecha
        if (fila == filas - 1 && columna == columnas - 1) {
            return pasos;
        }

        // Verificar límites y obstáculos
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas ||
                visitado[fila][columna] == 1 || laberinto.get(fila).get(columna).equals("#")) {
            return -1;
        }

        // Marcar como visitado
        visitado[fila][columna] = 1;

        // Movimiento hacia la derecha
        int pasosDerecha = resolverLaberinto(laberinto, visitado, fila, columna + 1, pasos + 1, centroFila, centroColumna);
        if (pasosDerecha != -1) {
            // Marcar camino
            laberinto.get(fila).set(columna, "@");
            return pasosDerecha;
        }

        // Movimiento hacia abajo
        int pasosAbajo = resolverLaberinto(laberinto, visitado, fila + 1, columna, pasos + 1, centroFila, centroColumna);
        if (pasosAbajo != -1) {
            // Marcar camino
            laberinto.get(fila).set(columna, "@");
            return pasosAbajo;
        }

        // Movimiento hacia arriba
        int pasosArriba = resolverLaberinto(laberinto, visitado, fila - 1, columna, pasos + 1, centroFila, centroColumna);
        if (pasosArriba != -1) {
            // Marcar camino
            laberinto.get(fila).set(columna, "@");
            return pasosArriba;
        }

        // Movimiento hacia la izquierda
        int pasosIzquierda = resolverLaberinto(laberinto, visitado, fila, columna - 1, pasos + 1, centroFila, centroColumna);
        if (pasosIzquierda != -1) {
            // Marcar camino
            laberinto.get(fila).set(columna, "@");
            return pasosIzquierda;
        }

        // Movimiento giratorio en la celda del centro del objeto
        if (fila == centroFila && columna == centroColumna) {
            // Actualizar posición del centro en cada iteración
            int nuevoCentroFila = centroFila;
            int nuevoCentroColumna = centroColumna;
            if (pasos % 3 == 0) {
                // Girar verticalmente
                nuevoCentroFila = centroFila + 1;
            } else if (pasos % 3 == 1) {
                // Girar horizontalmente
                nuevoCentroColumna = centroColumna + 1;
            } else if (pasos % 3 == 2) {
                // Girar verticalmente
                nuevoCentroFila = centroFila - 1;
            }

            int pasosGiro = resolverLaberinto(laberinto, visitado, fila, columna, pasos + 1, nuevoCentroFila, nuevoCentroColumna);
            if (pasosGiro != -1) {
                // Marcar camino
                laberinto.get(fila).set(columna, "@");
                return pasosGiro;
            }
        }

        // No hay camino posible, deshacer marca de visitado
        visitado[fila][columna] = 0;
        return -1;
    }

    public static void imprimirLaberinto(List<List<String>> laberinto) {
        for (List<String> fila : laberinto) {
            for (String celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        List<List<String>> laberinto = new ArrayList<>();
        laberinto.add(Arrays.asList(".", "#", "#", ".", ".", ".", ".", ".", "."));
        laberinto.add(Arrays.asList(".", ".", ".", ".", "#", ".", ".", "#", "."));
        laberinto.add(Arrays.asList("#", ".", ".", ".", ".", ".", ".", ".", "."));
        laberinto.add(Arrays.asList(".", "#", ".", ".", ".", ".", ".", "#", "."));
        laberinto.add(Arrays.asList(".", "#", ".", ".", ".", ".", ".", "#", "."));


        int filas = laberinto.size();
        int columnas = laberinto.get(0).size();

        // Verificar tamaño del laberinto
        if (filas < 3 || filas > 1000 || columnas < 3 || columnas > 1000) {
            System.out.println("Tamaño de laberinto inválido.");
            return;
        }

        int[][] visitado = new int[filas][columnas];

        // Obtener posición inicial del objeto
        int inicioFila = 0;
        int inicioColumna = 0;

        // Encontrar posición del centro del objeto
        int centroFila = inicioFila;
        int centroColumna = inicioColumna + 1;

        int minPasos = resolverLaberinto(laberinto, visitado, inicioFila, inicioColumna, 0, centroFila, centroColumna);

        if (minPasos != -1) {
            System.out.println("Solución encontrada:");
            imprimirLaberinto(laberinto);
            System.out.println("Número mínimo de pasos: " + minPasos);
        } else {
            System.out.println("No hay solución posible.");
        }
    }
}

