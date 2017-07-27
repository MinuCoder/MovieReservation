package MovieReservation;

/**
 * Created by 민우 on 2017-07-27.
 */
public class Theater {
    private Seat[][] seats;
    private int rowCount, colCount;

    public Theater(int rowCount, int colCount){
        // 알파벳은 26개만 존재
        if (rowCount > 26) {
            rowCount = 26; // number of alphabets
        }
        seats = new Seat[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                seats[i][j] = new Seat();
            }
        }
        this.rowCount=rowCount;
        this.colCount=colCount;
    }

    public boolean reserve(String name, char rowChar, int col, int numSet){
        Seat s = new Seat();
        if (getRowIndex(rowChar)>=rowCount||col>=colCount){
            return false;
        }
        else if (col<0||numSet+col-1>colCount){
            return false;
        }
        else if (s.isOccupied()){
            return false;
        }
        else
            for (int i=col-1; i<col+numSet-1; i++) {
                seats[getRowIndex(rowChar)][i].reserve(name);
            }
        return true;
    }
    public int cancel(String name) {
        Seat seat = new Seat();
        int count = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (seats[i][j].getName()!=null) {
                    if (seats[i][j].match(name)){
                        seats[i][j].cancel();
                        count++;
                    }
                }
            }
        }
        return count;
    }
    public int cancel(char rowChar, int col, int numSet){
        int count = 0;
        for (int i=col-1; i<col+numSet-1; i++) {
            if (seats[getRowIndex(rowChar)][i].getName()!=null) {
                seats[getRowIndex(rowChar)][i].cancel();
                count++;
            }
        }
        return count;
    }
    public int getNumberOfReservedSeat(){
        int count = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                if (seats[i][j].getName()!=null) {
                    count++;
                }
            }
        }
        return count;
    }


    public void printSeatMatrix() {
        System.out.print("  ");
        for (int i = 1; i <= 9; i++) {
            System.out.print("  " + i);
        }
        System.out.println();

        for (int i = 0; i < rowCount; i++) {
            System.out.print((char) ('A' + i) + ": ");
            for (int j = 0; j < colCount; j++) {
                Seat s = seats[i][j];
                if (s.isOccupied()) {
                    System.out.print("[O]");
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }

    private int getRowIndex(char uppercaseChar) {
        return uppercaseChar - 'A';
    }
}
