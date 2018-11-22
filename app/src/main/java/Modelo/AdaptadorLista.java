package Modelo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mario.ahorramas.EditarListaFragment;
import com.example.mario.ahorramas.Item;
import com.example.mario.ahorramas.NuevaLista2Fragment;
import com.example.mario.ahorramas.NuevaListaFragment;
import com.example.mario.ahorramas.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static Modelo.Usuario.getuCorrreo;
import static com.facebook.appevents.codeless.internal.PathComponent.MatchBitmaskType.TAG;


/**
 * Created by mario on 10/8/2017.
 */

public class AdaptadorLista
        extends RecyclerView.Adapter<AdaptadorLista.AdaptadorViewHolder> {

    private List <Item> listaItem;
    private Context context;
    ImageView mAddImage;
    String cantidad;
    Dialog myDialog;
    TextView tvIdLista;
    TextView tvNombreLista;
    TextView tvFecha;

    public AdaptadorLista(List<Item> listaItem, Context context) {
        this.listaItem = listaItem;
        this.context = context;
    }

    @NonNull
    @Override
    public AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_view,viewGroup,false);
        return new AdaptadorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdaptadorViewHolder adaptadorViewHolder, int i) {
        final int pos = i;
        mAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(String.valueOf(TAG), "onclick pos = " + pos);
                PopupMenu popupMenu = new PopupMenu(context,mAddImage);
                popupMenu.inflate(R.menu.editar_eliminar_menu);
                //tvIdLista.setText(listaItem.get(pos).getIdcabeceralista());
                final String lista = (listaItem.get(pos).getNombreLista());
                final String idlista = (listaItem.get(pos).getIdcabeceralista());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.accion_editar:
                                //Toast.makeText(context,"Editar "+IdLista,Toast.LENGTH_LONG).show();
                                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                                Fragment f = new NuevaLista2Fragment();
                                Bundle args = new Bundle();
                                args.putString("lista", lista);
                                args.putString("idlista", idlista);
                                f.setArguments(args);
                                manager.beginTransaction().replace(R.id.content_main,f).commit();
                                break;
                            case R.id.accion_eliminar:
                                Toast.makeText(context,"Eliminar",Toast.LENGTH_LONG).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
                //myDialog.setContentView(R.layout.dialog_list_prod);
                //myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //TextView dialog_name_tv = (TextView) myDialog.findViewById(R.id.dialog_name);
                //Button btnMenu = (Button) myDialog.findViewById(R.id.image_menu);
                //final EditText edtCantidad = (EditText) myDialog.findViewById(R.id.edtCantidad);
                //dialog_name_tv.setText(listaItem.get(pos).getProducto());
                //myDialog.show();
                //btnMenu.setOnClickListener(new View.OnClickListener() {
                //    @Override
                //    public void onClick(View v) {
                //Toast.makeText(context,"Selecciono el producto: "+listaItem.get(pos).getProducto(),Toast.LENGTH_LONG).show();
                //Toast.makeText(context,"Valor "+edtCantidad.getText().toString(),Toast.LENGTH_LONG).show();
                //Toast.makeText(context,"Precio Selectos: "+listaItem.get(pos).getSuperselectos(),Toast.LENGTH_LONG).show();
                //Toast.makeText(context,"Precio Walmart: "+listaItem.get(pos).getWalmart(),Toast.LENGTH_LONG).show();

                //consumirServicio();
                //    }
                //});
            }
        });


        adaptadorViewHolder.bindProducto(listaItem.get(i));
    }

    @Override
    public int getItemCount() {
        return listaItem.size() ;
    }

    public class AdaptadorViewHolder extends RecyclerView.ViewHolder{
        android.support.v7.widget.CardView lista_id;
        //TextView tvDescripcion;
        //TextView tvPrecioSuperSelectos;
        //TextView tvPrecioWalmart;
        //Button dialog_btn_add;

        public AdaptadorViewHolder(View itemView){
            super(itemView);

            lista_id = itemView.findViewById(R.id.producto_id);
            tvNombreLista = itemView.findViewById(R.id.tvNombreLista);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            mAddImage = itemView.findViewById(R.id.image_menu);

        }

        public void bindProducto( Item item){
            //tvID.setText(String.valueOf(item.getIdcabeceralista()));
            tvNombreLista.setText(""+ String.valueOf(item.getNombreLista()));
            tvFecha.setText("Lista creada: "+String.valueOf(item.getFechaLista()));
        }
    }

    public void setFilter(List<Item> newList){
        listaItem = new ArrayList<>();
        listaItem.addAll(newList);
        notifyDataSetChanged();
    }

    public void consumirServicio(){
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date = new Date();

        String fecha = dateFormat.format(date);
        Toast.makeText(context,"LA FECHA ES: "+fecha,Toast.LENGTH_LONG).show();

        ServcioTask servcioTask = new ServcioTask(context,"https://apex.oracle.com/pls/apex/pruebas123/api1/insertCabecera",getuCorrreo(),fecha,tvNombreLista.getText().toString());
        servcioTask.execute();
    }
}
