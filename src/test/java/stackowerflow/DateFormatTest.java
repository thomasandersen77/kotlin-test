package stackowerflow;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RunWith(JUnit4.class)
public class DateFormatTest {

    private static final DateTimeFormatter BIRTDATE_FORMAT = DateTimeFormatter.ofPattern("dd.MMMM.yyyy");
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");

    @Test
    public void test_date_formatting() {
        Persons person = new Persons();
        String text = "John.Davidson/05051988";
        String[] valami = text.split("[ ./]+");
        for (int i = 0; i < valami.length; i += 2) {
            person.name = valami[i];
            person.lastName = valami[i];
            person.dateBirth = LocalDate.parse(valami[i + 3], dtf).format(BIRTDATE_FORMAT);
            System.out.println(person);
            Assert.assertEquals("05.May.1988", person.dateBirth);
        }
    }


    private static class Persons {
        String name;
        String lastName;
        String dateBirth;
        String Birthplace;

        @Override
        public String toString (){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MMM.yyyy.");
            String s = String.format("dd.$s.yyyy", dtf);

            return "Ime: " + this.name + " , "+ "Prezime: " + this.lastName + " , " + "Datum rodjenja: " + this.dateBirth + " , " + "Mesto rodjenja: " + this.Birthplace;

        }

    }
}



