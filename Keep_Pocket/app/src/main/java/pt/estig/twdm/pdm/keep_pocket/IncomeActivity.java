package pt.estig.twdm.pdm.keep_pocket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class IncomeActivity extends AppCompatActivity implements IncomeAdapter.IncomeAdapterEventListener {
    private IncomeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        RecyclerView recyclerView = findViewById(R.id.incomeRecyclerView);
        this.adapter = new IncomeAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.updateIncomeList();
    }
    public void updateIncomeList(){
        List<Movements> movementsList = Database.getInstance(this).getmovementsDAO().getAll();
        this.adapter.updateIncomeList(movementsList);
    }


    @Override
    public void onIncomeClicked(long movementsId) {
        Intent intent = new Intent(this, IncomeAdapter.class);
        startActivity(intent);
    }

    @Override
    public void onIncomeLongClicked(long movementsId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Delete Income?");
        builder.setMessage("Do you really want to delete this Income?");

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Código a ser executado quando o utilizador clica em Cancel
            }
        });
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Código a ser executado quando o utilizador clica em Delete
                Movements movements = Database.getInstance(IncomeActivity.this).getmovementsDAO().getById(movementsId);
                Database.getInstance(IncomeActivity.this).getmovementsDAO().delete(movements);
                IncomeActivity.this.updateIncomeList();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void toExpenses(View view) {
        Intent intent= new Intent (this, ExpensesActivity.class);
        startActivity(intent);
    }

    public void toCategory(View view) {
        Intent intent= new Intent (this, CategoryActivity.class);
        startActivity(intent);
    }

    public void toIncome(View view) {
        Intent intent= new Intent (this, IncomeActivity.class);
        startActivity(intent);
    }

    public void toLimit(View view) {
        Intent intent= new Intent (this, LimitActivity.class);
        startActivity(intent);
    }

    public void tomain(View view) {
        Intent intent= new Intent (this, MainActivity.class);
        startActivity(intent);
    }


}