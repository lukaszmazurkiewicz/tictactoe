package ticktacktoe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

import static ticktacktoe.Graphics.*;
import static ticktacktoe.Graphics.ANIMATION_FOR_X;

public class Game {

    private Pawn pawn;
    private Button exitButton, newGameButton;
    private VBox buttons;

    private HBox topScoreBoard;
    private Text playerOneName, playerOneScore, playerTwoName, playerTwoScore;

    private String playerName;
    private int numberOfRounds;

    private Users user = new Users();

    public Game() {
        pawn = new Pawn(this, user);
    }

    public void gamePlay(Stage primaryStage) {

        exitButton = new Button("Exit");
        exitButton.setMinSize(200, 30);
        exitButton.setOnMouseClicked(e -> handleButtonClick(primaryStage, e));

        newGameButton = new Button("New game");
        newGameButton.setMinSize(200, 30);
        newGameButton.setOnMouseClicked(e -> handleButtonClick(primaryStage, e));


        buttons = new VBox(exitButton, newGameButton);
        buttons.setSpacing(20);
        buttons.setPadding(new Insets(20, 20, 20, 20));

        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Imię.");
        dialog.setHeaderText(null);
        dialog.setContentText("Please enter your name:");
        Optional<String> result = dialog.showAndWait();
        playerName = result.get();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Rundy");
        alert.setHeaderText("Do ilu wygranych rund chcesz zagrać?");
        alert.setContentText("Choose your option.");

        ButtonType buttonTypeOne = new ButtonType("One");
        ButtonType buttonTypeTwo = new ButtonType("Two");
        ButtonType buttonTypeThree = new ButtonType("Three");
        ButtonType buttonTypeFour = new ButtonType("Four");
        ButtonType buttonTypeFive = new ButtonType("Five");
        ButtonType buttonTypeCancel = new ButtonType("Exit", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeFour, buttonTypeFive, buttonTypeCancel);

        Optional<ButtonType> result1 = alert.showAndWait();
        if (result1.get() == buttonTypeOne){
            numberOfRounds = 1;
        } else if (result1.get() == buttonTypeTwo) {
            numberOfRounds = 2;
        } else if (result1.get() == buttonTypeThree) {
            numberOfRounds = 3;
        } else if (result1.get() == buttonTypeFour) {
            numberOfRounds = 4;
        } else if (result1.get() == buttonTypeFive) {
            numberOfRounds = 5;
        } else {
            System.exit(0);
        }

        playerOneName = new Text(playerName);
        playerOneName.setFont(Font.font("Verdana", 30));
        playerOneName.setFill(Color.AQUA);

        playerOneScore = new Text("0");
        playerOneScore.setFont(Font.font("Verdana", 30));
        playerOneScore.setFill(Color.AQUA);

        playerTwoName = new Text("Computer: ");
        playerTwoName.setFont(Font.font("Verdana", 30));
        playerTwoName.setFill(Color.AQUA);

        playerTwoScore = new Text("0");
        playerTwoScore.setFont(Font.font("Verdana", 30));
        playerTwoScore.setFill(Color.AQUA);

        topScoreBoard = new HBox(playerOneName, playerOneScore, playerTwoName, playerTwoScore);
        topScoreBoard.setSpacing(10);

        newGame(primaryStage);
    }

    public void handleButtonClick(Stage primaryStage, MouseEvent event) {

        if (event.getSource().equals(exitButton)) {
            System.exit(0);
        } else if (event.getSource().equals(newGameButton)) {

            zeroingScore();

            newGame(primaryStage);
        }

    }

    public void showScore(Stage primaryStage) {
        if (user.getUserScore() >= numberOfRounds || user.getCompScore() >= numberOfRounds) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("End of the Game!");
            alert.setHeaderText(null);
            if (user.getUserScore() == numberOfRounds) {
                alert.setContentText("You just won the game!");
                //alert.showAndWait();
            } else if (user.getCompScore() == numberOfRounds) {
                alert.setContentText("You just lost the game!");
                //alert.showAndWait();
            }

            ButtonType buttonNewGame = new ButtonType("New Game");
            ButtonType buttonExitGame = new ButtonType("Exit Game");

            alert.getButtonTypes().setAll(buttonNewGame, buttonExitGame);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == buttonNewGame) {
                zeroingScore();
                newGame(primaryStage);
            } else if (result.get() == buttonExitGame) {
                System.exit(0);
            }
        } else {
            Messages message = new Messages("Scorebox", "Human versus Machine", "Score" + user.getUserScore() + ":" + user.getCompScore());
            message.showMessage();
            playerOneScore = new Text(Integer.toString(user.getUserScore()));
            playerOneScore.setFont(Font.font("Verdana", 30));
            playerOneScore.setFill(Color.AQUA);

            playerTwoScore = new Text(Integer.toString(user.getCompScore()));
            playerTwoScore.setFont(Font.font("Verdana", 30));
            playerTwoScore.setFill(Color.AQUA);

            topScoreBoard = new HBox(playerOneName, playerOneScore, playerTwoName, playerTwoScore);
            topScoreBoard.setSpacing(10);
        }
    }

    public void newGame(Stage primaryStage) {
        pawn.setMoveCounter(0);
        BackgroundSize backgroundSize = new BackgroundSize(200, 200, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(IMAGE_FOR_BACKGROUND, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane newGameBoardPane = new GridPane();
        newGameBoardPane.setCursor(new ImageCursor(IMAGE_FOR_CURSOR));
        newGameBoardPane.setAlignment(Pos.CENTER);

        pawn.pawnSetting(primaryStage, newGameBoardPane);

        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(background);
        borderPane.setCenter(newGameBoardPane);
        borderPane.setLeft(buttons);
        borderPane.setTop(topScoreBoard);

        topScoreBoard.setAlignment(Pos.CENTER);

        Scene scene = new Scene(borderPane, 600, 600, Color.BLACK);

        primaryStage.setTitle("Tick Tack Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void handleUserClick(Stage primaryStage, MouseEvent e, int col, int row) {

        ImageView view = (ImageView) e.getSource();
        if (view.getImage() != ANIMATION_FOR_X && view.getImage() != ANIMATION_FOR_Y) {
            view.setImage(ANIMATION_FOR_X);
            System.out.println("Player Col " + col + " Row " + row);

            pawn.verifyIfFinish();
            if (user.isUserWin()) {
                user.setUserScore(user.getUserScore()+1);
                showScore(primaryStage);
                user.setUserWin(false);
                newGame(primaryStage);
            } else if (user.isDraw()) {
                showScore(primaryStage);
                newGame(primaryStage);
                user.setDraw(false);
            }

            pawn.handleComputerClick();
            pawn.verifyIfFinish();
            if (user.isCompWin()) {
                user.setCompScore(user.getCompScore()+1);
                showScore(primaryStage);
                user.setCompWin(false);
                newGame(primaryStage);
            } else if (user.isDraw()) {
                showScore(primaryStage);
                newGame(primaryStage);
                user.setDraw(false);
            }
        } else {
            Messages messages = new Messages("Error",null,"You cannot do that!");
            messages.showMessage();
            view.setOnMouseClicked(event -> handleUserClick(primaryStage, event, col, row));
        }

    }

    public void zeroingScore() {
        user.setUserScore(0);
        user.setCompScore(0);

        playerOneScore = new Text(Integer.toString(user.getUserScore()));
        playerOneScore.setFont(Font.font("Verdana", 30));
        playerOneScore.setFill(Color.AQUA);

        playerTwoScore = new Text(Integer.toString(user.getCompScore()));
        playerTwoScore.setFont(Font.font("Verdana", 30));
        playerTwoScore.setFill(Color.AQUA);

        topScoreBoard = new HBox(playerOneName, playerOneScore, playerTwoName, playerTwoScore);
        topScoreBoard.setSpacing(10);
    }

}
