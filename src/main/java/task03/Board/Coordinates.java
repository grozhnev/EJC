package task03.Board;

/**
 * Represents coordinates, that are implemented in class Field.
 */
public interface Coordinates {

    Character[] X = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    Integer[] Y = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    String[] TYPE = {"CLEAN", "PADDED", "DAMAGED", "MARKED", "KILLED"};
    /* display as : CLEAN='0', PADDED='*',  DAMAGED='?', MARKED='-', KILLED='x' ) */
}
