package tp_1_part_2;

import tp_0.Movie;

import java.time.LocalTime;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Slot slot = new Slot(LocalTime.of(9, 30), 150, "1");
        Slot slot2 = new Slot(LocalTime.of(10, 30), 120, "1");
        Slot slot3 = new Slot(LocalTime.of(15, 30), 130, "1");
        slot.display();
        slot2.display();
        System.out.println(slot.hasTimeConflict(slot2));
        System.out.println(slot.hasTimeConflict(slot3));
        System.out.println();
        HashMap<String, Slot> slots = new HashMap<String, Slot>();
        MovieScheduler scheduler = new MovieScheduler(slots);
        //scheduler.getMovieSlot();




    }
}
