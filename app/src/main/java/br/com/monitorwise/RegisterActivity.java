package br.com.monitorwise;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Conexão entre os elementos do layout para o script
        Button btnRegister = findViewById(R.id.button_register);
        EditText edtEmail = findViewById(R.id.edit_text_login_register);
        EditText edtPassword = findViewById(R.id.edit_text_password_register);
        EditText edtPasswordConfirm = findViewById(R.id.edit_text_password_confirm);
        CheckBox cbShowPassword = findViewById(R.id.check_box_show_password_register);
        CheckBox cbMorning = findViewById(R.id.check_box_morning);
        CheckBox cbAfternoon = findViewById(R.id.check_box_afternoon);
        CheckBox cbNight = findViewById(R.id.check_box_night);
        ListView lvCourse = findViewById(R.id.list_view_course);


        // Configurações da list view
        ArrayList<String> courses = fatecCourses();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(RegisterActivity.this, android.R.layout.simple_list_item_single_choice, courses);
        lvCourse.setAdapter(arrayAdapter);


        // Realizar alguma ação a partir do item clicado da list view
        lvCourse.setOnItemClickListener((parent, view, position, id) -> {
        });


        // Teste das check box dos períodos
        cbMorning.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                cbAfternoon.setChecked(false);
                cbNight.setChecked(false);
            }
        });
        cbAfternoon.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                cbMorning.setChecked(false);
                cbNight.setChecked(false);
            }
        });
        cbNight.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                cbMorning.setChecked(false);
                cbAfternoon.setChecked(false);
            }
        });


        // Teste da chack box para mostrar a senha
        cbShowPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                edtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                edtPasswordConfirm.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else{
                edtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                edtPasswordConfirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });


        // Realiza cadastro, esconde teclado e redireciona para tela principal
        btnRegister.setOnClickListener(v -> hideKeyboardFrom(RegisterActivity.this, btnRegister));
    }

    // Método para preencher a lista de cursos da fatec
    private ArrayList<String> fatecCourses() {
        ArrayList<String> data = new ArrayList<>();

        data.add("Análise e Desenvolvimento de Sistemas");
        data.add("Comércio Exterior");
        data.add("Gestão de Serviços");
        data.add("Gestão Empresarial");
        data.add("Logística Aeroportuária");
        data.add("Redes de Computadores");

        return data;
    }

    // Função para esconder o teclado
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(RegisterActivity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}