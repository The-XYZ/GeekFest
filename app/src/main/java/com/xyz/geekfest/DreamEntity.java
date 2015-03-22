package com.xyz.geekfest;

import android.graphics.Bitmap;

public class DreamEntity {
        public String name;
        public String price;
        public Bitmap pic;


        public DreamEntity (Bitmap pic, String name, String price){
            this.pic = pic;
            this.name= name;
            this.price = price;
        }
    }