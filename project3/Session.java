package project3;
import java.util.Date;
/**
 * This class represents a single log in session. Session classes are compared by the time associated by their login record
 * @author Imani Gomez
 */
public class Session implements Comparable<Session>{
    //should represent a single login session

    private Record loginRecord;
    private Record logoutRecord;

    /**
     * constructs a new session object
     */
    public Session(){

    }
    
    /**
     * Constructs a Session object based on a login record and a logout record (if available)
     * @param login the login record
     * @param logout the logout record (can be null if the session is still active)
     * @throws IllegalArgumentException if the login record is null or if the login time is greater than the logout time
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
     * @return the terminal
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


    /**
     * Compares two sessions based on their login times
     * 
     * @param other the other session to compare
     * @return a negative value or a positive value depending on login time
     */
    public int compareTo(Session other){
        return this.getLoginTime().compareTo(other.getLoginTime());
    }


    /**
     * Checks if this session is equal to another object. 
     * 
     * @param obj the object to compare
     * @return true is objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        //check if this object is equal to obj
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }

        Session other = (Session) obj;
        //check equality for other variables
        return loginRecord.equals(other.loginRecord) && logoutRecord.equals(other.logoutRecord);
    }
}