package day21;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScramblerTest {

    @Test
    public void getString() throws Exception {
        Scrambler scrambler = new Scrambler("abc");
        assertEquals("abc", scrambler.getString());
    }

    @Test
    public void swapPosition() throws Exception {
        Scrambler scrambler = new Scrambler("012345");
        scrambler.swapPosition(1,4);
        assertEquals("042315", scrambler.getString());
    }

    @Test
    public void swapLetter() throws Exception {
        Scrambler scrambler = new Scrambler("101010111000");
        scrambler.swapLetter('1', '0');
        assertEquals("010101000111", scrambler.getString());
    }

    @Test
    public void rotateDir() throws Exception {
        Scrambler scrambler = new Scrambler("abcd");
        scrambler.rotateDir("right", 1);
        assertEquals("dabc", scrambler.getString());
    }

    @Test
    public void rotateByPosition() throws Exception {
        Scrambler scrambler = new Scrambler("0123456789abcdefg");
        scrambler.rotateByPosition('5');
        assertEquals("abcdefg0123456789", scrambler.getString());
    }

    @Test
    public void reversePositions() throws Exception {
        Scrambler scrambler = new Scrambler("aa111000bb");
        scrambler.reversePositions(2, 8);
        assertEquals("aa000111bb", scrambler.getString());
    }

    @Test
    public void move() throws Exception {
        Scrambler scrambler = new Scrambler("012345");
        scrambler.move(1,4);
        assertEquals("023415", scrambler.getString());
    }

    @Test
    public void completeTest() throws Exception {
        Scrambler scrambler = new Scrambler("abcde");
        scrambler.swapPosition(4, 0);
        assertEquals("ebcda", scrambler.getString());
        scrambler.swapLetter('d', 'b');
        assertEquals("edcba", scrambler.getString());
        scrambler.reversePositions(0, 4);
        assertEquals("abcde", scrambler.getString());
        scrambler.rotateDir("left", 1);
        assertEquals("bcdea", scrambler.getString());
        scrambler.move(1, 4);
        assertEquals("bdeac", scrambler.getString());
        scrambler.move(3, 0);
        assertEquals("abdec", scrambler.getString());
        scrambler.rotateByPosition('b');
        assertEquals("ecabd", scrambler.getString());
        scrambler.rotateByPosition('d');
        assertEquals("decab", scrambler.getString());

    }
}