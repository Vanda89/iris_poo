public class Main {
    public static void main(String[] args) {
        // ITEM
        System.out.println("ITEM");
        Item smartphone = new Item("iPhone", 800.0, 0.20);
        System.out.println(smartphone.getTotalPrice()); // 960.0
        smartphone.applyDiscount(10.0); // 10% de remise
        System.out.println(smartphone.getTotalPrice()); // 864.0
        System.out.println();
        // MOVIE
        System.out.println("MOVIE");
        Movie drama = new Movie("The Godfather", 1972, 175, "Drama", "Francis Ford Coppola", "Albert S. Ruddy");
        System.out.println(drama.getAge());
        System.out.println(drama.getMediaType());
        drama.displayInfo();
        System.out.println((int) drama.getFileSize() + " MB");
        drama.showCredits();
        System.out.println();
        // SONG
        System.out.println("SONG");
        Song lullaby = new Song("Harvester of Sorrow", 1988, 5.45, "...And Justice for All", "Metallica");
        System.out.println(lullaby.getAge());
        System.out.println(lullaby.getMediaType());
        lullaby.displayInfo();
        System.out.println(lullaby.getFileSize() + " MB");
        lullaby.showLyrics();


    }
}
