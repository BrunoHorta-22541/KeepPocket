package pt.estig.twdm.pdm.keep_pocket;

public class Session {

    private String username;
    private long userid;

    public Session(String username, long userid) {
        this.username = username;
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public long getUserid() {
        return userid;
    }
}
