package E2;


import java.util.ArrayList;
import java.util.List;

public class ControlDevice implements Observers {
    List<Alert> alertList;
    public ControlDevice(Tank tank){
        this.alertList = new ArrayList<>();
        tank.attach(this);
    }
    public void addAlert(Sensor sensor, State state){
        Alert alert = new Alert(sensor,state);
        this.addAlert(alert);
    }
    public void addAlert(Alert alert){
        addChore(alert, alertList);
    }
    static void addChore(Alert alert, List<Alert> alertList) {
        boolean was=false;
        for (Alert a: alertList) {
            if (a.equals(alert) && a.getValue()==alert.getSensor().getValue()) {
                was = true;
                break;
            }
        }
        if (!was) alertList.add(alert);
    }

    @Override
    public void update(Alert alert) {
        if(this.alertList.contains(alert)){
            alert.getSensor().setValue(alert.getSensor().getdefValue());
            alert.getSensor().setState(State.GOOD);
        }
    }
}
