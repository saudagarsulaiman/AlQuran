package com.islam.alquran.Interfaces;

import com.islam.alquran.Models.SurahName;

import java.util.ArrayList;

public interface SelectSurahListener {
    public void SurahSelected(ArrayList<SurahName> allSurahList, int pos);
}
