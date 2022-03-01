package rps.gui.controller;

// Java imports

import java.awt.*;
import java.awt.image.*;
import javafx.scene.image.Image;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
    private final ImageIcon scissorSiLamin= new ImageIcon(("scissorSiLamin.png"));

    public GameViewController(){
        human = new Player("Renars", PlayerType.Human);
        bot = new Player("Bot",PlayerType.AI);
        gameManager = new GameManager(human,bot);

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HBox scissorBox= new HBox();
        /*try {
            resize("/Users/aminaouina/Documents/GitHub/rps2022/Untitled/Resources/scissorSiLamin.png","/Users/aminaouina/Documents/GitHub/rps2022/Untitled/Resources/resizedScissor.png",40,30);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        for (int i =1;i<=3;i++)
        scissorBox.getChildren().add(new ImageView(new Image("resizedScissor.png")));
        anchorPane.getChildren().add(scissorBox);
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

        if (result.getType()== ResultType.Tie)
            tieScore.setText(String.valueOf(Integer.valueOf(tieScore.getText())+1));
        else if (result.getWinnerPlayer().getPlayerType()==PlayerType.AI)
            botScore.setText(String.valueOf(Integer.valueOf(botScore.getText())+1));
        else
            humanScore.setText(String.valueOf(Integer.valueOf(botScore.getText())+1));
    }
    public void resize(String inputImagePath,
                       String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);

        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);

        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }
}
