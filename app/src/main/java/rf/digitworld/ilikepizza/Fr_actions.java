package rf.digitworld.ilikepizza;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Дмитрий on 02.03.2015.
 */
public class Fr_actions extends Fragment implements View.OnClickListener {
    public static final String TAG ="myLogs";
    ApplicationInterface listener;
    LinearLayout spisok;
    CatalogActions actions=new CatalogActions();

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
        View v=inflater.inflate(R.layout.fr_actions, container, false);
        Log.d(TAG,"Fr_cart onCreateView");
        spisok=(LinearLayout)v.findViewById(R.id.actions_layout2);
        LayoutInflater layoutInflater=getActivity().getLayoutInflater();

       for(int i=0;i<actions.getcount(); i++)
        {
            View item=layoutInflater.inflate(R.layout.action_view,spisok,false);
            TextView caption=(TextView)item.findViewById(R.id.action_name);
            final TextView description=(TextView)item.findViewById(R.id.action_description);
           // ImageView im=(ImageView) item.findViewById(R.id.item_image);
            Action action=actions.getAction(i);

            caption.setText(action.getName());
            description.setText(action.getDescription());

            item.setId(i);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            item.setClickable(true);

            spisok.addView(item);

        }
        return v;
    }

    @Override
    public void onClick(View v) {
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
