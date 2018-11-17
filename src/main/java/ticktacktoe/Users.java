package ticktacktoe;

public class Users {
    private boolean userWin = false;
    private boolean compWin = false;
    private boolean draw = false;

    private int userScore;
    private int compScore;

    public boolean isUserWin() {
        return userWin;
    }

    public boolean isCompWin() {
        return compWin;
    }

    public boolean isDraw() {
        return draw;
    }

    public int getUserScore() {
        return userScore;
    }

    public int getCompScore() {
        return compScore;
    }

    public void setUserWin(boolean userWin) {
        this.userWin = userWin;
    }

    public void setCompWin(boolean compWin) {
        this.compWin = compWin;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public void setCompScore(int compScore) {
        this.compScore = compScore;
    }
}
