package rf.digitworld.ilikepizza;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Дмитрий on 02.03.2015.
 */
public class Fr_cart extends Fragment implements View.OnClickListener {
    public static final String TAG ="myLogs";
    ApplicationInterface listener;
    LinearLayout spisok,layout,emptyCart ;
    Button order, catalog, catalog2;
    TextView total;
    float sum;
    Cart cart;

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        try {
            listener=(ApplicationInterface)activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+" must implement ApplicationInterface");

        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        //return super.onCreateView(inflater, container, savedInstanceState);
     //   api=new Api();
        View v=inflater.inflate(R.layout.fr_cart, container, false);
        Log.d(TAG,"Fr_cart onCreateView");
        spisok=(LinearLayout)v.findViewById(R.id.cart_list);
        layout=(LinearLayout)v.findViewById(R.id.cart_layout);
        emptyCart=(LinearLayout)v.findViewById(R.id.empty_cart);
        total=(TextView)v.findViewById(R.id.total);
        order=(Button)v.findViewById(R.id.order);
        order.setOnClickListener(this);
        catalog=(Button)v.findViewById(R.id.butt_go_catalog);
        catalog.setOnClickListener(this);
        catalog2=(Button)v.findViewById(R.id.button2);
        catalog2.setOnClickListener(this);

        updateView();


        return v;
    }
    void updateView(){
        sum=0;
        spisok.removeAllViewsInLayout();
        LayoutInflater layoutInflater=getActivity().getLayoutInflater();
        cart=listener.getCart();

        if (cart.getcount()>0) {
            emptyCart.setVisibility(View.GONE);
            catalog.setVisibility(View.GONE);
            spisok.setVisibility(View.VISIBLE);
            order.setVisibility(View.VISIBLE);
            total.setVisibility(View.VISIBLE);
            catalog2.setVisibility(View.VISIBLE);


            for (int i = 0; i < cart.getcount(); i++) {
                View item = layoutInflater.inflate(R.layout.cart_list_item, spisok, false);
                TextView caption = (TextView) item.findViewById(R.id.item_caption);
                TextView price = (TextView) item.findViewById(R.id.item_price);
                ImageView im = (ImageView) item.findViewById(R.id.item_image);
               ImageView del = (ImageView) item.findViewById(R.id.butt_del);
                Product product = cart.getProduct(i);


                caption.setText(product.getName());
                price.setText(product.getPriceS());
                sum += product.getPricef();
                del.setId(i);

               del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.delProduct(v.getId());
                        updateView();
                    }
                });
                item.setClickable(true);


                spisok.addView(item);
            }
            total.setText("Итого: " + String.format("%.02f",sum) + " руб.");
        } else {
            emptyCart.setVisibility(View.VISIBLE);
            catalog.setVisibility(View.VISIBLE);
            spisok.setVisibility(View.GONE);
            order.setVisibility(View.GONE);
            total.setVisibility(View.GONE);
            catalog2.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.order:
                listener.openDelivery();
                break;
            case R.id.butt_go_catalog:
                listener.goToCatalog();
                break;
            case R.id.button2:
                listener.goToCatalog();
                break;
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();

       /* if (asyncLogin!=null) {
            Log.d(TAG, "asyncLogin cancel " + asyncLogin.cancel(false));
        }*/
    }
}
