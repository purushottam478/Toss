package com.example.rajulnahar.toss.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.rajulnahar.toss.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginActivity extends AppCompatActivity {

    public String user_id;

    SignInButton btnSignInG;
    GoogleSignInOptions gso;
    PackageInfo info;
    GoogleApiClient googleApiClient;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_login);

        AppEventsLogger.activateApp(this);

        SharedPreferences sharedPreferences = getSharedPreferences("toss",MODE_PRIVATE);
        user_id  = sharedPreferences.getString("toss_user",null);
        if(user_id!= null){
            sucessLogin();
        }
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginActivity.this, loginResult.getAccessToken().getUserId(), Toast.LENGTH_SHORT).show();
                user_id = loginResult.getAccessToken().getUserId().toString();
                sucessLogin();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }

             });
        btnSignInG = (SignInButton) findViewById(R.id.btnSignInG);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        btnSignInG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signIn();

            }
        });

    }

    private void signIn()   {

        Intent  signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100)  {
            GoogleSignInResult result= Auth.GoogleSignInApi
                    .getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
        else    {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void  handleSignInResult(GoogleSignInResult result) {
        if(result.isSuccess())  {
            GoogleSignInAccount acct = result.getSignInAccount();
            Toast.makeText(this, acct.getEmail(), Toast.LENGTH_SHORT).show();
            user_id= acct.getEmail();
            sucessLogin();
        }
        else    {
            Toast.makeText(this, "Failed!!!", Toast.LENGTH_SHORT).show();
        }
    }

    private  void sucessLogin() {

        SharedPreferences sharedPreferences = getSharedPreferences("toss",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("toss_user",user_id);
        editor.commit();
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
