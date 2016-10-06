package coursera.cocodibuja.android.petagram.restApi.adaptador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import coursera.cocodibuja.android.petagram.restApi.ConstantesRestApi;
import coursera.cocodibuja.android.petagram.restApi.IEndPointsApi;
import coursera.cocodibuja.android.petagram.restApi.deserializador.MascotaPerfilBusquedaIDDeserializador;
import coursera.cocodibuja.android.petagram.restApi.deserializador.MascotaPerfilDeserializador;
import coursera.cocodibuja.android.petagram.restApi.modelo.MascotaPerfilResponse;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nicopro on 30/8/16.
 */
public class RestApiAdapter {
    public IEndPointsApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(IEndPointsApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaPerfilResponse.class,new MascotaPerfilDeserializador());   // buscamos el MascotaPerfilDeserializador lo asocie al MascotaPerfil
        return gsonBuilder.create();

    }

    public Gson construyeGsonDeserializadorBusquedaID(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaPerfilResponse.class,new MascotaPerfilBusquedaIDDeserializador());
        return   gsonBuilder.create();
    }
}
