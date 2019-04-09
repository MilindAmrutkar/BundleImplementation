package com.example.android.recyclerviewimplementation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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

    ValuesFragment valuesFragment;
    SecondFragment secondFragment;

    public static final int SECOND_ACTIVITY_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        valuesFragment = new ValuesFragment();
        secondFragment = new SecondFragment();
        loadFragment(valuesFragment);
        loadSecondFragment(secondFragment);
    }

    @OnClick(R.id.btnSubmit)
    public void onViewClicked() {

        if (!TextUtils.isEmpty(etFirstName.getText()) && !TextUtils.isEmpty(etLastName.getText())) {

            firstName = etFirstName.getText().toString();
            lastName = etLastName.getText().toString();

            /**
             * Code for passing values from one activity to another
             *//*
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra(Constants.FIRST_NAME, firstName);
            intent.putExtra(Constants.LAST_NAME, lastName);
            startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);*/

            /**
             * Code for passing values to the Fragment from Activity
             */

            valuesFragment = new ValuesFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constants.FIRST_NAME, firstName);
            bundle.putString(Constants.LAST_NAME, lastName);
            valuesFragment.setArguments(bundle);
            loadFragment(valuesFragment);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
                if (data != null) {
                    String fName = data.getStringExtra(Constants.FIRST_NAME);
                    String lName = data.getStringExtra(Constants.LAST_NAME);
                    etFirstName.setText(fName);
                    etLastName.setText(lName);
                }
            }
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    private void loadSecondFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container2, fragment);
        fragmentTransaction.commit();
    }
}
