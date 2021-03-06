package com.bungabear.androidstudy.webviewactivity

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.bungabear.androidstudy.R
import kotlinx.android.synthetic.main.activity_webview.*


class WebViewActivity : AppCompatActivity(){

    private val TAG = "WebView" + this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        WebView.setWebContentsDebuggingEnabled(true)
        wb_activity_webview.settings.javaScriptEnabled = true
        wb_activity_webview.addJavascriptInterface(JavascriptBridge(), "android")
        wb_activity_webview.loadUrl("file:///android_asset/javapage.html")
        wb_activity_webview.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView, url: String) {
//                super.onPageFinished(view, url)
                view.loadUrl("javascript:window.android.setMessage('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>'")
            }
        }
        et_activity_webview.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                wb_activity_webview.loadUrl("javascript:setMessage('$p0')")
            }
        })
    }


    inner class JavascriptBridge{
        private val handler = Handler()

        @JavascriptInterface
        fun setMessage(message : String){
            handler.post {
                et_activity_webview.setText(message)
                Log.d(TAG, message)
            }
        }
    }
}