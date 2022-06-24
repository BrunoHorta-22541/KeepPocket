package pt.estig.twdm.pdm.keep_pocket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private CheckBox checkBoxRemeberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.editTextEmail = findViewById(R.id.editTextEmail);
        this.editTextPassword = findViewById(R.id.editTextPassword);
        this.checkBoxRemeberMe = findViewById(R.id.checkBoxRemeberMe);


        if (SessionManager.persistedSession(this)) {
            Intent intent = new Intent(this, pt.estig.twdm.pdm.keep_pocket.MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void login(View view) {
        String email = this.editTextEmail.getText().toString();
        String password = this.editTextPassword.getText().toString();

        if (email.isEmpty()) { // string.Equals("")
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_LONG).show();
            return;
        }

        User loggedInUser = LoginManager.validateUser(email, password);
        if (loggedInUser != null) {
            // user válido
            SessionManager.saveSession(this, email, checkBoxRemeberMe.isChecked(), loggedInUser.getId());
            Toast.makeText(this, "Login com sucesso", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, pt.estig.twdm.pdm.keep_pocket.MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_LONG).show();
        }

    }

    public void signup(View view) {
        startActivity(new Intent(this, SignupActivity.class));
    }
}