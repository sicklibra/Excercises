import javax.swing.border.TitledBorder;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
/*This is the text based version of serialize for proof of concept*/


public class Serialize {
    /*This Method takes all of the games from the to string method of each game and turns it into 1 large string which
    * includes the line breaks sent over from the object method. Each game is separated by line in the csv.*/
    public static void save(ArrayList<Game> games) {
        Path filePath = Paths.get("gamelog.csv");
        int counter = 0;
        //Creates a single string to write into file
        String bigString="";
        try {//This trys to run the loop
            for (Game x : games) {
                bigString=bigString+x.csvStr();
                    counter++;
            }
        }
        //if loop run fails
        catch (Exception e) {
            System.out.println("Something went wrong... Good luck");
        } finally {
            try{
                Files.writeString(filePath, bigString);
            }
            catch(IOException e){
                e.printStackTrace();
            }
            System.out.println("You have successfully written " + counter + " games into the system.");
        }
    }
/*This function reads everything from the CSV and re-maps it to objects and repopulates the games array list
* from the last point that was saved. it does this by reading each line into a list and then separating the
* values by commas and recreates each game and adds it to the list.*/
    public static ArrayList<Game> deserialize(ArrayList<Game> games) {
        String title;
        String system;
        int numplayers;
        String format;
        try{
            List<String> lines = Files.readAllLines(Paths.get("gamelog.csv"), StandardCharsets.UTF_8);
            for (String line : lines) {
                String[] split = line.split(",");
                title = split[0];
                system = split[1];
                numplayers = Integer.parseInt(split[2]);
                format = split[3];
                games.add(new Game(title, system, numplayers, format));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return games;
    }
    /*This method was separated from main in an effort to simplify main and streamline the process. It takes user input
    * and creates games from it. separating it also allowed me to easily stop the function if the title input was
    * unacceptable. */
    public static ArrayList<Game> entry(ArrayList<Game> games){
        Scanner scnr =new Scanner(System.in);
        String title;
        String system;
        int numPlayers;
        String format;
        System.out.println("What is the title of the game?");
        title=scnr.nextLine();
        //This clause kicks back to the main function if no title is entered.
        if(title.isEmpty()){
            System.out.println("The game must have a title! \n Try again!");
            return games;
        }
        System.out.println("What System is the game for?");
        system=scnr.nextLine();
        if(system.isEmpty()){
            system= "Unknown";
        }
        System.out.println("How many players can play at once?");
        numPlayers=scnr.nextInt();
        if (numPlayers<0){
            numPlayers=-1;
        }
        System.out.println("What format is the game?(Disk, Cartridge, Digital)");
        format=scnr.next();
        if (format.isEmpty()){
            format="Unknown";
        }
        Game newgame = new Game(title,system,numPlayers,format);
        games.add(newgame);
        return games;
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String cont = "y";
        ArrayList<Game> games = new ArrayList<Game>();
        //Pulls from the CSV to pick up where We last left off.
        deserialize(games);
        //This allows for the repetitive entry of games to be saved as long as the user continues to enter "y".
        while (cont.equalsIgnoreCase("y")) {
            System.out.println("Would you like to enter a new game to the Database? y/n");
            try {
                cont = scnr.next();
                if (!(cont.equalsIgnoreCase("y")) && !(cont.equalsIgnoreCase("n"))) {
                    throw new InputMismatchException();
                } else if (cont.equalsIgnoreCase("y")) {
                    games = entry(games);
                }


            } catch (InputMismatchException e) {
                System.out.println("Please enter Y or N.");
                continue;
            }
        }
        System.out.println("Would you like to save the games you have entered?(y/n)");
        cont=scnr.next();
        if (cont.equalsIgnoreCase("y")){
            save(games);
        }
    }
}