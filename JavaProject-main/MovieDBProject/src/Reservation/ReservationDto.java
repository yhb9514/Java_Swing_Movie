package Reservation;

public class ReservationDto {
    int reservationNumber;
    String id;
    int movieNumber;
    String movieName;
    String screenDate;
    String reservationDate;

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMovieNumber() {
        return movieNumber;
    }

    public void setMovieNumber(int movieNumber) {
        this.movieNumber = movieNumber;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getScreenDate() {
        return screenDate;
    }

    public void setScreenDate(String screenDate) {
        this.screenDate = screenDate;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }
}
