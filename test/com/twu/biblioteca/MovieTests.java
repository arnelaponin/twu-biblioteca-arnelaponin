package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MovieTests {

    Movie movie;

    @Before
    public void setUp() {
        movie = new Movie("Avengers: Endgame", "Anthony Russo, Joe Russo", "2019", 9.5);
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
    public void shouldHaveRatingWhenMovieIsCreated() {
        assertThat(movie.getRating(), is(9.5));
    }
}
