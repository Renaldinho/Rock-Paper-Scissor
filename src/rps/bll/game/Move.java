package rps.bll.game;


/**
 * The various move options in the game
 *
 * @author smsj
 */
public enum Move {
    Rock("rock.png",0),
    Paper("paperResized.png",1),
    Scissor("resizedScissor.png",2);

    final String imageURL;
    final int matrixIndex;
    public Move losesTo;

    Move(String imageURL,int matrixIndex){
        this.imageURL = imageURL;
        this.matrixIndex = matrixIndex;
    }

    public String getImageURL(){
        return imageURL;
    }

    public int getMatrixIndex() {
        return matrixIndex;
    }

    static {
        Rock.losesTo = Paper;
        Paper.losesTo = Scissor;
        Scissor.losesTo = Rock;
    }
}
