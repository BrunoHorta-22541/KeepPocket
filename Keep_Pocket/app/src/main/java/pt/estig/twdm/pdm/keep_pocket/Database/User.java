package pt.estig.twdm.pdm.keep_pocket.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private long idUser;
    private String userName;
    private String email;
    private String password;

    public User(long idUser, String userName, String email, String password){
        this.idUser = idUser;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return idUser;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
