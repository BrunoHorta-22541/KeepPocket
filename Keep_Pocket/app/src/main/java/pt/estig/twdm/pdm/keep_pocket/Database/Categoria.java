package pt.estig.twdm.pdm.keep_pocket.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Categoria {

    @PrimaryKey(autoGenerate = true)
    private long id_categoria;
    private String nome_categoria;

    public Categoria(long id_categoria, String nome_categoria){
        this.id_categoria= id_categoria;
        this.nome_categoria= nome_categoria;

    }

    public long getId_categoria() {
        return id_categoria;
    }

    public String getNome_categoria() {
        return nome_categoria;
    }
}
