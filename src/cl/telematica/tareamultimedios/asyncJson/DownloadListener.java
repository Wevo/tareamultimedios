package cl.telematica.tareamultimedios.asyncJson;

public interface DownloadListener {
	
	public void onRequestStart();
	
	public void onRequestComplete(String data);
	
	public void onError(String error, int code);

}
