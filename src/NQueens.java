import java.util.Stack;

public class NQueens {

    public static int solve(int n) {
        int[][] board = new int[n][n];
        Stack<Integer> stack = new Stack<>();
        int i = 0, j = 0, numSolutions = 0;

        while(i < n && j < n){
            if(checkIfValidPosition(board, i, j)){
                board[i][j] = 1;
                stack.push(j);
                if(stack.size() == n){
                    numSolutions++;
                    printSolution(stack);
                    stack.pop();
                    board[i][j] = 0;
                    i--;
                    j = stack.pop();
                    while(j == n - 1){
                        board[i][j] = 0;
                        if(stack.isEmpty()){
                            return numSolutions;
                        }
                        i--;
                        j = stack.pop();
                    }
                    board[i][j] = 0;
                    j++;
                    continue;
                }
                j = -1;
                i++;
            }
            else if(j == n - 1) {
                if (stack.isEmpty()) {
                    return numSolutions;
                } else {
                    i--;
                    board[i][j] = 0;
                    j = stack.pop();
                    while (j == n - 1) {
                        board[i][j] = 0;
                        if (stack.isEmpty()) {
                            return numSolutions;
                        }
                        j = stack.pop();
                        i--;
                    }
                    board[i][j] = 0;
                    j++;
                    continue;
                }
            }
            j++;
        }
        return numSolutions;
    }

    public static boolean checkIfValidPosition(int[][] board, int x, int y){
        int i = 0;
        boolean isValid = true;

        //Checks horizontally and vertically if placement is valid
        for(; i < board.length; i++){
            if(board[x][i] == 1 || board[i][y] == 1){
                isValid = false;
                break;
            }
        }

        //Checks if position is valid diagonally
        for(i = 0; i < board.length; i++){
            if(x + i < board.length && y + i < board.length){
                if(board[x + i][y + i] == 1){
                    isValid = false;
                    break;
                }
            }
            if(x - i >= 0 && y - i >= 0){
                if(board[x - i][y - i] == 1){
                    isValid = false;
                    break;
                }
            }
            if(x - i >= 0 && y + i < board.length){
                if(board[x - i][y + i] == 1){
                    isValid = false;
                    break;
                }
            }
            if(x + i < board.length && y - i >= 0){
                if(board[x + i][y - i] == 1){
                    isValid = false;
                    break;
                }
            }
        }

        return isValid;

    }

    private static void printSolution(Stack<Integer> s) {

        for (int i = 0; i < s.size(); i ++) {
            for (int j = 0; j < s.size(); j ++) {
                if (j == s.get(i)) { System.out.print("Q "); }
                else { System.out.print("* "); }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int n = 8;
        // pass in parameter n from command line
        if (args.length == 1) {
            n = Integer.parseInt(args[0].trim());
            if (n < 1) {
                System.out.println("Incorrect parameter");
                System.exit(-1);
            }
        }

        int number = solve(n);

        System.out.println("There are " + number + " solutions to the " + n + "-queens problem.");

    }



}