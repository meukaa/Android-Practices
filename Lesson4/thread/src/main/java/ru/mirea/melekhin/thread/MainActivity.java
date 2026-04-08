package ru.mirea.melekhin.thread;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.Arrays;
import ru.mirea.melekhin.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Thread mainThread = Thread.currentThread();
        binding.textViewMirea.setText("Имя текущего потока: " + mainThread.getName());

        mainThread.setName("МОЙ НОМЕР ГРУППЫ: БСБО-52-24, НОМЕР ПО СПИСКУ: 20, МОЙ ЛЮБИМЫЙ ФИЛЬМ: Конец света");
        binding.textViewMirea.append("\nНовое имя потока: " + mainThread.getName());

        Log.d(MainActivity.class.getSimpleName(), "Stack: " + Arrays.toString(mainThread.getStackTrace()));
        Log.d(MainActivity.class.getSimpleName(), "Group: " + mainThread.getThreadGroup());

        binding.buttonMirea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        int numberThread = counter++;
                        Log.d("ThreadProject", String.format(
                                "Запущен поток № %d студентом группы № %s номер по списку № %d ",
                                numberThread, "БСБО-52-24", 20));

                        long endTime = System.currentTimeMillis() + 20 * 1000;
                        while (System.currentTimeMillis() < endTime) {
                            synchronized (this) {
                                try {
                                    wait(endTime - System.currentTimeMillis());
                                    Log.d(MainActivity.class.getSimpleName(), "Endtime: " + endTime);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        Log.d("ThreadProject", "Выполнен поток № " + numberThread);


                        String pairsStr = binding.editTextPairs.getText().toString();
                        String daysStr  = binding.editTextDays.getText().toString();
                        if (!pairsStr.isEmpty() && !daysStr.isEmpty()) {
                            final int totalPairs = Integer.parseInt(pairsStr);
                            final int studyDays  = Integer.parseInt(daysStr);
                            final double average = (double) totalPairs / studyDays;
                            final int num = numberThread;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    binding.textViewMirea.setText(
                                            "Поток #" + num + "\nСреднее пар в день: " + average
                                    );
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
}