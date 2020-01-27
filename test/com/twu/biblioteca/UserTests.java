package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserTests {

    User user;

    @Before
    public void setUp() throws IncorrectLibraryNumberFormat {
        String libraryNumber = "123-4567";
        String password = "test1234";
        user = new User(libraryNumber, password);
    }

    @Test
    public void shouldHaveLibraryNumberWhenCreated() {
        assertThat(user.getLibraryNumber(), is("123-4567"));
    }

    @Test
    public void shouldHavePasswordWhenCreated() {
        assertThat(user.getPassword(), is("test1234"));
    }

    @Test(expected = IncorrectLibraryNumberFormat.class)
    public void shouldNotCreateUserWhenLibraryNumberIsWithIncorrectFormat() throws IncorrectLibraryNumberFormat {
        User falseUser = new User("12-4567", "test1234");
    }
}
