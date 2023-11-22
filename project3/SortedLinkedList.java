package project3;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is an implementation of a sorted doubly-linked list.
 * All elements in the list are maintained in ascending/increasing order
 * based on the natural order of the elements.
 * This list does not allow <code>null</code> elements.
 *
 * @author Joanna Klukowska
 * @author Imani Gomez - I used the given linked list template
 *
 * @param <E> the type of elements held in this list
 */
public class SortedLinkedList<E extends Comparable<E>>
    implements Iterable<E> {

    private Node head;
    private Node tail;
    private int size;
    Node getHead(){
        return head;
    }

    /**
     * Constructs a new empty sorted linked list.
     */
    public SortedLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Adds the specified element to the list in ascending order.
     *
     * @param element the element to add
     * @return <code>true</code> if the element was added successfully,
     * <code>false</code> otherwise (if <code>element==null</code>)
     */
    public boolean add(E element) {
        if(element == null){
            //null elements are accepted
            return false;
        }
        Node newNode = new Node(element);
        if(head == null){
            //list is empty - set new element as head and tail
            head = newNode;
            tail = newNode;
        }else{
            if(element.compareTo(head.getData()) <= 0){
                //element is smaller or equal to the current head, insert at head
                newNode.setNext(head);
                head.setPrev(newNode);
                head = newNode;
            }else if(element.compareTo(tail.getData()) >= 0){
                //element is greater or equal to the current tail, insert at tail
                tail.setNext(newNode);
                newNode.setPrev(tail);
                tail = newNode;
            }else{
                //element should be inserted in the middle
                Node current = head;
                while(current.getNext() != null && element.compareTo(current.getNext().getData())>0){
                    current = current.getNext();
                }
                newNode.setNext(current.getNext());
                newNode.setPrev(current);
                current.setNext(newNode);
                newNode.getNext().setPrev(newNode);
            }
        }
        size++;
        return true;
    }

    /**
     * Removes all elements from the list.
     */
    public void clear() {
        //set head to null
        head = null;
        //set tail to null
        tail = null;
        //size set to 0
        size = 0;
    }

    /**
     * Returns <code>true</code> if the list contains the specified element,
     * <code>false</code> otherwise.
     *
     * @param o the element to search for
     * @return <code>true</code> if the element is in the list,
     * <code>false</code> otherwise
     */
    public boolean contains(Object o) {
        if(o==null){
            //null elements not accepted
            return false;
        }
        //case object to generic type E
        E element = (E) o;
        //start at the head of the list
        Node current = head;
        while(current != null){
            if(element.compareTo(current.getData()) == 0){
                //element in the list matches
                return true;
            }
            //move onto the next node in the list
            current = current.getNext();
        }
        //element not found
        return false;
    }

    /**
     * Returns the element at the specified index in the list.
     *
     * @param index the index of the element to return
     * @return the element at the specified index
     * @throw IndexOutOfBoundsException  if the index is out of
     * range <code>(index < 0 || index >= size())</code>
     */
    public E get(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException("index not found");
        }
        //start at the head of the list
        Node current = head;
        for(int i = 0; i< index; i++){
            //move to the next element
            current = current.getNext();
        }
        //return the data of the element at specified index
        return current.getData();
    }

    /**
     * Returns the index of the first occurrence of the specified element in the list,
     * or -1 if the element is not in the list.
     *
     * @param o the element to search for
     * @return the index of the first occurrence of the element,
     * or -1 if the element is not in the list
     */
    public int indexOf(Object o) {
        if(o==null){
            //null element is not in list
            return -1;
        }

        //cast the object to the generic type E
        E element = (E) o;
        //start at the head of the list
        Node current = head;
        int index = 0;

        //iterate through the list to find the index of the element
        while(current != null){
            if(element.compareTo(current.getData()) == 0){
                //found the element, return its index
                return index;
            }
            //move to the next element
            current = current.getNext();
            //increment the index
            index++;
        }
        //element not found in the list
        return -1;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the list,
     * starting at the specified <code>index</code>, i.e., in the range of indexes
     * <code>index <= i < size()</code>, or -1 if the element is not in the list
     * in the range of indexes <code>index <= i < size()</code>.
     *
     * @param o the element to search for
     * @param index the index to start searching from
     * @return the index of the first occurrence of the element, starting at the specified index,
     * or -1 if the element is not found
     */
    public int nextIndexOf(Object o, int index) {
        if(index < 0 || index >= size()){
            return -1; //invalid index is out of range
        }
        if(o==null){
            //null element is not in list
            return -1; 
        }

        //cast the object to the generic type
        E element = (E) o; 
        Node current = head;
        int currentIndex = 0;

        //move to the specified index in the list
        while (current != null && currentIndex < index){
            current = current.getNext();
            currentIndex++;
        }

        //search for the element
        while(current != null){
            if(element.compareTo(current.getData()) == 0){
                //element found
                return currentIndex;
            }
            current = current.getNext();
            currentIndex ++;
        }
        //element not found
        return -1;
    }

    /**
     * Removes the first occurence of the specified element from the list.
     *
     * @param o the element to remove
     * @return <code>true</code> if the element was removed successfully,
     * <code>false</code> otherwise
     */
    public boolean remove(Object o) {
        if(o==null){
            //null element cant be removed
            return false;
        }

        //cast object to generic type E
        E element = (E) o;
        Node current = head;

        while(current != null){
            if(element.compareTo(current.getData()) == 0){
                if(current == head){
                    //if element to remove is at the head of the list
                    //update the head to the next element
                    head = current.getNext();
                    if(head != null){
                        //set the new head's previous to null
                        head.setPrev(null);
                    }else{
                        //if there's no new head, set tail to null
                        tail = null;
                    }
                }else if(current == tail){
                    //if elememt is at tail, update to previous element
                    tail = current.getPrev();
                    tail.setNext(null);
                }else{
                    //if element is in the middle, linke previous to next node
                    current.getPrev().setNext(current.getNext());
                    current.getNext().setPrev(current.getPrev());
                }
                size--;
                //element removed
                return true;
            }
            current = current.getNext();
        }
        //element not found
        return false;
    }

    /**
     * Returns the size of the list.
     *
     * @return the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Returns an iterator over the elements in the list.
     *
     * @return an iterator over the elements in the list
     */
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    /**
     * Compares the specified object with this list for equality.
     *
     * @param o the object to compare with
     * @return <code>true</code> if the specified object is equal to this list,
     * <code>false</code> otherwise
     */
    public boolean equals(Object o) {
        if(this==o){
            return true;
        }
        if(o==null || getClass() != o.getClass()){
            return false;
        }

        SortedLinkedList<E> that = (SortedLinkedList<E>) o; 

        //e is generic element as placeholder for any type

        if(size != that.size){
            //size of two lists are not the same
            return false;
        }

        Node thisCurrent = head;
        Node thatCurrent = that.head;

        while(thisCurrent != null){
            //compare current position
            if(!thisCurrent.getData().equals(thatCurrent.getData())){
                //if elements are not the same
                return false;
            }
            thisCurrent = thisCurrent.getNext();
            thatCurrent = thatCurrent.getNext();
        }
        //lists are equal
        return true;
    }

    /**
     * Returns a string representation of the list.
     *  The string representation consists of a list of the lists's elements in
     *  ascending order, enclosed in square brackets ("[]").
     *  Adjacent elements are separated by the characters ", " (comma and space).
     *
     * @return a string representation of the list
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        //start from head of list
        Node current = head;
        while(current != null){
            //add the current element data to string builder
            sb.append(current.getData());
            if(current.getNext() != null){
                //if next element is not null add a comma
                sb.append(", ");
            }
            //move to next element
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    /* Inner class to represent nodes of this list.*/
    private class Node implements Comparable<Node> {
        private E data;
        private Node next;
        private Node prev;
        Node(E data) {
            if (data == null ) throw new NullPointerException ("does not allow null");
            this.data = data;
        }
        Node (E data, Node next, Node prev) {
            this(data);
            this.next = next;
            this.prev = prev;
        }
        E getData(){
            return data;
        }

        void setNext(Node next){
            this.next = next;
        }

        Node getNext(){
            return next;
        }

        void setPrev(Node prev){
            this.prev = prev;
        }

        Node getPrev(){
            return prev;
        }

        @Override
        public int compareTo( Node n ) {
            return this.data.compareTo(n.data);
        }
    }

    /* A basic forward iterator for this list. */
    private class ListIterator implements Iterator<E> {

        private  Node nextToReturn = head;

        /**
         * checks if there are more elements to iterate over
         * 
         * @return true if there are more elements, false if otherwise
         */
        @Override
        public boolean hasNext() {
            return nextToReturn != null;
        }

        /**
         * returns the next element of iteration
         * @return the next element in iteration
         * @throws NoSuchElementException if the end of list is reached
         */
        @Override
        public E next() throws NoSuchElementException {
            if (nextToReturn == null )
                throw new NoSuchElementException("the end of the list reached");
            E tmp = nextToReturn.data;
            nextToReturn = nextToReturn.next;
            return tmp;
        }

    }


}

