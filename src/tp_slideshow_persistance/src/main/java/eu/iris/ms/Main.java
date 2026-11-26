package eu.iris.ms;
import eu.iris.ms.dao.SessionDAO;
import eu.iris.ms.dao.SlideDAO;
import eu.iris.ms.exporter.HTMLExporter;
import eu.iris.ms.model.Session;
import eu.iris.ms.model.Slide;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:slideshow.db")) {

            SessionDAO sessionDAO = new SessionDAO(connection);
            SlideDAO slideDAO = new SlideDAO(connection);

            Scanner scanner = new Scanner(System.in);
            Session currentSession = null;

            while (true) {
                System.out.println("\n=== SLIDESHOW CREATOR ===");
                System.out.println("1. Creer nouvelle session");
                System.out.println("2. Charger session");
                System.out.println("3. Lister sessions");

                if (currentSession != null) {
                    System.out.println("--- Session : " + currentSession.getName() + " ---");
                    System.out.println("4. Ajouter slide");
                    System.out.println("5. Afficher slides");
                    System.out.println("6. Sauvegarder");
                    System.out.println("7. Fermer session");
                    System.out.println("8. Exporter la session");
                }

                System.out.println("0. Quitter");
                System.out.print("Votre choix : ");

                String choice = scanner.nextLine();

                if (choice.isBlank()) {
                    System.err.println("Erreur : le choix ne peut pas etre vide");
                    continue;
                }

                switch (choice) {
                    case "1":
                        System.out.println("Creer une nouvelle session");
                        System.out.print("Nom de la session : ");
                        String name = scanner.nextLine();
                        try {
                            currentSession = new Session(name);
                            System.out.println("Session créée");
                        } catch (IllegalArgumentException e) {
                            System.err.println("Erreur : " + e.getMessage());
                        }
                        break;

                    case "2":
                        System.out.println("Charger une session");
                        List<Session> sessions = sessionDAO.findAllSessions();

                        if (sessions.isEmpty()) {
                            System.err.println("Erreur : aucune session n'existe");
                        } else {
                            System.out.println("Quelle session voulait vous charger ?");
                            name = scanner.nextLine();

                            for (Session session : sessions) {
                                if (name.equals(session.getName())) {
                                    currentSession = session;
                                    break;
                                }
                            }

                            if (currentSession != null) {
                                int sessionId = currentSession.getId();

                                List<Slide> slides = slideDAO.findBySessionId(sessionId);

                                for (Slide slide : slides) {
                                    currentSession.addSlide(slide);
                                }

                                System.out.println("Session chargée avec " + slides.size() + " slide(s)");
                            } else {
                                System.err.println("Session introuvable");
                            }
                        }
                        break;

                    case "3":
                        System.out.println("Lister les sessions");
                        sessions = sessionDAO.findAllSessions();
                        if (sessions.isEmpty()) {
                            System.err.println("Erreur : aucune session n'existe");
                        } else {
                            for (Session session : sessions) {
                                System.out.println(session);
                            }
                        }
                        break;
                    
                    case "4":
                        if (currentSession == null) {
                            System.out.println("Erreur : aucune session ouverte.");
                            break;
                        }
                        System.out.println("Ajouter une slide");

                        System.out.print("Titre : ");
                        String title = scanner.nextLine();

                        System.out.print("Couleur : ");
                        String color = scanner.nextLine();

                        System.out.print("Chemin de l'image : ");
                        String imagePath = scanner.nextLine();

                        try {
                            Slide slide = new Slide(title, color, imagePath);
                            currentSession.addSlide(slide);
                            System.out.println("Slide ajoutée");
                        } catch (IllegalArgumentException e) {
                            System.err.println("Erreur lors de la création de la slide : " + e.getMessage());
                        }
                        break;
                    case "5":
                        System.out.println("Afficher slides");

                        if (currentSession == null) {
                            System.out.println("Erreur : aucune session ouverte.");
                            break;
                        }
                        List<Slide> slides = currentSession.getSlides();
                        if (slides.isEmpty()) {
                            System.err.println("Erreur : aucune slide n'existe");
                        } else {
                            for (Slide slide : slides) {
                                System.out.println(slide);
                            }
                        }
                        break;
                    case "6":
                        System.out.println("Sauvegarder session");

                        if (currentSession == null) {
                            System.out.println("Erreur : aucune session ouverte.");
                            break;
                        }

                        int sessionId = currentSession.getId();

                        if (sessionId == 0) {
                            sessionDAO.insert(currentSession);
                            System.out.println("Session sauvegardée");
                        } else {
                            System.out.println("Session déjà sauvegardée (ID : " + sessionId + ")");
                        }

                        slides = currentSession.getSlides();

                        if (slides.isEmpty()) {
                            System.err.println("Erreur : aucune slide n'existe dans cette session");
                        } else {
                            for (Slide slide : slides) {
                                slideDAO.save(slide, sessionId);
                            }
                        }
                        break;

                    case "7":
                        System.out.println("Fermer session");

                        if (currentSession == null) {
                            System.out.println("Erreur : aucune session ouverte");
                            break;
                        }

                        currentSession = null;
                        System.out.println("Session fermée");
                        break;

                    case "8":
                        if (currentSession == null) {
                            System.out.println("Aucune session ouverte");
                            break;
                        }

                        System.out.print("Nom du fichier HTML : ");
                        String outputPath = scanner.nextLine();

                        HTMLExporter exporter = new HTMLExporter();
                        System.out.println("Répertoire courant : "
                                + System.getProperty("user.dir"));

                        exporter.export(currentSession, outputPath);
                        System.out.println("Fichier généré : "
                                + Paths.get(outputPath).toAbsolutePath());

                        System.out.println("Export HTML réussi : " + outputPath);
                        break;

                    case "0":
                        System.out.println("Fermeture de l'application...");
                        break;

                    default:
                        System.out.println("Choix invalide, veuillez reessayer.");
                }

                if (choice.equals("0")) {
                    break;
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
