//import java.io.Serializable;

public class Game implements VG {
    String title;
    String system;
    int plyrs;
    String medium;


    public Game(String title, String system, int plyrs, String medium) {
        this.title = title;
        this.system = system;
        this.plyrs = plyrs;
        this.medium = medium;
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public String getSystem() {
        return medium;
    }

    @Override
    public int players() {
        return plyrs;
    }

    public String csvStr(){
        String strplayers=String.valueOf(plyrs);
        //Returns a string of separated values, the line break is to separate each game in the csv
        return title +","+system+","+ strplayers +"," + medium+"\n";
    }
}
