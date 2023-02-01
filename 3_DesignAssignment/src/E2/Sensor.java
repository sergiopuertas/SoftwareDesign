package E2;

import java.util.ArrayList;
import java.util.List;
import static E2.Aquarium.report;

public class Sensor{
    List<Alert> alertList=new ArrayList<>();
    private final String name;
    private double value;
    private final double defValue;
    private final double rangeGood;
    private final double rangeOrange;
    public double getValue(){
        return value;
    }
    public double getdefValue(){
        return defValue;
    }
    public String getName() {
        return name;
    }
    private final Tank tank;
    public Tank getTank() {
        return tank;
    }
    private State state;
    public State getstate() {
        return  state;
    }
    protected void setState(State state){
        this.state = state;
    }
    public Sensor(String name, Tank tank, double defValue,double rangeGood, double rangeOrange){
        this.rangeOrange = rangeOrange;
        this.rangeGood= rangeGood;
        this.name = name;
        this.tank = tank;
        this.setState(State.GOOD);
        this.defValue = defValue;
        this.setValue(defValue);
    }
    public void setValue(double value){
        this.value = value;
        double def = getdefValue();
        Alert alert;
        if (Math.abs(def-value) > rangeGood && Math.abs(def-value)<=rangeOrange) {
            this.setState(State.ORANGE);
            alert = this.ringAlert(State.ORANGE);
        }
        else if (Math.abs(def-value) > rangeOrange) {
            this.setState(State.RED);
            alert= this.ringAlert(State.RED);
        }
        else return;
        if(alert.isAble())  {
            report.add(alert);
            getTank().update(alert);
        }
    }
    public Alert ringAlert(State state){
        Alert alert = new Alert(this,state);
        for (Alert al:this.alertList) {
            if(al.equals(alert) && getValue() == al.getValue()) {
                    return al;
            }
        }
        alertList.add(alert);
        return alert;
    }
}
