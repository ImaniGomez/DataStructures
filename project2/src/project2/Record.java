package project2;
import java.util.Date;

/*
 * Represents the individual records from the input file
 * 
 * @author Imani Gomez
 */
public class Record {

	//initialize variables
    int terminal;
    boolean login;
    boolean logout; 
    String username;
    Date time;


    //should provide the four-parameter constructor that validates the information and creates the requested Record object
    /**
     * creates a new record object 
     * @param terminal record integer
     * @param login boolean value if user has or hasnt logged in
     * @param username string value of user's user name
     * @param time date value of the users log in time
     */
    public Record (int terminal, boolean login, String username, Date time){
    	//set all
        this.terminal = terminal;
        this.login = login;
        this.username = username;
        this.time = time;

        //If this constructor is called with an invalid value for the terminal number, it should throw an instance of 
        //IllegalArgumentException with an appropriate message

        if(terminal <= 0){
            throw new IllegalArgumentException("Terminal must be positive");
        }
    }


    //should provide several getter methods that return the values stored in the object: 
    
    /**
    * retrieves the terminal from a record
    * @return the terminal
    */
    public int getTerminal(){
        return terminal;
    }

    /**
     * retrieves the login from the record
     * @return the login
     */
    public boolean isLogin(){
        return login;
    }

    /**
     * retrieves the logout from the record
     * @return the logout
     */
    public boolean isLogout(){
    	if(isLogin() == false) {
    		return false;
    		
    	}
        return logout;
    }

    /**
     * retrieves the user name from the record
     * @return the user name
     */
    public String getUsername(){
        return username;
    }

    /**
     * retrieves the time from the record
     * @return the time
     */
    public Date getTime(){
        return time;
    }
}