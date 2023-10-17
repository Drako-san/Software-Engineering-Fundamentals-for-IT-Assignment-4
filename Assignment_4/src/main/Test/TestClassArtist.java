import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
public class TestClassArtist {
    @Test
    public void testValidID1() {
        Artist artist = new Artist("123MMMRR_%", "07-08-1990", "Melbourne|Victoria|Australia",
                "Test this is just an example for the artist project", "singer", "pop, EDM",
                "2023, The Best Song In The World");
        assertTrue(artist.addArtist());
    }

    @Test
    public void testValidID2() {
        Artist artist = new Artist("333ABCDE@$", "07-08-1990", "Melbourne|Victoria|Australia",
                "Test this is just an example for the artist project", "singer", "pop, EDM",
                "2023, The Best Song In The World");
        assertTrue(artist.addArtist());
    }

    @Test
    public void testInvalidID1() {
        Artist artist = new Artist("123MMaRR_%", "07-08-1990", "Melbourne|Victoria|Australia",
                "Test this is just an example for the artist project", "singer", "pop, EDM",
                "2023, The Best Song In The World");
        assertFalse(artist.addArtist());
    }

    @Test
    public void testInvalidID2() {
        Artist artist = new Artist("12vMMMRR_%", "07-08-1990", "Melbourne|Victoria|Australia",
                "Test this is just an example for the artist project", "singer", "pop, EDM",
                "2023, The Best Song In The World");
        assertFalse(artist.addArtist());
    }

    @Test
    public void testValidBioLength1() {
        Artist artist = new Artist("123MMMRR_%", "07-08-1990", "Melbourne|Victoria|Australia",
                "Mic test this is just an example for the assignment", "singer", "pop, EDM",
                "2023, The Best Song In The World");
        assertTrue(artist.addArtist());
    }

    @Test
    public void testValidBioLength2() {
        Artist artist = new Artist("123MMMRR_%", "07-08-1990", "Melbourne|Victoria|Australia",
                "This is another example trying to make it more than ten words just to test things out", "singer", "pop, EDM",
                "2023, The Best Song In The World");
        assertTrue(artist.addArtist());
    }

    @Test
    public void testInvalidBioLength1() {
        Artist artist = new Artist("123MMMRR_%", "07-08-1990", "Melbourne|Victoria|Australia",
                "Make this short to fail", "singer", "pop, EDM",
                "2023, The Best Song In The World");
        assertFalse(artist.addArtist());
    }

    @Test
    public void testInvalidBioLength2() {
        Artist artist = new Artist("123MMMRR_%", "07-08-1990", "Melbourne|Victoria|Australia",
                "Making this one very long to test that the bios will be invalid if it is over thirty words still not enough so just putting random words to try to make this longer", "singer", "pop, EDM",
                "2023, The Best Song In The World");
        assertFalse(artist.addArtist());
    }

    @Test
    public void testValidIDandBioLenget1() {
        Artist artist = new Artist("123MMMRR_%", "07-08-1990", "Melbourne|Victoria|Australia",
                "This is another example trying to make it more than ten words just to test things out",
                "singer", "pop, EDM", "2023, The Best Song In The World");
        assertTrue(artist.addArtist());
    }

    @Test
    public void testValidIDandBioLenget2() {
        Artist artist = new Artist("333ABCDE@$", "07-08-1990", "Melbourne|Victoria|Australia",
                "Mic test this is just an example for the assignment",
                "singer", "pop, EDM", "2023, The Best Song In The World");
        assertTrue(artist.addArtist());
    }

    @Test
    public void testInvalidIDandBioLenget1() {
        Artist artist = new Artist("123MMaRR_%", "07-08-1990", "Melbourne|Victoria|Australia",
                "Make this short to fail",
                "singer", "pop, EDM", "2023, The Best Song In The World");
        assertFalse(artist.addArtist());
    }

    @Test
    public void testInvalidIDandBioLenget2() {
        Artist artist = new Artist("12vMMMRR_%", "07-08-1990", "Melbourne|Victoria|Australia",
                "Making this one very long to test that the bios will be invalid if it is over thirty words still not enough so just putting random words to try to make this longer",
                "singer", "pop, EDM", "2023, The Best Song In The World");
        assertFalse(artist.addArtist());
    }
}
