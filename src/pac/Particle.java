package pac;

import processing.core.*;

class Particle {

  Start p;

  PVector position;

  PVector velocity;
  float lifespan = 255;
  
  PShape part;
  float partSize;
  
  PVector gravity = new PVector(0f,-0.1f);

  int Color = 360;

  Particle(Start p,float x,float y,int Color) {
    this.p = p;
    this.Color = Color;
    PImage sprite = p.Psprite;
    partSize = p.random(10,50);
    // part = p.createShape();
    // part.beginShape(p.QUAD);
    // part.noStroke();
    // part.texture(sprite);
    // part.normal(0, 0, 1);
    // part.vertex(-partSize/2, -partSize/2, 0, 0);
    // part.vertex(+partSize/2, -partSize/2, sprite.width, 0);
    // part.vertex(+partSize/2, +partSize/2, sprite.width, sprite.height);
    // part.vertex(-partSize/2, +partSize/2, 0, sprite.height);
    // part.endShape();
    
    rebirth(x,y);
    lifespan = 50;
  }

  PShape getShape() {
    return part;
  }
  
  void rebirth(float x, float y) {
    float a = p.random(p.TWO_PI);
    float speed = p.random(0.5f,4f);
    velocity = new PVector(p.cos(a)/2, p.sin(a));
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