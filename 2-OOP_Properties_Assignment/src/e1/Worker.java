package e1;

public abstract class Worker{
    protected void set(String name,String surname, String id,String phonenumber,String nationality, int hours,Film film) {
        this.setName(name);
        this.setSurname(surname);
        this.setId(id);
        this.setPhonenumber(phonenumber);
        this.setNationality(nationality);
        this.setHours(hours);
        this.setFilm(film);
        film.insertWorker(this);
    }
    private Film film;
    public Film getFilm(){
        return film;
    }
    public void setFilm(Film film){
        this.film = film;
    }
    private String name;
    private String surname;
    private int salaryPerHour;
    private  int bonus;
    private int hours;
    private String phonenumber;
    private String id;
    private String nationality;
    private String category;
    private String job;
    private String bonusText;

    private double royalties;
    public double getRoyalties() {
        return royalties;
    }
    public void setRoyalties(double royalties) {
        this.royalties = royalties;
    }
    public String getBonusText(){
        if (bonusText ==null) return "";
        return bonusText;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getPhonenumber() {
        return phonenumber;
    }
    public String getId() {
        return id;
    }
    public String getNationality() {return nationality;}
    public String getCategory() {
        return category;
    }
    public int getSalaryPerHour() {
        return salaryPerHour;
    }
    public int getBonus(){
        return bonus;
    }
    public int getHours(){
        return hours;
    }

    public String getJob() {
        return job;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setSalaryPerHour(int salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }
    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
    public void setHours(int hours) {
        this.hours = hours;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public void setJob(String job) {
        this.job = job;
    }
    public void setBonustext(String bonustext){
        if(this.getBonus()>0)
            this.bonusText = bonustext;
        else this.bonusText ="";
    }


}