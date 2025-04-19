/**
 * Author: Parsa Sojoodi
 * NetID: PS769471
 * Project: Playlist Manager
 * Class: ICSI213
 */

public class RecentlyPlayedQueue {
    private static class Node {
        Song song;
        Node next;

        Node(Song song) {
            this.song = song;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public RecentlyPlayedQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void addRecentlyPlayed(Song song) {
        Node newNode = new Node(song);

        if (size == 10) {
            // HEre it will remove the oldest (head)
            head = head.next;
            size--;
        }

        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    public void printRecentlyPlayed() {
        System.out.println("\n-- Recently Played Songs --");
        Node current = head;
        int index = 1;
        while (current != null) {
            System.out.println(index++ + ". " + current.song);
            current = current.next;
        }
        if (size == 0) System.out.println("No songs played recently."); // if the user choose the recent songs without plating any
    }
}
