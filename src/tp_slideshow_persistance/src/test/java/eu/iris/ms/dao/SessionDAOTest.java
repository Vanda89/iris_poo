package eu.iris.ms.dao;

import eu.iris.ms.model.Session;
import eu.iris.ms.model.Slide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SessionDAOTest {
    private SessionDAO sessionDAO;
    private SlideDAO slideDAO;
    private Connection testConnection;

    @BeforeEach
    void setUp() throws SQLException {
        this.testConnection = DriverManager.getConnection("jdbc:sqlite::memory:");

        // Activer les FOREIGN KEY constraints (désactivées par défaut en SQLite)
        Statement stmt = this.testConnection.createStatement();
        stmt.execute("PRAGMA foreign_keys = ON");

        this.sessionDAO = new SessionDAO(this.testConnection);
        this.slideDAO = new SlideDAO(this.testConnection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (this.testConnection != null && !this.testConnection.isClosed()) {
            this.testConnection.close();
        }
    }

    @Test
    void shouldInsertSession() {
        Session session = new Session("Test Session");

        sessionDAO.insert(session);

        assertNotEquals(0, session.getId());
        String sql = "SELECT * FROM sessions WHERE id = ?";

        try (PreparedStatement pstmt = this.testConnection.prepareStatement(sql)) {
            pstmt.setInt(1, session.getId());

            ResultSet rs = pstmt.executeQuery();

            assertTrue(rs.next());
            assertEquals("Test Session", rs.getString("name"));
            assertEquals(session.getId(), rs.getInt("id"));

        } catch (SQLException e) {
            fail("Erreur SQL : " + e.getMessage());
        }

    }

    @Test
    void shouldFindSessionByName() {
        Session session = new Session("Session 1");

        this.sessionDAO.insert(session);

        Session foundSession = this.sessionDAO.findSessionByName("Session 1");

        assertNotNull(foundSession);
    }

    @Test
    void shouldReturnEmptyWhenSessionNotFound() {
        Session shadowSession = this.sessionDAO.findSessionByName("Session 1");

        assertNull(shadowSession);
    }

    @Test
    void shouldFindAllSessions() {
        Session session = new Session("Session 1");
        Session session2 = new Session("Session 2");
        Session session3 = new Session("Session 3");

        this.sessionDAO.insert(session);
        this.sessionDAO.insert(session2);
        this.sessionDAO.insert(session3);

        List<Session> sessions = this.sessionDAO.findAllSessions();

        assertEquals(3, sessions.size());
    }

    @Test
    void shouldUpdateExistingSession() {
        Session session = new Session("Session 1");

        this.sessionDAO.insert(session);

        session.setName("Session 1 bis");

        this.sessionDAO.updateSession(session.getId(), session.getName());

        Session updatedSession = this.sessionDAO.findSessionByName("Session 1 bis");

        assertEquals("Session 1 bis", updatedSession.getName());
    }

    @Test
    void shouldDeleteSessionAndCascadeSlides() {
        Session session = new Session("Session 1");
        Slide slide = new Slide("Introduction", "#FF0000", "intro.jpg");
        Slide slide2 = new Slide("Conclusion", "#FF0000", "conclusion.jpg");

        session.addSlide(slide);
        session.addSlide(slide2);

        this.sessionDAO.insert(session);

        this.sessionDAO.deleteSession(session.getId());

        assertNull(this.sessionDAO.findSessionByName("Session 1"));
        assertEquals(0, slideDAO.findBySessionId(session.getId()).size());
    }

    @Test
    void shouldSaveSessionWithSlides() {
        Session session = new Session("Session 1");
        Slide slide = new Slide("Introduction", "#FF0000", "intro.jpg");
        Slide slide2 = new Slide("Conclusion", "#FF0000", "conclusion.jpg");

        session.addSlide(slide);
        session.addSlide(slide2);

        this.sessionDAO.insert(session);
        this.slideDAO.save(slide2, session.getId());
        this.slideDAO.save(slide, session.getId());

        assertNotNull(this.sessionDAO.findSessionByName("Session 1"));
        assertEquals(2, slideDAO.findBySessionId(session.getId()).size());
    }
}
