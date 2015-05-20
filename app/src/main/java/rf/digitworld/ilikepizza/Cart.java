package rf.digitworld.ilikepizza;

import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by Дмитрий on 02.03.2015.
 */
public   class Cart {
    ArrayList<Product> cart=new ArrayList<>();
    SharedPreferences sp;

    public void addToCart(Product product){
         cart.add(product);
     }

    public void getProducts(){



    }
    public int getcount(){

        return  cart.size();
    }

    public void saveCart(){

    }
    public void clearCart(){
        cart.clear();
    }
    public Product getProduct(int id){
        return cart.get(id);
    }
    public void delProduct(int id){
        cart.remove(id);
    }
    public ArrayList<Product> getCart(){
        return cart;
    }
    public String getProductsList(){
        String order="<table >\n" +
                "<tr>\n" +
                "<th>Наименование</th>\n" +
                "<th>Цена</th>\n" +
                "<th>Кол-во</th>\n" +
                "<th>Итого</th>\n" +
                "</tr>\n" ;
        Float sum=0f;
        for (Product product: cart){
            int kol=1;
            Float sumPos=product.getPricef()*kol;
            sum+=sumPos;

            order+="<tr><td>"+product.getName()+"</td><td>"+String.format("%.02f",product.getPricef())+"</td><td>"+kol+"</td><td>"+String.format("%.02f",sumPos)+"</td></tr>";
        }
        order+="<tr>\n" +
                "<td colspan=\"3\">Итого</td>\n" +
                "<td>"+String.format("%.02f",sum)+"</td>\n" +
                "</tr>\n" +
                "</table>";
        return order;
    }

}
