package project2;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;

/*
 * This class stores all Record Objects, implmenting the first log in session and the last log in session
 * 
 * @author Imani Gomez
 */

public class RecordList extends ArrayList<Record>{

    public RecordList(){
        super();
    }

    /**
     * Returns the first log in session for a specified user
     * @param user String representing the user's user name
     * @return the first log in session for the user
     * @throws IllegalArgumentException when specified user is null
     */
    public Session getFirstSession(String user) throws IllegalArgumentException{

        if (user == null || user.isEmpty()) {
            throw new IllegalArgumentException("User cannot be null or empty");
        }

        //initialize first session to null
        Session firstSession = null;
        boolean userFound = false;

        for (int i = 0; i < size(); i++) {
            String name = get(i).getUsername();

            if (name.equalsIgnoreCase(user.trim())) {
            	//if user name is found
                userFound = true;

                if (get(i).isLogin()) {
                    int terminal = get(i).getTerminal();
                    Record login = get(i);

                    for (int j = i + 1; j < size(); j++) {
                    	//get the next record
                        Record record = get(j); 

                        if (record.getTerminal() == terminal && record.getUsername().equals(name)) {
                            if (!record.isLogin()) {
                            	//if the next record is a login record
                                try {
                                    firstSession = new Session(login, record);
                                } catch (IllegalArgumentException e) {
                                    // Handle the exception as needed
                                }
                            }
                            break;
                        }
                    }

                    if (userFound) {
                        break;
                    }
                }
            }
        }

        if (!userFound) {
            throw new NoSuchElementException("User not found in records");
        }

        return firstSession;
    }
    
    /**
     * The method returns the the last log in session for a specified user. If there are multiple log in sessions for one user, 
     * the last one is the latest log in time
     * @param user String representation of user's name
     * @return the last log in for a specified user
     * @throws IllegalArgumentException when specified user is null
     */
    public Session getLastSession(String user) throws IllegalArgumentException{
        if(user == null || user.isEmpty()){
            throw new IllegalArgumentException("User cannot be empty");
        }

        //initialize last session to null
        Session lastSession = null;
        Date lastLogin = new Date(0);

        for(Record record: this){
            if(record.getUsername().equals(user) && record.isLogin() && record.getTime().compareTo(lastLogin) > 0){
            	//update last log in time
                lastLogin = record.getTime();
                lastSession = new Session(record, null);
            }
        }

        if(lastSession == null){
            throw new NoSuchElementException("no login found");
        }

        return lastSession; //returning the last log in session
    }

    
}

