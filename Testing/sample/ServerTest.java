package sample;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {


    private TextArea message = new TextArea();

    NetworkConnection conne = new Client("127.0.0.1" ,9999, data-> {
        Platform.runLater(()->{
            message.appendText(data.toString() + "\n");
        });
    });
    NetworkConnection test = new Server(9999, data-> {
        Platform.runLater(()->{
            message.appendText(data.toString() + "\n");
        });
    });



    @org.junit.jupiter.api.Test
    void Test1() {
        assertTrue(test.isServer(), "isServer Error\n");
    }

    @org.junit.jupiter.api.Test
    void Test2() {
        assertNotEquals(null, conne.getIP(),"IP is Null Error\n");
        assertEquals("127.0.0.1", conne.getIP(),"IP is not 127.0.0.1 Error\n");
    }

    @org.junit.jupiter.api.Test
    void Test3() {
        assertNotEquals(0, conne.getPort(), "Port is 0 Error\n");
        assertEquals(9999, conne.getPort(),"Port is not 9999 Error\n");
    }
}