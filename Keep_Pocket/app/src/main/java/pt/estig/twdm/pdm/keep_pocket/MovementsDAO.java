package pt.estig.twdm.pdm.keep_pocket;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MovementsDAO {
    @Query("SELECT * FROM Movements")
    List<Movements> getAll();

    @Query("SELECT * FROM Movements WHERE idMovements = :movementsId")
    Movements getById(long movementsId);

    @Query("SELECT * FROM Movements WHERE idUser = :userId AND value>0")
    List<Movements> getIncome(long userId);

    @Query("SELECT * FROM Movements WHERE idUser = :userId AND value<0")
    List<Movements> getExpense(long userId);

    @Query("SELECT SUM(value) AS totalSpent, * FROM Movements WHERE idUser = :userId GROUP BY idCategory")
    List<Movements> getTotalByUserId(long userId);

    @Delete
    void delete(Movements movements);

    @Update
    void update(Movements movements);


    @Insert
    void insert(Movements movements);
}
