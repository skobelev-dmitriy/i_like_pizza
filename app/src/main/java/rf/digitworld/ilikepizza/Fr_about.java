package rf.digitworld.ilikepizza;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by Дмитрий on 02.03.2015.
 */
public class Fr_about extends Fragment implements View.OnClickListener {
    public static final String TAG ="myLogs";
    ApplicationInterface listener;
    EditText callback;
    Button send;


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
        View v=inflater.inflate(R.layout.fr_about, container, false);
        Log.d(TAG,"Fr_cart onCreateView");
        TextView madeIn=(TextView)v.findViewById(R.id.made_in);
        callback=(EditText)v.findViewById(R.id.callback_edit);
        send=(Button)v.findViewById(R.id.button_send);
        send.setOnClickListener(this);
        madeIn.setMovementMethod(LinkMovementMethod.getInstance());

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_send:
                new SendCallback().execute(callback.getText().toString());
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
    public void showOkDialog(){
        final DialogInterface.OnClickListener clicklistener= new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case Dialog.BUTTON_NEUTRAL:
                        listener.goToCatalog();
                        break;



                }
            }
        };

        AlertDialog.Builder adb2= new AlertDialog.Builder(getActivity().getApplicationContext())


                .setNeutralButton("Ok", clicklistener)
                .setMessage("Ваш заказ успешно отправлен. Но так как это демонстрационное приложение, то мы ничего вам не доставим:).");
        adb2.create();
        adb2.show();
    }


    class SendCallback extends AsyncTask<String, Void, Void> {
        String server_message;
        int error, count;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "SendOrder onPreExecute");
            send.setEnabled(false);

        }
        @Override
        protected Void doInBackground(String... a) {
            // TODO Auto-generated method stub

            try {
                JSONObject answer=new Api().send_callback(a[0]);
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

            send.setEnabled(true);
                callback.setText("");

              listener.goToCatalog();


            } else {
                send.setEnabled(true);
            }
        }

    }
}
