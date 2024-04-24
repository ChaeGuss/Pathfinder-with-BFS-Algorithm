/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package w1998779;

/**
 *
 * @author DELL
 */
import java.util.LinkedList;
import java.util.ArrayList;

public class Algorithm {

    private final ArrayList<Square> positions = new ArrayList<>(); 

    public void findPath(String[][] iceGrid, int startX, int startY, int finishX, int finishY) {  

        Square position = new Square(startX, startY);
        LinkedList<Square> node = new LinkedList<>();
        Square[][] gridLengths = new Square[iceGrid.length][iceGrid[0].length];

        node.addLast(position);
        gridLengths[startY][startX] = position;

        boolean checker = true;

        while (!node.isEmpty() && checker) {

            Square currPos = node.pollFirst();

            for (Direction dir : Direction.values()) {

                Square nextPos = movePlayer(iceGrid, gridLengths, currPos, dir, finishX, finishY); 

                if (nextPos != null) {  

                    node.addLast(nextPos); 
                    gridLengths[nextPos.getY()][nextPos.getX()] = new Square(currPos.getX(), currPos.getY());  //Updating the array indexing of the point

                    if (nextPos.getY() == finishY && nextPos.getX() == finishX) {  //Check whether the moved position is the finish position

                        Square tmp = nextPos;

                        while (tmp != position) {  
                            tmp = gridLengths[tmp.getY()][tmp.getX()]; 
                            positions.add(0, tmp); 
                        }
                        checker = false;
                    }
                }
            }
        }

        positions.add(new Square(finishX, finishY)); 
        
        int step = 1;
        
        System.out.println("Player: @");
        System.out.println("");

        for(int i = 0; i<positions.size(); i++) {

            if (i == 0) {
                System.out.println("Player '@' has started moving from " + positions.get(i));
                System.out.println("");
                System.out.println(step + ".Started At " + positions.get(i));
            } else {
                if (positions.get(i).getX() > positions.get(i - 1).getX()) {
                    System.out.println(step + ".Move right to " + positions.get(i));
                } else if (positions.get(i).getX() < positions.get(i - 1).getX()) {
                    System.out.println(step + ".Move left to " + positions.get(i));
                } else if (positions.get(i).getY() > positions.get(i - 1).getY()) {
                    System.out.println(step + ".Move down  to " + positions.get(i));
                } else if (positions.get(i).getY() < positions.get(i - 1).getY()) {
                    System.out.println(step + ".Move up to " + positions.get(i));
                }
            }
            step++;
        }
        System.out.println(step + ".Done!");  
        System.out.println("");
        System.out.println("Player '@' has reached the finish at " + positions.get(positions.size() - 1)); 
    }

    public Square movePlayer (String[][]iceGrid, Square[][]gridLengths, Square currPos, Direction dir, int end_A, int end_B) {

        int x = currPos.getX();
        int y = currPos.getY();

        int diffX;                              // X Axis
        int diffY; 

        if (null == dir) {
            diffX = 0;
        }
        else diffX = switch (dir) {
            case left -> -1;
            case right -> 1;
            default -> 0;
        };

        if (null == dir) {                      // Y Axis
            diffY = 0;
        }
        else diffY = switch (dir) {
            case up -> -1;
            case down -> 1;
            default -> 0;
        }; 
        

        
        int i = 1;
        while (x + i * diffX >= 0
                && x + i * diffX < iceGrid[0].length
                && y + i * diffY >= 0
                && y + i * diffY < iceGrid.length
                && !(iceGrid[y + i * diffY][x + i * diffX]).equals("0")) {
            i++;

            if(iceGrid[y + (i-1) * diffY][x + (i-1) * diffX].equals("F")) {
                return new Square(end_A,end_B);  
            }
        }
        i--;  

        if (gridLengths[y + i * diffY][x + i * diffX] != null) {
            return null;
        }
        return new Square(x + i * diffX, y + i * diffY);  
    }

    public enum Direction {
        left,
        right,
        up,
        down
    }
}

