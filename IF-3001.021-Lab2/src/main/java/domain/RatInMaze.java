package domain;

public class RatInMaze {
    private int N;
    private int[][] solution;

    public RatInMaze(int N) {
        this.N = N;
        this.solution = new int[N][N];
    }

    // Función para resolver el laberinto utilizando backtracking
    private boolean solveMazeUtil(int maze[][], int x, int y, int solve[][]) {
        // Si la posición actual es la posición final del laberinto
        if (x == N - 1 && y == N - 1) {
            solve[x][y] = 2; // Marca la posición como parte de la solución
            return true;
        }

        // Marca la posición actual como parte de la solución
        solve[x][y] = 2;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Explora todas las direcciones posibles
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            // Verifica si la próxima posición está dentro de los límites del laberinto
            if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && solve[nextX][nextY] != 2 && maze[nextX][nextY] != 0) {
                if (solveMazeUtil(maze, nextX, nextY, solve)) {
                    return true;
                }
            }
        }
        // Si ninguna dirección es viable, retrocede y marca esta posición como no visitada
        solve[x][y] = 0;
        return false;
    }

    public boolean solveMaze(int maze[][]) { // Función para resolver el laberinto y devolver la solución
        int solve[][] = new int[N][N];

        if (!solveMazeUtil(maze, 0, 0, solve)) {
            return false;
        }

        // Copiar la solución encontrada al atributo solution
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // Si no es una parte de la solución, marcar como pared (0) o espacio vacío (1)
                if (solve[i][j] != 2) {
                    solution[i][j] = maze[i][j];
                } else {
                    solution[i][j] = 2;
                }
            }
        }
        return true;
    }

    public int[][] getSolution() {
        return solution;
    }
}
