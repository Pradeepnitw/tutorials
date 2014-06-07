package leetcode.jun2014;

import java.util.*;

public class LRUCache {
    private HashMap<Integer, Node> map;
    private Node listHead;
    private Node listTail;
    private int listSize;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        listHead = null;
        listTail = null;
        listSize = 0;
        map = new HashMap<Integer, Node>();
    }
        
    public void set(int key, int value) {
        if (map.containsKey(key)) {
                Node oldNode = map.remove(key);
                deleteNodeFromList(oldNode);
                
                Node newNode = new Node(key, value);
                addNodeToListEnd(newNode);
                map.put(key, newNode);
        } else {
            if (listSize == capacity) {
                Node removeNode = removeNodeFromFront();
                map.remove(removeNode.getKey());
            }
            Node addNode = new Node(key, value);
            addNodeToListEnd(addNode);
            map.put(key, addNode);
        }
    }
        
    public int get(int key) {
        if (map.containsKey(key)) {
            Node thisNode = map.get(key);
            deleteNodeFromList(thisNode);
            addNodeToListEnd(thisNode);
            return thisNode.getValue();
        }
        else return -1;
    }
    
    private Node removeNodeFromFront() {
        if (listSize == 0) return null;
        
        Node n = listHead;
        if (listSize == 1) {
            listHead = null;
            listTail = null;
        } else {
            listHead = n.next;
            n.next = null;
            listHead.prev = null;
        }
        listSize--;
        return n;
    }
    
    private Node removeNodeFromBack() {
        if (listSize == 0) return null;
        
        Node n = listTail;
        if (listSize == 1) {
            listHead = null;
            listTail = null;
        } else {
            listTail = n.prev;
            n.prev = null;
            listTail.next = null;
        }
        listSize--;
        return n;
    }
    
    private void addNodeToListFront(Node n) {
        if (listSize == 0) {
            listHead = n;
            listTail = n;
        } else {
            listHead.prev = n;
            n.next = listHead;
            listHead = n;
        }
        listSize++;
    }
    
    private void addNodeToListEnd(Node n) {
        if (listSize == 0) {
            listHead = n;
            listTail = n;
        } else {
            listTail.next = n;
            n.prev = listTail;
            listTail = n;
        }
        listSize++;
    }
    
    private void deleteNodeFromList(Node n) {
        if (listSize == 0) return;
        if (n == listHead) {
            removeNodeFromFront();
            return;
        } else if (n == listTail) {
            removeNodeFromBack();
            return;
        }
        
        n.prev.next = n.next;
        n.next.prev = n.prev;
        n.prev = null;
        n.next = null;
        
        listSize--;
    }
    
    public class Node {
        private int key;
        private int value;
        
        public Node next;
        public Node prev;
        
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            next = null;
            prev = null;
        }
        
        public int getKey() {
            return key;
        }
        
        public int getValue() {
            return value;
        }
    }
}
