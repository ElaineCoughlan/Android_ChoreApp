package projectr.housechores.ConstructorClass;

public class Tasks {
    public int _id;
    public String task;
    public String day;
    public String name;

    public Tasks(){
        this.task="";
        this.day="";
        this.name="";
    }

    public Tasks(String task){
        this.task = task;
        this.day = "";
        this.name="";
    }

    public Tasks(String task, String day){
        this.task = task;
        this.day = day;
        this.name = "";
        }
    public Tasks(String task, String day, String name){
        this.task = task;
        this.day = day;
        this.name = name;
    }

    public int getId() {
        return _id;
    }
    public String getName(){
        return name;
    }
    public String getTask(){
        return task;
    }
    public String getDay(){
        return day;
    }

    public String toString(){
        return _id + " , "+task+" , "+day+" , "+name;
    }

}