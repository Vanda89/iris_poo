public class Song extends Media {
    private String album;
    private String artist;

    public Song(String title, int year, double duration, int rating, String album, String artist) {
        super(title, year, duration, rating);
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

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
