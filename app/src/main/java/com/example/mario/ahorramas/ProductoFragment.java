package com.example.mario.ahorramas;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Modelo.Adaptador;
import Modelo.AdaptadorProducto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductoFragment extends Fragment implements SearchView.OnQueryTextListener {
    //private final String baseUrl = "http://parcialinter-001-site1.dtempurl.com/";
    //private final String baseUrl = "http://192.168.1.6/WebApi/";
    //private final String baseUrl = "https://apex.oracle.com/pls/apex/pruebas123/hr/";
    private final String baseUrl = "https://apex.oracle.com/";
    private static final String TAG = "ProductoFragment";
    RecyclerView rvProductos;
    List<Item> items = new ArrayList<>();
    AdaptadorProducto adaptador;
    private Context mContext;

    private OnFragmentInteractionListener mListener;

    public ProductoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        setHasOptionsMenu(true);
        //setButtons();

        //adaptador.setOnClick(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_producto, container, false);
        rvProductos = (RecyclerView) view.findViewById(R.id.rvProductos);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvProductos.setLayoutManager(llm);
        adaptador = new AdaptadorProducto(items,mContext);
        rvProductos.setAdapter(adaptador);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(rvProductos.getContext(),llm.getOrientation());
        rvProductos.addItemDecoration(itemDecoration);

        String categoria = getArguments() != null ? getArguments().getString("categoria") : "Revisar";
        String email = getArguments() != null ? getArguments().getString("correo") : "Revisar";
        //Toast.makeText(mContext,"PARAMETRO CORREO: "+email,Toast.LENGTH_LONG).show();
        //Toast.makeText(mContext,"PARAMETRO CATEGORIA: "+categoria,Toast.LENGTH_LONG).show();

        ProductosService productosService = retrofit.create(ProductosService.class);

        Call<Productos> lista = productosService.getProductosAbarrotes(categoria);
        lista.enqueue(new Callback<Productos>() {
            @Override
            public void onResponse(Call<Productos> call, Response<Productos> response) {
                if (response.isSuccessful()){
                    Productos productos = response.body();
                    items = productos.getItems();

                    if ( items.size() > 0) {
                        adaptador = new AdaptadorProducto(items,mContext);
                        rvProductos.setAdapter(adaptador);
                        adaptador.notifyDataSetChanged();
                    }else {

                        Log.d("ErrorProd","Vacio" );
                    }
                }
            }

            @Override
            public void onFailure(Call<Productos> call, Throwable t) {
                Log.d("ErrorSer",t.getMessage());
                Log.d("ErrorSer",call.toString());
            }
        });
        return view;
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
        getActivity().getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        List<Item> newList = new ArrayList<>();
        for(Item item : items){
            String name = item.getProducto().toLowerCase();
            if (name.contains(newText)){
                newList.add(item);
            }
        }
        adaptador.setFilter(newList);
        return true;
    }


    public void addProductList(int position){
        //Aqui va como agregar el producto a la lista
        //https://www.youtube.com/watch?v=kaf2dCd8Zfs
    }

    /*public void setButtons(){
        adaptador.setOnClick(new Adaptador.OnItemClicked() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onItemAdd(int position) {

            }
        });
    }*/
}
