package tp_0;

public class AnimationMovie extends  Movie {

    public AnimationMovie(String title, int year, double duration, String director, String producer) {
        super(title, year, duration, "Animation", director, producer);
    }

    @Override
    public double getDuration() {
        System.out.println("Showing High Fantasy movie duration");
        return super.getDuration();
    }

    @Override
    public void play() {
        System.out.println("Playing animation movie : " + title);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
