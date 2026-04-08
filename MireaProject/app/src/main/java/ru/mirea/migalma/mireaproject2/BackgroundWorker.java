package ru.mirea.migalma.mireaproject2;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class BackgroundWorker extends Worker {

    static final String TAG = "BackgroundWorker";
    static final String KEY_INPUT = "input_number";
    static final String KEY_RESULT = "result";

    public BackgroundWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d(TAG, "doWork: старт фоновой задачи");

        int inputNumber = getInputData().getInt(KEY_INPUT, 10);


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            return Result.failure();
        }

        long sum = 0;
        for (int i = 1; i <= inputNumber; i++) {
            sum += i;
        }

        Log.d(TAG, "doWork: завершено, результат = " + sum);

        Data output = new Data.Builder()
                .putLong(KEY_RESULT, sum)
                .build();

        return Result.success(output);
    }
}