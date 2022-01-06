package ModelClass;

public class Detail
{
   private String date,gamename,lastdate,location;

    public Detail(String date, String gamename, String lastdate, String location) {
        this.date = date;
        this.gamename = gamename;
        this.lastdate = lastdate;
        this.location = location;
    }
    public Detail()
    {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
