package pt.estig.twdm.pdm.keep_pocket;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Movements.class,Category.class}, version = 1)
public abstract class Database extends RoomDatabase  {
        public abstract MovementsDAO getmovementsDAO();
        public abstract CategoryDAO getcategoryDAO();
        private static Database INSTANCE;
        public static Database getInstance(Context context){
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        Database.class, "KeePocketDatabase").
                        allowMainThreadQueries().build();
            }
            return INSTANCE;
        }

}
