package com.example.mario.ahorramas;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mario.ahorramas.Remote.APIService;
import com.example.mario.ahorramas.Remote.ApiUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import Modelo.ServcioTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static Modelo.Usuario.getuCorrreo;


public class NuevaLista2Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText edtNombreLista;
    private Context mContext;
    private APIService mAPIService;
    String lista;
    String idlista;
    String nombreLista;
    String correos;
    int idL;
    Item2 item2;
    MenuItem menuItem;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NuevaLista2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NuevaListaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NuevaLista2Fragment newInstance(String param1, String param2) {
        NuevaLista2Fragment fragment = new NuevaLista2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        lista = getArguments() != null ? getArguments().getString("lista") : "Revisar";
        idlista = getArguments() != null ? getArguments().getString("idlista") : "Revisar";

        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_editar_lista, container, false);
        edtNombreLista = (EditText) view.findViewById(R.id.edtNombreLista);
        mAPIService = ApiUtils.getApiService();


        return view;
    }

    public void sendPut(int id,int idcabecera,String fECHALISTA, String nOMBRELISTA) {

            mAPIService.actualizaLista(id,idcabecera,fECHALISTA,nOMBRELISTA).enqueue(new Callback<Item2>() {
            @Override
            public void onResponse(Call<Item2> call, Response<Item2> response) {

                if(response.isSuccessful()) {
                    showResponse(response.body().toString());
                    Log.i("ERROR", "post submitted to API." + response.body().toString());
                }else{
                Log.i("ERROR", "Unable to submit update to APIres." + response.code());
                }

            }

            @Override
            public void onFailure(Call<Item2> call, Throwable t) {
                Log.e("ERROR", "Unable to submit update to API."+t.getMessage());
                Log.e("ERROR", "Unable to submit update to API."+call.request().body().toString());
                t.printStackTrace();
            }
        });
        mostrarDetalles();
    }

    public void showResponse(String response) {
        /*if(mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);*/
        Toast.makeText(mContext,"Lista creada con retrofit: "+response,Toast.LENGTH_LONG).show();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflater.inflate(R.menu.search_menu,menu);
        getActivity().getMenuInflater().inflate(R.menu.aceptar_menu,menu);
        menuItem = menu.findItem(R.id.accion_confirmar);

        //int id = menuItem.getItemId();
        //switch (id) {
        //    case R.id.accion_confirmar:
        //        insertar();
        //        break;
            /*case R.id.accion_eliminar:
                eliminar();
                break;
                */
        //}
        //SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        //searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.accion_confirmar:
                //insertar();
                nombreLista = edtNombreLista.getText().toString();
                item2 = new Item2();

                // Validaciones y pruebas de cordura
                if (!esNombreValido(nombreLista)) {
                    TextInputLayout mascaraCampoNombre = (TextInputLayout) getView().findViewById(R.id.nombreList);
                    mascaraCampoNombre.setError("Este campo no puede quedar vacío");
                } else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    Date date = new Date();

                    String fecha = dateFormat.format(date);
                    //Toast.makeText(mContext,"LA FECHA ES: "+fecha,Toast.LENGTH_LONG).show();

                    /*Toast.makeText(mContext,"Nombre Lista: "+ nombreLista,Toast.LENGTH_LONG).show();
                    ServcioTask servcioTask = new ServcioTask(mContext,"https://apex.oracle.com/pls/apex/pruebas123/api/CABECERA",getuCorrreo(),fecha,nombreLista.toUpperCase());
                    servcioTask.execute();
                    */
                    //mostrarDetalles();
                    correos = getuCorrreo();
                    idL = Integer.parseInt(idlista);
                    //Toast.makeText(mContext,"LA correo ES: "+correos,Toast.LENGTH_LONG).show();
                    //item2.setIdcabeceralista(idL);
                   // item2.setFechalista(fecha);
                    //item2.setNombreLista(nombreLista.toUpperCase());
                    //item2.setCorreo(correos);
                    sendPut(idL,idL,nombreLista.toUpperCase(),fecha);

                }

                break;
            /*case R.id.accion_eliminar:
                eliminar();
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }


    private boolean esNombreValido(String nombre) {
        return !TextUtils.isEmpty(nombre);
    }

    public void insertar(){
        // Extraer datos de UI
        nombreLista = edtNombreLista.getText().toString();

        // Validaciones y pruebas de cordura
        if (!esNombreValido(nombreLista)) {
            TextInputLayout mascaraCampoNombre = (TextInputLayout) getView().findViewById(R.id.nombreList);
            mascaraCampoNombre.setError("Este campo no puede quedar vacío");
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date date = new Date();

            String fecha = dateFormat.format(date);
            Toast.makeText(mContext,"LA FECHA ES: "+fecha,Toast.LENGTH_LONG).show();

            Toast.makeText(mContext,"Nombre Lista: "+ nombreLista,Toast.LENGTH_LONG).show();
            ServcioTask servcioTask = new ServcioTask(mContext,"https://apex.oracle.com/pls/apex/pruebas123/api/CABECERA",getuCorrreo(),fecha,nombreLista.toUpperCase());
            servcioTask.execute();

            mostrarDetalles();
        }
    }

    void mostrarDetalles() {

        //FragmentTransaction transaction =
        FragmentManager fragmentManager = getFragmentManager();
        Fragment f = new ListaFragment();
        Bundle args = new Bundle();
        args.putString("correo", getuCorrreo());
        f.setArguments(args);
        fragmentManager.beginTransaction().replace(R.id.content_main,f).commit();
    }
}
