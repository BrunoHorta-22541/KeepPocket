package pt.estig.twdm.pdm.keep_pocket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CategoryDetails extends AppCompatActivity {
    public static final String KEY_ID = "categoryId";

    private long userId;
    private TextView categoryName;
    private long categoryId;
    private int categoryLimit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);
        this.categoryId = getIntent().getExtras().getLong(KEY_ID);
        Category category = Database.getInstance(this).getcategoryDAO().getById(categoryId);
        Session activeSession = SessionManager.getActiveSession(this);
        userId = activeSession.getUserid();
        categoryName = findViewById(R.id.categoryNameEditText2);
        this.categoryName.setText(category.getCategoryName());

    }



    public void updateCategory(View view) {
        String nameCategory = this.categoryName.getText().toString();

        Category category = new Category(categoryId, nameCategory, categoryLimit, userId);
        Database.getInstance(this).getcategoryDAO().update(category);

        finish();
    }
}