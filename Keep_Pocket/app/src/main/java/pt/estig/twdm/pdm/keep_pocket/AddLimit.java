package pt.estig.twdm.pdm.keep_pocket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddLimit extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinnerLimit;
    private EditText editTextLimit;
    private String itemSelected;
    ArrayAdapter<String> spinnerAdapter;
    private List<String> categoryList;
    private long userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_limit);
        Session activeSession = SessionManager.getActiveSession(this);
        userId = activeSession.getUserid();
        this.editTextLimit = findViewById(R.id.editTextLimitValue);
        this.spinnerLimit = findViewById(R.id.spinnerLimit);
        categoryList = Database.getInstance(this).getcategoryDAO().getUserCategoryName(userId);

        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<String>());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLimit.setAdapter(spinnerAdapter);
        spinnerAdapter.addAll(categoryList);
        spinnerAdapter.notifyDataSetChanged();
        spinnerLimit.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        itemSelected = (String)adapterView.getItemAtPosition(i);
        Toast.makeText(adapterView.getContext(),
                "OnItemSelectedListener : " + adapterView.getItemAtPosition(i).toString(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(this, "Nenhum item selecionado", Toast.LENGTH_SHORT).show();
    }

    public void saveLimit(View view) {

        String limitValueString = this.editTextLimit.getText().toString();
        int limitValueInt = Integer.parseInt(limitValueString);

        Category category = Database.getInstance(this).getcategoryDAO().getCategoryByName(userId,itemSelected);

        Category categoryUpdate = new Category(category.getIdCategory(),category.getCategoryName(),limitValueInt,userId);
        Database.getInstance(this).getcategoryDAO().update(categoryUpdate);

        finish();

    }

    public void previous(View view) {
        Intent intent = new Intent(this,LimitActivity.class);
        startActivity(intent);
        finish();
    }
}