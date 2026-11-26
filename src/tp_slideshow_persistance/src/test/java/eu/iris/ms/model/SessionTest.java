package eu.iris.ms.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SessionTest {

    private Session session;
    private Slide slide;
    private final int ID_SESSION = 0;
    private final String NAME_SESSION = "Session";
    private List<Slide> slides = new ArrayList<>();

    @BeforeEach
    void setUp() {
        session = new Session(NAME_SESSION);
        slide = new Slide("Slide 1", "#0000FF", "/images/slide_1.jpg");

    }

    @Test
    void shouldCreateSessionWithDefaultId() {
        assertEquals(0, session.getId());
        assertEquals(NAME_SESSION, session.getName());
    }

    @Test
    void shouldCreateSessionWithGivenId() {
        Session session2 = new Session(1, "Session 2");
        assertEquals(1, session2.getId());
        assertEquals("Session 2", session2.getName());
    }

    @Test
    void shouldCreateSessionWithGivenIdAndEmptySlides() {
        Session session2 = new Session(1, "Session 2");
        assertEquals(1, session2.getId());
        assertEquals("Session 2", session2.getName());
        assertTrue(session2.getSlides().isEmpty());
        assertEquals(0, session2.getSlideCount());
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "Ma Présentation",
            "Project_2024",
            "Formation-Java",
            "TP 4"
    })
    void shouldAcceptSessionNameWithAllowedCharacters(String name) {
        Session session = new Session(name);
        assertEquals(name, session.getName());
    }

    @Test
    void shouldTrimSessionName() {
        Session session = new Session(" Session 2 ");
        assertEquals("Session 2", session.getName());
    }

    @Test
    void shouldUpdateSessionName() {
        session.setName("Session 1");
        assertEquals("Session 1", session.getName());
    }

    @Test
    void shouldRejectNullSessionName() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Session(null)
        );
        assertEquals("Session name cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectNullWhenUpdatingSessionName() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> session.setName(null)
        );
        assertEquals("Session name cannot be null or empty", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void shouldRejectEmptySessionName(String name) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Session(name)
        );
        assertEquals("Session name cannot be null or empty", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    void shouldRejectEmptyNameWhenUpdatingSessionName(String name) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> session.setName(name)
        );
        assertEquals("Session name cannot be null or empty", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hi", "A"})
    void shouldRejectSessionNameTooShort(String name) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Session(name)
        );
        assertEquals("Session name must be at least 3 characters long", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hi", "A"})
    void shouldRejectTooShortNameWhenUpdatingSessionName(String name) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> session.setName(name)
        );
        assertEquals("Session name must be at least 3 characters long", exception.getMessage());
    }

    @Test
    void shouldRejectSessionNameTooLong() {
        String name = "A".repeat(51);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Session(name)
        );
        assertEquals("Session name cannot exceed 50 characters", exception.getMessage());
    }

    @Test
    void shouldRejectTooLongNameWhenUpdatingSessionName() {
        String name = "A".repeat(51);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> session.setName(name)
        );
        assertEquals("Session name cannot exceed 50 characters", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "My$Session",
            "Test@Home",
            "Test!",
            "My/Session",
            "Test#Session"
    })
    void shouldRejectSessionNameWithInvalidCharacters(String name) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Session(name)
        );
        assertEquals("Session name contains invalid characters", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "My$Session",
            "Test@Home",
            "Test!",
            "My/Session",
            "Test#Session"
    })
    void shouldRejectInvalidCharactersWhenUpdatingSessionName(String name) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> session.setName(name)
        );
        assertEquals("Session name contains invalid characters", exception.getMessage());
    }

    @Test
    void shouldReturnEmptySlidesWhenSessionIsCreated() {
        assertTrue(session.getSlides().isEmpty());    }

    @Test
    void shouldReturnZeroSlidesCountWhenSessionIsCreated() {
        assertEquals(0, session.getSlideCount());    }

    @Test
    void shouldAddSlide() {
        session.addSlide(slide);
        assertEquals(1, session.getSlideCount());
        assertEquals(slide, session.getSlides().get(0));
    }

    @Test
    void shouldAddMultipleSlidesInOrder() {
        Slide slide2 = new Slide("Slide 2", "#0000FF", "/images/slide_2.jpg");
        Slide slide3 = new Slide("Slide 3", "#0000FF", "/images/slide_3.jpg");
        session.addSlide(slide);
        session.addSlide(slide2);
        session.addSlide(slide3);

        assertEquals(3, session.getSlideCount());
        assertEquals(slide, session.getSlides().get(0));
        assertEquals(slide2, session.getSlides().get(1));
        assertEquals(slide3, session.getSlides().get(2));

    }

    @Test
    void shouldAllowDuplicatesSlides() {
        session.addSlide(slide);
        session.addSlide(slide);

        assertEquals(2, session.getSlideCount());
        assertEquals(slide, session.getSlides().get(0));
        assertEquals(slide, session.getSlides().get(1));
    }

    @Test
    void shouldRejectNullSlideWhenAdding() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                ()-> session.addSlide(null)
        );
        assertEquals("Slide cannot be null", exception.getMessage());
    }

    @Test
    void shouldRemoveSlide() {
        Slide slide2 = new Slide("Slide 2", "#0000FF", "/images/slide_2.jpg");

        session.addSlide(slide);
        session.addSlide(slide2);

        session.removeSlide(slide2);

        assertEquals(1, session.getSlideCount());
        assertEquals(slide, session.getSlides().get(0));
    }

    @Test
    void shouldRemoveOnlyFirstOccurrenceOfSlideList() {
        session.addSlide(slide);
        session.addSlide(slide);
        session.removeSlide(slide);

        assertEquals(slide, session.getSlides().get(0));
    }

    @Test
    void shouldDoNothingWhenRemovingAbsentSlide() {
        Slide slide2 = new Slide("Slide 2", "#0000FF", "/images/slide_2.jpg");

        session.addSlide(slide);
        session.removeSlide(slide2);

        assertEquals(1, session.getSlideCount());
        assertEquals(slide, session.getSlides().get(0));
    }

    @Test
    void shouldRejectNullSlideWhenRemoving() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                ()-> session.removeSlide(null)
        );

        assertEquals("Slide cannot be null", exception.getMessage());
    }

    @Test
    void shouldRemoveSlideAtIndex() {
        Slide slide2 = new Slide("Slide 2", "#0000FF", "/images/slide_2.jpg");

        session.addSlide(slide);
        session.addSlide(slide2);
        session.removeSlideAt(0);

        assertEquals(1, session.getSlideCount());
        assertEquals(slide2, session.getSlides().get(0));
    }

    @Test
    void shouldRejectInvalidIndexWhenRemovingSlide() {
        IndexOutOfBoundsException exception = assertThrows(
                IndexOutOfBoundsException.class,
                ()-> session.removeSlideAt(-1)
        );

        assertEquals("Invalid slide index: -1", exception.getMessage());
    }

    @Test
    void shouldReturnsSlideAtIndex() {
        session.addSlide(slide);

        assertEquals(slide, session.getSlide(0));
    }

    @Test
    void shouldRejectInvalidIndexWhenGettingSlide() {
        IndexOutOfBoundsException exception = assertThrows(
                IndexOutOfBoundsException.class,
                ()-> session.getSlide(-1)
        );

        assertEquals("Invalid slide index: -1", exception.getMessage());
    }

    @Test
    void shouldReturnDefensiveCopyOfSlides() {
        session.addSlide(slide);

        List<Slide> slides2 = session.getSlides();
        slides.clear();

        assertEquals(1, session.getSlideCount());
        assertEquals(slide, session.getSlides().get(0));
    }

    @Test
    void shouldReturnTrueWhenSessionIsEmpty() {
        assertTrue(session.isEmpty());
    }

    @Test
    void shouldReturnFalseWhenSessionIsNotEmpty() {
        session.addSlide(slide);
        assertFalse(session.isEmpty());
    }

    @Test
    void shouldClearAllSlides() {
        Slide slide2 = new Slide("Slide 2", "#0000FF", "/images/slide_2.jpg");

        session.addSlide(slide);
        session.addSlide(slide2);
        assertFalse(session.isEmpty());

        session.clear();
        assertEquals(0, session.getSlideCount());
        assertTrue(session.isEmpty());
    }

    @Test
    void shouldReturnTrueWhenSessionContainsSlide() {
        session.addSlide(slide);

        assertTrue(session.contains(slide));
    }

    @Test
    void shouldReturnFalseWhenSessionDoesNotContainsSpecificSlide() {
        assertFalse(session.contains(slide));
    }

    @Test
    void shouldReturnsSlideIndex() {
        session.addSlide(slide);
        session.indexOf(slide);

        assertEquals(0, session.indexOf(slide));
    }

    @Test
    void shouldReturnsFirstOccurrenceDuplicateSlide() {
        session.addSlide(slide);
        session.addSlide(slide);
        session.addSlide(slide);


        assertEquals(0, session.indexOf(slide));

    }

    @Test
    void shouldReturnMinusOneWhenSlideIsAbsent() {
        assertEquals(-1, session.indexOf(slide));
    }



}
