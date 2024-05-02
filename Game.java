//import java.io.Serializable;

public class Game implements VG {
    String title;
    String system;
    int plyrs;
    String medium;

    public void Game(String title, String system, int plyrs, String medium) {
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

    public String csvTxt(){
        String strplayers=String.valueOf(plyrs);
        return title +","+system+","+ strplayers +"," + medium;
    }
}
