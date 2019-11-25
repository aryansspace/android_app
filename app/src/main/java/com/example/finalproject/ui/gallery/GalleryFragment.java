package com.example.finalproject.ui.gallery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.finalproject.R;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class GalleryFragment extends Fragment {
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";
    private TextView textViewHighScore;
    private int highscore;

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("The CircQuiz");
            }
        });

        textViewHighScore =(TextView) root.findViewById(R.id.text_view_highscore);
        loadHighscore();
        Button buttonStartQuiz =(Button) root.findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz();
            }
        });

        return root;
    }

    private void startQuiz(){
        Intent intent = new Intent(getActivity().getApplication(), QuizActivity.class);
//        startActivity(intent);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_QUIZ)
        {
            if(resultCode == RESULT_OK){
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE,0);
                if(score > highscore)
                {
                    updateHighscore(score);
                }
            }
        }
        else {

        }
    }

    private void loadHighscore(){
        SharedPreferences prefs = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE,0);
        textViewHighScore.setText("Highscore: " + highscore);
    }

    private void updateHighscore(int highscoreNew){
        highscore = highscoreNew;
        textViewHighScore.setText("Highscore: " + highscore);

        SharedPreferences prefs =getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }
}