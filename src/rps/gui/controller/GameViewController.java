package rps.gui.controller;

// Java imports

import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import rps.bll.Utility;
import rps.bll.game.*;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {

    public AnchorPane anchorPane;
    public StackPane stackPane;
    @FXML
    private Label roundLabel,tieScore,botScore,humanScore;;

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
            utility.resize("/Users/aminaouina/Documents/GitHub/rps2022/Untitled/Resources/siLaminResized.png","/Users/aminaouina/Documents/GitHub/rps2022/Untitled/Resources/siLamin.png",89,210);
        } catch (IOException e) {
            e.printStackTrace();
        }

        vBox.setLayoutX(165);
        vBox.setLayoutY(96);


        //HBox.setHgrow(vBox, Priority.ALWAYS);




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

        ImageView imageView = new ImageView(new Image("backGround.png"));
        imageView.fitHeightProperty().bind(anchorPane.heightProperty());
        imageView.fitWidthProperty().bind(anchorPane.widthProperty());
        imageView.setLayoutX(0);
        imageView.setLayoutY(0);

        anchorPane.getChildren().add(imageView );
        vBox.getChildren().add(questionMark);
        stackPane.getChildren().add(vBox);

        //stackPane.layoutXProperty().set(100);

        ImageView imageView0 = new ImageView(new Image("siLamin.png"));

        stackPane.getChildren().add(imageView0);
        stackPane.setAlignment(imageView0,Pos.BOTTOM_CENTER);




        ImageView rockImage = new ImageView(new Image("rockResized.png"));
        rockImage.setOnMouseClicked(event -> handlePlayRound(Move.Rock));
        ImageView paperImage = new ImageView(new Image("paperResized.png"));
        paperImage.setOnMouseClicked(event -> handlePlayRound(Move.Paper));
        ImageView scissorImage = new ImageView(new Image("resizedScissor.png"));
        scissorImage.setOnMouseClicked(event -> handlePlayRound(Move.Scissor));

       anchorPane.getChildren().add(rockImage);
       rockImage.setLayoutY(30);
        anchorPane.getChildren().add(paperImage);
        paperImage.setLayoutY(90);
        anchorPane.getChildren().add(scissorImage);
        scissorImage.setLayoutY(140);




    }

    @FXML
    private void handlePaper(MouseEvent mouseEvent) {
    }

    @FXML
    private void handleRock(MouseEvent mouseEvent) {

    }

    @FXML
    private void handleScissors(MouseEvent mouseEvent) {

    }

    private void handlePlayRound(Move playerMove) {
        Result result = gameManager.playRound(playerMove);

        updateGameState(result);
    }

    private void updateGameState(Result result) {
        roundLabel.setText(String.valueOf(result.getRoundNumber()));

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
