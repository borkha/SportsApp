package ModelClass;

public class RPlayer
{
    public String name, emailId, phoneNo,sport,age;

    public RPlayer(String name, String emailId, String phoneNo, String sport, String age) {
        this.name = name;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
        this.sport = sport;
        this.age = age;
    }

    public RPlayer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
