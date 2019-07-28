package base.OOTest;

public class Direction {
    private char ch;

    public Direction(char ch){
        this.ch = ch;
    }

    public void moveLeft(){
        switch (ch){
            case 'N':
                this.ch = 'W';
                break;
            case  'W':
                this.ch = 'S';
                break;
            case 'S':
                this.ch = 'E';
                break;
            case  'E':
                this.ch = 'N';
                break;

        }

    }

    public void moveRight(){
        switch (ch){
            case 'N':
                this.ch = 'E';
                break;
            case  'E':
                this.ch = 'S';
                break;
            case 'S':
                this.ch = 'W';
                break;
            case  'W':
                this.ch = 'N';
                break;

        }

    }

    public void moveForword(Position position){
        switch (ch){
            case 'N':
                position.moveN();
                break;
            case  'E':
                position.moveE();
                break;
            case 'S':
                position.moveS();
                break;
            case  'W':
                position.moveW();
                break;
        }
    }
}
