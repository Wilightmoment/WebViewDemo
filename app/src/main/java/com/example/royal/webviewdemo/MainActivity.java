package com.example.royal.webviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView)findViewById(R.id.webView);
        //讓JavaScript語法可以在WebView元件上執行
        webView.getSettings().setJavaScriptEnabled(true);
        //使用者點選WebView 上任何連結都會自動開啟Android 內建的瀏覽器來呈現內容，而非顯示在WebView上。
        //若想使用自行設計的WebView來處理URL請求，必須自訂類別繼承WebViewClient類別，並改寫shouldOverrideUrlLoading()，且回傳false
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });
        //WebView一開始欲載入的網頁來源
        webView.loadUrl("http://www.google.com");
    }

    @Override
    //預設按返回鍵 會返回前一個Android頁面(Activity)，可以改寫onKeyDown()，判斷是否按下返回鍵(KEYCODE_BACK)以及是否可以回到上個網頁(canGoBack())來決定是否返回前一個網頁。
    //回傳true代表事件到此為止，不會再向後延續。
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

}
