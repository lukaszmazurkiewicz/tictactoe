package ticktacktoe;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessagesTest {

    @Test
    public void getTitle() {
        //Given
        Messages messages = new Messages("This", "is", "a test!");
        //When
        String result = messages.getTitle();
        //Then
        Assert.assertEquals("This", result);
    }

    @Test
    public void getHeader() {
        //Given
        Messages messages = new Messages("This", "is", "a test!");
        //When
        String result = messages.getHeader();
        //Then
        Assert.assertEquals("is", result);

    }

    @Test
    public void getContent() {
        //Given
        Messages messages = new Messages("This", "is", "a test!");
        //When
        String result = messages.getContent();
        //Then
        Assert.assertEquals("a test!", result);
    }
}