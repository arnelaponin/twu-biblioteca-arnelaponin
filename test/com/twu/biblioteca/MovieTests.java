package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MovieTests {

    Movie movie;

    @Before
    public void setUp() {
        movie = new Movie("Avengers: Endgame", "Anthony Russo, Joe Russo", "2019");
    }

    @Test
    public void shouldBeAvailableWhenMovieIsCreated() {
        assertTrue(movie.isAvailable());
    }

    @Test
    public void shouldHaveTitleWhenMovieIsCreated() {
        assertThat(movie.getTitle(), is("Avengers: Endgame"));
    }

    @Test
    public void shouldHaveDirectorWhenMovieIsCreated() {
        assertThat(movie.getDirector(), is("Anthony Russo, Joe Russo"));
    }

    @Test
    public void shouldHaveYearWhenMovieIsCreated() {
        assertThat(movie.getYear(), is("2019"));
    }

    @Test
    public void shouldHaveRating0WhenMovieIsCreated() {
        assertThat(movie.getRating(), is(0.0));
    }

    @Test
    public void shouldHaveRatingWhenRatingSet() throws RatingRangeException {
        movie.setRating(9);
        assertThat(movie.getRating(), is(9.0));
    }

    @Test
    public void shouldPrintUnratedWhenRatingNotSet() {
        assertThat(movie.toString(), containsString("unrated"));
    }

    @Test
    public void shouldNotPrintUnratedWhenRatingSet() throws RatingRangeException {
        movie.setRating(9);
        assertNotNull(movie.toString());
        assertThat(movie.toString(), containsString("9"));
    }

    @Test(expected = RatingRangeException.class)
    public void shouldNotHaveRating0dWhenRatingSet() throws RatingRangeException {
        movie.setRating(0);
    }

    @Test
    public void testMovieCheckOut() {
        Movie movie = new Movie("Avengers: Endgame", "Anthony Russo, Joe Russo", "2019");
        movie.checkOut();
        assertFalse(movie.isAvailable());
    }
}
