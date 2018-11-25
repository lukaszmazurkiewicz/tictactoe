package ticktacktoe;

public enum DifficultyLevel {
    EASY("e"),
    MEDIUM("m"),
    HARD("h");

    private String choice;

    DifficultyLevel(String choice) {
        this.choice = choice;
    }

    static DifficultyLevel of(String text){
        if (text.equals("e")) {
            return DifficultyLevel.EASY;
        } else if (text.equals("m")) {
            return DifficultyLevel.MEDIUM;
        } else if (text.equals("h")) {
            return DifficultyLevel.HARD;
        } else {
            throw new RuntimeException("Error");
        }
    }
}
