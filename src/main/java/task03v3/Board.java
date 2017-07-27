package task03v3;



public class Board {
    public static final int FIELDSAMOUNT = 100;
    public static final int SIDELENGTH = 10;
    public static final char[] COORDINATEX = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    public static final int[] COORDINATEY = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    public static final char[] VIEW = {'.', '*', '-', '?', 'x'};
    public enum status {EMPTY, SHOT, BOUND, DAMAGED, KILLED}

    /* THE BOARD */
    static Field[][] gameBoard = new Field[SIDELENGTH][SIDELENGTH];

    public void generateBoard(){
        for (int i = 0; i < Board.SIDELENGTH ; i++) {
            for (int j = 0; j <Board.SIDELENGTH ; j++) {
                gameBoard[i][j] = new Field(COORDINATEX[i], j);
            }
        }
    }

    public void displayBoard(){
        int newString = 1;
        System.out.print("   A B C D E F G H I J\n");
        for (int j = 0; j <Board.SIDELENGTH ; j++) {
            if (j!=9){
                System.out.print((j+1) + " ");
            } else{
                System.out.print((j+1) + "");
            }


            for (int i = 0; i < Board.SIDELENGTH ; i++) {
                System.out.print( " " + gameBoard[i][j].getImage() );
                if ( newString++ % 10 == 0){
                    System.out.print("\n");
                }
            }
        }
    }

    public static int defineIndexOfCoordinateX(char userX){
        int indexOfCoordinateX = -1;
        switch (userX){
            case 'A':
                indexOfCoordinateX = 0;
                break;
            case 'B':
                indexOfCoordinateX = 1;
                break;
            case 'C':
                indexOfCoordinateX = 2;
                break;
            case 'D':
                indexOfCoordinateX = 3;
                break;
            case 'E':
                indexOfCoordinateX = 4;
                break;
            case 'F':
                indexOfCoordinateX = 5;
                break;
            case 'G':
                indexOfCoordinateX = 6;
                break;
            case 'H':
                indexOfCoordinateX = 7;
                break;
            case 'I':
                indexOfCoordinateX = 8;
                break;
            case 'J':
                indexOfCoordinateX = 9;
                break;
            default:
                System.err.println("VITAL ERROR! Incorrect coordinate X input!");
                break;
        }
        return indexOfCoordinateX;
    }

    public static int defineIndexOfCoordinateY(int userY){
        int indexOfCoordinateY = -1;
        switch (userY){
            case 1:
                indexOfCoordinateY = 0;
                break;
            case 2:
                indexOfCoordinateY = 1;
                break;
            case 3:
                indexOfCoordinateY = 2;
                break;
            case 4:
                indexOfCoordinateY = 3;
                break;
            case 5:
                indexOfCoordinateY = 4;
                break;
            case 6:
                indexOfCoordinateY = 5;
                break;
            case 7:
                indexOfCoordinateY = 6;
                break;
            case 8:
                indexOfCoordinateY = 7;
                break;
            case 9:
                indexOfCoordinateY = 8;
                break;
            case 10:
                indexOfCoordinateY = 9;
                break;
            /*default:
                System.err.println("VITAL ERROR! Incorrect coordinate X input!");
                break;*/
        }
        return indexOfCoordinateY;
    }

}
