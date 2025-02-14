package com.project;

import com.project.dao.MovieDAO;
import com.project.entity.Movie;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MovieManager {
    public static void main(String[] args) {
        MovieDAO dao = new MovieDAO();
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                printMenu();

                int choice = getUserChoice(scanner);

                switch (choice) {
                    case 1:
                        addMovie(scanner, dao);
                        break;
                    case 2:
                        viewMovies(dao);
                        break;
                    case 3:
                        updateMovie(scanner, dao);
                        break;
                    case 4:
                        deleteMovie(scanner, dao);
                        break;
                    case 5:
                        System.out.println("\nThank you for using Movie Management System! 🎬");
                        System.exit(0);
                    default:
                        System.out.println("❌ Invalid choice! Please enter a number between 1 and 5.");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n🎬 Movie Management System 🎬");
        System.out.println("=============================");
        System.out.println("1️. Add Movie");
        System.out.println("2️. View Movies");
        System.out.println("3️.  Update Movie");
        System.out.println("4️.  Delete Movie");
        System.out.println("5️.  Exit");
        System.out.print("🔹 Enter your choice: ");
    }

    private static int getUserChoice(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("❌ Invalid input! Please enter a valid number: ");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    private static void addMovie(Scanner scanner, MovieDAO dao) {
        scanner.nextLine(); // Consume the leftover newline

        System.out.print("🎥 Enter Movie Title: ");
        String title = scanner.nextLine();

        System.out.print("🎭 Enter Genre: ");
        String genre = scanner.nextLine();

        System.out.print("🎬 Enter Director: ");
        String director = scanner.nextLine();

        int year = getIntInput(scanner, "📅 Enter Release Year: ");
        double rating = getDoubleInput(scanner, "⭐ Enter Rating (0-10): ");

        Movie movie = new Movie(title, genre, director, year, rating);
        dao.addMovie(movie);

        System.out.println("✅ Movie added successfully!");
    }

    private static void viewMovies(MovieDAO dao) {
        List<Movie> movies = dao.getAllMovies();
        if (movies.isEmpty()) {
            System.out.println("⚠️ No movies found in the database.");
        } else {
            System.out.println("\n📜 List of Movies:");
            System.out.println("---------------------------");
            movies.forEach(System.out::println);
        }
    }

    private static void updateMovie(Scanner scanner, MovieDAO dao) {
        System.out.print("Enter Movie ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        Movie movie = dao.getMovieById(id);
        if (movie == null) {
            System.out.println("❌ Movie not found with ID: " + id);
            return;
        }

        System.out.println("\n🎬 Updating Movie: " + movie.getTitle());
        
        boolean updating = true;
        while (updating) {
            System.out.println("\nWhich field would you like to update?");
            System.out.println("1. Title");
            System.out.println("2. Genre");
            System.out.println("3. Director");
            System.out.println("4. Release Year");
            System.out.println("5. Rating");
            System.out.println("6. Done (Save changes)");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter new Title: ");
                    movie.setTitle(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter new Genre: ");
                    movie.setGenre(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter new Director: ");
                    movie.setDirector(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Enter new Release Year: ");
                    movie.setReleaseYear(scanner.nextInt());
                    scanner.nextLine(); // Consume newline
                    break;
                case 5:
                    System.out.print("Enter new Rating: ");
                    movie.setRating(scanner.nextDouble());
                    scanner.nextLine(); // Consume newline
                    break;
                case 6:
                    updating = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }

        dao.updateMovie(movie);
        System.out.println("✅ Movie updated successfully!");
    }

    

    private static void deleteMovie(Scanner scanner, MovieDAO dao) {
        int id = getIntInput(scanner, "❌ Enter Movie ID to delete: ");
        dao.deleteMovie(id);
        System.out.println("✅ Movie deleted successfully!");
    }

    private static int getIntInput(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("❌ Invalid input! Please enter a valid number: ");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    private static double getDoubleInput(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.print("❌ Invalid input! Please enter a valid number: ");
                scanner.next(); // Clear the invalid input
            }
        }
    }
}
