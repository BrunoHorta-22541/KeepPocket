package pt.estig.twdm.pdm.keep_pocket;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    PieChart pieChart;
    ArrayList<PieEntry> pieEntries;

    Map<String, Integer> expensesDataDataset = new HashMap<>();
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!SessionManager.sessionExists(this)) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        Session activeSession = SessionManager.getActiveSession(this);
        TextView textViewUsername = findViewById(R.id.textViewUsername);
        textViewUsername.setText(activeSession.getUsername());


        pieChart = findViewById(R.id.pieChart);
        pieEntries = new ArrayList<>();
        fillExpensesArrayList();

        for (Map.Entry<String, Integer> entry : expensesDataDataset.entrySet()) {
            pieEntries.add(new PieEntry(entry.getValue(), entry.getKey()));
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Month Expenses");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextSize(16);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        Legend legend = pieChart.getLegend();
        legend.setTextSize(13);
        legend.setTextColor(R.color.colorPrimaryDark);
        legend.setWordWrapEnabled(true);
        pieChart.animateXY(2000, 2000);
        pieChart.invalidate();
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, Highlight h) {
                String label =((PieEntry)e).getLabel();
                int value = expensesDataDataset.get(label);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                String sales = NumberFormat.getCurrencyInstance().format(value);
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_chart_details, null);
                TextView categoryTxtView = view.findViewById(R.id.pieChartCategory);
                TextView expensesTxtView = view.findViewById(R.id.pieChartExpenses);
                categoryTxtView.setText(label);
                expensesTxtView.setText(sales);
                builder.setView(view);
                alertDialog = builder.create();
                alertDialog.show();
            }
            @Override
            public void onNothingSelected() {

            }
        });

    }

    private void fillExpensesArrayList() {
        expensesDataDataset.put("Gaming", 15);
        expensesDataDataset.put("Saúde", 55);
        expensesDataDataset.put("Luz", 60);
        expensesDataDataset.put("Água", 30);
        expensesDataDataset.put("Alimentação", 150);
    }


    public void logout() {
        SessionManager.clearSession(this);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
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