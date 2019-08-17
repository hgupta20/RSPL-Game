package sample;

import javafx.application.Platform;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    private TextArea message = new TextArea();

    NetworkConnection conne = new Client("127.0.0.1" ,5555, data-> {
        Platform.runLater(()->{
            message.appendText(data.toString() + "\n");
        });
    });
    NetworkConnection test = new Server(5555, data-> {
        Platform.runLater(()->{
            message.appendText(data.toString() + "\n");
        });
    });



    @org.junit.jupiter.api.Test
    void Test1() {
        assertTrue(test.isServer());
    }

    @org.junit.jupiter.api.Test
    void Test2() {
        assertNotEquals(null, conne.getIP());
        assertEquals("127.0.0.1", conne.getIP());
    }

    @org.junit.jupiter.api.Test
    void Test3() {
        assertNotEquals(0, conne.getPort());
        assertEquals(5555, conne.getPort());
    }

}