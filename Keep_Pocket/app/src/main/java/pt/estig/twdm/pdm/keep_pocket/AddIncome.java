package pt.estig.twdm.pdm.keep_pocket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddIncome extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    private List<String> categoryList;
    private long userId;
    private EditText descriptionIncome;
    private EditText valueIncome;
    private Spinner spinner;
    private String itemSelected;
    ArrayAdapter<String> spinnerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        Session activeSession = SessionManager.getActiveSession(this);
        userId = activeSession.getUserid();
        this.descriptionIncome = findViewById(R.id.editTextIncomeDescription);
        this.spinner = findViewById(R.id.incomeCategorySpinner);
        this.valueIncome = findViewById(R.id.editTextIncomeValue);
        categoryList = Database.getInstance(this).getcategoryDAO().getUserCategoryName(userId);

        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<String>());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinnerAdapter.addAll(categoryList);
        spinnerAdapter.notifyDataSetChanged();
        spinner.setOnItemSelectedListener(this);
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
    public void saveIncome(View view) {
        String description = this.descriptionIncome.getText().toString();
        String valueIncomeString = this.valueIncome.getText().toString();
        int valueIncomeInt = Integer.parseInt(valueIncomeString);
        Category category = Database.getInstance(this).getcategoryDAO().getCategoryByName(userId, itemSelected);

        Movements movements = new Movements(0, userId, category.getIdCategory(), valueIncomeInt, description, System.currentTimeMillis());
        Database.getInstance(this).getmovementsDAO().insert(movements);

        finish();
    }

    public void previous(View view) {
        Intent intent = new Intent(this,IncomeActivity.class);
        startActivity(intent);
        finish();
    }
}