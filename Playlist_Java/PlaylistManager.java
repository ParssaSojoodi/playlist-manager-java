/**
 * Author: Parsa Sojoodi
 * NetID: PS769471
 * Project: Playlist Manager
 * Class: ICSI213
 */

import java.util.Scanner;

public class PlaylistManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RecentlyPlayedQueue recentQueue = new RecentlyPlayedQueue();

        boolean useArray = true;
        PlaylistArrayList arrayList = new PlaylistArrayList(recentQueue);
        PlaylistLinkedList linkedList = new PlaylistLinkedList(recentQueue);

        while (true) {
            System.out.println("\n-- Playlist Manager --");
            System.out.println("1. Add Song");
            System.out.println("2. Insert Song at Position");
            System.out.println("3. Remove Song by Position");
            System.out.println("4. View Song");
            System.out.println("5. Shuffle Playlist");
            System.out.println("6. Print Playlist");
            System.out.println("7. Sort Playlist");
            System.out.println("8. Search");
            System.out.println("9. View Recently Played Songs");
            System.out.println("10. Switch Playlist Type");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            var current = useArray ? arrayList : linkedList;

            // Switch to allow user to choose an option
            switch (choice) {
                case 1 -> {
                    Song song = createSong(sc);
                    if (useArray) arrayList.addSong(song);
                    else linkedList.addSong(song);
                }
                case 2 -> {
                    System.out.print("Enter index: ");
                    int index = Integer.parseInt(sc.nextLine());
                    Song song = createSong(sc);
                    if (useArray) arrayList.insertSong(index, song);
                    else linkedList.insertSong(index, song);
                }
                case 3 -> {
                    System.out.print("Enter index: ");
                    int index = Integer.parseInt(sc.nextLine());
                    if (useArray) arrayList.removeSong(index);
                    else linkedList.removeSong(index);
                }
                case 4 -> {
                    System.out.print("Enter index: ");
                    int index = Integer.parseInt(sc.nextLine());
                    Song song = useArray ? arrayList.getSong(index) : linkedList.getSong(index);
                    if (song != null) System.out.println("Playing: " + song);
                }
                case 5 -> {
                    if (useArray) arrayList.shuffle();
                    else linkedList.shuffle();
                }
                case 6 -> {
                    if (useArray) arrayList.printPlaylist();
                    else linkedList.printPlaylist();
                }
                case 7 -> {
                    System.out.println("Sort by: 1. Title 2. Artist 3. Duration 4. Play Count");
                    int sortType = Integer.parseInt(sc.nextLine());
                    if (useArray) {
                        switch (sortType) {
                            case 1 -> arrayList.sortByTitle();
                            case 2 -> arrayList.sortByArtist();
                            case 3 -> arrayList.sortByDuration();
                            case 4 -> arrayList.sortByPlayCount();
                        }
                    } else {
                        switch (sortType) {
                            case 1 -> linkedList.sortByTitle();
                            case 2 -> linkedList.sortByArtist();
                            case 3 -> linkedList.sortByDuration();
                            case 4 -> linkedList.sortByPlayCount();
                        }
                    }
                }
                case 8 -> {
                    System.out.println("Search by: 1. Title 2. Artist 3. Genre");
                    int searchType = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter keyword: ");
                    String keyword = sc.nextLine();
                    if (useArray) {
                        switch (searchType) {
                            case 1 -> arrayList.searchByTitle(keyword);
                            case 2 -> arrayList.searchByArtist(keyword);
                            case 3 -> arrayList.searchByGenre(keyword);
                        }
                    } else {
                        switch (searchType) {
                            case 1 -> linkedList.searchByTitle(keyword);
                            case 2 -> linkedList.searchByArtist(keyword);
                            case 3 -> linkedList.searchByGenre(keyword);
                        }
                    }
                }
                case 9 -> recentQueue.printRecentlyPlayed();
                case 10 -> {
                    useArray = !useArray;
                    System.out.println("Switched to " + (useArray ? "ArrayList" : "LinkedList") + " mode.");
                }
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
    // show the liust for the create song command
    private static Song createSong(Scanner sc) {
        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Artist: ");
        String artist = sc.nextLine();
        System.out.print("Duration (in seconds): ");
        int duration = Integer.parseInt(sc.nextLine());
        System.out.print("Genre: ");
        String genre = sc.nextLine();
        return new Song(title, artist, duration, genre);
    }
}
