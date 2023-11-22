package project2;
import java.util.Date;
/**
 * Represents a single log in session
 * @author Imani Gomez
 *
 */
public class Session {
    //create variables
    private Record loginRecord;
    private Record logoutRecord;

    /**
     * default constructor
     */
    public Session(){

    }
    
    /**
     * creates a session object based on the login and logout records
     * @param login a record object representing the start of a session
     * @param logout a record object representing the end of a session
     */
    public Session(Record login, Record logout){
    	//provide the two-parameter constructor that constructs a Session object based on a login record and logout record:
        if(login == null){
            throw new IllegalArgumentException("Login record cannot be null.");
        }
        //nested if statements if the logout is not null
        if(logout!=null) {
        	if(!login.getUsername().equals(logout.getUsername()) || login.getTerminal() != logout.getTerminal()){
                throw new IllegalArgumentException("Invalid login and logout records. They do not match.");
            }
            if(logout.getTime() != null && login.getTime().compareTo(logout.getTime())>0){
                throw new IllegalArgumentException("Login time cannot be greater than logout time.");
            }
        }
        

        this.loginRecord = login;
        this.logoutRecord = logout;
    }
    	

    /**
     * retrieves the terminal from the session
     * @return the location
     */
    public int getTerminal(){
        return this.loginRecord.getTerminal();
    }

    /**
     * retrieves the login time from the session
     * @return the login time
     */
    public Date getLoginTime(){
        return this.loginRecord.getTime();
    }

    /**
     * retrieves the logout time from the session
     * @return the logout time
     */
    public Date getLogoutTime(){
    	 if(logoutRecord!=null) {  
    		 return this.logoutRecord.getTime();	
    	}else {
    		return null;
    	}
    }

    /**
     * retrieves the user name from the session
     * @return the user name
     */
    public String getUsername(){
        return this.loginRecord.getUsername();
    }

    /**
     * retrieves the duration from the session
     * @return the duration
     */
    public long getDuration(){
        //returns the number of milliseconds elapsed between the login time and logout time, or -1 if the session is still active;
        if(logoutRecord != null){
            return logoutRecord.getTime().getTime() - loginRecord.getTime().getTime();
        }else{
            return -1;
        }

    }

 /**
  * Returns creates string of the session object
  * @return a string represetation of a session object
  */
    public String toString(){
        String duration;
        String logoutTime;
        String loginTime;

        if(logoutRecord == null){
        	//if logout is null, user is still active
            duration = "active session";
            logoutTime = "still logged in";
        }else{
        	//duration calculations
            long milliseconds = getDuration();
            long seconds = milliseconds / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;
            
            //add all the calculations up
            duration = days + " days, " + (hours % 24) + " hours" +  ", " + " minutes, " + (seconds % 60) + " seconds";
            loginTime = loginRecord.getTime().toString();
            logoutTime = logoutRecord.getTime().toString();
        }

        //create and return the string
        return getUsername() + ", terminal" + getTerminal() + ", duration " + duration 
            + "/n"  + "logged in: " + getLoginTime().toString() 
            + "/n" + "logged out: " + getLogoutTime().toString();
    }
}
