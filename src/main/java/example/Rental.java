package example;

public class Rental {
    private final Movie movie;
    private final int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public double getCharge() {
        double result = 0;
        switch (movie.getPriceCode()) {
            case REGULAR -> {
                result += 2;
                if (daysRented > 2) result += (daysRented - 2) * 1.5;
            }
            case NEW_RELEASE -> result += daysRented * 3;
            case CHILDRENS -> {
                result += 1.5;
                if (daysRented > 3) result += (daysRented - 3) * 1.5;
            }
        }
        return result;
    }

    public int getFrequentRenterPoints() {
        return (movie.getPriceCode() == Movie.MovieType.NEW_RELEASE && daysRented > 1) ? 2 : 1;
    }

    public Movie getMovie() {
        return movie;
    }
}
