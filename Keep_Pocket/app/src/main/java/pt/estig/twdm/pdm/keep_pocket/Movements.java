package pt.estig.twdm.pdm.keep_pocket;

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
    private long movementsDate;


    public Movements(long idMovements, long idUser, long idCategory, int value, String description, long movementsDate){

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

    public long getIdCategory() {
        return idCategory;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public long getMovementsDate() {
        return movementsDate;
    }

}
