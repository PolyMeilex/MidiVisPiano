package pac;

import processing.core.*;

class Particle {

  Start p;

  PVector position;

  PVector velocity;
  float lifespan = 255;
  
  PShape part;
  float partSize;
  
  PVector gravity = new PVector(0f,0f);

  int Color = 360;

  Particle(Start p,float x,float y,int Color) {
    this.p = p;
    this.Color = Color;
    PImage sprite = p.Psprite;
    partSize = p.random(1,20);
    
    rebirth(x,y);
    lifespan = 50;
  }

  PShape getShape() {
    return part;
  }
  
  void rebirth(float x, float y) {
    float a = p.random(p.TWO_PI);
    float speed = p.random(0.5f,4f);
    velocity = new PVector(p.cos(a)/2, -a/2);
    velocity.mult(speed);
    // part.resetMatrix();
    // part.translate(x, y); 
    position = new PVector(x,y);
  }
  
  boolean isDead() {
    if (lifespan < 0) {
     return true;
    } else {
     return false;
    } 
  }
  
  public void display() {
    p.fill(p.color(p.hue(Color),360,360,p.map(lifespan,0,50,0,360) ));
    // p.tint(p.color(p.hue(Color),360,360,p.map(lifespan,0,100,0,360) ));
    // p.image(p.Psprite,position.x, position.y, partSize, partSize);
    p.noStroke();
    p.ellipse(position.x, position.y, partSize, partSize);
  }


  public void update() {
    lifespan = lifespan - 1;
    velocity.add(gravity);
    
    // part.setTint(p.color(p.hue(Color),360,360,360));
    // part.translate(velocity.x, velocity.y);
    position.add(velocity);
  }
}