package coursera.cocodibuja.android.petagram.restApi;

/**
 * Created by nicopro on 30/8/16.
 */
public final class ConstantesRestApi {

    // es final por que son constante

    //https://api.instagram.com/v1/
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "3687996825.ce605dc.35f2a1f7a7614d84ba72777d40d5b535";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String USUARIO = "perritolike";
    public static final String USUARIO_ID = "3687996825";
    public static final String IMAGEN_URL_PERFIL = "https://igcdn-photos-c-a.akamaihd.net/hphotos-ak-xfl1/t51.2885-19/s150x150/14027228_150943048682778_1058312691_a.jpg";


    public static final String URL_RECENT_MEDIA_USER_POR_ID= "users/{user_id}/media/recent/";
    public static final String URL_ID_DEL_USUARIO = "users/search";

    public static final String ROOT_FIREBASE_URL = "https://pure-refuge-12837.herokuapp.com/";
    public static final String KEY_POST_ID_TOKEN = "registrar-usuario/";

}
