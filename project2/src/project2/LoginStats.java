package project2;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Implementation that allows user to explore log in and log out data. Opens and reads the data file, obtains user input, 
 * performs some data validation and handles errors that may occur
 * 
 * @author Imani Gomez
 */

public class LoginStats {

    public static void main(String [] args){
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
        try{
            input = new Scanner(newRecords);
        }catch (FileNotFoundException e){
            System.err.println("Program did not generate any output");
            System.exit(1);
        }

        RecordList list = new RecordList();
        String line = null;
        Scanner parseLine = null;
        
        while (input.hasNextLine()){
            try{
                line = input.nextLine();
                parseLine = new Scanner(line);
                parseLine.useDelimiter(" ");
                
                //get first number within the file
                if(parseLine.hasNext()) {
                	int terminal = Integer.parseInt(parseLine.next());
                	//if user is logged in
                	if(parseLine.hasNext()) {
                		long loginValue = Long.parseLong(parseLine.next());
                    	boolean isLogin = (loginValue == 1);
                		
                    	//get user's user name
                			if(parseLine.hasNext()) {
                				String username = parseLine.next();
                				if(parseLine.hasNext()) {
                					//creating date from the numbers
                					String dateString = parseLine.next();
                					SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                					Date time = dateFormat.parse(dateString);
                					Record records = new Record (terminal, isLogin, username, time);
                					System.out.println(records);
                					list.add(records);
                				}
                			}
                	}
                }
                
            }catch(NoSuchElementException ex){
                System.err.println(line);
                continue;
            }catch(ParseException ex) {
            	System.err.println(line);
            }
        }

        	//print out user menu
            System.out.println("Welcome to Login Stats!");
            System.out.println("Available commands: ");
            System.out.println("  first USERNAME  - retrieves first login session for the USER");
            System.out.println("  last USERNAME   - retrieves last login session for the USER");
            System.out.println("  quit            - terminates this program");

            Scanner in = new Scanner (System.in);
            String userInputString = null;
            Scanner userInput = null;

            do{
            	//take in user input
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

                //if the input is valid
                if(command == null || !(command.equalsIgnoreCase("first") || command.equalsIgnoreCase("last"))){
                    System.out.println("This is not a valid input");
                    continue;
                }

                try{ //process each command by calling the corresponding function
                    if(command.equalsIgnoreCase("first") && keyword!=null){
                        matches = list.getFirstSession(keyword);
                        execute = true;
                    }
                    else if(command.equalsIgnoreCase("last") && keyword != null){
                        matches = list.getLastSession(keyword);
                        execute = true;

                    }
                    //input not first last or quit
                }catch(IllegalArgumentException ex){
                    System.out.println("This is not a valid input");
                    continue;
                }

                if(execute && matches == null){
                    System.out.println("No matches found");
                    continue;
                }

                if(execute){
                    System.out.println(matches);
                }
            }while (!userInputString.equalsIgnoreCase("quit"));
                in.close();
                userInput.close();
            }
    } 
