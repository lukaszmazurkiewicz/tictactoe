package ticktacktoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;


public class TickTackToeRunner extends Application {

    private static final Random RANDOM = new Random();
    private static final double PAWN_SIZE_MULTIPLIER = 0.30;
    private static final Image IMAGE_FOR_BACKGROUND = new Image("Graphics/background.jpg");
    private static final Image ANIMATION_FOR_X = new Image("Graphics/cross.png");
    private static final Image ANIMATION_FOR_Y = new Image("Graphics/circle.png");
    private static final Image IMAGE_FOR_CURSOR = new Image("Graphics/cursorIcon.png");
    private static final Image IMAGE_FOR_EMPTY_FIELD = new Image("Graphics/transparent.png");
    private int numberOfRounds;
    private int moveCounter = 0;
    private boolean userWin = false;
    private boolean compWin = false;
    private boolean draw = false;
    private boolean continueGame = true;

    private Button exitButton, newGameButton;
    private VBox buttons;

    private ImageView pawn11;
    private ImageView pawn12;
    private ImageView pawn13;
    private ImageView pawn21;
    private ImageView pawn22;
    private ImageView pawn23;
    private ImageView pawn31;
    private ImageView pawn32;
    private ImageView pawn33;

    private Map<String, ImageView> pawns = new HashMap<>();

    private HBox topScoreBoard;
    private Text playerOneName, playerOneScore, playerTwoName, playerTwoScore;
    private int userScore;
    private int compScore;
    private String playerName;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setResizable(false);

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


        //playerOneName = new Text("Player One: ");
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

    private void pawnSetting(Stage primaryStage, GridPane gameBoardPane) {
        pawn11 = newPawn(primaryStage, gameBoardPane, 1, 1);
        gameBoardPane.add(pawn11, 1, 1);
        pawns.put("11", pawn11);


        pawn21 = newPawn(primaryStage, gameBoardPane, 2, 1);
        gameBoardPane.add(pawn21, 2, 1);
        pawns.put("21", pawn21);

        pawn31 = newPawn(primaryStage, gameBoardPane, 3, 1);
        gameBoardPane.add(pawn31, 3, 1);
        pawns.put("31", pawn31);

        pawn12 = newPawn(primaryStage, gameBoardPane, 1, 2);
        gameBoardPane.add(pawn12, 1, 2);
        pawns.put("12", pawn12);

        pawn22 = newPawn(primaryStage, gameBoardPane, 2, 2);
        gameBoardPane.add(pawn22, 2, 2);
        pawns.put("22", pawn22);

        pawn32 = newPawn(primaryStage, gameBoardPane, 3, 2);
        gameBoardPane.add(pawn32, 3, 2);
        pawns.put("32", pawn32);

        pawn13 = newPawn(primaryStage, gameBoardPane, 1, 3);
        gameBoardPane.add(pawn13, 1, 3);
        pawns.put("13", pawn13);

        pawn23 = newPawn(primaryStage, gameBoardPane, 2, 3);
        gameBoardPane.add(pawn23, 2, 3);
        pawns.put("23", pawn23);

        pawn33 = newPawn(primaryStage, gameBoardPane, 3, 3);
        gameBoardPane.add(pawn33, 3, 3);
        pawns.put("33", pawn33);
    }

    private ImageView newPawn(Stage primaryStage, GridPane gameBoardPane, int col, int row) {
        ImageView imageView = new ImageView(IMAGE_FOR_EMPTY_FIELD);
        imageView.fitWidthProperty().bind(gameBoardPane.widthProperty().multiply(PAWN_SIZE_MULTIPLIER));
        imageView.fitHeightProperty().bind(gameBoardPane.widthProperty().multiply(PAWN_SIZE_MULTIPLIER));
        imageView.setOnMouseClicked(e -> handleUserClick(primaryStage, e, col, row));

        return imageView;
    }

    private void handleUserClick(Stage primaryStage, MouseEvent e, int col, int row) {

        ImageView view = (ImageView) e.getSource();
        if (view.getImage() != ANIMATION_FOR_X && view.getImage() != ANIMATION_FOR_Y) {
            view.setImage(ANIMATION_FOR_X);
            System.out.println("Player Col " + col + " Row " + row);

            verifyIfFinish();
            if (userWin) {
                userScore += 1;
                showScore();
                userWin = false;
                newGame(primaryStage);
            } else if (draw) {
                showScore();
                newGame(primaryStage);
                draw = false;
            }

            handleComputerClick();
            verifyIfFinish();
            if (compWin) {
                compScore += 1;
                showScore();
                compWin = false;
                newGame(primaryStage);
            } else if (draw) {
                showScore();
                newGame(primaryStage);
                draw = false;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("You cannot do that!");
            alert.showAndWait();
            view.setOnMouseClicked(event -> handleUserClick(primaryStage, event, col, row));
        }

    }

    public void showScore() {
        if (userScore >= numberOfRounds || compScore >= numberOfRounds) {
            System.exit(0);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ScoreBox");
            alert.setHeaderText("Human versus Machine");
            alert.setContentText("Score: " + userScore + ":" + compScore);
            alert.showAndWait();
            playerOneScore = new Text(Integer.toString(userScore));
            playerOneScore.setFont(Font.font("Verdana", 30));
            playerOneScore.setFill(Color.AQUA);

            playerTwoScore = new Text(Integer.toString(compScore));
            playerTwoScore.setFont(Font.font("Verdana", 30));
            playerTwoScore.setFill(Color.AQUA);

            topScoreBoard = new HBox(playerOneName, playerOneScore, playerTwoName, playerTwoScore);
            topScoreBoard.setSpacing(10);
        }
    }

    private void verifyIfFinish() {
        if (pawns.get("11").getImage() == ANIMATION_FOR_X && pawns.get("21").getImage() == ANIMATION_FOR_X && pawns.get("31").getImage() == ANIMATION_FOR_X) {
            userWin = true;
        } else if (pawns.get("12").getImage() == ANIMATION_FOR_X && pawns.get("22").getImage() == ANIMATION_FOR_X && pawns.get("32").getImage() == ANIMATION_FOR_X) {
            userWin = true;
        } else if (pawns.get("13").getImage() == ANIMATION_FOR_X && pawns.get("23").getImage() == ANIMATION_FOR_X && pawns.get("33").getImage() == ANIMATION_FOR_X) {
            userWin = true;
        } else if (pawns.get("11").getImage() == ANIMATION_FOR_X && pawns.get("12").getImage() == ANIMATION_FOR_X && pawns.get("13").getImage() == ANIMATION_FOR_X) {
            userWin = true;
        } else if (pawns.get("21").getImage() == ANIMATION_FOR_X && pawns.get("22").getImage() == ANIMATION_FOR_X && pawns.get("23").getImage() == ANIMATION_FOR_X) {
            userWin = true;
        } else if (pawns.get("31").getImage() == ANIMATION_FOR_X && pawns.get("32").getImage() == ANIMATION_FOR_X && pawns.get("33").getImage() == ANIMATION_FOR_X) {
            userWin = true;
        } else if (pawns.get("11").getImage() == ANIMATION_FOR_X && pawns.get("22").getImage() == ANIMATION_FOR_X && pawns.get("33").getImage() == ANIMATION_FOR_X) {
            userWin = true;
        } else if (pawns.get("31").getImage() == ANIMATION_FOR_X && pawns.get("22").getImage() == ANIMATION_FOR_X && pawns.get("13").getImage() == ANIMATION_FOR_X) {
            userWin = true;
        } else if (pawns.get("11").getImage() == ANIMATION_FOR_Y && pawns.get("21").getImage() == ANIMATION_FOR_Y && pawns.get("31").getImage() == ANIMATION_FOR_Y) {
            compWin = true;
        } else if (pawns.get("12").getImage() == ANIMATION_FOR_Y && pawns.get("22").getImage() == ANIMATION_FOR_Y && pawns.get("32").getImage() == ANIMATION_FOR_Y) {
            compWin = true;
        } else if (pawns.get("13").getImage() == ANIMATION_FOR_Y && pawns.get("23").getImage() == ANIMATION_FOR_Y && pawns.get("33").getImage() == ANIMATION_FOR_Y) {
            compWin = true;
        } else if (pawns.get("11").getImage() == ANIMATION_FOR_Y && pawns.get("12").getImage() == ANIMATION_FOR_Y && pawns.get("13").getImage() == ANIMATION_FOR_Y) {
            compWin = true;
        } else if (pawns.get("21").getImage() == ANIMATION_FOR_Y && pawns.get("22").getImage() == ANIMATION_FOR_Y && pawns.get("23").getImage() == ANIMATION_FOR_Y) {
            compWin = true;
        } else if (pawns.get("31").getImage() == ANIMATION_FOR_Y && pawns.get("32").getImage() == ANIMATION_FOR_Y && pawns.get("33").getImage() == ANIMATION_FOR_Y) {
            compWin = true;
        } else if (pawns.get("11").getImage() == ANIMATION_FOR_Y && pawns.get("22").getImage() == ANIMATION_FOR_Y && pawns.get("33").getImage() == ANIMATION_FOR_Y) {
            compWin = true;
        } else if (pawns.get("11").getImage() == ANIMATION_FOR_Y && pawns.get("22").getImage() == ANIMATION_FOR_Y && pawns.get("33").getImage() == ANIMATION_FOR_Y) {
            compWin = true;
        } else if (pawns.get("11").getImage() != IMAGE_FOR_EMPTY_FIELD && pawns.get("12").getImage() != IMAGE_FOR_EMPTY_FIELD
                                                                       && pawns.get("13").getImage() != IMAGE_FOR_EMPTY_FIELD
                                                                       && pawns.get("21").getImage() != IMAGE_FOR_EMPTY_FIELD
                                                                       && pawns.get("22").getImage() != IMAGE_FOR_EMPTY_FIELD
                                                                       && pawns.get("23").getImage() != IMAGE_FOR_EMPTY_FIELD
                                                                       && pawns.get("31").getImage() != IMAGE_FOR_EMPTY_FIELD
                                                                       && pawns.get("32").getImage() != IMAGE_FOR_EMPTY_FIELD
                                                                       && pawns.get("33").getImage() != IMAGE_FOR_EMPTY_FIELD) {
            draw = true;
        }
    }


    private void handleComputerClick() {
        int path = RANDOM.nextInt(4);
        if (moveCounter == 0) {
            //int path = RANDOM.nextInt(4);
            if (path == 0 && pawns.get("11").getImage() == IMAGE_FOR_EMPTY_FIELD){

                pawns.get("11").setImage(ANIMATION_FOR_Y);
                moveCounter++;

            } else if (path == 1 && pawns.get("31").getImage() == IMAGE_FOR_EMPTY_FIELD) {

                pawns.get("31").setImage(ANIMATION_FOR_Y);
                moveCounter++;

            } else if (path == 2 && pawns.get("13").getImage() == IMAGE_FOR_EMPTY_FIELD) {

                pawns.get("13").setImage(ANIMATION_FOR_Y);
                moveCounter++;

            } else if (path == 3 && pawns.get("33").getImage() == IMAGE_FOR_EMPTY_FIELD) {

                pawns.get("33").setImage(ANIMATION_FOR_Y);
                moveCounter++;

            } else {
                pawns.get("22").setImage(ANIMATION_FOR_Y);
                moveCounter++;
            }
        } else if (moveCounter == 1) {
            canHeWin();
            if (continueGame && path == 0 && pawns.get("33").getImage() == IMAGE_FOR_EMPTY_FIELD) {

                pawns.get("33").setImage(ANIMATION_FOR_Y);
                moveCounter++;

            } else if (continueGame && path == 1 && pawns.get("13").getImage() == IMAGE_FOR_EMPTY_FIELD) {

                pawns.get("13").setImage(ANIMATION_FOR_Y);
                moveCounter++;

            } else if (continueGame && path == 2 && pawns.get("31").getImage() == IMAGE_FOR_EMPTY_FIELD) {

                pawns.get("31").setImage(ANIMATION_FOR_Y);
                moveCounter++;

            } else if (continueGame && path == 3 && pawns.get("11").getImage() == IMAGE_FOR_EMPTY_FIELD) {

                pawns.get("11").setImage(ANIMATION_FOR_Y);
                moveCounter++;

            } else if (moveCounter == 1 ){

                String key = "22";
                while (pawns.get(key).getImage() != IMAGE_FOR_EMPTY_FIELD) {
                    int col = RANDOM.nextInt(3) + 1;
                    int row = RANDOM.nextInt(3) + 1;
                    key = String.valueOf(col) + String.valueOf(row);

                }
                pawns.get(key).setImage(ANIMATION_FOR_Y);
                moveCounter++;

            }
        } else {
            continueGame = true;
            canHeWin();
            if (continueGame) {

                String key = "22";
                while (pawns.get(key).getImage() != IMAGE_FOR_EMPTY_FIELD) {
                    int col = RANDOM.nextInt(3) + 1;
                    int row = RANDOM.nextInt(3) + 1;
                    key = String.valueOf(col) + String.valueOf(row);
                }

                pawns.get(key).setImage(ANIMATION_FOR_Y);

            }
        }
    }

    private void handleButtonClick(Stage primaryStage, MouseEvent event) {

        if (event.getSource().equals(exitButton)) {
            System.exit(0);
        } else if (event.getSource().equals(newGameButton)) {

            userScore = 0;
            compScore = 0;

            playerOneScore = new Text(Integer.toString(userScore));
            playerOneScore.setFont(Font.font("Verdana", 30));
            playerOneScore.setFill(Color.AQUA);

            playerTwoScore = new Text(Integer.toString(compScore));
            playerTwoScore.setFont(Font.font("Verdana", 30));
            playerTwoScore.setFill(Color.AQUA);

            topScoreBoard = new HBox(playerOneName, playerOneScore, playerTwoName, playerTwoScore);
            topScoreBoard.setSpacing(10);

            newGame(primaryStage);
        }

    }

    private void newGame(Stage primaryStage) {
        moveCounter = 0;
        BackgroundSize backgroundSize = new BackgroundSize(200, 200, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(IMAGE_FOR_BACKGROUND, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane newGameBoardPane = new GridPane();
        newGameBoardPane.setCursor(new ImageCursor(IMAGE_FOR_CURSOR));
        newGameBoardPane.setAlignment(Pos.CENTER);

        pawnSetting(primaryStage, newGameBoardPane);

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

    private boolean canHeWin() {
        String winningMove = "0";
        String defendingMove = "0";
        for (Map.Entry<String, ImageView> entry : pawns.entrySet()) {
            if (entry.getValue().getImage() == IMAGE_FOR_EMPTY_FIELD) {
                entry.getValue().setImage(ANIMATION_FOR_Y);
                verifyIfFinish();
                if (compWin) {
                    winningMove = entry.getKey();
                    compWin = false;
                    entry.getValue().setImage(IMAGE_FOR_EMPTY_FIELD);
                }
                entry.getValue().setImage(ANIMATION_FOR_X);
                verifyIfFinish();
                if (userWin) {
                    defendingMove = entry.getKey();
                    userWin = false;
                    entry.getValue().setImage(IMAGE_FOR_EMPTY_FIELD);
                }
                entry.getValue().setImage(IMAGE_FOR_EMPTY_FIELD);
            }

        }
        if (!winningMove.equals("0")) {

            pawns.get(winningMove).setImage(ANIMATION_FOR_Y);
            moveCounter++;
            continueGame = false;

        } else if (winningMove.equals("0") && !defendingMove.equals("0")) {
            pawns.get(defendingMove).setImage(ANIMATION_FOR_Y);
            moveCounter++;
            continueGame = false;
        }
        return continueGame;
    }

   /* private void getPlayerName() {
        Label label1 = new Label("Name:");
        TextField textField = new TextField();
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField);
        hb.setSpacing(10);
        playerName = textField.getText();
    }*/
}
