package tp_1_part_2;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public Slot getMovieSlot(String title) {

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title cannot be null or empty");
        }
        return slots.get(title);
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

    public Map<String, Integer> getMoviesPerRooms() {
        Map<String, Integer> moviesPerRoom = new HashMap<>();
        for (Map.Entry<String, Slot> entry : slots.entrySet()) {
            String room = entry.getValue().getRoom();
            moviesPerRoom.put(room, moviesPerRoom.getOrDefault(room, 0) + 1);
        }
        return moviesPerRoom;
    }

    public void showStats() {
        System.out.println("Movie Scheduler Statistics");
        System.out.println("Number of movies: " + slots.size());
        System.out.println("Total duration: " + getTotalDuration());
        System.out.println("Movies per rooms : " + getMoviesPerRooms());
    }

    private void importMovie(String[] movieData) {
        String title = movieData[0];
        String startTimeInString = movieData[1];
        String durationInString = movieData[2];
        String room = movieData[3];
        LocalTime startTime = LocalTime.parse(startTimeInString, DateTimeFormatter.ofPattern("HH'h'mm"));
        int duration = Integer.parseInt(durationInString);
        Slot slot = new Slot(startTime, duration, room);
        addMovie(title, slot);
    }

    public void importMovieSchedule(String[][] movies) {
        Map<String, Slot> backup = new HashMap<>(slots);
        try {
            for (String [] movie : movies) {
                System.out.println("Movie currently being imported : " + movie[0]);
                importMovie(movie);
                System.out.println("Movie imported : " + movie[0]);
            }
            System.out.println();
            System.out.println("Imports complete :");
        } catch (Exception exception) {
            System.out.println("List before rollback : " + slots);
            System.out.println("Loading failed : " + exception.getMessage());
            slots.clear();
            slots.putAll(backup);
            System.out.println("List after rollback : " + slots);
        }
    }

    public boolean hasMovieConflicts() {
        for (Slot slot : this.slots.values()) {
            for (Slot otherSlot : this.slots.values()) {
                if (slot != otherSlot) {
                    if (slot.hasTimeConflict(otherSlot)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<String> getMoviesByRoom(String room) {
        List<String> moviesInARoom = new ArrayList<>();
        for (Map.Entry<String, Slot> entry : slots.entrySet()) {
            if (entry.getValue().getRoom().equalsIgnoreCase(room)) {
                moviesInARoom.add(entry.getKey());

            }}
        return moviesInARoom;
    }

    public boolean isRoomAvailable(String room, String startTime, int duration) {
        if (room == null || room.isBlank()) {
            throw new IllegalArgumentException("room cannot be null or empty");
        }
        if (startTime == null || startTime.isBlank()) {
            throw new IllegalArgumentException("startTime cannot be null or empty");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("duration does not be equal to 0 or negative ");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH'h'mm");
        LocalTime startTimeParsed = LocalTime.parse(startTime, formatter);

        Slot slotToCheck = new Slot(startTimeParsed, duration, room);
        for (Slot existingSlot : slots.values()) {
            if (slotToCheck.hasTimeConflict(existingSlot)) {
                return false;
            }
        }
        return true;
    }

    public HashMap<String, Slot> getSlots() {
        return slots;
    }
    public void setSlots(HashMap<String, Slot> slots) {
        this.slots = slots;
    }
}
