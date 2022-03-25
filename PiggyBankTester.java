/**
 * Tests the methods in the PiggyBank Class
 * 
 * @author Laura Dinh & Devashi Ghoshal
 * @see also PiggyBank
 */
public class PiggyBankTester {

  /**
   * Checks whether PiggyBank.getCoinName() method works as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean getCoinNameTestMethod() {
    // change some coin values and names
    PiggyBank.COINS_NAMES[1] = "Two cents";
    PiggyBank.COINS_NAMES[3] = "Fifty Cents";
    PiggyBank.COINS_VALUES[1] = 2;
    PiggyBank.COINS_VALUES[3] = 50;

    // consider all coin values as input arguments
    for (int i = 0; i < PiggyBank.COINS_VALUES.length; i++)
      if (!PiggyBank.getCoinName(PiggyBank.COINS_VALUES[i]).equals(PiggyBank.COINS_NAMES[i]))
        return false;
    // consider input argument which does not correspond to a coin value
    if (!PiggyBank.getCoinName(7).equals(PiggyBank.NO_SUCH_COIN))
      return false;
    if (!PiggyBank.getCoinName(-10).equals(PiggyBank.NO_SUCH_COIN))
      return false;
    if (!PiggyBank.getCoinName(0).equals(PiggyBank.NO_SUCH_COIN))
      return false;
    return true;
  }

  /**
   * Checks whether PiggyBank.getBalance() method works as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean getBalanceTestMethod() {
    // scenario 1 - empty piggy bank
    int[] coins = new int[10]; // array storing the coins held in a piggy bank
    int size = 0; // number of coins held in coins array
    if (PiggyBank.getBalance(coins, size) != 0) {
      System.out.println("Problem detected. Your PiggyBank.getBalance() did not "
          + "return the expected output when passed an empty piggy bank.");
      return false;
    }
    // scenario 2 - non empty piggy bank
    coins = new int[] {10, 1, 5, 25, 1, 0, 0};
    size = 5;
    if (PiggyBank.getBalance(coins, size) != 42) {
      System.out.println("Problem detected. Your PiggyBank.getBalance() did not "
          + "return the expected output when passed an piggy bank that holds "
          + "two pennies, a nickel, a dime, and a quarter.");
      return false;
    }
    // scenario 3 - another non empty piggy bank
    coins = new int[] {10, 1, 5, 25, 1, 0};
    size = 3;
    if (PiggyBank.getBalance(coins, size) != 16) {
      System.out.println("Problem detected. Your PiggyBank.getBalance() did not "
          + "return the expected output when passed an piggy bank that holds "
          + "a penny, a nickel, and a dime, only.");
      return false;
    }
    return true;
  }

  /**
   * Checks whether getSpecificCoinCount works as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean getSpecificCoinCountTestMethod() {
    // scenario 1 - empty piggy bank
    int[] coins = new int[10]; // array storing the coins held in a piggy bank
    int size = 0; // number of coins held in coins array
    int coinValue = 25; // value of specific coin
    if (PiggyBank.getSpecificCoinCount(coinValue, coins, size) != 0) {
      System.out.println("Problem detected. Your PiggyBank.getSpecificCoinCount() did not "
          + "return the expected output when passed an empty piggy bank.");
      return false;
    }
    // scenario 2 - piggy bank without specific coin
    coins = new int[] {10, 1, 5, 5, 1, 0, 0};
    size = 7;
    if (PiggyBank.getSpecificCoinCount(coinValue, coins, size) != 0) {
      System.out.println("Problem detected. Your PiggyBank.getSpecificCoinCount() did not "
          + "return the expected output when passed a piggy bank without quarters.");
      return false;
    }
    // scenario 3 - piggy bank with specific coin
    coins = new int[] {25, 25, 25, 5, 25, 1, 10};
    if (PiggyBank.getSpecificCoinCount(coinValue, coins, size) != 4) {
      System.out.println("Problem detected. Your PiggyBank.getSpecificCoinCount() did not "
          + "return the expected output when passed a piggy bank with 4 quarters.");
      return false;
    }
    return true;
  }

  /**
   * Checks whether addCoinTestMethod works as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean addCoinTestMethod() {
    // scenario 1 - non full piggy bank
    int[] coins = new int[10];
    int size = 3;
    int coin = 25;
    if (PiggyBank.addCoin(coin, coins, size) != 4) {
      System.out.println("Problem detected. Your PiggyBank.addCoin() did not "
          + "return the expected output when passed a non full piggy bank.");
      return false;
    }
    // scenario 2 - another non full piggy bank
    coins = new int[10];
    size = 7;
    coin = 10;
    if (PiggyBank.addCoin(coin, coins, size) != 8) {
      System.out.println("Problem detected. Your PiggyBank.addCoin() did not "
          + "return the expected output when passed another non full piggy bank.");
      return false;
    }
    // scenario 3 - incorrect currency value
    size = 5;
    coin = 49;
    if (PiggyBank.addCoin(coin, coins, size) != 5) {
      System.out.println("Problem detected. Your PiggyBank.addCoin() did not "
          + "return the expected output when passed an incorrect currency value.");
      return false;
    }
    // scenario 4 - full piggy bank
    coins = new int[] {1, 25, 25, 10, 10, 10, 1, 1, 1, 1};
    size = 10;
    coin = 10;
    if (PiggyBank.addCoin(coin, coins, size) != 10) {
      System.out.println("Problem detected. Your PiggyBank.addCoin() did not "
          + "return the expected output when passed a full piggy bank.");
      return false;
    }
    return true;
  }

  /**
   * Checks whether addCoinTestMethod works as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean removeCoinTestMethod() {
    // scenario 1 - piggy bank is empty
    int[] coins = new int[10];
    int size = 0;
    if (PiggyBank.removeCoin(coins, size) != 0) {
      System.out.println("Problem detected. Your PiggyBank.removeCoin() did not "
          + "return the expected output when passed an empty piggy bank.");
      return false;
    }
    // scenario 2 - piggy bank removes coin
    coins = new int[] {10, 5, 25, 5, 1, 1};
    size = 6;
    if (PiggyBank.removeCoin(coins, size) != 5) {
      System.out.println("Problem detected. Your PiggyBank.removeCoin() did not "
          + "return the expected output when passed a piggy bank containing coins.");
      return false;
    }
    // scenario 3 - another coin removed from piggy bank
    coins = new int[] {5, 5, 5, 5, 1};
    size = 5;
    if (PiggyBank.removeCoin(coins, size) != 4) {
      System.out.println("Problem detected. Your PiggyBank.removeCoin() did not "
          + "return the expected output when passed another piggy bank containing coins.");
      return false;
    }

    return true;
  }

  /**
   * Checks whether emptyPiggyBank works as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean emptyPiggyBankTestMethod() {
    // scenario 1 - empty piggy bank
    int[] coins = new int[10];
    int size = 0;
    if (PiggyBank.emptyPiggyBank(coins, size) != 0) {
      System.out.println("Problem detected. Your PiggyBank.emptyPiggyBank() did not "
          + "return the expected output when passed an empty piggy bank.");
      return false;
    }
    // scenario 2 - piggy bank contains coins
    coins = new int[] {5, 10, 25, 1, 1, 25};
    size = 6;
    if (PiggyBank.emptyPiggyBank(coins, size) != 0) {
      System.out.println("Problem detected. Your PiggyBank.emptyPiggyBank() did not "
          + "return the expected output when passed a piggy bank that contains coins.");
      return false;
    }
    // scenario 3 - another piggy bank contains coins
    coins = new int[] {5, 10, 25, 5, 1, 1, 1};
    size = 7;
    if (PiggyBank.emptyPiggyBank(coins, size) != 0) {
      System.out.println("Problem detected. Your PiggyBank.emptyPiggyBank() did not "
          + "return the expected output when passed another piggy bank that contains coins.");
      return false;
    }
    return true;
  }

  /**
   * Checks whether withdraw works as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean withdrawTestMethod() {
    // scenario 1 - empty piggy bank
    int[] coins = new int[6];
    int[] answer = new int[] {0, 0, 0, 0, 0};
    int size = 0;
    int amount = 25;
    int[] withdraw = PiggyBank.withdraw(amount, coins, size);
    for (int i = 0; i < answer.length; i++) {
      if (answer[i] != withdraw[i]) {
        System.out.println("Problem detected. Your PiggyBank.withdraw() did not "
            + "return the expected output when passed an empty piggy bank.");
        return false;
      }
    }
    // scenario 2 - not enough to withdrawal
    coins = new int[] {1, 1, 1, 1, 1, 1};
    size = 6;
    answer = new int[] {6, 0, 0, 0, 0};
    withdraw = PiggyBank.withdraw(amount, coins, size);
    for (int i = 0; i < answer.length; i++) {
      if (answer[i] != withdraw[i]) {
        System.out.println("Problem detected. Your PiggyBank.withdraw() did not "
            + "return the expected output when passed amount too big to withdraw.");
        return false;
      }
    }
    // scenario 3 - enough to withdrawal exact amount
    coins = new int[] {1, 1, 5, 5, 5, 10, 10, 25, 25};
    size = 9;
    amount = 40;
    answer = new int[] {6, 1, 1, 1, 0};
    withdraw = PiggyBank.withdraw(amount, coins, size);
    for (int i = 0; i < answer.length; i++) {
      if (answer[i] != withdraw[i]) {
        System.out.println("Problem detected. Your PiggyBank.withdraw() did not "
            + "return the expected output when passed an exact amount.");
        return false;
      }
    }
    
    // scenario 4 - enough to withdrawal not exact amount
    coins = new int[] {1, 5, 25, 25, 10};
    size = 9;
    amount = 43;
    answer = new int[] {5, 1, 1, 2, 0};
    withdraw = PiggyBank.withdraw(amount, coins, size);
    for (int i = 0; i < answer.length; i++) {
      if (answer[i] != withdraw[i]) {
        System.out.println("Problem detected. Your PiggyBank.withdraw() did not "
            + "return the expected output when passed a non exact amount.");
        return false;
      }
    }
    //another example
//    coins = new int[] {1, 1, 1, 5, 5, 10, 25, 25, 25};
//    size = 9;
//    amount = 44;
//    answer = new int[] {5, 1, 1, 2, 0};
//    withdraw = PiggyBank.withdraw(amount, coins, size);
//    for (int i = 0; i < answer.length; i++) {
//      if (answer[i] != withdraw[i]) {
//        System.out.println("Problem detected. Your PiggyBank.withdraw() did not "
//            + "return the expected output when passed a non exact amount.");
//        return false;
//      }
//    }
    return true;
  }

  /**
   * Calls the test methods implemented in this class and displays their output
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    // System.out.println("getCoinNameTest(): " + getCoinNameTestMethod());
    // System.out.println("getBalanceTest(): " + getBalanceTestMethod());
    // System.out.println("getSpecificCoinCountTest(): " + getSpecificCoinCountTestMethod());
    // System.out.println("addCoinTest(): " + addCoinTestMethod());
    // System.out.println("removeCoinTest(): " + removeCoinTestMethod());
    // System.out.println("emptyPiggyBankTest(): " + emptyPiggyBankTestMethod());
    System.out.println("withdrawTest(): " + withdrawTestMethod());
  }


}
