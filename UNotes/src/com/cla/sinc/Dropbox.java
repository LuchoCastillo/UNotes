package com.cla.sinc;

import android.app.Activity;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;

public class Dropbox {
	final static private String APP_KEY = "c1illq8avtm6347";
	final static private String APP_SECRET = "qzp0upzhbm0pvc6";
	final static private AccessType ACCESS_TYPE = AccessType.APP_FOLDER;

	// In the class declaration section:
	private DropboxAPI<AndroidAuthSession> mDBApi;
	
	private Activity actividad;
	
	public Dropbox(Activity actividad) {
		this.actividad = actividad;
	}
	
	public void conectar() {

		// And later in some initialization function:
		AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
		AndroidAuthSession session = new AndroidAuthSession(appKeys, ACCESS_TYPE);
		
		mDBApi = new DropboxAPI<AndroidAuthSession>(session);
		
		mDBApi.getSession().startAuthentication(actividad);
		

	}

}
