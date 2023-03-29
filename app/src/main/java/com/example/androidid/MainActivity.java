package com.example.androidid;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "scanner";
    private TextView androidID;
    private String[] permissoesNecessarias = new String[]{Manifest.permission.READ_PHONE_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidID = findViewById(R.id.androidId);
        registerForContextMenu(androidID);
        Permissao.validarPermissoes(permissoesNecessarias, this, 1);

        androidID.setText(Settings.Secure.getString(getApplicationContext().getContentResolver(),
                Settings.Secure.ANDROID_ID));

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()) {
            case R.id.androidId:
                menu.add(0, v.getId(), 0, "Copy");
                TextView textView = (TextView) v;
                ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("text", textView.getText());
                if (manager != null) {
                    manager.setPrimaryClip(clipData);
                }

                break;

        }
    }
}