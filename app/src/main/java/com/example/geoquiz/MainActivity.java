package com.example.geoquiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private final String KEY_INDEX = "index";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private Button mCheatButton;

    private final int REQUEST_CODE_CHEAT = 0;

    private QuizViewModel mQuizViewModel;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data != null) {
                mQuizViewModel.setIsCheater(
                        data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false));
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");


        ViewModelProvider provider = ViewModelProviders.of(this);
        mQuizViewModel = provider.get(QuizViewModel.class);
        Log.d(TAG, "Got a QuizViewModel");


        if (savedInstanceState != null) {
            mQuizViewModel.mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        } else {
            mQuizViewModel.mCurrentIndex = 0;
        }

        setContentView(R.layout.activity_main);
        mTrueButton = findViewById(R.id.True);
        mFalseButton = findViewById(R.id.False);
        mNextButton = findViewById(R.id.nextButton);
        mQuestionTextView = findViewById(R.id.question);


        mCheatButton = findViewById(R.id.cheat_button);

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuizViewModel.currentQuestionAnswer();
                Intent intent = CheatActivity.newIntent(MainActivity.this, answerIsTrue);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });


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
                mQuizViewModel.moveToNext();
                updateQuestion();
            }
        });
        updateQuestion();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mQuizViewModel.mCurrentIndex);
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
        int questionTextResId = mQuizViewModel.currentQuestionText();
        mQuestionTextView.setText(questionTextResId);
    }
    private void checkAnswer(boolean answer) {
        boolean correctAnswer = mQuizViewModel.currentQuestionAnswer();
        if (mQuizViewModel.isCheater()) {
            Toast.makeText(this, R.string.judgment_toast, Toast.LENGTH_SHORT).show();
        } else if (answer == correctAnswer) {
            Toast.makeText(this, R.string.correct ,Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.incorrect, Toast.LENGTH_SHORT).show();
        }
    }


}

