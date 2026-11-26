package eu.iris.ms.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SlideTest {

    private Slide slide;
    private final int ID_SLIDE = 0;
    private final String TITLE = "title";
    private final String COLOR_TITLE = "#FF0000";
    private final String IMAGE_PATH = "image.png";


    @BeforeEach
    void setUp() {
        slide = new Slide(ID_SLIDE, TITLE, COLOR_TITLE, IMAGE_PATH);
    }

    @Test
    void shouldCreateSlideWithDefaultId() {
        Slide slide = new Slide(TITLE, COLOR_TITLE, IMAGE_PATH);
        assertEquals(0, slide.getId());
        assertEquals(TITLE, slide.getTitle());
        assertEquals(COLOR_TITLE, slide.getTitleColor());
        assertEquals(IMAGE_PATH, slide.getImagePath());
    }

    @Test
    void shouldSetSlideId() {
        Slide slide = new Slide(TITLE, COLOR_TITLE, IMAGE_PATH);

        slide.setId(1);

        assertEquals(1, slide.getId());
    }

    @Test
    void shouldAcceptValidSlideTitle() {
        assertEquals("title", slide.getTitle());
    }

    @Test
    void shouldAcceptSlideTitleWithExactlyThreeCharacters() {
        Slide slide = new Slide(ID_SLIDE, "abc", COLOR_TITLE, IMAGE_PATH);

        assertEquals("abc", slide.getTitle());
    }

    @Test
    void shouldAcceptSlideTitleWithExactly100Characters() {
        String title = "a".repeat(100);

        Slide slide = new Slide(ID_SLIDE, title, COLOR_TITLE, IMAGE_PATH);

        assertEquals(title, slide.getTitle());
    }


    @Test
    void shouldTrimTitle() {
        Slide slide = new Slide("  Chapitre 1  ", COLOR_TITLE, IMAGE_PATH);

        assertEquals("Chapitre 1", slide.getTitle());
    }

    @Test
    void shouldUpdateSlideTitle() {
        Slide slide = new Slide(TITLE, COLOR_TITLE, IMAGE_PATH);

        slide.setTitle("New title");

        assertEquals("New title", slide.getTitle());
    }


    @Test
    void shouldRejectNullSlideTitle() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(ID_SLIDE, null, COLOR_TITLE, IMAGE_PATH)
        );
        assertEquals("Title cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectEmptySlideTitle() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(ID_SLIDE, " ", COLOR_TITLE, IMAGE_PATH)
        );
        assertEquals("Title cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectInvalidTitleWhenUpdatingSlide() {
        assertThrows(
                IllegalArgumentException.class,
                () -> slide.setTitle("ab")
        );
    }

    @Test
    void shouldRejectSlideTitleWithLessThanThreeCharacters() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(ID_SLIDE, "ti", COLOR_TITLE, IMAGE_PATH)
        );
        assertEquals("Title must be at least 3 characters long", exception.getMessage());
    }

    @Test
    void shouldRejectSlideTitleWithMoreThan100Characters() {
        String title = "a".repeat(101);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(ID_SLIDE, title, COLOR_TITLE, IMAGE_PATH)
        );
        assertEquals("Title cannot exceed 100 characters", exception.getMessage());
    }

    @Test
    void shouldAcceptValidSlideColor() {
        assertEquals("#FF0000", slide.getTitleColor());
    }

    @Test
    void shouldUpdateSlideTitleColor() {
        Slide slide = new Slide(TITLE, COLOR_TITLE, IMAGE_PATH);

        slide.setTitleColor("#00FF00");

        assertEquals("#00FF00", slide.getTitleColor());
    }

    @Test
    void shouldAcceptLowercaseHexadecimalColor() {
        Slide lowercaseSlide = new Slide(ID_SLIDE, TITLE, "#ffffff", IMAGE_PATH);
        assertEquals("#ffffff", lowercaseSlide.getTitleColor());
    }

    @Test
    void shouldRejectNullSlideColor() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(ID_SLIDE, TITLE, null, IMAGE_PATH)
        );
        assertEquals("Color cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectEmptySlideColor() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(ID_SLIDE, TITLE, " ", IMAGE_PATH)
        );
        assertEquals("Color cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectInvalidColorWhenUpdatingSlide() {
        assertThrows(
                IllegalArgumentException.class,
                () -> slide.setTitleColor("red")
        );
    }

    @Test
    void shouldRejectColorWithLessThanSevenHexadecimalCharacters() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(ID_SLIDE, TITLE, "#FF00", IMAGE_PATH)
        );
        assertEquals("Invalid color format. Expected #RRGGBB (e.g., #FF0000)", exception.getMessage());
    }

    @Test
    void shouldRejectColorWithMoreThanSevenHexadecimalCharacters() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(ID_SLIDE, TITLE, "#FF00000", IMAGE_PATH)
        );
        assertEquals("Invalid color format. Expected #RRGGBB (e.g., #FF0000)", exception.getMessage());
    }

    @Test
    void shouldRejectColorWithInvalidHexadecimalCharacters() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(ID_SLIDE, TITLE, "#rouge", IMAGE_PATH)
        );
        assertEquals("Invalid color format. Expected #RRGGBB (e.g., #FF0000)", exception.getMessage());
    }

    @Test
    void shouldAcceptImagePathWithUppercaseExtension() {
        Slide slide = new Slide(ID_SLIDE, TITLE, COLOR_TITLE, "image.PNG");

        assertEquals("image.PNG", slide.getImagePath());
    }

    @Test
    void shouldAcceptImagePathWithWindowsPath() {
        Slide slide = new Slide(ID_SLIDE, TITLE, COLOR_TITLE, "C:\\images\\slide1.PNG");

        assertEquals("C:\\images\\slide1.PNG", slide.getImagePath());
    }

    @Test
    void shouldAcceptValidImageExtensions() {
        assertEquals("/images/image.jpg",
                new Slide(ID_SLIDE, TITLE, COLOR_TITLE, "/images/image.jpg").getImagePath());

        assertEquals("/images/image.jpeg",
                new Slide(ID_SLIDE, TITLE, COLOR_TITLE, "/images/image.jpeg").getImagePath());

        assertEquals("/images/image.png",
                new Slide(ID_SLIDE, TITLE, COLOR_TITLE, "/images/image.png").getImagePath());

        assertEquals("/images/image.gif",
                new Slide(ID_SLIDE, TITLE, COLOR_TITLE, "/images/image.gif").getImagePath());
    }

    @Test
    void shouldAcceptImagePathWithMinimumLength() {
        Slide slide = new Slide(ID_SLIDE, TITLE, COLOR_TITLE, "i.jpg");

        assertEquals("i.jpg", slide.getImagePath());
    }

    @Test
    void shouldUpdateSlideImagePath() {
        Slide slide = new Slide(TITLE, COLOR_TITLE, IMAGE_PATH);

        slide.setImagePath("/images/new-image.png");

        assertEquals("/images/new-image.png", slide.getImagePath());
    }

    @Test
    void shouldRejectNullImagePath() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(ID_SLIDE, TITLE, COLOR_TITLE, null)
        );
        assertEquals("Image path cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectEmptyImagePath() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(ID_SLIDE, TITLE, COLOR_TITLE, " ")
        );
        assertEquals("Image path cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectInvalidImagePathWhenUpdatingSlide() {
        assertThrows(
                IllegalArgumentException.class,
                () -> slide.setImagePath("image.txt")
        );
    }

    @Test
    void shouldRejectImagePathShorterThanFiveCharacters() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(ID_SLIDE, TITLE, COLOR_TITLE, "a.jp")
        );
        assertEquals("Image path is too short", exception.getMessage());
    }

    @Test
    void shouldRejectImagePathWithoutExtension() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(ID_SLIDE, TITLE, COLOR_TITLE, "image")
        );
        assertEquals("Image path must end with .jpg, .jpeg, .png, or .gif", exception.getMessage());
    }

    @Test
    void shouldRejectImagePathWithInvalidExtension() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(ID_SLIDE, TITLE, COLOR_TITLE, "image.txt")
        );
        assertEquals("Image path must end with .jpg, .jpeg, .png, or .gif", exception.getMessage());
    }

    @Test
    void shouldDisplaySlideCorrectly() {
        Slide slide = new Slide("Chapitre 1", "#8B0000", "/colors/darkred.png");

        assertEquals(
                "Slide{title='Chapitre 1', titleColor='#8B0000', imagePath='/colors/darkred.png'}",
                slide.toString()
        );
    }

    @Test
    void shouldUpdateAllSlideInformations() {
        Slide slide2 = new Slide(TITLE, COLOR_TITLE, IMAGE_PATH);
        slide2.setTitle("Chapitre 1");
        slide2.setTitleColor("#8B0000");
        slide2.setImagePath("/colors/darkred.png");

        assertEquals("Chapitre 1", slide2.getTitle());
        assertEquals("#8B0000", slide2.getTitleColor());
        assertEquals("/colors/darkred.png", slide2.getImagePath());
    }

}
