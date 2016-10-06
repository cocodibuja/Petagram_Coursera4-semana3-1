package coursera.cocodibuja.android.petagram.vista.fragment;

import java.util.ArrayList;

import coursera.cocodibuja.android.petagram.adaptader.MascotaPerfilAdaptador;
import coursera.cocodibuja.android.petagram.pojo.MascotaPerfil;

/**
 * Created by nicopro on 26/8/16.
 */
public interface IPerfilFragmentView {

    public void generarLinearLayoutVertical();
    public MascotaPerfilAdaptador crearAdaptador(ArrayList<MascotaPerfil> mascotas);
    public void inicializarAdaptadorEnRecyclerView(MascotaPerfilAdaptador mascotaAdaptador);
    public void generarGridLayout();

}
