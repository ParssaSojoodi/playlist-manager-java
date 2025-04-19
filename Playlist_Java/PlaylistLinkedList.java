/**
 * Author: Parsa Sojoodi
 * NetID: PS769471
 * Project: Playlist Manager
 * Class: ICSI213
 */

public class PlaylistLinkedList {
    private static class Node {
        Song song;
        Node next;

        Node(Song song) {
            this.song = song;
        }
    }

    private Node head; // start of the playlist at here
    private int size; // number of the songs in the playlist
    private RecentlyPlayedQueue queue;

    public PlaylistLinkedList(RecentlyPlayedQueue queue) {
        this.queue = queue;
        this.head = null;
        this.size = 0;
    }

    // adds a song to the end of the playlist
    public void addSong(Song song) {
        Node newNode = new Node(song);
        if (head == null) head = newNode;
        else {
            Node curr = head;
            while (curr.next != null) curr = curr.next;
            curr.next = newNode;
        }
        size++;
    }

    // inserts the song at a specific position . if we get 0 it's 0 = front
    public void insertSong(int index, Song song) {
        if (index < 0 || index > size) {
            System.out.println("Invalid index.");
            return;
        }
        Node newNode = new Node(song);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node curr = head;
            for (int i = 0; i < index - 1; i++) curr = curr.next;
            newNode.next = curr.next;
            curr.next = newNode;
        }
        size++;
    }

    // this part will removes a song from the playlist by the index
    public void removeSong(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid index.");
            return;
        }
        if (index == 0) head = head.next;
        else {
            Node curr = head;
            for (int i = 0; i < index - 1; i++) curr = curr.next;
            curr.next = curr.next.next;
        }
        size--;
    }

    // accesses a song by index, and it will marks it as it's been played
    public Song getSong(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid index.");
            return null;
        }
        Node curr = head;
        for (int i = 0; i < index; i++) curr = curr.next;
        curr.song.incrementPlayCount(); // track plays
        queue.addRecentlyPlayed(curr.song); // update recently played
        return curr.song;
    }

    // prints out the full playlist in the output
    public void printPlaylist() {
        System.out.println("\n-- Playlist (LinkedList) --");
        Node curr = head;
        int i = 1;
        while (curr != null) {
            System.out.println((i++) + ". " + curr.song);
            curr = curr.next;
        }
    }

    // this command is for the randomly shuffle the song.
    public void shuffle() {
        if (size <= 1) return;
        Song[] songs = new Song[size];
        Node curr = head;
        for (int i = 0; i < size; i++) {
            songs[i] = curr.song;
            curr = curr.next;
        }
        for (int i = 0; i < size; i++) {
            int rand = (int)(Math.random() * size);
            Song temp = songs[i];
            songs[i] = songs[rand];
            songs[rand] = temp;
        }
        curr = head;
        for (int i = 0; i < size; i++) {
            curr.song = songs[i];
            curr = curr.next;
        }
    }

    // sorting the jfunctions using the bubble sort method
    public void sortByTitle() {
        bubbleSort((a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));
    }

    public void sortByArtist() {
        bubbleSort((a, b) -> a.getArtist().compareToIgnoreCase(b.getArtist()));
    }

    public void sortByDuration() {
        bubbleSort((a, b) -> Integer.compare(a.getDuration(), b.getDuration()));
    }

    public void sortByPlayCount() {
        bubbleSort((a, b) -> Integer.compare(b.getPlayCount(), a.getPlayCount()));
    }

    private void bubbleSort(java.util.Comparator<Song> comp) {
        if (size < 2) return;
        for (int i = 0; i < size; i++) {
            Node curr = head;
            while (curr != null && curr.next != null) {
                if (comp.compare(curr.song, curr.next.song) > 0) {
                    Song temp = curr.song;
                    curr.song = curr.next.song;
                    curr.next.song = temp;
                }
                curr = curr.next;
            }
        }
    }

        // searches by song title and prints any matches it will find 
    public void searchByTitle(String title) {
        Node curr = head;
        int i = 0;
        while (curr != null) {
            if (curr.song.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Found at position " + i + ": " + curr.song);
            }
            curr = curr.next;
            i++;
        }
    }

    // searches all songs by the rtist name
    public void searchByArtist(String artist) {
        Node curr = head;
        while (curr != null) {
            if (curr.song.getArtist().equalsIgnoreCase(artist)) {
                System.out.println(curr.song);
            }
            curr = curr.next;
        }
    }

    // searches all songs by their genre
    public void searchByGenre(String genre) {
        Node curr = head;
        while (curr != null) {
            if (curr.song.getGenre().equalsIgnoreCase(genre)) {
                System.out.println(curr.song);
            }
            curr = curr.next;
        }
    }
}
