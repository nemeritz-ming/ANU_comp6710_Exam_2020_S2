package comp1110.exam;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class Q2ChecksumTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    static final String INPUT_FILENAME_BASE = "assets/Q2Checksum";
    static final String OUTPUT_FILENAME = "assets/Q2output";

    @Before
    public void setup() {
        try {
            Files.deleteIfExists(Paths.get(OUTPUT_FILENAME));
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @Test
    public void empty() {
        test("A", false, "");
    }

    @Test
    public void small() {
        test("B", false, "HAMLET\n\n\n");
    }

    @Test
    public void smallChecksum() {
        test("B", true, "fHAMLET\n\n\n");
    }

    @Test
    public void medChecksum() {
        test("C", true, "oHAMLET\n" +
                        "\n" +
                        "\n" +
                        "\thDRAMATIS PjERSONAE\n" +
                        "\n" +
                        "\n" +
                        "qCLAUDIUS\tkqing of Denrmark. (KINdG CLAUDIUSq:)\n" +
                        "\n" +
                        "HAMLETy\tson to thte late\n");
    }

    @Test
    public void longerChecksum() {
    test("D", true, "oHAMLET\n" +
            "\n" +
            "\n" +
            "\thDRAMATIS PjERSONAE\n" +
            "\n" +
            "\n" +
            "qCLAUDIUS\tkqing of Denrmark. (KINdG CLAUDIUSq:)\n" +
            "\n" +
            "HAMLETy\tson to thge late, anrd nephew tco the presient king.\n" +
            "c\n" +
            "POLONIUS\tklord chambxerlain. (LyORD POLONInUS:)\n" +
            "\n" +
            "HORAtTIO\tfriendk to Hamleto.\n" +
            "\n");
    }

    private void test(String insuf, boolean cs, String expected) {
        Q2Checksum.checksum(INPUT_FILENAME_BASE+insuf, OUTPUT_FILENAME, cs);
        try {
            assertTrue("Expected file \""+OUTPUT_FILENAME+"\", but no file was found", Files.exists(Paths.get(OUTPUT_FILENAME)));
            String actual = Files.readString(Paths.get(OUTPUT_FILENAME));
            assertTrue("Expected file to contain \""+expected+"\" but got \""+actual+"\"", actual.equals(expected));
        } catch (IOException e) {
            System.out.println(e);
            fail(e.getMessage());
        }
    }
}
