package com.example.finalproject.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.finalproject.R;

public class HomeFragment extends Fragment {
    Button click;
    public static TextView data;
//    Added later
    public static ListView listdata;
    public static ArrayAdapter<String> arrayAdapter;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("Top 10 ODI Players");
            }
        });

        click = root.findViewById(R.id.button1);
        data = root.findViewById(R.id.data);
        //Added later
        listdata = (ListView) root.findViewById(R.id.listdata);


        click.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                fetchData process = new fetchData();
                process.execute();

            }
        });

        return root;
    }
}