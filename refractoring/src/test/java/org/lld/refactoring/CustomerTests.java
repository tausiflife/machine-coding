package org.lld.refactoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.refactoring.models.Customer;
import org.lld.refactoring.models.Movie;
import org.lld.refactoring.models.Rental;

public class CustomerTests {

    @DisplayName("Should print statement with only regular movie for less than 2 days")
    @Test
    void shouldPrintStatementForRegularMovieWithLessThan2DaysRent() {
        String movieName = "DDLJ";
        String customerName = "Bob";
        Movie movie = new Movie(movieName, 0);
        Rental regular = new Rental(movie, 1);
        Customer customer = new Customer(customerName);
        customer.addRental(regular);
        Assertions.assertEquals("Rental Record for "+customer.getName()+"\n" +
                "\t"+movieName+"\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points", customer.statement());
    }

    @DisplayName("Should print statement with only regular movie for more than 2 days")
    @Test
    void shouldPrintStatementForRegularMovieWithMoreThan2DaysRent() {
        String movieName = "DDLJ";
        String customerName = "Bob";
        Movie movie = new Movie(movieName, 0);
        Rental regular = new Rental(movie, 3);
        Customer customer = new Customer(customerName);
        customer.addRental(regular);
        Assertions.assertEquals("Rental Record for "+customer.getName()+"\n" +
                "\t"+movieName+"\t3.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter points", customer.statement());
    }

    @DisplayName("Should print statement with multiple movies for less than 2 days")
    @Test
    void shouldPrintStatementForMultipleRegularMoviesWithLessThan2DaysRent() {
        String movieName1 = "LOTR 1";
        String movieName2 = "HOME ALONE";
        String customerName = "Bob";
        Customer customer = new Customer(customerName);
        Movie movie = new Movie(movieName1, 0);
        Rental regular = new Rental(movie, 1);
        customer.addRental(regular);
        movie = new Movie(movieName2, 0);
        regular = new Rental(movie, 1);
        customer.addRental(regular);
        Assertions.assertEquals("Rental Record for "+customer.getName()+"\n" +
                "\t"+movieName1+"\t2.0\n" +
                "\t"+movieName2+"\t2.0\n" +
                "Amount owed is 4.0\n" +
                "You earned 2 frequent renter points", customer.statement());
    }

    @DisplayName("Should print statement with multiple movies for more than 2 days")
    @Test
    void shouldPrintStatementForMultipleRegularMoviesWithMoreThan2DaysRent() {
        String movieName1 = "LOTR 1";
        String movieName2 = "HOME ALONE";
        String customerName = "Bob";
        Customer customer = new Customer(customerName);
        Movie movie = new Movie(movieName1, 0);
        Rental regular = new Rental(movie, 3);
        customer.addRental(regular);
        movie = new Movie(movieName2, 0);
        regular = new Rental(movie, 3);
        customer.addRental(regular);
        Assertions.assertEquals("Rental Record for "+customer.getName()+"\n" +
                "\t"+movieName1+"\t3.5\n" +
                "\t"+movieName2+"\t3.5\n" +
                "Amount owed is 7.0\n" +
                "You earned 2 frequent renter points", customer.statement());
    }

    @DisplayName("Should print statement with only new movie for less than 2 days")
    @Test
    void shouldPrintStatementForNewMovieWithLessThan2DaysRent() {
        String movieName = "DDLJ";
        String customerName = "Bob";
        Movie movie = new Movie(movieName, 1);
        Rental regular = new Rental(movie, 1);
        Customer customer = new Customer(customerName);
        customer.addRental(regular);
        Assertions.assertEquals("Rental Record for "+customer.getName()+"\n" +
                "\t"+movieName+"\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points", customer.statement());
    }

    @DisplayName("Should print statement with only new movie for more than 2 days")
    @Test
    void shouldPrintStatementForNewMovieWithMoreThan2DaysRent() {
        String movieName = "DDLJ";
        String customerName = "Bob";
        Movie movie = new Movie(movieName, 1);
        Rental regular = new Rental(movie, 3);
        Customer customer = new Customer(customerName);
        customer.addRental(regular);
        Assertions.assertEquals("Rental Record for "+customer.getName()+"\n" +
                "\t"+movieName+"\t9.0\n" +
                "Amount owed is 9.0\n" +
                "You earned 2 frequent renter points", customer.statement());
    }

    @DisplayName("Should print statement with multiple new movies for less than 2 days")
    @Test
    void shouldPrintStatementForMultipleNewMoviesWithLessThan2DaysRent() {
        String movieName1 = "LOTR 1";
        String movieName2 = "HOME ALONE";
        String customerName = "Bob";
        Customer customer = new Customer(customerName);
        Movie movie = new Movie(movieName1, 1);
        Rental regular = new Rental(movie, 1);
        customer.addRental(regular);
        movie = new Movie(movieName2, 1);
        regular = new Rental(movie, 1);
        customer.addRental(regular);
        Assertions.assertEquals("Rental Record for "+customer.getName()+"\n" +
                "\t"+movieName1+"\t3.0\n" +
                "\t"+movieName2+"\t3.0\n" +
                "Amount owed is 6.0\n" +
                "You earned 2 frequent renter points", customer.statement());
    }

    @DisplayName("Should print statement with multiple new movies for more than 2 days")
    @Test
    void shouldPrintStatementForMultipleNewMoviesWithMoreThan2DaysRent() {
        String movieName1 = "LOTR 1";
        String movieName2 = "HOME ALONE";
        String customerName = "Bob";
        Customer customer = new Customer(customerName);
        Movie movie = new Movie(movieName1, 1);
        Rental regular = new Rental(movie, 3);
        customer.addRental(regular);
        movie = new Movie(movieName2, 1);
        regular = new Rental(movie, 3);
        customer.addRental(regular);
        Assertions.assertEquals("Rental Record for "+customer.getName()+"\n" +
                "\t"+movieName1+"\t9.0\n" +
                "\t"+movieName2+"\t9.0\n" +
                "Amount owed is 18.0\n" +
                "You earned 4 frequent renter points", customer.statement());
    }

    @DisplayName("Should print statement with only children movie for less than 3 days")
    @Test
    void shouldPrintStatementForChildrenMovieWithLessThan3DaysRent() {
        String movieName = "DDLJ";
        String customerName = "Bob";
        Movie movie = new Movie(movieName, 2);
        Rental regular = new Rental(movie, 1);
        Customer customer = new Customer(customerName);
        customer.addRental(regular);
        Assertions.assertEquals("Rental Record for "+customer.getName()+"\n" +
                "\t"+movieName+"\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points", customer.statement());
    }

    @DisplayName("Should print statement with only Children movie for more than 3 days")
    @Test
    void shouldPrintStatementForChildrenMovieWithMoreThan3DaysRent() {
        String movieName = "DDLJ";
        String customerName = "Bob";
        Movie movie = new Movie(movieName, 2);
        Rental regular = new Rental(movie, 4);
        Customer customer = new Customer(customerName);
        customer.addRental(regular);
        Assertions.assertEquals("Rental Record for "+customer.getName()+"\n" +
                "\t"+movieName+"\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points", customer.statement());
    }

    @DisplayName("Should print statement with multiple Children movies for less than 3 days")
    @Test
    void shouldPrintStatementForMultipleChildrenMoviesWithLessThan3DaysRent() {
        String movieName1 = "LOTR 1";
        String movieName2 = "HOME ALONE";
        String customerName = "Bob";
        Customer customer = new Customer(customerName);
        Movie movie = new Movie(movieName1, 2);
        Rental regular = new Rental(movie, 1);
        customer.addRental(regular);
        movie = new Movie(movieName2, 2);
        regular = new Rental(movie, 1);
        customer.addRental(regular);
        Assertions.assertEquals("Rental Record for "+customer.getName()+"\n" +
                "\t"+movieName1+"\t1.5\n" +
                "\t"+movieName2+"\t1.5\n" +
                "Amount owed is 3.0\n" +
                "You earned 2 frequent renter points", customer.statement());
    }

    @DisplayName("Should print statement with multiple Children movies for more than 3 days")
    @Test
    void shouldPrintStatementForMultipleChildrenMoviesWithMoreThan3DaysRent() {
        String movieName1 = "LOTR 1";
        String movieName2 = "HOME ALONE";
        String customerName = "Bob";
        Customer customer = new Customer(customerName);
        Movie movie = new Movie(movieName1, 2);
        Rental regular = new Rental(movie, 4);
        customer.addRental(regular);
        movie = new Movie(movieName2, 2);
        regular = new Rental(movie, 4);
        customer.addRental(regular);
        Assertions.assertEquals("Rental Record for "+customer.getName()+"\n" +
                "\t"+movieName1+"\t3.0\n" +
                "\t"+movieName2+"\t3.0\n" +
                "Amount owed is 6.0\n" +
                "You earned 2 frequent renter points", customer.statement());
    }

    @DisplayName("Should print statement with multiple movies for less than 3 days")
    @Test
    void shouldPrintStatementForMultipleMoviesWithLessThan3DaysRent() {
        String movieName1 = "LOTR 1";
        String movieName2 = "HOME ALONE";
        String customerName = "Bob";
        Customer customer = new Customer(customerName);
        Movie movie = new Movie(movieName1, 0);
        Rental regular = new Rental(movie, 1);
        customer.addRental(regular);
        movie = new Movie(movieName2, 2);
        regular = new Rental(movie, 1);
        customer.addRental(regular);
        Assertions.assertEquals("Rental Record for "+customer.getName()+"\n" +
                "\t"+movieName1+"\t2.0\n" +
                "\t"+movieName2+"\t1.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 2 frequent renter points", customer.statement());
    }

    @DisplayName("Should print statement with multiple movies for more than 3 days")
    @Test
    void shouldPrintStatementForMultipleMoviesWithMoreThan3DaysRent() {
        String movieName1 = "LOTR 1";
        String movieName2 = "HOME ALONE";
        String customerName = "Bob";
        Customer customer = new Customer(customerName);
        Movie movie = new Movie(movieName1, 0);
        Rental regular = new Rental(movie, 4);
        customer.addRental(regular);
        movie = new Movie(movieName2, 2);
        regular = new Rental(movie, 4);
        customer.addRental(regular);
        Assertions.assertEquals("Rental Record for "+customer.getName()+"\n" +
                "\t"+movieName1+"\t5.0\n" +
                "\t"+movieName2+"\t3.0\n" +
                "Amount owed is 8.0\n" +
                "You earned 2 frequent renter points", customer.statement());
    }

    @DisplayName("Should print statement with multiple movies for more than 2 days")
    @Test
    void shouldPrintStatementForMultipleMoviesWithMoreThan2DaysRent() {
        String movieName1 = "LOTR 1";
        String movieName2 = "HOME ALONE";
        String customerName = "Bob";
        Customer customer = new Customer(customerName);
        Movie movie = new Movie(movieName1, 0);
        Rental regular = new Rental(movie, 3);
        customer.addRental(regular);
        movie = new Movie(movieName2, 2);
        regular = new Rental(movie, 3);
        customer.addRental(regular);
        Assertions.assertEquals("Rental Record for "+customer.getName()+"\n" +
                "\t"+movieName1+"\t3.5\n" +
                "\t"+movieName2+"\t1.5\n" +
                "Amount owed is 5.0\n" +
                "You earned 2 frequent renter points", customer.statement());
    }

    @DisplayName("Should print statement with multiple movies with new release for more than 2 days")
    @Test
    void shouldPrintStatementForMultipleMoviesWithNewMoreThan2DaysRent() {
        String movieName1 = "LOTR 1";
        String movieName2 = "HOME ALONE";
        String movieName3 = "LOTR 2";
        String customerName = "Bob";
        Customer customer = new Customer(customerName);
        Movie movie = new Movie(movieName1, 0);
        Rental regular = new Rental(movie, 3);
        customer.addRental(regular);
        movie = new Movie(movieName2, 2);
        regular = new Rental(movie, 3);
        customer.addRental(regular);
        movie = new Movie(movieName3, 1);
        regular = new Rental(movie, 3);
        customer.addRental(regular);
        Assertions.assertEquals("Rental Record for "+customer.getName()+"\n" +
                "\t"+movieName1+"\t3.5\n" +
                "\t"+movieName2+"\t1.5\n" +
                "\t"+movieName3+"\t9.0\n" +
                "Amount owed is 14.0\n" +
                "You earned 4 frequent renter points", customer.statement());
    }
}
