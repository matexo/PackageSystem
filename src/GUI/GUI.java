/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import car.Car;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFrame;
import logic.Logic;
import map.Map;
import parser.Parser;
import priorityQueue.LogInfo;
import priorityQueue.Pack;
import priorityQueue.PriorityQueue;

/**
 *
 * @author Matexo
 */
public class GUI extends JFrame{
    public Map map;
    public Car cars[];
    public PriorityQueue<Pack> pack;
    public Painter painter;
    
    public TextField logger;
    public JButton nextStep;
    
    public LogInfo log;
    
    public Logic logic;
    
    public GUI() throws FileNotFoundException
    {
    map = Parser.parseMap("map.txt");
    map.generatePosition();
    map = Parser.parseConnection("connections.txt", map);
    pack= new PriorityQueue<>();
    pack = Parser.parsePackage("packages.txt"); 
        
    logic = new Logic(map , pack , 3 , 1 , 0);
    logic.run();
    initFrame();
    
    cars = logic.getCars();
    
    
    painter = new Painter(map , cars);
    painter.setBounds(0, 0, 600, 600);
    add(painter);
    
    logger = new TextField();
    logger.setBounds(10 , 600 , 600 , 30);
    logger.setEditable(false);
    add(logger);
    
    nextStep = new JButton("Następny krok");
    nextStep.setBounds(800 , 20 , 150 , 50);
    nextStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
                {
                log = logic.getLog();
                logger.setText(logic.writeLog(log));
                
                }
        });
    add(nextStep);
        
    }
    
    private void initFrame()
    {
        setSize(1000, 851);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Wizualizacja");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

