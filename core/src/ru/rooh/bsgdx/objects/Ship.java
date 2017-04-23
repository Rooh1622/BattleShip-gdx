package ru.rooh.bsgdx.objects;

/**
 * Created by rooh on 4/23/17.
 */
public class Ship {
    //public int x,y,x2,y2,x3,y3,x4,y4 = -1;
    public int c, c2, c3, c4 = -1;

    public Ship(int c, int c2, int c3, int c4) {
        this.c = c;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
    }

    public Boolean checkCord(int c) {
        if (c == -1) return false;
        if (this.c == c) return true;
        if (this.c2 == c) return true;
        if (this.c3 == c) return true;
        if (this.c4 == c) return true;
        return false;
    }



    /*public Ship(int c,int c2,int c3,int c4, int x,int y,int x2,int y2,int x3,int y3,int x4,int y4){
        this.c = c;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.x = x;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.y = y;
        this.y2 = y2;
        this.y3 = y3;
        this.y4 = y4;

    }*/
}

