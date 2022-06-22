package pt.estig.twdm.pdm.keep_pocket;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


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
}