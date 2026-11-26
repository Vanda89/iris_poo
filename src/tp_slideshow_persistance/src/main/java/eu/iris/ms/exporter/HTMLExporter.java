package eu.iris.ms.exporter;

import eu.iris.ms.dao.SessionDAO;
import eu.iris.ms.dao.SlideDAO;
import eu.iris.ms.model.Session;
import eu.iris.ms.model.Slide;
import java.io.*;
import java.nio.file.*;
import java.util.List;

/**
 * Exporte une session en fichier HTML visualisable dans un navigateur.
 * Utilise un template HTML séparé pour la présentation.
 * 
 * ⚠️ IMPORTANT - Chemins d'images :
 * Les chemins d'images dans les Slide doivent être RELATIFS au fichier HTML généré.
 * 
 * Exemple :
 *   ✅ new Slide("Intro", "#FF0000", "intro.jpg")              // chemin relatif
 *   ❌ new Slide("Intro", "#FF0000", "C:\\Photos\\intro.jpg")  // chemin absolu
 * 
 * Organisation des fichiers pour que ça fonctionne :
 *   presentation.html  ← fichier HTML généré
 *   intro.jpg          ← images au même niveau
 *   slide1.png
 */

public class HTMLExporter {
    
    private static final String TEMPLATE_PATH = "slide_template.html"; //le fichier slide_template.html doit être dans les ressources du projet !
    
    /**
     * Exporte une session complète en fichier HTML.
     * @param session La session à exporter
     * @param outputPath Le chemin du fichier de sortie (ex: "presentation.html")
     */
    public void export(Session session, String outputPath) {
        // TODO : Implémenter
        // 
        // 1. Charger le template avec loadTemplate()
        String template = loadTemplate();
        // 2. Remplacer {{SESSION_NAME}} par session.getName() (à l'aide de .replace())
        template = template.replace("{{SESSION_NAME}}", session.getName());
        // 3. Générer le HTML de tous les slides à l'aide de buildSlideHTML() et les concaténer
        List<Slide> slides = session.getSlides();
        StringBuilder slidesContent = new StringBuilder();

        for (Slide slide : slides) {
            slidesContent.append(buildSlideHTML(slide));
        }
        // 4. Remplacer {{SLIDES_CONTENT}} par ce HTML concaténé
        template = template.replace(
                "{{SLIDES_CONTENT}}",
                slidesContent.toString()
        );
        // 5. Écrire le résultat dans outputPath avec writeToFile()
        writeToFile(template, outputPath);
    }
    
    /**
     * Écrit le contenu HTML dans un fichier.
     * @param htmlFileContent Le fichier HTML à écrire
     * @param outputPath Le chemin du fichier de sortie
     */
    private void writeToFile(String htmlFileContent, String outputPath) {
        try (FileWriter writer = new FileWriter(outputPath)) {
            writer.write(htmlFileContent);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write file: " + outputPath, e);
        }
    }
    
    /**
     * Génère le HTML pour une slide individuelle.
     * @param slide Le slide à convertir en HTML
     * @return Une chaîne HTML représentant le slide
     */
    private String buildSlideHTML(Slide slide) {
        // TODO : À compléter
        // 
        // Structure suggérée :
        // <div class="slide" style="background-image: url('...')">
        //     <h2 style="color: ...">Titre</h2>
        // </div>
        //
        // Le CSS et le JavaScript du template gèrent l'affichage et la navigation
        // Vous n'avez qu'à générer les divs avec les bonnes infos dans un String

        return """
                <div class="slide" style="background-image: url('%s')">
                    <h2 style="color: %s">%s</h2>
                </div>
                """.formatted(
                slide.getImagePath(),
                slide.getTitleColor(),
                slide.getTitle()
        );
    }
    
    /**
     * Charge le template HTML depuis le fichier.
     * @return Le contenu du template comme String
     */
    private String loadTemplate() {
        try {
            // Chercher d'abord à la racine du projet
            Path templatePath = Paths.get(TEMPLATE_PATH);
            if (Files.exists(templatePath)) {
                return Files.readString(templatePath);
            }
            
            // Sinon chercher dans les resources
            InputStream stream = getClass().getClassLoader().getResourceAsStream(TEMPLATE_PATH);
            if (stream != null) {
                return new String(stream.readAllBytes());
            }
            
            throw new FileNotFoundException("Template not found: " + TEMPLATE_PATH);
            
        } catch (IOException e) {
            throw new RuntimeException("Failed to load template", e);
        }
    }
}