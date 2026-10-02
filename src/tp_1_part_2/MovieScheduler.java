package tp_1_part_2;

import java.util.HashMap;
import java.util.Map;

public class MovieScheduler {
    HashMap<String, Slot> slots;

    public MovieScheduler(HashMap<String, Slot> slots) {
        if (slots == null) {
            throw new IllegalArgumentException("slots cannot be null");
        }
        this.slots = slots;
    }

    public void addMovie(String title, Slot slot) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        if (slots.containsKey(title)) {
            throw new IllegalArgumentException("title already exists");
        }
        if (slot == null) {
            throw new IllegalArgumentException("slot cannot be null");
        }
        for (Slot entryValues : this.slots.values()) {
            if (slot.hasTimeConflict(entryValues)) {
                throw new IllegalArgumentException("slot has time conflict");
            }
        }

        slots.put(title, slot);
    }

    public void removeMovie(String title) {
        if (!slots.containsKey(title)) {
            throw new IllegalArgumentException("title does not exist");
        }
        slots.remove(title);
    }

    public void getMovieSlot(String title) {
        for (Map.Entry<String, Slot> entry : slots.entrySet()) {
            System.out.println(slots.containsKey(entry.getKey()));
        }
    }

    public void updateMovieSlot(String title, Slot newSlot) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        if (!slots.containsKey(title)) {
            throw new IllegalArgumentException("title does not exist");
        }
        if (newSlot == null) {
            throw new IllegalArgumentException("newSlot cannot be null");
        }
        for (Map.Entry<String, Slot> entry : slots.entrySet()) {
            if (!entry.getKey().equals(title) && newSlot.hasTimeConflict(entry.getValue())) {
                if (newSlot.hasTimeConflict(entry.getValue())) {
                    throw new IllegalArgumentException("slot has time conflict");
                }
            }
        }
        slots.put(title, newSlot);
    }

    public void display() {
        for (Map.Entry<String, Slot> entry : slots.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public int getTotalDuration() {
        int totalDuration = 0;
        for (Map.Entry<String, Slot> entry : slots.entrySet()) {
            totalDuration += entry.getValue().getDuration();
        }
        return totalDuration;
    }

    public int getMoviesPerRooms() {
        Map<String, Integer> moviesPerRoom = new HashMap<>();
        for (Map.Entry<String, Slot> entry : slots.entrySet()) {
            String room = entry.getValue().getRoom();
            moviesPerRoom.put(room, moviesPerRoom.getOrDefault(room, 0) + 1);
        }
        return moviesPerRoom.size();
    }

    public void showStats() {
        System.out.println("Movie Scheduler Statistics");
        System.out.println("Number of movies: " + slots.size());
        System.out.println("Total duration: " + getTotalDuration());
        System.out.println("Movies per rooms" + getMoviesPerRooms());
    }




    public HashMap<String, Slot> getSlots() {
        return slots;
    }
    public void setSlots(HashMap<String, Slot> slots) {
        this.slots = slots;
    }


}
