package pl.przybylo.przychodnia.commons.normalize;

import java.util.Collection;

import static lombok.Lombok.checkNotNull;
import static org.apache.commons.lang3.StringUtils.*;

public final class NormalizeUtils {

    private NormalizeUtils() {
        // empty
    }

    public static String normalize(NormalizePair... pairArray) {
        if (pairArray == null) {
            return EMPTY;
        }

        StringBuilder normalized = new StringBuilder();

        for (NormalizePair pair : pairArray) {
            normalized.append(normalize(pair.getText1(), pair.getText2()));
        }

        return normalized.toString();
    }

    public static String normalize(Collection<String> textCollection, NormalizePair... pairArray) {
        checkNotNull(textCollection, "20190525112526");

        StringBuilder normalized = new StringBuilder();
        normalized.append(normalize(textCollection.toArray(new String[textCollection.size()])));
        normalized.append(normalize(pairArray));
        return normalized.toString();
    }

    public static String normalize(String... textArray) {
        if (textArray == null) {
            return EMPTY;
        }

        StringBuilder normalized = new StringBuilder();

        for (String text : textArray) {
            normalized.append(normalizeSingle(text));
        }

        if (textArray.length == 2) {
            for (int idx = textArray.length - 1; idx >= 0; idx--) {
                normalized.append(normalizeSingle(textArray[idx]));
            }
        }

        return normalized.toString();
    }

    private static String normalizeSingle(String text) {
        if (isBlank(text)) {
            return EMPTY;
        }

        return stripAccents(text.toLowerCase()).replaceAll("[^\\p{Alpha}\\p{Digit}]+", "");
    }

}
