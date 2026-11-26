package eu.iris.ms.dao;

import eu.iris.ms.model.Slide;
import java.sql.*;
import java.util.ArrayList;

public class SlideDAO {
    private final Connection connection;

    public SlideDAO(Connection connection) {
        this.connection = connection;
        initializeTable();
    }

    private void initializeTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS slides (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                title_color TEXT NOT NULL,
                image_path TEXT NOT NULL,
                position INTEGER NOT NULL,
                session_id INTEGER NOT NULL,
                FOREIGN KEY (session_id) REFERENCES sessions(id) ON DELETE CASCADE
            )
        """;

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create slides table: " + e.getMessage(), e);
        }
    }

    private int getNextPosition(int sessionId) {
        String sql = "SELECT COALESCE(MAX(position), -1) + 1 FROM slides WHERE session_id = ?";

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.setInt(1, sessionId);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to calculate next position", e);
        }
    }

    public void save(Slide slide, int sessionId) {
        int nextPosition = getNextPosition(sessionId);
        String sql = "INSERT INTO slides (title, title_color, image_path, position, session_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, slide.getTitle());
            pstmt.setString(2, slide.getTitleColor());
            pstmt.setString(3, slide.getImagePath());
            pstmt.setInt(4, nextPosition);
            pstmt.setInt(5, sessionId);
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    slide.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save slide: " + e.getMessage(), e);
        }
    }

    public ArrayList<Slide> findBySessionId(int sessionId) {
        ArrayList<Slide> slides = new ArrayList<>();

        // TODO: À implémenter (exercice 4.4)
        String sql = """
                SELECT *
                FROM slides
                WHERE session_id = ?
                ORDER BY position ASC
                """;
        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.setInt(1, sessionId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Slide slide = new Slide(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("title_color"),
                    rs.getString("image_path")
                );
                slides.add(slide);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find by session id", e);
        }
        return slides;
    }

    public void deleteBySessionId(int sessionId) {
        // TODO: À implémenter (exercice 4.4)
        String sql = """
                DELETE
                FROM slides
                WHERE session_id = ?
                """;
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setInt(1, sessionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete slides by session id", e);
        }

    }
}