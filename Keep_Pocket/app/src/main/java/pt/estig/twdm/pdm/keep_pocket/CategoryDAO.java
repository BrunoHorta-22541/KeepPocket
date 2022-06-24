package pt.estig.twdm.pdm.keep_pocket;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CategoryDAO {
    @Query("SELECT * FROM Category")
    List<Category> getAll();

    @Query("SELECT * FROM Category WHERE idCategory = :categoryId")
    Category getById(long categoryId);

    @Query("SELECT * FROM Category WHERE idUser = :userId")
    List<Category> getUserCategory(long userId);


    @Delete
    void delete(Category category);

    @Update
    void update(Category category);


    @Insert
    void insert(Category category);
}
