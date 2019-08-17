package sample;

import javafx.application.Platform;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class NetworkConnectionTest {
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

    @Test
    void Test1() {
        assertEquals(false, conne.clientOne, "Constructor Error\n");
        assertEquals(false, conne.clientTwo, "Constructor Error\n");

    }

    @Test
    void Test2() {
        assertEquals(0, conne.score1, "Constructor Error, score not initialized properly\n");
        assertEquals(0, conne.score2, "Constructor Error, score not initialized properly\n");


    }

    @Test
    void Test3() { // test player1- Rock and Player2 is paper
        conne.dataOne= "User Played Rock";
        conne.dataTwo= "User Played Paper";
        assertEquals("Player2 Wins: " + conne.dataTwo + "\nPlayer1 Score: " + 0 + " Player2 Score: " + 1, conne.compare(), "Error on compare\n");
    }

    @Test
    void Test4() { // test player1- Paper and Player2 is Rock
        conne.dataOne= "User Played Paper";
        conne.dataTwo= "User Played Rock";
        assertEquals("Player1 Wins: " + conne.dataOne + "\nPlayer1 Score: " + 1 + " Player2 Score: " + 0, conne.compare(), "Error on compare\n");
    }

    @Test
    void Test5() { // test player2- Scissors and Player1 is paper
        conne.dataOne= "User Played Paper";
        conne.dataTwo= "User Played Scissors";
        assertEquals("Player2 Wins: " + conne.dataTwo + "\nPlayer1 Score: " + 0 + " Player2 Score: " + 1, conne.compare(), "Error on compare\n");
    }

    @Test
    void Test6() { // test player1- Rock and Player2 is Scissors
        conne.dataOne= "User Played Rock";
        conne.dataTwo= "User Played Paper";
        assertEquals("Player2 Wins: " + conne.dataTwo + "\nPlayer1 Score: " + 0 + " Player2 Score: " + 1, conne.compare(), "Error on compare\n");
    }

    @Test
    void Test7() { // test player1- Rock and Player2 is Rock
        conne.dataOne= "User Played Rock";
        conne.dataTwo= "User Played Rock";
        assertEquals("Tie since both had same hand: " + conne.dataOne + "\nPlayer1 Score: " + conne.score1 + " Player2 Score: " + conne.score2, conne.compare(), "Error on compare\n");
    }

    @Test
    void Test8() { // test player1- Spock and Player2 is Spock
        conne.dataOne= "User Played Spock";
        conne.dataTwo= "User Played Spock";
        assertEquals("Tie since both had same hand: " + conne.dataOne + "\nPlayer1 Score: " + conne.score1 + " Player2 Score: " + conne.score2, conne.compare(), "Error on compare\n");
    }

    @Test
    void Test9() { // test player1- Rock and Player2 is spock
        conne.dataOne= "User Played Rock";
        conne.dataTwo= "User Played Spock";
        assertEquals("Player2 Wins: " + conne.dataTwo + "\nPlayer1 Score: " + 0 + " Player2 Score: " + 1, conne.compare(), "Error on compare\n");
    }

    @Test
    void Test10() { // test player1- Lizard and Player2 is Rock
        conne.dataOne= "User Played Lizard";
        conne.dataTwo= "User Played Rock";
        assertEquals("Player2 Wins: " + conne.dataTwo + "\nPlayer1 Score: " + 0 + " Player2 Score: " + 1, conne.compare(), "Error on compare\n");
    }
}