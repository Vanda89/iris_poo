package eu.iris.ms.model;

import java.util.ArrayList;
import java.util.List;

public class Session {

    private int idSession;
    private String name;
    private List<Slide> slides = new ArrayList<>();

    public Session(String name) {
        validateSessionParams(name);
        this.name = name.trim();
    }

    public Session(int idSession, String name) {
        this(name);
        this.idSession = idSession;
    }

    public void validateSessionParams(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Session name cannot be null or empty");
        }

        if (name.length() < 3) {
            throw new IllegalArgumentException("Session name must be at least 3 characters long");
        }

        if (name.length() > 50) {
            throw new IllegalArgumentException("Session name cannot exceed 50 characters");
        }

        if (!name.matches("^[\\p{L}0-9 _-]+$")) {
            throw new IllegalArgumentException("Session name contains invalid characters");
        }
    }

    public void addSlide(Slide slide) {
        if (slide == null) {
            throw new IllegalArgumentException("Slide cannot be null");
        }
        this.slides.add(slide);
    }

    public void removeSlide(Slide slide) {
        if (slide == null) {
            throw new IllegalArgumentException("Slide cannot be null");
        }
        this.slides.remove(slide);
    }

    public void removeSlideAt(int index) {
        if (index < 0 || index >= this.slides.size()) {
            throw new IndexOutOfBoundsException("Invalid slide index: " + index);
        }
        this.slides.remove(index);
    }

    public Slide getSlide(int index) {
        if (index < 0 || index >= this.slides.size()) {
            throw new IndexOutOfBoundsException("Invalid slide index: " + index);
        }
        return this.slides.get(index);
    }

    public int getId() {
        return this.idSession;
    }

    public String getName() {
        return this.name;
    }

    public List<Slide> getSlides() {
        return List.copyOf(this.slides);
    }

    public int getSlideCount() {
        return this.slides.size();
    }

    public void setName(String name) {
        validateSessionParams(name);

        this.name = name;
    }

    public void setId(int idSession) {
        this.idSession = idSession;
    }

    public boolean isEmpty()  {
        return getSlideCount() <= 0;
    }

    public void clear() {
        this.slides.clear();
    }

    public boolean contains(Slide slide) {
        for (Slide slideElement : this.slides) {
            if (slideElement == slide) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(Slide slide) {
        for (int i = 0; i < this.slides.size() ; i++) {
            if (this.slides.get(i) == slide) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Session{" +
                "name='" + name + '\'' +
                '}';
    }
}
