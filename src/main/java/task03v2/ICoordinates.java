package task03v2;

/**
 * interface ICoordinates реализует общие для всех полей координаты.
 * <p>
 * Meaning of the field status:
 *   Type         View    Description/Number
 *   primary
 *                 .      empty field = 0
 *                 .      field over deck of the ship (in stealth mode) = 1
 *                 .      deck of the ship (in stealth mode) = 2
 *   floating
 *                 *      damaged field over deck of the ship  (in stealth mode, till the killing of the ship) = 3
 *                 ?      damaged deck of the ship (other decks alive still remains) = 4
 *   final
 *                 *      empty field shot = 5
 *                 '      field over the deck (appears after the ship is killed) = 6
 *                 x      deck of the killed ship = 7
 */
public interface ICoordinates {
    Character[] X = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    Integer[] Y = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    String[] TYPE = {".", ".", ".", "*", "?", "*", "-", "x"};
}
