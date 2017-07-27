package com.oleman.androidtasks.adapters;


import android.content.Context;
import android.util.Log;
import android.util.Xml;

import com.oleman.androidtasks.MainActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;


import static com.oleman.androidtasks.MainActivity.LOG_TAG;

public class FileAdapter {

    private static final String FILENAME = "AndroidTasks.txt";

    private Context context;

    public FileAdapter(Context context) {
        this.context = context;
    }

    /**
     * readFile() method reads the file from internal storage.
     * @return String that contains full document text.
     */
    private String readFile() {
        String data = "";
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            char[] inputBuffer = new char[fis.available()];
            isr.read(inputBuffer);
            data = new String(inputBuffer);
            isr.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Getting the string and writing it to file, that goes to internal storage.
     * @param s If it has a few parameters (button names in this example),
     *          they should be written in one string variable, divided by comma.
     */
    void writeFile(String s) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);

            XmlSerializer serializer = Xml.newSerializer();

            serializer.setOutput(fos, "UTF-8");
            serializer.startDocument(null, true);
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);

            serializer.startTag(null, "task_names");
            serializer.text(s);
            serializer.endTag(null, "task_names");

            serializer.endDocument();
            serializer.flush();
            fos.close();

            Log.d(MainActivity.LOG_TAG, "File has been written!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return true if file is available.
     */
    public boolean isAvailable(){
        String s = readFile();
        return !(s.isEmpty() || s.equals(""));
    }

    /**
     * Getting the comma separated string and parse it in ArrayList.
     * @return ArrayList with separated names.
     */
    public ArrayList<String> getNameList() {
        String s = parseXml();
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

    /**
     * parseXml() get and @return the text from task_names tag.
     */
    private String parseXml(){
        String tmp = "";
        try{
            XmlPullParser xpp = prepareXpp(readFile());

            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                switch (xpp.getEventType()) {
                    // начало документа
//                  case XmlPullParser.START_DOCUMENT:
//                        Log.d(LOG_TAG, "START_DOCUMENT");
//                        break;
//                    // начало тэга
//                    case XmlPullParser.START_TAG:
//                        Log.d(LOG_TAG, "START_TAG: name = " + xpp.getName()
//                                + ", depth = " + xpp.getDepth() + ", attrCount = "
//                                + xpp.getAttributeCount());
//                        tmp = "";
//                        for (int i = 0; i < xpp.getAttributeCount(); i++) {
//                            tmp = tmp + xpp.getAttributeName(i) + " = "
//                                    + xpp.getAttributeValue(i) + ", ";
//                        }
//                        if (!TextUtils.isEmpty(tmp)) {
//                            Log.d(LOG_TAG, "Attributes: " + tmp);
//                        }
//                        break;
//                    // конец тэга
//                    case XmlPullParser.END_TAG:
//                        Log.d(LOG_TAG, "END_TAG: name = " + xpp.getName());
//                        break;
                    // содержимое тэга
                    case XmlPullParser.TEXT:
                        Log.d(LOG_TAG, "text = " + xpp.getText());
                        return xpp.getText();
                    default:
                        break;
                }
                // следующий элемент
                xpp.next();
            }
//            Log.d(LOG_TAG, "END_DOCUMENT");
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    private XmlPullParser prepareXpp(String data) throws XmlPullParserException {
        // получаем фабрику
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        // включаем поддержку namespace (по умолчанию выключена)
        factory.setNamespaceAware(true);
        // создаем парсер
        XmlPullParser xpp = factory.newPullParser();
        // даем парсеру на вход Reader
        xpp.setInput(new StringReader(data));
        return xpp;
    }








}
