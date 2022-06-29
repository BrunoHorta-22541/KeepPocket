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

public class ExpenseDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String KEY_ID = "movementId";
    private List<String> categoryList;
    private long expenseMovementId;
    private long userId;
    private EditText descriptionExpense;
    private EditText valueExpense;
    private Spinner spinner;
    private String itemSelected;
    private Movements movements;
    private long categoryId;
    private String spinnerStringValue;
    ArrayAdapter<String> spinnerAdapter;
    private long originalInsertDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_details);

        this.expenseMovementId = getIntent().getExtras().getLong(KEY_ID);
        Session activeSession = SessionManager.getActiveSession(this);
        userId = activeSession.getUserid();
        this.descriptionExpense = findViewById(R.id.editTextExpenseDescriptionDetails);
        this.spinner = findViewById(R.id.expenseDetailsCategorySpinner);
        this.valueExpense = findViewById(R.id.editTextExpenseDetailsValue);

        categoryList = Database.getInstance(this).getcategoryDAO().getUserCategoryName(userId);
        movements = Database.getInstance(this).getmovementsDAO().getById(expenseMovementId);
        categoryId = movements.getIdCategory();
        Category category = Database.getInstance(this).getcategoryDAO().getById(categoryId);
        spinnerStringValue = category.getCategoryName();
        this.descriptionExpense.setText(movements.getDescription());
        int positive = movements.getValue() * (-1);
        String stringValueExpenses = Integer.toString(positive);
        this.valueExpense.setText(stringValueExpenses);
        this.originalInsertDate = movements.getMovementsDate();


        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new ArrayList<String>());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinnerAdapter.addAll(categoryList);
        spinnerAdapter.notifyDataSetChanged();
        spinner.setSelection(getIndex(spinner, spinnerStringValue));
        spinner.setOnItemSelectedListener(this);
    }
    private int getIndex(Spinner spinner, String spinnerStringValue){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(spinnerStringValue)){
                return i;
            }
        }

        return 0;

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
    public void updateExpense(View view) {
        String description = this.descriptionExpense.getText().toString();
        String valueExpenseString = this.valueExpense.getText().toString();
        int valueExpenseInt = Integer.parseInt(valueExpenseString);
        int valueExpenseNegative= valueExpenseInt * (-1);
        Category category = Database.getInstance(this).getcategoryDAO().getCategoryByName(userId, itemSelected);
        Movements movements = new Movements(this.movements.getIdMovements(), userId, category.getIdCategory(),valueExpenseNegative, description, originalInsertDate);
        Database.getInstance(this).getmovementsDAO().update(movements);

        finish();
    }

    public void previous(View view) {
        Intent intent = new Intent(this,ExpensesActivity.class);
        startActivity(intent);
        finish();
    }
}