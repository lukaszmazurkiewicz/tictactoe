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

public class PawnContainer {
    private static final double PAWN_SIZE_MULTIPLIER = 0.30;
    private static final Random RANDOM = new Random();

    private Game game;
    private UsersResults user;

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

    public PawnContainer(Game game, UsersResults user) {
        this.game = game;
        this.user = user;
    }


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
        } else if (pawns.get("31").getImage() == ANIMATION_FOR_Y && pawns.get("22").getImage() == ANIMATION_FOR_Y && pawns.get("13").getImage() == ANIMATION_FOR_Y) {
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
        String winningMove = "0";
        String defendingMove = "0";
        for (Map.Entry<String, ImageView> entry : pawns.entrySet()) {
            if (entry.getValue().getImage() == IMAGE_FOR_EMPTY_FIELD) {
                entry.getValue().setImage(ANIMATION_FOR_Y);
                verifyIfFinish();
                if (user.isCompWin()) {
                    winningMove = entry.getKey();
                    user.setCompWin(false);
                    entry.getValue().setImage(IMAGE_FOR_EMPTY_FIELD);
                }
                entry.getValue().setImage(ANIMATION_FOR_X);
                verifyIfFinish();
                if (user.isUserWin()) {
                    defendingMove = entry.getKey();
                    user.setUserWin(false);
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

    public boolean canHeWinMedium() {
        for (Map.Entry<String, ImageView> entry : pawns.entrySet()) {
            if (entry.getValue().getImage() == IMAGE_FOR_EMPTY_FIELD) {
                entry.getValue().setImage(ANIMATION_FOR_Y);
                verifyIfFinish();
                if (user.isCompWin()) {
                    user.setCompWin(false);
                    moveCounter++;
                    continueGame = false;
                    break;
                }
                entry.getValue().setImage(ANIMATION_FOR_X);
                verifyIfFinish();
                if (user.isUserWin()) {
                    user.setUserWin(false);
                    entry.getValue().setImage(ANIMATION_FOR_Y);
                    moveCounter++;
                    continueGame = false;
                    break;
                }
                entry.getValue().setImage(IMAGE_FOR_EMPTY_FIELD);
            }

        }
        return continueGame;
    }

    public void handleEasyComputerClick() {

        String key = "22";
        while (pawns.get(key).getImage() != IMAGE_FOR_EMPTY_FIELD) {
            int col = RANDOM.nextInt(3) + 1;
            int row = RANDOM.nextInt(3) + 1;
            key = String.valueOf(col) + String.valueOf(row);

        }

        pawns.get(key).setImage(ANIMATION_FOR_Y);

    }

    public void handleMediumComputerClick() {

        int path = RANDOM.nextInt(4);
        if (moveCounter == 0) {
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
            canHeWinMedium();
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
            canHeWinMedium();
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

    public void handleHardComputerClick() {
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
}
