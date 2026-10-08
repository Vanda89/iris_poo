package tp_0;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MediaPlayer {
    private List<Media> playlist;
    private int currentIndex;
    private int mediaCount;
    private int capacity;

    // Initialiser le tableau
    public MediaPlayer(int capacity) {
       this.capacity = capacity;
       this.playlist = new ArrayList<>();
       this.currentIndex = 0;
       this.mediaCount = 0;
    }

    // Ajouter si place disponible
    public void addMedia(Media media) {
        if (mediaCount < capacity) {
            playlist.add(media);
            mediaCount++;
        }
    }

    // Jouer tous les medias (polymorphisme !)
    public void playAll() {
        for (Media media : playlist) {
            media.play();
        }
    }

    // Afficher tous les medias avec leurs infos
    public void displayPlaylist() {
        for (Media media : playlist) {
            media.displayInfo();
        }
    }

    // Calculer la taille totale de tous les medias
    public double getTotalSize() {
        double totalSize = 0f;
        for (Media media : playlist) {
            totalSize += media.getFileSize();
        }
        return totalSize ;
    }

    // Retourner tous les medias d'un type donné
    public Media[] getMediaByType(String type) {
        List<Media> mediaWithSameType = new ArrayList<>();

        for (Media media : playlist) {
            if (media.getMediaType().equals(type)) {
                mediaWithSameType.add(media);
            }
        }
        return mediaWithSameType.toArray(new Media[0]);
    }

    // Returns media created after the specified year
    public Media[] getRecentMedia(int year) {
        List<Media> mediaCreatedAfterSpecifiedYear = new ArrayList<>();

        for (Media media : playlist) {
            if (media.getYear() > year) {
                mediaCreatedAfterSpecifiedYear.add(media);
            }
        }
        return mediaCreatedAfterSpecifiedYear.toArray(new Media[0]);
    }

    // Returns media at a size larger than the specified size
    public Media[] getLargeMedia(double sizeLimit) {
        List<Media> mediaLargerThanSizeLimit = new ArrayList<>();

        for (Media media : playlist) {
            if (media.getFileSize() > sizeLimit) {
                mediaLargerThanSizeLimit.add(media);
            }
        }
        return mediaLargerThanSizeLimit.toArray(new Media[0]);
    }

    // Displays the total duration of all media in hours
    public double getTotalDuration() {
        double totalDuration = 0f;

        for (Media media : playlist) {
            totalDuration += media.getDuration();
        }

        double totalDurationInHours = totalDuration / 60f ;
        return Math.floor(totalDurationInHours * 100) / 100;
    }

    public void shuffle() {
        Collections.shuffle(playlist);
    }

    public List<Media> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Media> playlist) {
        this.playlist = playlist;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public int getMediaCount() {
        return mediaCount;
    }

    public void setMediaCount(int mediaCount) {
        this.mediaCount = mediaCount;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


}
