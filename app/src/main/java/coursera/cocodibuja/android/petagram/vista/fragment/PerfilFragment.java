package coursera.cocodibuja.android.petagram.vista.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import coursera.cocodibuja.android.petagram.R;
import coursera.cocodibuja.android.petagram.adaptader.MascotaPerfilAdaptador;
import coursera.cocodibuja.android.petagram.pojo.MascotaPerfil;
import coursera.cocodibuja.android.petagram.presentador.PerfilFragmentPresenter;
import coursera.cocodibuja.android.petagram.restApi.ConstantesRestApi;
import coursera.cocodibuja.android.petagram.restApi.JsonKeys;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment implements IPerfilFragmentView {

    ArrayList<MascotaPerfil> arrayListMascotas;
    private RecyclerView rvListaMascotas;
    private PerfilFragmentPresenter presenter;
    TextView nombreUsuarioActual;
    CircularImageView imagenPerfilUsuarioActual;

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);
        rvListaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);
        presenter = new PerfilFragmentPresenter(this,getContext());

        nombreUsuarioActual = (TextView) v.findViewById(R.id.TextView_NombreUsuario);
        imagenPerfilUsuarioActual = (CircularImageView) v.findViewById(R.id.circularimageview_fotoPerfil);

        MostrarInformacionUsuario();

        return v;
    }


    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvListaMascotas.setLayoutManager(llm); // le estoy diciendo que reciclerView se comporte como linearlayoutManager

    }

    @Override
    public MascotaPerfilAdaptador crearAdaptador(ArrayList<MascotaPerfil> mascotas) {

       MascotaPerfilAdaptador mascotaPerfilAdaptador = new MascotaPerfilAdaptador(mascotas,getActivity());
        return mascotaPerfilAdaptador;

    }

    @Override
    public void inicializarAdaptadorEnRecyclerView(MascotaPerfilAdaptador mascotaAdaptador) {
        rvListaMascotas.setAdapter(mascotaAdaptador);
    }

    @Override
    public void generarGridLayout() {
        int cantidad_de_columnas = 3;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),cantidad_de_columnas);
        rvListaMascotas.setLayoutManager(gridLayoutManager);
    }

    public void MostrarInformacionUsuario(){
        SharedPreferences preferences = getContext().getSharedPreferences("datosDeIntercambio", Context.MODE_PRIVATE);
        String userFullName = preferences.getString(JsonKeys.USER_FULLNAME, ConstantesRestApi.USUARIO);
        String userProfilePicture = preferences
                .getString(JsonKeys.USER_FOTO_PERFIL,ConstantesRestApi.IMAGEN_URL_PERFIL);

        nombreUsuarioActual.setText(userFullName);

        //Toast.makeText(getContext(),"urlImagen:"+userProfilePicture,Toast.LENGTH_SHORT).show();

        Picasso.with(getContext())
                .load(userProfilePicture)
                .placeholder(R.drawable.perro_globo)
                .into(imagenPerfilUsuarioActual);

         /* String profilePicture = preps.getString(JsonKeys.PROFILE_PICTURE, "");

        TextView tvNombre = (TextView) view.findViewById(R.id.tvNombreUsuario);
        CircularImageView profilePic = (CircularImageView) view.findViewById(R.id.ivProfilePicture);

        if (!profilePicture.equals("")) {

            Picasso.with(getContext())
                    .load(profilePicture)
                    .placeholder(R.drawable.footprint).into(profilePic);
        * */
    }

}
