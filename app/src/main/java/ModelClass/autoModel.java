package ModelClass;

public class autoModel
{
    private String name;
    private String GroupName;

    public autoModel(String name, String groupName) {
        this.name = name;
        GroupName = groupName;
    }

    public autoModel(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }
}
