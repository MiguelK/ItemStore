package text;

import com.itemstore.engine.model.Text;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TextTest {

    @Test(dataProvider = "similar_text", dataProviderClass = TextDataProvider.class)
    public void isSimilar(TextDataProvider.SimilarTextData similarTextData) {
        Assert.assertTrue(Text.isSimilar(similarTextData.getA(), similarTextData.getB()));
    }

    @Test(expectedExceptions = IllegalArgumentException.class ,dataProvider = "similar_text_invalid_parameter", dataProviderClass = TextDataProvider.class)
    public void isSimilar_invalid_parameter(String a, String b) {
        Assert.assertTrue(Text.isSimilar(a, b));
    }

    @Test(dataProvider = "similar_text_context", dataProviderClass = TextDataProvider.class)
    public void isSimilarInContext_true(TextDataProvider.SimilarTextContextDate contextDate) {
        Text text = Text.createSimilarTextContext(contextDate.getText(), contextDate.getSynonyms(),
                contextDate.getContextWords());

        Assert.assertTrue(text.isSimilarInContext(contextDate.getInputText()));
    }
}