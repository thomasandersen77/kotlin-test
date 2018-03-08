package stackowerflow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormat {

    private static final DateTimeFormatter BIRTDATE_FORMAT = DateTimeFormatter.ofPattern("dd.MMMM.yyyy");
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy");

    public static void main(String[] args) {
        Persons person = new Persons();
        String text = "John.Davidson/05051988/Belgrade Michael.Barton/01011968/Krakov Ivan.Perkinson/23051986/Moscow";
        String[] valami = text.split("[ ./]+");
        for(int i=0; i < valami.length ; i+=4){
            person.name = valami[i];
            person.lastName = valami[i+1];
            person.dateBirth = LocalDate.parse(valami[i+2], dtf).format(BIRTDATE_FORMAT);
            person.Birthplace = valami[i+3];
            System.out.println(person);
        }}

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

