package com.example.finalproject.ui.home;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.finalproject.MainActivity;
import com.example.finalproject.R;

import java.util.List;

public class HomeFragment extends Fragment {
    Button click;
    ArrayAdapter adapter;
    public static TextView data;
    public static ListView list;

    private ScrollView scrollView;
    private ImageView imageView;
    boolean fontChange;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        final TextView tv = root.findViewById(R.id.tv);
        SharedPreferences sharedPref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this.getContext());

        Boolean fontPref = sharedPref.getBoolean("font", true);
        fontChange = fontPref.booleanValue();

        homeViewModel.getText().observe(this, new Observer<String>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("Top 10 ODI Players");



                scrollView = root.findViewById(R.id.scrollView2);
                list = (ListView) root.findViewById(R.id.list);
                scrollView.setVisibility(View.INVISIBLE);
                list.setVisibility(View.INVISIBLE);

                if(fontChange){
                    Typeface type = getResources().getFont(R.font.amaticregular);
                    textView.setTextAppearance(R.style.TextAppearance_AppCompat_Large);
                    textView.setTypeface(type);
                }
            }
        });

        scrollView = root.findViewById(R.id.scrollView2);
        list = (ListView) root.findViewById(R.id.list);

//        click = (Button) root.findViewById(R.id.button1);
        data = (TextView) root.findViewById(R.id.data);
        imageView = (ImageView) root.findViewById(R.id.imageView2);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setVisibility(View.INVISIBLE);
                list.setVisibility(View.VISIBLE);
//                scrollView.setVisibility(View.VISIBLE);

                fetchData process = new fetchData(HomeFragment.this);
                process.execute();
            }
        });

        return root;
    }
}