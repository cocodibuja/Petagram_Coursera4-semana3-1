package coursera.cocodibuja.android.petagram.presentador;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import coursera.cocodibuja.android.petagram.db.ConstructorMascotasPerfil;
import coursera.cocodibuja.android.petagram.pojo.MascotaPerfil;
import coursera.cocodibuja.android.petagram.restApi.ConstantesRestApi;
import coursera.cocodibuja.android.petagram.restApi.IEndPointsApi;
import coursera.cocodibuja.android.petagram.restApi.JsonKeys;
import coursera.cocodibuja.android.petagram.restApi.adaptador.RestApiAdapter;
import coursera.cocodibuja.android.petagram.restApi.modelo.MascotaPerfilResponse;
import coursera.cocodibuja.android.petagram.vista.fragment.IPerfilFragmentView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nicopro on 26/8/16.
 */
public class PerfilFragmentPresenter implements IPerfilFragmentPresenter {



    private IPerfilFragmentView iPerfilFragmentView;
    private Context context;
    private ConstructorMascotasPerfil constructorMascotasPerfil; //<-- ver uno que se conecte al API y otro al sqlite
    ArrayList<MascotaPerfil> mascotasPerfil;
    String usuarioIDInstagram;

    public PerfilFragmentPresenter(IPerfilFragmentView iPerfilFragmentView, Context context) {
        this.iPerfilFragmentView = iPerfilFragmentView;
        this.context = context;
       // obtenerDatosDeBaseDatos();
        obtenerMediosRecientes();
    }

    @Override
    public void obtenerDatosDeBaseDatos() {
        constructorMascotasPerfil = new ConstructorMascotasPerfil(context);
        mascotasPerfil = constructorMascotasPerfil.obtenerDatos();
        mostarMascotasEnRecyclerView();

    }

    @Override
    public void mostarMascotasEnRecyclerView() {

        iPerfilFragmentView.inicializarAdaptadorEnRecyclerView(iPerfilFragmentView.crearAdaptador(mascotasPerfil));
        iPerfilFragmentView.generarGridLayout();


    }

    @Override
    public void obtenerMediosRecientes() {

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        IEndPointsApi iEndPointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
       // final Call<MascotaPerfilResponse> mascotaPerfilResponseCall = iEndPointsApi.getRecentMedia();//ConstantesRestApi.USUARIO, ConstantesRestApi.ACCESS_TOKEN);




        SharedPreferences preferences =   context.getApplicationContext()
                .getSharedPreferences("datosDeIntercambio", Context.MODE_PRIVATE);
        usuarioIDInstagram = preferences.getString(JsonKeys.USER_ID, ConstantesRestApi.USUARIO_ID);


        //usuarioIDInstagram = context.getResources().getString(R.string.id_instagram);
        final Call<MascotaPerfilResponse> mascotaPerfilResponseCall = iEndPointsApi.
                getRecentMedia(usuarioIDInstagram, ConstantesRestApi.ACCESS_TOKEN);

        mascotaPerfilResponseCall.enqueue(new Callback<MascotaPerfilResponse>() {
            @Override
            public void onResponse(Call<MascotaPerfilResponse> call, Response<MascotaPerfilResponse> response) {

                MascotaPerfilResponse mascotaPerfilResponse = response.body();
                mascotasPerfil = mascotaPerfilResponse.getMascotas();
                mostarMascotasEnRecyclerView();

                Log.e("HAY CONEXION","FUNCA");

                Toast.makeText(context,"Conexiooonnnn",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<MascotaPerfilResponse> call, Throwable t) {
                Toast.makeText(context,"Algo paso en la conexion! intenta de nuevo",Toast.LENGTH_SHORT).show();

                Log.e("fallo la cooooonexion",t.toString());
            }
        });


    }
}
