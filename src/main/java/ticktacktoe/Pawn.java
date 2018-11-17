package ticktacktoe;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static ticktacktoe.Graphics.ANIMATION_FOR_X;
import static ticktacktoe.Graphics.ANIMATION_FOR_Y;
import static ticktacktoe.Graphics.IMAGE_FOR_EMPTY_FIELD;

public class Pawn {
    private static final double PAWN_SIZE_MULTIPLIER = 0.30;
    private static final Random RANDOM = new Random();

    private Game game;

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

    private int moveCounter = 0;
    private boolean continueGame = true;

    public Pawn(Game game) {
        this.game = game;
    }

    private Users user = new Users();

    public void setMoveCounter(int moveCounter) {
        this.moveCounter = moveCounter;
    }

    public boolean isContinueGame() {
        return continueGame;
    }

    public void setContinueGame(boolean continueGame) {
        this.continueGame = continueGame;
    }

    public Map<String, ImageView> getPawns() {
        return pawns;
    }

    private ImageView newPawn(Stage primaryStage, GridPane gameBoardPane, int col, int row) {
        ImageView imageView = new ImageView(IMAGE_FOR_EMPTY_FIELD);
        imageView.fitWidthProperty().bind(gameBoardPane.widthProperty().multiply(PAWN_SIZE_MULTIPLIER));
        imageView.fitHeightProperty().bind(gameBoardPane.widthProperty().multiply(PAWN_SIZE_MULTIPLIER));
        imageView.setOnMouseClicked(e -> game.handleUserClick(primaryStage, e, col, row));

        return imageView;
    }

    public void pawnSetting(Stage primaryStage, GridPane gameBoardPane) {
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

    public void verifyIfFinish() {
        if (pawns.get("11").getImage() == ANIMATION_FOR_X && pawns.get("21").getImage() == ANIMATION_FOR_X && pawns.get("31").getImage() == ANIMATION_FOR_X) {
            user.setUserWin(true);
        } else if (pawns.get("12").getImage() == ANIMATION_FOR_X && pawns.get("22").getImage() == ANIMATION_FOR_X && pawns.get("32").getImage() == ANIMATION_FOR_X) {
            user.setUserWin(true);
        } else if (pawns.get("13").getImage() == ANIMATION_FOR_X && pawns.get("23").getImage() == ANIMATION_FOR_X && pawns.get("33").getImage() == ANIMATION_FOR_X) {
            user.setUserWin(true);
        } else if (pawns.get("11").getImage() == ANIMATION_FOR_X && pawns.get("12").getImage() == ANIMATION_FOR_X && pawns.get("13").getImage() == ANIMATION_FOR_X) {
            user.setUserWin(true);
        } else if (pawns.get("21").getImage() == ANIMATION_FOR_X && pawns.get("22").getImage() == ANIMATION_FOR_X && pawns.get("23").getImage() == ANIMATION_FOR_X) {
            user.setUserWin(true);
        } else if (pawns.get("31").getImage() == ANIMATION_FOR_X && pawns.get("32").getImage() == ANIMATION_FOR_X && pawns.get("33").getImage() == ANIMATION_FOR_X) {
            user.setUserWin(true);
        } else if (pawns.get("11").getImage() == ANIMATION_FOR_X && pawns.get("22").getImage() == ANIMATION_FOR_X && pawns.get("33").getImage() == ANIMATION_FOR_X) {
            user.setUserWin(true);
        } else if (pawns.get("31").getImage() == ANIMATION_FOR_X && pawns.get("22").getImage() == ANIMATION_FOR_X && pawns.get("13").getImage() == ANIMATION_FOR_X) {
            user.setUserWin(true);
        } else if (pawns.get("11").getImage() == ANIMATION_FOR_Y && pawns.get("21").getImage() == ANIMATION_FOR_Y && pawns.get("31").getImage() == ANIMATION_FOR_Y) {
            user.setCompWin(true);
        } else if (pawns.get("12").getImage() == ANIMATION_FOR_Y && pawns.get("22").getImage() == ANIMATION_FOR_Y && pawns.get("32").getImage() == ANIMATION_FOR_Y) {
            user.setCompWin(true);
        } else if (pawns.get("13").getImage() == ANIMATION_FOR_Y && pawns.get("23").getImage() == ANIMATION_FOR_Y && pawns.get("33").getImage() == ANIMATION_FOR_Y) {
            user.setCompWin(true);
        } else if (pawns.get("11").getImage() == ANIMATION_FOR_Y && pawns.get("12").getImage() == ANIMATION_FOR_Y && pawns.get("13").getImage() == ANIMATION_FOR_Y) {
            user.setCompWin(true);
        } else if (pawns.get("21").getImage() == ANIMATION_FOR_Y && pawns.get("22").getImage() == ANIMATION_FOR_Y && pawns.get("23").getImage() == ANIMATION_FOR_Y) {
            user.setCompWin(true);
        } else if (pawns.get("31").getImage() == ANIMATION_FOR_Y && pawns.get("32").getImage() == ANIMATION_FOR_Y && pawns.get("33").getImage() == ANIMATION_FOR_Y) {
            user.setCompWin(true);
        } else if (pawns.get("11").getImage() == ANIMATION_FOR_Y && pawns.get("22").getImage() == ANIMATION_FOR_Y && pawns.get("33").getImage() == ANIMATION_FOR_Y) {
            user.setCompWin(true);
        } else if (pawns.get("11").getImage() == ANIMATION_FOR_Y && pawns.get("22").getImage() == ANIMATION_FOR_Y && pawns.get("33").getImage() == ANIMATION_FOR_Y) {
            user.setCompWin(true);
        } else if (pawns.get("11").getImage() != IMAGE_FOR_EMPTY_FIELD && pawns.get("12").getImage() != IMAGE_FOR_EMPTY_FIELD
                && pawns.get("13").getImage() != IMAGE_FOR_EMPTY_FIELD
                && pawns.get("21").getImage() != IMAGE_FOR_EMPTY_FIELD
                && pawns.get("22").getImage() != IMAGE_FOR_EMPTY_FIELD
                && pawns.get("23").getImage() != IMAGE_FOR_EMPTY_FIELD
                && pawns.get("31").getImage() != IMAGE_FOR_EMPTY_FIELD
                && pawns.get("32").getImage() != IMAGE_FOR_EMPTY_FIELD
                && pawns.get("33").getImage() != IMAGE_FOR_EMPTY_FIELD) {
            user.setDraw(true);
        }
    }

    public boolean canHeWin() {
        for (Map.Entry<String, ImageView> entry : getPawns().entrySet()) {
            if (entry.getValue().getImage() == IMAGE_FOR_EMPTY_FIELD) {
                entry.getValue().setImage(ANIMATION_FOR_Y);
                verifyIfFinish();
                if (user.isUserWin()) {
                    user.setCompWin(false);
                    setContinueGame(false);
                    break;
                }
                entry.getValue().setImage(ANIMATION_FOR_X);
                verifyIfFinish();
                if (user.isUserWin()) {
                    setContinueGame(false);
                    user.setCompWin(false);
                    entry.getValue().setImage(ANIMATION_FOR_Y);
                    break;
                }

                entry.getValue().setImage(IMAGE_FOR_EMPTY_FIELD);
                setContinueGame(true);
            }

        }
        return isContinueGame();
    }

    public void handleComputerClick() {
        if (moveCounter == 0) {
            ImageView imageView = getPawns().get("11");
            if (imageView.getImage() == IMAGE_FOR_EMPTY_FIELD) {
                imageView.setImage(ANIMATION_FOR_Y);
                moveCounter++;
            } else if (imageView.getImage() == ANIMATION_FOR_X) {
                imageView = getPawns().get("31");
                imageView.setImage(ANIMATION_FOR_Y);
                moveCounter++;
            }
        } else if (moveCounter == 1) {
            ImageView imageView = getPawns().get("33");
            canHeWin();
            if (continueGame && getPawns().get("11").getImage() == ANIMATION_FOR_Y && imageView.getImage() == IMAGE_FOR_EMPTY_FIELD) {
                imageView.setImage(ANIMATION_FOR_Y);
                moveCounter++;
                continueGame = false;
            } else if (continueGame && getPawns().get("11").getImage() == ANIMATION_FOR_Y && imageView.getImage() == ANIMATION_FOR_X) {
                getPawns().get("13").setImage(ANIMATION_FOR_Y);
                moveCounter++;
                continueGame = false;
            } else if (continueGame && getPawns().get("11").getImage() == ANIMATION_FOR_Y && getPawns().get("13").getImage() == ANIMATION_FOR_X) {
                getPawns().get("31").setImage(ANIMATION_FOR_Y);
                moveCounter++;
                continueGame = false;
            } else if (continueGame && getPawns().get("11").getImage() == ANIMATION_FOR_X && getPawns().get("13").getImage() == IMAGE_FOR_EMPTY_FIELD) {
                getPawns().get("13").setImage(ANIMATION_FOR_Y);
                moveCounter++;
                continueGame = false;
            } else if (continueGame && getPawns().get("11").getImage() == ANIMATION_FOR_X && getPawns().get("13").getImage() == ANIMATION_FOR_X) {
                getPawns().get("33").setImage(ANIMATION_FOR_Y);
                moveCounter++;
                continueGame = false;
            }
        } else {
            int col = RANDOM.nextInt(3) + 1;
            int row = RANDOM.nextInt(3) + 1;
            System.out.println("Computer Col " + col + " Row " + row);
            String key = String.valueOf(col) + String.valueOf(row);
            ImageView imageView = getPawns().get(key);
            canHeWin();
            if (continueGame && imageView.getImage() != ANIMATION_FOR_Y && imageView.getImage() != ANIMATION_FOR_X) {
                imageView.setImage(ANIMATION_FOR_Y);
                moveCounter++;
                continueGame = false;
            } else if (continueGame && (imageView.getImage() == ANIMATION_FOR_Y || imageView.getImage() == ANIMATION_FOR_X)){
                System.out.println("Field is taken");
                handleComputerClick();
            }
        }
    }
}
