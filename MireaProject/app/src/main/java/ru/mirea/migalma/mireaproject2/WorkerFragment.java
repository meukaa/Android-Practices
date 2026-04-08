package ru.mirea.migalma.mireaproject2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

public class WorkerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_worker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText editNumber = view.findViewById(R.id.editNumber);
        Button btnStart = view.findViewById(R.id.btnStartWorker);
        TextView tvStatus = view.findViewById(R.id.tvWorkerStatus);

        btnStart.setOnClickListener(v -> {
            String input = editNumber.getText().toString().trim();
            int number = input.isEmpty() ? 10 : Integer.parseInt(input);

            tvStatus.setText("Задача запущена, ожидайте...");

            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();



            Data inputData = new Data.Builder()
                    .putInt(BackgroundWorker.KEY_INPUT, number)
                    .build();

            OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(BackgroundWorker.class)
                    .setInputData(inputData)
                    .build();


            WorkManager workManager = WorkManager.getInstance(requireContext());
            workManager.enqueue(workRequest);


            workManager.getWorkInfoByIdLiveData(workRequest.getId())
                    .observe(getViewLifecycleOwner(), workInfo -> {
                        if (workInfo == null) return;

                        if (workInfo.getState() == WorkInfo.State.RUNNING) {
                            tvStatus.setText("Статус: выполняется...");
                        } else if (workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                            long result = workInfo.getOutputData()
                                    .getLong(BackgroundWorker.KEY_RESULT, -1);
                            tvStatus.setText("Готово! Сумма от 1 до " + number + " = " + result);
                        } else if (workInfo.getState() == WorkInfo.State.FAILED) {
                            tvStatus.setText("Ошибка выполнения задачи.");
                        } else if (workInfo.getState() == WorkInfo.State.ENQUEUED) {
                            tvStatus.setText("Статус: задача в очереди...");
                        }
                    });
        });
    }
}