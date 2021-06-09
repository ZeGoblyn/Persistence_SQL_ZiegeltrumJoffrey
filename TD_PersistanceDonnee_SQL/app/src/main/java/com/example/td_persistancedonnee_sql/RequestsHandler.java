package com.example.td_persistancedonnee_sql;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestsHandler {
    private static RequestsHandler instance;
    private RequestQueue requestQueue;
    // private ImageLoader imageLoader; // On ne gère pas les images dans ce cas précis
    private static Context ctx;

    private RequestsHandler(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();

        /*imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });*/
    }

    public static synchronized RequestsHandler getInstance(Context context) {
        if (instance == null) {
            instance = new RequestsHandler(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    /*public ImageLoader getImageLoader() {
        return imageLoader;
    }*/
}
