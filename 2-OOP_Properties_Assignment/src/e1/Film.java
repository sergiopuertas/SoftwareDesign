package e1;

import java.util.ArrayList;
import java.util.List;

public class Film {
    List<Worker> workerList;
    private final String title;
    private final int inbox;
    public int getInbox(){
        return inbox;
    }
    public String getTitle(){
        return title;
    }
    public Film(String title, int inbox){
        workerList = new ArrayList<>();

        this.title = title;
        this.inbox = inbox;
    }
    public void insertWorker(Worker worker){
        this.workerList.add(worker);
    }
    public String printSalaries() {
        int sum =0;
        String all = "";
        for(Worker worker : this.workerList){
            all= all.concat(worker.toString()+"\n");
            sum+=worker.getSalaryPerHour()* worker.getHours() + worker.getBonus();
        }
        if(getInbox()<sum) all=all.concat("The film has been a dismal failure!! (you shouldn't have let your brother-in-law film it)\n");
        return all.concat("The total payroll for "+ this.getTitle()+" is "+sum+" euros");
    }
    public String printRoyalties() {
        String all="";
        for(Worker worker : this.workerList){
            if(worker.getCategory().equalsIgnoreCase("Technician")) {
                all = all.concat(worker.getName() + " " + worker.getSurname() + " " + " (" + worker.getJob() + "): " + " "
                        +worker.getRoyalties()*getInbox()+ " " + " euros\n");
            }
        }
        return all;
    }
}