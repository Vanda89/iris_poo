package tp_1;


// Enum pour représenter les priorités des tâches
// Un enum définit un ensemble fini de constantes

public enum Priority {
    HIGH("Haute"),
    MEDIUM("Moyenne"),
    LOW("Basse");

    private final String label;

    Priority(String label){
        this.label = label;
    }

    // Méthode toString() personnalisée pour l'affichage
    @Override
    public String toString() {
        return this.label;
    }

    // Méthode utilitaire pour créer un enum depuis un String
    public static Priority fromString(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Priority cannot be null");
        }

        for (Priority p : Priority.values()) {
            if (p.name().equalsIgnoreCase(text)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Priority " + text + " not found");
    }
}
