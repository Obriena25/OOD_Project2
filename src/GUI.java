import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

public class GUI extends JFrame implements ItemListener
{
    private JLabel label1;
    private JButton loadFile;
    private BorderLayout borderLayout1;
    private GridLayout gridLayout1;
    private CardLayout cardLayout1;
    private JLabel label2;
    private JButton saveImage;
    private JButton greyScale;
    private JComboBox filterBox;
    private static final String[] labels = {"Choose Filter", "GreyScale", "Inverted Colors", "Mirrored", "Blur"};
    private JPanel cards;
    private final static String NOFUNCTIONPANEL = "Choose Filter";
    private final static String GREYSCALEPANEL = "GreyScale";
    private final static String INVERTCOLORPANEL = "Inverted Colors";
    private final static String MIRRORPANEL = "Mirrored";
    private final static String BLURPANEL = "Blur";

    public GUI() 
    {
        super("Load Picture");
        borderLayout1 = new BorderLayout();
        gridLayout1 = new GridLayout(1,3,5,5); 
        setLayout(borderLayout1);
        JPanel buttonPanel = new JPanel(gridLayout1);
      


        JPanel comboBoxPanel = new JPanel();
        String comboBoxItems[] = {NOFUNCTIONPANEL, GREYSCALEPANEL, INVERTCOLORPANEL, MIRRORPANEL, BLURPANEL};
        filterBox = new JComboBox<>(comboBoxItems);
        filterBox.setEditable(false);
        filterBox.addItemListener(this);
        comboBoxPanel.add(filterBox);

        JPanel card1 = new JPanel();
        card1.add();
        JPanel card2 = new JPanel();
        card2.add();
        JPanel card3 = new JPanel();
        card3.add();
        JPanel card4 = new JPanel();
        card4.add();
        JPanel card5 = new JPanel();
        card5.add();
        
        // filterBox = new JComboBox(labels);
        // filterBox.setMaximumRowCount(4);
        // filterBox.addItemListener(new ItemListener() 
        // {
        //     public void itemStateChanged(ItemEvent event)
        //     {
        //         if (event.getStateChange() == ItemEvent.SELECTED)
        //             label1.setText(labels[filterBox.getSelectedIndex()]);
        //     }
        // }
        // );



        loadFile = new JButton("Load File");
        ButtonHandler buttonHandler = new ButtonHandler();
        loadFile.addActionListener(buttonHandler);

        saveImage = new JButton("Save Image");
        ButtonHandler buttonHandler2 = new ButtonHandler();
        saveImage.addActionListener(buttonHandler2);




        buttonPanel.add(loadFile);
        buttonPanel.add(saveImage);
        buttonPanel.add(filterBox);
        label1 = new JLabel(labels[0]);
        this.add(buttonPanel, borderLayout1.PAGE_START);

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

    
    public void saveImage()
    {
        JFileChooser saveFile = new JFileChooser();
        int x = saveFile.showSaveDialog(null);
        if(x == JFileChooser.APPROVE_OPTION)
        {
            //this.label2.setText(saveFile.getSelectedFile().getAbsolutePath());
        }
        //check to see if file exists
            //show confirm dialog
            //if statement to see if user selected yes
    }

    public void greyScale(File file)
    {
        BufferedImage bufImg= null;
        

        try
        {
            bufImg = ImageIO.read(file);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        int width = bufImg.getWidth();
        int height = bufImg.getHeight();
        int[] pixels = bufImg.getRGB(0, 0, width, height, null, 0, width);

        for (int i = 0; i < pixels.length; i++)
        {
            int p = pixels[i];
            int a = (p >> 24) & 0xff;
            int r = (p >> 16) & 0xff;
            int g = (p >> 8) & 0xff;
            int b = p & 0xff;

            int avg = (r + g + b) / 3;

            p = (a << 24) | (avg << 16) | (avg << 8) | avg;

            pixels[i] = p;
        }
        bufImg.setRGB(0,0, width, height, pixels, 0, width);

    
    }

    public void invertColors()
    {

    }

    public void invertYAxis()
    {

    }

    public void blurImage()
    {

    }

    public void draw()
    {

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
                //greyScale(f);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }

    }

    // private class GreyScaleHandler implements ActionListener
    // {
    //     @Override
    //     public void actionPerformed(ActionEvent event)
    //     {
    //         greyScale();
    //     }
    // }


    
}