package com.example.pablo_lema.a24find;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pablo_lema.a24find.modelo.Usuario;
import com.example.pablo_lema.a24find.utilidades.ClienteRest;
import com.example.pablo_lema.a24find.utilidades.OnTaskCompleted;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class SuccesLoginActivity extends AppCompatActivity implements OnTaskCompleted {

    private String nombreUsuario;
    private String correoUsuario;
    private String profilePicture;

    private ImageView profilePictureView;
    private TextView tvtNombreUsuario;
    private TextView tvtCorreoUsuario;

    private ProfileTracker profileTracker;

    private Button btnContinuar;
    private Button btnSalir;
    private String IP = "35.229.114.53";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succes_login);
        inicializarComponentes();

    }

    public void inicializarComponentes() {
        profilePictureView = findViewById(R.id.profilePicture);

        tvtNombreUsuario = findViewById(R.id.tvtNombreUsuario);
        tvtCorreoUsuario = findViewById(R.id.tvtCorreoUsuario);
        btnContinuar = findViewById(R.id.btnContinuar);

        btnSalir = findViewById(R.id.btnSalir);

        accioneFB();
        acciones();
    }

    public void accioneFB() {
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (currentProfile != null) {
                    displayProfileInfo(currentProfile);
                }
            }
        };

        if (AccessToken.getCurrentAccessToken() == null) {
            goLoginScreen();
        } else {
            requestEmail(AccessToken.getCurrentAccessToken());

            Profile profile = Profile.getCurrentProfile();
            if (profile != null) {
                displayProfileInfo(profile);
            } else {
                Profile.fetchProfileForCurrentAccessToken();
            }
        }
    }

    private void requestEmail(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if (response.getError() != null) {
                    Toast.makeText(getApplicationContext(), response.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    String email = object.getString("email");
                    setEmail(email);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email, gender, birthday, location");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void setEmail(String correoUsuario) {
        this.correoUsuario = correoUsuario;
        tvtCorreoUsuario.setText(correoUsuario);
    }

    private void displayProfileInfo(Profile profile) {

        nombreUsuario = profile.getName();
        profilePicture = profile.getProfilePictureUri(500, 500).toString();

        tvtNombreUsuario.setText(nombreUsuario);
        tvtCorreoUsuario.setText(correoUsuario);

        Picasso.with(getApplicationContext()).load(profilePicture).into(profilePictureView);
    }

    public void logout(View view) {
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }


    public void acciones() {
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                guardarUsuario();
                Toast.makeText(view.getContext(), "Registrado Correctamente", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(view);
            }
        });
    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @Override
    public void onTaskCompleted(int idSolicitud, String result) {

    }

    public void guardarUsuario() {
        ClienteRest clienteRest = new ClienteRest(this);
        try {
            String URL = "http://192.168.1.103:8080/24FINDAPP_/srv/24find/guardarUsuario";
            Usuario usuario = new Usuario();

            usuario.setNombre(nombreUsuario);
            usuario.setCorreo(correoUsuario);
            usuario.setProfilePicture(profilePicture);

            clienteRest.doPost(URL, usuario, 1, true, this);
        } catch (Exception e) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        profileTracker.stopTracking();
    }
}

