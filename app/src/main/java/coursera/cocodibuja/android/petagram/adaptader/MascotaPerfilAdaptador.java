package coursera.cocodibuja.android.petagram.adaptader;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import coursera.cocodibuja.android.petagram.DetalleMascotaPerfilActivity;
import coursera.cocodibuja.android.petagram.R;
import coursera.cocodibuja.android.petagram.pojo.MascotaPerfil;

/**
 * Created by nicopro on 24/8/16.
 */
public class MascotaPerfilAdaptador extends RecyclerView.Adapter <MascotaPerfilAdaptador.MascotaViewHolder>{

    ArrayList<MascotaPerfil> arrayListMascotas;
    Activity activity;

    public MascotaPerfilAdaptador(ArrayList<MascotaPerfil> arrayListMascotas, Activity activity) {
        this.arrayListMascotas = arrayListMascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota_perfil,parent,false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MascotaViewHolder mascotaViewHolder, int position) {
        final MascotaPerfil mascota = arrayListMascotas.get(position);
      //  mascotaViewHolder.imgFoto.setImageResource(mascota.getUrlFoto());
        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.perro_globo)
                .into(mascotaViewHolder.imgFoto);

        mascotaViewHolder.tvLikes.setText(String.valueOf(mascota.getLikes()));

        mascotaViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAbrirDetalleMascotaPerfil = new Intent(activity, DetalleMascotaPerfilActivity.class);

                intentAbrirDetalleMascotaPerfil.putExtra("urlImagen",mascota.getUrlFoto());
                intentAbrirDetalleMascotaPerfil.putExtra("like",mascota.getLikes());
                activity.startActivity(intentAbrirDetalleMascotaPerfil);

            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayListMascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        // declaramos lo que tenemos en el cardview_mascota

        private ImageView imgFoto;
        private TextView tvLikes;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto     = (ImageView) itemView.findViewById(R.id.imgFoto);
             tvLikes     = (TextView) itemView.findViewById(R.id.tvLikes);
        }
    }

}
