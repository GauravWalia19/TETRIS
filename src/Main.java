import java.util.*;

abstract class Main 
{
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);                        // scanner for input in tetris
        Board board = new Board(50, 40);                            // initialize the board size
        boolean flag_shape_fixed = false;                           // initial shape is now fixed
        int shape_counter = 0;                                      // for counting and changing different shapes
        
        /**
         * MAKING 2D ARRAY FOR ROTATION
         * storing the rotation of different shapes
         *      0   1   2   3
         * 0 |  
         * 1 |
         * 2 |
         * 3 |
         * 4 |
         * 5 |
         * 6 |
         **/
        // Shape[][] rotation = new Shape[7][4];
        
        // rotation = makeRotationArray(rotation);

        // building the line blocks
        Block a = new Block(2, 19);
        Block b = new Block(2, 20);
        Block c = new Block(2, 21);
        Block d = new Block(2, 22);
        Shape LINE = new Shape(a, b, c, d, 0);                      // created line shape

        board.insertShape(LINE);                                    // insert initial shape on board
        board.printBoard();                                         // print the initial board
        System.out.println();
        
        flag_shape_fixed = false;                                   // shape is fixed or not
        
        // start game
        while (true) 
        {
            board.clearboard();                                     // clear board for above
            flag_shape_fixed = board.movedown(LINE);                // default move down

            if(flag_shape_fixed)                                    // if flag shape cannot move down so making it fixed 
            {
                board.insertFixedShape(LINE);                       // insert the fixed shape on board
                board.printBoard();                                 // print the board
                
                if(shape_counter==6)                                // set shape counter to 0 when it becomes 6
                {
                    shape_counter=0;
                }
                else
                {
                    shape_counter++;
                }
                LINE = create_shape(LINE, shape_counter);           // creating new shape for second move

                flag_shape_fixed=false;                             // set shape fixed to false for next shape
            }

            System.out.println("ENTER THE OPTION");                 // displaying the options
            System.out.println("D/d -- right");                     // right option
            System.out.println("A/a -- left");                      // left option
            System.out.println("W/w -- rotate");                    // rotate option
            System.out.println("S/s -- save");                      // save option
            
            char ans = in.next().charAt(0);

            if (ans == 'D' || ans == 'd')                           // move right
            {
                if (!board.moveright(LINE)) 
                {
                    break;
                }
            } 
            else if (ans == 'A' || ans == 'a')                      // moveleft
            {
                if (!board.moveleft(LINE)) 
                {
                    break;
                }
            }
            else if(ans=='w' || ans=='W')                           // rotate shape
            {
                
            }
            else if(ans=='q' || ans=='Q')                           // EXIT GAME
            {
                System.exit(0);
            }

            // rotate shape
            board.insertShape(LINE);                                // insert shape on board
            board.printBoard();                                     // print board
            System.out.println();
        }
        in.close();
    }

    /**
    *  LINE  0
    *  # # # #
    *
    *  SQUARE  1
    *  # # 
    *  # #
    *
    *  LR SHAPE 2
    *  #
    *  #
    *  # #
    *
    *  LL SHAPE 3
    *    #
    *    # 
    *  # #
    * 
    *  T SHAPE 4
    *  # # #
    *    #
    * 
    *  ZL SHAPE 5
    *  # #
    *    # #
    *
    *  ZR SHAPE 6
    *    # #
    *  # #
    **/

    /**
     * This function will create initial shapes
     * 
     * @param sh for shape
     * @param num integer for shapeno
     * 
     * @return the build shape
     **/
    private static Shape create_shape(Shape sh,int num)
    {
        switch(num)
        {
            case 0:
                //LINE SHAPE
                sh.getarrofblock()[0].setX(2);
                sh.getarrofblock()[0].setY(19);
                sh.getarrofblock()[1].setX(2);
                sh.getarrofblock()[1].setY(20);
                sh.getarrofblock()[2].setX(2);
                sh.getarrofblock()[2].setY(21);
                sh.getarrofblock()[3].setX(2);
                sh.getarrofblock()[3].setY(22);
                break;
            case 1:
                //SQUARE SHAPE
                sh.getarrofblock()[0].setX(2);
                sh.getarrofblock()[0].setY(19);
                sh.getarrofblock()[1].setX(2);
                sh.getarrofblock()[1].setY(20);
                sh.getarrofblock()[2].setX(3);
                sh.getarrofblock()[2].setY(19);
                sh.getarrofblock()[3].setX(3);
                sh.getarrofblock()[3].setY(20);
                break;
            case 2:
                //LR SHAPE
                sh.getarrofblock()[0].setX(3);
                sh.getarrofblock()[0].setY(19);
                sh.getarrofblock()[1].setX(4);
                sh.getarrofblock()[1].setY(19);
                sh.getarrofblock()[2].setX(5);
                sh.getarrofblock()[2].setY(19);
                sh.getarrofblock()[3].setX(5);
                sh.getarrofblock()[3].setY(20);
                break;
            case 3:
                //LL SHAPE
                sh.getarrofblock()[0].setX(3);
                sh.getarrofblock()[0].setY(19);
                sh.getarrofblock()[1].setX(4);
                sh.getarrofblock()[1].setY(19);
                sh.getarrofblock()[2].setX(5);
                sh.getarrofblock()[2].setY(19);
                sh.getarrofblock()[3].setX(5);
                sh.getarrofblock()[3].setY(18);
                break;
            case 4:
                //T SHAPE
                sh.getarrofblock()[0].setX(2);
                sh.getarrofblock()[0].setY(19);
                sh.getarrofblock()[1].setX(2);
                sh.getarrofblock()[1].setY(20);
                sh.getarrofblock()[2].setX(2);
                sh.getarrofblock()[2].setY(21);
                sh.getarrofblock()[3].setX(3);
                sh.getarrofblock()[3].setY(20);
                break;
            case 5:
                //ZL SHAPE
                sh.getarrofblock()[0].setX(2);
                sh.getarrofblock()[0].setY(19);
                sh.getarrofblock()[1].setX(2);
                sh.getarrofblock()[1].setY(20);
                sh.getarrofblock()[2].setX(3);
                sh.getarrofblock()[2].setY(20);
                sh.getarrofblock()[3].setX(3);
                sh.getarrofblock()[3].setY(21);
                break;
            case 6:
                //ZR SHAPE
                sh.getarrofblock()[0].setX(2);
                sh.getarrofblock()[0].setY(19);
                sh.getarrofblock()[1].setX(2);
                sh.getarrofblock()[1].setY(20);
                sh.getarrofblock()[2].setX(3);
                sh.getarrofblock()[2].setY(18);
                sh.getarrofblock()[3].setX(3);
                sh.getarrofblock()[3].setY(19);
                break;
            default:
                System.exit(0);

        }
        return sh;
    }

    /**
     * This function will make the rotation array
     * 
     * @param rotation array for rotating the shapes
     * @return shapes array
     **/
    private static Shape[][] makeRotationArray(Shape[][] rotation)
    {
        //initial shapes of the block
        Block e = new Block(0,0);
        Block f = new Block(0,0);
        Block g = new Block(0,0);
        Block h = new Block(0,0);

        //LINE SHAPE 0
        rotation[0][0] = new Shape(e,f,g,h,0);
        rotation[0][2] = new Shape(e,f,g,h,2);
        e = new Block(-1,1);
        f = new Block(0,0);
        g = new Block(1,-1);
        h = new Block(2,-2);
        rotation[0][1] = new Shape(e,f,g,h,1);
        rotation[0][3] = new Shape(e,f,g,h,3);
        
        //SQUARE SHAPE 1
        e = new Block(0,0);
        f = new Block(0,0);
        g = new Block(0,0);
        h = new Block(0,0);
        rotation[1][0] = new Shape(e,f,g,h,0);
        rotation[1][1] = new Shape(e,f,g,h,1);
        rotation[1][2] = new Shape(e,f,g,h,2);
        rotation[1][3] = new Shape(e,f,g,h,3);

        //LR SHAPE 2
        e = new Block(0,0);
        f = new Block(0,0);
        g = new Block(0,0);
        h = new Block(0,0);
        rotation[2][0] = new Shape(e,f,g,h,0);
        e = new Block(1,2);
        f = new Block(0,0);
        g = new Block(0,0);
        h = new Block(-1,0);
        rotation[2][1] = new Shape(e,f,g,h,1);
        e = new Block(3,1);
        f = new Block(3,1);
        g = new Block(0,0);
        h = new Block(0,0);
        rotation[2][2] = new Shape(e,f,g,h,2);
        e = new Block(0,2);
        f = new Block(0,0);
        g = new Block(-1,1);
        h = new Block(-1,1);
        rotation[2][3] = new Shape(e,f,g,h,3);

        // LL SHAPE 3
        e = new Block(0,0);
        f = new Block(0,0);
        g = new Block(0,0);
        h = new Block(0,0);
        rotation[3][0] = new Shape(e,f,g,h,0);
        e = new Block(1,-1);
        f = new Block(0,0);
        g = new Block(0,0);
        h = new Block(1,1);
        rotation[3][1] = new Shape(e,f,g,h,1);
        e = new Block(0,0);
        f = new Block(0,0);
        g = new Block(0,0);
        h = new Block(-2,2);
        rotation[3][2] = new Shape(e,f,g,h,2);
        e = new Block(1,-1);
        f = new Block(0,0);
        g = new Block(-1,-2);
        h = new Block(0,1);
        rotation[3][3] = new Shape(e,f,g,h,3);

        // T SHAPE 4
        e = new Block(0, 0);
        f = new Block(0, 0);
        g = new Block(0, 0);
        h = new Block(0, 0);
        rotation[4][0] = new Shape(e,f,g,h,0);
        e = new Block(0, 0);
        f = new Block(0, 0);
        g = new Block(-1,-1);
        h = new Block(0, 0);
        rotation[4][1] = new Shape(e,f,g,h,1);
        e = new Block(0, 0);
        f = new Block(0, 0);
        g = new Block(0, 0);
        h = new Block(-2, 0);
        rotation[4][2] = new Shape(e,f,g,h,2);
        e = new Block(-1,1);
        f = new Block(0, 0);
        g = new Block(0, 0);
        h = new Block(0, 0);
        rotation[4][3] = new Shape(e,f,g,h,3);

        // ZL SHAPE 5
        e = new Block(0, 0);
        f = new Block(0, 0);
        g = new Block(0, 0);
        h = new Block(0, 0);
        rotation[5][0] = new Shape(e,f,g,h,0);
        e = new Block(0, 0);
        f = new Block(0, 0);
        g = new Block(0,-1);
        h = new Block(-2,1);
        rotation[5][1] = new Shape(e,f,g,h,1);
        e = new Block(0, 0);
        f = new Block(0, 0);
        g = new Block(0, 0);
        h = new Block(0, 0);
        rotation[5][2] = new Shape(e,f,g,h,2);
        e = new Block(0, 0);
        f = new Block(0, 0);
        g = new Block(0,-1);
        h = new Block(-2,1);
        rotation[5][3] = new Shape(e,f,g,h,3);

        // ZR SHAPE 6
        e = new Block(0, 0);
        f = new Block(0, 0);
        g = new Block(0, 0);
        h = new Block(0, 0);
        rotation[6][0] = new Shape(e,f,g,h,0);
        e = new Block(0, 0);
        f = new Block(0, 0);
        g = new Block(0, 2);
        h = new Block(2, 0);
        rotation[6][1] = new Shape(e,f,g,h,1);
        e = new Block(0, 0);
        f = new Block(0, 0);
        g = new Block(0, 0);
        h = new Block(0, 0);
        rotation[6][2] = new Shape(e,f,g,h,2);
        e = new Block(0, 0);
        f = new Block(0, 0);
        g = new Block(0, 2);
        h = new Block(2, 0);
        rotation[6][3] = new Shape(e,f,g,h,3);
        
        return rotation;
    }
}