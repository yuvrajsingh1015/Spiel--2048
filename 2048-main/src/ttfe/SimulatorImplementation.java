package ttfe;

import java.util.Random;

public class SimulatorImplementation implements SimulatorInterface {

    private int height;
    private int width;
    private int[][] board;
    private Random randu;
    private int numofmoves;
    private int credits;

    public SimulatorImplementation(int width, int height, Random r) {
        if (width < 2 || height < 2 || r == null) {
            throw new IllegalArgumentException("Incorrect Height and Width");
        }
        this.width = width;
        this.height = height;
        this.randu = r;
        this.board = new int[width][height];
        this.numofmoves = 0;
        this.credits = 0;
        addPiece();
        addPiece();
    }

    @Override
    public void addPiece() {
        if (!isSpaceLeft()) {
            throw new IllegalStateException("Hi HA board full!");
        }
        int x; // = rand.nextInt(width);
        int y; // = rand.nextInt(height);
        do {
            x = randu.nextInt(getBoardWidth());
            y = randu.nextInt(getBoardHeight());
        } while (board[x][y] != 0);

        int randomValue = this.randu.nextInt(101);
        if (randomValue >= 90) {
            board[x][y] = 4;
        } else {
            board[x][y] = 2;
        }
    }

    @Override
    public int getBoardHeight() {
        return height;
    }

    @Override
    public int getBoardWidth() {
        return width;
    }

    @Override
    public int getNumMoves() {
        return numofmoves;
    }

    @Override
    public int getNumPieces() {
        int counter = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] != 0) {
                    counter++;
                }
            }
        }
        return counter;
    }

    @Override
    public int getPieceAt(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Incorrect co-ordinates");
        }
        return board[x][y];
    }

    @Override
    public int getPoints() {
        return credits;
    }

    @Override
    public boolean isMovePossible() {
        return isMovePossible(MoveDirection.NORTH) ||
                isMovePossible(MoveDirection.SOUTH) ||
                isMovePossible(MoveDirection.WEST) ||
                isMovePossible(MoveDirection.EAST);
    }

    @Override
    public boolean isMovePossible(MoveDirection direction) {
        if (direction == null) {
            throw new IllegalArgumentException();
        }
        switch (direction) {
            case NORTH:
                for (int x = 0; x < width; x++) {
                    for (int y = 1; y < height; y++) {
                        if (board[x][y] != 0 && (board[x][y - 1] == 0 || board[x][y - 1] == board[x][y])) {
                            return true;
                        }
                    }
                }
                break;
            case SOUTH:
                for (int x = 0; x < width; x++) {
                    for (int y = height - 2; y >= 0; y--) {
                        if (board[x][y] != 0 && (board[x][y + 1] == 0 || board[x][y + 1] == board[x][y])) {
                            return true;
                        }
                    }
                }
                break;
            case WEST:
                for (int y = 0; y < height; y++) {
                    for (int x = 1; x < width; x++) {
                        if (board[x][y] != 0 && (board[x - 1][y] == 0 || board[x - 1][y] == board[x][y])) {
                            return true;
                        }
                    }
                }
                break;
            case EAST:
                for (int y = 0; y < height; y++) {
                    for (int x = width - 2; x >= 0; x--) {
                        if (board[x][y] != 0 && (board[x + 1][y] == 0 || board[x + 1][y] == board[x][y])) {
                            return true;
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public boolean isSpaceLeft() {
        int zerocounter = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == 0) {
                    zerocounter++;
                }
            }
        }
        if (zerocounter != 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean performMove(MoveDirection direction) {
        if (direction == null) {
            throw new IllegalArgumentException("HiHa Incorrect Direction Direction");
        }
        if (!isMovePossible(direction)) {
            return false;
        }
        boolean moved = false;
        switch (direction) {
            case NORTH:
                moved = moveUp();
                break;
            case SOUTH:
                moved = moveDown();
                break;
            case WEST:
                moved = moveLeft();
                break;
            case EAST:
                moved = moveRight();
                break;
        }
        if (moved) {
            numofmoves++;
        }

        return moved;
    }

    public boolean moveUp() {
        boolean moved = false;
        for (int x = 0; x < width; x++) {
            for (int y = 1; y < height; y++) {
                if (board[x][y] != 0) {
                    int yy = y;
                    while (yy > 0 && (board[x][yy - 1] == 0 || board[x][yy - 1] == board[x][y])) {
                        yy--;
                    }
                    if (yy != y) {
                        if (board[x][yy] == board[x][y]) {
                            board[x][yy] *= 2;
                            credits += board[x][yy];
                            board[x][y] = 0;
                            moved = true;
                        } else if (board[x][yy] == 0) {
                            board[x][yy] = board[x][y];
                            board[x][y] = 0;
                            moved = true;
                        }
                    }
                }
            }
        }

        return moved;
    }

    public boolean moveDown() {
        boolean moved = false;
        for (int x = 0; x < width; x++) {
            for (int y = height - 2; y >= 0; y--) {
                if (board[x][y] != 0) {
                    int yy = y;
                    while (yy < height - 1 && (board[x][yy + 1] == 0 || board[x][yy + 1] == board[x][y])) {
                        yy++;
                    }
                    if (yy != y) {
                        if (board[x][yy] == board[x][y]) {
                            board[x][yy] *= 2;
                            credits += board[x][yy];
                            board[x][y] = 0;
                            moved = true;
                        } else if (board[x][yy] == 0) {
                            board[x][yy] = board[x][y];
                            board[x][y] = 0;
                            moved = true;
                        }
                    }
                }
            }
        }

        return moved;
    }

    public boolean moveLeft() {
        boolean moved = false;

        for (int y = 0; y < height; y++) {
            for (int x = 1; x < width; x++) {
                if (board[x][y] != 0) {
                    int xx = x;
                    while (xx > 0 && (board[xx - 1][y] == 0 || board[xx - 1][y] == board[x][y])) {
                        xx--;
                    }
                    if (xx != x) {
                        if (board[xx][y] == board[x][y]) {
                            board[xx][y] *= 2;
                            credits += board[xx][y];
                            board[x][y] = 0;
                            moved = true;
                        } else if (board[xx][y] == 0) {
                            board[xx][y] = board[x][y];
                            board[x][y] = 0;
                            moved = true;
                        }
                    }
                }
            }
        }

        return moved;
    }

    public boolean moveRight() {
        boolean moved = false;
        for (int y = 0; y < height; y++) {
            for (int x = width - 2; x >= 0; x--) {
                if (board[x][y] != 0) {
                    int xx = x;
                    while (xx < width - 1 && (board[xx + 1][y] == 0 || board[xx + 1][y] == board[x][y])) {
                        xx++;
                    }
                    if (xx != x) {
                        if (board[xx][y] == board[x][y]) {
                            board[xx][y] *= 2;
                            credits += board[xx][y];
                            board[x][y] = 0;
                            moved = true;
                        } else if (board[xx][y] == 0) {
                            board[xx][y] = board[x][y];
                            board[x][y] = 0;
                            moved = true;
                        }
                    }
                }
            }
        }

        return moved;
    }

    @Override
    public void run(PlayerInterface player, UserInterface ui) {
        if (player == null || ui == null) {
            throw new IllegalArgumentException();
        }
        ui.updateScreen(this);
        while (isMovePossible()) {
            MoveDirection move = player.getPlayerMove(this, ui);
            boolean moved = performMove(move);
            if (moved) {
                addPiece();
                ui.updateScreen(this);
            }
        }
        ui.showGameOverScreen(this);
    }

    @Override
    public void setPieceAt(int x, int y, int piece) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Not correct Co-Ordinates");
        }
        if (piece < 0) {
            throw new IllegalArgumentException("Piece Not correct");
        }
        board[x][y] = piece;
    }
}
