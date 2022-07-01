package pt.estig.twdm.pdm.keep_pocket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCategory extends AppCompatActivity {
    private EditText editTextCategoryName;
    private long userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        this.editTextCategoryName = findViewById(R.id.categoryNameEditText);
    }

    public void saveCategory(View view) {
        Session activeSession = SessionManager.getActiveSession(this);
        userId = activeSession.getUserid();
        String nameCategory = this.editTextCategoryName.getText().toString();

        Category category = new Category(0,nameCategory,0,userId);
        Database.getInstance(this).getcategoryDAO().insert(category);

        Intent intent = new Intent(this,CategoryActivity.class);
        startActivity(intent);
        finish();

    }

    public void previous(View view) {
        Intent intent = new Intent(this,CategoryActivity.class);
        startActivity(intent);
        finish();
    }
}
