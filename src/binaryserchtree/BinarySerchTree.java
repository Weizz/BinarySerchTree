package binaryserchtree;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class BinarySerchTree {
    public static window mainWin = null;

    public static void main(String[] args) {
        new control(mainWin);
        mainWin = new window();
    }
    
    public static class window extends JFrame{
        public treePanel page;
        public treeNode treeRoot = null;

        window(){
            super("BinarySerchTree");
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension size = new Dimension((int)(screen.width*0.6), (int)(screen.height*0.8));
            this.setSize(size);
            this.setLocation(screen.width/2-size.width/2, screen.height/2-size.height/2);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            page = new treePanel(null);
            this.getContentPane().add(new JScrollPane(page));
            
            this.setVisible(true);
        }
    } 
}
