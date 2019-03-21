package com.example.android.capstone.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Colors implements Parcelable {

    @SerializedName("hex_value")
    @Expose
    private String hex_value;

    @SerializedName("colour_name")
    @Expose
    private String colour_name;

    protected Colors(Parcel in) {
        hex_value = in.readString();
        colour_name = in.readString();
    }

    public static final Creator<Colors> CREATOR = new Creator<Colors>() {
        @Override
        public Colors createFromParcel(Parcel in) {
            return new Colors(in);
        }

        @Override
        public Colors[] newArray(int size) {
            return new Colors[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hex_value);
        dest.writeString(colour_name);
    }
}
