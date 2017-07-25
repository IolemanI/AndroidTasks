package com.oleman.androidtasks.Settings;

import android.content.SharedPreferences;

import java.util.ArrayList;


public class SettingAdapter {

    public final static String SETTING_FILE_NAME = "app_settings"; //используется в других классах при передаче в конструктор sPref.

    private SharedPreferences sPref;
    private String settingsKey = "";
    private String data = null;

    /**
     * @param settingsKey указывает на то, какие именно настройки сохранять или загружать (например BTN_NAMES).
     * @param sPref нужен для установки getSharedPreferences() обьекту sPref, так как в этом классе я пока не знаю как его вызвать :)
     *              Приходится добавлять SharedPreferences в каждом классе, который использует SettingAdapter.
     */
    public SettingAdapter(String settingsKey, SharedPreferences sPref) {
        this.settingsKey = settingsKey;
        this.sPref = sPref;
    }

    /**
     * В методе saveText передается settingsKey (т.е. какие именно настройки) и сохраняется текст настроек.
     * @param dataToSave информация, которую надо сохранить в файл.
     */
    void saveText(String dataToSave) {
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString(settingsKey, dataToSave);
        editor.commit();
    }

    /**
     * В методе loadText читается текст файла настроек (переменная data) по соответствующему ключу.
     * @return строку с текстом настроек (для настроек с одним параметромм)
     */
    public String loadText(){
        return data = sPref.getString(settingsKey, "");
    }

    /**
     * В методе getArrayTexts идет разбор по комам каждой настройки и их запись в ArrayList (пробелы после ком пропускаются).
     * @return ArrayList с упорядочненым текстом настроек
     */
    public ArrayList<String> getArrayTexts(){
        String s = loadText();
        char ch;
        String name = "";
        ArrayList<String> namesList = new ArrayList<String>();

        namesList.clear();
        for (int i=0; i<s.length(); i++){
            ch = s.charAt(i);

            if (i == s.length()-1){
                name = name + String.valueOf(ch);
                namesList.add(name);
                break;
            }
            if (String.valueOf(s.charAt(i+1)).equals(",")) {
                name = name + String.valueOf(ch);
                namesList.add(name);
                name = "";
                i++;
                continue;
            }else if (i>0 && String.valueOf(s.charAt(i-1)).equals(",") && String.valueOf(s.charAt(i)).equals(" "))
                continue;

            name = name + String.valueOf(ch);

        }

        return namesList;
    }
}
