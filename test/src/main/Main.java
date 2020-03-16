package main;

import utils.WebDriverFactory;

public class Main {
	public static void main (String[] args) {
		WebDriverFactory.getDriver().get("http://localhost:4200");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebDriverFactory.clearDriver();
	}
}
