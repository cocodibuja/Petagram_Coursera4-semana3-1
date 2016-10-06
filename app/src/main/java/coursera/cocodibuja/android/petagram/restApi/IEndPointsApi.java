package coursera.cocodibuja.android.petagram.restApi;

import coursera.cocodibuja.android.petagram.restApi.modelo.MascotaPerfilResponse;
import coursera.cocodibuja.android.petagram.restApi.modelo.UsuarioResponseFirebase;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by nicopro on 30/8/16.
 */
public interface IEndPointsApi {
/*
    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER) //<-- lo guarde por las dudas
    Call<MascotaPerfilResponse> getRecentMedia();
*/


    @GET(ConstantesRestApi.URL_RECENT_MEDIA_USER_POR_ID)
    Call<MascotaPerfilResponse> getRecentMedia(@Path("user_id") String userId,
                                               @Query("access_token") String accessToken);

     @GET(ConstantesRestApi.URL_ID_DEL_USUARIO)
    public Call<MascotaPerfilResponse> search(@Query("q") String query, @Query("access_token") String accessToken);

/*
    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN)
    Call<UsuarioResponseFirebase> registrarTokenID(@Field("token") String token);

*/
    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN)
    Call<UsuarioResponseFirebase> registrarTokenID(@Field("token") String  token, @Field("nombreUsuario") String nombreUsuario);

}
