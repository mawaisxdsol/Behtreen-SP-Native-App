package com.behtreen.spapp.controller.utilities;

public class URLList {


//	String domain="http://192.168.0.102:8080/";
	String[] list={
			"include_sp_user.php",
			"include_sp_status.php",
			"include_sp_jobs.php",
			"include_device.php",
			"include_notifications.php",
			"include_page_data.php",
			"include_balance.php",

			
	};
	
	public String getUrl(int index){
		return list[index];
	}
}
