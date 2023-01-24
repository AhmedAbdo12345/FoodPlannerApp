package com.example.foodplanner.model.ModelResponse;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "meal_plans")
public class PlanModel {

        @PrimaryKey
        @ColumnInfo(name = "pId")
        @NonNull
        private long pId;

        @ColumnInfo(name = "pName")
        private String pName;


        @ColumnInfo(name = "day")
        private String day;

        @ColumnInfo(name = "type")
        private String type;

        @ColumnInfo(name = "pImg")
        private String pImg;


        public long getpId() {
            return pId;
        }

        public void setpId(long pId) {
            this.pId = pId;
        }

        public String getpName() {
            return pName;
        }

        public void setpName(String pName) {
            this.pName = pName;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getpImg() {
            return pImg;
        }

        public void setpImg(String pImg) {
            this.pImg = pImg;
        }

    }
