package domain;

import java.util.Arrays;

public class SudokuSolver {

    private final int cuadricula = 9;
    private int[][] boardOriginal = {};

    //Constructor
    public SudokuSolver(int[][] tablero) {
        this.boardOriginal = tablero;
    }

    public void setBoard(int[][] tablero) {
        this.boardOriginal = tablero;
    }

    //Cambiar el arreglo a String
    public String tableroOriginal() {
        return printBoard(boardOriginal);
    }


    public String resolverSudoku() {
        int[][] boardClon = clon(boardOriginal);//Clonar tablero
        if (resuelveSudoku(boardClon)) {//Se puede resolver el board
            return printBoard(boardClon);//imprime tablero
        } else {
            return "El sudoku no se puede resolver";
        }
    }

    //Método de clonar tablero
    private int[][] clon(int[][] original) {
        int[][] clone = new int[cuadricula][cuadricula];
        for (int i = 0; i < cuadricula; i++) {
            clone[i] = Arrays.copyOf(original[i], original[i].length);
        }
        return clone;
    }

    //Verificar si en la fila está el número
    private boolean vFila(int[][] tablero, int numero, int fila) {
        for (int i = 0; i < cuadricula; i++) {
            if (tablero[fila][i] == numero) {
                return true;
            }
        }
        return false;
    }

    //Verificar si en la columna está el número
    private boolean vColumna(int[][] tablero, int columna, int numero) {
        for (int i = 0; i < cuadricula; i++) {
            if (tablero[i][columna] == numero) {
                return true;
            }
        }
        return false;
    }


    //Verificar si el numero esta en la caja de 3x3
    private boolean numCaja(int[][] tablero, int num, int fila, int columna) {
        int cajaFila = fila - fila % 3;
        int cajaColumna = columna - columna % 3;
        for (int i = cajaFila; i < cajaFila + 3; i++) {
            for (int j = cajaColumna; j < cajaColumna + 3; j++) {
                if (tablero[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    //Verificar posicion válid
    private boolean posValid(int[][] tablero, int fila, int columna, int numero) {
        return !(vFila(tablero, numero, fila) || vColumna(tablero, columna, numero) || numCaja(tablero, numero, fila, columna));
    }

    //Imprimir el tablero
    public String printBoard(int[][] tablero) {
        StringBuilder resultado = new StringBuilder();
        for (int fila = 0; fila < cuadricula; fila++) {
            for (int columna = 0; columna < cuadricula; columna++) {
                resultado.append(tablero[fila][columna]).append(" ");
            }
            resultado.append("\n");
        }
        return resultado.toString();
    }


    private boolean resuelveSudoku(int[][] tablero) {
        for (int fila = 0; fila < cuadricula; fila++) {
            for (int columna = 0; columna < cuadricula; columna++) {
                if (tablero[fila][columna] == 0) {
                    for (int numPrueba = 1; numPrueba <= cuadricula; numPrueba++) {
                        if (posValid(tablero, fila, columna, numPrueba)) {
                            tablero[fila][columna] = numPrueba;
                            if (resuelveSudoku(tablero)) {
                                return true;
                            } else {
                                tablero[fila][columna] = 0;//Borrar el número
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public String getOriginalBoard() {
        return printBoard(boardOriginal);
    }

    public String getSudokuSolution() {
        return resolverSudoku();
    }
}
