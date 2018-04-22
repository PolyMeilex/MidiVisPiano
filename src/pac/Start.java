package pac;
import java.util.ArrayList;
import processing.core.*;



public class Start extends PApplet {
public static void main(String[] args)
{
    PApplet.main("pac.Start");
}

public void settings()
{
	// size(1280,1000);
    fullScreen();
}

PImage white, black, blackA, noteImage;

ArrayList<Key> Keys   = new ArrayList<Key>();
ArrayList<Note> Notes = new ArrayList<Note>();

ArrayList<Key> WhiteKeysA = new ArrayList<Key>();
ArrayList<Key> BlackKeysA = new ArrayList<Key>();

KeyRenderer KeyRenderer;

long last_time = System.nanoTime();

public void noteTrigger(int i, int channel, Boolean state)
{
    Key KeyObj = this.Keys.get(i);

    int Color = 91;

    if (channel % 2 == 0)
        Color = 200;
    else if (channel % 3 == 0)
        Color = 91;
    else
        Color = 300;

    KeyObj.Active = state;
    KeyObj.Color  = Color;
//
//		if(!state) System.out.println("Delete");

    if (state)
    {
        Note test = new Note(this, KeyObj, Color);
        Notes.add(test);
    }
}



public void setup()
{
    background(0);
    // white     = loadImage("texture/wKey.png");
    // black     = loadImage("texture/bKey.png");
    // blackA    = loadImage("texture/bKeyA.png");
    // noteImage = loadImage("texture/note.png");

    for (int oct = 0; oct < 8; oct++)
    {
        for (int i = 0; i < 12; i++)
        {
            if (i == 1 || i == 3 || i == 6 || i == 8 || i == 10)
            {
                Keys.add(new Key(true, Keys.size()));
            }
            else
            {
                Keys.add(new Key(false, Keys.size()));
            }
        }
    }
    int xTrans = 0;
    for (int i = 0; i < Keys.size(); i++)
    {
        if (!Keys.get(i).Black)
        {
            Key obj = Keys.get(i);
            obj.x = xTrans;
            WhiteKeysA.add(obj);
            xTrans += 36;
        }
        else
        {
            Key obj = Keys.get(i);
            obj.x = xTrans;
            BlackKeysA.add(obj);
        }
    }


    KeyRenderer = new KeyRenderer(this,WhiteKeysA,BlackKeysA);


    Player MidiPlayer = new Player("1.mid", this);
    MidiPlayer.start();

    colorMode(HSB, 360);
}

public void draw()
{
    background(0);

    translate(0, height - 233);

    KeyRenderer.draw(Keys);
   

    fill(1, 200, 200);
    rect(0, -5, width, 5);

    long time       = System.nanoTime();
    int  delta_time = (int) ((time - last_time) / 1000000);
    last_time = time;

    for (int i = 0; i < Notes.size(); i++)
    {
        Note Note = Notes.get(i);
        Note.update(delta_time);
        Note.draw();
    }

    translate(0, -height + 233);
    fill(255);
    textSize(26);

    text((int) frameRate + " FPS : " + Notes.size() + " Objs", 100, 100);

    if ((int) frameRate < 30 & Notes.size() > 20)
    {
        Notes.remove(0);
    }
}
}
