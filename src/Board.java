import RAINBOW.*;

/**
 * Board class for making board
 **/
public class Board
{
    private int rows;                                               // rows of the board
    private int cols;                                               // cols of the board
    private char[][] arr;                                           // array for making board
    private int[] count_block;                                      // store count the number of fixed block in the line
    private int points;                                             // points of collecting during game play
    private String[] ENV;                                           // array for environment variables

    /**
     * Parameterised Constructor for making new board
     * 
     * @param int for rows
     * @param int for cols
     **/
    public Board(int rows,int cols)
    {
        this.rows=rows;
        this.cols=cols;
        arr = new char[rows][cols];                                 // create dynamic array for printing board
        count_block = new int[rows];
        points=0;
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                arr[i][j]=' ';                                      // setting space in the board array
            }
        }
        // ENV = new String[1];
    }
    
    /**
     * This function will return board array
     * 
     * @return char array
     **/
    public char[][] getARR()
    {
        return arr;
    }

    /**
     * This function will return the board rows
     * 
     * @return int for board rows
     **/
    public int getRows()
    {
        return this.rows;
    }

    /**
     * This function will return the board cols
     * 
     * @return int for board cols
     **/
    public int getCols()
    {
        return this.cols;
    }
    /**
     * This function will return the upper limit 
     * 
     * @return int the upper limit for storing in file
     **/
    public int getUpperLimit()
    {
        int limit=-1;
        for(int i=0;i<count_block.length;i++)
        {
            if(count_block[i]!=0)
            {
                limit = i;
                break;
            }
        }
        return limit;
    }

    /**
     * This function sets param array for the board
     * 
     * @param arr for char array 
     * @return void
     **/
    public void setARR(char[][] arr)
    {
        this.arr=arr;
    }
    
    /**
     * This function will print the board on the screen with colors
     * 
     * @return void
     **/
    public void printBoard()
    {
        Rain R = new Rain();                                        // making object of rain class for using colors from rainbow
        System.out.print(R.BYELLOW);
        for(int i=0;i<cols+2;i++)                                   // printing the above == line
        {
            System.out.print("=");
        }
        System.out.print(R.RESET);
        System.out.println();
        
        for(int i=0;i<rows;i++)                                     // printing the left and right | | lines
        {
            System.out.print(R.BYELLOW+"|"+R.RESET);
            for(int j=0;j<cols;j++)
            {
                System.out.print(arr[i][j]);
            }
            System.out.println(R.BYELLOW+"|"+count_block[i]+R.RESET);
        }

        System.out.print(R.BYELLOW);
        for(int i=0;i<cols+2;i++)                                   // printing the below == line
        {
            System.out.print("=");
        }
        System.out.print(R.RESET);
        System.out.println();
    }

    /**
     * This function will clear the board except '@' fixed one
     * 
     * @return void
     **/
    public void clearboard()
    {
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(arr[i][j]!='@')                                  // if fixed char don't found
                {
                    arr[i][j]=' ';                                  // printing space char for clearing
                }
            }
        }
    }

    /**
     * <ol>
     *  <li>It will check whether the coords are in board range
     *  <li>It will check whether all boundary are valid or not
     *  <li>It will also check whether the new coords are on fixed shape or not
     *  <li>If it returns true then it will ensure that the block inserted in right coords
     * 
     * @param x for x coordinate
     * @param y for y coordinate
     * 
     * @return boolean 
     **/
    public boolean checkValidCoords(int x,int y)
    {
        if(x<0 || y<0 || x>=rows || y>=cols)
        {
            return false;
        }
        switch(arr[x][y])
        {
            case '#':
            case '|':
            case '@':
            case '=':
                return false;
        }
        return true;
    }

    /**
     * This function will insert shape on board for runtime with '#'
     * 
     * @param S for getting Shape for printing
     * 
     * @return boolean
     **/
    public boolean insertShape(Shape S)
    {
        for(int i=0;i<4;i++)
        {
            int r = S.getarrofblock()[i].getX();                    // get x coordinate of the block
            int c = S.getarrofblock()[i].getY();                    // get y coordinate of the block
            if(checkValidCoords(r, c))
            {
                arr[r][c] = '#';                                    // printing the block with # block for runtime    
            }
            else                                                    // if shape cannot be inserted
            {
                return false;
            }
        }
        return true;
    }

    /**
     * This function will insert the fixed shape with '@'
     * 
     * @param S for Shape to printed as fixed
     * 
     * @return boolean
     **/
    public void insertFixedShape(Shape S)
    {
        for(int i=0;i<4;i++)
        {
            int r = S.getarrofblock()[i].getX();                    // get x coordinate of the block
            int c = S.getarrofblock()[i].getY();                    // get y coordinate of the block
            count_block[r]++;                                       // increment the line blocks counter
            arr[r][c] = '@';                                        // printing @ for fixed shape
        }
        
        deleteLines();                                               // check for line deletion
        System.out.println("POINTS: "+points);
    }

    /**
     * This function will delete the line if the line is full of fixed blocks or not
     * 
     * @return boolean whether the lines are deleted or not
     * @bug below line don't work
     **/
    private boolean deleteLines()
    {
        boolean deleted = false;                                    // boolean for whether the line is deleted or not
        for(int i=0;i<count_block.length;i++)
        {
            if(count_block[i]==cols)                                // if the line gets full
            {
                deleted=true;                                       // set boolean to true as line is deleted

                // DELETING THE i LINE WHICH IS FULL                 
                for(int j=0;j<cols;j++)
                {
                    arr[i][j] = ' ';                                // reset the spaces
                    count_block[i]--;                               // decrease the count
                }

                //SHIFTING THE 2D ARRAY DOWN
                for(int j=i;j>0;j--)
                {
                    for(int k=0;k<cols;k++)
                    {
                        arr[j][k] = arr[j-1][k];
                    }
                }

                //SHIFTING THE COUNT BLOCK ARRAY TO RIGHT
                for(int j=i;j>0;j--)
                {
                    count_block[j] = count_block[j-1];
                }

                points++;                                           // increase the points by 1
            }
            else if(count_block[i]>cols)
            {
                System.out.println("GAME OVER !!!");
                System.exit(0);
            }
        }
        return deleted;
    }

    /**
     * This function will move the block down
     * 
     * @param S for Shape movement
     * 
     * @return boolean to check whether shape get fixed or not
     **/
    public boolean movedown(Shape S)
    {
        boolean flag_touched = false;                               // flag for checking block touching
        for(int i=0;i<4;i++)
        {
            //Get block coordinates 
            int x = S.getarrofblock()[i].getX();                    // get x coords of every block
            int y = S.getarrofblock()[i].getY();                    // get y coords of every block

            /**
             * Generate new coords for checking
             * x incremented by one
             * y remain unchanged
             **/
            x=x+1; 

            /**
             * Checking next down coordinates
             **/
            if(x==rows || arr[x][y]=='=' || arr[x][y]=='@')
            {
                flag_touched=true;                                  // shape touched to end or fixed block or rows
                break;
            }
        }
        if(!flag_touched)                                           // if shape don't touch to anything then move down
        {
            for(int i=0;i<4;i++)
            { 
                //Get block coordinates 
                int x = S.getarrofblock()[i].getX();
                int y = S.getarrofblock()[i].getY();
                
                /**
                 * Generate new coords for down movement
                 * x incremented by one
                 * y remain unchanged
                 **/
                S.getarrofblock()[i].setX(x+1);
                S.getarrofblock()[i].setY(y);
            }
            return false;                                           // returning false as shape not fixed see @Tetris.java
        }
        else                                                        // if flag touched get true
        {
            return true;                                            // returning true as shape fixed
        }
    }
    
    /**
     * This function will move the shape right
     * 
     * @param S for moving Shape right
     * @return boolean whether the shape moved right or not
     **/
    public void moveright(Shape S)
    {
        boolean invalid_flag = false;
        for(int i=0;i<4;i++)
        {
            int x = S.getarrofblock()[i].getX();
            int y = S.getarrofblock()[i].getY();

            y=y+1;

            if(y==cols || arr[x][y]=='@')                           // if y coordinate get equals to cols
            {
                invalid_flag = true;
            }
        }
        if(!invalid_flag)                                           // if new shape will be true
        {
            for(int i=0;i<4;i++)
            {
                //Get block coordinates
                int x = S.getarrofblock()[i].getX();
                int y = S.getarrofblock()[i].getY();
                
                //setting coords to the block
                S.getarrofblock()[i].setX(x);
                S.getarrofblock()[i].setY(y+1);
            }
        }
    }

    /**
     * This function will move the shape left
     * 
     * @param S for moving Shape left
     * @return boolean whether the shape moved left or not
     **/
    public void moveleft(Shape S)
    {
        boolean invalid_flag = false;
        for(int i=0;i<4;i++)
        {
            int x = S.getarrofblock()[i].getX();
            int y = S.getarrofblock()[i].getY();

            y=y-1;

            if(y==-1 || arr[x][y]=='@')                             // if y coordinate get equals to cols
            {
                invalid_flag = true;
            }
        }
        if(!invalid_flag)                                           // if new shape will be true
        {
            for(int i=0;i<4;i++)
            {
                //Get block coordinates
                int x = S.getarrofblock()[i].getX();
                int y = S.getarrofblock()[i].getY();
                
                //setting coords to the block
                S.getarrofblock()[i].setX(x);
                S.getarrofblock()[i].setY(y-1);
            }
        }
    }
}