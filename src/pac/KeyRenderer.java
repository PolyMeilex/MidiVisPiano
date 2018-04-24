package pac;

import java.util.ArrayList;

public class KeyRenderer {

    Start p;
    ArrayList<Key> WhiteKeysA;
    ArrayList<Key> BlackKeysA;

    KeyRenderer(Start P, ArrayList<Key> WhiteKeysA, ArrayList<Key> BlackKeysA) {
        this.p = P;
        this.WhiteKeysA = WhiteKeysA;
        this.BlackKeysA = BlackKeysA;
    }

    public void draw(ArrayList<Key> Keys) {
        p.stroke(0);
        for (int i = 0; i < WhiteKeysA.size(); i++) {
            Key obj = WhiteKeysA.get(i);
            int Color = obj.Color;           

            if (Keys.get(obj.index).Active){
                p.fill(Color);
            }
            else
                p.fill(0, 0, 320);

            p.rect(WhiteKeysA.get(i).x, 0, 35, 233);

            
            Keys.get(obj.index).ps.update();
            Keys.get(obj.index).ps.display();

            
            // p.fill(1,360,360);
            // p.text(obj.index+12 * 2,WhiteKeysA.get(i).x,0);
        }
        for (int i = 0; i < BlackKeysA.size(); i++) {
            Key obj = BlackKeysA.get(i);
            int Color = obj.Color;
            if (Keys.get(obj.index).Active) {
                p.fill(Color);
            } else
                p.fill(0, 0, 0);
            // p.translate(0, 0,0.1f);
            p.rect(BlackKeysA.get(i).x - 15, 0, 24, 155);
        }

    }

}