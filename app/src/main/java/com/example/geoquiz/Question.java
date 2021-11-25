package com.example.geoquiz;

public class Question {

        int textResId;
        boolean answer;

    public boolean isAnswer() {
        return answer;
    }

    Question(int textResId, boolean answer) {
            this.textResId = textResId;
            this.answer = answer;
        }

        public int getTextResId() {
            return textResId;
        }

        public void setTextResId(int textResId) {
            this.textResId = textResId;
        }

}
