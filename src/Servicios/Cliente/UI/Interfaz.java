/*
 * IDEA ICONOS PARA SABER SI SON FILES O DIR
 */

package Servicios.Cliente.UI;

import Servicios.Cliente.ClienteFTP;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author dam
 */
public class Interfaz {
    
    private static DefaultListModel listModel = new DefaultListModel();
    private static JList list= new JList();
    private static ClienteFTP cliente;
    public static JLabel fichAct, dirAct;
    private static JFrame frame;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        cliente= new ClienteFTP();
        createAndShowUI();
    }

    public Interfaz() {
        //---------------PANEL DE LA LISTA----------------------------//
        JPanel panelList = new JPanel();
        panelList.setLayout(null);
        
        dirAct = new JLabel("Carpeta: \" / \"");        
        panelList.add(dirAct);
        dirAct.setBounds(85,30,205,10);
        
        fichAct = new JLabel();        
        panelList.add(fichAct);
        fichAct.setBounds(50,310,180,10);
        
        listModel = new DefaultListModel();
        
        cliente.realizarOP("inicio");
        
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        
        list.setCellRenderer(new MyCellRender());
        panelList.add(new JScrollPane(list));        
        
        list.addMouseListener(new Listeners(cliente)); 

        list.setVisibleRowCount(5);
        
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 250));
        
        scrollPane.setBounds(50,50,190,250);
        
        panelList.add(scrollPane);
        
        JButton atras=new RoundJButton("");
        atras.setIcon(new ImageIcon(getClass().getResource("Imagenes/atras.png")));
        atras.setBounds(55,25,20,20);
        atras.setName("atras");
        atras.addMouseListener(new Listeners());
        panelList.add(atras);
        
        frame.add(panelList);
        
        //----------------------PANEL DE BOTONES------------------//
        
        JPanel panelButton = new JPanel();
        panelButton.setLayout(null);
        
        JButton subir = new JButton("Upload");
        subir.setIcon(new ImageIcon(getClass().getResource("Imagenes/subir.png")));
        subir.setName("upload");
        panelButton.add(subir);
        subir.setBounds(55,50,130, 25);
        subir.addMouseListener(new Listeners());
        
        JButton bajar = new JButton("Download");
        bajar.setIcon(new ImageIcon(getClass().getResource("Imagenes/bajar.png")));
        panelButton.add(bajar);
        bajar.setName("download");
        bajar.setBounds(55,125,130, 25);     
        bajar.addMouseListener(new Listeners());
        
        JButton mkdir = new JButton("Create File");
        mkdir.setIcon(new ImageIcon(getClass().getResource("Imagenes/crearD.png")));
        mkdir.setName("mkdir");
        panelButton.add(mkdir);
        mkdir.setBounds(55,200,130, 25); 
        mkdir.addMouseListener(new Listeners());
        
        JButton delD = new JButton("Delete File");
        delD.setIcon(new ImageIcon(getClass().getResource("Imagenes/delD.png")));
        delD.setName("rmdir");
        panelButton.add(delD);
        delD.setBounds(55,275,130, 25);
        delD.addMouseListener(new Listeners());        
        
        frame.add(panelButton);
    }

    private static void createAndShowUI() {
        frame = new JFrame("FileChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        frame.setContentPane(container);   
        
        new Interfaz();
               
        frame.pack();
        
        frame.setSize(500,400);
        frame.setLocationRelativeTo(null);   
        frame.setResizable(false);
        
        frame.setVisible(true);
    }
    
    public static void setList(ArrayList<String> listita){
        listModel.clear();
        for (int i=0; i<listita.size();i++){
            listModel.addElement(listita.get(i));            
        }
        
        list.setSelectedIndex(0);
        
        if(list.getSelectedValue()!=null){
            fichAct.setText("Fichero actual: " + ((String) list.getSelectedValue()).substring(1));
        }
    }
}
