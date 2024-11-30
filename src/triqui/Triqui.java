package triqui;

import java.util.Scanner;

public class Triqui {
    public static void startGame() {
        // 1. Identificar el primer jugador X o O
        // 2. Inicializar y mostrar el tablero
        // 3. Leer el movimiento del jugador
        // 4. Verificar si hizo 3 en linea
        // Si lo hizo, diga gano!
        // Sino
        // cambio de jugador
        // volver a 3
        var scanner = new Scanner(System.in);
        var player1 = 'X';
        var isFinished = false;
        var counter = 0;
        var board = initializeBoard();
        do {
            System.out.println();
            showBoard(board);
            System.out.println();
            System.out.printf("Jugador %c ingresa tu movimiento: %n", player1);
            System.out.print("Fila (1-3): ");
            var row = scanner.nextInt()-1;
            System.out.print("Columna (1-3): ");
            var column = scanner.nextInt()-1;

            if (makeMove(board, row, column, player1)) {
                counter++;
                if (checkWinner(board, player1)) {
                    System.out.println();
                    showBoard(board);
                    System.out.println();
                    System.out.println("El jugador " + player1 + " ha ganado.");
                    // Terminar el programa
                    isFinished = true;
                } else {
                    player1 = player1 == 'X' ? 'O' : 'X';
                }
            } else {
                System.err.println("Movimiento inválido. Intenta de nuevo.");
            }
            if (counter==9) {
                System.out.println();
                showBoard(board);
                System.out.println("Han quedado empatados.");
                isFinished = true;
            }
        } while (!isFinished);

        scanner.close();
    }

    private static boolean checkWinner(char[][] board, char player1) {
        for (var row : board) {
            if (row[0] == player1 && row[1] == player1 && row[2] == player1) {
                return true;
            }
        }
        // Validar columnas
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == player1 && board[1][i] == player1 && board[2][i] == player1) {
                return true;
            }
        }
        // Validar diagonales
        if (board[0][0] == player1 && board[1][1] == player1 && board[2][2] == player1) {
            return true;
        }
        if (board[0][2] == player1 && board[1][1] == player1 && board[2][0] == player1) {
            return true;
        }
        return false;
    }

    private static boolean makeMove(char[][] board, int row, int column, char player1) {
        if (board[row][column] != ' ') {
            // Ya hay una valor en esta posición
            return false;
        }
        board[row][column] = player1;
        return true;
    }

    private static void showBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
                if (j < board[i].length - 1) {
                    System.out.print('|');
                }
            }
            System.out.println();
            if (i < board.length - 1) {
                System.out.println("-----");
            }
        }

    }

    public static char[][] initializeBoard() {
        var board = new char[3][3];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
        return board;
    }
}
