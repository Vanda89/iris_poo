public abstract class Media {
    // Attributs protégés
    protected String title;
    protected int year;
    protected double duration; // en minutes
    protected int rating;

    // Constructeur
    public Media(String title, int year, double duration) {
        this.title = title;
        this.year = year;
        this.duration = duration;
    }

    public Media(String title, int year, double duration, int rating) {
        this(title, year, duration);
        this.rating = rating;
    }

    // Méthodes concrètes
    public void displayInfo() {
        System.out.println("Title: " + title + ", année: " +  year + ", rating: " + rating);
    }

    // Retourne l'âge du media (année actuelle - année de création)
    public int getAge() {
        return java.time.LocalDate.now().getYear() - year;
    }

    // Set the rating
    public void rate(int rating) {
        setRating(rating);
    }

    // Méthodes abstraites
    public abstract void play();
    public abstract void pause();
    public abstract String getMediaType();
    public abstract double getFileSize(); // en MB

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating < 0 || rating > 20) {
            throw new IllegalArgumentException("Rating should be between 0 and 20");
        }
        this.rating = rating;
    }

    @Override
    public String toString() {
        return title;
    }

}