package com.example.thespa.preferences;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.Locale;

public class LanguageManager {

    public static String languageLocal="en";
    public static void addLanguage(String locale)
    {
        languageLocal = locale;
    }

    public static String returnLanguage()
    {
        return languageLocal;
    }



}
