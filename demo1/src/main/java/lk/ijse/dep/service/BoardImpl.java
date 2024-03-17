package lk.ijse.dep.service;

public class BoardImpl implements Board{
    private final Piece[][] pieces=new Piece[6][5]; // 2D array

    private final BoardUI boardUI; // Interface type

    public BoardImpl(BoardUI boardUI) { // Constructor

        System.out.println("set Emty");

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                this.pieces[i][j]=Piece.EMPTY;
            }
        }
        this.boardUI = boardUI;

    }

    @Override
    public BoardUI getBoardUI() {

        return this.boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {

        for (int i = 0; i <5; ++i) {
            if (this.pieces[col][i]==Piece.EMPTY){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {

        return this.findNextAvailableSpot(col) != -1;
    }

    @Override
    public boolean existsLegalMove() {
        for (int i = 0; i <6; ++i) {
            if (this.isLegalMove(i)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {

        this.pieces[col][this.findNextAvailableSpot(col)]=move;

    }

    @Override
    public Winner findWinner() {
        
        int humanCount;
        int aiCount;

        for (int i = 0; i < 6; i++) {

            humanCount=0;
            aiCount=0;

            for (int j = 0; j < 5; j++) {

                if (pieces[i][j] == Piece.BLUE) {
                    humanCount++;
                    aiCount=0;
                } else if (pieces[i][j] == Piece.GREEN) {
                    aiCount++;
                    humanCount=0;
                }else {
                    humanCount=0;
                    aiCount=0;
                }

                if (humanCount==4){
                    return new Winner(Piece.BLUE,i,j-3,i,j);
                }
                if (aiCount == 4) {
                    return new Winner(Piece.GREEN,i,j-3,i,j);

                }
            }
        }

        for (int i = 0; i < 5; i++) {
            humanCount=0;
            aiCount=0;

            for (int j = 0; j < 6; j++) {

                if (pieces[j][i]==Piece.BLUE) {
                    humanCount++;
                    aiCount=0;
                }
                else if (pieces[j][i] ==Piece.GREEN) {
                    aiCount++;
                    humanCount=0;
                }else {
                    humanCount=0;
                    aiCount=0;
                }

                if (humanCount ==4){
                    return new Winner(Piece.BLUE,j-3,i,j,i);
                }
                if (aiCount == 4) {
                    return new Winner(Piece.GREEN,j-3,i,j,i);
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }
}