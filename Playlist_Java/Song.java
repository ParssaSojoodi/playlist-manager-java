/**
 * Author: Parsa Sojoodi
 * NetID: PS769471
 * Project: Playlist Manager
 * Class: ICSI213
 */

public class Song {
    private String title;
    private String artist;
    private int duration; // receive tome in seconds
    private String genre;
    private int playCount;

    public Song(String title, String artist, int duration, String genre) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.genre = genre;
        this.playCount = 0; // start at 0 like specified
    }

    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public int getDuration() { return duration; }
    public String getGenre() { return genre; }
    public int getPlayCount() { return playCount; }

    public void incrementPlayCount() {
        playCount++;
    }

    @Override
    // We are getting seconds from user so we have to turn in into minutes. so we do this to convert it
    public String toString() {
        int minutes = duration / 60;
        int seconds = duration % 60;
        return "\"" + title + "\" by " + artist + " [" + minutes + "m " + seconds + "s] - " + genre + " (Played " + playCount + " times)";
    }
}
