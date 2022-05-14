package pt.estig.twdm.pdm.keep_pocket.Database;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Limites {

    @PrimaryKey(autoGenerate = true)
    private long id_limites;
    private long id_categoria;
    private long id_utilizador;
    private int valor_limite;

    public Limites(long id_limites, long id_categoria, long id_utilizador, int valor_limite){
        this.id_limites= id_limites;
        this.id_categoria= id_categoria;
        this.id_utilizador= id_utilizador;
        this.valor_limite= valor_limite;
    }

    public long getId_limites() {
        return id_limites;
    }

    public long getId_categoria() {
        return id_categoria;
    }

    public long getId_utilizador() {
        return id_utilizador;
    }

    public int getValor_limite() {
        return valor_limite;
    }
}
