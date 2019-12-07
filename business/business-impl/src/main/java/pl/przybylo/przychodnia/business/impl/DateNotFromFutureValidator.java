package pl.przybylo.przychodnia.business.impl;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static java.time.LocalDate.now;
import static pl.wavesoftware.eid.utils.EidPreconditions.checkNotNull;

@Component
public class DateNotFromFutureValidator {

    public boolean valid(LocalDate date) {
        checkNotNull(date, "20190725191614");

        return date.isBefore(now());
    }

}
