package e2;

import java.util.Iterator;

public class SelectCandidates {
    public static String getWinner(TVRealityList <String> list, int increment){
        if(list.isEmpty()) throw new IllegalArgumentException("Empty list cannot be iterated");
        if (increment> list.size() || increment<0) throw new IllegalArgumentException();

        Iterator<String> it = list.iterator();
        if(list.rebound) System.out.printf("Using the rebound algorithm with increment of %d:\n\n", increment);
        else System.out.printf("Using the circular algorithm with increment of %d:\n\n", increment);

        while(it.hasNext()){
            int i = 0;
            System.out.println(it);
            while(i < increment) {
                it.next();
                i++;
            }
            it.remove();
        }
        System.out.println("And the winner of the last spot is... "+list.candidates.get(0).toUpperCase()+"!!!!\n");
        return list.candidates.get(0);
    }
}

