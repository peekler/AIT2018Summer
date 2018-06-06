package hu.ait.android.tictactoe.model;

public class TicTacToeModel {

    private static TicTacToeModel ticTacToeModel = null;

    public static TicTacToeModel getInstance() {
        if (ticTacToeModel == null) {
            ticTacToeModel = new TicTacToeModel();
        }

        return ticTacToeModel;
    }

    private TicTacToeModel() {
    }

    public static final short EMPTY = 0;
    public static final short CIRCLE = 1;
    public static final short CROSS = 2;

    private short nextPlayer = CIRCLE;



    private short[][] model = {
        {EMPTY, EMPTY, EMPTY},
        {EMPTY, EMPTY, EMPTY},
        {EMPTY, EMPTY, EMPTY}
    };

    public short getFieldContent(int x, int y) {
        return model[x][y];
    }

    public void setFieldContent(int x, int y, short content) {
        model[x][y] = content;
    }

    public short getNextPlayer() {
        return nextPlayer;
    }

    public void switchPlayer() {
        //nextPlayer = (nextPlayer == CIRCLE) ? CROSS : CIRCLE;

        if (nextPlayer == CIRCLE) {
            nextPlayer = CROSS;
        } else {
            nextPlayer = CIRCLE;
        }
    }

    public void resetModel() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                model[i][j] = EMPTY;
            }
        }

        nextPlayer = CIRCLE;
    }

}
