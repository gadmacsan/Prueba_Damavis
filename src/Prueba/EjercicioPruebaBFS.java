package Prueba;

import java.util.*;

public class EjercicioPruebaBFS {
    
    public static int resolverMatriz(List<List<String>> mapa) {
        int filas = mapa.size();
        int columnas = mapa.get(0).size();
        
        if (filas < 3 || filas > 1000 || columnas < 3 || columnas > 1000) {
            return -1;
        }
        
        int[][] visitado = new int[filas][columnas];
        Queue<int[]> cola = new LinkedList<>();
        
        // Agregar la posicion inicial a la cola
        cola.offer(new int[]{0, 0, 0}); // {fila, columna, pasos}
        
        while (!cola.isEmpty()) {
            int[] posicion = cola.poll();
            int fila = posicion[0];
            int columna = posicion[1];
            int pasos = posicion[2];
            
            // Verificar si se alcanzó la esquina inferior derecha
            if (fila == filas - 1 && columna == columnas - 1) {
                return pasos;
            }
            
            // Verificar límites y obstáculos
            if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas ||
                    visitado[fila][columna] == 1 || mapa.get(fila).get(columna).equals("#")) {
                continue;
            }
            
            // Marcar como visitado
            visitado[fila][columna] = 1;
            
            // Movimiento hacia la derecha
            cola.offer(new int[]{fila, columna + 1, pasos + 1});

            // Movimiento hacia abajo
            cola.offer(new int[]{fila + 1, columna, pasos + 1});
            
            // Movimiento hacia arriba
            cola.offer(new int[]{fila - 1, columna, pasos + 1});
            
            // Movimiento hacia la izquierda
            cola.offer(new int[]{fila, columna - 1, pasos + 1});
        }
        
        return -1; // No hay solución
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


