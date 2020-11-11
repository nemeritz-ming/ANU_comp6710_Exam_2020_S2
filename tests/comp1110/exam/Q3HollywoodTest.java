package comp1110.exam;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.util.Set;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Q3HollywoodTest {
    @Rule
    public Timeout globalTimeout = Timeout.millis(1000);

    Q3Hollywood library;

    @Before
    public void setup() {
        library = new Q3Hollywood();
        addInitialFilms();
    }

    @Test
    public void testGetFilmCount() {
        Q3Hollywood empty = new Q3Hollywood();
        assertEquals("getFilmCount() returned incorrect number of films for empty library", 0, empty.getFilmCount());
        assertEquals("getFilmCount() returned incorrect number of films", 11, library.getFilmCount());
        addMoreFilms();
        assertEquals("getFilmCount() returned incorrect number of films", 14, library.getFilmCount());
    }

    @Test
    public void testGetFilmCountDuplicate() {
        addInitialFilms();
        assertEquals("getFilmCount() returned incorrect number of films", 11, library.getFilmCount());
        addMoreFilms();
        addMoreFilms();
        assertEquals("getFilmCount() returned incorrect number of films", 14, library.getFilmCount());
    }

    @Test
    public void testGetFilmCountDelete() {
        Q3Hollywood empty = new Q3Hollywood();
        final String NON_EXISTENT_ARTICLE = "Alien 5: Awakening";
        library.deleteFilm(NON_EXISTENT_ARTICLE);
        assertEquals("getFilmCount() returned incorrect number of films for empty library", 0, empty.getFilmCount());
        assertTrue(library.deleteFilm(names[0]));
        assertFalse(library.deleteFilm(names[0]));
        assertEquals("getFilmCount() returned incorrect number of films", 10, library.getFilmCount());
    }

    @Test
    public void testGetFilmsDirectedBy() {
        checkDirector("Ron Howard", Set.of());
        checkDirector("Joel Schumacher", Set.of("The Lost Boys", "Flatliners"));
        checkDirector("Penny Marshall", Set.of("Big", "Thelma & Louise"));
        checkDirector("Rob Reiner", Set.of("This Is Spinal Tap", "A Few Good Men"));
        addMoreFilms();
        checkDirector("Ron Howard", Set.of("Parenthood"));
        checkDirector("Penny Marshall", Set.of("Big", "A League of Their Own", "Thelma & Louise"));
    }

    @Test
    public void testGetFilmsDirectedByDuplicate() {
        addMoreFilms();
        addInitialFilms();
        addMoreFilms();
        checkDirector("Penny Marshall", Set.of("Big", "A League of Their Own", "Thelma & Louise"));
    }

    @Test
    public void testGetFilmsDirectedByDelete() {
        checkDirector("Penny Marshall", Set.of("Big", "Thelma & Louise"));
        library.deleteFilm("Big");
        checkDirector("Penny Marshall", Set.of("Thelma & Louise"));
        library.deleteFilm("Big");
        checkDirector("Penny Marshall", Set.of("Thelma & Louise"));
        checkDirector("Steven Spielberg", Set.of("The Color Purple"));
        library.deleteFilm("The Color Purple");
        checkDirector("Steven Spielberg", Set.of());
    }

    private void checkDirector(String director, Set<String> expected) {
        assertEquals("getFilmsDirectedBy(\"" + director + "\") returned incorrect set", expected, library.getFilmsDirectedBy(director));
    }

    @Test
    public void testGetFilmsActorOnly() {
        checkFilms("Danny Glover", Set.of("The Color Purple"));
        checkFilms("Whoopi Goldberg", Set.of("The Color Purple", "Ghost"));
        checkFilms("Tom Hanks", Set.of("Big", "Sleepless in Seattle"));
        addMoreFilms();
        checkFilms("Tom Hanks", Set.of("Big", "Sleepless in Seattle", "A League of Their Own"));
    }

    @Test
    public void testGetFilmsDirectorOnly() {
        checkFilms("Ron Howard", Set.of());
        checkFilms("Joel Schumacher", Set.of("The Lost Boys", "Flatliners"));
        checkFilms("Penny Marshall", Set.of("Big", "Thelma & Louise"));
        addMoreFilms();
        checkFilms("Ron Howard", Set.of("Parenthood"));
        checkFilms("Penny Marshall", Set.of("Big", "A League of Their Own", "Thelma & Louise"));
    }

    @Test
    public void testGetFilmsActorOrDirector() {
        checkFilms("Rob Reiner", Set.of("This Is Spinal Tap", "A Few Good Men", "Sleepless in Seattle"));
        addMoreFilms();
        checkFilms("Rob Reiner", Set.of("This Is Spinal Tap", "A Few Good Men", "Sleepless in Seattle", "When Harry Met Sally..."));
    }

    @Test
    public void testGetFilmsDelete() {
        checkFilms("Rob Reiner", Set.of("This Is Spinal Tap", "A Few Good Men", "Sleepless in Seattle"));
        checkFilms("Tom Cruise", Set.of("A Few Good Men"));
        checkFilms("Demi Moore", Set.of("A Few Good Men", "Ghost"));
        library.deleteFilm("A Few Good Men");
        checkFilms("Rob Reiner", Set.of("This Is Spinal Tap", "Sleepless in Seattle"));
        checkFilms("Tom Cruise", Set.of());
        checkFilms("Demi Moore", Set.of("Ghost"));
    }

    @Test
    public void testGetFilmsDuplicate() {
        library.addFilm(names[6], years[6], directors[6], Set.of(actors[6]));
        checkFilms("Joel Schumacher", Set.of("Flatliners", "The Lost Boys"));
        checkFilms("Kiefer Sutherland", Set.of("Flatliners", "The Lost Boys"));
        checkFilms("Kevin Bacon", Set.of("Flatliners", "Footloose", "A Few Good Men"));
    }

    private void checkFilms(String person, Set<String> expected) {
        Set<String> result = library.getFilms(person);
        assertEquals("getFilms(\"" + person + "\") returned incorrect set", expected, result);
    }

    @Test
    public void testAreCoStars() {
        checkCoStars("Kevin Bacon", "Kiefer Sutherland", true);
        checkCoStars("Kevin Bacon", "Rob Reiner", false);
        checkCoStars("Christopher Guest", "Dianne Wiest", false);
        checkCoStars("Tom Cruise", "Rob Reiner", false);
        checkCoStars("Jack Nicholson", "Demi Moore", true);
        checkCoStars("Jack Nicholson", "Rob Reiner", false);
        checkCoStars("Jennifer Grey", "Patrick Swayze", true);
        checkCoStars("Joaquin Phoenix", "Dianne Wiest", false);
        checkCoStars("Geena Davis", "Tom Hanks", false);
        checkCoStars("Joaquin Phoenix", "Whoopi Goldberg", false);
        addMoreFilms();
        checkCoStars("Geena Davis", "Tom Hanks", true);
        checkCoStars("Joaquin Phoenix", "Dianne Wiest", true);
        checkCoStars("Joaquin Phoenix", "Whoopi Goldberg", false);
    }

    @Test
    public void testAreCoStarsDelete() {
        addMoreFilms();
        checkCoStars("Kevin Bacon", "Dianne Wiest", true);
        checkCoStars("Geena Davis", "Tom Hanks", true);
        library.deleteFilm("A League of Their Own");
        library.deleteFilm("Footloose");
        checkCoStars("Kevin Bacon", "Dianne Wiest", false);
        checkCoStars("Geena Davis", "Tom Hanks", false);
    }

    private void checkCoStars(String person1, String person2, boolean expected) {
        boolean result = library.areCoStars(person1, person2);
        assertTrue("areCoStars(\"" + person1 + "\", \"" + person2 + "\") returned " + result + ", expected " + expected, expected == result);
    }

    @Test
    public void testGetFirstFilmYear() {
        checkFilmYear("Rob Reiner", 1984);
        checkFilmYear("Penny Marshall", 1988);
        addMoreFilms();
        checkFilmYear("Penny Marshall", 1988);
    }

    @Test
    public void testGetFirstFilmYearDelete() {
        checkFilmYear("Rob Reiner", 1984);
        checkFilmYear("Penny Marshall", 1988);
        library.deleteFilm("Big");
        checkFilmYear("Penny Marshall", 1991);
    }

    private void checkFilmYear(String person, int expected) {
        int result = library.getFirstFilmYear(person);
        assertTrue("getFirstFilmYear(\"" + person + "\") returned " + result + ", expected " + expected, expected == result);
    }

    @Test
    public void testGetNumActors() {
        assertEquals("getNumActors() returned incorrect value", 24, library.getNumActors());
        addMoreFilms();
        assertEquals("getNumActors() returned incorrect value", 31, library.getNumActors());
    }

    @Test
    public void testGetNumActorsDelete() {
        library.deleteFilm("Big");
        assertEquals("getNumActors() returned incorrect value", 23, library.getNumActors()); // - Elizabeth Perkins
        addMoreFilms();
        library.deleteFilm("The Color Purple");
        assertEquals("getNumActors() returned incorrect value", 29, library.getNumActors()); // - Danny Glover
    }

    @Test
    public void testGetMaxFilms() {
        assertEquals("getMaxFilms() returned incorrect value", 3, library.getMaxFilms()); // Rob Reiner or Kevin Bacon
        addMoreFilms();
        assertEquals("getMaxFilms() returned incorrect value", 4, library.getMaxFilms()); // Rob Reiner
    }

    @Test
    public void testGetMaxFilmsDuplicate() {
        addInitialFilms();
        assertEquals("getMaxFilms() returned incorrect value", 3, library.getMaxFilms()); // Rob Reiner or Kevin Bacon
        addMoreFilms();
        addMoreFilms();
        assertEquals("getMaxFilms() returned incorrect value", 4, library.getMaxFilms()); // Rob Reiner
    }

    @Test
    public void testGetMaxFilmsDelete() {
        library.deleteFilm("This Is Spinal Tap");
        library.deleteFilm("Flatliners");
        assertEquals("getMaxFilms() returned incorrect value", 2, library.getMaxFilms());
        addMoreFilms();
        library.deleteFilm("A Few Good Men");
        assertEquals("getMaxFilms() returned incorrect value", 3, library.getMaxFilms());
    }

    private void addInitialFilms() {
        for (int i = 0; i < names.length; i++) {
            library.addFilm(names[i], years[i], directors[i], Set.of(actors[i]));
        }
    }

    private void addMoreFilms() {
        library.addFilm("A League of Their Own", 1992, "Penny Marshall", Set.of("Geena Davis", "Tom Hanks", "Madonna", "Lori Petty", "Bill Pullman"));
        library.addFilm("Parenthood", 1989, "Ron Howard", Set.of("Steve Martin", "Joaquin Phoenix", "Keanu Reeves", "Dianne Wiest"));
        library.addFilm("When Harry Met Sally...", 1989, "Rob Reiner", Set.of("Meg Ryan", "Billy Crystal", "Carrie Fisher"));
    }

    String[] names = new String[]{
            "Footloose",
            "This Is Spinal Tap",
            "The Color Purple",
            "Thelma & Louise",
            "The Lost Boys",
            "Dirty Dancing",
            "Flatliners",
            "Ghost",
            "Big",
            "A Few Good Men",
            "Sleepless in Seattle"};
    int[] years = new int[]{
            1984,
            1984,
            1985,
            1991,
            1987,
            1987,
            1990,
            1990,
            1988,
            1992,
            1993
    };
    String[] directors = new String[]{
            "Herbert Ross",
            "Rob Reiner",
            "Steven Spielberg",
            "Penny Marshall",
            "Joel Schumacher",
            "Emile Ardolino",
            "Joel Schumacher",
            "Jerry Zucker",
            "Penny Marshall",
            "Rob Reiner",
            "Nora Ephron"
    };
    String[][] actors = new String[][]{
            new String[]{"Kevin Bacon", "Dianne Wiest", "John Lithgow"},
            new String[]{"Christopher Guest", "Rob Reiner", "Harry Shearer"},
            new String[]{"Danny Glover", "Whoopi Goldberg"},
            new String[]{"Susan Sarandon", "Geena Davis", "Harvey Keitel"},
            new String[]{"Corey Haim", "Dianne Wiest", "Kiefer Sutherland", "Alex Winter"},
            new String[]{"Jennifer Grey", "Patrick Swayze"},
            new String[]{"Kiefer Sutherland", "Julia Roberts", "Kevin Bacon"},
            new String[]{"Patrick Swayze", "Demi Moore", "Whoopi Goldberg"},
            new String[]{"Tom Hanks", "Elizabeth Perkins"},
            new String[]{"Tom Cruise", "Demi Moore", "Jack Nicholson", "Kevin Bacon"},
            new String[]{"Meg Ryan", "Tom Hanks", "Bill Pullman", "Rob Reiner"},};
}
