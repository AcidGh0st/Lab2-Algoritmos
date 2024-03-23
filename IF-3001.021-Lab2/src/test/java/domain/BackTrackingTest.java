package domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BackTrackingTest {

    @Test
    void KnapsackTest() {
        Item itemList []= new Item[21];
        itemList[0]= new Item("SmartTV", 1000, 12);
        itemList[1]= new Item("Smart TV 65 pulg",800,10);
        itemList[2]= new Item("Smarte TV 32 pulg",600,7);
        itemList[3]= new Item("PS5",600,1.3);
        itemList[4]= new Item("LIBRO JAVA",25,0.7);
        itemList[5]= new Item("LIBRO ANSI C",40,1.2);
        itemList[6]= new Item("LIBRO C++",45,0.9);
        itemList[7]= new Item("LIBRO PYTHON",60,1.3);
        itemList[8]= new Item("Samsung",800,0.5);
        itemList[9]= new Item("Huawei",700,0.4);
        itemList[10]= new Item("iPhone 8",1000,0.3);
        itemList[11]= new Item("iPone 10",800,0.3);
        itemList[12]= new Item("iPone 13",700,0.3);
        itemList[13]= new Item("Xbox",600,1.5);
        itemList[14]= new Item("LapTop HP",500,3);
        itemList[15]= new Item("MacBook Air",1300,0.7);
        itemList[16]= new Item("Drone",200,4.5);
        itemList[17]= new Item("Impresora 3D",1200,5);
        itemList[18]= new Item("MacBook Air",1300,0.7);
        itemList[19]= new Item("Gafas  Realidad virtual",1700,0.4);
        itemList[20]= new Item("Proyector",300,1.2);

        Knapsack knapsack= new Knapsack(itemList,14.5);
        Knapsack knapsack1= new Knapsack(itemList,24.5);
        Knapsack knapsack2= new Knapsack(itemList,36.5);
        Knapsack knapsack3= new Knapsack(itemList,45.5);
        Knapsack knapsack4= new Knapsack(itemList,50.2);

        System.out.println(knapsack); //Esta llamando al ToString para mostrar la clase
        System.out.println();
        System.out.println(knapsack1);
        System.out.println();
        System.out.println(knapsack2);
        System.out.println();
        System.out.println(knapsack3);
        System.out.println();
        System.out.println(knapsack4);
    }

    @Test
    void testMaze() {
        int[][] maze1 = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
        };
        testAndPrintMaze(maze1, "Laberinto 1");

        int[][] maze2 = {
                {1, 0, 0, 1, 1, 0, 0, 0},
                {1, 0, 1, 1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 1, 0, 1, 0, 1, 1},
                {1, 0, 0, 1, 1, 1, 1, 0},
                {1, 0, 0, 1, 1, 0, 1, 1},
                {1, 0, 0, 1, 1, 0, 0, 1},
        };
        testAndPrintMaze(maze2, "Laberinto 2");

        int[][] maze3 = {
                {1, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 1, 1, 0, 0},
                {0, 1, 1, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 1, 1, 1},
                {1, 0, 0, 1, 1, 1, 1, 0},
                {1, 0, 0, 1, 1, 0, 1, 1},
                {1, 0, 0, 1, 1, 0, 0, 1},
        };
        testAndPrintMaze(maze3, "Laberinto 3");
    }

    void testAndPrintMaze(int[][] maze, String mazeName) {
        RatInMaze ratInMaze = new RatInMaze(maze.length);
        assertTrue(ratInMaze.solveMaze(maze));
        int[][] solution = ratInMaze.getSolution();
        System.out.println("\n" + mazeName + ": ");
        printMaze(solution);
    }

    void printMaze(int[][] maze) {
        for (int[] row : maze) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    @Test
    void nQueenTest() {
        NQueenProblem nQueen = new NQueenProblem();
        System.out.println("N Queen Problem for 4x4 board: \n");
        System.out.println(nQueen.solveNQueen(4));

        System.out.println("N Queen Problem for 8x8 board: \n");
        System.out.println(nQueen.solveNQueen(8));
    }

    @Test
    void solveKnightTour() {
        KnightTourProblem knightTour = new KnightTourProblem();

        System.out.println("Knight's Tour Problem for 8x8 board: \n");
        knightTour.resolverTour();
    }

    @Test
    void sudokuSolverTest() {
        int[][] board1 = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}};

        SudokuSolver sudokuSolver = new SudokuSolver(board1);

        System.out.println("Sudoku Original:");
        System.out.println(sudokuSolver.printBoard(board1));

        String solution = sudokuSolver.resolverSudoku();

        if (solution.equals("El sudoku no se puede resolver")) {
            System.out.println("No se pudo resolver el sudoku.");
        }
        else {
            System.out.println("Sudoku Resuelto:");
            System.out.println(solution);
        }
    }
}
