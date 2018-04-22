package pac;

import java.util.ArrayList;

public class KeyRenderer{

    Start p;
    ArrayList<Key> WhiteKeysA;
    ArrayList<Key> BlackKeysA;

    KeyRenderer(Start P,ArrayList<Key> WhiteKeysA,ArrayList<Key> BlackKeysA){
        this.p = P;
        this.WhiteKeysA = WhiteKeysA;
        this.BlackKeysA = BlackKeysA;
    }

    public void draw(ArrayList<Key> Keys){

        for (int i = 0; i < WhiteKeysA.size(); i++)
        {
            Key obj   = WhiteKeysA.get(i);
            int Color = obj.Color;
    
            if (Keys.get(obj.index).Active)
                p.fill(Color, 201, 307);
            else
                p.fill(0, 0, 320);
    
            p.rect(WhiteKeysA.get(i).x, 0, 35, 233);
        }
        for (int i = 0; i < BlackKeysA.size(); i++)
        {
            Key obj   = BlackKeysA.get(i);
            int Color = obj.Color;
            if (Keys.get(obj.index).Active)
            {                                      
                p.fill(Color, 201, 307);
            }
            else
                p.fill(0, 0, 0);
    
    
            p.rect(BlackKeysA.get(i).x - 15, 0, 24, 155);
        }

    }

}