package pt.estig.twdm.pdm.keep_pocket.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Movements {

    @PrimaryKey(autoGenerate = true)
    private long idMovements;
    private long idUser;
    private long idCategory;
    private int value;
    private String description;
    private String movementsDate;


    public Movements(long idMovements, long idUser, long idCategory, int value, String description, String movementsDate){

        this.idMovements = idMovements;
        this.idUser = idUser;
        this.idCategory = idCategory;
        this.value = value;
        this.description=description;
        this.movementsDate = movementsDate;

    }

    public long getIdMovements() {
        return idMovements;
    }

    public long getIdUser() {
        return idUser;
    }

    public long getId_categoria() {
        return idCategory;
    }

    public int getValue() {
        return value;
    }

    public String getDescricao() {
        return description;
    }

    public String getMovementsDate() {
        return movementsDate;
    }
}
