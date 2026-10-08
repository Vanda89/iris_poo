package tp_0;

public class Movie extends Media {
    private String genre;
    private String director;
    private String producer;

    public Movie(String title, int year, double duration, String genre, String director, String producer) {
        super(title, year, duration);
        this.genre = genre;
        this.director = director;
        this.producer = producer;
    }

    @Override
    public void play() {
        System.out.println("Playing movie: " + title);
    }

    @Override
    public void pause() {
        System.out.println("Movie paused");
    }

    @Override
    public String getMediaType() {
        return "Movie";
    }

    @Override
    public double getFileSize() {
        return duration * 10f;
    }

    public void showCredits() {
        System.out.println("Director: " + director + ", producer : " + producer);
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}
