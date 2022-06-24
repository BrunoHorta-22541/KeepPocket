package pt.estig.twdm.pdm.keep_pocket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCategory extends AppCompatActivity {
    private EditText categoryName;
    private long userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        this.categoryName = findViewById(R.id.categoryNameEditText);
    }

    public void saveCategory(View view) {
        String nameCategory = this.categoryName.getText().toString();
        Session activeSession = SessionManager.getActiveSession(this);
        userId = activeSession.getUserid();
        Category category = new Category(0,nameCategory,0,userId);
        Database.getInstance(this).getcategoryDAO().insert(category);

        finish();

    }
}