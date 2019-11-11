package pl.przybylo.przychodnia.mapper.wizyta;

import org.springframework.stereotype.Component;
import pl.przybylo.przychodnia.domain.model.wizyta.Status;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
public class StatusMapper {

    public String map(Status status) {
        if (isNull(status)) {
            return null;
        }

        return status.getCode();
    }

    public Status map(String status) {
        if (isBlank(status)) {
            return null;
        }

        return Status.findByCode(status);
    }

}
