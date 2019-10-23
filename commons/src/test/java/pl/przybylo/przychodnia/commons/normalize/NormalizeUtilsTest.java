package pl.przybylo.przychodnia.commons.normalize;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static pl.przybylo.przychodnia.commons.normalize.NormalizeUtils.normalize;

@RunWith(Parameterized.class)
public class NormalizeUtilsTest {

    @Parameterized.Parameters
    public static Collection textCollection() {
        return asList(new Object[][]{
                {asList(null, ""), ""},
                {asList(null, "", "J a n"), "jan"},
                {asList("Łucja", "Dzie rżonowiczówna"), "lucjadzierzonowiczownadzierzonowiczownalucja"},
                {asList("Łucja", "Dzier        żonowiczówna", "12-230"), "lucjadzierzonowiczowna12230"},
                {asList("żźćńąśłęóŻŹĆŃĄŚŁĘÓ"), "zzcnasleozzcnasleo"},
                {asList("1234567890", "text"), "1234567890texttext1234567890"},
                {asList("12-230", ".", ",", "-", "/", "~", "!", "@", "$", "^", "&", "_", "+"), "12230"}
        });
    }

    private Collection<String> inputTextCollection;
    private String expectedNormalized;

    public NormalizeUtilsTest(Collection<String> inputTextCollection, String expectedNormalized) {
        this.inputTextCollection = inputTextCollection;
        this.expectedNormalized = expectedNormalized;
    }

    @Test
    public void normalize_should_return_normalized_text() {
        assertEquals(expectedNormalized, normalize(inputTextCollection));
    }

}
