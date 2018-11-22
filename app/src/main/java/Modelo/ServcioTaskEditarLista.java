package Modelo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class ServcioTaskEditarLista extends AsyncTask<Void, Void, String> {
    private Context httpContext;
    ProgressDialog progressDialog;
    public String resultadoApi="";
    public String linkRequestApi = "";
    //public String pCorreo;
    public int pIdLista;
    public String pFecha;
    public String pNombreLista;

    public ServcioTaskEditarLista(Context httpContext, String linkRequestApi, String pFecha, String pNombreLista, int pIdLista) {
        this.httpContext = httpContext;
        this.linkRequestApi = linkRequestApi;
        this.pFecha = pFecha;
        this.pNombreLista = pNombreLista;
        this.pIdLista = pIdLista;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        progressDialog = ProgressDialog.show(httpContext,"Procesando solicitud","Por favor espere");
    }

    @Override
    protected String doInBackground(Void... params){
        String result = null;
        String wsUrl = linkRequestApi;
        URL url = null;
        try {
            url = new URL(wsUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            JSONObject parametrosPost = new JSONObject();

            //Usuario usuario = new Usuario();
            //String correo = Usuario

            //Toast.makeText(httpContext, ""+getuCorrreo(), Toast.LENGTH_SHORT).show();

            parametrosPost.put("FECHALISTA",""+pFecha);
            parametrosPost.put("NOMBRELISTA",""+pNombreLista);
            parametrosPost.put("IDCABECERALISTA",pIdLista);

            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("PUT");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(parametrosPost));
            writer.flush();
            writer.close();
            os.close();

            int responseCode = urlConnection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String linea = "";
                while ((linea = in.readLine()) != null) {
                    sb.append(linea);
                    break;
                }
                in.close();
                result = sb.toString();
            } else{
                result = new String("Error: "+responseCode);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);
        progressDialog.dismiss();
        resultadoApi = s;
        Toast.makeText(httpContext,"Lista actualizada: "+resultadoApi,Toast.LENGTH_LONG).show();
    }

    public String getPostDataString(JSONObject params) throws Exception{
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while (itr.hasNext()){
            String key = itr.next();
            Object value = params.get(key);

            if(first){
                first = false;
            } else{
                result.append("&");
            }

            result.append(URLEncoder.encode(key,"UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(),"UTF-8"));
        }
        return result.toString();
    }
}
