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

public class CategoryActivity extends AppCompatActivity implements CategoryAdapter.CategoryAdapterEventListener {
    private CategoryAdapter adapter;
    private long userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        RecyclerView recyclerView = findViewById(R.id.categoryRecyclerView);
        this.adapter = new CategoryAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.updateCategoryList();
    }
    public void updateCategoryList(){

        Session activeSession = SessionManager.getActiveSession(this);
        userId = activeSession.getUserid();
        List<Category> categoryList = Database.getInstance(this).getcategoryDAO().getUserCategory(userId);
        this.adapter.updateCategoryList(categoryList);
    }
    public void onCategoryClicked(long categoryId) {
        Intent intent = new Intent(this, CategoryDetails.class);
        intent.putExtra(CategoryDetails.KEY_ID, categoryId);
        startActivity(intent);
        finish();
    }
    @Override
    public void onCategoryLongClicked(long categoryId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Delete Category?");
        builder.setMessage("Do you really want to delete this Category?");

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
                Category category = Database.getInstance(CategoryActivity.this).getcategoryDAO().getById(categoryId);
                Database.getInstance(CategoryActivity.this).getcategoryDAO().delete(category);
                CategoryActivity.this.updateCategoryList();
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

    public void addCategory(View view) {
        Intent intent= new Intent (this, AddCategory.class);
        startActivity(intent);
        finish();
    }
}