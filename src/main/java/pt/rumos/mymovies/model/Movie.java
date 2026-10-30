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
}
