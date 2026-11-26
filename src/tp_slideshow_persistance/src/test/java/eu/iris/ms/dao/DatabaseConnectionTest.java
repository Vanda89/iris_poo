package eu.iris.ms.dao;

import java.sql.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionTest {
    @Test
    void shouldConnectToInMemoryDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite::memory:")) {
            assertNotNull(connection);
            assertTrue(connection.isValid(1));
        } catch (SQLException e) {
            fail("Connection failed: " + e.getMessage());
        }
    }
}