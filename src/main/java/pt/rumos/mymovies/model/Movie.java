package pt.rumos.mymovies.model;

public class Movie {
    //No pacote com.academia.mymovies.model,
    // crie o record Movie com os atributos id (Long),
    // title (String), director (String) e
    // year (Integer).
    private Long id;
    private String title;
    private String director;
    private int year;
    Double rating;


    public Movie(long id, String title, String director, int year) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
