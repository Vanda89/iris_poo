public class HighFantasyMovie extends Movie {

    public HighFantasyMovie(String title, int year, double duration, String director, String producer) {
        super(title, year, duration, "HighFantasy", director, producer);
    }

    @Override
    public void play() {
        System.out.println("Playing High Fantasy movie : " + title);
    }

    @Override
    public double getFileSize() {
        return  duration * 15;
    }

    @Override
    public void showCredits() {
        super.showCredits();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
