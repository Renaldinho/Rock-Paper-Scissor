package rps.bll.game;

import java.awt.*;

/**
 * The various move options in the game
 *
 * @author smsj
 */
public enum Move {
    Rock("rock.png"),
    Paper("paper.png"),
    Scissor("scissor.png");

    String imageURL;
    Move(String imageURL){
        this.imageURL = imageURL;
    }

    public String getImageURL(){
        return imageURL;
    }

}
