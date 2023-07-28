package ui;

import java.util.Collection;

import Voz_Util.Konzola;
import dao.VagonDAO;
import model.Vagon;

public class VagonUI {

	private static VagonDAO vagonDAO;
	
	public static void setVagonDAO(VagonDAO vagonDAO) {
		VagonUI.vagonDAO = vagonDAO;
	}
	
	
	public static Vagon pronalazenje() throws Exception {
		prikazSvih();
		
		long id = Konzola.ocitajLong("Unesite ID vagona: ");
		
		Vagon vagon = vagonDAO.get(id);
		if (vagon == null) {
			Konzola.prikazi("Vagon nije pronadjen");
			
		}
		return vagon;
	}


	public static void prikazSvih() {
		
		
	}
	
	
	
	
}
