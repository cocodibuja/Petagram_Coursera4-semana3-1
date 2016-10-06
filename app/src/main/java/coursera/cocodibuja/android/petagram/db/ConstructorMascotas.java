package coursera.cocodibuja.android.petagram.db;

import android.content.Context;

import java.util.ArrayList;

import coursera.cocodibuja.android.petagram.R;
import coursera.cocodibuja.android.petagram.pojo.Mascota;

/**
 * Created by nicopro on 24/8/16.
 */
public class ConstructorMascotas {

    ArrayList<Mascota> arrayListMascotas;
    private Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos(){

        arrayListMascotas = new ArrayList<Mascota>();
        arrayListMascotas.add(new Mascota(R.drawable.perro_feliz,"pepe Grillo", "7777777","pepegrillo@gmail.com",1));
        arrayListMascotas.add(new Mascota(R.drawable.perro_globo,"Juan peroz", "22222222","juanperoz@gmail.com",2));
        arrayListMascotas.add(new Mascota(R.drawable.perro_orejas,"Amalia Gomez", "55557777","AmaliaGomez@gmail.com",3));
        arrayListMascotas.add(new Mascota(R.drawable.perro_star,"Joana Lopez", "43334444443","Joanalopez@gmail.com",4));
        return arrayListMascotas;
    }


}
