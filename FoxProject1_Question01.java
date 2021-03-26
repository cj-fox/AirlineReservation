import java.util.*;
public class FoxProject1_Question01 {
    public static void main(String [] args){
        Airplane plane = new Airplane();
        Scanner input = new Scanner(System.in);
        boolean loopAgain=true; //boolean value to determine when to exit program

        while(loopAgain) {//loop until the user decides not to reserve another seat
            plane.displayMenu();

            boolean validAction = false;

            while(!validAction) {
                System.out.println("To reserve a seat enter Y/y(Yes), N/n(No):");
                char action = (input.nextLine()).charAt(0);
                action = Character.toUpperCase(action);

                if(action != 'N' && action!='Y')
                    System.out.println("Invalid input. Please try again.");
                else {
                    validAction = true;

                    if (action == 'N')
                        loopAgain = false; //make loop again false to exit while loop and finish program
                    if (action == 'Y')
                        plane.assignSeat();//if action is Y, call method from Airplane class to begin to assign a seat
                }
            }
        }


    }
}

class Airplane{
    static private final char [] seatLetters = {'A','B','C','D','E','F'};
    static private final int[] firstClassRows = {1,2};
    static private final int [] businessClassRows = {3,4,5,6,7};
    static private final int [] economyClassRows = {8,9,10,11,12,13};
    private char [][] totalSeats = new char[13][6];
    private char ticketType;
    private int row;
    private int column;

    Airplane(){
        for(int i=0;i<totalSeats.length;i++) //initialize totalSeats: * indicates available, X will indicate unavailable seats
            Arrays.fill(totalSeats[i], '*');
    }

    public void displaySeats(){ //method to show the formatted output of the seating arrangement
        System.out.printf("%7c",' '); //create space in top left corner of the table
        for(int i=0;i<seatLetters.length;i++){ //output the seat letters above
            if(i==3)
            System.out.printf(" %c ", seatLetters[i]);//extra space between the middle columns
            else System.out.printf("%c ",seatLetters[i]);
        }
        System.out.println(); //skip to next line
        for(int i =0; i<totalSeats.length;i++) {//loop through rows of totalSeats
            System.out.printf("Row %-3d", i + 1); //output the formatted row number
            for(int j=0; j<totalSeats[i].length; j++) { //loop through the columns of that row
                if(j==3)
                    System.out.printf(" %c ",totalSeats[i][j]);//extra space between the middle columns
                else
                    System.out.printf("%c ", totalSeats[i][j]); //output element at that index

            }
            System.out.println();//skip to next line
        }
    }
    public void displayMenu() {//method to show the opening menu and seats

        System.out.println("This program assigns seats for a commercial airplane.");
        System.out.println("The current seat assignment is as follows.");
        this.displaySeats(); //display the current seating arrangement with displaySeats method
        System.out.println("* = available seat");
        System.out.println("X = occupied seat\n");
        System.out.println("Rows 1 and 2 are for first class passengers.");
        System.out.println("Rows 3 through 7 are for business class passengers.");
        System.out.println("Rows 7 through 13 are for economy class passengers.");

    }
    public void assignSeat() { //method for user to choose reserve their seat
        Scanner input = new Scanner(System.in);

        boolean validTicketType = false;
        while(!validTicketType){//loop to validate the ticket type
                //read input for ticket class and validate the ticket type
                System.out.print("Enter ticket type: F/f(first class); B/b(business class); E/e(economy class: ");
                ticketType = (input.nextLine()).charAt(0);
                ticketType = Character.toUpperCase(ticketType);


                if(ticketType == 'F' || ticketType =='B'||ticketType == 'E')
                    validTicketType = true;
                else
                    System.out.println("Invalid input. Please try again.");
            }//end ticket type validation


            boolean available = false;//boolean value to indicate whether or not the chosen seat was available
            while (!available) { //loop until the user selects a seat that is available

                boolean validRow = false;
                while(!validRow) {//loop to validate the row
                    //ask user to choose a row based on what class of ticket they chose
                    if (ticketType == 'F')
                        System.out.print("Enter Row number 1-2: ");
                    else if (ticketType == 'B')
                        System.out.print("Enter Row number 3-7:  ");
                    else if (ticketType == 'E')
                        System.out.print("Enter Row number 8-13: ");
                    row = input.nextInt();
                    input.nextLine();//read the enter character from previous input to avoid error

                    //validate the chosen row was a valid choice for that ticket type
                    if(ticketType == 'F'){
                        for(int i: firstClassRows)
                            if(i==row)//if the row is one of the elements of firstClassRows
                                validRow = true;
                    }
                    else if(ticketType =='B'){
                        for(int i: businessClassRows)
                            if(i==row)//if the row is an element of businessClassRows
                                validRow = true;
                    }
                    else if(ticketType == 'E'){
                        for(int i:economyClassRows)
                            if(i == row)//if the row is an element of economyClassRows
                                validRow = true;
                    }

                    if(!validRow)
                        System.out.println("Invalid row input. Please try again.");

                }//end row validation

                boolean validLetter=false;
                while(!validLetter) {//loop to validate the seat letter
                    System.out.print("Enter seat letter (A-F): ");
                    char letter = (input.nextLine()).charAt(0);
                    letter = Character.toUpperCase(letter);

                    for(int i=0;i<seatLetters.length;i++){
                        if(seatLetters[i] == letter)
                            validLetter = true;
                    }
                    if(validLetter)
                        column = (letter - 'A');//cast the seat letter to a column index
                    else
                        System.out.println("Invalid seat letter. Please try again.");

                }//end letter validation

                //check that the seat is available
                if (totalSeats[row - 1][column] == '*') {//if the seat is available
                    available = true;//reflect availability in the boolean variable
                    totalSeats[row - 1][column] = 'X';//change the element in the totalSeats array to show that the seat is now reserved
                }

                if (available) {//if the seat was available to the user, output a message indicating so
                    System.out.println("The seat is reserved for you.");
                } else {//if the seat was not available to the user, tell them, display the seating chart again and loop again to get new inputs
                    System.out.println("Seat is occupied. Make another selection.");
                    displaySeats();
                }//end availability validation

            }




    }
}


