package org.pursuit.cliffordcharles_finalassessment.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

    public class Locations implements Comparable<Locations>, Parcelable {

        private String country;
        private String name;
        private Integer _id;
        private Coordinates coord;

        protected Locations(Parcel in) {
            country = in.readString();
            name = in.readString();
            if (in.readByte() == 0) {
                _id = null;
            } else {
                _id = in.readInt();
            }
        }

        public static final Creator<Locations> CREATOR = new Creator<Locations>() {
            @Override
            public Locations createFromParcel(Parcel in) {
                return new Locations(in);
            }

            @Override
            public Locations[] newArray(int size) {
                return new Locations[size];
            }
        };

        public String getCountry() {
            return country;
        }

        public String getName() {
            return name;
        }

        public Integer get_id() {
            return _id;
        }

        public Coordinates getCoord() {
            return coord;
        }

        public Locations(String country, String name, Integer _id, Coordinates coord) {
            this.country = country;
            this.name = name;
            this._id = _id;
            this.coord = coord;


        }

        @Override
        public int compareTo(Locations locale) {
            int location = this.getName().toLowerCase().compareTo(locale.getName().toLowerCase());
            return location;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(country);
            dest.writeString(name);
            if (_id == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeInt(_id);
            }
        }

//        private  List <Locations> locationsList( List<Locations> input,List<Locations>input2){
//            int location =input.getName().toLowerCase().compareTo(.getName().toLowerCase());
//

    }
