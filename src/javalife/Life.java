package javalife;

import java.util.Random;

public class Life {
    private  boolean[][] lifePole;
    private int years;

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    private boolean isEnd;

    public boolean[][] getLifePole() {
        return lifePole;
    }

    private void setLifePole(boolean[][] lifePole) {
        this.lifePole = lifePole;
    }

    private Random random;

    public Life(int length) {
        this.lifePole = new boolean[length][length];
        this.random = new Random();
        this.years = 0;
        isEnd = false;
        for(int x =0; x<lifePole.length; x++) {
            for(int y=0;y<lifePole[x].length;y++) {
                lifePole[x][y]=random.nextBoolean();
            }
        }
    }

    public boolean[][] getNextGeneration() {
        boolean[][] oldPole = this.getLifePole();
        years++;
        int length = oldPole.length;
        boolean[][] newGenPole = new boolean[length][length];

        for(int x=0;x<length;x++) {
            for(int y=0;y<length;y++) {
                newGenPole[x][y] = this.getNexGenCell(oldPole[x][y],x,y);
            }
        }
        this.setLifePole(newGenPole);
        if(this.years % 10 == 0){
            checkIsEnd(oldPole);
        }
        return newGenPole;
    }


    private boolean getNexGenCell(boolean cell,int x, int y) {
        int neighborsCount = getNeighborsCount(x,y);
        return (!cell && neighborsCount==3) || cell && (neighborsCount==2 || neighborsCount==3);
    }

    //получить количество соседей
    private byte getNeighborsCount(int x, int y){
        boolean[][] pole = this.getLifePole();
        int length = pole.length;
        int steps[] = {-1, 0, 1};
        byte count = 0;

        for(int stepX: steps){
            for (int stepY: steps){
                if(stepX==0 && stepY==0){
                    continue;
                }
                if((0<=x+stepX&&x+stepX<length) && (0<=y+stepY&&y+stepY<length) && pole[x+stepX][y+stepY]){
                    count++;
                }
            }
        }
        return count;
    }

    private void checkIsEnd(boolean[][]old){
        boolean isSame = true;
        for (int i=0; i<old.length; i++){
            for (int j=0; j<old.length; j++){
                isSame = isSame && old[i][j] == this.getLifePole()[i][j];
            }
        }
        this.setEnd(isSame);
    }
}
