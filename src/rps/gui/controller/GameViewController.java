package rps.gui.controller;

// Java imports

import java.awt.*;
import java.awt.image.*;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
public class GameViewController implements Initializable {

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
    private Utility utility;
    VBox vBox ;

    public GameViewController(){
        human = new Player("Renars", PlayerType.Human);
        bot = new Player("Bot",PlayerType.AI);
        gameManager = new GameManager(human,bot);

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HBox scissorBox= new HBox();
        HBox paperBox = new HBox();
        HBox rockBox = new HBox();
        vBox = new VBox();
        utility=new Utility();
        /**
         * resized images
         */
        try {
            utility.resize("/Users/aminaouina/Documents/GitHub/rps2022/Untitled/Resources/slot_machine_png.png","/Users/aminaouina/Documents/GitHub/rps2022/Untitled/Resources/slot_machine_resized.png",450,450);
        } catch (IOException e) {
            e.printStackTrace();
        }
        anchorPane.getChildren().add(vBox);
        anchorPane.getChildren().add(new ImageView(new Image("slot_machine_resized.png")));



        for (int j =1;j<=3;j++) {
                scissorBox.getChildren().add(new ImageView(new Image("resizedScissor.png")));
                paperBox.getChildren().add(new ImageView(new Image("paperResized.png")));
                rockBox.getChildren().add(new ImageView(new Image("rockResized.png")));
            }
            vBox.getChildren().add(scissorBox);
            vBox.getChildren().add(paperBox);
            vBox.getChildren().add(rockBox);
            vBox.setLayoutX(160);
            vBox.setLayoutY(170);


        rockImage.setImage(new Image("rock.png"));
        paperImage.setImage(new Image("paper.png"));
        scissorImage.setImage(new Image("scissor.png"));


    }

    @FXML
    private void handlePaper(MouseEvent mouseEvent) {
        TranslateTransition tt = new TranslateTransition();
        tt.setDuration(Duration.seconds(2));
        tt.setNode(vBox);
        tt.setToY(100);
        tt.play();
        handlePlayRound(Move.Paper);
    }

    @FXML
    private void handleRock(MouseEvent mouseEvent) {
        TranslateTransition tt = new TranslateTransition();
        tt.setDuration(Duration.seconds(2));
        tt.setNode(vBox);
        tt.setToY(100);
        tt.play();
        handlePlayRound(Move.Rock);
    }

    @FXML
    private void handleScissors(MouseEvent mouseEvent) {
        TranslateTransition tt = new TranslateTransition();
        tt.setDuration(Duration.seconds(2));
        tt.setNode(vBox);
        tt.setToY(100);
        tt.play();
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

        if (result.getType()== ResultType.Tie)
            tieScore.setText(String.valueOf(Integer.valueOf(tieScore.getText())+1));
        else if (result.getWinnerPlayer().getPlayerType()==PlayerType.AI)
            botScore.setText(String.valueOf(Integer.valueOf(botScore.getText())+1));
        else
            humanScore.setText(String.valueOf(Integer.valueOf(botScore.getText())+1));
    }

}
