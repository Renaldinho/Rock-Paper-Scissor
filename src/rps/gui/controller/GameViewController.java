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
    HBox rockBox = new HBox();
    VBox vBox ;

    public GameViewController(){
        human = new Player("Renars", PlayerType.Human);
        bot = new Player("Bot",PlayerType.AI);
        gameManager = new GameManager(human,bot);

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        HBox scissorBox= new HBox();
        HBox questionMark= new HBox();
        HBox paperBox = new HBox();

        vBox = new VBox();

        utility=new Utility();
        /**
         * resized images
         */
        try {
            utility.resize("/Users/aminaouina/Documents/GitHub/rps2022/Untitled/Resources/scissorSiLamin.png","/Users/aminaouina/Documents/GitHub/rps2022/Untitled/Resources/resizedScissor.png",38,60);
        } catch (IOException e) {
            e.printStackTrace();
        }

        vBox.setLayoutX(165);
        vBox.setLayoutY(96);


        //HBox.setHgrow(vBox, Priority.ALWAYS);
        anchorPane.getChildren().add(vBox);
        ImageView imageView = new ImageView(new Image("slot_machine_resized.png"));
        imageView.setLayoutX(85);
        imageView.setLayoutY(0);
        anchorPane.getChildren().add(imageView );



        for (int j =1;j<=3;j++) {
                questionMark.getChildren().add(new ImageView(new Image("question_mark_resized.png")));
                scissorBox.getChildren().add(new ImageView(new Image("resizedScissor.png")));
                paperBox.getChildren().add(new ImageView(new Image("paperResized.png")));
                rockBox.getChildren().add(new ImageView(new Image("rockResized.png")));
            }

            vBox.getChildren().add(scissorBox);
            //vBox.getChildren().add(new ImageView(new Image("white_gap_resized.png")));
             //vBox.getChildren().add(new ImageView(new Image("white_gap_resized.png")));

        vBox.getChildren().add(paperBox);
        //vBox.getChildren().add(new ImageView(new Image("white_gap_resized.png")));

        //vBox.getChildren().add(new ImageView(new Image("white_gap_resized.png")));

            vBox.getChildren().add(rockBox);
        vBox.getChildren().add(questionMark);
        vBox.setLayoutX(250);
        vBox.setLayoutY(95);



        rockImage.setImage(new Image("rock.png"));
        paperImage.setImage(new Image("paper.png"));
        scissorImage.setImage(new Image("scissor.png"));



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

        Move botMove = (result.getLoserPlayer().getPlayerType()==PlayerType.AI) ?
                result.getLoserMove() :
                result.getWinnerMove();

        TranslateTransition tt = new TranslateTransition();
        tt.setDuration(Duration.seconds(0.5));
        tt.setNode(vBox);
        tt.setToY(180);
        tt.play();
        tt.setToY(-180);
        tt.play();

        if(botMove.equals(Move.Rock))
            {
                TranslateTransition ttr = new TranslateTransition();
                ttr.setDuration(Duration.seconds(0.5));
                ttr.setNode(vBox);
                ttr.setToY(60);
                ttr.play();
                //System.out.println("Rock");
            }
            else if (botMove.equals(Move.Scissor))
            {
                TranslateTransition ttr = new TranslateTransition();
                ttr.setDuration(Duration.seconds(0.5));
                ttr.setNode(vBox);
                ttr.setToY(180);
                ttr.play();
                //System.out.println("Scissor");
            }
            else  {
                TranslateTransition ttr = new TranslateTransition();
                ttr.setDuration(Duration.seconds(0.5));
                ttr.setNode(vBox);
                ttr.setToY(120);
                ttr.play();
                //System.out.println("Paper");
            }


        if (result.getType()== ResultType.Tie)
            tieScore.setText(String.valueOf(Integer.valueOf(tieScore.getText())+1));
        else if (result.getWinnerPlayer().getPlayerType()==PlayerType.AI)
            botScore.setText(String.valueOf(Integer.valueOf(botScore.getText())+1));
        else
            humanScore.setText(String.valueOf(Integer.valueOf(botScore.getText())+1));
    }

}
