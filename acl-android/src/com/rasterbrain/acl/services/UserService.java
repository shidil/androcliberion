package com.rasterbrain.acl.services;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.rasterbrain.acl.Constants;


public class UserService {
	String SOAP_ACTION = "";
	
	public String register(String username,String password,String email,String name,String phonenumber,String address){
		SOAP_ACTION = Constants.NAME_SPACE + "register";
		SoapObject object = new SoapObject(Constants.NAME_SPACE, "register");
		object.addProperty("username", username);
		object.addProperty("password", password);
		object.addProperty("email",email);
		object.addProperty("name",name);
		object.addProperty("phonenumber",phonenumber);
		object.addProperty("address",address);
		//object.addProperty("email",email);
		String rsp = upload(object);
		return rsp;
	}
	public String checkLogin(String username, String password) {
		// TODO Auto-generated method stub
		SOAP_ACTION = Constants.NAME_SPACE + "login";
		SoapObject object = new SoapObject(Constants.NAME_SPACE, "login");
		object.addProperty("username", username);
		object.addProperty("password", password);
		String rsp = upload(object);
		return rsp;
	}

	private String upload(SoapObject object) {
		// TODO Auto-generated method stub
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.setOutputSoapObject(object);
		HttpTransportSE se = new HttpTransportSE(Constants.URL);
		try {
			se.call(SOAP_ACTION, envelope);
			SoapPrimitive primitive = (SoapPrimitive) envelope.getResponse();
			return primitive.toString();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return "error";
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return "error";
		}
	}

}
