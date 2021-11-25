package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);
        mTrueButton = findViewById(R.id.True);
        mFalseButton = findViewById(R.id.False);
        mNextButton = findViewById(R.id.nextButton);
        mQuestionTextView = findViewById(R.id.question);


        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);

            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        updateQuestion();
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart called.");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called.");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called.");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called.");
    }
    private void updateQuestion() {
        int questionTextResId = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(questionTextResId);
    }
    private void checkAnswer(boolean answer) {
        boolean correctAnswer = mQuestionBank[mCurrentIndex].isAnswer();
        if (answer == correctAnswer) {
            Toast.makeText(this, R.string.correct ,Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.incorrect, Toast.LENGTH_SHORT).show();
        }
    }


}

