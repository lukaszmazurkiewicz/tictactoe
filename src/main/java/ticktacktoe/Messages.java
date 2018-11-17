package ticktacktoe;

import javafx.scene.control.Alert;

public class Messages {
    private String title;
    private String header;
    private String content;

    public Messages(String title, String header, String content) {
        this.title = title;
        this.header = header;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getHeader() {
        return header;
    }

    public String getContent() {
        return content;
    }

    public void showMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
