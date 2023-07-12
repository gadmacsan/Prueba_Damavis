package Prueba;
import java.util.*;

public class EjercicioPruebaBacktracking {
    
    public static int resolverMatriz(List<List<String>> mapa) {
        int filas = mapa.size();
        int columnas = mapa.get(0).size();
        
        if (filas < 3 || filas > 1000 || columnas < 3 || columnas > 1000) {
            return -1;
        }
        
        int[][] visitado = new int[filas][columnas];
        
        // Iniciar la recursión desde la posición inicial
        return backtrack(mapa, visitado, 0, 0, 0);
    }
    
    private static int backtrack(List<List<String>> mapa, int[][] visitado, int fila, int columna, int pasos) {
        int filas = mapa.size();
        int columnas = mapa.get(0).size();
        
        
        // Verificar límites y obstáculos
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas ||
                visitado[fila][columna] == 1 || mapa.get(fila).get(columna).equals("#")) {
            return -1;
        }
        
        // Verificar si se alcanzó la esquina inferior derecha
        if (fila == filas - 1 && columna == columnas - 1) {
            return pasos;
        }
        
        // Marcar como visitado
        visitado[fila][columna] = 1;
        
        // Movimiento hacia la derecha
        int pasosDerecha = backtrack(mapa, visitado, fila, columna + 1, pasos + 1);
        // Movimiento hacia abajo
        int pasosAbajo = backtrack(mapa, visitado, fila + 1, columna, pasos + 1);
        // Movimiento hacia arriba
        int pasosArriba = backtrack(mapa, visitado, fila - 1, columna, pasos + 1);
        // Movimiento hacia la izquierda
        int pasosIzquierda = backtrack(mapa, visitado, fila, columna - 1, pasos + 1);
        
        // Desmarcar como visitado para explorar otras opciones
        visitado[fila][columna] = 0;
        
        // Calcular el mínimo de pasos entre los movimientos posibles
        int minPasos = Integer.MAX_VALUE;
        if (pasosDerecha != -1) {
            minPasos = Math.min(minPasos, pasosDerecha);
        }
        if (pasosAbajo != -1) {
            minPasos = Math.min(minPasos, pasosAbajo);
        }
        if (pasosArriba != -1) {
            minPasos = Math.min(minPasos, pasosArriba);
        }
        if (pasosIzquierda != -1) {
            minPasos = Math.min(minPasos, pasosIzquierda);
        }
        
        if (minPasos == Integer.MAX_VALUE) {
            return -1; // No hay solución
        }
        
        return minPasos;
    }

    public static void imprimirmapa(List<List<String>> mapa) {
        for (List<String> fila : mapa) {
            for (String celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        List<List<String>> mapa = new ArrayList<>();
        mapa.add(Arrays.asList(".", "#", "#", ".", ".", ".", ".", ".", "."));
        mapa.add(Arrays.asList(".", ".", ".", ".", "#", ".", ".", "#", "."));
        mapa.add(Arrays.asList("#", ".", ".", ".", ".", ".", ".", ".", "."));
        mapa.add(Arrays.asList(".", "#", ".", ".", ".", ".", ".", "#", "."));
        mapa.add(Arrays.asList(".", "#", ".", ".", ".", ".", "#", "#", "."));

        int minPasos = resolverMatriz(mapa);

        if (minPasos != -1) {
            System.out.println("Solucion encontrada:");
            imprimirmapa(mapa);
            System.out.println("Numero minimo de pasos: " + minPasos);
        } else {
            System.out.println("No tiene solucion: -1");
        }
    }
}


