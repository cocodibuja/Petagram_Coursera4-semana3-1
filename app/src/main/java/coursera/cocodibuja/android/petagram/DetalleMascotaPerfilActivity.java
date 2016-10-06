package coursera.cocodibuja.android.petagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleMascotaPerfilActivity extends AppCompatActivity {
    private static final String KEY_EXTRA_URL = "urlImagen";
    private static final String KEY_EXTRA_LIKES = "like";
    private ImageView imageViewFotoDetalle;
    private TextView tvLikesDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota_perfil);

            Bundle extras = getIntent().getExtras();
            String url = extras.getString(KEY_EXTRA_URL);
            int likes = extras.getInt(KEY_EXTRA_LIKES);

            tvLikesDetalle = (TextView) findViewById(R.id.tvLikesDetalle);
            tvLikesDetalle.setText(String.valueOf(likes));


    }
}
