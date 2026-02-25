package ru.mirea.melekhinma.buttonclicker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnWhoAmI;
    private Button btnItIsNotMe;
    private TextView textViewStudent;
    private CheckBox CheckBox1;

    public void onMyButtonClick(View view) {
        textViewStudent.setText("Это не я сделал");
        CheckBox1.toggle();
        Toast.makeText(this, "Ещё один способ!", Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        textViewStudent = findViewById(R.id.textViewStudent);
        btnWhoAmI = findViewById(R.id.btnWhoAmI);
        btnItIsNotMe = findViewById(R.id.btnItIsNotMe);
        CheckBox1 = findViewById(R.id.CheckBox1);


        View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewStudent.setText("Мой номер по списку № 20");
                CheckBox1.toggle();

            }
        };
        btnWhoAmI.setOnClickListener(oclBtnWhoAmI);
    }
}