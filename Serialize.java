import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/*This is the text based version of serialize for proof of concept*/

//This method will write the array list to csv
public class Serialize {
    public static void save(ArrayList<Game> games) {
        Path filePath = Paths.get("gamelog.csv");
        int counter = 0;
        try {//This trys to run the loop
            for (Game x : games) {
                try {//Trys to write to csv file and counts number of successes
                    //for final statement
                    Files.write(filePath, x.csvTxt().getBytes(StandardCharsets.UTF_8));
                    counter++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //if loop run fails
        catch (Exception e) {
            System.out.println("Something went wrong... Good luck");
        } finally {
            System.out.println("You have successfully written" + counter + "games into the system.");
        }
    }

    public static void deserialize(ArrayList<Game> games) {
        Path filepath =Paths.get("gamelog.csv");
    }
    public static ArrayList<Game> entry(ArrayList<Game> games){
        Scanner scnr =new Scanner(System.in);
        String title;
        String system;
        int numPlayers;
        String format;
        System.out.println("What is the title of the game?");
        title=scnr.nextLine();
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
        format=scnr.nextLine();
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
        games=deserialize(games);

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