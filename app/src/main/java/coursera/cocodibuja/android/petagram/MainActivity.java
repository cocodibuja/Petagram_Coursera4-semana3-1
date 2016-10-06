package coursera.cocodibuja.android.petagram;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import coursera.cocodibuja.android.petagram.adaptader.ViewPagerAdapter;
import coursera.cocodibuja.android.petagram.restApi.ConstantesRestApi;
import coursera.cocodibuja.android.petagram.restApi.IEndPointsApi;
import coursera.cocodibuja.android.petagram.restApi.JsonKeys;
import coursera.cocodibuja.android.petagram.restApi.adaptador.RestApiAdapterFirebase;
import coursera.cocodibuja.android.petagram.restApi.modelo.UsuarioResponseFirebase;
import coursera.cocodibuja.android.petagram.vista.fragment.PerfilFragment;
import coursera.cocodibuja.android.petagram.vista.fragment.PresentacionFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);



        setUpViewPager(); // aca llamo a mi metodo para que cree los tab y los llene con fragments
        if (toolbar !=null){
            setSupportActionBar(toolbar);
        }





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        if (id == R.id.action_configurar_cuenta) {
            Intent configurar = new Intent(MainActivity.this,ConfigurarCuentaActivity.class);
            startActivity(configurar);
        }
        if(id == R.id.action_recibir_notificaciones) {
            enviarToken();
        }

        return super.onOptionsItemSelected(item);
    }


    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new PresentacionFragment()); // en el primer TAB
        fragments.add(new PerfilFragment());       //Segundo TAB
        return fragments;

    }


    public void setUpViewPager(){



        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(),agregarFragments()));
        viewPager.setCurrentItem(0); //con esto digo donde quiero que empiece a verse los fragment
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_account_circle);
    }

    public void enviarToken(){

        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("TOKEN", "Refreshed token: " + refreshedToken);
        enviarTokenRegistro(refreshedToken);

    }

    private void enviarTokenRegistro(String token){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("datosDeIntercambio", Context.MODE_PRIVATE);
        String userFullName = preferences.getString(JsonKeys.USER_FULLNAME, ConstantesRestApi.USUARIO);
        Log.d("MI TOKEN",token);
        RestApiAdapterFirebase restApiAdapterFirebase = new RestApiAdapterFirebase();
        IEndPointsApi iEndPointsApi = restApiAdapterFirebase.establecerConexionRestAPI();

        Call<UsuarioResponseFirebase> usuarioResponseCall = iEndPointsApi.registrarTokenID(token,userFullName); //ES LO QUE MI SERVIDOR VA A ESTAR RECIBIENDO

        usuarioResponseCall.enqueue(new Callback<UsuarioResponseFirebase>() {
            @Override
            public void onResponse(Call<UsuarioResponseFirebase> call, Response<UsuarioResponseFirebase> response) {
                UsuarioResponseFirebase usuarioResponse = response.body();
                Log.d("ID_FIREBASE", usuarioResponse.getId());
                Log.d("USUARIO_FIREBASE", usuarioResponse.getToken());
            }

            @Override
            public void onFailure(Call<UsuarioResponseFirebase> call, Throwable t) {

            }
        });
    }





}
