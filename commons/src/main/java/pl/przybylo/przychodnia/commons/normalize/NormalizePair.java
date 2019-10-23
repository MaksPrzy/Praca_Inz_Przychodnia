package pl.przybylo.przychodnia.commons.normalize;

public final class NormalizePair {

    private String text1;
    private String text2;

    NormalizePair(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
    }

    public static NormalizePair pairOf(String text1, String text2) {
        return new NormalizePair(text1, text2);
    }

    public String getText1() {
        return text1;
    }

    public String getText2() {
        return text2;
    }

}
