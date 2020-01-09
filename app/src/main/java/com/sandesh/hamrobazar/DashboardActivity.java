package com.sandesh.hamrobazar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sandesh.hamrobazar.Api.UsersAPI;
import com.sandesh.hamrobazar.Model.User;
import com.sandesh.hamrobazar.URL.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardActivity extends AppCompatActivity {
    private CircleImageView imageProfile;
    private RecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        imageProfile = findViewById(R.id.imgProfile);
        loadCurrentUser();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


    private void loadCurrentUser() {
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<User> userCall = usersAPI.getUserDetails(Url.token);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, final Response<User> response) {
                if (!response.isSuccessful()) {
//                    Toast.makeText(DashboardActivity.this, "Code "+response.code(), Toast.LENGTH_SHORT).show();

                    //to show login and registration when user not logged in

                    imageProfile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openDialog();
                        }
                    });

                } else {
                    String imgPath = Url.imagePath + response.body().getImage();

                    Picasso.get().load(imgPath).into(imageProfile);


                    imageProfile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(DashboardActivity.this, "Full name: " + response.body().getFullName(), Toast.LENGTH_SHORT).show();
                        }

                    });

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                Picasso.get().load(R.drawable.profile).into(imageProfile);
            }

        });

    }

    private void openDialog() {
        LoginActivity loginDialog = new LoginActivity();

        loginDialog.show(getSupportFragmentManager(), "login dialog");
    }
}
