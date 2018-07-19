package pac;

import java.util.ArrayList;
import processing.core.*;
import controlP5.*;

public class Start extends PApplet {
    static String file;

    public static void main(String[] args) {
        // file = args[0];
        file = "44.mid";
        PApplet.main("pac.Start");
    }

    public void settings() {
        // size(1280, 1000);
        fullScreen();
        // size(1000,1000);
    }

    // PImage white, black, blackA, noteImage;
    public PImage Psprite;

    ArrayList<Key> Keys = new ArrayList<Key>();
    ArrayList<Note> Notes = new ArrayList<Note>();

    ArrayList<Key> WhiteKeysA = new ArrayList<Key>();
    ArrayList<Key> BlackKeysA = new ArrayList<Key>();

    KeyRenderer KeyRenderer;

    float SclPlaned = 1f;
    float Scl = 1f;
    long last_time = System.nanoTime();

    Boolean ParticlesSwitch = false;
    int ParticleNumber = 2;

    public void controlEvent(ControlEvent theEvent) {
        System.out.println(theEvent.name());
        if (theEvent.name() == "ParticleToggle")
            ParticlesSwitch = theEvent.value() == 1;
        if (theEvent.name() == "ParticleNumber")
            ParticleNumber = (int) theEvent.getValue();
    }

    public void noteTrigger(int i, int channel, Boolean state) {
        Key KeyObj = this.Keys.get(i - 12 * 2);
        int Color = color(91, 201, 307);

        if (channel % 2 == 0)
            Color = color(200, 300, 300);
        else if (channel % 3 == 0)
            Color = color(91, 300, 300);
        else
            Color = color(300, 300, 300);

        if (channel == 9) {
            Color = color(0, 0, 100);

            if (i == 35 || i == 36) {
                SclPlaned = 1.05f;
                // else bgPlaned=1f;
            } else {
                SclPlaned = 1f;
            }

        } else {
            if (ParticlesSwitch)
                KeyObj.ps.spawnParticles(this.ParticleNumber, KeyObj.x, 0, Color);
        }

        KeyObj.Active = state;
        KeyObj.Color = Color;
        //
        // if(!state) System.out.println("Delete");

        if (state) {
            Note test = new Note(this, KeyObj, Color);
            Notes.add(test);
        }
    }

    public void setup() {
        background(0);

        new Gui(this);

        Psprite = loadImage("texture/sprite.png");

        for (int oct = 0; oct < 8; oct++) {
            for (int i = 0; i < 12; i++) {
                if (i == 1 || i == 3 || i == 6 || i == 8 || i == 10) {
                    Keys.add(new Key(true, Keys.size(), new ParticleSystem(this, 10)));
                } else {
                    Keys.add(new Key(false, Keys.size(), new ParticleSystem(this, 10)));
                }
            }
        }
        int xTrans = 0;
        for (int i = 0; i < Keys.size(); i++) {
            if (!Keys.get(i).Black) {
                Key obj = Keys.get(i);
                obj.x = xTrans;
                WhiteKeysA.add(obj);
                xTrans += 36;
            } else {
                Key obj = Keys.get(i);
                obj.x = xTrans;
                BlackKeysA.add(obj);
            }
        }

        KeyRenderer = new KeyRenderer(this, WhiteKeysA, BlackKeysA);

        Player MidiPlayer = new Player(file, this);
        MidiPlayer.start();

        colorMode(HSB, 360);

    }

    public void draw() {

        Scl = lerp(Scl, SclPlaned, 0.05f);

        background(0, 0, 0);

        fill(255);
        textSize(26);
        text((int) frameRate + " FPS : " + Notes.size() + " Notes", 100, 100);

        translate(width / 2, height / 2);
        // scale(Scl);
        translate(-width / 2, -height / 2);

        // translate(0, -0*50f);

        pushMatrix();
        translate(0, height - 233);

        KeyRenderer.draw(Keys);

        // fill(1, 200, 200);
        // rect(0, -5, width, 5);

        long time = System.nanoTime();
        int delta_time = (int) ((time - last_time) / 1000000);
        last_time = time;

        for (int i = 0; i < Notes.size(); i++) {
            Note Note = Notes.get(i);
            Note.update(delta_time, 1);
            Note.draw();
        }

        popMatrix();
        // translate(0, -height + 233);

        if ((int) frameRate < 30 & Notes.size() > 20) {
            Notes.remove(0);
        }
    }
}
