package text;

import org.testng.annotations.DataProvider;

import java.util.Set;

public class TextDataProvider {
    @DataProvider(name = "similar_text")
    public static Object[][] similarTextData() {
        return new Object[][]{
                {new SimilarTextData("a", "a")}, {new SimilarTextData(" soppa", " soppa")}, {new SimilarTextData("ac", "ac")},
                {new SimilarTextData(" a", "a ")}, {new SimilarTextData(" Sommar i", " sommar i")}, {new SimilarTextData("Abba", "ABBA")},
                {new SimilarTextData("a", "a")}, {new SimilarTextData(" soppa", " soppa")}, {new SimilarTextData("ac", "ac")}
        };
    }

    @DataProvider(name = "similar_text_context")
    public static Object[][] similarTextContextData() {
        return new Object[][]{
                {new SimilarTextContextDate("Södermalm", "Södermalm",null,null)},
                {new SimilarTextContextDate(" Södermalm", " Södermalm",null,null)},
                {new SimilarTextContextDate(" Södermalm ", " Södermalm ",null,null)},
                {new SimilarTextContextDate("Södermalm", "södermalm",null,null)}
        };
    }
    //

    @DataProvider(name = "similar_text_invalid_parameter")
    public static Object[][] similarTextInvalidParameter() {
        return new Object[][]{
                   {null,null},{"",""}
        };
    }

    static class SimilarTextData {
        private final String a;
        private String b;


        public SimilarTextData(String a, String b) {
            this.a = a;
            this.b = b;
        }

        public String getA() {
            return a;
        }

        public String getB() {
            return b;
        }
    }


    static class SimilarTextContextDate{
        String inputText;
        String text;
        Set<String> synonyms;
        Set<String> contextWords;

        public SimilarTextContextDate(String inputText, String text, Set<String> synonyms, Set<String> contextWords) {
            this.inputText = inputText;
            this.text = text;
            this.synonyms = synonyms;
            this.contextWords = contextWords;
        }

        public String getInputText() {
            return inputText;
        }

        public String getText() {
            return text;
        }

        public Set<String> getSynonyms() {
            return synonyms;
        }

        public Set<String> getContextWords() {
            return contextWords;
        }
    }

}
