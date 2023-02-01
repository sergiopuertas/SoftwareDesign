package e1;


import e1.Artist.Actor;
import e1.Artist.Dubber;
import e1.Artist.Stunt;
import e1.Technician.Director;
import e1.Technician.Musician;
import e1.Technician.Producer;
import e1.Technician.Screenwriter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FilmTest {
    /*
    * Our approach concerning the declarations of the members of the crew is the following:
    * So as to easing the declarations, each member will have an assigned film (which makes sense, as no character -which implies no worker- can be declared
    * without a film) so that no inner implementation management will be needed like, for example, adding it to a list. Our approach is more abstract and user-friendly.
    * The inner code does all que assignments to the lists needed.
    * */
    private static final Film operacionCamaron = new Film("Operacion Camaron",200000);
    private static final Film Popeye = new Film("Popeye", 345678985);
    private static final Actor w1 = new Actor("pepe","lopez", "1","981632213","españa",60,"Protagonist", Popeye);
    private static final Actor w2 = new Actor("pipa","lopez", "2","981665213","españa",90,"Secondary", Popeye);
    private static final Dubber w3 = new Dubber("mino","garcia","3", "56787657","peru", 45, Popeye);
    private static final Stunt w4 =new Stunt("juan","dominguez","4","5678765456","LaCoru",75,true, Popeye);
    private static final Director w5 =new Director("sergio","doors", "5", "604051767","españa",78,4,operacionCamaron);
    private static final Musician w6 = new Musician("toño","pernas", "6","780234234","coru",56,operacionCamaron);
    private static final Producer w7 = new Producer("mark","seco","7", "7492724924","USA",90,operacionCamaron);
    private static final Screenwriter w8 =new Screenwriter("th","seijas","8", "7492724924","ESPAÑA",87,false,operacionCamaron);
    @Test
    void getInbox(){
        assertEquals(200000,operacionCamaron.getInbox());
        assertEquals(345678985, Popeye.getInbox());
    }
    @Test
    void getTitle (){
        assertEquals("Operacion Camaron",operacionCamaron.getTitle());
        assertEquals("Popeye", Popeye.getTitle());
    }
    @Test
    void printSalaries(){
        assertEquals("""
                sergio doors (Director with extra for 4 years of seniority): 11800 euro
                toño pernas (Musician):3360 euros
                mark seco (Producer):8100 euros
                th seijas (Screenwriter ): 6090 euro
                The total payroll for Operacion Camaron is 29350 euros""",operacionCamaron.printSalaries());
        assertEquals("""
                pepe lopez (Actor with extra for protagonism): 36000 euro
                pipa lopez (Actor ): 18000 euro
                mino garcia (Dubber):900 euros
                juan dominguez (Stunt with extra for danger): 4000 euro
                The total payroll for Popeye is 58900 euros""", Popeye.printSalaries());
    }
    @Test
    void printRoyalties(){
        assertEquals("""
                sergio doors  (Director):  10000.0  euros
                toño pernas  (Musician):  8000.0  euros
                mark seco  (Producer):  4000.0  euros
                th seijas  (Screenwriter):  10000.0  euros
                """,operacionCamaron.printRoyalties());
        assertEquals("""
                """ , Popeye.printRoyalties());
    }
    @Test
    void getBonus(){
        assertEquals(400*60, w1.getBonus());
        assertEquals(0, w2.getBonus());
        assertEquals(0, w3.getBonus());
        assertEquals(1000, w4.getBonus());
        assertEquals(4000, w5.getBonus());
        assertEquals(0, w6.getBonus());
        assertEquals(0, w7.getBonus());
        assertEquals(0, w8.getBonus());
    }

    @Test
    void getRoyalties(){
        assertEquals(0, w1.getRoyalties());
        assertEquals(0, w2.getRoyalties());
        assertEquals(0, w3.getRoyalties());
        assertEquals(0, w4.getRoyalties());
        assertEquals(0.05, w5.getRoyalties());
        assertEquals(0.04, w6.getRoyalties());
        assertEquals(0.02, w7.getRoyalties());
        assertEquals(0.05, w8.getRoyalties());
    }

    @Test
    void getFilm(){
        assertEquals(Popeye, w1.getFilm());
        assertEquals(Popeye, w2.getFilm());
        assertEquals(Popeye, w3.getFilm());
        assertEquals(Popeye, w4.getFilm());
        assertEquals(operacionCamaron, w5.getFilm());
        assertEquals(operacionCamaron, w6.getFilm());
        assertEquals(operacionCamaron, w7.getFilm());
        assertEquals(operacionCamaron, w8.getFilm());
    }
    @Test
    void getPhone(){
        assertEquals("981632213", w1.getPhonenumber());
        assertEquals("981665213", w2.getPhonenumber());
        assertEquals("56787657", w3.getPhonenumber());
        assertEquals("5678765456", w4.getPhonenumber());
        assertEquals("604051767", w5.getPhonenumber());
        assertEquals("780234234", w6.getPhonenumber());
        assertEquals("7492724924", w7.getPhonenumber());
        assertEquals("7492724924", w8.getPhonenumber());
    }
    @Test
    void getSurname(){
        assertEquals("lopez", w1.getSurname());
        assertEquals("lopez", w2.getSurname());
        assertEquals("garcia", w3.getSurname());
        assertEquals("dominguez", w4.getSurname());
        assertEquals("doors", w5.getSurname());
        assertEquals("pernas", w6.getSurname());
        assertEquals("seco", w7.getSurname());
        assertEquals("seijas", w8.getSurname());
    }
    @Test
    void getName(){
        assertEquals("pepe", w1.getName());
        assertEquals("pipa", w2.getName());
        assertEquals("mino", w3.getName());
        assertEquals("juan", w4.getName());
        assertEquals("sergio", w5.getName());
        assertEquals("toño", w6.getName());
        assertEquals("mark", w7.getName());
        assertEquals("th", w8.getName());
    }
    @Test
    void getId(){
        assertEquals("Artist", w1.getCategory());
        assertEquals("Artist", w2.getCategory());
        assertEquals("Artist", w3.getCategory());
        assertEquals("Artist", w4.getCategory());
        assertEquals("Technician", w5.getCategory());
        assertEquals("Technician", w6.getCategory());
        assertEquals("Technician", w7.getCategory());
        assertEquals("Technician", w8.getCategory());
    }
    @Test
    void getCategory(){
        assertEquals("1", w1.getId());
        assertEquals("2", w2.getId());
        assertEquals("3", w3.getId());
        assertEquals("4", w4.getId());
        assertEquals("5", w5.getId());
        assertEquals("6", w6.getId());
        assertEquals("7", w7.getId());
        assertEquals("8", w8.getId());
    }

    @Test
    void getJob(){
        assertEquals("Actor", w1.getJob());
        assertEquals("Actor", w2.getJob());
        assertEquals("Dubber", w3.getJob());
        assertEquals("Stunt", w4.getJob());
        assertEquals("Director", w5.getJob());
        assertEquals("Musician", w6.getJob());
        assertEquals("Producer", w7.getJob());
        assertEquals("Screenwriter", w8.getJob());
    }

    @Test
    void getNationality(){
        assertEquals("españa", w1.getNationality());
        assertEquals("españa", w2.getNationality());
        assertEquals("peru", w3.getNationality());
        assertEquals("LaCoru", w4.getNationality());
        assertEquals("españa", w5.getNationality());
        assertEquals("coru", w6.getNationality());
        assertEquals("USA", w7.getNationality());
        assertEquals("ESPAÑA", w8.getNationality());
    }
    @Test
    void getHours(){
        assertEquals(60, w1.getHours());
        assertEquals(90, w2.getHours());
        assertEquals(45, w3.getHours());
        assertEquals(75, w4.getHours());
        assertEquals(78, w5.getHours());
        assertEquals(56, w6.getHours());
        assertEquals(90, w7.getHours());
        assertEquals(87, w8.getHours());
    }
    @Test
    void getSalaryPerHour(){
        assertEquals(200, w1.getSalaryPerHour());
        assertEquals(200, w2.getSalaryPerHour());
        assertEquals(20, w3.getSalaryPerHour());
        assertEquals(40, w4.getSalaryPerHour());
        assertEquals(100, w5.getSalaryPerHour());
        assertEquals(60, w6.getSalaryPerHour());
        assertEquals(90, w7.getSalaryPerHour());
        assertEquals(70, w8.getSalaryPerHour());
    }
    @Test
    void getBonusText(){
        assertEquals("with extra for protagonism", w1.getBonusText());
        assertEquals("", w2.getBonusText());
        assertEquals("", w3.getBonusText());
        assertEquals("with extra for danger", w4.getBonusText());
        assertEquals("with extra for " +w5.getSeniority()+" years of seniority", w5.getBonusText());
        assertEquals("", w6.getBonusText());
        assertEquals("", w7.getBonusText());
        assertEquals("", w8.getBonusText());
    }

}
