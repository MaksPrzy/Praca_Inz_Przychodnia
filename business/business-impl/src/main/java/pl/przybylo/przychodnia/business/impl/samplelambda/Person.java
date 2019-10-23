package pl.przybylo.przychodnia.business.impl.samplelambda;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Person {

    private final String name;
    private final int age;
    private final Address address;

    public static List<Person> getSamplePersonList() {
        return newArrayList(
                new Person("Jan", 32, new Address("Rzeszow", "Miła")),
                new Person("Marek", 93, null),
                new Person("Marcin", 23, new Address("Rzeszow", "Urocza")),
                new Person("Waldek", 12, new Address("Rzeszow", "Warszawska")),
                new Person("Ola", 93, new Address("Warszawa", "Listopadowa"))
        );
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("nazwa", this.name)
                .append("wiek", this.age)
                .build();
    }

}
