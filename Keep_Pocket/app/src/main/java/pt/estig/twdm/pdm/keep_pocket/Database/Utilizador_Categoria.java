package pt.estig.twdm.pdm.keep_pocket.Database;

import androidx.room.PrimaryKey;

public class Utilizador_Categoria {

    @PrimaryKey(autoGenerate = true)
    private long id_utilizador_categoria;
    private long id_utilizador;
    private long id_categoria;
    private String tipo;

    public Utilizador_Categoria(long id_utilizador_categoria, long id_utilizador, long id_categoria, String tipo  ){
        this.id_utilizador_categoria= id_utilizador_categoria;
        this.id_utilizador=id_utilizador;
        this.id_categoria=id_categoria;
        this.tipo= tipo;
    }

    public long getId_utilizador_categoria() {
        return id_utilizador_categoria;
    }

    public long getId_utilizador() {
        return id_utilizador;
    }

    public long getId_categoria() {
        return id_categoria;
    }

    public String getTipo() {
        return tipo;
    }
}
