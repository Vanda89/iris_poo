package tp_1_part_2;

import java.time.LocalTime;

public class Slot {
    private LocalTime startTime;
    private int duration;
    private String room;

    public Slot(LocalTime startTime, int duration, String room) {
        if (startTime == null ) {
            throw new IllegalArgumentException("Start time cannot be null");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration cannot be negative");
        }
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }

        this.startTime = startTime;
        this.duration = duration;
        this.room = room;
    }

    public LocalTime getEndTime() {
        return startTime.plusMinutes(duration);
    }

    public void display() {
        System.out.println("Heure de début : " + startTime.toString() + " - Heure de fin : " + getEndTime().toString() + " - Durée : " + duration + " - Salle : " + room);
    }

    public boolean hasTimeConflict(Slot other) {
        if (other == null) {
            return false;
        }

        return room.equalsIgnoreCase(other.room)
                && startTime.isBefore(other.getEndTime())
                && getEndTime().isAfter(other.startTime);
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }


}
