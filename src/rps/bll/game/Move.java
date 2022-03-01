package rps.bll.game;


/**
 * The various move options in the game
 *
 * @author smsj
 */
public enum Move {
    Rock("rock.png"),
    Paper("paper.png"),
    Scissor("scissor.png");

    final String imageURL;
    Move(String imageURL){
        this.imageURL = imageURL;
    }

    public String getImageURL(){
        return imageURL;
    }

}
