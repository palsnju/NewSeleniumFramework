package com.test.genericlib;

public enum GetData {
	
	//generics name and property 
	CHROME("chrome"),
	FIREFOX("firefox"),
	INTERNETEXPLORER("internetexplorer"),
	EDGE("edge"),
	
	
	//dataName & property 
	FACEBOOK("https://www.facebook.com/"),
	TestDataRepo("DataRepository"),
	
	
	//Messages
	BROWSER_INITIALIZATION_MESSAGE("Driver configured for : "),
	INVALID_BROWSER("No such browser configured in the property"),
	PROP_READ("Poperty fetched from the property file "),
	PROP_LOAD_SYS("Property loaded to the system property "),
	BROWSER_SESSION_QUIT("Closing the browser instance session : Opartaion quite"),
	BROWSER_CLOSE("Closing the current tab session"),
	OPEN_BROWSER("Opening the browser session"),
	MOVE_PAGE("Moving to the another page"),
	JDBC_CONN_OPEN("Opening the JDBC connection"),
	JDBC_CONN_CLOSE("Closing the JDBC connection");
	
	
	
	private String name;

	GetData(String name) {
		this.name = name;
	}

	public String data() {
		return name;
	}
	
	
	

}
