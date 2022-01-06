package ModelClass;

public class Score
{
    String Firstplayer,ScoreFirstplayer,SecondPlayer,ScoreSecondplayer,TournamentName,Groupname;

    public Score(String firstplayer, String scoreFirstplayer, String secondPlayer, String scoreSecondplayer, String tournamentName, String groupname) {
        Firstplayer = firstplayer;
        ScoreFirstplayer = scoreFirstplayer;
        SecondPlayer = secondPlayer;
        ScoreSecondplayer = scoreSecondplayer;
        TournamentName = tournamentName;
        Groupname = groupname;
    }

    public Score()
    {

    }

    public String getFirstplayer() {
        return Firstplayer;
    }

    public void setFirstplayer(String firstplayer) {
        Firstplayer = firstplayer;
    }

    public String getScoreFirstplayer() {
        return ScoreFirstplayer;
    }

    public void setScoreFirstplayer(String scoreFirstplayer) {
        ScoreFirstplayer = scoreFirstplayer;
    }

    public String getSecondPlayer() {
        return SecondPlayer;
    }

    public void setSecondPlayer(String secondPlayer) {
        SecondPlayer = secondPlayer;
    }

    public String getScoreSecondplayer() {
        return ScoreSecondplayer;
    }

    public void setScoreSecondplayer(String scoreSecondplayer) {
        ScoreSecondplayer = scoreSecondplayer;
    }

    public String getTournamentName() {
        return TournamentName;
    }

    public void setTournamentName(String tournamentName) {
        TournamentName = tournamentName;
    }

    public String getGroupname() {
        return Groupname;
    }

    public void setGroupname(String groupname) {
        Groupname = groupname;
    }
}
