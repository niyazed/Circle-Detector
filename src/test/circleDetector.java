package test;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Sys;



public class circleDetector extends JFrame{
    
	JButton openButton, detectButton ;
    JLabel openLabel, detectLabel;
    
    public circleDetector()
    {
    	
	    super("Circle Detector");
	    openButton = new JButton("Open Image");
	    openButton.setBounds(127, 414, 159, 48);
	    openButton.setForeground(new Color(255, 255, 255));
		openButton.setBackground(new Color(46, 139, 87));
		
	    detectButton = new JButton("Double Click to Detect Image");
	    detectButton.setBounds(700, 414, 175, 48);
	    detectButton.setForeground(new Color(255, 255, 255));
		detectButton.setBackground(new Color(46, 139, 87));
		
	    openLabel = new JLabel(" ");
	    openLabel.setBounds(31,63,355,250);
	    
	    detectLabel = new JLabel(" ");
	    detectLabel.setBounds(600,66,355,250);
	    
	    
	    
	    add(openButton);
	    add(openLabel);
	    
	    add(detectButton);
	    add(detectLabel);
	    
	    openButton.addActionListener(new ActionListener() {
	
	        public void actionPerformed(ActionEvent e) {
	        
	          JFileChooser file = new JFileChooser();
	          file.setCurrentDirectory(new File(System.getProperty("user.dir")));
	          //filter the files
	          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","jpeg","png");
	          file.addChoosableFileFilter(filter);
	          int result = file.showSaveDialog(null);
	           //if the user click on save in Jfilechooser
	          if(result == JFileChooser.APPROVE_OPTION){
	              File selectedFile = file.getSelectedFile();
	              String path = selectedFile.getAbsolutePath();
	              //System.out.print(selectedFile.getName());
	              openLabel.setIcon(ResizeImage(path));
	              openLabel.setText(selectedFile.getName());
	              
	              //System.out.println(path);
	              //System.out.println(System.getProperty("user.dir"));
	              //copy image
	              
	              
	              
	          }
	           //if the user click on save in Jfilechooser
	
	          
	          else if(result == JFileChooser.CANCEL_OPTION){
	              System.out.println("No File Select");
	          }
	        }
	    });
	    
	    detectButton.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				
				
				String arg1 = openLabel.getText();
				
				try {
						Process p = Runtime.getRuntime().exec("python detect.py "+ arg1.toString());
						
					} 
				  
				  catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				//BufferedImage image = null;
				File file = new File("output.png");
				String path = file.getAbsolutePath();
				detectLabel.setIcon(ResizeImage(path));
				
				
			}
		});
	    
		
	    
	    setLayout(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setLocation(400,300);
	    setSize(1000,600);
	    setVisible(true);
	    setResizable(false);
    }
     
     // Methode to resize imageIcon with the same size of a Jlabel
    public ImageIcon ResizeImage(String ImagePath)
    {
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image img = MyImage.getImage();
        Image newImg = img.getScaledInstance(openLabel.getWidth(), openLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    
    
    
    public static void main(String[] args){
        new circleDetector();
        
    }
   }
