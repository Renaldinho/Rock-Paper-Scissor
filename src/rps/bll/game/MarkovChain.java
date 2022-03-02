package rps.bll.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
        //printMatrix();

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

    /**
     * This method used the data from previous turns and provides the counter to the move that the player is most likely to use
     */
    public static Move getMostProbable(){
        int mostCommonMoveIndex = -1;
        int mostCommonMoveCount = 0;

        for (int i = 0; i < matrix.length; i++) {//Find the most common next move from the context of the previous move
            if (matrix[i][previousMove.matrixIndex]>mostCommonMoveCount){
                mostCommonMoveIndex = i;
                mostCommonMoveCount = matrix[i][previousMove.matrixIndex];
            }
        }
        Move nextPlayerMove = (mostCommonMoveIndex==-1) ? getRandomMove() : getMoveByIndex(mostCommonMoveIndex);//The move the player would be most likely to play next
        Move optimalMove = nextPlayerMove.losesTo; //gets the counter move to the move the player is most likely to use

        return optimalMove;
    }

    /**
     * This method uses the data from previous moves of the player gives each move a probability and then chooses randomly based
     * on this probability
     */
    public static Move getRandomProbable(){
        Random random = new Random();

        int totalOccurrences = 0;
        HashMap<Integer,Double> occurrenceMap = new HashMap<>();


        for (int i = 0; i < matrix.length; i++) {
            totalOccurrences+=matrix[i][previousMove.matrixIndex];
        }//add up total amount of occurrences

        for(int i = 0; i < matrix.length; i++){
            occurrenceMap.put(i,(double) matrix[i][previousMove.matrixIndex]/totalOccurrences);
        }//add the move index and probability to a hashmap (index being the keyset and probavility the key value)


        Double p = Double.parseDouble(String.format("%.3f",random.nextDouble(1)));//Random decimal between 0 and 1
        double accumulativeProbability = 0;
        for (Integer index: occurrenceMap.keySet()){
            accumulativeProbability += occurrenceMap.get(index);
            if (p <= accumulativeProbability){
                return getMoveByIndex(index).losesTo;
            }
        }
        return getRandomMove();
    }

    private static Move getMoveByIndex(int index) {
        Move[] values = Move.values();
        return values[index];
    }

    private static Move getRandomMove() {
        Move[] values = Move.values();
        int size = values.length;
        Random random = new Random();

        return values[random.nextInt(size)];
    }
}
