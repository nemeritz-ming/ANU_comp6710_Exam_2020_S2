package comp1110.exam;

import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class P1HelloWorldTest {
	@Rule
	public Timeout globalTimeout = Timeout.millis(1000);

	@Test
	public void testHasHello() {
	    P1HelloWorld.main(null);
	    assertTrue("Does not print hello", (outContent.toString().toLowerCase().lastIndexOf("hello") >= 0));
	}
	@Test
	public void testHasWorld() {
	    P1HelloWorld.main(null);
	    assertTrue("Does not print world", (outContent.toString().toLowerCase().lastIndexOf("world") >= 0));
	}
	@Test
	public void testHasExactlyOneSpace() {
	    P1HelloWorld.main(null);
	    assertTrue("Has wrong number of spaces", (outContent.toString().contains(" ") && outContent.toString().lastIndexOf(" ") == outContent.toString().indexOf(" ")));
	}
	@Test
	public void testEndsWithExclamation() {
	    P1HelloWorld.main(null);
	    Pattern p = Pattern.compile("!$");
	    Matcher m = p.matcher( outContent.toString().toLowerCase() );
	    assertTrue("Does not end with exclamation mark", m.find());
	}
	@Test
	public void testCorrectCase() {
	    P1HelloWorld.main(null);
	    assertTrue("Incorrect use of upper and lower case", (outContent.toString().lastIndexOf("Hello") >= 0 && outContent.toString().lastIndexOf("world") >= 0));
	}
	@Test
	public void testExactlyCorrect() {
	    P1HelloWorld.main(null);
	    Pattern p = Pattern.compile("^Hello world!\\s+$");
	    Matcher m = p.matcher( outContent.toString());
	    assertTrue("Text not precisely the same"+"-"+outContent.toString()+"-", m.matches());
	}
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
}
