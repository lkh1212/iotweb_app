package com.example.iotweb_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.app.Activity

class CctvActivity : AppCompatActivity() {
    private var mWebView // 웹뷰 선언
            : WebView? = null
    private var mWebSettings //웹뷰세팅
            : WebSettings? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cctv)
        mWebView = findViewById<View>(R.id.webView) as WebView
        mWebView!!.webViewClient = WebViewClient() // 클릭시 새창 안뜨게
        mWebSettings = mWebView!!.settings //세부 세팅 등록
        mWebSettings!!.javaScriptEnabled = true // 웹페이지 자바스클비트 허용 여부
        mWebSettings!!.setSupportMultipleWindows(false) // 새창 띄우기 허용 여부
        mWebSettings!!.javaScriptCanOpenWindowsAutomatically = false // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
        mWebSettings!!.loadWithOverviewMode = true // 메타태그 허용 여부
        mWebSettings!!.useWideViewPort = true // 화면 사이즈 맞추기 허용 여부
        mWebSettings!!.setSupportZoom(false) // 화면 줌 허용 여부
        mWebSettings!!.builtInZoomControls = false // 화면 확대 축소 허용 여부
        mWebSettings!!.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN // 컨텐츠 사이즈 맞추기
        mWebSettings!!.cacheMode = WebSettings.LOAD_NO_CACHE // 브라우저 캐시 허용 여부
        mWebSettings!!.domStorageEnabled = true // 로컬저장소 허용 여부
        mWebView!!.loadUrl("http://:8000/cctv") // 웹뷰에 표시할 장고 주소, 웹뷰 시작
        mWebView!!.loadUrl("http://192.168.219.102:8000/cctv") // 웹뷰에 표시할 장고 주소, 웹뷰 시작
    }
}