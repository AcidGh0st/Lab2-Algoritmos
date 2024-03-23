package domain;

public class NQueenProblem {

    public String solveNQueen(int n){
        String result="";
        int board[][] = new int[n][n];
        if(placeQueens(board, 0)){ //Parta de la columna cero

            result+= printBoard(board);
        }else {
            result+= "Solution does not exist for a " + n + "x" + n + " board";

        }
        return result;

    }

    private boolean placeQueens(int[][] board, int col) {
        // Caso base: todas las reinas han sido colocadas
        if (col == board.length) {
            return true;
        }

        // Probar cada fila en esta columna
        for (int row = 0; row < board.length; row++) {
            // Verificar si es seguro colocar una reina en esta posición
            if (isSafe(board, row, col)) {
                // Colocar una reina en esta posición
                board[row][col] = 1;

                // Llamar recursivamente para colocar la siguiente reina en la siguiente columna
                if (placeQueens(board, col + 1)) {
                    return true;
                }

                // Si no es posible colocar la siguiente reina, retroceder y deshacer el movimiento actual
                board[row][col] = 0;
            }
        }

        // No se pudo colocar una reina en esta columna
        return false;
    }

    private boolean isSafe(int[][] board, int row, int col) {
        // Verificar si hay una reina en la misma fila
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        // Verificar si hay una reina en la diagonal superior izquierda
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Verificar si hay una reina en la diagonal inferior izquierda
        for (int i = row, j = col; i < board.length && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Es seguro colocar una reina en esta posición
        return true;
    }

    private String printBoard(int[][] board) {

        String result = "";
        int n = board.length;
        for (int i = 0; i <  n; i++) {
            for (int j = 0; j < n ; j++) {
                result += board[i][j] + " ";

            }
            result += "\n";

        }
        return result;

    }


}