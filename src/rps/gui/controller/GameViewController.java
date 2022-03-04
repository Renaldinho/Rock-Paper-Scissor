package rps.gui.controller;
import javafx.animation.TranslateTransition;
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
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {
    @FXML
    private StackPane stackPane;
    private int balance = 250;

    private final Label roundNumberLabel=new Label();
    private final Label tieScoreLabel=new Label();
    private final Label botScoreLabel=new Label();
    private final Label humanScoreLabel= new Label();
    private final Label scoreLabel= new Label("Score:  ");
    private final Label balanceLabel = new Label(balance+" available");
    private final Label moneyWonLabel = new Label();
    private final Label moneyLostLabel= new Label();
    private int moneyWon=0;
    private int moneyLost=0;
    ImageView imageViewPaper = new ImageView(new Image("zebizebi.png"));
    ImageView imageViewScissor = new ImageView(new Image("zebizebiversion2.png"));
    ImageView imageViewRock = new ImageView(new Image("zebizebiversion3.png"));




    private final GameManager gameManager;
    ImageView imageViewBackground = new ImageView(new Image("aasba.png"));

    VBox vBox ;

    public GameViewController(){
        Player human = new Player("Renars", PlayerType.Human);
        Player bot = new Player("Bot", PlayerType.AI);
        gameManager = new GameManager(human, bot);

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        balanceLabel.getStyleClass().add("balance-label");
        scoreLabel.getStyleClass().add("scoreLabel");


        StackPane.setAlignment(scoreLabel,Pos.CENTER_LEFT);

        HBox scissorBox= new HBox();
        HBox questionMark= new HBox();
        HBox paperBox = new HBox();
        HBox rockBox = new HBox();

        VBox labelVBox= new VBox();
        HBox tieScore = new HBox();
        HBox winScore = new HBox();
        HBox lossScore = new HBox();

        VBox balance = new VBox(30);
        balance.translateXProperty().set(76);
        balance.translateYProperty().set(14);
        Label balanceLabel0 = new Label("Balance: ");
        balanceLabel0.translateYProperty().set(40);
        balanceLabel0.getStyleClass().add("balance-header");
        balance.getChildren().add(moneyWonLabel);
        moneyWonLabel.getStyleClass().add("money-won-label");
        balance.getChildren().add(moneyLostLabel);
        moneyLostLabel.getStyleClass().add("money-lost-label");

        HBox roundBox= new HBox();
        Label roundNumberHeader = new Label("Round number: ");
        roundNumberHeader.getStyleClass().add("roundNumberHeader");
        roundBox.getChildren().add(roundNumberHeader);
        roundNumberLabel.getStyleClass().add("RoundNumberLabel");
        roundBox.getChildren().add(roundNumberLabel);
        roundBox.setTranslateY(150);


        Label tieScoreLabelHeader = new Label("Tie(s): ");
        tieScoreLabelHeader.getStyleClass().add("tieScoreLabelHeader");
        tieScore.getChildren().add(tieScoreLabelHeader);
        tieScoreLabel.getStyleClass().add("tieScoreLabel");
        tieScore.getChildren().add(tieScoreLabel);

        Label winScoreLabelHeader = new Label("Win(s): ");
        winScoreLabelHeader.getStyleClass().add("winScoreLabelHeader");
        winScore.getChildren().add(winScoreLabelHeader);
        humanScoreLabel.getStyleClass().add("humanScoreLabel");
        winScore.getChildren().add(humanScoreLabel);

        Label lossScoreLabelHeader=new Label("Loss(es): ");
        lossScoreLabelHeader.getStyleClass().add("lossScoreLabelHeader");
        lossScore.getChildren().add(lossScoreLabelHeader);
        botScoreLabel.getStyleClass().add("botScoreLabel");
        lossScore.getChildren().add(botScoreLabel);

        labelVBox.getChildren().add(winScore);
        labelVBox.getChildren().add(tieScore);
        labelVBox.getChildren().add(lossScore);
        labelVBox.setTranslateY(172);
        labelVBox.setTranslateX(50);


        vBox = new VBox(3);

        /**
         * resize images
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
        balanceLabel.translateYProperty().set(-150);
        balanceLabel.translateXProperty().set(-148);
        stackPane.getChildren().add(balanceLabel);

        ImageView money = new ImageView(new Image("flous.png"));
        money.translateXProperty().set(-220);
        money.translateYProperty().set(-124);

        stackPane.getChildren().add(money);

        stackPane.getChildren().add(balanceLabel0);
        stackPane.setAlignment(balanceLabel0,Pos.TOP_LEFT);

        stackPane.getChildren().add(labelVBox);
        stackPane.getChildren().add(roundBox);
        stackPane.getChildren().add(scoreLabel);
        stackPane.getChildren().add(balance);

        imageViewPaper.setOnMouseClicked(event -> {
            try {
                handlePlayRound(Move.Paper);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        imageViewScissor.setOnMouseClicked(event -> {
            try {
                handlePlayRound(Move.Scissor);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
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

    private void gameOver() {
        ImageView gameOverImageView =new ImageView(new Image("zebizebi2022.png"));
        stackPane.getChildren().add(gameOverImageView);
        stackPane.setAlignment(gameOverImageView,Pos.BOTTOM_CENTER);

        imageViewPaper.setOnMouseClicked(event -> {});
        imageViewRock.setOnMouseClicked(event -> {});
        imageViewScissor.setOnMouseClicked(event -> {});
    }



    private void handlePlayRound(Move playerMove) throws InterruptedException {
        Result result = gameManager.playRound(playerMove);

        updateGameState(result);
    }

    private void updateGameState(Result result)  {
        roundNumberLabel.setText(String.valueOf(result.getRoundNumber()));

        Move botMove = (result.getLoserPlayer().getPlayerType()==PlayerType.AI) ?
                result.getLoserMove() :
                result.getWinnerMove();

        switch (botMove){

            case Rock -> {
                TranslateTransition ttr = new TranslateTransition();
                ttr.setDuration(Duration.seconds(0.2));
                ttr.setNode(vBox);
                ttr.setToY(161);
                if(vBox.translateYProperty().get()==198)
                    ttr.setCycleCount(3);
                else
                    ttr.setCycleCount(1);
                ttr.autoReverseProperty().set(true);
                ttr.play();
            }
            case Scissor ->{TranslateTransition ttr = new TranslateTransition();
                ttr.setDuration(Duration.seconds(0.2));
                ttr.setNode(vBox);
                ttr.setToY(92);
                if(vBox.translateYProperty().get()==198)
                    ttr.setCycleCount(3);
                else
                ttr.setCycleCount(1);
                ttr.autoReverseProperty().set(true);
                ttr.play();}
            case Paper -> {
                TranslateTransition ttr = new TranslateTransition();
                ttr.setDuration(Duration.seconds(0.2));
                ttr.setNode(vBox);
                ttr.setToY(126);
                if(vBox.translateYProperty().get()==198)
                    ttr.setCycleCount(3);
                else
                    ttr.setCycleCount(1);
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
            moneyLost+=50;
            balance-=50;
            moneyLostLabel.setText(moneyLost+" lost");

            if (botScoreLabel.getText().isEmpty())
                botScoreLabel.setText("1");
            else
            botScoreLabel.setText(String.valueOf(Integer.parseInt(botScoreLabel.getText())+1));}
        else{
            moneyWon+=50;
            balance+=50;
            moneyWonLabel.setText(moneyWon+ " won");
            if (humanScoreLabel.getText().equals(""))
                humanScoreLabel.setText("1");
            else
            humanScoreLabel.setText(String.valueOf(Integer.parseInt(humanScoreLabel.getText())+1));}
        balanceLabel.setText(balance+" available");

        if (balance<=0)
            gameOver();
}
}
