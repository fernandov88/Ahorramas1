package Modelo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mario.ahorramas.Item;
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

public class Adaptador
        extends RecyclerView.Adapter<Adaptador.AdaptadorViewHolder> {

    private List <Item> listaItem;
    private Context context;
    //OnItemClicked onClick;
    ImageView mAddImage;
    //EditText edtCantidad;
    String cantidad;
    Dialog myDialog;
    TextView tvDescripcion;
    TextView tvPrecioSuperSelectos;
    TextView tvPrecioWalmart;

    public Adaptador(List<Item> listaItem, Context context) {
        this.listaItem = listaItem;
        this.context = context;
    }

    @NonNull
    @Override
    public AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_view,viewGroup,false);
        return new AdaptadorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdaptadorViewHolder adaptadorViewHolder, int i) {
        final int pos = i;

        mAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(String.valueOf(TAG), "onclick pos = " + pos);
                myDialog = new Dialog(context);
                myDialog.setContentView(R.layout.dialog_list_prod);
                //myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                TextView dialog_name_tv = (TextView) myDialog.findViewById(R.id.dialog_name);
                Button dialog_btn_add = (Button) myDialog.findViewById(R.id.dialog_btn_add);
                final EditText edtCantidad = (EditText) myDialog.findViewById(R.id.edtCantidad);
                dialog_name_tv.setText(listaItem.get(pos).getProducto());
                myDialog.show();
                dialog_btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(context,"Selecciono el producto: "+listaItem.get(pos).getProducto(),Toast.LENGTH_LONG).show();
                        //Toast.makeText(context,"Valor "+edtCantidad.getText().toString(),Toast.LENGTH_LONG).show();
                        //Toast.makeText(context,"Precio Selectos: "+listaItem.get(pos).getSuperselectos(),Toast.LENGTH_LONG).show();
                        //Toast.makeText(context,"Precio Walmart: "+listaItem.get(pos).getWalmart(),Toast.LENGTH_LONG).show();

                        //consumirServicio();
                    }
                });
            }
        });

        adaptadorViewHolder.bindProducto(listaItem.get(i));
    }

    @Override
    public int getItemCount() {
        return listaItem.size() ;
    }

    public class AdaptadorViewHolder extends RecyclerView.ViewHolder{
        android.support.v7.widget.CardView producto_id;
        //TextView tvDescripcion;
        //TextView tvPrecioSuperSelectos;
        //TextView tvPrecioWalmart;
        //Button dialog_btn_add;

        public AdaptadorViewHolder(View itemView){
            super(itemView);

            producto_id = itemView.findViewById(R.id.producto_id);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecioSuperSelectos = itemView.findViewById(R.id.tvPrecioSuperSelectos);
            tvPrecioWalmart = itemView.findViewById(R.id.tvPrecioWalmart);
            //dialog_btn_add = itemView.findViewById(R.id.edtCantidad);
            //edtCantidad = itemView.findViewById(R.id.edtCantidad);
            mAddImage = itemView.findViewById(R.id.image_add);

        }

        public void bindProducto( Item item){
            tvDescripcion.setText(String.valueOf(item.getProducto()));
            tvPrecioSuperSelectos.setText("Super Selectos $" + String.valueOf(item.getSuperselectos()));
            tvPrecioWalmart.setText("Walmart $" + String.valueOf(item.getWalmart()));
            //cantidad = edtCantidad.getText().toString();
        }
    }

    public void setFilter(List<Item> newList){
        listaItem = new ArrayList<>();
        listaItem.addAll(newList);
        notifyDataSetChanged();
    }

    public void consumirServicio(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date = new Date();

        String fecha = dateFormat.format(date);
        Toast.makeText(context,"LA FECHA ES: "+fecha,Toast.LENGTH_LONG).show();

        ServcioTask servcioTask = new ServcioTask(context,"https://apex.oracle.com/pls/apex/pruebas123/api/CABECERA",getuCorrreo(),fecha,tvDescripcion.getText().toString());
        servcioTask.execute();
    }
}
