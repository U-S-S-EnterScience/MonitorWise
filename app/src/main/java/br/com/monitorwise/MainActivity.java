package br.com.monitorwise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import br.com.monitorwise.Dashboard.DashboardActivity;
import br.com.monitorwise.Monitor.MonitorActivity;

import static br.com.monitorwise.RegisterActivity.hideKeyboardFrom;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRegisterScreen = findViewById(R.id.button_sign_up);
        Button btnLogin = findViewById(R.id.button_login);
        EditText edtPassword = findViewById(R.id.edit_text_password);
        EditText edtEmail = findViewById(R.id.edit_text_login);
        CheckBox cbShowPassword = findViewById(R.id.check_box_show_password_login);

        // Quando clica na check box mostra e esconde a senha
        cbShowPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else{
                edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });


        // Quando clicar no botao de login -> valida o usuario, esconde o teclado e redireciona o usuário para tela principal
        btnLogin.setOnClickListener(v -> {
            hideKeyboardFrom(MainActivity.this, btnLogin);
            startActivity(new Intent(MainActivity.this, DashboardActivity.class));
        });


        // Quando clicar no btnRegisterScreen -> direciona o usuario para tela de cadastro
        btnRegisterScreen.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RegisterActivity.class)));
    }
}