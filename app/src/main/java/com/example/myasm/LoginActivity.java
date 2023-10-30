package com.example.myasm;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myasm.Model.MONHOC;
import com.example.myasm.Model.THONGTIN;
import com.example.myasm.Service.LoginService;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {
    EditText edtUser, edtPass;
    Button btnLogin;
    CheckBox chkNhoMK;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String user;
    String pass;
    TextView tvSignUp;
    FirebaseAuth mAuth;
    CallbackManager callbackManager;
//    SignInButton btnGoogle;
    Button btnGoogle, btnFacebook;


    private SignInClient oneTapClient;
    private BeginSignInRequest signUpRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtPass = findViewById(R.id.edtPass);
        edtUser = findViewById(R.id.edtUser);
        btnLogin = findViewById(R.id.btnLogin);
        chkNhoMK = findViewById(R.id.chkNhoMK);

        btnGoogle = findViewById(R.id.btnGoogle);
        btnFacebook = findViewById(R.id.btnFacebook);
//        tvSignUp = findViewById(R.id.tvSignUp);
        mAuth = FirebaseAuth.getInstance();

//        tvSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
//            }
//        });
        //chức năng đăng nhập
        mAuth.signInWithEmailAndPassword("user", "pass").addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //lấy thông tin tài khoản mới vừa đăng nhập
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else{
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Facebook
        callbackManager = CallbackManager.Factory.create();
//        check người dùng đã đăng nhập trước đó hay chưa?
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            getUserProfile(accessToken);
            Toast.makeText(this, "Đã đăng nhập", Toast.LENGTH_SHORT).show();
        }
        //nút đăng nhập/logout
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginfacebook();
            }
        });




        // Google
        oneTapClient = Identity.getSignInClient(this);
        signUpRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId(getString(R.string.default_web_client_id))
                        .setFilterByAuthorizedAccounts(false)
                        .build()).build();

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneTapClient.beginSignIn(signUpRequest)
                        .addOnSuccessListener(LoginActivity.this, new OnSuccessListener<BeginSignInResult>() {
                            @Override
                            public void onSuccess(BeginSignInResult beginSignInResult) {
                                try {
                                    IntentSenderRequest intentSenderRequest =
                                            new IntentSenderRequest.Builder(beginSignInResult.getPendingIntent().getIntentSender()).build();
                                    // Sử dụng ActivityResultLauncher ta vừa khởi tạo đến thục hiện đăng nhập
                                    startActivityForResult.launch(intentSenderRequest);
                                } catch (Exception e){
                                    Log.e(TAG, "Không thể Start One Tap UI: " + e.getLocalizedMessage());
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(TAG, "onFailure: ", e);
                            }
                        });
            }
        });



        //Sử dụng sharePreferences để nhớ user và pass
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        boolean checkRemember = sharedPreferences.getBoolean("Remember", false);
        if(checkRemember){
            String userName = sharedPreferences.getString("user", "");
            String passWord= sharedPreferences.getString("pass", "");

            edtUser.setText(userName);
            edtPass.setText(passWord);
            chkNhoMK.setChecked(checkRemember);
        }


        //đăng ký với hệ thống
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("dangnhap");
        registerReceiver(myBroadcast, intentFilter);
        
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, LoginService.class);
                 user = edtUser.getText().toString();
                 pass = edtPass.getText().toString();
                 if(user.length() > 0 && pass.length() >0){
                     Bundle bundle = new Bundle();
                     bundle.putString("user", user);
                     bundle.putString("pass", pass);
                     i.putExtras(bundle);
                     startService(i);
                 }else{
                     Toast.makeText(LoginActivity.this, "Nhập User hoặc Pass", Toast.LENGTH_SHORT).show();
                 }
            }
        });

    }

    private void loginfacebook(){
        LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "user_friends"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getUserProfile(loginResult.getAccessToken());
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Hủy đăng nhập", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(LoginActivity.this, "Lỗi trong quá trình đăng nhập", Toast.LENGTH_SHORT).show();
            }
        });
    }




    private BroadcastReceiver myBroadcast = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case "dangnhap":
                    Bundle bundle = intent.getExtras();
                    boolean check = bundle.getBoolean("check");
                    int idNguoiDung = bundle.getInt("idNguoiDung");
                    if(check){
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        boolean isRemember = chkNhoMK.isChecked();
                        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        editor.putBoolean("Remember", isRemember);
                        editor.putString("user", user);
                        editor.putString("pass", pass);
                        editor.putInt("idNguoiDung", idNguoiDung);
                        editor.apply();
                        finish();
                        startActivity(i);
                    }else{
                        Toast.makeText(context, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
                    }
            }
        }
    };
    // google
    ActivityResultLauncher<IntentSenderRequest> startActivityForResult = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    int x = 2;

                    if(result.getResultCode() == Activity.RESULT_OK) {
                        try {
                            SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(result.getData());
                            String idToken = credential.getGoogleIdToken();
                            if (idToken != null) {
                                //Thông tin email
                                String email = credential.getId();
                                //Password
                                String name = credential.getDisplayName();
                                //Chúng ta có thể lẩy ảnh của người dùng, và dùng thư viện glide để show ảnh lên UI
                                Uri avatar = credential.getProfilePictureUri();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                Toast.makeText(LoginActivity.this, "email: " + email + "\n" + "name: " + name, Toast.LENGTH_SHORT).show();
                                Toast.makeText(LoginActivity.this, "Đã đăng nhập thành công với tài khoản Google !!", Toast.LENGTH_SHORT).show();

                            }
                        } catch (Exception e) {
                            Log.e(TAG, "onFailure: ", e);
                        }
                    }else {

                    }
                }
            });

    //Facebook
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void getUserProfile(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String name = object.getString("name");
                    String email = object.getString("id");
                    String image = object.getJSONObject("picture").getJSONObject("data").getString("url");
                    Toast.makeText(LoginActivity.this, name + " - " + email + " - " + image, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,picture.width(200)");
        request.setParameters(parameters);
        request.executeAsync();
    }


}