package coursera.cocodibuja.android.petagram.restApi.modelo;

import java.util.ArrayList;

import coursera.cocodibuja.android.petagram.pojo.MascotaPerfil;

/**
 * Created by nicopro on 30/8/16.
 */
public class MascotaPerfilResponse {

    ArrayList<MascotaPerfil> mascotas;

    public ArrayList<MascotaPerfil> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<MascotaPerfil> mascotas) {
        this.mascotas = mascotas;
    }
}
