public class Player {
    String name;
    double handicap;
    Player(){
        name="";
        handicap=0;
    }
    Player(String name, double handicap){
        this.name=name;
        this.handicap=handicap;
    }
    public String getName() {
        return name;
    }
    public double getHandicap() {
        return handicap;
    }
    public void setHandicap(double handicap) {
        this.handicap = handicap;
    }

    public void setName(String name) {
        this.name = name;
    }
}
