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

    @Query("SELECT categoryName FROM Category WHERE idUser = :userId")
    List<String> getUserCategoryName(long userId);

    @Query("SELECT * FROM Category WHERE idUser = :userId AND categoryName = :nameCategory")
    Category getCategoryByName(long userId, String nameCategory);

    @Query("SELECT * FROM Category WHERE idUser = :userId AND `limit` > 0 ")
    List<Category> getUserCategoryLimit(long userId);


    @Delete
    void delete(Category category);

    @Update
    void update(Category category);


    @Insert
    void insert(Category category);
}
