package com.openfaas;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Handler {

    public String Handle(String functionRequest) {
	String ret = "Hello world";

//	return ret;

	try {
          OkHttpClient client = new OkHttpClient();

	  Request request = new Request.Builder()
	      .url(functionRequest)
	      .build();

	  Response response = client.newCall(request).execute();
	  ret = response.body().string();

	} catch(Exception e) {
	  e.printStackTrace();
	  return e.toString();
	}

        return ret;
    }
}
