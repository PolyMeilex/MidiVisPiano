package pac;
import processing.core.*;
import controlP5.*;

public class Gui{



    private ControlP5 control;
    
    private Controller<Toggle> bgColor;
    
    
    public Gui(PApplet p) {
    
          control = new ControlP5(p);
    

          bgColor = control.addToggle("blackOrWhite").setPosition(10, 40)
    
                .setSize(50, 20).setValue(true).setMode(ControlP5.SWITCH);

    
    }
    

} 