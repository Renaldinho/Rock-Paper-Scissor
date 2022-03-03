package rps.gui.controller;
import javafx.animation.TranslateTransition;
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

    private Label roundNumberLabel,tieScoreLabel,botScoreLabel,humanScoreLabel;

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
        ImageView imageViewPaper = new ImageView(new Image("zebizebi.png"));
        imageViewPaper.setOnMouseClicked(event -> handlePlayRound(Move.Paper));
        ImageView imageViewScissor = new ImageView(new Image("zebizebiversion2.png"));
        imageViewScissor.setOnMouseClicked(event -> handlePlayRound(Move.Scissor));
        ImageView imageViewRock = new ImageView(new Image("zebizebiversion3.png"));
        imageViewRock.setOnMouseClicked(event -> handlePlayRound(Move.Rock));


        VBox vbox = new VBox(20);
        vbox.getChildren().add(imageViewPaper);
        vbox.getChildren().add(imageViewScissor);
        vbox.getChildren().add(imageViewRock);
        vbox.translateXProperty().set(440);
        vbox.translateYProperty().set(48);

        stackPane.getChildren().add(vbox);
    }

    private void handlePlayRound(Move playerMove) {
        Result result = gameManager.playRound(playerMove);

        updateGameState(result);
    }

    private void updateGameState(Result result) {
        //roundLabel.setText(String.valueOf(result.getRoundNumber()));

        Move botMove = (result.getLoserPlayer().getPlayerType()==PlayerType.AI) ?
                result.getLoserMove() :
                result.getWinnerMove();

        TranslateTransition ttr = new TranslateTransition();
        ttr.setDuration(Duration.seconds(0.5));
        ttr.setNode(vBox);
        /*ttr.setToY(92);
        ttr.setCycleCount(2);
        ttr.autoReverseProperty().set(true);
        ttr.play();*/

        switch (botMove){

            case Rock -> ttr.setToY(161);
            case Scissor -> ttr.setToY(92);
            case Paper -> ttr.setToY(126);
        }
        ttr.play();


        /*if (result.getType()== ResultType.Tie)
            tieScore.setText(String.valueOf(Integer.valueOf(tieScore.getText())+1));
        else if (result.getWinnerPlayer().getPlayerType()==PlayerType.AI)
            botScore.setText(String.valueOf(Integer.valueOf(botScore.getText())+1));
        else
            humanScore.setText(String.valueOf(Integer.valueOf(botScore.getText())+1));
            */

    }

}
