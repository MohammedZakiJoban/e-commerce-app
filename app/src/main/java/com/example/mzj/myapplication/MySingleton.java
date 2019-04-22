package com.example.mzj.myapplication;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by MZJ on 11/6/2017.
 */

public class MySingleton {



    private static MySingleton minstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private MySingleton(Context context){
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue(){
        if (requestQueue==null)
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        return requestQueue;
    }

    public static synchronized MySingleton getInstance(Context context){
        if (minstance==null){
            minstance = new MySingleton(context);
        }
        return minstance;
    }

    public<T> void addToRequestQue(Request<T> request){
        getRequestQueue().add(request);
    }
}
