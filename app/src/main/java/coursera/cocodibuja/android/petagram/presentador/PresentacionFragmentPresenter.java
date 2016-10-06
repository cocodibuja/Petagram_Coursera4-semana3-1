package coursera.cocodibuja.android.petagram.presentador;

import android.content.Context;

import java.util.ArrayList;

import coursera.cocodibuja.android.petagram.db.ConstructorMascotas;
import coursera.cocodibuja.android.petagram.pojo.Mascota;
import coursera.cocodibuja.android.petagram.vista.fragment.IPresentacionFragmentView;

/**
 * Created by nicopro on 24/8/16.
 */
public class PresentacionFragmentPresenter implements IPresentacionFragmentPresenter {



    private IPresentacionFragmentView iPresentacionFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    ArrayList<Mascota> macostas;


    public PresentacionFragmentPresenter(IPresentacionFragmentView iPresentacionFragmentView, Context context) {
        this.iPresentacionFragmentView = iPresentacionFragmentView;
        this.context = context;
        obtenerDatosDeBaseDatos();
       // Log.d("obtenerDatos","PresentacionFragmentPresenter")
    }

    @Override
    public void obtenerDatosDeBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        macostas = constructorMascotas.obtenerDatos();
        mostarMascotasEnRecyclerView();


    }

    @Override
    public void mostarMascotasEnRecyclerView() {

        iPresentacionFragmentView.inicializarAdaptadorEnRecyclerView(iPresentacionFragmentView.crearAdaptador(macostas));
        iPresentacionFragmentView.generarLinearLayoutVertical();
    }


}
