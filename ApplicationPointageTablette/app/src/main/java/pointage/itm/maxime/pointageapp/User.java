package pointage.itm.maxime.pointageapp;

public class User {
    private String ID;
    private String Heure;

    public User(String ID, String heure) {
        this.ID = ID;
        this.Heure = heure;
    }

    public String getID() {
        return ID;
    }

    public String getHeure() {
        return Heure;
    }
}
