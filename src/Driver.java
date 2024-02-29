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

//git config --global user.email "you@example.com"
//git config --global user.name "Your Name"