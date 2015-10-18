/**
 * A replicated version of Java's LinkedList
 * @author user Teddy Todorov
 * @version 10.3.14
 */
public class LinkedList {
    private int size;
    private Node head;
    private Node tail;
    
    /**
     * Sets initial values to variables.
     */
    public LinkedList() {
        size = 0;
        head = null;
        tail = null;
        
    }
    
    /**
     * Nested class, acts as the basis of the LinkedList class
     * @author user Teddy Todorov
     * @version 10.3.14
     */
    public class Node {
        private Object data;
        private Node next;
        
        /**
         * Sets initial data
         * @param o Data is to be set to Object
         */
        public Node( Object o ) {
            data = o;
        }
        
        /**
         * Returns data of Node
         * @return data of Node
         */
        public Object getData() {
            return data;
        }
        
        /**
         * Sets data of Node
         * @param o Value of Node to be set
         */
        public void setData( Object o ) {
            data = o;
        }
        
        /**
         * Returns Node pointed at
         * @return Node pointed at
         */
        public Node getNext() {
            return next;
        }
    }
    
    /**
     * Returns the Object at specified index
     * @param index of element to return
     * @return element at specified index
     */
    public Object get( int index ) {
        Node current = head;
        int tracker = index;
        while ( current != null ) {
            if ( tracker == 0 ) {
                return current.data;
            }
            current = current.next;
            tracker--;
        }
        return null;
    }
    
    /**
     * Returns amount of slots in List.
     * @return amount of slots in List.
     */
    public int size() {
        return size;
    }
    
    /**
     * Adds specif ied Object to the end of List.
     * @param o Object to be added
     * @return Whether or not Object o was added
     */
    public boolean add( Object o ) {
        if ( size > 0 ) {
            Node temp = new Node(o);
            tail.next = temp;
            tail = temp;
        }
        else {
            head = new Node(o);
            tail = head;
        }
        size++;
        return true;
    }
    
    
    /**
     * Sets the specified spots Node to the specified Object.
     * Returns overridden Object.
     * @param index Index of Object to replace
     * @param o Object to replace with
     * @return Object replaced
     */
    
    public Object set( int index, Object o ) {
        Node current = head;
        Object ants = null;
        int tracker = index;
        while ( current != null ) {
            if ( tracker == 0 ) {
                ants = current.data;
                current.setData(o);
                return ants;
            }
            current = current.next;
            tracker--;
        }
        return null;
    }
    
    /**
     * Finds index of specified Object
     * @param o Object to search for 
     * @return Index of the specified Object
     */
    public int indexOf( Object o ) {
        int tracker = -1;
        Node current = head;
        while ( current != null ) {
            tracker++;
            if ( o.equals( current.data ) ) {
                return tracker;
            }
            current = current.next;
        }
        return -1;
    }
    
    /**
     * Find the last index of the specified Object
     * @param o Object to search for 
     * @return Last index of the specified Object
     */
    public int lastIndexOf( Object o ) {
        int tracker = -1;
        int slot = 0;
        for ( Node i = head; i != null; i = i.next ) {
            if ( o.equals( i.data ) ) {
                tracker = slot;
            }
            slot++;
        }
        return tracker;
    }
    
    /**
     * Adds a specified Object to the specified index
     * @param index index to add specified Object
     * @param o Object to add to specified index
     */
    public void add( int index, Object o ) {
        if ( index == size ) {
            add(o);
        }
        else if ( index >= 0 && index < size ) {
            if ( index != 0 ) {
                int slot = 1;
                for ( Node current = head; current != null;
                        current = current.next ) {
                    if ( slot == index ) {
                        Node temp = new Node(o);
                        temp.next = current.next;
                        current.next = temp;
                    }
                    slot++;
                }
            }
            else {
                Node temp = new Node(o);
                temp.next = head;
                head = temp;
            }
            size++;
        }
    }
    
    /**
     * Removes Object at specified location.
     * Returns Object removed.
     * @param index Index to remove
     * @return Object to remove
     */
    public Object remove( int index ) {
        if ( size >  0 && index >= 0 && index < size) {
            if ( size == 1 ) {
                Object temp = head.data;
                tail = null;
                head = tail;
                size--;
                return temp;
            }
            if ( index <= 0 ) {
                Object temp = head.data;
                head = head.next;
                size--;
                return temp;
            }
            int tracker = 1;
            for ( Node current = head; current != null; 
                    current = current.next, tracker++ ) {
                if ( tracker == index ) {
                    Object temp = current.next.data;
                    current.next = current.next.next;
                    size--;
                    return temp;
                }
            }
        }
        return null;
    }
    
    /**
     * Removes first occurrence of Object o
     * @param o Specified Object to search for 
     * @return Whether or not Object was found and removed.
     */
    public boolean remove( Object o ) {
        if ( size > 0 ) {
            if ( o.equals( head.data ) ) {
                if ( size == 1 ) {
                    tail = null;
                    head = tail;
                    size--;
                    return true;
                }
                else {
                    head = head.next;
                    size--;
                    return true;
                }
            }
            for ( Node current = head; current.next != null;
                    current = current.next ) {
                if ( current.next.data.equals( o ) ) {
                    current.next = current.next.next;
                    size--;
                    return true;
                }
            }
        }
        return false;
    }
    //It runs more efficiently this way :)
    
    /**
     * Creates an Object Array of the LinkedList
     * @return Converted Object Array
     */
    public Object[] toArray() {
        Object[] temp = new Object[size];
        int i = 0;
        if ( size > 0 ) {
            for ( Node current = head; current != null;
                    current = current.next, i++) {
                temp[i] = current.data;
            }
            return temp;
        }
        return null;
    }
    
    /**
     * Returns whether or not the specified object
     * is contained within the Linked List.
     * @param o Object to be searched for .
     * @return Whether or not Object is in List.
     */
    public boolean contains( Object o ) {
        return indexOf(o) >= 0;
    }
    
    /**
     * Removes all data from List
     */
    public void clear() {
        size = 0;
        tail.next = null;
        head.next = tail;
    }
    
    /**
     * Checks if  List has no data.
     * @return Whether or note List is empty.
     */
    public boolean isEmpty() {
        return size <= 0;
    }
    
    /**
     * Turns the data in the Linked List to 
     * String for mat.
     * @return A String of the List's data.
     */
    
    public String toString() {
        if ( size > 0 ) {
            String ans = "[";
            int i = 1;
            for ( Node current = head; i < size;
                    current = current.next, i++ ) {
                ans += current.data + ", ";
            }
            ans += tail.data + "]";
            return ans;
        }
        return "[]";
    }
    
}