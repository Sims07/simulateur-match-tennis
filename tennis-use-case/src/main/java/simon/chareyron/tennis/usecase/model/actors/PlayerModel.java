package simon.chareyron.tennis.usecase.model.actors;

public class PlayerModel {

    private String name;
    private String surname;
    private int rank;
    private String nationality;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public PlayerModel withName(String name) {
        this.name = name;
        return this;
    }

    public PlayerModel withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PlayerModel withRank(int rank) {
        this.rank = rank;
        return this;
    }

    public PlayerModel withNationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

}
