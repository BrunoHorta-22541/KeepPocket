package pt.estig.twdm.pdm.keep_pocket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;


import java.util.List;

public class LimitDetails extends AppCompatActivity {
    public static final String KEY_ID = "limitId";
    private Category categoryList;

    private long limitId;
    private long userId;
    private EditText valueLimit;

    private String categoryName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limit_details);

        this.limitId = getIntent().getExtras().getLong(KEY_ID);
        Session activeSession = SessionManager.getActiveSession(this);
        userId = activeSession.getUserid();
        this.valueLimit = findViewById(R.id.editTextLimitDetailsValue);
        this.categoryList = Database.getInstance(this).getcategoryDAO().getById(limitId);
        this.categoryName = categoryList.getCategoryName();
        this.valueLimit.setText(Integer.toString(categoryList.getLimit()));
    }


    public void updateLimit(View view) {
        String limitValueString = this.valueLimit.getText().toString();
        int limitValueInt = Integer.parseInt(limitValueString);
        Category category = new Category(limitId,categoryName,limitValueInt,userId);
        Database.getInstance(this).getcategoryDAO().update(category);

        finish();
    }

    public void previous(View view) {
        Intent intent = new Intent(this,LimitDetails.class);
        startActivity(intent);
        finish();
    }
}