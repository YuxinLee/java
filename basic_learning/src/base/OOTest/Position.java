package base.OOTest;

public class Position {
    private int i;
    private int j;

    public Position(int i, int j){
        this.i = i;
        this.j = j;
    }

    public void moveN(){
        j++;
    }
    public void moveE(){
        i++;
    }
    public void moveS(){
        j--;
    }
    public void moveW(){
        i--;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }
}
