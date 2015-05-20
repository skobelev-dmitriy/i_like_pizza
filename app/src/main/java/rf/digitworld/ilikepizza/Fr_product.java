package rf.digitworld.ilikepizza;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Дмитрий on 02.03.2015.
 */
public class Fr_product extends Fragment implements View.OnClickListener {
    public static final String TAG ="myLogs";
    ApplicationInterface listener;
    ImageButton cart_butt;
    TextView productCaption;
    TextView productDescription;
    //ImageButton favorite;
    TextView productPrice;
    ImageView productImage;
    ImageView favorite;
    Product product=new Product();
    int id=0;

    public void setProduct(Product pr){
        product=pr;
    }

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
        View v=inflater.inflate(R.layout.fr_product_page, container, false);

        Log.d(TAG,"Fr_cart onCreateView");
        productCaption=(TextView)v.findViewById(R.id.product_caption);
        productDescription=(TextView)v.findViewById(R.id.product_description);
        productPrice=(TextView)v.findViewById(R.id.product_price);
        productImage=(ImageView)v.findViewById(R.id.product_image);
        cart_butt =(ImageButton)v.findViewById(R.id.product_button_cart);

        cart_butt.setOnClickListener(this);

      //  productCaption.setText(product.getName());
        productDescription.setText(product.getDescription());
        productPrice.setText(product.getPriceS());

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.product_button_cart:
                listener.addToCart(product);

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
