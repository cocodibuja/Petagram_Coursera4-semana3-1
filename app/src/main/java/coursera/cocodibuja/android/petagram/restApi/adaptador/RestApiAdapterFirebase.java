package coursera.cocodibuja.android.petagram.restApi.adaptador;

import coursera.cocodibuja.android.petagram.restApi.ConstantesRestApi;
import coursera.cocodibuja.android.petagram.restApi.IEndPointsApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nicopro on 5/10/16.
 */

public class RestApiAdapterFirebase {

    public IEndPointsApi establecerConexionRestAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_FIREBASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;
        return retrofit.create(IEndPointsApi.class);

    }
}
