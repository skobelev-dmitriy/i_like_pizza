package rf.digitworld.ilikepizza;

import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by Дмитрий on 06.03.2015.
 */
public class Catalog {
    ArrayList<Product> catalog=new ArrayList<>();
    SharedPreferences sp;

    public Catalog() {
        String[] names={"Дьябола","Калабрия","Лацио","Неаполитана","Тоскана","Чили"};
        float[] price={454.00f,537.00f,467.00f,335.00f,400.00f,489.00f};
        String[] descriptions={"Тесто, соус \"Помадоро\", сыр \"Моцарелла\", сыр \"Пармезан\", колбаса сырокопченная, свежие помидоры, каперсы, маслины, укроп.",
                                "Тесто, соус \"Помадоро\", сыр \"Моцарелла\", сыр \"Пармезан\", подкопчённая свиная шейка, ветчина, колбаса сырокопченая, маслины, укроп.",
                                        "Тесто, соус \"Помадоро\", сыр \"Моцарелла\", сыр \"Пармезан\", филе куриное, ананас, кунжут.",
                               "Тесто, соус \"Помадоро\", сыр \"Моцарелла\", сыр \"Пармезан\", филе куриное, ананас, кунжут.",
        "Тесто, соус \"Помадоро\", сыр \"Моцарелла\", сыр \"Пармезан\", ветчина, шампиньоны, маслины, укроп.",
        "Тесто, соус \"Помадоро\", сыр Моцарелла, сыр \"Пармезан\", куриная грудка, помидоры, болгарский перец, шампиньоны, маслины, укроп.",
        "Тесто, соус \"Помадоро\", сыр \"Моцарелла\", сыр \"Пармезан\", колбаса сырокопченная, болгарский перец, маслины , укроп, соус \"Табаско\"."};
        for(int i=0;i<names.length; i++) {

            Product product=new Product(names[i],descriptions[i],price[i]);
            product.setId(i);
            addProduct(product);
        }

    }
    public void addProduct(Product product){

        catalog.add(product);
    }

    public ArrayList<Product> getProducts(){

        return catalog;

    }
    public int getcount(){

        return  catalog.size();
    }

    public void saveCatalog(){

    }
    public Product getProduct(int id){
        return catalog.get(id);
    }
    public void delProduct(int id){
        catalog.remove(id);
    }
    public ArrayList<Product> getCatalog(){
        return catalog;
    }

}

