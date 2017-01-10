package com.example.hoangduy.japanese4you.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HoangDuy on 08/01/2017.
 */
public class Quiz implements Parcelable {
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private int choosenQuestion;
    private int rightAnswer;

    public Quiz(String question, String answerA, String answerB, String answerC, String answerD, int choosenQuestion, int rightAnswer) {
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.choosenQuestion = choosenQuestion;
        this.rightAnswer = rightAnswer;
    }

    protected Quiz(Parcel in) {
        question = in.readString();
        answerA = in.readString();
        answerB = in.readString();
        answerC = in.readString();
        answerD = in.readString();
        choosenQuestion = in.readInt();
        rightAnswer = in.readInt();
    }

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public int getChoosenQuestion() {
        return choosenQuestion;
    }

    public void setChoosenQuestion(int choosenQuestion) {
        this.choosenQuestion = choosenQuestion;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(answerA);
        dest.writeString(answerB);
        dest.writeString(answerC);
        dest.writeString(answerD);
        dest.writeInt(choosenQuestion);
        dest.writeInt(rightAnswer);
    }
}
