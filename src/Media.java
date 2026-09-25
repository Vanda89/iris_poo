public abstract class Media {
    // Attributs protégés
    protected String title;
    protected int year;
    protected double duration; // en minutes
    private int age;

    // Constructeur
    public Media(String title, int year, double duration) {
        this.title = title;
        this.year = year;
        this.duration = duration;
    }

    // Méthodes concrètes
    public void displayInfo() {
        System.out.println("Title: " + title + ", année: " +  year);
    }

    // Retourne l'âge du media (année actuelle - année de création)
    public int getAge() {
        age = java.time.LocalDate.now().getYear() - year;
        return age;
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

}