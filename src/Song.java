public class Song extends Media {
    private String album;
    private String artist;

    public Song(String title, int year, double duration, String album, String artist) {
        super(title, year, duration);
        this.album = album;
        this.artist = artist;
    }

    @Override
    public void play() {
        System.out.println("Playing song: " + this.title);
    }

    @Override
    public void pause() {
        System.out.println("Song paused");
    }

    @Override
    public String getMediaType() {
        return "Song";
    }

    @Override
    public double getFileSize() {
        return duration * 4;
    }

    public void showLyrics() {
        System.out.println("Displaying lyrics for " + title);
    }
}
