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
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    public ArrayList<LogInfo> logs;
    public LogInfo log;
    public Logic logic;
    
    public int index;
    public int carSize;
    public int carQuantity;
    public int startingPosition;
    
    public TextField logger;
    public JButton nextStep;
    public JButton restart;
    public JButton logWindow;
    public JLabel [] labels;
           
    public GUI() throws FileNotFoundException
    {
    loadOptions();
    map.generatePosition();
    logs = new ArrayList<>();
    index=0;
        
    logic = new Logic(map , pack , carQuantity , carSize , startingPosition);
    logic.run();
    logs = logic.getLogs();
    cars = logic.getCars();

    initFrame();
    
    painter = new Painter(map , cars);
    painter.setBounds(0, 0, 600, 600);
    add(painter);
    
    logger = new TextField();
    logger.setBounds(10 , 600 , 600 , 30);
    logger.setEditable(false);
    add(logger);
    
    nextStep = new JButton("Następny krok");
    nextStep.setBounds(610 , 20 , 150 , 30);
    nextStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
                {
                map.clearColor();
                if(index == logs.size()) return;
                log = logs.get(index);
                index++;
                
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
    
    restart = new JButton("Restart");
    restart.setBounds(610, 60, 150, 30);
    restart.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
        map.clearColor();
        painter.repaint();
        index=0;
        logger.setText("");
        }
    });
    add(restart);
    
    logWindow = new JButton("Wyświetl logi");
    logWindow.setBounds(610, 100, 150, 30);
    logWindow.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null , logic.writeLogs());
        }
    });
    add(logWindow);

    labels = new JLabel[carQuantity];
    for(int i=0;i<carQuantity;i++)
        {  
        labels[i] = new JLabel("Samochód nr:" + i);
        labels[i].setForeground(cars[i].getColor());
        labels[i].setBounds(600  , 200 + i*20 , 100 , 50);
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
    
    private void loadOptions() throws FileNotFoundException
    {
    BufferedReader reader = new BufferedReader(new FileReader("options.txt"));
    String buffer;
    try 
        {
        if ((buffer = reader.readLine()) != null)
            {
            String[] tmp = buffer.split("\\s+");
            try 
                {
                map = Parser.parseMap(tmp[0]);
                map = Parser.parseConnection(tmp[1], map);
                pack= new PriorityQueue<>();
                pack = Parser.parsePackage(tmp[2]);
                carQuantity = Integer.parseInt(tmp[3]);
                carSize = Integer.parseInt(tmp[4]);
                startingPosition = Integer.parseInt(tmp[5]);
                }
            catch (ArrayIndexOutOfBoundsException e)
                {
                System.err.println("Zignorowana linia \"" + buffer + "\" - zbyt mało pól");
                }
            catch (NumberFormatException e)
                {
                System.err.println("Zignorowana linia \"" + buffer + "\" - \"" + "\" nie zawiera liczb naturalnych na 4, 5 lub 6 pozycji");
                }
            }   
        }
    catch (IOException e)
        {
        System.err.println(e.getMessage());
        }

    }  
    
}

