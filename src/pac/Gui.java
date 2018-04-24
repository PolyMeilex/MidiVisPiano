package pac;
import processing.core.*;
import controlP5.*;

public class Gui{



    private ControlP5 control;
    
    
    public Gui(PApplet p) {
    
          control = new ControlP5(p);
    

      control.addToggle("ParticleToggle").setPosition(10, 40)
            .setSize(50, 20).setValue(true).setMode(ControlP5.SWITCH);

      control.addSlider("ParticleNumber")
      .setPosition(100,20)
      .setRange(1,100)
      ;
    
    }
    

} 