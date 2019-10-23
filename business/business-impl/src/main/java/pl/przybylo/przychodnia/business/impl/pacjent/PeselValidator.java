package pl.przybylo.przychodnia.business.impl.pacjent;

import org.springframework.stereotype.Component;

import static java.lang.Integer.parseInt;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkArgument;

@Component
public class PeselValidator {

    public boolean valid(String pesel) {
        checkArgument(isNotBlank(pesel), "20190718195431");

        if (pesel.length() == 11) {
            int pierwszaCyfra = parseInt(pesel.substring(0, 1));
            int drugaCyfra = parseInt(pesel.substring(1, 2));
            int trzeciaCyfra = parseInt(pesel.substring(2, 3));
            int czwartaCyfra = parseInt(pesel.substring(3, 4));
            int piataCyfra = parseInt(pesel.substring(4, 5));
            int szostaCyfra = parseInt(pesel.substring(5, 6));
            int siodmaCyfra = parseInt(pesel.substring(6, 7));
            int osmaCyfra = parseInt(pesel.substring(7, 8));
            int dziewiataCyfra = parseInt(pesel.substring(8, 9));
            int dziesiataCyfra = parseInt(pesel.substring(9, 10));
            int jedenastaCyfra = parseInt(pesel.substring(10, 11));

            int check = 1 * pierwszaCyfra + 3 * drugaCyfra + 7 * trzeciaCyfra
                    + 9 * czwartaCyfra + 1 * piataCyfra + 3 * szostaCyfra + 7
                    * siodmaCyfra + 9 * osmaCyfra + 1 * dziewiataCyfra + 3
                    * dziesiataCyfra;
            int lastNumber = check % 10;
            int controlNumber = 10 - lastNumber;

            if (controlNumber == jedenastaCyfra) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

}
