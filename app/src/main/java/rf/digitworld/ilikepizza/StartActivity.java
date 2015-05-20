package rf.digitworld.ilikepizza;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.AdapterView;
import android.widget.ImageView;

import android.widget.Toast;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.json.JSONObject;

import java.util.ArrayList;


public class StartActivity extends ActionBarActivity implements View.OnClickListener, ApplicationInterface {
    public static final String TAG="myLogs";

    private static final int STOPSPLASH=0;
    private static final long SPLASHTIME=1000;
    Cart myCart=new Cart();
    Catalog myCat=new Catalog();
    Drawer.Result drawer;
    Api api=new Api();

    FragmentManager fragmentManager=getFragmentManager();
    Fr_start fr_start=new Fr_start();
    Fr_cart fr_cart=new Fr_cart();
    Fr_about fr_about=new Fr_about();
    Fr_contacts fr_contacts=new Fr_contacts();
    Fr_actions fr_actions=new Fr_actions();
    Fr_product fr_product=new Fr_product();
    Fr_profile fr_profile=new Fr_profile();
    Fr_delivery fr_delivery=new Fr_delivery();


    MenuItem cart_icon_menu;


    ImageView splash;
    Toolbar toolbar;


    private   Handler splashHandler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case STOPSPLASH:
                    splash.setVisibility(View.VISIBLE);
                    ViewPropertyAnimator anim =splash.animate();


                   	anim.alpha(0f);
                    	anim.start();

            }
            super.handleMessage(msg);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_);
        splash=(ImageView)findViewById(R.id.splash);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));


       //

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.contaner, fr_start).commit();
        drawer=new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.drawer_header)
                .withOnDrawerItemClickListener( new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {

                    switch (i){
                        case 1:
                            fragmentTransact(fr_start,"Каталог");
                            break;
                        case 2:
                            fragmentTransact(fr_cart,"Моя корзина");
                            break;
                        case 3:
                            fragmentTransact(fr_actions,"Акции и скидки");
                            break;
                      /*  case 4:
                            fragmentTransact(fr_profile,"Мой профиль");
                            break;*/
                        case 5:
                            fragmentTransact(fr_contacts,"Контакты");
                            break;
                        case 6:
                               fragmentTransact(fr_about,"О приложении");
                            break;
                        default:
                            break;
                    }
                    }
                })
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withName(R.string.catalog).withIcon(FontAwesome.Icon.faw_list).withBadge("").withIdentifier(1),
                        new PrimaryDrawerItem()
                                .withName(R.string.cart).withIcon(FontAwesome.Icon.faw_shopping_cart).withBadge(Integer.toString(myCart.getcount())).withIdentifier(2),
                        new PrimaryDrawerItem()
                                .withName(R.string.actions).withIcon(FontAwesome.Icon.faw_gamepad),
                    //    new PrimaryDrawerItem()
                    //            .withName(R.string.profile).withIcon(FontAwesome.Icon.faw_gamepad),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.contacts).withIcon(FontAwesome.Icon.faw_info).withIdentifier(3),
                        new SecondaryDrawerItem().withName(R.string.about).withIcon(FontAwesome.Icon.faw_info).setEnabled(true)
                )
                .build();
        Message msg=new Message();
        msg.what=STOPSPLASH;
        splashHandler.sendMessageDelayed(msg,SPLASHTIME);
    /*    */



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.cart_icon:
                fragmentTransact(fr_cart,"Моя корзина");
                break;
            case R.id.share:
                final Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                //String textToSend=getResources().getString(R.string.text_to_share);
                String textToSend="Я заказываю пиццу в  vk.com/club84941785";



                intent.putExtra(Intent.EXTRA_TEXT,textToSend);
                try{
                    startActivity(Intent.createChooser(intent,"С кем бы поделиться?"));
                }catch (ActivityNotFoundException e){
                    Toast.makeText(getApplicationContext(),"Не скем поделиться:(", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.vk_login:



                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {


    }
    public void updateMenuCartIcon(){
        cart_icon_menu=(MenuItem)toolbar.getMenu().findItem(R.id.cart_icon);
       int count= myCart.getcount();
        switch (count){
            case 0:
                cart_icon_menu.setIcon(getResources().getDrawable(R.drawable.cart_outline));
                break;
            case 1:
            cart_icon_menu.setIcon(getResources().getDrawable(R.drawable.cart));
                break;
        }
    }

    @Override
    public void openProduct(int id) {
        fragmentTransact(fr_product,myCat.getProduct(id).getName());
        fr_product.setProduct(myCat.getProduct(id));
    }
    @Override
    public void delProduct(int id) {
        myCart.delProduct(id);
        updateMenuCartIcon();
        drawer.updateBadge(Integer.toString(myCart.getcount()),2);
        Log.d(TAG,"StartActivity.delProduct");
    }

    @Override
    public void openCart() {
        Log.d(TAG,"StartActivity.openCart");
        fragmentTransact(fr_cart, "Моя корзина");
    }

    @Override
    public void openProfile() {
        Log.d(TAG,"StartActivity.openProfile");
    }
    @Override
    public void openDelivery() {
        Log.d(TAG,"StartActivity.openCart");
        fragmentTransact(fr_delivery, "Оформление заказа");
    }
    @Override
    public void addToCart(Product product){
        Log.d(TAG,"StartActivity.addToCart");
        myCart.addToCart(product);
        updateMenuCartIcon();
        drawer.updateBadge(Integer.toString(myCart.getcount()),2);
        Toast.makeText(getApplicationContext(),"Товар добавлен в корзину.",Toast.LENGTH_SHORT).show();
      //  fragmentTransact(fr_start,"");
    }
    @Override
    public Cart getCart(){
        return myCart;
    }
    @Override
    public void orderOk(String address, String tel, String koment){
        if (netState(this)==true){
        new SendOrder().execute(address,tel,koment,myCart.getProductsList());
          }
      else{
            Toast.makeText(this, "Нет подключения к интернету :(", Toast.LENGTH_LONG).show();
        }


    }
    @Override
    public void goToCatalog(){
        Log.d(TAG,"StartActivity.goToCatalog()");

        fragmentTransact(fr_start,"");
    }


    public void fragmentTransact (Fragment fragment,String title){
       // if (netState(this)!=false){
            fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.contaner, fragment).commit();
            toolbar.setTitle(title);
       // }
       // else{
       //     Toast.makeText(this, "Нет подключения к интернету :(", Toast.LENGTH_LONG).show();
      //  }
    }
    public void fragmentTransact (Fragment fragment,String title,String subtitle){
        //if (netState(this)==false){
            fragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.contaner, fragment).commit();
            toolbar.setTitle(title);
            toolbar.setSubtitle(subtitle);
      //  }
      /*  else{
            Toast.makeText(this, "Нет подключения к интернету :(", Toast.LENGTH_LONG).show();
        }*/
    }
    public boolean netState(Context context){
        boolean net=false;
        ConnectivityManager connMng=(ConnectivityManager)getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connMng.getActiveNetworkInfo();
        if (networkInfo!=null&&networkInfo.isConnected()){
            net=true;

        }else{
            net=false;

        }
        return net;

    }
    public void showOkDeliveryDialog(){
        DialogInterface.OnClickListener listener= new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case Dialog.BUTTON_NEUTRAL:
                        fragmentTransact(fr_start, "");
                        break;



                }
            }
        };

        AlertDialog.Builder adb2= new AlertDialog.Builder(this)


                .setNeutralButton("Ok", listener)
                .setMessage("Ваш заказ успешно отправлен. Но так как это демонстрационное приложение, то мы ничего вам не доставим:).");
        adb2.create();
        adb2.show();
    }


    class SendOrder extends AsyncTask<String, Void, Void> {
        String server_message;
        int error, count;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "SendOrder onPreExecute");

        }
        @Override
        protected Void doInBackground(String... a) {
            // TODO Auto-generated method stub

            try {
                JSONObject answer=api.send_order(a[0],a[1],a[2],a[3]);
                Log.d(TAG, "answer: "+answer.toString());
                error=answer.getInt("iError");
                if (error==0){


                } else {
                    server_message=answer.optString("Message");
                }

            }catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (error == 0) {
                Toast.makeText(getApplicationContext(),"Заказ успешно отправлен",Toast.LENGTH_LONG).show();

                myCart.clearCart();
                drawer.updateBadge(Integer.toString(myCart.getcount()),2);
                updateMenuCartIcon();
                showOkDeliveryDialog();


            }
        }

    }
}
