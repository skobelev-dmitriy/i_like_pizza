package rf.digitworld.ilikepizza;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.NameValuePair;
import android.util.Log;




public class QueryAdapter {
    final int SUCCESS=200; 						//Все ок
    final int BAD_REQUEST=400;					//Неверный запрос. Запрос не может быть понят сервером из-за некорректного синтаксиса
    final int FORBIDDEN=403;					//Доступ к ресурсу запрещен. Доступ к документу запрещен
    final int NOT_FOUND=404;					//Ресурс не найден. Документ не существует
    final int NOT_ACCEPTABLE=406;				//Неприемлемый запрос. Нужный документ существует, но не в том формате (язык или кодировка не поддерживаются роботом).
    final int REQUEST_TIMEOUT=408;				//Время запроса истекло. Сайт не передал полный запрос в течение установленного времени и робот разорвал соединение
    final int CONFLICT=409;						//Конфликт. Запрос конфликтует с другим запросом или с конфигурацией сервера
    final int GONE=410;							//Ресурс недоступен. Затребованный ресурс был окончательно удален с сайта
    final int INTERNAL_SERVER_ERROR=500;		//Внутренняя ошибка сервера.  Сервер столкнулся с непредвиденным условием, которое не позволяет ему выполнить запрос
    final int NOT_IMPLEMENTED=501;				//Метод не поддерживается. Сервер не поддерживает функциональные возможности, требуемые для выполнения запроса (получен код 501 Not Implemented). Этот ответ соответствует состоянию, когда сервер не распознает метод запроса и не способен обеспечить его для любого ресурса.
    final int BAD_GATEWAY=502;					//Ошибка шлюза. Сервер, действуя в качестве шлюза или прокси-сервера, получил недопустимый ответ от следующего сервера в цепочке запросов, к которому обратился при попытке выполнить запрос (получен код 502 Bad Gateway).
    final int SERVISE_UNAVAILABLE=503;			//Служба недоступна. Возникла ошибка из-за временной перегрузки или отключения на техническое обслуживание сервера
    final int GATEWAY_TIMEOUT=504;				//Время прохождения через межсетевой шлюз истекло. Сервер, при работе в качестве внешнего шлюза или прокси-сервера, своевременно не получил отклик от вышестоящего сервера, к которому он обратился, пытаясь выполнить запрос
    final int HTTP_VERSION_NOT_SUPPORTED=505;	//Версия НТТР не поддерживается. Сервер не поддерживает или отказывается поддерживать версию HTTP-протокола, которая используется в сообщении запроса робота
    int error;
    String errorString;
    String myUrl="http://xn--h1aalhjdxo.xn--p1ai/api.php";

    private void netError(int err){

        error=err;
        switch (error){
            case BAD_REQUEST:
                errorString="Неверный запрос. Запрос не может быть понят сервером из-за некорректного синтаксиса";
                break;
            case FORBIDDEN:
                errorString="Доступ к ресурсу запрещен. Доступ к документу запрещен ";
                break;
            case NOT_FOUND:
                errorString="Ресурс не найден. Документ не существует";
                break;
            case NOT_ACCEPTABLE:
                errorString="Неприемлемый запрос. Нужный документ существует, но не в том формате (язык или кодировка не поддерживаются роботом)";
                break;
            case REQUEST_TIMEOUT:
                errorString="Время запроса истекло. Сайт не передал полный запрос в течение установленного времени и робот разорвал соединение";
                break;
            case CONFLICT:
                errorString="Конфликт. Запрос конфликтует с другим запросом или с конфигурацией сервера";
                break;
            case GONE:
                errorString="Ресурс недоступен. Затребованный ресурс был окончательно удален с сайта";
                break;
            case INTERNAL_SERVER_ERROR:
                errorString="Внутренняя ошибка сервера.  Сервер столкнулся с непредвиденным условием, которое не позволяет ему выполнить запрос ";
                break;
            case NOT_IMPLEMENTED:
                errorString="Метод не поддерживается. Сервер не поддерживает функциональные возможности, требуемые для выполнения запроса (получен код 501 Not Implemented). Этот ответ соответствует состоянию, когда сервер не распознает метод запроса и не способен обеспечить его для любого ресурса";
                break;
            case BAD_GATEWAY:
                errorString="Ошибка шлюза. Сервер, действуя в качестве шлюза или прокси-сервера, получил недопустимый ответ от следующего сервера в цепочке запросов, к которому обратился при попытке выполнить запрос";
                break;
            case SERVISE_UNAVAILABLE:
                errorString="Служба недоступна. Возникла ошибка из-за временной перегрузки или отключения на техническое обслуживание сервера";
                break;
            case GATEWAY_TIMEOUT:
                errorString="Время прохождения через межсетевой шлюз истекло. Сервер, при работе в качестве внешнего шлюза или прокси-сервера, своевременно не получил отклик от вышестоящего сервера, к которому он обратился, пытаясь выполнить запрос";
                break;
            case HTTP_VERSION_NOT_SUPPORTED:
                errorString="Версия НТТР не поддерживается. Сервер не поддерживает или отказывается поддерживать версию HTTP-протокола, которая используется в сообщении запроса робота";
                break;

        }

        Log.d("myLogs", "QueryAdapter. Ошибка запроса: "+errorString);

    }

	/*	public String getData() throws IOException{
			InputStream is=null;
			HttpURLConnection conn=null;

			try{
				URL url=new URL(myUrl);
				 conn=(HttpURLConnection)url.openConnection();
				conn.setReadTimeout(200000);
				conn.setConnectTimeout(300000);
				conn.setRequestMethod("POST");
				conn.setDoInput(true);
				conn.setDoOutput(true);
				//start the query
				conn.connect();
				int response=conn.getResponseCode();
				Log.d("myLogs","Код ответа сервера: "+response);
				if (response==SUCCESS)
				{
				//Convert the inputStream into a string
				StringBuffer buffer=new StringBuffer();
				is=conn.getInputStream();
				BufferedReader br=new BufferedReader(new InputStreamReader(is));
				String line=null;
				while((line=br.readLine())!=null)
					buffer.append(line);
				is.close();
				conn.disconnect();
				String contentAsString=buffer.toString();
				Log.d("myLogs", "Строка ответа: "+contentAsString);

				return contentAsString;
				} else netError(response);
				}catch (Throwable t){
					t.printStackTrace();
			}finally{

				try { is.close();} catch(Throwable t){}
				try { conn.disconnect();} catch (Throwable t){}
			}
			return null;

		}*/

    public String setData(List <NameValuePair> post) throws IOException{
        InputStream is=null;
        HttpURLConnection conn=null;

        try{
            URL url=new URL(myUrl);
            conn=(HttpURLConnection)url.openConnection();
            conn.setReadTimeout(20000);
            conn.setConnectTimeout(30000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os=conn.getOutputStream();
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            writer.write(getQuery(post));
            writer.flush();
            writer.close();
            os.close();

            //Log.d("myLogs",conn.getRequestProperties().toString());

            //start the query
            conn.connect();
            int response=conn.getResponseCode();
            Log.d("myLogs","Код ответа сервера: "+response);

            if (response==SUCCESS){
                //Convert the inputStream into a string
                StringBuffer buffer=new StringBuffer();
                is=conn.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is,"UTF-8"));
                String line=null;
                while((line=br.readLine())!=null)
                    buffer.append(line);
                is.close();
                conn.disconnect();
                String contentAsString=buffer.toString();
                Log.d("myLogs", "Строка ответа: "+contentAsString);

                return contentAsString;
            }else netError(response);
        }catch (Throwable t){
            t.printStackTrace();
        }finally{
				/*if(is!=null){
					is.close();
				}*/
            try { is.close();} catch(Throwable t){}
            try { conn.disconnect();} catch (Throwable t){}
        }
        return null;

    }
	/*	public String setData() throws IOException{
			InputStream is=null;
			HttpURLConnection conn=null;

			try{
				URL url=new URL(myUrl);
				 conn=(HttpURLConnection)url.openConnection();
				conn.setReadTimeout(20000);
				conn.setConnectTimeout(30000);
				conn.setRequestMethod("GET");
				conn.setDoInput(true);
				conn.setDoOutput(true);

				conn.connect();
				int response=conn.getResponseCode();
				Log.d("myLogs","Код ответа сервера: "+response);

				if (response==SUCCESS){
				//Convert the inputStream into a string
				StringBuffer buffer=new StringBuffer();
				is=conn.getInputStream();
				BufferedReader br=new BufferedReader(new InputStreamReader(is,"UTF-8"));
				String line=null;
				while((line=br.readLine())!=null)
					buffer.append(line);
				is.close();
				conn.disconnect();
				String contentAsString=buffer.toString();
				return contentAsString;
				} else netError(response);
				}catch (Throwable t){
					t.printStackTrace();
			}finally{

				try { is.close();} catch(Throwable t){}
				try { conn.disconnect();} catch (Throwable t){}
			}
			return null;

		}*/

    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
        StringBuilder result= new StringBuilder();
        boolean first=true;
        for (NameValuePair pair:params)
        {
            if (first)
                first=false;
            else
                result.append("&");
            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }





}






