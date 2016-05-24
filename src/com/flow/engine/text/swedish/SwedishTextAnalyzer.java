package com.flow.engine.text.swedish;

import com.flow.engine.text.TextAnayzier;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwedishTextAnalyzer extends TextAnayzier {
    private static final String SWEDISH_DICTIONARY_FILE = "Swedish.txt";
    private static final String SWEDISH_SYNONYM_FILE = "Synonymer.txt";

    private static Map<String, Integer> LOWER_CASE_SYNONYM_WORDS;

    static {
        InputStream resourceAsStream =
                SwedishTextAnalyzer.class.getResourceAsStream(SWEDISH_DICTIONARY_FILE);
        try {
            List<String> LOWER_CASE_WORDS = IOUtils.readLines(resourceAsStream);
            System.out.println("Words " + LOWER_CASE_WORDS.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            List<String> rows = IOUtils.readLines(SwedishTextAnalyzer.class.getResourceAsStream(SWEDISH_SYNONYM_FILE));
            //  System.out.println("Words Synonymer " + rows);

            LOWER_CASE_SYNONYM_WORDS = new HashMap<String, Integer>();
            int i = 0;
            for (String synonymWordRow : rows) {
                String[] split = StringUtils.split(synonymWordRow);

                for (String synonymWord : split) {
                    LOWER_CASE_SYNONYM_WORDS.put(synonymWord, i);
                }
                i++;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SwedishTextAnalyzer(String text) {
        super(text);
    }

    public static SwedishTextAnalyzer create(String text) {
        return new SwedishTextAnalyzer(text);
    }

    protected Map<String, Integer> getLowerCaseSynonymWords() {
        return LOWER_CASE_SYNONYM_WORDS;
    }

}
