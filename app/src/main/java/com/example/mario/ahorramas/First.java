package com.example.mario.ahorramas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class First {

    @SerializedName("$ref")
    @Expose
    private String $ref;

    public String get$ref() {
        return $ref;
    }

    public void set$ref(String $ref) {
        this.$ref = $ref;
    }

}
