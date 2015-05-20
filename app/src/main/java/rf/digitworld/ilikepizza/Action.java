package rf.digitworld.ilikepizza;

/**
 * Created by Дмитрий on 02.03.2015.
 */
public class Action {
    String description;
    String name;

    String img;
    int pid;
    public Action(){}
    public Action(String pname, String pdescription){
        name=pname;
        description=pdescription;

    }

    public void setId(int id){
     pid=id;
    }
    public int getId(){
        return  pid;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }



}
