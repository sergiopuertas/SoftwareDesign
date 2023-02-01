package E2;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;

public class Alert implements Comparable<Alert>{
    LocalDateTime time;
    public void setTime() {
        this.time = LocalDateTime.now();
    }
    private final State state;
    private final Sensor sensor;
    private boolean able;
    private double value;
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    public boolean isAble() {
        return able;
    }

    public State getState() {
        return state;
    }
    public Sensor getSensor() {
        return sensor;
    }

    public String getDevice(){
        return sensor.getName()+" control device";
    }
    public Alert(Sensor sensor, State state){
        this.setValue(sensor.getValue());
        this.sensor = sensor;
        this.state = state;
        this.setTime();
        sensor.alertList.add(this);
        this.able = true;
    }
    public void disable(){
        this.able = false;
    }
    public void enable(){
        this.able = true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alert alert = (Alert) o;
        return state == alert.state && Objects.equals(sensor, alert.sensor);
    }
    public static class AlertComparator implements Comparator<Alert> {
        public int compare(Alert a1, Alert a2){
            return a1.compareTo(a2);
        }
    }
    @Override
    public int compareTo(Alert o) {
        return o.getState().getNum() - this.getState().getNum();
    }

    @Override
    public String toString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return getState()+" Alert:\n"+"Tank: "+sensor.getTank().getName()+", "+sensor.getTank().getLocation()
                +"\n"+getDevice()+". Parameter "+sensor.getName()+": level "+ getValue() +"\n"+
                dtf.format(time);
    }


}

