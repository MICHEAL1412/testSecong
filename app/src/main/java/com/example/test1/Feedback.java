package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {
private EditText editTextTextMultiLine;
private Button button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Intent intent = getIntent();

        editTextTextMultiLine=findViewById(R.id.editTextTextMultiLine);
        button=findViewById(R.id.button);

        editTextTextMultiLine.addTextChangedListener(loginTextWatcher);

    }
private  TextWatcher loginTextWatcher=new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
       String feedbackInput=editTextTextMultiLine.getText().toString().trim();

       button.setEnabled(!feedbackInput.isEmpty());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
};
    public void back1(View view){
        // This is go new page
        /*Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);*/
        ///////////////////////////////////////////
        // This is close page
        finish();
    }
    public void displayToast(View view) {
       Toast toast=Toast.makeText(this,R.string.toast_message,Toast.LENGTH_SHORT);
       toast.show();
    }
}