package com.chaquo.python.utils;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.console.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FilenameFilter;

public class AccountActivity extends BacNetActivity {

    public  String publicKey;
    public String keyDirectory;
    private String fileExtension = ".key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        keyDirectory = getBaseContext().getFilesDir().getPath();
        getAndSetPublicKey();
        setPublicKeyText();
        final TextInputLayout changeUsernameField = findViewById(R.id.changeUsernameText);
        FloatingActionButton updateUsernameButton = findViewById(R.id.floatingActionButton_updateUsername);
        updateUsernameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("HELLO", changeUsernameField.getEditText().getText().toString());
            }
        });
    }
//-------------------------------------publickey---------------------------------------------
    public class FileFilter implements FilenameFilter {

        private String fileExtension;

        public FileFilter(String fileExtension) {
            this.fileExtension = fileExtension;
        }

        @Override
        public boolean accept(File directory, String fileName) {
            return (fileName.endsWith(this.fileExtension));
        }
    }

    public void getAndSetPublicKey() {
        FileFilter fileFilter = new FileFilter(fileExtension);
        File parentDir = new File(keyDirectory);
        // Put the names of all files ending with .key in a String array
        String[] listOfTextFiles = parentDir.list(fileFilter);

        if (listOfTextFiles.length == 0) {
            System.out.println("No public key!");
            return;
        }
        String s = "";
        for (String file : listOfTextFiles) {
            //construct the absolute file paths...
            String absoluteFilePath = new StringBuffer(keyDirectory).append(File.separator).append(file).toString();
            s = s + file;
            System.out.println(absoluteFilePath);
        }
        publicKey = s;
    }

    public void setPublicKeyText() {
        TextView keyInfos = findViewById(R.id.publicKey);
        keyInfos.setText(publicKey);
    }
//-------------------------------------------------------------------------------------------

    public static class Task extends DebugActivity.Task {
        public Task(Application app) {
            super(app);
        }

        @Override
        public void run() {
            py.getModule("main").callAttr("main");
        } //TODO
    }

    @Override
    protected Class<? extends AccountActivity.Task> getTaskClass() {
        return AccountActivity.Task.class;
    }
}
