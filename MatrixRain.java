
import java.util.Random;

public class MatrixRain {

    private static final int WIDTH = 120;
    private static final int HEIGHT = 40;
    private static final int DELAY = 50;
    private static final String HEAD_COLOR = "\033[97m";
    private static final String TRAIL_COLOR = "\033[32m";
    public static void main(String[] args) throws Exception {

        Random random = new Random();
        int[] drops = new int[WIDTH];

        for (int i = 0; i < WIDTH; i++) {
            drops[i] = random.nextInt(HEIGHT);
        }

        // Clear screen
        System.out.print("\033[2J");
        System.out.print("\033[?25l"); // Hide cursor

        while (true) {

            for (int x = 0; x < WIDTH; x++) {

                int y = drops[x];

                // Bright head
                moveCursor(y, x + 1);
                System.out.print(HEAD_COLOR+ randomChar(random));

                // Green trail
                if (y > 1) {
                    moveCursor(y - 1, x + 1);
                    System.out.print(TRAIL_COLOR + randomChar(random));
                }

                if (y > 2) {
                    moveCursor(y - 2, x + 1);
                    System.out.print(TRAIL_COLOR + randomChar(random));
                }

                // Erase tail
                if (y > 8) {
                    moveCursor(y - 8, x + 1);
                    System.out.print(" ");
                }

                drops[x]++;

                if (drops[x] > HEIGHT + random.nextInt(20)) {
                    drops[x] = 0;
                }
            }

            System.out.flush();
            Thread.sleep(DELAY);
        }
    }

    private static void moveCursor(int row, int col) {
        System.out.printf("\033[%d;%dH", row, col);
    }

    private static char randomChar(Random random) {
        String chars =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                        "0123456789" +
                        "!@#$%^&*()[]{}<>?/";

        return chars.charAt(random.nextInt(chars.length()));
    }
}
