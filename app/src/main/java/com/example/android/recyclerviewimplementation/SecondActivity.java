package com.example.android.recyclerviewimplementation;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.fName)
    EditText etFName;
    @BindView(R.id.lName)
    EditText etLName;
    @BindView(R.id.btnSendToFirstActivity)
    Button btnSendToFirstActivity;

    String firstName, lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        if (intent != null) {
            if ((getIntent().getStringExtra(Constants.FIRST_NAME) != null)
                    && (getIntent().getStringExtra(Constants.LAST_NAME) != null)) {
                firstName = getIntent().getStringExtra(Constants.FIRST_NAME);
                lastName = getIntent().getStringExtra(Constants.LAST_NAME);
            }
            etFName.setText(firstName);
            etLName.setText(lastName);
        }
    }

    @OnClick(R.id.btnSendToFirstActivity)
    public void onViewClicked() {
        firstName = etFName.getText().toString();
        lastName = etLName.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(Constants.FIRST_NAME, etFName.getText().toString());
        intent.putExtra(Constants.LAST_NAME, etLName.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }


}
