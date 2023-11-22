package project3;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Iterator;

/*
 * This class stores all Record Objects, implmenting the first log in session and the last log in session
 * 
 * @author Imani Gomez
 */

public class RecordList extends SortedLinkedList<Record>{

    /**
     * Constructs a new record list
     */
    public RecordList(){
        super();
    }

    /**
     * Returns the first log in session for a specified user
     * @param user String representing the user's username
     * @return the first log in session for the user
     * @throws IllegalArgumentException when specified user is null
     */
    public Session getFirstSession(String user) throws IllegalArgumentException{

        //check if the provided user is null or empty
        if (user == null || user.isEmpty()) {
            throw new IllegalArgumentException("Invalid argument: user cannot be null or empty");
        }
    
        Iterator<Record> recordIterator = iterator(); // Assuming there's a getIterator() method in your SortedLinkedList class
    
        //iterate through records to find the first login
        while (recordIterator.hasNext()) {
            Record record = recordIterator.next();
            if (record.getUsername().equals(user) && record.isLogin()) {
                // Found the first login session for the user
                return new Session(record, null);
            }
        }
    
        throw new NoSuchElementException("User not found in the record list");
    }


    /**
     * Returns the last log in session for a user
     * @param user
     * @return
     * @throws IllegalArgumentException
     */
    public Session getLastSession(String user) throws IllegalArgumentException{

            if (user == null || user.isEmpty()) {
                throw new IllegalArgumentException("Invalid argument: user cannot be null or empty");
            }
        
            Iterator<Record> recordIterator = iterator(); // Assuming there's a getIterator() method in your SortedLinkedList class
            Record lastLoginRecord = null;
            Record lastLogoutRecord = null;
        
            //iterator through records to find last login
            while (recordIterator.hasNext()) {
                Record record = recordIterator.next();
                if (record.getUsername().equals(user) && record.isLogin()) {
                    // Update the last login session for the user
                    lastLoginRecord = record;
                }else if (record.getUsername().equals(user) && record.isLogout()) {
                    // Update the last logout record for the user
                    lastLogoutRecord = record;
                }
            }
        
            if (lastLogoutRecord != null) {
                // Found the last logout record for the user
                return new Session(lastLoginRecord, lastLogoutRecord);
            }
        
            // Return null or throw an appropriate exception if no valid session is found
            throw new NoSuchElementException("User not found"); // You can change this to throw an exception if desired
        
    }
    

    /**
     * Returns the total login time for a user
     * @param user String representation the username
     * @return the login time for the user 
     * @throws IllegalArgumentException when specified user is null or empty
     */
    public long getTotalTime(String user) throws IllegalArgumentException{

        if (user == null || user.isEmpty()) {
            throw new IllegalArgumentException("Invalid argument: user cannot be null or empty");
        }
        
        long totalTime = 0;
        boolean counting = false;
        
        Iterator<Record> recordIterator = iterator(); // Assuming there's a getIterator() method in your SortedLinkedList class
        //iterate through records to find the total login time
        while (recordIterator.hasNext()) {
            Record record = recordIterator.next();
        
            if (record.getUsername().equals(user) && record.isLogin()) {
                // Start counting for this user's session
                counting = true;
            } else if (record.getUsername().equals(user) && record.isLogout()) {
                if (counting) {
                    // This user has logged out, add the session duration to total time
                    totalTime += new Session(record, null).getDuration();
                }
                counting = false;
            }
        }
        
        return totalTime;
    }


    /**
     * gets the login sessions for a user and returns them as a sorted list
     * @param user String representation the username
     * @return sorted list of login sessios for the user
     * @throws NoSuchElementException if user is not found in the record list
     * @throws IllegalArgumentException when specified user is null or empty
     */
    public SortedLinkedList<Session> getAllSessions(String user) throws NoSuchElementException {
        //null check
        if (user == null || user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        SortedLinkedList<Session> userSessions = new SortedLinkedList<>();
        Iterator<Record> recordIterator = iterator();
        Record lastLoginRecord = null;

        //iterate through the records to find and store user's login sessions
        while (recordIterator.hasNext()) {
            Record record = recordIterator.next();

            if (record.getUsername().equals(user)) {
                if (record.isLogin()) {
                    // Store the last login record for this user's session
                    lastLoginRecord = record;
                } else if (record.isLogout()) {
                    // Found a logout record, create a session and add it to the list
                    userSessions.add(new Session(lastLoginRecord, record));
                }
            }
        }

        if (userSessions.size() == 0) {
            throw new NoSuchElementException("User not found in the record list");
        }

        return userSessions;
    }
}
