import javax.swing.JFrame;

public class Driver
{
    public static void main (String[] args)
    {
        GUI gui = new GUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(1100, 900);
        gui.setVisible(true);
    }
}

