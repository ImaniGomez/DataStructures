package project3;
//import java.io.File;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Date;

/**
 * The main program for processing login stats from a file
 * allows users to retrieve first login session, last login session, 
 * all login sessions and the total login time for a specified user
 *
 * @author Imani Gomez
 */
public class LoginStats {

    /**
     * The main method of the program
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //no command line arguments specified, print an error and terminate
        if(args.length == 0){
        	//empty
            System.err.println("Program did not generate any output");
            System.exit(1);
        }
        File newRecords = new File(args[0]);

        //if the file doesnt exist
        if(!newRecords.exists()){
            System.err.println("Program did not generate any output");
            System.exit(1);
        }
        //file is unreadable
        if(!newRecords.canRead()){
            System.err.println("Program did not generate any output");
            System.exit(1);
        }

        Scanner input = null;
        //try to create a scanner to read from the input file
        try{
            input = new Scanner(newRecords);
        }catch (FileNotFoundException e){
            System.out.println("Program did not generate any output");
            System.exit(1);
        }

        //Create a list to store the login records
        RecordList list = new RecordList();
        String line = null;
        Scanner parseLine = null;

        //print user instructions
        System.out.println("Welcome to Login Stats!\n");
        System.out.println("Available commands: ");
        System.out.println("    first USERNAME  - retrieves first login session for the USER");
        System.out.println("    last USERNAME   - retrieves last login session for the USER");
        System.out.println("    all USERNAME    - retrieves all login sessions for the USER");
        System.out.println("    total USERNAME - retrieves total login time for the USER");
        System.out.println("    quit    - terminates this program");

        //create scanners to read user input
        Scanner in = new Scanner (System.in);
        String userInputString = null;
        Scanner userInput = null;

        do{
            //read user input
            userInputString = in.nextLine();
            userInput = new Scanner (userInputString);
            String command = null, keyword = null;
            boolean execute = false;

            Session matches = null;

            if(userInput.hasNext()){
                command = userInput.next();
            }

            if(userInput.hasNext()){
                keyword = userInput.next();
            }


            try{ 
                //process each command by calling the corresponding function
                if(command.equalsIgnoreCase("first") && keyword!=null){
                    matches = list.getFirstSession(keyword);
                    execute = true;
                }
                else if(command.equalsIgnoreCase("last") && keyword != null){
                    matches = list.getLastSession(keyword);
                    execute = true;

                }else if(command.equalsIgnoreCase("all") && keyword != null){
                    //retrieve and show all login sessions 
                    SortedLinkedList<Session> allSessions = list.getAllSessions(keyword);
                    for(Session session : allSessions){
                        System.out.println(session);
                    }
                }else if(command.equalsIgnoreCase("total") && keyword != null){
                    //calculate and display total login time
                    long totalTime = list.getTotalTime(keyword);
                    System.out.println("Total login time for " + keyword + "; " + totalTime + " milliseconds");
                }
            }catch(IllegalArgumentException ex){
                System.out.println("This is not a valid input");
                //continue to next iteration
                continue;
            }

            if(execute && matches == null){
                System.out.println("No matches found");
                continue;
            }

            if(execute){
                System.out.println(matches);
            }
        //continue loping until the user chooses to quit
        }while (!userInputString.equalsIgnoreCase("quit"));
            //close scanners
            in.close();
            userInput.close();
        }
    } 
