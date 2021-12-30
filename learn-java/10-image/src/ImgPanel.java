// Ausfuehrung: java ImgPanel img_file transparency num1 num2 ...
// Beispiel: java ImgPanel yoghurt.png 150 50 40 35 50 35

import java.awt.*;
import java.awt.event.*;

public class ImgPanel extends Component
{

    private int IMG_WIDTH = 800;
    private int IMG_HEIGHT = 600;
    private int RADIUS = 300;

    private Image img;
    private int[] nums;
    private int trans;

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(IMG_WIDTH + 100, IMG_HEIGHT + 100);
    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(img, 50, 50, IMG_WIDTH, IMG_HEIGHT, this); // Skalierung

        int[][] color = {{251, 212, 229}, {253, 208, 177}, {255, 241, 166}, {180, 249, 165}, {158, 231, 245}, {224, 205, 255}};

        int pos = 0;

        for (int i = 0; i < nums.length; i++) {
            int j = i % 6;
            int r = color[j][0];
            int gr = color[j][1];
            int b = color[j][2];
            Color c = new Color(r, gr, b, trans);
            c.getAlpha();
            g.setColor(c);
            g.fillArc(250, 200, RADIUS, RADIUS, pos, nums[i]);
            pos += nums[i];
        }

    }

    // Constructor
    public ImgPanel(Image img, int trans, int[] nums)
    {
        this.img = img;
        this.trans = trans;
        this.nums = nums;
        MediaTracker MT = new MediaTracker(this);
        MT.addImage(img, 1);
        try { MT.waitForID(1); }
        catch (Exception e) {}
    }

    public static void main(String[] args){
        if (args.length < 3) {
            System.out.println("Bitte Werte eingeben");
            System.exit(1);
        }
        try {
            Frame F = new Frame("ImagePanel");
            Image img = F.getToolkit().getImage(args[0]);
            int trans = Integer.parseInt(args[1]);

            // Werte von Kommandozeile fuer Tortendiagramm
            int len = args.length - 2;
            int[] nums = new int[len];
            for (int i = 0; i < len; i++) {
                nums[i] = Integer.parseInt(args[i+2]);
            }

            ImgPanel P = new ImgPanel(img, trans, nums);
            F.add(P);
            F.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent we) {System.exit(0);}
            });
            F.setVisible(true);
            F.pack();
        }
        catch (Exception e) {}
    }
}
