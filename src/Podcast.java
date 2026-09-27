public class Podcast extends Media{
    private String host;
    private int episodeNumber;

    public Podcast(String title, int year, double duration, String host, int episodeNumber) {
        super(title, year, duration);
        this.host = host;
        this.episodeNumber = episodeNumber;
    }

    @Override
    public void play() {
        System.out.println("Playing podcast: " + title);
    }

    @Override
    public void pause() {
        System.out.println("Podcast paused");
    }

    @Override
    public String getMediaType() {
        return "Podcast";
    }

    @Override
    public double getFileSize() {
        return duration * 30;
    }

    public void subscribe() {
        System.out.println("Subscription activated");
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }
}
