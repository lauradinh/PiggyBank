import java.util.Random;

/**
 * Represents a Piggy Bank that contains the names and values of coins
 * 
 * @author Laura Dinh & Devashi Ghoshal
 */
public class PiggyBank {

  // Declaring constants for the class
  public final static int[] COINS_VALUES = {1, 5, 10, 25}; // coins values in cents
  // coins names
  public final static String[] COINS_NAMES = {"PENNY", "NICKEL", "DIME", "QUARTER"};
  public final static String NO_SUCH_COIN = "N/A"; // N/A string
  private final static Random RAND_GEN = new Random(100); // generator of random integers

  /**
   * Returns the name of a specified coin value
   * 
   * @param coin represents a coin value in cents.
   * @return the String name of a specified coin value if it is valid and N/A if the coin value is
   *         not valid
   */
  public static String getCoinName(int coin) {
    if (coin == COINS_VALUES[0]) {
      return COINS_NAMES[0];
    } else if (coin == COINS_VALUES[1]) {
      return COINS_NAMES[1];
    } else if (coin == COINS_VALUES[2]) {
      return COINS_NAMES[2];
    } else if (coin == COINS_VALUES[3]) {
      return COINS_NAMES[3];
    }
    return NO_SUCH_COIN;
  }

  /**
   * Returns the balance of a piggy bank in cents
   * 
   * @param coins an oversize array which contains all the coins held in a piggy bank
   * @param size  the total number of coins stored in coins array
   * @return the total value of the coins held in a piggy bank in cents
   */
  public static int getBalance(int[] coins, int size) {
    int balance = 0;
    for (int i = 0; i < size; i++) {
      balance += coins[i];
    }
    return balance;
  }

  /**
   * Returns the total number of coins of a specific coin value held in a piggy bank
   *
   * @param coinValue the value of a specific coin
   * @param coins     an oversize array which contains all the coins held in a piggy bank
   * @param size      the total number of coins stored in coins array
   * @return the number of coins of value coinValue stored in the array coins
   */
  public static int getSpecificCoinCount(int coinValue, int[] coins, int size) {
    int count = 0;
    for (int i = 0; i < size; i++) {
      if (coins[i] == coinValue) {
        count++;
      }
    }
    return count;
  }

  /**
   * Displays information about the content of a piggy bank
   *
   * @param coins an oversize array storing the coins held in a piggy bank
   * @param size  number of coin held array coins
   */
  public static void printPiggyBank(int[] coins, int size) {
    System.out.println("QUARTERS: " + getSpecificCoinCount(25, coins, size));
    System.out.println("DIMES: " + getSpecificCoinCount(10, coins, size));
    System.out.println("NICKELS: " + getSpecificCoinCount(5, coins, size));
    System.out.println("PENNIES: " + getSpecificCoinCount(1, coins, size));
    System.out.println("Balance: $" + getBalance(coins, size) * 0.01);
  }

  /**
   * Adds a given coin to a piggy bank. This operation can terminate successfully or unsuccessfully.
   * For either cases, this method displays a descriptive message for either cases.
   *
   * @param coin  the coin value in cents to be added to the array coins
   * @param coins an oversize array storing the coins held in a piggy bank
   * @param size  the total number of coins contained in the array coins before this addCoin method
   *              is called.
   * @return the new size of the coins array after trying to add coin.
   */
  public static int addCoin(int coin, int[] coins, int size) {
    if (coin != COINS_VALUES[0] && coin != COINS_VALUES[1] && coin != COINS_VALUES[2]
        && coin != COINS_VALUES[3]) {
      System.out.println(coin + " cents is not a valid US currency coin.");
      return size;
    }

    String coinName = getCoinName(coin);
    if (size >= coins.length) {
      System.out.println(
          "Tried to add a " + coinName + ", but could not because the piggy bank is full.");
      return size;
    }
    System.out.println("Added a " + coinName + ".");
    coins[size] = coin;
    return size + 1;
  }

  /**
   * Removes an arbitrary coin from a piggy bank. This method may terminate successfully or
   * unsuccessfully. In either cases, a descriptive message is displayed.
   *
   * @param coins an oversize array which contains the coins held in a piggy bank
   * @param size  the number of coins held in the coins array before this method is called
   * @return the size of coins array after this method returns successfully
   */
  public static int removeCoin(int[] coins, int size) {
    if (size == 0) {
      System.out.println("Tried to remove a coin, but could not because the piggy bank is empty.");
    } else {
      int index = RAND_GEN.nextInt(size);
      int coin = coins[index];
      coins[index] = 0;
      System.out.println("Removed a " + getCoinName(coin) + ".");
      size--;
    }
    return size;
  }

  /**
   * Removes all the coins in a piggy bank. It also displays the total value of the removed coins
   *
   * @param coins an oversize array storing the coins held in a piggy bank application
   * @param size  number of coins held in coins array before this method is called
   * @return the new size of the piggy bank after removing all its coins.
   */
  public static int emptyPiggyBank(int[] coins, int size) {
    if (size == 0) {
      System.out.println("Zero coin removed. The piggy bank is already empty.");
    } else {
      System.out.println("All done. " + getBalance(coins, size) + " cents removed.");
      for (int i = 0; i < size; i++) {
        coins[i] = 0;
      }
      size = 0;
    }
    return size;
  }

  /**
   * Removes the least number of coins needed to fulfill a withdrawal request
   *
   * @param amount amount to withdraw given in cents
   * @param coins  an oversize array storing the coins held in a piggy bank
   * @param size   number of coins stored in coins array before this method is called
   * @return perfect size array of 5 elements, index 0 stores the new size of the piggy bank after
   *         this method returns. Indexes 1, 2, 3, and 4 store respectively the number of removed
   *         quarters, dimes, nickels, and pennies.
   */
  public static int[] withdraw(int amount, int[] coins, int size) {
    // counts how many coins are removed
    int quarter = 0;
    int dime = 0;
    int nickel = 0;
    int penny = 0;
    int balance = getBalance(coins, size);
    int[] contents = new int[5];
    if (amount > balance) {
      System.out
          .println("Unable to withdraw " + amount + " cents. NOT enough money in the piggy bank.");
      contents = new int[] {size, quarter, dime, nickel, penny};
      return contents;
    }
    
    // checks quarters
    for (int i = 0; i < size; i++) {
      if (coins[i] == 25 && amount - 25 >= 0) {
        amount -= 25;
        quarter++;
        coins[i] = 0;
      }
    }

    // checks for dimes
    for (int i = 0; i < size; i++) {
      if (coins[i] == 10 && amount - 10 >= 0) {
        amount -= 10;
        dime++;
        coins[i] = 0;
      }
    }

    // checks for nickels
    for (int i = 0; i < size; i++) {
      if (coins[i] == 5 && amount - 5 >= 0) {
        amount -= 5;
        nickel++;
        coins[i] = 0;
      }
    }

    // checks for pennies
    for (int i = 0; i < size; i++) {
      if (coins[i] == 1 && amount - 1 >= 0) {
        amount -= 1;
        penny++;
        coins[i] = 0;
      }
    }


    //non exact amounts
    
    if (amount > 0) {
    	// quarters
        for (int i = 0; i < size; i++) {
            if (coins[i] == 25) {
              amount -= 25;
              quarter++;
              coins[i] = 0;
            }
          }
    } 
    // shift elements in coins array
    int temp;
    int count = 0;
    for (int i = 0; i < size; i++) {
      if (coins[i] != 0) {
        temp = coins[count];
        coins[count] = coins[i];
        coins[i] = temp;
        count = count + 1;
      }
    }
    
    // prints coin array 
    for (int i = 0; i < size; i++) {
      System.out.print(coins[i] + " ");
    }
    System.out.println();
    
    size = size - quarter - dime - nickel - penny;
    
    contents = new int[] {size, quarter, dime, nickel, penny};
  
    // prints content array 
    for (int i = 0; i < 5; i++) {
      System.out.print(contents[i] + " ");
    }
    System.out.println();

    return contents;
  }

  /**
   * command menu
   * 
   * @param args
   */
  public static void main(String[] args) {

  }
}
