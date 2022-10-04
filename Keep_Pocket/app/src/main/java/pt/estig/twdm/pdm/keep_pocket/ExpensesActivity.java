package pt.estig.twdm.pdm.keep_pocket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class ExpensesActivity extends AppCompatActivity implements ExpensesAdapter.ExpensesAdapterEventListener {
    private ExpensesAdapter adapter;
    private long userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewExpenses);
        this.adapter = new ExpensesAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        Session activeSession = SessionManager.getActiveSession(this);
        userId = activeSession.getUserid();
    }

    protected void onResume() {
        super.onResume();
        this.updateIncomeList();
    }

    @Override
    public void onExpenseClicked(long movementsId) {
        Intent intent = new Intent(this, ExpenseDetails.class);
        intent.putExtra(ExpenseDetails.KEY_ID, movementsId);
        startActivity(intent);
    }

    @Override
    public void onExpenseLongClicked(long movementsId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Delete Expense?");
        builder.setMessage("Do you really want to delete this Expense?");

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
                Movements movements = Database.getInstance(ExpensesActivity.this).getmovementsDAO().getById(movementsId);
                Database.getInstance(ExpensesActivity.this).getmovementsDAO().delete(movements);
                ExpensesActivity.this.updateIncomeList();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void updateIncomeList(){
        List<Movements> movementsList = Database.getInstance(this).getmovementsDAO().getExpense(this.userId);
        this.adapter.updateIncomeList(movementsList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                // código do logout
                this.logout();
                return true;
            case R.id.settings:
                // Abrir Activity Settings
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void logout() {
        SessionManager.clearSession(this);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void toExpenses(View view) {
        Intent intent= new Intent (this, ExpensesActivity.class);
        startActivity(intent);
        finish();
    }

    public void toCategory(View view) {
        Intent intent= new Intent (this, CategoryActivity.class);
        startActivity(intent);
        finish();
    }

    public void toIncome(View view) {
        Intent intent= new Intent (this, IncomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void toLimit(View view) {
        Intent intent= new Intent (this, LimitActivity.class);
        startActivity(intent);
        finish();
    }

    public void tomain(View view) {
        Intent intent= new Intent (this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    public void saveExpense(View view) {
        Intent intent= new Intent (this, AddExpense.class);
        startActivity(intent);
        finish();
    }
}