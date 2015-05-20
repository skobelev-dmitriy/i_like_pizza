package rf.digitworld.ilikepizza;

import android.util.Log;

/**
 * Created by Дмитрий on 02.03.2015.
 */
public class Product {
    String description;
    String name;
    float price;
    String img;
    int pid;
    public Product(){}
    public Product( String pname, String pdescription,float pprice){
        name=pname;
        description=pdescription;
        price=pprice;
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

    public float getPricef(){
        return price;
    }
    public String getPriceS(){
       String priceStr=String.format("%.02f",price);
        priceStr+=" руб.";
        return priceStr;
    }

}
