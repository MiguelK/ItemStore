package com.itemstore.engine.text;

public class TextMatcherSwe implements TextMatcherLang {

    @Override
    public String getWordId(String word) {

        if(word==null || word.isEmpty()){
            return null;
        }

        String s = word.toLowerCase();

        if(s.equals("av") || s.equals("en")){
            return null; //FIXME     common words
        }

        if(s.equals("kvarlevor") || s.equals("resterna"))    {
            return "kvarlevor"; //FIXME synonymer
        }


        return s;
    }
}
