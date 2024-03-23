package domain;

import java.util.Arrays;

public class KnightTourProblem {
    // Tamaño del tablero de ajedrez
    private static final int SIZE = 8;
    // Posibles movimientos del caballo en forma de par de coordenadas (x, y)
    private static final int[][] MOVIMIENTOS = {
            {-2, -1}, {-1, -2}, {1, -2}, {2, -1},
            {2, 1}, {1, 2}, {-1, 2}, {-2, 1}
    };

    // Representación del tablero
    private int[][] tablero;

    // Constructor para inicializar el tablero con valores predeterminados
    public KnightTourProblem() {
        tablero = new int[SIZE][SIZE];
        // Inicializar el tablero con -1 en todas las casillas
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tablero[i][j] = -1;
            }
        }
    }

    // Método principal para resolver el problema del recorrido del caballo
    public void resolverTour() {
        // Empezar el recorrido desde la casilla (0,0)
        tablero[0][0] = 0;
        // Llamar al método recursivo para resolver el recorrido
        if (resolverTourRecursivo(0, 0, 1)) {
            // Imprimir el tablero si se encontró una solución
            imprimirTablero();
        } else {
            // Si no hay solución posible, imprimir un mensaje
            System.out.println("No hay solución posible.");
        }
    }

    // Método recursivo para encontrar el recorrido del caballo
    private boolean resolverTourRecursivo(int x, int y, int movimientosHechos) {
        // Verificar si se han hecho todos los movimientos posibles
        if (movimientosHechos == SIZE * SIZE) {
            return true;
        }

        // Iterar sobre todos los movimientos posibles del caballo
        for (int i = 0; i < 8; i++) {
            int nextX = x + MOVIMIENTOS[i][0];
            int nextY = y + MOVIMIENTOS[i][1];

            // Verificar si el próximo movimiento es válido
            if (esMovimientoValido(nextX, nextY)) {
                // Marcar la casilla con el número de movimiento
                tablero[nextX][nextY] = movimientosHechos;

                // Llamar recursivamente al método para el próximo movimiento
                if (resolverTourRecursivo(nextX, nextY, movimientosHechos + 1)) {
                    return true;
                }

                // Deshacer el movimiento si no lleva a una solución
                tablero[nextX][nextY] = -1;
            }
        }
        // Si no se encontró una solución, retornar falso
        return false;
    }

    // Método para verificar si un movimiento es válido
    private boolean esMovimientoValido(int x, int y) {
        // Verificar si las coordenadas están dentro del tablero y si la casilla no ha sido visitada
        return (x >= 0 && x < SIZE && y >= 0 && y < SIZE && tablero[x][y] == -1);
    }

    // Método para imprimir el tablero
    private void imprimirTablero() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // Imprimir el valor de cada casilla
                System.out.print(tablero[i][j] + "\t");
            }
            // Nueva línea después de imprimir una fila completa
            System.out.println();
        }
    }

    // Nuevo método para obtener el tablero
    public int[][] getTablero() {
        return tablero;
    }
}
