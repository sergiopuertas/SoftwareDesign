package e2;

public class SocialDistance{
        public static void main (String[] args){
        }
        public static char [][] seatingPeople(char[][] layout){
                //Check if the input layout is null
                if (layout == null){
                        throw new IllegalArgumentException();
                }
                int row = layout.length, col = layout[0].length;
                boolean stayend = false ,sitend = false;
                //Check if the input is not ragged (every row have the same amount of columns) and for illegal characters
                for (char[] chars : layout) {
                        if (chars.length != col) {
                                throw new IllegalArgumentException("Invalid input");
                        }
                        for (int j = 0; j < layout[0].length; j++) {
                                if (chars[j] != '#' && chars[j] != '.' && chars[j] != 'A') {
                                        throw new IllegalArgumentException("Invalid input");
                                }
                        }
                }
                //Starting the main loop
                while(!(sitend && stayend)) {
                        //it will end when no one can sit and everyone sat is safe
                        stayend = true;
                        sitend = true;
                        //for every # in the layout, if it isnt safe it changes to an intermediate position u (up)
                        for (int i = 0; i < row; i++) {
                                for (int j = 0; j < col; j++) {
                                        if (layout[i][j] == '#') {
                                                if (!isSafeToStay(layout, i, j, row, col)) {
                                                        layout[i][j] = 'u';
                                                        stayend = false;
                        //if someone has to get up, finishing isnt possible
                                                }
                                        }
                                }
                        }
                        //Seats marked as "u" change into "A" (the were safe)
                        sitOrGetUp(layout);
                        //for every "A" in the layout, if the seat is safe, it changes to an intermediate position "s" (sit)
                        for (int i = 0; i < row; i++) {
                                for (int j = 0; j < col; j++) {
                                        if (layout[i][j] == 'A') {
                                                if (isSafeToSit(layout, i, j, row, col)) {
                                                        layout[i][j] = 's';
                                                        sitend = false;
                        //if anyone could have seat, finishing isn't possible as the layout has changed, it need to be checked again
                                                }
                                        }
                                }
                        }
                        //Seats marked as "s" change into "#" (they were safe)
                        sitOrGetUp(layout);
                }
                //return the final layout
                return layout;
        }
        //this method counts the number of occupied seats around the occupied place checked to see if it is safe
        private static boolean isSafeToStay(char[][] layout, int row, int col,int matRow, int matCol){
                int occupied = 0;
                for (int i = row - 1; i <= row + 1; i++) {
                          for (int j = col - 1; j <= col + 1; j++) {
                                  //for the seats on the borders, so as to avoiding errors, we reject the indexes out of bounds
                                  if(i<0 || i>=matRow || j<0 || j>=matCol){continue;}
                                   if (layout[i][j] == '#'|| layout[i][j] == 'u' ) {
                                           occupied++;
                                   }
                          }
                }
                //The number of neighbours must be lower or equal to 3, but as we count the seat to be checked, cnt is= 4
                return occupied <= 4;
        }
        //this method counts the number of occupied seats around the empty place checked to see if it can be occupied
        private static boolean isSafeToSit(char[][] layout, int row, int col,int matRow, int matCol){
                for(int i = row-1; i<=row+1;i++){
                        for(int j = col-1 ; j<=col+1; j++){
                                //for the seats on the borders, so as to avoiding errors, we reject the indexes out of bounds
                                if(i<0 || i>=matRow || j<0 || j>=matCol) {continue;}
                                //if it has any neighbour, return false
                                if(layout[i][j]== '#'|| layout[i][j] == 'u' )
                                        return false;
                        }
                }
                return true;
        }
        // All the seats marked as u (up) are changed by A and all the seats marked as s (sit) are changed by #
        private static void sitOrGetUp(char[][] layout){
                for (int i = 0; i < layout.length; i++) {
                        for (int j = 0; j <layout[0].length; j++) {
                                if (layout[i][j] == 'u') {
                                        layout[i][j] = 'A';
                                }
                                else if (layout[i][j] == 's') {
                                        layout[i][j] = '#';
                                }
                        }
                }
        }
}