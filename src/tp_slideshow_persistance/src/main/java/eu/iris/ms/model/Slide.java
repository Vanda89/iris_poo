package eu.iris.ms.model;

import java.util.Locale;

public class Slide {

    private int idSlide;
    private String title;
    private String titleColor;
    private String imagePath;

    public Slide(String title, String titleColor, String imagePath) {
        this(0, title, titleColor, imagePath);
    }

    public Slide(int idSlide, String title, String titleColor, String imagePath) {

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (title.trim().length() < 3) {
            throw new IllegalArgumentException("Title must be at least 3 characters long");
        }

        if (title.trim().length() > 100) {
            throw new IllegalArgumentException("Title cannot exceed 100 characters");
        }

        if (titleColor == null || titleColor.isBlank()) {
            throw new IllegalArgumentException("Color cannot be null or empty");
        }

        if (titleColor.length() != 7 || !titleColor.matches("^#[0-9A-Fa-f]{6}$")) {
            throw new IllegalArgumentException("Invalid color format. Expected #RRGGBB (e.g., #FF0000)");
        }

        if (imagePath == null || imagePath.isBlank()) {
            throw new IllegalArgumentException("Image path cannot be null or empty");
        }

        if (imagePath.length() < 5) {
            throw new IllegalArgumentException("Image path is too short");
        }

        String lowerImagePath = imagePath.toLowerCase(Locale.ROOT);

        if (!lowerImagePath.endsWith(".jpg")
                && !lowerImagePath.endsWith(".jpeg")
                && !lowerImagePath.endsWith(".png")
                && !lowerImagePath.endsWith(".gif")) {
            throw new IllegalArgumentException(
                    "Image path must end with .jpg, .jpeg, .png, or .gif"
            );
        }


        this.idSlide = idSlide;
        this.title = title.trim();
        this.titleColor = titleColor;
        this.imagePath = imagePath.trim();
    }

    public int getId() {
        return idSlide;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setId(int idSlide) {
        this.idSlide = idSlide;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (title.trim().length() < 3) {
            throw new IllegalArgumentException("Title must be at least 3 characters long");
        }

        if (title.trim().length() > 100) {
            throw new IllegalArgumentException("Title cannot exceed 100 characters");
        }

        this.title = title.trim();
    }

    public void setTitleColor(String titleColor) {
        if (titleColor == null || titleColor.isBlank()) {
            throw new IllegalArgumentException("Color cannot be null or empty");
        }

        if (titleColor.length() != 7 || !titleColor.matches("^#[0-9A-Fa-f]{6}$")) {
            throw new IllegalArgumentException("Invalid color format. Expected #RRGGBB (e.g., #FF0000)");
        }

        this.titleColor = titleColor;
    }

    public void setImagePath(String imagePath) {
        if (imagePath == null || imagePath.isBlank()) {
            throw new IllegalArgumentException("Image path cannot be null or empty");
        }

        if (imagePath.length() < 5) {
            throw new IllegalArgumentException("Image path is too short");
        }

        String lowerImagePath = imagePath.toLowerCase(Locale.ROOT);

        if (!lowerImagePath.endsWith(".jpg")
                && !lowerImagePath.endsWith(".jpeg")
                && !lowerImagePath.endsWith(".png")
                && !lowerImagePath.endsWith(".gif")) {
            throw new IllegalArgumentException(
                    "Image path must end with .jpg, .jpeg, .png, or .gif"
            );
        }

        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Slide{" +
                "title='" + title + '\'' +
                ", titleColor='" + titleColor + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
