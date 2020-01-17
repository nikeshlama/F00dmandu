package com.nikesh.f00dmandu.bll;

import com.nikesh.f00dmandu.api.UserApi;
import com.nikesh.f00dmandu.model.username;
import com.nikesh.f00dmandu.serverresponse.SignUpResponse;
import com.nikesh.f00dmandu.url.Url;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBll {

    boolean isSuccess = false;

    public boolean checkUser(String username, String password) {

       com.nikesh.f00dmandu.model.username Username= new username(username,password);
       UserApi userapi= Url.getInstance().create(UserApi.class);
       Call<SignUpResponse> usersCall = userapi.checklogin(Username);

        try {
            Response<SignUpResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {

                Url.token += loginResponse.body().getToken();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
