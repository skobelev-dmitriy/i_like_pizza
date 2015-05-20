package rf.digitworld.ilikepizza;

import java.util.ArrayList;

/**
 * Created by Дмитрий on 02.03.2015.
 */
public interface ApplicationInterface {
    public void openProduct( int id);
    public void delProduct(int id);
    public void openCart();
    public void openProfile();
    public void addToCart(Product product);
    public Cart getCart();
    public void openDelivery();
    public void orderOk(String address, String tel,String komment);
    public void goToCatalog();

}
