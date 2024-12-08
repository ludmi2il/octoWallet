package Octo.Controlador;

import Octo.Vista.gui3.*;

public class Control {
	private static Control instance;
    private vistas princ;
    
    public Control() {}
    
    public static Control getControl() {
	if (instance==null) {
	     instance = new Control();
	}
	return instance;
}
    
    public vistas getprinc(){
         if(princ == null){
             princ = new vistas();
             princ.setLocationRelativeTo(null);
       }
    return princ;      
  }
}
