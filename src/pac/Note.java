package pac;

import processing.core.*;

public class Note {
  Start parent;

  PImage DisplayImage;
  Boolean black = false;

  int Color = 91;

  float x = 0;
  float y = -10;

  float h = 3;

  float offset = 0;

  Key key;

  Boolean NoLongerActive = false;

  long last_time = System.nanoTime();

  Note(Start p, Key key, int Color) {
    parent = p;
    this.key = key;
    this.x = key.x;
    this.black = key.Black;
    this.Color = Color;
  }

  void draw() {
    // parent.translate(0,0,0.0001f*this.key.index);
    if (!this.black) {
      parent.fill(this.Color);
      parent.rect(this.x, this.y, 36, this.h, 7);
    } else {
      parent.fill(this.Color);
      parent.rect(this.x - 15, this.y, 24, this.h, 7);
    }

  }

  void update(int delta_time,float speed) {

    this.y -= speed * delta_time;

    if (this.y < -parent.height - this.h) {
      parent.Notes.remove(this);
    }

    if (!NoLongerActive) {

      if (this.key.Active) {
        this.h += speed * delta_time;
      } else
        NoLongerActive = true;

    }

  }

}
