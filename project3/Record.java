package project3;
import java.util.Date;
/**
 * This class represents the individual record from the file
 * Each record all user info
 * This class implements the comparable interface to enable sorting by timestamp
 * 
 * @author Imani Gomez
 * 
 */
public class Record implements Comparable<Record>{

    //store record information
    int terminal;
    boolean login;
    boolean logout; 
    String username;
    Date time;

    /**
     * Constructs a record object with the provided information and validates it
     * @param terminal the terminal associated with the record
     * @param login true if the record represents a login
     * @param username the username of the record
     * @param time the time of the record
     * @throws IllegalArgumentException if the provided information is invalid
     */
    public Record (int terminal, boolean login, String username, Date time){
        //initialize variables for record 
        this.terminal = terminal;
        this.login = login;
        this.username = username;
        this.time = time;

        //set logout based on login
        if(login == false){
            this.logout = false;
        }else{
            this.logout = false;
        }

        //validate terminal, username and time
        if(terminal <= 0){
            throw new IllegalArgumentException("Terminal must be positive");
        }
        if(username == null){
            throw new IllegalArgumentException("Username cannot be null");
        }

        if(time == null){
            throw new IllegalArgumentException("Time cannot be null");
        }
    }

    //Getter methods

    /**
     * gets the terminal associated with this record
     * @return terminal
     */
    public int getTerminal(){
        return terminal;
    }

    /**
     * checks if this record represents a login 
     * @return true if login is true, false if otherwise
     */
    public boolean isLogin(){
        return login;
    }

    /**
     * checks is this record represents a logout
     * @return true if its a logout, false if otherwise
     */
    public boolean isLogout(){
        return !login;
    }

    /**
     * the username associated with this record
     * @return the username
     */
    public String getUsername(){
        return username;
    }

    /**
     * the timestamp associated with this record
     * @return timestamp as date
     */
    public Date getTime(){
        return time;
    }

    /**
     * compares this record to another record based on their times
     * @param other the other record to compare
     * @return a negative num if the time is earlier, 0 if equal and 1 if later
     */
    @Override
    public int compareTo(Record other){
        return this.time.compareTo(other.time);
    }


    /**
     * Indicated whether some other object is "equal to" this record. 
     * Records are equal is they have the same login status, terminal, username and time
     * 
     * @param obj the object being compared to
     * @return true is record is equal, false if not equal
     */
    @Override
    public boolean equals(Object obj){
        //check if provided object is the same reference
        if(this == obj){
            return true;
        }

        //check if the provided object is not null
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }

        //cast the object to a record
        Record other = (Record) obj;

        //check is the required elements are equal
        return login == other.login && terminal == other.terminal && username.equals(other.username) && time.equals(other.time);
    }
}