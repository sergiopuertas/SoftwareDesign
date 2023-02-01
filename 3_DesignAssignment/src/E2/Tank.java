package E2;
public class Tank extends Aquarium implements Observers{
    private final String location;
    public String getLocation(){
        return location;
    }
    private final String name;
    public String getName(){
        return name;
    }
    public Tank (String name,String location){
        this.name = name;
        this.location= location;
    }
    @Override
    public void update(Alert alert){
        notifyObservers(alert);
    }
}
