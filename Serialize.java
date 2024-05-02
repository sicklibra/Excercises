import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Serialize {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String title;
        String system;
        int numPlayers;
        String format;
        String cont="y";
        ArrayList<Game> games = new ArrayList<Game>();

        while(cont.equalsIgnoreCase("y")){
           System.out.println("Would you like to enter a new game to the Database? y/n");
           try{
            cont=scnr.next();
            if (!(cont.equalsIgnoreCase("y"))&& !(cont.equalsIgnoreCase("n"))){
                throw(InputMismatchException x);
            }
            else if(cont.equalsIgnoreCase("y"))){
                System.out.println("What is the title?");
                title=scnr.nextLine();
                System.out.println("What system is the game on?");
                system= scnr.nextLine();
                System.out.println("How Many players does the game support?");
                numPlayers=scnr.nextInt();
                System.out.println("What medium is the game?(disk, cartridte, digithal)");
                format=scnr.nextLine();
                Game thisGame= new Game(title, system,numPlayers,format);

                
            }
            
           } 
           else{break;}
           catch(InputMismatchException e){
            System.out.println("Please enter Y or N.");
            continue;
           }
        }
        
    }
}