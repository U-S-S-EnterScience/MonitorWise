package br.com.monitorwise.Monitor;

import static br.com.monitorwise.RegisterActivity.hideKeyboardFrom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.monitorwise.MainActivity;
import br.com.monitorwise.R;

public class MonitorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);

        Button btSendReport = (Button) findViewById(R.id.button_send_report);

        ListView lvDiscipline = findViewById(R.id.list_view_discipline);

        ListView lvStudent = findViewById(R.id.list_view_student);

        // Configurações da List View Discipline
        ArrayList<String> disciplines = fatecDisciplines();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MonitorActivity.this, android.R.layout.simple_list_item_single_choice, disciplines);
        lvDiscipline.setAdapter(arrayAdapter);

        // Configurações da List View Student
        ArrayList<String> students = fatecStudents();
        ArrayAdapter<String> arrayAdapterStudent = new ArrayAdapter<>(MonitorActivity.this, android.R.layout.simple_list_item_multiple_choice, students);
        lvStudent.setAdapter(arrayAdapterStudent);

        // Evento de click do botão
        btSendReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Esconde o teclado
                hideKeyboardFrom(MonitorActivity.this,btSendReport);

                // Mensagem visual ao usuário
                Toast.makeText(MonitorActivity.this, R.string.sendReport, Toast.LENGTH_SHORT).show();

                // Encerra a activity
                finish();
            }
        });

    }



    // Método para preencher a lista das disciplinas da fatec
    private ArrayList<String> fatecDisciplines(){
        ArrayList<String> discipline = new ArrayList<>();

        discipline.add("Administração Geral");
        discipline.add("Gestão de Projetos");
        discipline.add("Contabilidade");
        discipline.add("Economia e Finanças");
        discipline.add("Sociedade e Tecnologia");
        discipline.add("Arquitetura e Organização de Computadores");
        discipline.add("Algoritmos e Lógica de Programação");
        discipline.add("Banco de Dados");
        discipline.add("Laboratório de Banco de Dados");
        discipline.add("Estrutura de Dados");
        discipline.add("Engenharia de Software I");
        discipline.add("Engenharia de Software II");
        discipline.add("Engenharia de Software III");
        discipline.add("Interação Humano Computador");
        discipline.add("Laboratório de Hardware");
        discipline.add("Programação em Microinformática");
        discipline.add("Programação Orientada a Objetos");
        discipline.add("Linguagem de Programação");
        discipline.add("Programação Web");
        discipline.add("Programação para Dispositivos Móveis");
        discipline.add("Segurança da Informação");
        discipline.add("Sistemas da Informação");
        discipline.add("Sistemas Operacionais I");
        discipline.add("Sistemas Operacionais II");
        discipline.add("Gestão e Governança da Técnologia da Informação");
        discipline.add("Inglês I");
        discipline.add("Inglês II");
        discipline.add("Inglês III");
        discipline.add("Comunicação e Expressão");
        discipline.add("Cálculo");
        discipline.add("Estatística Aplicada");
        discipline.add("Matemática Discreta");
        discipline.add("Programação Linear e Aplicações");
        discipline.add("Metodologia da Pesquisa Científico-Tecnológica");

        return discipline;
    }

    // Método para preencher a lista de estudantes da fatec
    private ArrayList<String> fatecStudents(){
        ArrayList<String> student = new ArrayList<>();

        student.add("Nome Aluno(a) 1");
        student.add("Nome Aluno(a) 2");
        student.add("Nome Aluno(a) 3");
        student.add("Nome Aluno(a) 4");
        student.add("Nome Aluno(a) 5");
        student.add("Nome Aluno(a) 6");
        student.add("Nome Aluno(a) 7");
        student.add("Nome Aluno(a) 8");
        student.add("Nome Aluno(a) 9");
        student.add("Nome Aluno(a) 10");
        student.add("Nome Aluno(a) 11");
        student.add("Nome Aluno(a) 12");
        student.add("Nome Aluno(a) 13");
        student.add("Nome Aluno(a) 14");
        student.add("Nome Aluno(a) 15");
        student.add("Nome Aluno(a) 16");
        student.add("Nome Aluno(a) 17");
        student.add("Nome Aluno(a) 18");
        student.add("Nome Aluno(a) 19");
        student.add("Nome Aluno(a) 20");
        student.add("Nome Aluno(a) 21");
        student.add("Nome Aluno(a) 22");
        student.add("Nome Aluno(a) 23");
        student.add("Nome Aluno(a) 24");
        student.add("Nome Aluno(a) 25");
        student.add("Nome Aluno(a) 26");
        student.add("Nome Aluno(a) 27");
        student.add("Nome Aluno(a) 28");
        student.add("Nome Aluno(a) 29");
        student.add("Nome Aluno(a) 30");
        student.add("Nome Aluno(a) 31");
        student.add("Nome Aluno(a) 32");
        student.add("Nome Aluno(a) 33");
        student.add("Nome Aluno(a) 34");
        student.add("Nome Aluno(a) 35");
        student.add("Nome Aluno(a) 36");
        student.add("Nome Aluno(a) 37");
        student.add("Nome Aluno(a) 38");
        student.add("Nome Aluno(a) 39");
        student.add("Nome Aluno(a) 40");

        return student;
    }

}