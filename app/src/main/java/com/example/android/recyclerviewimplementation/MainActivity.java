package com.example.android.recyclerviewimplementation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.firstName)
    EditText etFirstName;
    @BindView(R.id.lastName)
    EditText etLastName;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    String firstName, lastName;

    public static final int SECOND_ACTIVITY_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSubmit)
    public void onViewClicked() {

        if (!TextUtils.isEmpty(etFirstName.getText()) && !TextUtils.isEmpty(etLastName.getText())) {

            firstName = etFirstName.getText().toString();
            lastName = etLastName.getText().toString();

            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra(Constants.FIRST_NAME, firstName);
            intent.putExtra(Constants.LAST_NAME, lastName);
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            if(requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
                if(data != null) {
                    String fName = data.getStringExtra(Constants.FIRST_NAME);
                    String lName = data.getStringExtra(Constants.LAST_NAME);
                    etFirstName.setText(fName);
                    etLastName.setText(lName);
                }
            }
        }
    }
}
