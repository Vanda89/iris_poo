package eu.iris.ms;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MavenTest {
    @Test
    public void shouldVerifyMavenAndJUnitWork() {
        // Test simple pour vérifier que tout fonctionne
        String message = "Maven et JUnit fonctionnent !";
        assertEquals("Maven et JUnit fonctionnent !", message);
        assertTrue(true);
    }
}