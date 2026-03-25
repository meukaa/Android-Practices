package ru.mirea.melekhin.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FavoriteBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_share);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TextView textViewBook = findViewById(R.id.textViewBook);
            TextView textViewQuote = findViewById(R.id.textViewQuote);
            String bookName = extras.getString(MainActivity.BOOK_NAME_KEY);
            String quotes = extras.getString(MainActivity.QUOTES_KEY);
            textViewBook.setText("Любимая книга: " + bookName);
            textViewQuote.setText("Цитата: " + quotes);
        }

        Button buttonSend = findViewById(R.id.buttonSend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editBook = findViewById(R.id.editTextBook);
                EditText editQuote = findViewById(R.id.editTextQuote);
                String userBook = editBook.getText().toString();
                String userQuote = editQuote.getText().toString();

                String text = "Название Вашей любимой книги: " + userBook
                        + ". Цитата: " + userQuote;

                Intent data = new Intent();
                data.putExtra(MainActivity.USER_MESSAGE, text);
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
    }
}