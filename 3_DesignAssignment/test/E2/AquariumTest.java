package E2;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(OrderAnnotation.class)

public class AquariumTest {
    private static final Tank morsas = new Tank("Morsas Antárticas","Fuera");
    private static final Tank medusas = new Tank("Medusas jamaicanas","Pasillo tropical");

    private static final Sensor oxiMors = new Sensor("Oxygen",morsas,25,2,5);
    private static final Sensor phMors = new Sensor("Ph",morsas,7,2,5);
    private static final Sensor tempMors = new Sensor("Temperature",morsas,5,2,5);

    private static final Sensor oxiMed = new Sensor("Oxygen",medusas,25,2,5);
    private static final Sensor phMed = new Sensor("Ph",medusas,7,2,5);
    private static final Sensor tempMed = new Sensor("Temperature",medusas,20,2,5);

    private static final ControlDevice c1 = new ControlDevice(medusas);
    private static final ControlDevice c2 = new ControlDevice(morsas);

    private static final Personnel Jeronimo = new Personnel("Jeronimo");
    private static final Personnel Berta = new Personnel("Berta");

    @BeforeAll
    static void AddChores(){
        c1.addAlert(tempMed,State.RED);
        c1.addAlert(tempMed,State.ORANGE);
        c1.addAlert(phMed,State.RED);
        c1.addAlert(oxiMed,State.ORANGE);

        Alert a1 = new Alert(tempMors,State.RED);
        c2.addAlert(a1);
        c2.addAlert(oxiMors,State.ORANGE);
        c2.addAlert(phMors,State.RED);
        c2.addAlert(phMors,State.ORANGE);

        Jeronimo.addTask(tempMed,State.RED);
        Jeronimo.addTask(phMors,State.RED);
        Jeronimo.addTask(oxiMors,State.ORANGE);
        Berta.addTask(tempMed,State.ORANGE);
        Berta.addTask(tempMed,State.RED);
    }
    @Test
    @Order(1)
    void setStates(){
        phMed.setValue(4);//Orange
        oxiMed.setValue(1);//red & not dealt

        tempMed.setValue(-1);//red
        tempMed.setValue(2);//red


        tempMors.setValue(1);//orange
        oxiMors.setValue(22);//orange
        phMors.setValue(1);//red
    }
    @Test
    @Order(2)
    void checkChoresandStates(){
        assertEquals(4,c2.alertList.size());
        assertEquals(3,Jeronimo.alertList.size());
        assertEquals(2,Berta.alertList.size());
        tempMors.ringAlert(State.ORANGE).disable();
    }
    @Test
    @Order(3)
    void checkStatesFixed(){
        Personnel.printReport();
        assertEquals(State.RED,oxiMed.getstate());
        assertEquals(State.GOOD,oxiMors.getstate());
        assertEquals(State.GOOD,tempMed.getstate());
        assertEquals(State.ORANGE,tempMors.getstate());//disabled
        assertEquals(State.GOOD,phMors.getstate());
    }
    @Test
    @Order(4)
    void TestNotify(){
        //oxiMed and tempMors actually have dangerous values. We're going to simulate the setvalue operation by only calling notifyobservers
        assertEquals(State.RED,oxiMed.getstate());
        assertEquals(State.ORANGE,tempMors.getstate());//disabled
        Alert a1 = tempMors.ringAlert(State.ORANGE);
        a1.enable();
        Alert a2 = oxiMed.ringAlert(State.RED);
        assertFalse(c2.alertList.contains(a1));
       c2.addAlert(a1);
       assertTrue(c2.alertList.contains(a1));
       assertFalse(c1.alertList.contains(a2));
       c1.addAlert(a2);
       assertTrue(c1.alertList.contains(a2));

       morsas.notifyObservers(a1);
       medusas.notifyObservers(a2);
       assertEquals(State.GOOD, tempMors.getstate());
       assertEquals(State.GOOD, oxiMed.getstate());
    }
}
