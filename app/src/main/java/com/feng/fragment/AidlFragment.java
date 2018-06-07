package com.feng.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.feng.aidl.IMyAidl;
import com.feng.aidl.IMyAidlInterface;
import com.feng.aidl.IntentAidlService;
import com.feng.aidl.MyAidlService;
import com.feng.aidl.Person;
import com.feng.mvp.BaseFragment;
import com.feng.test.R;

import java.util.List;
import java.util.Random;


public class AidlFragment extends BaseFragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.aidl_fragment, container, false);
        view.findViewById(R.id.button).setOnClickListener(this);
        view.findViewById(R.id.button6).setOnClickListener(this);

        getActivity().bindService(new Intent(getContext().getApplicationContext(), MyAidlService.class), mConnection, Context.BIND_AUTO_CREATE);
        getActivity().bindService(new Intent(getContext().getApplicationContext(), IntentAidlService.class), mConnection2, Context.BIND_AUTO_CREATE);

        return view;
    }

    HandlerThread mHandlerThread;
    Handler mHandler = new Handler();

    @Override
    public void onClick(View v) {

        mHandlerThread = new HandlerThread("PlayerThread");
        Log.d("HandlerThread", "" + mHandlerThread.isInterrupted());
        mHandlerThread.start();
        Log.d("HandlerThread", "" + mHandlerThread.isInterrupted());
        mHandler = new Handler(mHandlerThread.getLooper());
        Log.d("HandlerThread", "" + mHandlerThread.isInterrupted());
        mHandler.removeMessages(5555);
        mHandlerThread.quit();
        Log.d("HandlerThread", "" + mHandlerThread.isInterrupted());

        if (v.getId() == R.id.button) {
            try {
                Intent intent = new Intent();
                intent.putExtra("vid", 1232131231);
                Log.d("IMyAidlInterface", "IMyAidlInterface:" + intent.hashCode());
                mIMyAidlInterface.nav(intent);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (v.getId() == R.id.button6) {
            Random random = new Random();
            Person person = new Person("shixin" + random.nextInt(10));

            try {
                mAidl.addPerson(person);
                List<Person> personList = mAidl.getPersonList();
                getActivity().setTitle(personList.toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }


    }

    private IMyAidl mAidl;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //连接后拿到 Binder，转换成 AIDL，在不同进程会返回个代理
            mAidl = IMyAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mAidl = null;
        }
    };

    private IMyAidlInterface mIMyAidlInterface;

    private ServiceConnection mConnection2 = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIMyAidlInterface = null;
        }
    };
}
