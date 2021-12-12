package com.example.todoapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.todoapp.R;

public class AddEditFragment extends Fragment {

    private EditText TitleView,descriptionView,categoryView;
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //disable the menu
        setHasOptionsMenu(true);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.fragment_add, container, false);

        TitleView = rootView.findViewById(R.id.editTextTextEmailAddress);
        descriptionView = rootView.findViewById(R.id.editTextTextDescription);
        categoryView = rootView.findViewById(R.id.editTextTextCategory);
        button = rootView.findViewById(R.id.add);

        //get the data from the today in case of editing tasks
        Bundle bundle = this.getArguments();
        int id;
        //if there is data to receive we put the id of the of the task that we want to modify in the bundle
        //other wise we send an id=-1 that means a new one created
        if(bundle != null){
            id = bundle.getInt("id");
            TitleView.setText(bundle.getString("title"));
            descriptionView.setText(bundle.getString("description"));
            categoryView.setText(bundle.getString("category"));
        }else  id = -1 ;

        //call the function when click the button
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                saveTask(id);
            }
        });

        return rootView;
    }

    //disable the menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    //function to sane tasks
    private void saveTask(int id) {
        String title = TitleView.getText().toString();
        String description = descriptionView.getText().toString();
        String category = categoryView.getText().toString();

        //trim function is to remove the spaces from both ends of a string.
        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(getContext(),"Please insert a title and description",Toast.LENGTH_SHORT).show();
        }
        //send data to the today fragment
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        bundle.putString("description",description);
        bundle.putString("category",category);
        bundle.putInt("id",id);
        TodayFragment today = new TodayFragment();
        today.setArguments(bundle);
        getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,today).commit();

    }
}