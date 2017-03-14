package com.example.nick.myfirstapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nick on 2017-03-05.
 */

public class Form implements Parcelable {
    public String destination;
    public String time;
    public String space;

    public Form(String destination, String space, String time){
        this.destination = destination;
        this.time = time;
        this.space= space;
    }

    public String getDestination() {
        return destination;
    }

    public String getTime() {
        return time;
    }

    public String getSpace() {
        return space;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.destination);
        dest.writeString(this.time);
        dest.writeString(this.space);
    }

    protected Form(Parcel in) {
        this.destination = in.readString();
        this.time = in.readString();
        this.space = in.readString();
    }

    public static final Creator<Form> CREATOR = new Creator<Form>() {
        @Override
        public Form createFromParcel(Parcel source) {
            return new Form(source);
        }

        @Override
        public Form[] newArray(int size) {
            return new Form[size];
        }
    };
}
