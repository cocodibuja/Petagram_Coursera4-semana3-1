package coursera.cocodibuja.android.petagram.restApi.modelo;

/**
 * Created by nicopro on 5/10/16.
 */

public class UsuarioResponseFirebase {

    private String id,
            token,
            nombreUsuario;

    public UsuarioResponseFirebase() {
    }

    public UsuarioResponseFirebase(String id, String token, String nombreUsuario) {
        this.id = id;
        this.token = token;
        this.nombreUsuario = nombreUsuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsuario() {
        return nombreUsuario;
    }

    public void setUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
