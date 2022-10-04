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

public class LimitActivity extends AppCompatActivity implements LimitAdapter.LimitAdapterEventListener {

    private LimitAdapter adapter;
    private long userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limit);
        RecyclerView recyclerViewLimit = findViewById(R.id.recyclerViewLimit);
        this.adapter = new LimitAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewLimit.setAdapter(adapter);
        recyclerViewLimit.setLayoutManager(layoutManager);
    }
    @Override
    protected void onResume() {
        super.onResume();
        this.updateCategoryList();
    }
    public void updateCategoryList(){

        Session activeSession = SessionManager.getActiveSession(this);
        userId = activeSession.getUserid();
        List<Category> categoryList = Database.getInstance(this).getcategoryDAO().getUserCategoryLimit(userId);
        this.adapter.updateCategoryList(categoryList);
    }


    public void addLimit(View view){
        Intent intent = new Intent(this, AddLimit.class);
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



    @Override
    public void onLimitClicked(long categoryId) {
            Intent intent = new Intent(this,LimitDetails.class);
            intent.putExtra(LimitDetails.KEY_ID, categoryId);
            startActivity(intent);
            finish();

    }

    @Override
    public void onLimitLongClicked(long categoryId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Delete Limit?");
        builder.setMessage("Do you really want to delete this Limit?");

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
                Category categoryname = Database.getInstance(LimitActivity.this).getcategoryDAO().getById(categoryId);
                Category category = new Category(categoryId,categoryname.getCategoryName(),0,userId);
                Database.getInstance(LimitActivity.this).getcategoryDAO().update(category);
                LimitActivity.this.updateCategoryList();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
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
}
