package com.amazonaws.lambda.demo;


import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
//import HTMLreader;
import com.google.gson.Gson;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

public class LambdaFunctionHandler implements RequestHandler<Object, String> {


    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);

   //   ArrayList<String>
    ArrayList <String> array=HTMLreader.agisci(inizio, fine);
    String a = array.toString();
    System.out.println(a);
    String jsonString = "errore";
	try {
		jsonString = new JSONObject()
									.put("statusCode", 200)
									.put("body", a)
									.toString();
	} catch (JSONException e) { 
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("entro in secondo tentativo");
		try {															//riprova
		    ArrayList <String> array2=HTMLreader.agisci(inizio2, fine);
		    String a2 = array.toString();
			jsonString = new JSONObject()
										.put("statusCode", 200)
										.put("body", a2)
										.toString();
		} catch (JSONException ex) {
		System.out.println("erroe nela formazione di JsonString");
		return "L'errore sarà riportato agli sviluppatori.\nPerdonaci "+" "+HTMLreader.findLink() ;
		
	}
	}
   //   String json = new Gson().toJson(a);       
    //  System.out.print(json);
        // TODO: implement your handler
        return jsonString;
    }
    
String inizio=    "<h3><b><em>Questa " +
    "è </em>Hello, World!, <em>la nostra rassegna mattiniera di attualità, cultura e internet." +
    "</em></b> Tutte le mattine, un pugno di link da leggere, vedere e ascoltare.</h3>";

String inizio2 ="<h3><b><em>Questa è </em>Hello, World!";
String fine = "Se ti piacciono Hello, World e the Submarine, ricorda di recensire la pagina su Facebook. A domani!";
}
