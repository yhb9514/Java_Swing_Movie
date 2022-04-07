package Movie;

public class MovieDto {
    int movieNumber;
    String movieName;
    int runningTime;
    String genre;
    String screenDate;

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

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getScreenDate() {
        return screenDate;
    }

    public void setScreenDate(String screenDate) {
        this.screenDate = screenDate;
    }
}
