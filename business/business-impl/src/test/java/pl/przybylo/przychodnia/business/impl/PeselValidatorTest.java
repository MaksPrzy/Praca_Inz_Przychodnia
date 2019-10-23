package pl.przybylo.przychodnia.business.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.przybylo.przychodnia.business.impl.pacjent.PeselValidator;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PeselValidatorTest {

    @Parameterized.Parameters
    public static Collection textCollection() {
        return asList(new Object[][]{
                {"123", false},
                {"830330231183", false},
                {"00033923193", false},
                {"83033023118", true},
                {"97092954771", true}
        });
    }

    private PeselValidator peselValidator;
    private String inputPesel;
    private boolean isPeselValid;

    public PeselValidatorTest(String inputPesel, boolean isPeselValid) {
        this.peselValidator = new PeselValidator();
        this.inputPesel = inputPesel;
        this.isPeselValid = isPeselValid;
    }

    @Test
    public void valid_should_check_if_pesel_ok() {
        assertEquals(isPeselValid, peselValidator.valid(inputPesel));
    }

}
