package rf.digitworld.ilikepizza;

import android.content.SharedPreferences;

        import java.util.ArrayList;
        import java.util.List;

        import org.apache.http.NameValuePair;
        import org.apache.http.message.BasicNameValuePair;
        import org.json.JSONObject;

        import android.app.Activity;
        import android.content.SharedPreferences;
        import android.util.Log;

public class Api {
    String id;
    String hash;
    SharedPreferences sp;

    public Api(){

    }

    public Api(String id, String hash) {
        super();
        this.id = id;
        this.hash = hash;
    }
    /**
     *
     * @param login
     * @param pass
     * @return
     */
    public JSONObject user_login(String login, String pass){
        List <NameValuePair> post=new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair("comm", "login_user"));
        post.add(new BasicNameValuePair("sLogin", login));
        post.add(new BasicNameValuePair("sPass", pass));

        return getJson(post);
    }

    public JSONObject send_order(String address, String tel, String koment, String order){
        List <NameValuePair> post=new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair("comm", "sendOrder"));
        post.add(new BasicNameValuePair("address", address));
        post.add(new BasicNameValuePair("tel", tel));
        post.add(new BasicNameValuePair("koment", koment));
        post.add(new BasicNameValuePair("order", order));

        return getJson(post);


    }
    public JSONObject send_callback(String callback) {
        List<NameValuePair> post = new ArrayList<NameValuePair>();
        post.add(new BasicNameValuePair("comm", "sendcallback"));
        post.add(new BasicNameValuePair("callback", callback));


        return getJson(post);

    }

    private JSONObject getJson(List<NameValuePair> post) {
        Log.d("myLogs", post.toString());
        try {
            QueryAdapter adapter=new QueryAdapter();
            String json=adapter.setData(post);
            if(json!=null){
                Log.d("myLogs",json);
                JSONObject answer=new JSONObject(json);
                return answer;
            }
        }catch (Exception e) {
            Log.d("mylogs", "api.getJson Exception " +e);
            e.printStackTrace();
        }
        return null;
    }


}



