package ru.mirea.melekhin.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MyLooper extends Thread {

    public Handler mHandler;
    private Handler mainHandler;
    public static final String KEY = "key";

    public MyLooper(Handler mainThreadHandler) {
        mainHandler = mainThreadHandler;
    }

    @Override
    public void run() {
        Log.d("MyLooper", "run");
        Looper.prepare();

        mHandler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                String data = msg.getData().getString(KEY);
                Log.d("MyLooper", "get message: " + data);

                int count = data.length();

                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("result", String.format(
                        "Количество букв в слове %s равно %d", data, count));
                message.setData(bundle);


                mainHandler.sendMessage(message);
            }
        };

        Looper.loop();
    }
}