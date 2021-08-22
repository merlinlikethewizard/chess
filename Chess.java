/*
 * By Merlin and Ido
 */
import java.util.Scanner;

public class Chess{
    Scanner input = new Scanner(System.in);
    private static String[][] grid = new String[8][8];
    private static String p1 = "P1";
    private static String p2 = "P2";
    private static boolean kingL = true;
    private static boolean rookLR = true;
    private static boolean rookLL = true;
    private static boolean kingU = true;
    private static boolean rookUR = true;
    private static boolean rookUL = true;
    private static boolean playerOneTurn = true;
    private static int yell = 0;
    private static int pawnLX = 8;
    private static int pawnLY = 8;
    private static int pawnUX = 8;
    private static int pawnUY = 8;
    private static void makeBoard(){
        String[][] tempGrid = new String[8][8];
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                tempGrid[i][j] = " ";
            }
        }
        tempGrid[0] = new String[] {"r", "n", "b", "q", "k", "b", "n", "r"};
        tempGrid[1] = new String[] {"p", "p", "p", "p", "p", "p", "p", "p"};
        tempGrid[6] = new String[] {"P", "P", "P", "P", "P", "P", "P", "P"};
        tempGrid[7] = new String[] {"R", "N", "B", "Q", "K", "B", "N", "R"};
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                grid[j][i] = tempGrid[i][j];
            }
        }
    }
    private static void playerInfo(){
        Scanner input = new Scanner(System.in);
        boolean bool = true;
        while(bool){
            drawScreen(1);
            p1 = input.nextLine();
            if(p1.length() <= 15 && p1.indexOf(" ") == -1){
                bool = false;
            } else {
                yell = 9;
                p1 = "P1";
            }
        }
        bool = true;
        playerOneTurn = false;
        while(bool){
            drawScreen(1);
            p2 = input.nextLine();
            if(p2.length() <= 15 && p2.indexOf(" ") == -1){
                bool = false;
            } else {
                yell = 9;
                p2 = "P2";
            }
        }
        playerOneTurn = true;
    }
    private static void drawScreen(int message){
        for (int i=1; i<101; i++){
            System.out.println("");
        }
        System.out.println("      _______   _      _   _______   _______   _______");
        System.out.println("     |  _____| | |    | | |  _____| |  _____| |  _____|");
        System.out.println("     | |       | |____| | | |___    | |_____  | |_____");
        System.out.println("     | |       |  ____  | |  ___|   |_____  | |_____  |");
        System.out.println("     | |_____  | |    | | | |_____   _____| |  _____| |");
        System.out.println("     |_______| |_|    |_| |_______| |_______| |_______|");
        System.out.println("");
        System.out.println("         a     b     c     d     e     f     g     h");
        System.out.println("       _______________________________________________");
        for(int i=1; i<=8; i++){
            System.out.println("      |     |     |     |     |     |     |     |     |");
            System.out.print("   " + i + "  |");
            for(int j=0; j<8; j++){
                System.out.print("  " + grid[j][i-1] + "  |");
            }
            System.out.println("");
            System.out.println("      |_____|_____|_____|_____|_____|_____|_____|_____|");
        }
        System.out.println("");
        switch(yell){
            case 0: System.out.println(""); break;
            case 1: System.out.println("Check!"); break;
            case 2: System.out.println("Checkmate!"); break;
            case 3: System.out.println("Illegal move!"); break;
            case 4: System.out.println("Piece taken!"); break;
            case 5: System.out.println("Stalemate!"); break;
            case 6: System.out.println("Invalid Coordinates!"); break;
            case 7: System.out.println("Castled!"); break;
            case 8: System.out.println("En Passant!"); break;
            case 9: System.out.println("Name must be no more than 15 characters, and contain no spaces."); break;
            case 10: System.out.println("Cannot leave king in check"); break;
            case 11: System.out.println("Piece Taken! Check!"); break;
            case 12: System.out.println("No moveable piece at the given coodinates!"); break;
            case 13: System.out.println("Invalid piece!"); break;
        }
        if(yell != 0){
            yell = 0;
        }
        System.out.println("");
        if(yell == 5){
            System.out.println("Nobody");
        } else if(playerOneTurn){
            System.out.println(p1);
        } else {
            System.out.println(p2);
        }
        switch(message){
            case 0: System.out.println(""); break;
            case 1: System.out.println("Enter name: "); break;
            case 2: System.out.println("Enter the current coordinates of the piece you want to move: "); break;
            case 3: System.out.println("Enter the coordinates of where you want to move the piece to: "); break;
            case 4: System.out.println("Enter piece to switch the pawn for (Q/R/N/B): "); break;
            case 5: System.out.println("Enter piece to switch the pawn for (q/r/n/b): "); break;
            case 6: System.out.println("Wins!"); break;
        }
    }
    private static int[] getCoords() {
        Scanner input = new Scanner(System.in);
        boolean bool = true;
        int[] newLocation;
        newLocation = new int[4];
        while(bool){
            drawScreen(2);
            String currentCoord = input.nextLine();
            currentCoord = currentCoord.toLowerCase();
            bool = false;
            if(currentCoord.length()==2){
                if (!(currentCoord.indexOf("a")==-1)) { newLocation[0]=0;
                } else if (!(currentCoord.indexOf("b")==-1)) { newLocation[0]=1;
                } else if (!(currentCoord.indexOf("c")==-1)) { newLocation[0]=2;
                } else if (!(currentCoord.indexOf("d")==-1)) { newLocation[0]=3;
                } else if (!(currentCoord.indexOf("e")==-1)) { newLocation[0]=4;
                } else if (!(currentCoord.indexOf("f")==-1)) { newLocation[0]=5;
                } else if (!(currentCoord.indexOf("g")==-1)) { newLocation[0]=6;
                } else if (!(currentCoord.indexOf("h")==-1)) { newLocation[0]=7;
                } else { bool = true; yell = 6;
                }
                if (!((currentCoord.indexOf("1"))==-1)) { newLocation[1]=0;
                } else if (!(currentCoord.indexOf("2")==-1)) { newLocation[1]=1;
                } else if (!(currentCoord.indexOf("3")==-1)) { newLocation[1]=2;
                } else if (!(currentCoord.indexOf("4")==-1)) { newLocation[1]=3;
                } else if (!(currentCoord.indexOf("5")==-1)) { newLocation[1]=4;
                } else if (!(currentCoord.indexOf("6")==-1)) { newLocation[1]=5;
                } else if (!(currentCoord.indexOf("7")==-1)) { newLocation[1]=6;
                } else if (!(currentCoord.indexOf("8")==-1)) { newLocation[1]=7;
                } else { bool = true; yell = 6;
                }
                String type = grid[newLocation[0]][newLocation[1]];
                if(type == " " || ((type == type.toUpperCase() && !playerOneTurn) || (type == type.toLowerCase() && playerOneTurn))){
                    bool = true;
                    yell = 12;
                }
            } else {
                bool = true;
                yell = 6;
            }
            if(!bool){
                drawScreen(3);
                String newCoord = input.nextLine();
                newCoord = newCoord.toLowerCase();
                if (newCoord.length()==2) {
                    if (!(newCoord.indexOf("a")==-1)) { newLocation[2]=0;
                    } else if (!(newCoord.indexOf("b")==-1)) { newLocation[2]=1;
                    } else if (!(newCoord.indexOf("c")==-1)) { newLocation[2]=2;
                    } else if (!(newCoord.indexOf("d")==-1)) { newLocation[2]=3;
                    } else if (!(newCoord.indexOf("e")==-1)) { newLocation[2]=4;
                    } else if (!(newCoord.indexOf("f")==-1)) { newLocation[2]=5;
                    } else if (!(newCoord.indexOf("g")==-1)) { newLocation[2]=6;
                    } else if (!(newCoord.indexOf("h")==-1)) { newLocation[2]=7;
                    } else { bool = true; yell = 6;
                    }
                    if (!((newCoord.indexOf("1"))==-1)) { newLocation[3]=0;
                    } else if (!(newCoord.indexOf("2")==-1)) { newLocation[3]=1;
                    } else if (!(newCoord.indexOf("3")==-1)) { newLocation[3]=2;
                    } else if (!(newCoord.indexOf("4")==-1)) { newLocation[3]=3;
                    } else if (!(newCoord.indexOf("5")==-1)) { newLocation[3]=4;
                    } else if (!(newCoord.indexOf("6")==-1)) { newLocation[3]=5;
                    } else if (!(newCoord.indexOf("7")==-1)) { newLocation[3]=6;
                    } else if (!(newCoord.indexOf("8")==-1)) { newLocation[3]=7;
                    } else { bool = true; yell = 6;
                    }
                } else {
                    bool = true;
                    yell = 6;
                }
            }
        }
        return newLocation;
    }
    private static int findKing(String[][] tempGrid, boolean upper, String xy){
        for(int i=0; i<=7; i++){
            for(int j=0; j<=7; j++){
                if((tempGrid[i][j] == "k" && !upper) || (tempGrid[i][j] == "K" && upper)){
                    if(xy == "x"){
                        return i;
                    } else {
                        return j;
                    }
                }
            }
        }
        return 8;
    }
    private static boolean castle(int startX, int startY, int endX, int endY, boolean upper){
        if(startX == 4 && !kingInCheck(upper, grid) && grid[endX][endY] == " "){
            boolean bool = true;
            String type = "k";
            if(upper){
                type = "K";
            }
            String[][] tempGrid = new String[8][8];
            for (int i=0; i<8; i++){
                for (int j=0; j<8; j++){
                    tempGrid[i][j] = grid[i][j];
                }
            }
            if(startY == 7 && kingU){
                if(endX == 1 && rookUL && grid[2][7] == " " && grid[3][7] == " "){
                    tempGrid[4][7] = " ";
                    tempGrid[3][7] = type;
                    if(kingInCheck(upper, tempGrid)){
                        bool = false;
                    }
                    tempGrid[3][7] = " ";
                    tempGrid[2][7] = type;
                    if(kingInCheck(upper, tempGrid)){
                        bool = false;
                    }
                    return bool;
                } else if(endX == 6 && rookUR && grid[5][7] == " "){
                    tempGrid[4][7] = " ";
                    tempGrid[5][7] = type;
                    if(kingInCheck(upper, tempGrid)){
                        bool = false;
                    }
                    return bool;
                }
            } else if(startY == 0 && kingL){
                if(endX == 1 && rookLL && grid[2][0] == " " && grid[3][0] == " "){
                    tempGrid[4][0] = " ";
                    tempGrid[3][0] = type;
                    if(kingInCheck(upper, tempGrid)){
                        bool = false;
                    }
                    tempGrid[3][0] = " ";
                    tempGrid[2][0] = type;
                    if(kingInCheck(upper, tempGrid)){
                        bool = false;
                    }
                    return bool;
                } else if(endX == 6 && rookLR && grid[5][0] == " "){
                    tempGrid[4][0] = " ";
                    tempGrid[5][0] = type;
                    if(kingInCheck(upper, tempGrid)){
                        bool = false;
                    }
                    return bool;
                }
            }
        }
        return false;
    }
    private static boolean blockCheck(String[][] tempGrid, int startX, int startY, int endX, int endY){
        int xAug = 0;
        int yAug = 0;
        if(startX > endX){
            xAug = -1;
        }
        if(startY > endY){
            yAug = -1;
        }
        if(startX < endX){
            xAug = 1;
        }
        if(startY < endY){
            yAug = 1;
        }
        boolean bool = true;
        for(int i=1; bool; i++){
            if(startX+(i*xAug) == endX && startY+(i*yAug) == endY){
                bool = false;
            } else if(tempGrid[startX+(i*xAug)][startY+(i*yAug)] != " "){
                return false;
            }
        }
        return true;
    }
    private static boolean pawnCheck(String[][] tempGrid, int startX, int startY, int endX, int endY, boolean upper){
        if((startY-1 == endY && upper) || (startY+1 == endY && !upper)){
            if(startX-endX >= -1 && startX-endX <= 1){
                if(startX == endX){
                    if(tempGrid[endX][endY] == " "){
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    String pawnPrey = tempGrid[endX][endY];
                    if(pawnPrey == " "){
                        if((endX == pawnLX && endY == pawnLY && upper) || (endX == pawnUX && endY == pawnUY && !upper)){
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        if((pawnPrey == pawnPrey.toUpperCase() && !upper) || (pawnPrey == pawnPrey.toLowerCase() && upper)){
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            } else {
                return false;
            }
        } else if (startX==endX && tempGrid[endX][endY] == " "){
            if (startY==6 && endY==4 && upper && tempGrid[endX][5] == " ") {
                return true;
            } else if ((startY==1 && endY==3 && !upper && tempGrid[endX][2] == " ")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    private static boolean rookCheck(String[][] tempGrid, int startX, int startY, int endX, int endY){
        if(startX==endX || startY==endY){
            return blockCheck(tempGrid, startX, startY, endX, endY);
        } else {
            return false;
        }
    }
    private static boolean knightCheck(int startX, int startY, int endX, int endY){
        if(Math.abs(startX-endX)+Math.abs(startY-endY)==3 && startX != endX && startY != endY){
            return true;
        } else {
            return false;
        }
    }
    private static boolean bishopCheck(String[][] tempGrid, int startX, int startY, int endX, int endY){
        if(Math.abs(startX-endX)==Math.abs(startY-endY)){
            return blockCheck(tempGrid, startX, startY, endX, endY);
        } else {
            return false;
        }
    }
    private static boolean queenCheck(String tempGrid[][], int startX, int startY, int endX, int endY){
        if(rookCheck(tempGrid, startX, startY, endX, endY) || bishopCheck(tempGrid, startX, startY, endX, endY)){
            return true;
        } else {
            return false;
        }
    }
    private static boolean kingCheck(String[][] tempGrid, int startX, int startY, int endX, int endY, boolean upper){
        if(Math.abs(startX-endX) <= 1 && Math.abs(startY-endY) <= 1){
            return true;
        } else {
            return false;
        }
    }
    private static boolean moveCheck(String tempGrid[][], int startX, int startY, int endX, int endY){
        String type = tempGrid[startX][startY];
        boolean upper = true;
        if(type == type.toLowerCase()){
            upper = false;
        }
        String typeTake = tempGrid[endX][endY];
        if((typeTake != " " && ((typeTake == typeTake.toUpperCase() && upper) || (typeTake == typeTake.toLowerCase() && !upper))) || (startX == endX && startY == endY)){
            return false;
        }
        type = type.toLowerCase();
        switch(type){
            case "p": return pawnCheck(tempGrid, startX, startY, endX, endY, upper);
            case "r": return rookCheck(tempGrid, startX, startY, endX, endY);
            case "n": return knightCheck(startX, startY, endX, endY);
            case "b": return bishopCheck(tempGrid, startX, startY, endX, endY);
            case "q": return queenCheck(tempGrid, startX, startY, endX, endY);
            case "k": return kingCheck(tempGrid, startX, startY, endX, endY, upper);
        }
        return false;
    }
    private static void pawnExchange(int endX, int endY){
        Scanner input = new Scanner(System.in);
        if(endY == 0 || endY == 7){
            boolean spellCheck = true;
            while(spellCheck) {
                if (endY == 0) {
                    drawScreen(4);
                } else {
                    drawScreen(5);
                }
                String newPiece = input.nextLine();
                newPiece.toLowerCase();
                spellCheck = false;
                switch(newPiece){
                    case "r": 
                        if (endY == 0) {
                            grid[endX][endY] = "R";
                        } else {
                            grid[endX][endY] = "r";
                        }
                        break;
                    case "b": 
                        if (endY == 0) {
                            grid[endX][endY] = "B";
                        } else {
                            grid[endX][endY] = "b";
                        }
                        break;
                    case "n":
                        if (endY == 0) {
                            grid[endX][endY] = "N";
                        } else {
                            grid[endX][endY] = "n";
                        }
                        break;
                    case "q":
                        if(endY == 0){
                            grid[endX][endY] = "Q";
                        } else {
                            grid[endX][endY] = "q";
                        }
                        break;
                    default: 
                        spellCheck = true;
                        yell = 13;
                        break;
                }
            }
        } 
    }
    private static boolean kingInCheck(boolean upper, String[][] tempGrid){
        for(int i=0; i<=7; i++){
            for(int j=0; j<=7; j++){
                String type = tempGrid[i][j];
                int kingX = findKing(tempGrid, upper, "x");
                int kingY = findKing(tempGrid, upper, "y");
                if(type != " " && ((upper && type == type.toLowerCase()) || (!upper && type == type.toUpperCase())) && moveCheck(tempGrid, i, j, kingX, kingY)){
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean canMove(boolean upper){
        for(int i=0; i<=7; i++){
            for(int j=0; j<=7; j++){
                String piece = grid[i][j];
                if(piece != " " && ((piece==piece.toLowerCase() && !upper) || (piece==piece.toUpperCase() && upper))){
                    for(int k=0; k<=7; k++){
                        for(int l=0; l<=7; l++){
                            if(moveCheck(grid, i, j, k, l)){
                                String[][] tempGrid = new String[8][8];
                                for (int m=0; m<8; m++){
                                    for (int n=0; n<8; n++){
                                        tempGrid[m][n] = grid[m][n];
                                    }
                                }
                                tempGrid[k][l] = piece;
                                tempGrid[i][j] = " ";
                                if(!kingInCheck(upper, tempGrid)){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    private static void takeTurn(){
        boolean bool = true;
        while(bool){
            bool = false;
            int[] coords = getCoords();
            String type = grid[coords[0]][coords[1]];
            String[][] tempGrid = new String[8][8];
            for (int i=0; i<8; i++){
                for (int j=0; j<8; j++){
                    tempGrid[i][j] = grid[i][j];
                }
            }
            boolean castled = (type == "k" || type == "K") && castle(coords[0], coords[1], coords[2], coords[3], playerOneTurn);
            if(moveCheck(tempGrid, coords[0], coords[1], coords[2], coords[3]) || castled){
                if(grid[coords[2]][coords[3]] != " "){
                    yell = 4;
                }
                tempGrid[coords[2]][coords[3]] = type;
                tempGrid[coords[0]][coords[1]] = " ";
                if(castled){
                    yell = 7;
                    if(coords[2] == 1){
                        if(coords[3] == 0){
                            tempGrid[2][0] = "r";
                            tempGrid[0][0] = " ";
                        } else {
                            tempGrid[2][7] = "R";
                            tempGrid[0][7] = " ";
                        }
                    } else {
                        if(coords[3] == 0){
                            tempGrid[5][0] = "r";
                            tempGrid[7][0] = " ";
                        } else {
                            tempGrid[5][7] = "R";
                            tempGrid[7][7] = " ";
                        }
                    }
                }
                if(kingInCheck(playerOneTurn, tempGrid)){
                    bool = true;
                    yell = 10;
                } else {
                    for (int i=0; i<8; i++){
                        for (int j=0; j<8; j++){
                            grid[i][j] = tempGrid[i][j];
                        }
                    }
                    if((coords[0] == 0 && coords[1] == 0) || (coords[2] == 0 && coords[3] == 0)){
                        rookLL = false;
                    }
                    if((coords[0] == 7 && coords[1] == 0) || (coords[2] == 7 && coords[3] == 0)){
                        rookLR = false;
                    }
                    if((coords[0] == 0 && coords[1] == 7) || (coords[2] == 0 && coords[3] == 7)){
                        rookUL = false;
                    }
                    if((coords[0] == 7 && coords[1] == 7) || (coords[2] == 7 && coords[3] == 7)){
                        rookUR = false;
                    }
                    if(coords[0] == 4 && coords[1] == 0){
                        kingL = false;
                    }
                    if(coords[0] == 4 && coords[1] == 7){
                        kingU = false;
                    }
                    if(coords[2] == pawnLX && coords[3] == pawnLY && playerOneTurn && (type == "p" || type == "P")){
                        grid[pawnLX][pawnLY+1] = " ";
                        yell = 8;
                    }
                    if(coords[2] == pawnUX && coords[3] == pawnUY && !playerOneTurn && (type == "p" || type == "P")){
                        grid[pawnUX][pawnUY-1] = " ";
                        yell = 8;
                    }
                    if(type == "p" || type == "P"){
                        pawnExchange(coords[2], coords[3]);
                        if(Math.abs(coords[1] - coords[3]) == 2){
                            pawnLX = coords[0];
                            pawnLY = (coords[1] + coords[3]) / 2;
                            pawnUX = coords[0];
                            pawnUY = (coords[1] + coords[3]) / 2;
                        }
                    }
                    if(kingInCheck(!playerOneTurn, tempGrid)){
                        if(yell == 4){
                            yell = 11;
                        } else {
                            yell = 1;
                        }
                    }
                }
            } else {
                bool = true;
                yell = 3;
            }
        }
    }
    public static void main(String[] argv){
        makeBoard();
        playerInfo();
        boolean bool = true;
        while(bool){
            takeTurn();
            if(playerOneTurn){
                playerOneTurn = false;
                pawnLX = 8;
                pawnLY = 8;
            } else {
                playerOneTurn = true;
                pawnUX = 8;
                pawnUY = 8;
            }
            if(!canMove(playerOneTurn)){
                if(kingInCheck(playerOneTurn, grid)){
                    if(playerOneTurn){
                        playerOneTurn = false;
                    } else {
                        playerOneTurn = true;
                    }
                    yell = 2;
                    drawScreen(6);
                } else {
                    yell = 5;
                    drawScreen(6);
                }
                bool = false;
            }
        }
    }
}
/*
 *           /\
 *          /  \
 *         /    \
 *        /______\
 *       /\      /\
 *      /  \    /  \
 *     /    \  /    \
 *    /______\/______\
 */
