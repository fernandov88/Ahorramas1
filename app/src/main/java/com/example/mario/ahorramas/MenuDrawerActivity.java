package com.example.mario.ahorramas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;

import static Modelo.Usuario.getuCorrreo;
import static Modelo.Usuario.setuCorrreo;
import static Modelo.Usuario.setuNombre;

public class MenuDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    /* Se definen variables de los identificadores del layout y para almacenar los datos de
    registro de usuario de google*/

    private ImageView imageView;
    private TextView tvNombre;
    private TextView tvEmail;
    private String urlImg;
    static String nombreC;
    static String eMailC;
    String categoria;
    //|UserService userService;

    Bundle argsFrag;

    String tvNombreFrag, tvCorreoFrag;
    private GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;
    private  FirebaseAuth.AuthStateListener firebaseAuthListener;

    /* Se crea una instancia de la clase usuario*/
    //Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        argsFrag = new Bundle();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Bundle parametros = getIntent().getExtras();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View navHeaderView = navigationView.getHeaderView(0);
        imageView = (ImageView)navHeaderView.findViewById(R.id.imageView);
        tvNombre = (TextView) navHeaderView.findViewById(R.id.tvNombre);
        tvEmail = (TextView) navHeaderView.findViewById(R.id.tvEmail);
        //userService = ApiUtils.getUserService();

        tvEmail.setText(parametros.getCharSequence("correo"));
        tvNombre.setText(parametros.getCharSequence("nombre"));
        Glide.with(MenuDrawerActivity.this).load(parametros.getCharSequence("imagen")).into(imageView);


        tvNombreFrag = tvNombre.getText().toString();
        tvCorreoFrag = tvEmail.getText().toString();
        argsFrag.putString("tvNombreF", tvNombreFrag);
        argsFrag.putString("tvCorreoF", tvCorreoFrag);

        /*
        Toast.makeText(getApplicationContext(),"NombreFacebook: "+tvNombre.getText().toString(), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),"CorreoFacebook: "+tvEmail.getText().toString(), Toast.LENGTH_LONG).show();
        */

        setuNombre(tvNombreFrag);
        setuCorrreo(tvCorreoFrag);

        //Toast.makeText(getApplicationContext(),"CorreoFacebook: "+getuCorrreo(), Toast.LENGTH_LONG).show();

        //Toast.makeText(getApplicationContext(),"CorreoFacebookUsuario: "+usuario.getuNombre(), Toast.LENGTH_LONG).show();


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        firebaseAuth =FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    setUserData(user);
                }else{
                    //goLogInScreen();
                }
            }
        };

        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                String value = getIntent().getExtras().getString(key);
            }
        }

        String token = FirebaseInstanceId.getInstance().getToken();
    }

    private void setUserData(FirebaseUser user) {
        tvNombre.setText(user.getDisplayName());
        if(user.getEmail() != null){
            tvEmail.setText(user.getEmail());
        }
        else{
            tvEmail.setText(getIntent().getExtras().getString("correo"));
            //Toast.makeText(this,"este es" +tvEmail.getText().toString(),Toast.LENGTH_LONG).show();
        }

        urlImg = user.getPhotoUrl().toString();
        Glide.with(MenuDrawerActivity.this).load(urlImg).into(imageView);

        nombreC = tvNombre.getText().toString();
        eMailC =  tvEmail.getText().toString();
        tvNombreFrag = tvNombre.getText().toString();
        tvCorreoFrag = tvEmail.getText().toString();

        setuCorrreo(tvCorreoFrag);
        //validador(getuCorrreo());

        //Toast.makeText(getApplicationContext(),"CorreoGoogle: "+getuCorrreo(), Toast.LENGTH_LONG).show();
        /*
        Toast.makeText(getApplicationContext(),"NombreGoogle: "+tvNombreFrag, Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),"CorreoGoogle: "+tvCorreoFrag, Toast.LENGTH_LONG).show();
        */

        //argsFrag = new Bundle();
        argsFrag.putString("tvNombreF", tvNombreFrag);
        //argsFrag.putString("tvCorreoF", tvCorreoFrag);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment f = null;
        boolean fragmentSeleccionado = false;

        if (id == R.id.nav_abarrotes) {
            f = new ProductoFragment();
            fragmentSeleccionado = true;
            categoria = "ABARROTES";
            argsFrag.putString("categoria", categoria);
            f.setArguments(argsFrag);
        } else if (id == R.id.nav_cuidadopersonal) {
            f = new ProductoFragment();
            fragmentSeleccionado = true;
            categoria = "CUIDADOPERSONAL";
            argsFrag.putString("categoria", categoria);
            f.setArguments(argsFrag);
        } else if (id == R.id.nav_perecederos) {
            f = new ProductoFragment();
            fragmentSeleccionado = true;
            categoria = "PERECEDEROS";
            argsFrag.putString("categoria", categoria);
            f.setArguments(argsFrag);
        } else if (id == R.id.nav_milista) {
            f = new ListaFragment();
            fragmentSeleccionado = true;
            argsFrag.putString("correo",getuCorrreo());
            f.setArguments(argsFrag);
        } else if (id == R.id.nav_exit) {
            firebaseAuth.signOut();
            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {
                    if(status.isSuccess()){
                        goLogInScreen();
                    }else{
                        Toast.makeText(getApplicationContext(),R.string.not_close_session,Toast.LENGTH_SHORT).show();
                    }
                }
            });
            goLogInScreen();
            finish();
        }

        if(fragmentSeleccionado){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,f).commit();
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    private void goLogInScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        LoginManager.getInstance().logOut();
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(firebaseAuthListener != null){
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }

    /*private void validador(final String correo){
        Call<RestObj> call = userService.login(correo);
        call.enqueue(new Callback<RestObj>() {
            @Override
            public void onResponse(Call<RestObj> call, Response<RestObj> response) {
                if (response.isSuccessful()){
                    RestObj restObj = response.body();
                    if (restObj.getMessage().equals("true")){
                        Toast.makeText(getApplicationContext(),"El correo "+correo+" SI existe",Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"El correo "+correo+" NO existe",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Error, intente de nuevo.",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RestObj> call, Throwable t) {
                Toast.makeText(getApplicationContext(),""+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }*/

}
