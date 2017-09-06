package com.feng.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.feng.test.R;
import com.feng.view.NestedScrollWebView;

/**
 * Created by feng on 2017/6/29.
 */

public class WebViewFragment extends Fragment {
    private WebView mWebView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.web_fragment, container, false);
        mWebView = (WebView) view.findViewById(R.id.webView);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setSupportMultipleWindows(true);

        String url = "http://59.151.93.132:12311/zyfm/app/app.php?p1=WJSODkB0tvIDAL7%2B6QYzEKfm&p2=108631&p3=16010503&p4=501603&p5=19&p6=IJIGAADFBBBFFBBIFDFG&p7=IGCABEADCDHIHCJ&p9=0&p16=1603-A03&p21=31301&p22=6.0&zysid=b116cabb2f787f3402804a55c7252bc0&pk=ch_net&pca=Channel.Index&ca=Book.GetList&sectionId=bl-i727389780-C_2-23473&key=132Bbl-i727389780-C_2-23473&type=l";
//        String url = "http://prepare.test.ireader.com:12311/zybook/u/p/book.php?key=4B4&zyeid=c0e3b69e8135429c959113ca22adc5d0&usr=i1099825580&rgt=7&p1=WHy5cVxFluYDAA3WBi8Kgi%2Fx&pc=10&p2=107130&p3=16000103&p4=501603&p5=19&p7=IGBGJFADABDFFGF&p9=2&p12=&p16=BTV-DL09&p21=10203&p22=7.0&p25=50010300&uios=11";
//        mWebView.loadUrl("javascript:document.body.style.padding=\"8%\"; void 0");
        mWebView.loadUrl(url);
        return view;
    }
}
