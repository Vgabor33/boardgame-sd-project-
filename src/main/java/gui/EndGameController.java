package gui;

import board.Board;
import board.BoardState;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import jdbi.LeaderboardController;
import jdbi.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//CHECKSTYLE:OFF
public class EndGameController
{
    private static Stage primaryStage;

    public static void setController(Stage stage)
    {
        primaryStage = stage;
    }

    private static String blueName;
    private static String redName;

    public static void setBlueName(String name)
    {
        blueName = name;
    }
    public static void setRedName(String name)
    {
        redName = name;
    }

    @FXML
    private TextFlow winnerFlow;

    @FXML
    private Button closeButton;

    @FXML
    private TextFlow winnersFlow;
    @FXML
    private TextFlow winsFlow;

    @FXML
    private void initialize()
    {
        Text annoncer = new Text(" has won!");

        if(BoardState.isBlueWon(Board.getInstance()))
        {
            Text winner = new Text(blueName);

            saveWin(blueName);

            winner.setFill(Color.BLUE);
            winner.setStyle("-fx-font-size: 30");
            annoncer.setStyle("-fx-font-size: 27");
            winnerFlow.getChildren().addAll(winner,annoncer);
        }
        else
        {
            Text winner = new Text(redName);

            saveWin(redName);

            winner.setStyle("-fx-font-size: 30");
            annoncer.setStyle("-fx-font-size: 27");
            winner.setFill(Color.RED);
            winnerFlow.getChildren().addAll(winner,annoncer);
        }
        closeButton.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent mouseEvent)
            {
                primaryStage.close();
            }
        });
    }
    private void saveWin(String winner)
    {
        LeaderboardController db = new LeaderboardController();
        db.insertWinner(winner);
    }
}
