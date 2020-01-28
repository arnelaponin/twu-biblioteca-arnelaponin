package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.IncorrectLibraryNumberFormat;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReservationTests {

    LibraryEntity book1;
    LibraryEntity movie1;
    User user;

    @Before
    public void setUp() throws IncorrectLibraryNumberFormat {
        book1 = new Book("Unquiet", "Linn Ullmann", "2018");
        movie1 = new Movie("Avengers: Endgame", "Anthony Russo, Joe Russo", "2019");
        user = new User("123-4567", "test1234");
    }

    @Test
    public void shouldHaveLibraryEntityWhenCreated() {
        Reservation reservation = new Reservation(book1, user);
        assertThat(reservation.getLibraryEntity().getTitle(), is(book1.getTitle()));
    }

    @Test
    public void shouldHaveUserWhenCreated() {
        Reservation reservation = new Reservation(book1, user);
        assertThat(reservation.getUser().getLibraryNumber(), is(user.getLibraryNumber()));
    }

    @Test
    public void shouldPrintInUserFriendlyWayCreated() {
        Reservation reservation = new Reservation(book1, user);
        String expectedResult = "Unquiet - 123-4567";
        assertThat(reservation.toString(), is(expectedResult));
    }
}
