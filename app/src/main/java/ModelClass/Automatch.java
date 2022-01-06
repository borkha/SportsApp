package ModelClass;

public class Automatch
{
    String playerOne,vs,playerTwo , Sport;

    public Automatch(String playerOne, String vs, String playerTwo, String sport) {
        this.playerOne = playerOne;
        this.vs = vs;
        this.playerTwo = playerTwo;
        Sport = sport;
    }

    public Automatch()
    {

    }

    public String getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(String playerOne) {
        this.playerOne = playerOne;
    }

    public String getVs() {
        return vs;
    }

    public void setVs(String vs) {
        this.vs = vs;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(String playerTwo) {
        this.playerTwo = playerTwo;
    }

    public String getSport() {
        return Sport;
    }

    public void setSport(String sport) {
        Sport = sport;
    }
}
