package rps.bll.game;

public class MarkovChain {


    private static Move previousMove = null;
    private static int[][] matrix = new int[3][3];

    public static int[][] getMatrix() {
        return matrix;
    }

    public static void recordMove(Move move){
        if(previousMove==null){
            previousMove=move;
            return;
        }
        int previousIndex = previousMove.matrixIndex;
        int currentIndex = move.matrixIndex;

        matrix[currentIndex][previousIndex] ++;
        printMatrix();

        previousMove = move;
    }

    private static void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public static Move getNextOptimal(){
        int mostCommonMoveIndex = 0;
        int mostCommonMoveCount = 0;

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][previousMove.matrixIndex]>mostCommonMoveCount){
                mostCommonMoveIndex = i;
                mostCommonMoveCount = matrix[i][previousMove.matrixIndex];
            }
        }
        Move nextPlayerMove = getMoveByIndex(mostCommonMoveIndex);//The move the player would be most likely to play next
        Move optimalMove = nextPlayerMove.losesTo;

        System.out.println(nextPlayerMove + " " + optimalMove);

        return optimalMove;
    }

    private static Move getMoveByIndex(int index) {
        Move[] values = Move.values();
        return values[index];
    }
}
