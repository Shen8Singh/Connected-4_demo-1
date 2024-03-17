package lk.ijse.dep.service;

public class AiPlayer extends Player{
    public AiPlayer(Board board) {
        super(board);
    }
    //  MCTC algorithm
    
    @Override
    public void movePiece(int col){

        do {
            col= (int) (Math.random() * 6);

        }while(!this.board.isLegalMove(col));

        this.board.updateMove(col,Piece.GREEN);
        this.board.getBoardUI().update(col,false);

        Winner winner=this.board.findWinner();

        if (winner.getWinningPiece() != Piece.EMPTY){
            this.board.getBoardUI().notifyWinner(winner);
        } else if (!this.board.existsLegalMove()) {
            this.board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
        }
    }
}
