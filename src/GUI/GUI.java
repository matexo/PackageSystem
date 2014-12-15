/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import car.Car;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    
    public JLabel [] labels;
    
    public LogInfo log;
    
    public Logic logic;
    
    public int carSize = 1;
    public int carQuantity = 10;
    public int startingPosition = 0;
    
    public GUI() throws FileNotFoundException
    {
    map = Parser.parseMap("map.txt");
    map.generatePosition();
    map = Parser.parseConnection("connections.txt", map);
    pack= new PriorityQueue<>();
    pack = Parser.parsePackage("packages.txt"); 
        
    logic = new Logic(map , pack , carQuantity , carSize , startingPosition);
    logic.run();
    //logic.writeLogs();
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
    nextStep.setBounds(610 , 20 , 150 , 50);
    nextStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
                {
                map.clearColor();
                log = logic.getLog();
                if(log==null) return;
                logger.setText(logic.writeLog(log));
                if(log.status == false)
                    map.getCity(log.city).setColor(cars[log.carId].getColor());
                else {
                     map.getCity(log.city).setColor(cars[log.carId].getColor());
                     map.getCity(log.pack.getDestinationCityId()).setColor(cars[log.carId].getColor());
                        ArrayList<Integer> block = new ArrayList<>();
                        int x=map.getCity(log.pack.getDestinationCityId()).getCityId();
                        block.add(x);
                        while(x!=-1)
                            {
                            x = map.getCity(log.city).getDist().previus[x];
                            block.add(x);
                            }
                        for(int i =0;i<block.size()-2;i++)
                            {
                            map.getCity(block.get(i)).getConnectionByDestinationId(block.get(i+1)).setColor(cars[log.carId].getColor());
                            map.getCity(block.get(i+1)).getConnectionByDestinationId(block.get(i)).setColor(cars[log.carId].getColor());
                            }
                        }
                painter.map = map;
                painter.repaint();

                }
        });
    add(nextStep);
    labels = new JLabel[carQuantity];
    for(int i=0;i<carQuantity;i++)
        {  
        labels[i] = new JLabel("Samochód nr:" + i);
        labels[i].setForeground(cars[i].getColor());
        labels[i].setBounds(600  , 100 + i*20 , 100 , 50);
        add(labels[i]);
        }
    }
    
    private void initFrame()
    {
        setSize(800, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Wizualizacja");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

