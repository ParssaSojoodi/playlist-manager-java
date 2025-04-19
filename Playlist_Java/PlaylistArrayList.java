/**
 * Author: Parsa Sojoodi
 * NetID: PS769471
 * Project: Playlist Manager
 * Class: ICSI213
 */

import java.util.Comparator;

public class PlaylistArrayList {
    private Song[] songs; // manually managed array of songs
    private int size; // check the current number of songs
    private RecentlyPlayedQueue queue; // recently played queue 

    public PlaylistArrayList(RecentlyPlayedQueue queue) {
        this.songs = new Song[20]; // starting with capacity 20 for the songs
        this.size = 0;
        this.queue = queue;
    }
    // adds song to the end of the playlist
    public void addSong(Song song) {
        ensureCapacity(); // with this command, we make sure we don’t run out of space
        songs[size++] = song;
    }

    // inserts a song at a specific position
    public void insertSong(int index, Song song) {
        if (index < 0 || index > size) {
            System.out.println("Invalid index.");
            return;
        }
        // shift everything to the right to make more space
        ensureCapacity();
        for (int i = size; i > index; i--) {
            songs[i] = songs[i - 1];
        }
        songs[index] = song;
        size++;
    }

    // removes a song by its index
    public void removeSong(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid index.");
            return;
        }
        // shift everything left to fill the gap
        for (int i = index; i < size - 1; i++) {
            songs[i] = songs[i + 1];
        }
        songs[--size] = null;
    }

    public Song getSong(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid index.");
            return null;
        }
        songs[index].incrementPlayCount();
        queue.addRecentlyPlayed(songs[index]);
        return songs[index];
    }

    public void shuffle() {
        for (int i = 0; i < size; i++) {
            int randIndex = (int)(Math.random() * size);
            Song temp = songs[i];
            songs[i] = songs[randIndex];
            songs[randIndex] = temp;
        }
    }

    public void printPlaylist() {
        System.out.println("\n-- Playlist (ArrayList) --");
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + songs[i]);
        }
    }

    private void ensureCapacity() {
        if (size == songs.length) {
            Song[] newSongs = new Song[songs.length * 2];
            for (int i = 0; i < songs.length; i++) {
                newSongs[i] = songs[i];
            }
            songs = newSongs;
        }
    }

    // Sorting methods:

    // sort songs by title in order of alphabet
    public void sortByTitle() {
        bubbleSort((s1, s2) -> s1.getTitle().compareToIgnoreCase(s2.getTitle()));
    }

    // sort songs by artist name alphabet order
    public void sortByArtist() {
        bubbleSort((s1, s2) -> s1.getArtist().compareToIgnoreCase(s2.getArtist()));
    }

    // sort songs from shortest to longest duration 
    public void sortByDuration() {
        bubbleSort((s1, s2) -> Integer.compare(s1.getDuration(), s2.getDuration()));
    }

    // sort songs by play count
    public void sortByPlayCount() {
        bubbleSort((s1, s2) -> Integer.compare(s2.getPlayCount(), s1.getPlayCount()));
    }

    private void bubbleSort(Comparator<Song> comparator) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (comparator.compare(songs[j], songs[j + 1]) > 0) {
                    Song temp = songs[j];
                    songs[j] = songs[j + 1];
                    songs[j + 1] = temp;
                }
            }
        }
    }

    // Searching

    // search by title and show all positions that match with user's input
    public void searchByTitle(String title) {
        for (int i = 0; i < size; i++) {
            if (songs[i].getTitle().equalsIgnoreCase(title)) {
                System.out.println("Found at position " + i + ": " + songs[i]);
            }
        }
    }

    // search by artist and list all matching songs 
    public void searchByArtist(String artist) {
        for (int i = 0; i < size; i++) {
            if (songs[i].getArtist().equalsIgnoreCase(artist)) {
                System.out.println(songs[i]);
            }
        }
    }

    // search by the genre and list all matching songs 
    public void searchByGenre(String genre) {
        for (int i = 0; i < size; i++) {
            if (songs[i].getGenre().equalsIgnoreCase(genre)) {
                System.out.println(songs[i]);
            }
        }
    }
}
