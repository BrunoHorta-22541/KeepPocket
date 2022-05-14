package pt.estig.twdm.pdm.keep_pocket.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Utilizador {

    @PrimaryKey(autoGenerate = true)
    private long id_utilizador;
    private String nome_utilizador;
    private String email;
    private String password;

    public Utilizador(long id_utilizador , String nome_utilizador, String email, String password){
        this.id_utilizador = id_utilizador;
        this.nome_utilizador = nome_utilizador;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id_utilizador;
    }

    public String getNome_utilizador() {
        return nome_utilizador;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
