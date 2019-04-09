package com.example.android.recyclerviewimplementation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    @BindView(R.id.fgFirstName)
    EditText fgFirstName;
    @BindView(R.id.fgLastName)
    EditText fgLastName;
    private Unbinder unbinder;
    private String firstName, lastName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        unbinder = ButterKnife.bind(this, view);

        if(getArguments() != null) {
            firstName = getArguments().getString(Constants.FG_FIRST_NAME);
            lastName = getArguments().getString(Constants.FG_LAST_NAME);
            fgFirstName.setText(firstName);
            fgLastName.setText(lastName);
        }

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
