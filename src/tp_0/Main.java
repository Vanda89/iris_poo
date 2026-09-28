package tp_0;

public class Main {
    public static void main(String[] args) {
        MediaPlayer player = new MediaPlayer(10);
        // ITEM
        System.out.println("ITEM");
        Item smartphone = new Item("iPhone", 800.0, 0.20);
        System.out.println(smartphone.getTotalPrice()); // 960.0
        smartphone.applyDiscount(10.0); // 10% de remise
        System.out.println(smartphone.getTotalPrice()); // 864.0
        System.out.println();
        // MOVIE
        System.out.println("MOVIE");
        Movie godfather = new Movie("The Godfather", 1972, 175, "Drama", "Francis Ford Coppola", "Albert S. Ruddy");
        Movie inception = new Movie("Inception", 2010, 148, "Thriller", "Christopher Nolan", "Christopher Nolan");
        HighFantasyMovie lordsOfTheRings = new HighFantasyMovie("Lord of the rings : Fellowship of the Ring", 2001, 219, "Peter Jackson", "Peter Jackson");
        HighFantasyMovie dungeonsAndDragons = new HighFantasyMovie("Dungeons & Dragons", 2023, 134, "Jonathan Goldstein", "Brian David Goldner");
        AnimationMovie lionKing = new AnimationMovie( "Le Roi Lion",1994, 88,"Roger Allers et Rob Minkoff","Don Hahn");
        AnimationMovie toyStory = new AnimationMovie("Toy Story",1995,81,"John Lasseter","Bonnie Arnold");
        System.out.println(godfather.getAge());
        System.out.println(godfather.getMediaType());
        godfather.displayInfo();
        System.out.println((int) godfather.getFileSize() + " MB");
        godfather.showCredits();
        inception.displayInfo();
        //lordsOfTheRings.setRating(21);
        lordsOfTheRings.rate(20);
        dungeonsAndDragons.rate(16);
        inception.rate(14);
        System.out.println();
        // SONG
        System.out.println("SONG");
        Song lullaby = new Song("Harvester of Sorrow", 1988, 5.45, 17, "...And Justice for All", "Metallica");
        Song bohemian = new Song("Bohemian Rhapsody", 1975, 6, 18,"A Night at the Opera", "Queen");
        Song thriller = new Song("Thriller", 1982, 5.5, 19, "Thriller", "Mickael Jackson");
        Song dynamite = new Song("Dynamite", 2020, 3.19, 20, "Be", "BTS");
        System.out.println(lullaby.getTitle());
        System.out.println(lullaby.getMediaType());
        lullaby.displayInfo();
        System.out.println(lullaby.getFileSize() + " MB");
        lullaby.showLyrics();
        System.out.println(bohemian.getAlbum());
        System.out.println(thriller.getArtist());
        System.out.println();
        // PODCAST
        System.out.println("PODCAST");
        Podcast techCoffee = new Podcast("Tech Café", 2014, 90, "Spotify", 524);
        System.out.println(techCoffee.getFileSize());
        System.out.println();


        // MEDIAS
        player.addMedia(godfather);
        player.addMedia(lullaby);
        player.addMedia(thriller);
        player.addMedia(bohemian);
        player.addMedia(inception);
        player.addMedia(techCoffee);
        player.addMedia(lordsOfTheRings);
        player.addMedia(dungeonsAndDragons);
        player.addMedia(lionKing);
        player.addMedia(toyStory);
        player.playAll();
        System.out.println();
        player.displayPlaylist();
        System.out.println();
        System.out.println("The total size of the media playlist is : " + player.getTotalSize() + " MB");
        System.out.println();
        System.out.println("The media with the same type are : ");
        for (Media media : player.getMediaByType("tp_0.Song")) {
            System.out.println(media.getTitle());
        }
        System.out.println();
        System.out.println("Subscriptions : ");
        for (Media media : player.getMediaByType("tp_0.Podcast")) {
            System.out.println(media.getTitle());
            techCoffee.subscribe();
        }
        System.out.println();
        int specifiedYear = 1989;
        System.out.println("The media created after the year " + specifiedYear + " are  : ");
        for (Media media : player.getRecentMedia(specifiedYear)) {
            System.out.println(media.getTitle() + " " + media.getYear());
        }
        System.out.println();
        double specifiedSize = 22;
        System.out.println("The media larger than the size " + specifiedSize + " MB are : ");
        for (Media media : player.getLargeMedia(specifiedSize)) {
            System.out.println(media.getTitle() + " " + media.getFileSize());
        }
        System.out.println();
        System.out.println("The total duration of the media playlist is : " + player.getTotalDuration() + " H");
        System.out.println();
        System.out.println("List before shuffle : ");
        for (Media media : player.getPlaylist()) {
            System.out.println(media.getTitle());
        }
        player.shuffle();
        System.out.println();
        System.out.println("List after shuffle : ");
        for (Media media : player.getPlaylist()) {
            System.out.println(media.getTitle());
        }

    }
}
