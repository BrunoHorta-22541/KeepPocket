package pt.estig.twdm.pdm.keep_pocket.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Movimentos {

    @PrimaryKey(autoGenerate = true)
    private long id_movimentos;
    private long id_utilizador;
    private long id_categoria;
    private int valor;
    private String descricao;
    private String data_movimentos;


    public Movimentos(long id_movimentos,long id_utilizador, long id_categoria, int valor, String descricao, String data_movimentos){

        this.id_movimentos = id_movimentos;
        this.id_utilizador= id_utilizador;
        this.id_categoria= id_categoria;
        this.valor= valor;
        this.descricao=descricao;
        this.data_movimentos= data_movimentos;

    }

    public long getId_movimentos() {
        return id_movimentos;
    }

    public long getId_utilizador() {
        return id_utilizador;
    }

    public long getId_categoria() {
        return id_categoria;
    }

    public int getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getData_movimentos() {
        return data_movimentos;
    }
}
