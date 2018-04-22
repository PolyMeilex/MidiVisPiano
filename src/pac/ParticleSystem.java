package pac;

import processing.core.*;

import java.util.ArrayList;

class ParticleSystem {
    ArrayList<Particle> particles;
  
    PShape particleShape;
    Start pS;
  
    ParticleSystem(Start pS,int n) {
      this.pS = pS;
      particles = new ArrayList<Particle>();
      particleShape = pS.createShape(PShape.GROUP);
    }

    void spawnParticles(int n,float x,float y,int Color){
        if (particles.size() > 1000) {
            particleShape.removeChild(0);
            particles.remove(0);
        }
        else{
            for (int i = 0; i < n; i++) {
                Particle p = new Particle(pS,x,y,Color);
                particles.add(p);
                particleShape.addChild(p.getShape());
              }
        }
      
    }
  
    void update() {
      for (int i = 0;i<particles.size();i++) {
        Particle p = particles.get(i);
        if(p.isDead()) {
            particleShape.removeChild( i );
            particles.remove(i);
        }
        else p.update();
      }
    //   System.out.println(particleShape.getChildIndex(who));
    }
  
    void display() {
    //   System.out.println(particleShape.getChildCount());
      pS.shape(particleShape);
    }
  }