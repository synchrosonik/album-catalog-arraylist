import java.util.ArrayList;
import java.util.Scanner;

public class AlbumManagement {

    private static ArrayList<Album> albums = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void addAlbum(Album album) {
        albums.add(album);
    }

    public static void createAlbum() {
        System.out.print("\nTitle: ");
        String title = scanner.nextLine();
        System.out.print("Artist: ");
        String artist = scanner.nextLine();
        System.out.print("Year released: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Score: ");
        int score = scanner.nextInt();
        scanner.nextLine();
        Album album = new Album(title, artist, year, score);
        addAlbum(album);
        System.out.println("Album added");
    }

    public static void printAlbums() {
        for (Album album : albums) {
            System.out.println(album);
        }
    }

    public static void updateAlbumRating() {
        findAlbumByArtist();

        System.out.print("\nProvide the id for the album you want to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Album album = findAlbumById(id);
        if (album != null) {
            System.out.println("Current rating: " + album.getRating());
            boolean menu = true;
            while (menu) {
                System.out.print("New rating (1-10): ");
                int rating = scanner.nextInt();
                scanner.nextLine();
                if (rating < 1 || rating > 10) {
                    System.out.println("Rating can only be set to numbers from 1 to 10");
                } else {
                    album.setRating(rating);
                    System.out.println("Rating updated");
                    menu = false;
                }
            }
        } else {
            System.out.println("Couldn't find the album");
        }

    }

    public static void deleteAlbum() {
        findAlbumByArtist();

        System.out.print("\nProvide the id for the album you want to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        albums.removeIf(album -> album.getId() == id);

        System.out.println("Album deleted");

    }

    public static void findAlbumByArtist() {
        System.out.print("\nArtist: ");
        String artist = scanner.nextLine();
        boolean albumExists = false;
        for (Album album : albums) {
            if (album.getArtist().equalsIgnoreCase(artist)) {
                albumExists = true;
                System.out.println(album);
            }
        }
        if (!albumExists) {
            System.out.println("There's no albums by " + artist + " in the catalog");
            AlbumMain.mainMenu();
        }
    }

    public static void findAlbumByYear() {
        System.out.print("\nYear: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        for (Album album : albums) {
            if (album.getYear() == year) {
                System.out.println(album);
            }
        }
    }

    public static void findAlbumByRating() {
        System.out.print("\nRating: ");
        int rating = scanner.nextInt();
        scanner.nextLine();
        for (Album album : albums) {
            if (album.getRating() == rating) {
                System.out.println(album);
            }
        }
    }

    public static Album findAlbumById(int id) {
        for (Album album : albums) {
            if (album.getId() == id) {
                return album;
            }
        }
        return null;
    }

    public static void showAverageRating() {
        int scoreSum = 0;
        for (Album album : albums) {
            scoreSum = scoreSum + album.getRating();
        }
        System.out.println("The average album rating is: " + (scoreSum / albums.size()));
    }
}
