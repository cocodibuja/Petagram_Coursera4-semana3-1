package coursera.cocodibuja.android.petagram;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import coursera.cocodibuja.android.petagram.pojo.MascotaPerfil;
import coursera.cocodibuja.android.petagram.restApi.ConstantesRestApi;
import coursera.cocodibuja.android.petagram.restApi.IEndPointsApi;
import coursera.cocodibuja.android.petagram.restApi.JsonKeys;
import coursera.cocodibuja.android.petagram.restApi.adaptador.RestApiAdapter;
import coursera.cocodibuja.android.petagram.restApi.modelo.MascotaPerfilResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfigurarCuentaActivity extends AppCompatActivity {

    private TextView nombreUsuarioBuscado;
    private String nombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);

        nombreUsuarioBuscado = (TextView) findViewById(R.id.EditText_usurioBusqueda);
    }

    public void buscarUsuario(View view) {


        nombreUsuario = nombreUsuarioBuscado.getText().toString(); //"poloth85";//

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonBusquedaID = restApiAdapter.construyeGsonDeserializadorBusquedaID();
        IEndPointsApi iEndPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonBusquedaID);

        final Call<MascotaPerfilResponse> mascotaPerfilResponseCall = iEndPointsApi.search(nombreUsuario,ConstantesRestApi.ACCESS_TOKEN);

        mascotaPerfilResponseCall.enqueue(new Callback<MascotaPerfilResponse>() {
            @Override
            public void onResponse(Call<MascotaPerfilResponse> call, Response<MascotaPerfilResponse> response) {


                MascotaPerfil mascotaPerfil = response.body().getMascotas().get(0);

                if(mascotaPerfil == null){
                    Toast.makeText(getApplicationContext(),"no se encuentra lo que estan buscando",Toast.LENGTH_SHORT).show();

                }else{
                    Log.e("Busca ID del usuario","FUNCA");

                    response.body().getMascotas().get(0);
                  //  Toast.makeText(getApplicationContext(),"nombre: "+mascotaPerfil.getNombreCompleto()+" id: "+mascotaPerfil.getId(),Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(ConfigurarCuentaActivity.this, MainActivity.class);
 /*                   intent.putExtra(getResources().getString(R.string.nombreusuario_instagram),mascotaPerfil.getNombreCompleto());
                    intent.putExtra(getResources().getString(R.string.id_instagram),mascotaPerfil.getId());

*/


                    //************
                    SharedPreferences preps = getSharedPreferences("datosDeIntercambio", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = preps.edit();
                    String idUsuario = mascotaPerfil.getId();
                    edit.putString(JsonKeys.USER_ID, idUsuario);
                    edit.putString(JsonKeys.USER_FULLNAME, mascotaPerfil.getNombreCompleto());
                    edit.putString(JsonKeys.USER_FOTO_PERFIL, mascotaPerfil.getUrlFoto());


                    edit.commit();
                    //--------
                    startActivity(intent);




                    finish();
                }


            }

            @Override
            public void onFailure(Call<MascotaPerfilResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Algo paso en la conexion! intenta de nuevo",Toast.LENGTH_SHORT).show();

                Log.e("fallo la cONEXION ID",t.toString());
            }
        });



    }
}
