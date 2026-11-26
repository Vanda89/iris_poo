package eu.iris.ms.dao;

import eu.iris.ms.model.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionDAO {
    private final Connection connection;

    public SessionDAO(Connection connection) {
        this.connection = connection;
        initializeTable();
    }

    private void initializeTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS sessions (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL UNIQUE
            )
            """;

        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException exception) {
            throw new RuntimeException("Failed to create session table: " + exception.getMessage(), exception);
        }
    }

    public void insert(Session session) {
        String sql = "INSERT INTO sessions (name) VALUES (?)";

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, session.getName());
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    session.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save session: " + e.getMessage(), e);
        }
    }

    public Session findSessionByName(String sessionName) {
        String sql = """
                SELECT *
                FROM sessions
                WHERE name = ?
                """;

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.setString(1, sessionName);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Session(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the session, this name don't exist", e);
        }
    }

    public List<Session> findAllSessions() {
        List<Session> sessions = new ArrayList<>();
        String sql = "SELECT * FROM sessions ORDER BY id ASC";

        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Session session = new Session(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                sessions.add(session);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find sessions");
        }
        return sessions;
    }

    public void updateSession(int sessionId, String newName) {
        String sql = "UPDATE sessions SET name = ? WHERE id = ?";

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, sessionId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update session", e);
        }
    }

    public void deleteSession(int sessionId) {
        String sql = """
                DELETE
                FROM sessions
                WHERE id = ?
                """;

        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setInt(1, sessionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete session by session id", e);
        }
    }



}
