package com.github.parkm.monop;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {
    public String name;
    public Player(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {return 0;}

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
    }

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel parcel) {
            Player p = new Player(parcel.readString());
            return p;
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}
