package coursera.cocodibuja.android.petagram.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import coursera.cocodibuja.android.petagram.pojo.MascotaPerfil;
import coursera.cocodibuja.android.petagram.restApi.JsonKeys;
import coursera.cocodibuja.android.petagram.restApi.modelo.MascotaPerfilResponse;

/**
 * Created by nicopro on 30/8/16.
 */
public class MascotaPerfilDeserializador implements JsonDeserializer<MascotaPerfilResponse> {
    @Override
    public MascotaPerfilResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaPerfilResponse mascotaPerfilResponse = gson.fromJson(json, MascotaPerfilResponse.class);
        JsonArray mascotaPerfilResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        mascotaPerfilResponse.setMascotas(desearializarMascotaPerfilDeJson(mascotaPerfilResponseData));
        return mascotaPerfilResponse;
    }

    private  ArrayList<MascotaPerfil> desearializarMascotaPerfilDeJson(JsonArray mascotaPerfilResponseData){
        ArrayList<MascotaPerfil> mascotasPerfil = new ArrayList<>();
        for (int i = 0; i < mascotaPerfilResponseData.size(); i++)
        {
            JsonObject mascotaPerfilResponseDataObject = mascotaPerfilResponseData.get(i).getAsJsonObject();

            JsonObject userJson = mascotaPerfilResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto = userJson.get(JsonKeys.USER_FULLNAME).getAsString();


            JsonObject imageJson             = mascotaPerfilResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGE);
            JsonObject stdResolutionJson     = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto                   = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = mascotaPerfilResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();


            MascotaPerfil mascotaActual = new MascotaPerfil();
            mascotaActual.setId(id);
            mascotaActual.setNombreCompleto(nombreCompleto);
            mascotaActual.setUrlFoto(urlFoto);
            mascotaActual.setLikes(likes);

            mascotasPerfil.add(mascotaActual);
        }


        return mascotasPerfil;
    }

   /* private ArrayList<MascotaPerfil> desearializarMascotaPerfilDeJson(JsonArray mascotaPerfilResponseData){
        ArrayList<MascotaPerfil> mascotasPerfilDesdeJson = new ArrayList<>();
        for (int i=0; i<mascotaPerfilResponseData.size();i++ )
        {
            JsonObject mascotaResponseDataObject = mascotaPerfilResponseData.get(i).getAsJsonObject();

            JsonObject userJson = mascotaResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto = userJson.get(JsonKeys.USER_FULLNAME).getAsString();

            JsonObject imageJson             = mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject standarResolutionJson = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDAR_RESOLUTION_IMAGES);
            String urlFoto                   = standarResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson             = mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();


            MascotaPerfil mascotaActual = new MascotaPerfil();
            mascotaActual.setId(id);
            mascotaActual.setNombreCompleto(nombreCompleto);
            mascotaActual.setUrlFoto(urlFoto);
            mascotaActual.setLikes(likes);

            mascotasPerfilDesdeJson.add(mascotaActual);
        }
        return mascotasPerfilDesdeJson;
    }
    */
}
