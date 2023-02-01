package e1;
public class DateUtilities {

    public static void main (String[] args){
    }
    public static boolean isLeap(int year) {
        return year % 400 == 0 || (year % 100 != 0 && year % 4 == 0); // If the value is divisible by 400 or by 4 but not by 100 is true. Else false.
    }
    public static int numberOfDays(int month, int year) {
        int days;
        switch (month) { // A switch with every month number that gives the corresponding number of days to an int.
            case 1, 3, 5, 7, 8, 10, 12:
                days = 31;
                break;
            case 4, 6, 9, 11:
                days = 30;
                break;
            case 2: // For February, we check if it's a leap year or not.
                if (isLeap(year)) {
                    days = 29;
                }
                else days = 28;
                break;
            default:
                throw new IllegalArgumentException(); // If the month number is not between 1 and 12, it throws a IllegalArgumentException.
        }
        return days;
    }
    public static String convertToISODate(String dateText) {
        String[] div1 = dateText.split(", "); // It splits the string into (Month, day) and year.
        String[] div2 = div1[0].split(" "); // It splits the first string into Month and day.
        String dayISO = div2[1];
        String yearISO = div1[1];
        String monthISO = switch(div2[0]){ // For each month, gives the respective number.
            case "January"->"01";
            case "February"->"02";
            case "March"->"03";
            case "April"-> "04";
            case "May"->"05";
            case "June"->"06";
            case "July"-> "07";
            case "August"->"08";
            case "September"->"09";
            case "October"-> "10";
            case "November"->"11";
            case "December"-> "12";
            default -> throw new IllegalArgumentException("Exception: Invalid Month"); // If the month isn't valid, it throws an IllegalArgumentException.
        };
        return String.join("-", yearISO, monthISO,dayISO); // Returns the joined string of the whole date in ISO format.
    }
    public static boolean checkISODate(String ISODate) {

        String[] div = ISODate.split("-"); // Splits the string into day, month and year.
        for (int n = 0; n < ISODate.length(); n++){ // Checks if every character is a number or a letter.
            if(ISODate.charAt(n)!=45 && (ISODate.charAt(n)>57 || ISODate.charAt(n)<48) ){
                return false;
            }
        }
        if( div[0].length()!=4 || div[1].length()!=2 || div[2].length()!=2){ // Check the length of the given data, to look if the size is correct.
            return false;
        }
        //check dates (once we know all characters are correctly entered)
        else{
            int year = Integer.parseInt(div[0]);
            int month = Integer.parseInt(div[1]);
            int day = Integer.parseInt(div[2]);
            return month <= 12 && day <= numberOfDays(month, year) && (!isLeap(year) || month != 2 || numberOfDays(month, year) != 28);
            // Check that the values are correct, even for a leap year.
        }
    }
}
