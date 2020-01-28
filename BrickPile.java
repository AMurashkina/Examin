//package bricks;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

public class BrickPile {
    private PlayField _pf;
    private PuckSupply _ps;
    private ArrayList _bricks;
    private final int _rows = 4;
    private final int _cols = 10;

    public BrickPile(PlayField pf, PuckSupply ps, Image img,Image img2) {
        _pf = pf;
        _ps = ps;
        _bricks = new ArrayList();
        int startx = 80;
        int x = startx, y = 10;

        for (int r = 0; r < _rows; r++) {
            for (int c = 0; c < _cols; c++) {
                Rectangle pos = new Rectangle(x, y, img.getWidth(null), img.getHeight(null));

                if (((r+1) * (c+1)) % (_rows * _cols * 0.2) == 0 ) {
                    pf.addSprite(new BonusBrick(_pf, _ps, this, img2, pos));

                    _bricks.add(new BonusBrick(_pf, _ps, this, img2, pos));
                }
                else {
                    pf.addSprite(new Brick(_pf, this, img, pos));

                    _bricks.add(new Brick(_pf,  this, img, pos));
                }
                x += img.getWidth(null);
            }

            y += img.getHeight(null) + 2;
            x = startx;
        }
    }

    public int unbrokenCount() {

        int result = 0;

        for (int i = 0; i < _bricks.size(); i++) {
            if ( !((Brick) _bricks.get(i)).isDead() )
                result++;
        }

        return result;
    }

    public int brokenCount() {
        return _bricks.size() - unbrokenCount();
    }
}
