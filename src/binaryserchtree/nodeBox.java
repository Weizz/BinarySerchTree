package binaryserchtree;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class nodeBox extends JButton{
    public nodeBox left = null,
                   right = null;
    
    nodeBox(int data, Point loc){
        super(Integer.toString(data));
        setSize(70, 50);
        setLocation(loc);
        
        addMouseListener(
                new MouseAdapter(){
                    public void mousePressed(MouseEvent e){
                        
                    }
            });
    }
}
