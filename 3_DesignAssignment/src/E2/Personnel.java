package E2;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static E2.Aquarium.report;

public class Personnel{
    static List<Alert> reportList = new ArrayList<>();
    static List<Personnel> crew = new ArrayList<>();
    List<Alert> alertList;
    private final String name ;
    public String getName() {
        return name;
    }
    public Personnel(String name){
        this.name = name;
        this.alertList = new ArrayList<>();
        crew.add(this);
    }
    public void addTask(Sensor sensor, State state){
        Alert alert = new Alert(sensor,state);
        this.addTask(alert);
    }
    public void addTask(Alert alert){
        ControlDevice.addChore(alert, alertList);
    }
    static private void transform(PriorityQueue<Alert> queue){
        while(!queue.isEmpty()){
            reportList.add(queue.poll());
        }
    }
    public static void printReport(){
        transform(report);
        for (Personnel p: crew) {
            boolean redw=false, oraw=false;
            System.out.println("Alerts of maintenance: "+p.getName()+"\n");
            for (Alert al:reportList) {
                if(p.alertList.contains(al)){
                    if(al.getState()==State.RED && !redw){
                        System.out.println("RED ALERTS");
                        redw = true;
                    }
                    if(al.getState()==State.ORANGE && !oraw){
                        System.out.println("ORANGE ALERTS");
                        oraw = true;
                    }
                    System.out.println(al+"\n");
                }
            }
        }
    }
}
