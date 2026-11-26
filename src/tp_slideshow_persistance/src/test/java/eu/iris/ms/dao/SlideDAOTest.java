package eu.iris.ms.dao;

import eu.iris.ms.model.Session;
import eu.iris.ms.model.Slide;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SlideDAOTest {

    private SlideDAO slideDAO;
    private Connection testConnection;
    private static final int TEST_SESSION_ID = 1;

    @BeforeEach
    void setUp() throws SQLException {
        this.testConnection = DriverManager.getConnection("jdbc:sqlite::memory:");

        // Activer les FOREIGN KEY constraints (désactivées par défaut en SQLite)
        Statement stmt = this.testConnection.createStatement();
        stmt.execute("PRAGMA foreign_keys = ON");

        // TODO (exercice 4.3)
        SessionDAO sessionDAO = new SessionDAO(this.testConnection);
        Session session = new Session(1, "Test session");
        sessionDAO.insert(session);

        this.slideDAO = new SlideDAO(this.testConnection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (this.testConnection != null && !this.testConnection.isClosed()) {
            this.testConnection.close();
        }
    }

    @Test
    void shouldSaveSlideWithPosition() {
        Slide slide = new Slide("Introduction", "#FF0000", "intro.jpg");

        this.slideDAO.save(slide, this.TEST_SESSION_ID);

        assertNotEquals(0, slide.getId());

        try (Statement stmt = this.testConnection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM slides WHERE id = " + slide.getId())) {

            assertTrue(rs.next());
            assertEquals("Introduction", rs.getString("title"));
            assertEquals(0, rs.getInt("position"));
            assertEquals(this.TEST_SESSION_ID, rs.getInt("session_id"));
        } catch (SQLException e) {
            fail("Erreur SQL : " + e.getMessage());
        }
    }

    // TODO (exercice 4.4): Écrire les autres tests en TDD
    @Test
    void shouldFindSlidesBySessionId() {
        Slide slide = new Slide("Introduction", "#FF0000", "intro.jpg");
        Slide slide2 = new Slide("Conclusion", "#FF0000", "conclusion.jpg");

        this.slideDAO.save(slide, TEST_SESSION_ID);
        this.slideDAO.save(slide2, TEST_SESSION_ID);

        List<Slide> slides = this.slideDAO.findBySessionId(TEST_SESSION_ID);

        assertEquals(2, slides.size());


    }

    @Test
    void shouldReturnSlidesInOrder() {
        Slide slide = new Slide("Introduction", "#FF0000", "intro.jpg");
        Slide slide2 = new Slide("Conclusion", "#FF0000", "conclusion.jpg");


        this.slideDAO.save(slide, TEST_SESSION_ID);
        this.slideDAO.save(slide2, TEST_SESSION_ID);

        List<Slide> slides = this.slideDAO.findBySessionId(TEST_SESSION_ID);

        assertEquals("Introduction", slides.get(0).getTitle());
        assertEquals("#FF0000", slides.get(0).getTitleColor());
        assertEquals("intro.jpg", slides.get(0).getImagePath());

        assertEquals("Conclusion", slides.get(1).getTitle());
        assertEquals("#FF0000", slides.get(1).getTitleColor());
        assertEquals("conclusion.jpg", slides.get(1).getImagePath());

    }

    @Test
    void shouldDeleteSlidesBySessionId() {
        Slide slide = new Slide("Introduction", "#FF0000", "intro.jpg");
        Slide slide2 = new Slide("Conclusion", "#FF0000", "conclusion.jpg");

        this.slideDAO.save(slide, TEST_SESSION_ID);
        this.slideDAO.save(slide2, TEST_SESSION_ID);

        this.slideDAO.deleteBySessionId(TEST_SESSION_ID);

        List<Slide> slides = this.slideDAO.findBySessionId(TEST_SESSION_ID);

        assertEquals(0, slides.size());
    }
    @Test
    void shouldReturnEmptyListForNonExistentSession() {
        List<Slide> slides = this.slideDAO.findBySessionId(999);

        assertEquals(0, slides.size());
    }
}