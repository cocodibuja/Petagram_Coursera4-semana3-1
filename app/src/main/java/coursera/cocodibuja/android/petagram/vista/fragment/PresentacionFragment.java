package coursera.cocodibuja.android.petagram.vista.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import coursera.cocodibuja.android.petagram.R;
import coursera.cocodibuja.android.petagram.adaptader.MascotaAdaptador;
import coursera.cocodibuja.android.petagram.pojo.Mascota;
import coursera.cocodibuja.android.petagram.presentador.IPresentacionFragmentPresenter;
import coursera.cocodibuja.android.petagram.presentador.PresentacionFragmentPresenter;


/**
 * A simple {@link Fragment} subclass.
 */
public class PresentacionFragment extends Fragment implements IPresentacionFragmentView{

    ArrayList<Mascota> arrayListMascotas;
    private RecyclerView rvListaMascotas;
    private IPresentacionFragmentPresenter presenter;



    public PresentacionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_presentacion, container, false);
        rvListaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);
        presenter = new PresentacionFragmentPresenter(this,getContext());
        return v;
    }



    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvListaMascotas.setLayoutManager(llm); // le estoy diciendo que reciclerView se comporte como linearlayoutManager
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador mascotaAdaptador = new MascotaAdaptador(mascotas,getActivity());// aca no hace falta volver a definirlo por que lo declaramos arriba y lo inicializamos y cargamos en public void inicializarListaDeContactos()
        return mascotaAdaptador;
    }

    @Override
    public void inicializarAdaptadorEnRecyclerView(MascotaAdaptador mascotaAdaptador) {
        rvListaMascotas.setAdapter(mascotaAdaptador); //lo tengo que mostrar

    }



}
