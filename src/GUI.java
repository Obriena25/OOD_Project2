import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GUI extends JFrame
{
    private JLabel label1;
    private JButton loadFile;
    private BorderLayout borderLayout1;
    private JLabel label2;

    public GUI()
    {
        super("Load Picture");
        borderLayout1 = new BorderLayout();
        setLayout(borderLayout1);
        label1 = new JLabel("Label");

        loadFile = new JButton("Load File");
        this.add(loadFile, borderLayout1.PAGE_START);
        ButtonHandler buttonHandler = new ButtonHandler();
        loadFile.addActionListener(buttonHandler);

        this.label2 = new JLabel();
        add(this.label2, borderLayout1.CENTER);

        
        //need a scaling function
        //add other functions so button doesnt take up entire row
        //use sub layouts

    }


    public File ChooseFile()
    {
        JFileChooser chooseFile = new JFileChooser(); // can put an arguement in to open file explorer to pictures
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Choose a PNG image","png");
        chooseFile.setFileFilter(filter);
        if(chooseFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File file = chooseFile.getSelectedFile();
            return file;
        }
        else
        {
            return null;
        }
    }

    public void loadImage(File file) throws IOException
    {
        BufferedImage bufImg = ImageIO.read(file);
        this.label2.setIcon(new ImageIcon(bufImg));
        
        //resize image if it is bigger than the gui
        int width = bufImg.getWidth(null);
        int height = bufImg.getHeight(null);
        int maxWidth = getWidth() - 200;
        int maxHeight = getHeight() - 200;

        if(width > maxWidth || height > maxHeight)
        {
            Image img = bufImg.getScaledInstance(maxWidth,maxHeight, Image.SCALE_DEFAULT);
            this.label2.setIcon(new ImageIcon(img));
        }
        
    }

    







    private class ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
            //String string = "";
            //if(event.getSource() == loadFile)
            //{
                //string = String.format("loadFile was pressed");
            //}

            //JOptionPane.showMessageDialog(null, "Loa");
            File f = ChooseFile();
            try {
                loadImage(f);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }

    }



    
}