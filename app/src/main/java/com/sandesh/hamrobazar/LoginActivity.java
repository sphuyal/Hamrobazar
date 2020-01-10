package com.sandesh.hamrobazar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sandesh.hamrobazar.BLL.LoginBLL;
import com.sandesh.hamrobazar.StrictModeClass.StrictModeClass;

public class LoginActivity extends AppCompatDialogFragment {
    private EditText etEmail, etPassword;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.Dialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_login, null);


        builder.setView(view)
                .setTitle("Login")

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), DashboardActivity.class);
                        getContext().startActivity(intent);


                    }
                })

                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (TextUtils.isEmpty(etEmail.getText()))
                        {
                            etEmail.setError("Enter email address");
                            etEmail.requestFocus();
                            return;
                        }
                        if (TextUtils.isEmpty(etPassword.getText()))
                        {
                            etPassword.setError("Enter password");
                            etPassword.requestFocus();
                            return;
                        }

                        String email = etEmail.getText().toString();
                        String pwd = etPassword.getText().toString();

                        LoginBLL loginBLL = new LoginBLL();
                        StrictModeClass.StrictMode();

                        if (loginBLL.checkUser(email, pwd)) {
                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("email", etEmail.getText().toString());
                            editor.putString("password", etPassword.getText().toString());
                            editor.commit();
                            Toast.makeText(getActivity(), "Login Sucessfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), DashboardActivity.class);
                            getContext().startActivity(intent);

                        } else {
                            Toast.makeText(getActivity(), "Email or password donot match" + email + pwd, Toast.LENGTH_SHORT).show();
                        }


                    }
                })

                .setNeutralButton("Register here", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), RegisterActivity.class);
                        getContext().startActivity(intent);

                    }
                });


        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);

        return builder.create();

    }
}
