package com.sandesh.hamrobazar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sandesh.hamrobazar.Api.UsersAPI;
import com.sandesh.hamrobazar.Model.User;
import com.sandesh.hamrobazar.ServerResponse.ImageResponse;
import com.sandesh.hamrobazar.ServerResponse.RegisterResponse;
import com.sandesh.hamrobazar.StrictModeClass.StrictModeClass;
import com.sandesh.hamrobazar.URL.Url;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText etEmail, etFname, etPassword, etCPassword, etPhone, etMphone, etAddress1, etAddress2;
    private Spinner spAddress3;
    private CheckBox chSubscribe, chPhone, chAgree;
    private Button btnRegister;
    private CircleImageView imgProfile;
    String imagePath;
    private String imageName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.etEmail);
        etFname = findViewById(R.id.etFname);
        etPassword = findViewById(R.id.etPassword);
        etCPassword = findViewById(R.id.etCPassword);
        etPhone = findViewById(R.id.etPhone);
        etMphone = findViewById(R.id.etMphone);
        etAddress1 = findViewById(R.id.etAddress1);
        etAddress2 = findViewById(R.id.etAddress2);
        spAddress3 = findViewById(R.id.spAddress3);
        chSubscribe = findViewById(R.id.chSubscribe);
        chPhone = findViewById(R.id.chPhone);
        chAgree = findViewById(R.id.chAgree);
        btnRegister = findViewById(R.id.btnRegister);
        imgProfile = findViewById(R.id.imgProfile);

        String address3[] = {"Select address", "Kathmandu", "Lalitpur", "Bhaktapur"};
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, address3);
        spAddress3.setAdapter(adapter);
        //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPassword.getText().toString().equals(etCPassword.getText().toString())) {
                    if (validate()) {
                        saveImageOnly();
                        register();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Password Donot match", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    return;
                }
            }
        });


    }

    private boolean validate() {
        boolean status = true;

        if (imgProfile.getDrawable() == null) {
            Toast.makeText(this, "Please select image", Toast.LENGTH_SHORT).show();
            imgProfile.requestFocus();
            status = false;
        }

        if (TextUtils.isEmpty(etEmail.getText())) {
            etEmail.setError("Enter email address");
            etEmail.requestFocus();
            status = false;
        }
        if (TextUtils.isEmpty(etFname.getText())) {
            etFname.setError("Enter full name");
            etFname.requestFocus();
            status = false;
        }
        if (TextUtils.isEmpty(etEmail.getText())) {
            etEmail.setError("Enter email address");
            etEmail.requestFocus();
            status = false;
        }
        if (TextUtils.isEmpty(etPassword.getText())) {
            etPassword.setError("Enter password");
            etPassword.requestFocus();
            status = false;
        }
        if (TextUtils.isEmpty(etPhone.getText())) {
            etPhone.setError("Enter phone number");
            etPhone.requestFocus();
            status = false;
        }
        if (TextUtils.isEmpty(etEmail.getText())) {
            etEmail.setError("Enter email address");
            etEmail.requestFocus();
            status = false;
        }
        if (TextUtils.isEmpty(etMphone.getText())) {
            etMphone.setError("Enter mobile phone number");
            etMphone.requestFocus();
            status = false;
        }
        if (etMphone.toString().length() < 10) {
            etMphone.setError("Minimum 10 character");
            etMphone.requestFocus();
            status = false;
        }

        if (TextUtils.isEmpty(etAddress1.getText())) {
            etAddress1.setError("Enter street name");
            etAddress1.requestFocus();
            status = false;
        }
        if (TextUtils.isEmpty(etAddress2.getText())) {
            etAddress2.setError("Enter area location");
            etAddress2.requestFocus();
            status = false;
        }
        if (spAddress3.getSelectedItem() == "Select address") {
            Toast.makeText(this, "Select address", Toast.LENGTH_SHORT).show();
            status = false;
        }
        if (!chAgree.isChecked()) {
            chAgree.setError("Please agree terms of use");
            chAgree.requestFocus();
            status = false;

        }
        if (etPassword.toString().length()<6)
        {
            etPassword.setError("Minimum 6 character");
            etPassword.requestFocus();
            status=false;
        }

        return status;
    }


    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please Select Image", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imgProfile.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                file.getName(), requestBody);
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<ImageResponse> responseBodyCall = usersAPI.uploadImage(body);

        StrictModeClass.StrictMode();

        try {
            Response<ImageResponse> imageResponse = responseBodyCall.execute();
            imageName = imageResponse.body().getFilename();
            Toast.makeText(this, "Image Inserted", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error inserting image " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    private void register() {


        String fname = etFname.getText().toString();
        String email = etEmail.getText().toString();
        String address1 = etAddress1.getText().toString();
        String address2 = etAddress2.getText().toString();
        String phone = etPhone.getText().toString();
        String mPhone = etMphone.getText().toString();
        String address = spAddress3.getSelectedItem().toString();
        String password = etPassword.getText().toString();

        User users = new User(fname, email, address1, address2, address, phone, mPhone, password, imageName);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<RegisterResponse> registerCall = usersAPI.registerUser(users);

        registerCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(RegisterActivity.this, "Register sucessfully", Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                Toast.makeText(RegisterActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    //for back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Leave New Registration");
        builder.setMessage("Do You Want to Leave New Account Registration. Any changes will not be Save." + "\n"
                + "\n" + "Are you Sure?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                openDialog();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void openDialog() {
        LoginActivity loginDialog = new LoginActivity();
        loginDialog.show(getSupportFragmentManager(), "login dialog");
    }

}
