package com.example.pablo_lema.a24find;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private Button btntOmitir;

    private String fbUserFirstName;
    private String fbUserEmail;
    private String fbBirthday;
    private String profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        acciones();

        if (AccessToken.getCurrentAccessToken() != null && com.facebook.Profile.getCurrentProfile() != null) {
            Intent intent = new Intent(LoginActivity.this, SuccesLoginActivity.class);
            startActivity(intent);
            finish();
        }

        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        callbackManager = CallbackManager.Factory.create();

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                performGraphRequest(loginResult);
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d("mylog", "Successful: " + exception.toString());
            }
        });
    }

    private void performGraphRequest(LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject json, GraphResponse response) {
                // Application code
                if (response.getError() != null) {
                    System.out.println("ERROR");
                } else {
                    System.out.println("Success");
                    String jsonresult = String.valueOf(json);
                    Log.d("mylog", "JSON Result" + jsonresult);

                    fbUserFirstName = json.optString("name");
                    fbUserEmail = json.optString("email");
                    fbBirthday = json.optString("birthday");
                    profileImage = "" + Profile.getCurrentProfile().getProfilePictureUri(500, 500);

                    Intent intent = new Intent(getApplicationContext(), SuccesLoginActivity.class);
//                    intent.putExtra("nombreUsuario", fbUserFirstName);
//                    intent.putExtra("correoUsuario", fbUserEmail);
//                    intent.putExtra("profileImage", profileImage);
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                Log.v("FaceBook Response :", response.toString());
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void acciones() {
        btntOmitir = findViewById(R.id.btnOmitir);
        btntOmitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
