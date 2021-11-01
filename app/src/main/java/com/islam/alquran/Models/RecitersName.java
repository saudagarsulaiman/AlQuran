package com.islam.alquran.Models;

import com.google.gson.annotations.SerializedName;

public class RecitersName {

    @SerializedName("identifier")
    String str_identifier;
    @SerializedName("language")
    String str_language;
    @SerializedName("name")
    String str_name;
    @SerializedName("englishName")
    String str_englishName;
    @SerializedName("format")
    String str_format;
    @SerializedName("type")
    String str_type;

    public String getStr_identifier() {
        return str_identifier;
    }

    public void setStr_identifier(String str_identifier) {
        this.str_identifier = str_identifier;
    }

    public String getStr_language() {
        return str_language;
    }

    public void setStr_language(String str_language) {
        this.str_language = str_language;
    }

    public String getStr_name() {
        return str_name;
    }

    public void setStr_name(String str_name) {
        this.str_name = str_name;
    }

    public String getStr_englishName() {
        return str_englishName;
    }

    public void setStr_englishName(String str_englishName) {
        this.str_englishName = str_englishName;
    }

    public String getStr_format() {
        return str_format;
    }

    public void setStr_format(String str_format) {
        this.str_format = str_format;
    }

    public String getStr_type() {
        return str_type;
    }

    public void setStr_type(String str_type) {
        this.str_type = str_type;
    }
}
