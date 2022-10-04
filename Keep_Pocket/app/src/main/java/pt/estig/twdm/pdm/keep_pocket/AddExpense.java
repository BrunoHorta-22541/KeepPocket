package pt.estig.twdm.pdm.keep_pocket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddExpense extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    private List<String> categoryList;
    private long userId;
    private EditText descriptionExpense;
    private EditText valueExpense;
    private Spinner spinner;
    private String itemSelected;
    ArrayAdapter<String> spinnerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
        Session activeSession = SessionManager.getActiveSession(this);
        userId = activeSession.getUserid();
        this.descriptionExpense = findViewById(R.id.editTextExpenseDescription);
        this.spinner = findViewById(R.id.expenseCategorySpinner);
        this.valueExpense = findViewById(R.id.editTextExpenseValue);
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
    public void saveExpense(View view) {
        String description = this.descriptionExpense.getText().toString();
        String valueExpenseString = this.valueExpense.getText().toString();
        int valueExpenseInt = Integer.parseInt(valueExpenseString);
        Category category = Database.getInstance(this).getcategoryDAO().getCategoryByName(userId, itemSelected);
        int valueExpenseNegative= valueExpenseInt * (-1);
        Movements movements = new Movements(0, userId, category.getIdCategory(),valueExpenseNegative, description, System.currentTimeMillis());
        Database.getInstance(this).getmovementsDAO().insert(movements);

        Intent intent = new Intent(this,ExpensesActivity.class);
        startActivity(intent);

        finish();
    }

    public void previous(View view) {
        Intent intent = new Intent(this,ExpensesActivity.class);
        startActivity(intent);
        finish();
    }
}