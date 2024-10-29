package example;

import org.junit.Test;
import java.util.List;
import static example.Movie.MovieType.*;
import static org.junit.Assert.assertEquals;

public class CustomerTest {

    // Тест для звичайного фільму (REGULAR) на 2 дні
    // Перевіряє базову вартість для двох днів без додаткової плати
    @Test
    public void testRegularMovieTwoDays() {
        Movie regularMovie = new Movie("Regular Movie", REGULAR);
        Rental rental = new Rental(regularMovie, 2);
        Customer customer = new Customer("Test Customer", List.of(rental));

        String expectedStatement = """
                Rental Record for Test Customer
                \tRegular Movie\t2.0
                Amount owed is 2.0
                You earned 1 frequent renter points""";

        assertEquals(expectedStatement, customer.buildStatement());
    }

    // Тест для звичайного фільму (REGULAR) на 3 дні
    // Перевіряє додаткову плату за третій день
    @Test
    public void testRegularMovieThreeDays() {
        Movie regularMovie = new Movie("Regular Movie", REGULAR);
        Rental rental = new Rental(regularMovie, 3);
        Customer customer = new Customer("Test Customer", List.of(rental));

        String expectedStatement = """
                Rental Record for Test Customer
                \tRegular Movie\t3.5
                Amount owed is 3.5
                You earned 1 frequent renter points""";

        assertEquals(expectedStatement, customer.buildStatement());
    }

    // Тест для новинки (NEW_RELEASE) на 1 день
    // Перевіряє розрахунок вартості без додаткового бала
    @Test
    public void testNewReleaseOneDay() {
        Movie newRelease = new Movie("New Release Movie", NEW_RELEASE);
        Rental rental = new Rental(newRelease, 1);
        Customer customer = new Customer("Test Customer", List.of(rental));

        String expectedStatement = """
                Rental Record for Test Customer
                \tNew Release Movie\t3.0
                Amount owed is 3.0
                You earned 1 frequent renter points""";

        assertEquals(expectedStatement, customer.buildStatement());
    }

    // Тест для новинки (NEW_RELEASE) на 2 дні
    // Перевіряє додатковий бал за оренду більше одного дня
    @Test
    public void testNewReleaseTwoDays() {
        Movie newRelease = new Movie("New Release Movie", NEW_RELEASE);
        Rental rental = new Rental(newRelease, 2);
        Customer customer = new Customer("Test Customer", List.of(rental));

        String expectedStatement = """
                Rental Record for Test Customer
                \tNew Release Movie\t6.0
                Amount owed is 6.0
                You earned 2 frequent renter points""";

        assertEquals(expectedStatement, customer.buildStatement());
    }

    // Тест для дитячого фільму (CHILDRENS) на 3 дні
    // Перевіряє базову вартість без додаткової плати
    @Test
    public void testChildrensMovieThreeDays() {
        Movie childrensMovie = new Movie("Children's Movie", CHILDRENS);
        Rental rental = new Rental(childrensMovie, 3);
        Customer customer = new Customer("Test Customer", List.of(rental));

        String expectedStatement = """
                Rental Record for Test Customer
                \tChildren's Movie\t1.5
                Amount owed is 1.5
                You earned 1 frequent renter points""";

        assertEquals(expectedStatement, customer.buildStatement());
    }

    // Тест для дитячого фільму (CHILDRENS) на 4 дні
    // Перевіряє додаткову плату за четвертий день
    @Test
    public void testChildrensMovieFourDays() {
        Movie childrensMovie = new Movie("Children's Movie", CHILDRENS);
        Rental rental = new Rental(childrensMovie, 4);
        Customer customer = new Customer("Test Customer", List.of(rental));

        String expectedStatement = """
                Rental Record for Test Customer
                \tChildren's Movie\t3.0
                Amount owed is 3.0
                You earned 1 frequent renter points""";

        assertEquals(expectedStatement, customer.buildStatement());
    }

    // Тест для клієнта з різними типами фільмів
    // Перевіряє правильність підрахунку загальної вартості і балів для різних типів оренд
    @Test
    public void testMixedRentals() {
        Movie regularMovie = new Movie("Regular Movie", REGULAR);
        Movie newRelease = new Movie("New Release Movie", NEW_RELEASE);
        Movie childrensMovie = new Movie("Children's Movie", CHILDRENS);

        Rental rental1 = new Rental(regularMovie, 2);
        Rental rental2 = new Rental(newRelease, 3);
        Rental rental3 = new Rental(childrensMovie, 4);

        Customer customer = new Customer("Test Customer", List.of(rental1, rental2, rental3));

        String expectedStatement = """
                Rental Record for Test Customer
                \tRegular Movie\t2.0
                \tNew Release Movie\t9.0
                \tChildren's Movie\t3.0
                Amount owed is 14.0
                You earned 4 frequent renter points""";

        assertEquals(expectedStatement, customer.buildStatement());
    }

    // Тест для порожнього списку оренд
    // Перевіряє, що звіт коректно формується при відсутності оренд
    @Test
    public void testEmptyRentals() {
        Customer customer = new Customer("Test Customer", List.of());

        String expectedStatement = """
                Rental Record for Test Customer
                Amount owed is 0.0
                You earned 0 frequent renter points""";

        assertEquals(expectedStatement, customer.buildStatement());
    }

    // Тест для фільму з нульовою кількістю днів оренди
    // Перевіряє, що не виникає помилок при 0 днях оренди, а також нараховується 1 базовий бал
    @Test
    public void testRentalWithZeroDays() {
        Movie regularMovie = new Movie("Regular Movie", REGULAR);
        Rental rental = new Rental(regularMovie, 0);
        Customer customer = new Customer("Test Customer", List.of(rental));

        String expectedStatement = """
            Rental Record for Test Customer
            \tRegular Movie\t2.0
            Amount owed is 2.0
            You earned 1 frequent renter points""";

        assertEquals(expectedStatement, customer.buildStatement());
    }

}
