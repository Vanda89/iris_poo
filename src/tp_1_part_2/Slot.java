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
        return this.startTime.plusMinutes(this.duration);
    }

    public void display() {
        System.out.println("Heure de début : " + this.startTime.toString() + " - Heure de fin : " + getEndTime().toString() + " - Durée : " + this.duration + " - Salle : " + this.room);
    }

    public boolean hasTimeConflict(Slot other) {
        if (other == null) {
            return false;
        }

        return this.room.equalsIgnoreCase(other.room)
                && this.startTime.isBefore(other.getEndTime())
                && getEndTime().isAfter(other.startTime);
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getRoom() {
        return this.room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return " startTime : " + startTime +
                ", duration : " + duration +
                ", room : " + room;
    }
}
