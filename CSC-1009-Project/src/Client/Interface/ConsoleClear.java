// Ref https://stackoverflow.com/questions/2979383/how-to-clear-the-console

package Client.Interface;

public class ConsoleClear { // just used to clear console
    public static void clear()
    {
        try
        {
            String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else
            {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        }
        catch (final Exception e)
        {
            // Exception
            System.out.println(e.getMessage());
        }
    }
}
