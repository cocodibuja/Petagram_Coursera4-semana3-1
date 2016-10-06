package coursera.cocodibuja.android.petagram.vista.fragment;

import java.util.ArrayList;

import coursera.cocodibuja.android.petagram.adaptader.MascotaAdaptador;
import coursera.cocodibuja.android.petagram.pojo.Mascota;

/**
 * Created by nicopro on 24/8/16.
 */
public interface IPresentacionFragmentView {

    public void generarLinearLayoutVertical();
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);
    public void inicializarAdaptadorEnRecyclerView(MascotaAdaptador mascotaAdaptador);

}
