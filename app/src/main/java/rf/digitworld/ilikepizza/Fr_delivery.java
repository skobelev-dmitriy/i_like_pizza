package rf.digitworld.ilikepizza;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Дмитрий on 02.03.2015.
 */
public class Fr_delivery extends Fragment implements View.OnClickListener {
    public static final String TAG ="myLogs";
    ApplicationInterface listener;
    EditText adress;
    EditText telephone;
    EditText koment;
    Button commit;
    SharedPreferences sp;

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
        Log.d(TAG,"Fr_cart onCreateView");
        View v=inflater.inflate(R.layout.fr_delivery, container, false);
        adress=(EditText)v.findViewById(R.id.edit_adress);
        telephone=(EditText)v.findViewById(R.id.edit_phone);
        koment=(EditText)v.findViewById(R.id.edit_komment);
        commit=(Button)v.findViewById(R.id.order_ok);
        commit.setOnClickListener(this);

        TelephonyManager telephonyManager=(TelephonyManager)getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String telOperator=telephonyManager.getNetworkOperator();
        String telNumber=telephonyManager.getLine1Number();
        Log.d(TAG,telNumber+"  | "+telOperator);
       // telephone.setText(telNumber);
        sp=getActivity().getPreferences(Context.MODE_PRIVATE);
        String spTel=sp.getString("tel","");
        String spAdress=sp.getString("adress","");
        telephone.setText(spTel);
        adress.setText(spAdress);

        return v;
    }
    private void validData(){
        String errorEmpty="Заполните поле";
        boolean error=false;

        String myAdress=adress.getText().toString();
        String tel=telephone.getText().toString();
        String komment=koment.getText().toString();

        SharedPreferences.Editor ed=sp.edit();
        ed.putString("adress", myAdress);
        ed.putString("tel", tel);

        ed.commit();


        if (myAdress.length()==0){
            error=true;
            adress.setError(errorEmpty);
           // Toast.makeText(getActivity().getApplicationContext(),"Заполните поля",Toast.LENGTH_SHORT).show();
        }
        if (tel.length()==0){
            error=true;
            telephone.setError(errorEmpty);
           // Toast.makeText(getActivity().getApplicationContext(),"Заполните поля",Toast.LENGTH_SHORT).show();
        }
       if (error==false){
            listener.orderOk(myAdress,tel,komment);

        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.order_ok:
               // commit.setEnabled(false);
                validData();
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
