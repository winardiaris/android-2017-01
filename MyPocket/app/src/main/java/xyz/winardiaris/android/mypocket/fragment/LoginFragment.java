package xyz.winardiaris.android.mypocket.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import xyz.winardiaris.android.mypocket.AfterLoginActivity;
import xyz.winardiaris.android.mypocket.R;

/**
 * Created by ars on 5/30/17.
 */

public class LoginFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fr_login,container,false);
        Button btLogin = (Button) fragmentView.findViewById(R.id.btLogin);
        final EditText etUsername = (EditText) fragmentView.findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) fragmentView.findViewById(R.id.etPassword);
        btLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String etUsername_ = etUsername.getText().toString();
                String etPassword_ = etPassword.getText().toString();
                Log.d("Username", etUsername_);
                Log.d("Password", etPassword_);

//                jika sukses lanjut
                Intent afterLoginActivity = new Intent(getContext(), AfterLoginActivity.class);
                startActivity(afterLoginActivity);
            }
        });
        return fragmentView;
    }
}
