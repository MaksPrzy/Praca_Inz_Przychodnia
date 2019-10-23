package pl.przybylo.przychodnia.business.impl.samplelambda;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class Lambda {

    public static void main(String[] args) {
        System.out.println("lambda test");

        List<Person> personList = Person.getSamplePersonList();
        personList.add(null);

        List<Person> marcinList = new ArrayList<>();

        for (int idx = 0; idx < personList.size(); idx++) {
            Person person = personList.get(idx);

            if (nonNull(person) && "marcin".equalsIgnoreCase(person.getName())) {
                marcinList.add(person);
            }
        }

        List<String> marcin2NameList = personList.stream()
                .filter(p -> nonNull(p))
                .filter(p -> "marcin".equalsIgnoreCase(p.getName()))
                .map(p -> p.getName())
                .distinct()
                .collect(Collectors.toList());



//        System.out.println("size: " + marcinList.size());
//        System.out.println(Joiner.on(", ").join(marcinList));
//        System.out.println(Joiner.on(", ").join(marcin2NameList));


        // imiona wszystkich osob mieszkajacych w rzeszowie

        List<String> personNamesFromRzeszowList = personList.stream()
                .filter(person -> nonNull(person) && nonNull(person.getAddress()))
                .filter(person -> "rzeszow".equalsIgnoreCase(person.getAddress().getCity()))
                .filter(person -> "urocza".equalsIgnoreCase(person.getAddress().getStreet()))
                .map(person -> person.getName())
                .collect(Collectors.toList());

        System.out.println(Joiner.on(", ").join(personNamesFromRzeszowList));
    }

}
