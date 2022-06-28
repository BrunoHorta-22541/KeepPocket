package pt.estig.twdm.pdm.keep_pocket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LimitActivity extends AppCompatActivity implements LimitAdapter.LimitAdapterEventListener {

    private LimitAdapter limitAdapter;
    private long userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limit);
        RecyclerView recyclerViewLimit = findViewById(R.id.recyclerViewLimit);
        this.limitAdapter = new LimitAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewLimit.setAdapter(limitAdapter);
        recyclerViewLimit.setLayoutManager(layoutManager);
    }
    public void addLimit(View view){
        Intent intent = new Intent(this, AddLimit.class);
        startActivity(intent);
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



    @Override
    public void onLimitClicked(long categoryId) {

    }

    @Override
    public void onLimitLongClicked(long chatsId) {

    }
}