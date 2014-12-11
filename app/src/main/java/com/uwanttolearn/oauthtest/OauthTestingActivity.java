package com.uwanttolearn.oauthtest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.uwanttolearn.easysocial.EasySocialAuthActivity;
import com.uwanttolearn.easysocial.EasySocialCredential;


public class OauthTestingActivity extends Activity {


    private int REQUEST_CODE= 0x1;

    private TextView _ResponseShowTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth_testing);
        _ResponseShowTextView = (TextView) findViewById(R.id.response_text_view);
    }


    public void onStartOauth(View v){
        Intent intent = new Intent(this, EasySocialAuthActivity.class);

        String loginUrl = "https://www.linkedin.com/uas/oauth2/authorization?" +
                        "response_type=code&" +
                        "client_id=78lwdspv8bcr46&" +
                        "state=avvsggd&" +
                        "redirect_uri=http://www.uwanttolearn.com/&" +
                        "scope=";
//        String loginUrl = "https://www.facebook.com/dialog/oauth?" +
//                "client_id=794159763977710&" +
//                "redirect_uri=https://www.uwanttolearn.com/&" +
//                "scope=email,publish_actions";
        intent.putExtra(EasySocialAuthActivity.URL, loginUrl);

        intent.putExtra(EasySocialAuthActivity.REDIRECT_URL,  "http://www.uwanttolearn.com/");

        String accessTokenUrl = "https://www.linkedin.com/uas/oauth2/accessToken?" +
                "grant_type=authorization_code&" +
                "redirect_uri=http://www.uwanttolearn.com/&" +
                "client_id=78lwdspv8bcr46&" +
                "client_secret=NKdbrMz9cXIrm9pU&" +
                "code=";
//        String accessTokenUrl = "https://graph.facebook.com/oauth/access_token?" +
//                "client_id=794159763977710&" +
//                "redirect_uri=https://www.uwanttolearn.com/&" +
//                "client_secret=f558c2bf4d6e745186a91291dd386e80&" +
//                "code=";
        intent.putExtra(EasySocialAuthActivity.ACCESS_TOKEN, accessTokenUrl);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CODE){
                    String accessToken = data.getStringExtra("data");
                    _ResponseShowTextView.setText(accessToken);
            }
        }else if(resultCode == RESULT_CANCELED){
            if(requestCode == REQUEST_CODE) {
                Toast.makeText(this, data.getIntExtra(EasySocialAuthActivity.ERROR_CODE, 0) + "", Toast.LENGTH_LONG).show();
                //These error codes are present in WebViewClient.
                //http://developer.android.com/reference/android/webkit/WebViewClient.html
            }
        }

    }
}
