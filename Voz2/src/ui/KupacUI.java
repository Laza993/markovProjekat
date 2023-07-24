package ui;

import dao.KupacDAO;

public class KupacUI {
	
	private static KupacDAO kupacDAO;
	
	public static void setKupacDAO(KupacDAO kupacDAO) {
		KupacUI.kupacDAO = kupacDAO;
	}

}
