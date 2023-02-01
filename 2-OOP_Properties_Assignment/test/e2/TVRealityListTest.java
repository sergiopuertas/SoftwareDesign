package e2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

class TVRealitylistTest {
    private static final List<String> candidates = new ArrayList<>();
    private static final List<String> empty = new ArrayList<>();

    @BeforeAll
    static void insertCandidates() {
        candidates.add("pepe");
        candidates.add("pepa");
        candidates.add("pupi");
        candidates.add("papi");
        candidates.add("popi");
    }
    @Test
    void testConstRebound() {
        TVRealityList<String> listreb = new TVRealityList<>(true,candidates);
        assertTrue(listreb.rebound);
        assertEquals(candidates,listreb.candidates);
    }
    @Test
    void testConstCirc() {
        TVRealityList<String> listcirc = new TVRealityList<>(false,candidates);
        assertFalse(listcirc.rebound);
        assertEquals(candidates,listcirc.candidates);
    }
    @Test
    void testReb(){
        TVRealityList<String> listreb = new TVRealityList<>(true,candidates);
        assertEquals("pepa", SelectCandidates.getWinner(listreb,4));
    }
    @Test
    void testCirc(){
        candidates.clear();
        insertCandidates();
        TVRealityList<String> listcirc = new TVRealityList<>(false,candidates);
        assertEquals("pepe", SelectCandidates.getWinner(listcirc,4));
    }
    @Test
    void testInc0(){
        candidates.clear();
        insertCandidates();
        TVRealityList<String> listreb = new TVRealityList<>(true,candidates);
        TVRealityList<String> listcirc = new TVRealityList<>(false,candidates);
        assertThrows(IllegalStateException.class, ()->SelectCandidates.getWinner(listreb,0));
        assertThrows(IllegalStateException.class, ()->SelectCandidates.getWinner(listcirc,0));
    }
    @Test
    void testEmpty(){
        TVRealityList<String> listemp = new TVRealityList<>(false,empty);
        assertThrows(IllegalArgumentException.class, ()->SelectCandidates.getWinner(listemp,3));
    }
    @Test
    void testBounds(){
        TVRealityList<String> listcirc = new TVRealityList<>(false,candidates);
        assertThrows(IllegalArgumentException.class, ()->SelectCandidates.getWinner(listcirc,8));
        assertThrows(IllegalArgumentException.class, ()->SelectCandidates.getWinner(listcirc,-4));
    }
}
