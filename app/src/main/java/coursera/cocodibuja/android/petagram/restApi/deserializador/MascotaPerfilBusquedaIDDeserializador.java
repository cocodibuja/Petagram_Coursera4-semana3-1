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
 * Created by nicopro on 18/9/16.
 */
public class MascotaPerfilBusquedaIDDeserializador implements JsonDeserializer<MascotaPerfilResponse> {
    @Override
    public MascotaPerfilResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaPerfilResponse mascotaPerfilResponse = gson.fromJson(json, MascotaPerfilResponse.class);
        JsonArray mascotaPerfilResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        mascotaPerfilResponse.setMascotas(desearializarMascotaPerfilDeJson(mascotaPerfilResponseData));
        return mascotaPerfilResponse;
    }

    private ArrayList<MascotaPerfil> desearializarMascotaPerfilDeJson(JsonArray mascotaPerfilResponseData){
        ArrayList<MascotaPerfil> mascotasPerfil = new ArrayList<>();
        for (int i = 0; i < mascotaPerfilResponseData.size(); i++)
        {
            JsonObject mascotaPerfilResponseDataObject = mascotaPerfilResponseData.get(i).getAsJsonObject();

            String id = mascotaPerfilResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto = mascotaPerfilResponseDataObject.get(JsonKeys.USER_FULLNAME).getAsString();
            String fotoDePerfil = mascotaPerfilResponseDataObject.get(JsonKeys.USER_FOTO_PERFIL).getAsString();


            MascotaPerfil mascotaActual = new MascotaPerfil();
            mascotaActual.setId(id);
            mascotaActual.setNombreCompleto(nombreCompleto);
            mascotaActual.setUrlFoto(fotoDePerfil);

            mascotasPerfil.add(mascotaActual);
        }


        return mascotasPerfil;
    }
}
