package com.example.aguapurificadacelica.activities.client;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aguapurificadacelica.R;
import com.example.aguapurificadacelica.activities.Home.Home;
import com.example.aguapurificadacelica.activities.includes.MyToolbar;
import com.example.aguapurificadacelica.activities.models.Client;
import com.example.aguapurificadacelica.activities.providers.AuthProvider;
import com.example.aguapurificadacelica.activities.providers.ClientProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class RegisterActivity extends AppCompatActivity {

    AuthProvider mAuthProvider;
    ClientProvider mClientProvider;

    // VIEWS
    Button mButtonRegister;
    TextInputEditText mTextInputEmail;
    TextInputEditText mTextInputName;
    TextInputEditText mTextInputPassword;
    TextInputEditText mTextInputPhone;
    TextInputEditText mTextInputCedula;

    AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        MyToolbar.show(this, "Registro de usuario", true);

       mAuthProvider = new AuthProvider();
       mClientProvider = new ClientProvider();

      // mDialog = new SpotsDialog.Builder().setContext(RegisterActivity.this).setMessage("Espere un momento").build();


        mButtonRegister = findViewById(R.id.btnRegister);
        mTextInputEmail = findViewById(R.id.textInputEmail);
        mTextInputName = findViewById(R.id.textInputName);
        mTextInputPassword = findViewById(R.id.textInputPassword);
        mTextInputCedula= findViewById(R.id.textInputCedula);
        mTextInputPhone = findViewById(R.id.textInputCelular);
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickRegister();
            }
        });
    }

    void clickRegister() {
        final String name = mTextInputName.getText().toString();
        final String email = mTextInputEmail.getText().toString();
        final String password = mTextInputPassword.getText().toString();
        final String cedula = mTextInputCedula.getText().toString();
        final String phone = mTextInputPhone.getText().toString();
        if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            if (password.length() >= 6) {
                if(cedula.length()==10){
                    if(phone.length()==10){
                        mDialog.show();
                        register(name, email, password,cedula,phone);
                    }else {
                        Toast.makeText(this, "El numero de celular debe tener 10 numeros", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(this, "La cedula debe tener 10 numeros", Toast.LENGTH_SHORT).show();
                }

            }
            else {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    void register(final String name, final String email, String password, final String cedula, final String phone) {
        mAuthProvider.register(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mDialog.hide();
                if (task.isSuccessful()) {
                    String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Client client = new Client(id, name, email,cedula,phone);
                    create(client);
                }
                else {
                    Toast.makeText(RegisterActivity.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void create(Client client) {
        mClientProvider.create(client).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(RegisterActivity.this, Home.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RegisterActivity.this, "No se pudo crear el cliente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    
    /*
    void saveUser(String id, String name, String email) {
        String selectedUser = mPref.getString("user", "");
        User user = new User();
        user.setEmail(email);
        user.setName(name);

        if (selectedUser.equals("driver")) {
            mDatabase.child("Users").child("Drivers").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Fallo el registro", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else if (selectedUser.equals("client")){
            mDatabase.child("Users").child("Clients").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Fallo el registro", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    */
     

}
