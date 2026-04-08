package ru.mirea.melekhin.cryptoloader;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyLoader extends AsyncTaskLoader<String> {

    public static final String ARG_WORD = "word";

    private byte[] cryptText;
    private SecretKey originalKey;

    public MyLoader(@NonNull Context context, Bundle args) {
        super(context);
        if (args != null) {
            byte[] keyBytes = args.getByteArray("key");
            cryptText = args.getByteArray(ARG_WORD);
            originalKey = new SecretKeySpec(keyBytes, 0, keyBytes.length, "AES");
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public String loadInBackground() {

        SystemClock.sleep(5000);
        return decryptMsg(cryptText, originalKey);
    }

    public static String decryptMsg(byte[] cipherText, SecretKey secret) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secret);
            return new String(cipher.doFinal(cipherText));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}