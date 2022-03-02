package rps.gui.controller;

// Java imports

import java.awt.*;
import java.awt.image.*;

import javafx.animation.TranslateTransition;
import javafx.beans.Observable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import rps.bll.Utility;
import rps.bll.game.*;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewTestController implements Initializable {

    public AnchorPane anchorPane;
    @FXML
    private Label roundLabel,tieScore,botScore,humanScore;;
    @FXML
    private ImageView scissorImage,rockImage,paperImage;
    @FXML
    private ImageView botMovieImage;

    private GameManager gameManager;
    private Player human;
    private Player bot;

    public GameViewTestController(){
        human = new Player("Renars", PlayerType.Human);
        bot = new Player("Bot",PlayerType.AI);
        gameManager = new GameManager(human,bot);

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        rockImage.setImage(new Image(Move.Rock.getImageURL()));
        paperImage.setImage(new Image(Move.Paper.getImageURL()));
        scissorImage.setImage(new Image(Move.Scissor.getImageURL()));
    }

    @FXML
    private void handlePaper(MouseEvent mouseEvent) {
        handlePlayRound(Move.Paper);
    }

    @FXML
    private void handleRock(MouseEvent mouseEvent) {
        handlePlayRound(Move.Rock);
    }

    @FXML
    private void handleScissors(MouseEvent mouseEvent) {

        handlePlayRound(Move.Scissor);
    }

    private void handlePlayRound(Move playerMove) {
        Result result = gameManager.playRound(playerMove);

        updateGameState(result);
    }

    private void updateGameState(Result result) {
        roundLabel.setText(String.valueOf(result.getRoundNumber()));

        Image botImage = (result.getLoserPlayer().getPlayerType()==PlayerType.AI) ?
                new Image(result.getLoserMove().getImageURL()) :
                new Image(result.getWinnerMove().getImageURL());
        botMovieImage.setImage(botImage);

        /*Move botMove = (result.getLoserPlayer().getPlayerType()==PlayerType.AI) ?
                result.getLoserMove() :
                result.getWinnerMove();

         */

        if (result.getType()== ResultType.Tie)
            tieScore.setText(String.valueOf(Integer.valueOf(tieScore.getText())+1));
        else if (result.getWinnerPlayer().getPlayerType()==PlayerType.AI)
            botScore.setText(String.valueOf(Integer.valueOf(botScore.getText())+1));
        else
            humanScore.setText(String.valueOf(Integer.valueOf(humanScore.getText())+1));
    }

}
