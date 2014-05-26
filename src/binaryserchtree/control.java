package binaryserchtree;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class control  extends JFrame{
    JTextField creatInput, insertInput, delectInput;
    control(final BinarySerchTree.window win){
        super("Control");
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension size = new Dimension((int)(screen.width*0.2), (int)(screen.height*0.8));
            this.setSize(size);
            this.setLocation(0, screen.height/2-size.height/2);
            
            JPanel creatPanel = new JPanel(), insertPanel = new JPanel(), delectPanel = new JPanel();
            
            TitledBorder creatTitle = new TitledBorder("Creat");
            creatPanel.setBorder(creatTitle);
            creatInput = new JTextField();
            JButton creatRand = new JButton("Random"),
                    creatSubmit = new JButton("Submit");
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(creatRand);
            buttonPanel.add(creatSubmit);
            creatPanel.setLayout(new BoxLayout(creatPanel, BoxLayout.Y_AXIS));
            creatPanel.add(creatInput);
            creatPanel.add(buttonPanel);
            
            TitledBorder insertTitle = new TitledBorder("Insert");
            insertPanel.setBorder(insertTitle);
            insertInput = new JTextField();
            JButton insertSubmit = new JButton("Submit");
            insertPanel.setLayout(new BoxLayout(insertPanel, BoxLayout.Y_AXIS));
            insertPanel.add(insertInput);
            insertPanel.add(insertSubmit);
            
            TitledBorder delectTitle = new TitledBorder("Delect");
            delectPanel.setBorder(delectTitle);
            delectInput = new JTextField();
            JButton delectSubmit = new JButton("Submit");
            delectPanel.setLayout(new BoxLayout(delectPanel, BoxLayout.Y_AXIS));
            delectPanel.add(delectInput);
            delectPanel.add(delectSubmit);
            
            this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
            this.getContentPane().add(creatPanel);
            this.getContentPane().add(insertPanel);
            this.getContentPane().add(delectPanel);
            
            creatRand.addMouseListener(
                new MouseAdapter(){
                    public void mousePressed(MouseEvent e){
                        int[] num = null;
                        Random ran = new Random();
                        String result = "";
                        if(isNumeric(control.this.creatInput.getText())){
                            num = new int[Integer.valueOf(control.this.creatInput.getText())];
                            for(int i=Integer.valueOf(control.this.creatInput.getText()); i>0; i--){
                                int r = ran.nextInt(30)+1;
                                num[i-1] = r;
                                result += " "+r;
                            }
                        }
                        if(num != null){
                            creatInput.setText(result);
                            BinarySerchTree.mainWin.treeRoot = new BSTcaculate(BinarySerchTree.mainWin.treeRoot).newTree(num);
                            BinarySerchTree.mainWin.page = new treePanel(BinarySerchTree.mainWin.treeRoot);
                            refresh();
                        }
                    }
            });
            
           creatSubmit.addMouseListener(
                new MouseAdapter(){
                    public void mousePressed(MouseEvent e){
                        int[] nums = null;
                        String[] numString = creatInput.getText().split(" ");
                        if(allNumeric(numString)){
                            nums = new int[numString.length];
                            for(int i=0; i<numString.length; i++)
                                nums[i] = Integer.valueOf(numString[i]);
                        }
                        if(nums != null){
                            BinarySerchTree.mainWin.treeRoot = new BSTcaculate(BinarySerchTree.mainWin.treeRoot).newTree(nums);
                            BinarySerchTree.mainWin.page = new treePanel(BinarySerchTree.mainWin.treeRoot);
                            refresh();
                        }
                    }
            });
           
           insertSubmit.addMouseListener(
                new MouseAdapter(){
                    public void mousePressed(MouseEvent e){
                        int num = -1;
                        if(isNumeric(control.this.insertInput.getText()))
                            num = Integer.valueOf(control.this.insertInput.getText());
                        if(num != -1){
                            new BSTcaculate(BinarySerchTree.mainWin.treeRoot).insertion(BinarySerchTree.mainWin.treeRoot, num);
                            BinarySerchTree.mainWin.page = new treePanel(BinarySerchTree.mainWin.treeRoot);
                            refresh();
                        }
                    }
            });
           
           delectSubmit.addMouseListener(
                new MouseAdapter(){
                    public void mousePressed(MouseEvent e){
                        int num = -1;
                        if(isNumeric(control.this.delectInput.getText()))
                            num = Integer.valueOf(control.this.delectInput.getText());
                        if(num != -1){ 
                            BinarySerchTree.mainWin.treeRoot = new BSTcaculate(BinarySerchTree.mainWin.treeRoot).delection(num);
                            BinarySerchTree.mainWin.page = new treePanel(BinarySerchTree.mainWin.treeRoot);
                            refresh();
                        }
                    }
            });
            
            this.setVisible(true);
    }
    
    void refresh(){
        BinarySerchTree.mainWin.getContentPane().removeAll();
        BinarySerchTree.mainWin.add(new JScrollPane(BinarySerchTree.mainWin.page));
        BinarySerchTree.mainWin.validate();
        BinarySerchTree.mainWin.repaint();
    }
    
    public static boolean isNumeric(String str){
        for (char c : str.toCharArray()){
            if (!Character.isDigit(c)){
                    return false;
            }
        }
        return true;
    }
    
    public static boolean allNumeric(String str[]){
        for(String s:str){
            boolean isN = isNumeric(s);
            if(!isN)
                return false;
        }
        return true;
    }
}
