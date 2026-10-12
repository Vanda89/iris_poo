package tp_1_part_2;

import java.time.LocalTime;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Slot slot = new Slot(LocalTime.of(9, 30), 150, "1");
        Slot slot2 = new Slot(LocalTime.of(10, 30), 120, "1");
        Slot slot3 = new Slot(LocalTime.of(15, 30), 130, "1");
        Slot slot4 = new Slot(LocalTime.of(22, 30), 100, "3");
        Slot slot5 = new Slot(LocalTime.of(18, 30), 100, "3");
        slot.display();
        slot2.display();
        System.out.println(slot.hasTimeConflict(slot2));
        System.out.println(slot.hasTimeConflict(slot3));
        System.out.println();

        System.out.println("---Valid data test---");
        HashMap<String, Slot> slots = new HashMap<String, Slot>();
        MovieScheduler scheduler = new MovieScheduler(slots);
        scheduler.importMovieSchedule(MovieSlotTestData.getValidMovieSchedule());
        scheduler.display();
        System.out.println();
        scheduler.showStats();
        System.out.println("Has time conflict ? " + scheduler.hasMovieConflicts());

        System.out.println();

        System.out.println("---Problematic data test---");
        slots = new HashMap<>();
        scheduler = new MovieScheduler(slots);
        scheduler.importMovieSchedule(MovieSlotTestData.getProblematicMovieSchedule());
        scheduler.display();
        System.out.println();

        System.out.println("---Duplicate data test---");
        slots = new HashMap<>();
        scheduler = new MovieScheduler(slots);
        scheduler.importMovieSchedule(MovieSlotTestData.getDuplicateMovieSchedule());
        scheduler.display();
        System.out.println();

        System.out.println("---Conflicting data test---");
        slots = new HashMap<>();
        scheduler = new MovieScheduler(slots);
        scheduler.importMovieSchedule(MovieSlotTestData.getConflictingSchedule());
        scheduler.display();
        System.out.println();

        System.out.println("---Invalid data test---");
        slots = new HashMap<>();
        scheduler = new MovieScheduler(slots);
        scheduler.importMovieSchedule(MovieSlotTestData.getInvalidMovieSchedule());
        scheduler.display();
        System.out.println();

        System.out.println("---Large movie data test---");
        slots = new HashMap<>();
        scheduler = new MovieScheduler(slots);
        scheduler.importMovieSchedule(MovieSlotTestData.getLargeMovieSchedule());
        scheduler.display();
        System.out.println();

        System.out.println("---Test hasMovieConflict---");
        HashMap<String, Slot> conflictSlots = new HashMap<>();
        conflictSlots.put("Movie 1", slot);
        conflictSlots.put("Movie 2", slot2);
        conflictSlots.put("Movie 3", slot3);
        conflictSlots.put("Movie 4", slot4);
        scheduler = new MovieScheduler(conflictSlots);
        System.out.println(conflictSlots);
        System.out.println("Has time conflict ? " + scheduler.hasMovieConflicts());
        scheduler.removeMovie("Movie 2");
        System.out.println(conflictSlots);
        System.out.println("Has time conflict ? " + scheduler.hasMovieConflicts());
        System.out.println(scheduler.getMovieSlot("Movie 1"));
        System.out.println(scheduler.getMovieSlot("Movie 2"));
        System.out.println(scheduler.getMovieSlot("Movie 3"));
        System.out.println(scheduler.getMovieSlot("Movie 4"));
        System.out.println(scheduler.getMovieSlot("Movie 5"));
        scheduler.updateMovieSlot("Movie 3", slot5);
        System.out.println("Movie in this room : " + scheduler.getMoviesByRoom("1"));
        System.out.println("Movie in this room : " + scheduler.getMoviesByRoom("3"));
        System.out.println("This room is available ? " + scheduler.isRoomAvailable("3", "18h30", 120));
        System.out.println("This room is available ? " + scheduler.isRoomAvailable("2", "11h30", 120));
        System.out.println("List : " + conflictSlots);

        try {
            scheduler.addMovie(null, slot);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            scheduler.addMovie("Movie 6", null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            scheduler.removeMovie("Movie 6");
        } catch (IllegalArgumentException e) {
            System.out.println("Remove : " + e.getMessage());
        }

        try {
            scheduler.updateMovieSlot("Movie 6", slot);
        } catch (IllegalArgumentException e) {
            System.out.println("Update : " + e.getMessage());
        }

        try {
            scheduler.updateMovieSlot("Movie 1", null);
        } catch (IllegalArgumentException e) {
            System.out.println("Update : " + e.getMessage());
        }

        try {
            System.out.println("Titre null" + scheduler.getMovieSlot(null));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }




    }
}
