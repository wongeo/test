package com.feng.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.feng.view.NestedScrollWebView;

/**
 * Created by feng on 2017/6/29.
 */

public class WebViewFragment extends Fragment {
    private WebView mWebView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mWebView = new NestedScrollWebView(getActivity());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setSupportMultipleWindows(true);
        mWebView.loadUrl("http://prepare.test.ireader.com:12311/zybook/u/p/book.php?key=4B4&zyeid=c0e3b69e8135429c959113ca22adc5d0&usr=i1099825580&rgt=7&p1=WHy5cVxFluYDAA3WBi8Kgi%2Fx&pc=10&p2=107130&p3=16000103&p4=501603&p5=19&p7=IGBGJFADABDFFGF&p9=2&p12=&p16=BTV-DL09&p21=10203&p22=7.0&p25=50010300&uios=11");
        return mWebView;
    }
}
