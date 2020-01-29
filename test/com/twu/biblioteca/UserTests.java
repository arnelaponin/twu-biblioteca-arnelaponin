package com.twu.biblioteca;

import com.twu.biblioteca.exceptions.IncorrectLibraryNumberFormat;
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

    @Test
    public void shouldBeAbleToSetNameAfterCreation() throws IncorrectLibraryNumberFormat {
        User newUser = new User.UserBuilder("123-4567", "test1234").setName("Arne Laponin").build();
        assertThat(newUser.getName(), is("Arne Laponin"));
    }

    @Test
    public void shouldBeAbleToSetEmailAfterCreation() throws IncorrectLibraryNumberFormat {
        User newUser = new User.UserBuilder("123-4567", "test1234").setEmail("arne.laponin@thoughtworks.com").build();
        assertThat(newUser.getEmail(), is("arne.laponin@thoughtworks.com"));
    }

    @Test
    public void shouldBeAbleToSetPhoneAfterCreation() throws IncorrectLibraryNumberFormat {
        User newUser = new User.UserBuilder("123-4567", "test1234").setPhone("+34 123 4567").build();
        assertThat(newUser.getPhone(), is("+34 123 4567"));
    }

    @Test
    public void shouldPrintAllUserData() throws IncorrectLibraryNumberFormat {
        User newUser = new User.UserBuilder("111-1111", "test1234")
                .setName("Sara McGregor")
                .setEmail("sara.mcgregor@thoughtworks.com")
                .setPhone("+34 765 4321").build();
        StringBuilder sb = new StringBuilder();
        sb.append("Library number: ").append(newUser.getLibraryNumber()).append("\n");
        sb.append("Name: ").append(newUser.getName()).append("\n");
        sb.append("Email: ").append(newUser.getEmail()).append("\n");
        sb.append("Phone: ").append(newUser.getPhone());
        assertThat(newUser.toString(), is(sb.toString()));
    }

    @Test
    public void shouldPrintUserDataWithSOmeFieldsMissing() throws IncorrectLibraryNumberFormat {
        User newUser = new User.UserBuilder("111-1111", "test1234")
                .setName("Sara McGregor").build();
        StringBuilder sb = new StringBuilder();
        sb.append("Library number: ").append(newUser.getLibraryNumber()).append("\n");
        sb.append("Name: ").append(newUser.getName()).append("\n");
        sb.append("Email: ").append("-").append("\n");
        sb.append("Phone: ").append("-");
        assertThat(newUser.toString(), is(sb.toString()));
    }
}
