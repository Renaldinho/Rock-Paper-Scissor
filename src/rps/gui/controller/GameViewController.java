package rps.gui.controller;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;
import rps.bll.game.*;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {
    @FXML
    private StackPane stackPane;

     Label roundNumberLabel,tieScoreLabel=new Label(),botScoreLabel=new Label(),humanScoreLabel= new Label();

    private final GameManager gameManager;
    private Player human;
    ImageView imageViewBackground = new ImageView(new Image("aasba.png"));

    private Player bot;
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
        HBox rockBox = new HBox();

        VBox labelVBox= new VBox();
        HBox tieScore = new HBox();
        HBox winScore = new HBox();
        HBox lossScore = new HBox();

        tieScore.getChildren().add(new Label("Tie(s)"));
        tieScore.getChildren().add(tieScoreLabel);

        winScore.getChildren().add(new Label("Win(s)"));
        winScore.getChildren().add(humanScoreLabel);

        lossScore.getChildren().add(new Label("Loss(es)"));
        lossScore.getChildren().add(botScoreLabel);

        labelVBox.getChildren().add(winScore);
        labelVBox.getChildren().add(lossScore);
        labelVBox.getChildren().add(tieScore);


        vBox = new VBox(3);

        /**
         * resized images
         */
         /*Utility utility=new Utility();
        try {
            utility.resize("/Users/aminaouina/Documents/GitHub/rps2022/Untitled/Resources/HandClipart.png","/Users/aminaouina/Documents/GitHub/rps2022/Untitled/Resources/scissorSiLamin.png",150,150);
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        for (int j =1;j<=3;j++) {
                questionMark.getChildren().add(new ImageView(new Image("qm00.png")));
                scissorBox.getChildren().add(new ImageView(new Image("jiboujibouscissor.png")));
                paperBox.getChildren().add(new ImageView(new Image("zebizebi111.png")));
                rockBox.getChildren().add(new ImageView(new Image("jibijibi.png")));
            }

        vBox.getChildren().add(questionMark);

        vBox.getChildren().add(rockBox);

        vBox.getChildren().add(paperBox);

        vBox.getChildren().add(scissorBox);


        vBox.translateXProperty().set(210);
        vBox.translateYProperty().set(198);

        stackPane.getChildren().add(vBox);

        imageViewBackground.fitWidthProperty().bind(stackPane.widthProperty().multiply(1));
        imageViewBackground.fitHeightProperty().bind(stackPane.heightProperty().multiply(1));

        stackPane.getChildren().add(imageViewBackground);
        stackPane.getChildren().add(labelVBox);

        ImageView imageViewPaper = new ImageView(new Image("zebizebi.png"));
        imageViewPaper.setOnMouseClicked(event -> {
            try {
                handlePlayRound(Move.Paper);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        ImageView imageViewScissor = new ImageView(new Image("zebizebiversion2.png"));
        imageViewScissor.setOnMouseClicked(event -> {
            try {
                handlePlayRound(Move.Scissor);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        ImageView imageViewRock = new ImageView(new Image("zebizebiversion3.png"));
        imageViewRock.setOnMouseClicked(event -> {
            try {
                handlePlayRound(Move.Rock);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        VBox vbox = new VBox(20);
        vbox.getChildren().add(imageViewPaper);
        vbox.getChildren().add(imageViewScissor);
        vbox.getChildren().add(imageViewRock);
        vbox.translateXProperty().set(440);
        vbox.translateYProperty().set(48);

        stackPane.getChildren().add(vbox);


    }

    private void handlePlayRound(Move playerMove) throws InterruptedException {
        Result result = gameManager.playRound(playerMove);

        updateGameState(result);
    }

    private void updateGameState(Result result) throws InterruptedException {
        //roundLabel.setText(String.valueOf(result.getRoundNumber()));

        Move botMove = (result.getLoserPlayer().getPlayerType()==PlayerType.AI) ?
                result.getLoserMove() :
                result.getWinnerMove();
       // Thread thread = new Thread(()->System.out.println("aasba" ));


        /*Thread firstThread = new Thread(new Runnable() {
            @Override
            public void run() {

                TranslateTransition tt = new TranslateTransition();
                tt.setDuration(Duration.seconds(0.5));
                tt.setNode(vBox);
                tt.setToY(92);
                tt.setCycleCount(4);
                tt.autoReverseProperty().set(true);
                tt.play();
                try {
                    tt.wait((long) tt.getDuration().toMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });*/




        switch (botMove){

            case Rock -> {
                TranslateTransition ttr = new TranslateTransition();
                ttr.setDuration(Duration.seconds(0.2));
                ttr.setNode(vBox);
                ttr.setToY(161);
                ttr.setCycleCount(3);
                ttr.autoReverseProperty().set(true);
                ttr.play();
            }
            case Scissor ->{TranslateTransition ttr = new TranslateTransition();
                ttr.setDuration(Duration.seconds(0.2));
                ttr.setNode(vBox);
                ttr.setToY(92);
                ttr.setCycleCount(3);
                ttr.autoReverseProperty().set(true);
                ttr.play();}
            case Paper -> {
                TranslateTransition ttr = new TranslateTransition();
                ttr.setDuration(Duration.seconds(0.2));
                ttr.setNode(vBox);
                ttr.setToY(126);
                ttr.setCycleCount(3);
                ttr.autoReverseProperty().set(true);
                ttr.play();
            }
        }
        if (result.getType()== ResultType.Tie){
            if (tieScoreLabel.getText().isEmpty())
                tieScoreLabel.setText("1");
            else
            tieScoreLabel.setText(String.valueOf(Integer.parseInt(tieScoreLabel.getText())+1));}
        else if (result.getWinnerPlayer().getPlayerType()==PlayerType.AI){
            if (botScoreLabel.getText().isEmpty())
                botScoreLabel.setText("1");
            else
            botScoreLabel.setText(String.valueOf(Integer.parseInt(botScoreLabel.getText())+1));}
        else{
            if (humanScoreLabel.getText().isEmpty())
                humanScoreLabel.setText("1");
            else
            humanScoreLabel.setText(String.valueOf(Integer.parseInt(botScoreLabel.getText())+1));}
}
}
