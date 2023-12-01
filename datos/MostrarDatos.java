package datos;

//Importo las librerias
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MostrarDatos extends JFrame{ // Creo la clase MostrarDatos que hereda de JFrame

	// Variable de JTextField para mostrar los datos de los archivos
    private JTextArea area;

    public MostrarDatos(){ // Constructor de la clase MostrarDatos
    	// Configuración inicial de la ventana
        setVisible(true);
        setIconImage(Toolkit.getDefaultToolkit().getImage("./img/imagen.png"));
        setSize(450, 361);
        setTitle("Datos de \"Gato\", \"Elfo\" y \"Humano\"");
        setLocationRelativeTo(null);
        setResizable(false);
        componentes(); //Invoco los métodos
    }

    private void componentes(){  // Método para configurar los componentes de la interfaz gráfica
    	// Creación del panel principal con diseño nulo
        JPanel panel = new JPanel();
        panel.setLayout(null);

     // Creación del área de texto y botones, y su configuración en el panel
        area = new JTextArea();
        panel.add(area);
        area.setEditable(false);
        area.setBounds(0, 0, 300, 400);

        JButton textoGato = new JButton("GATO");
        panel.add(textoGato);
        textoGato.setBounds(320, 50, 100, 20);

        JButton textoElfo = new JButton("ELFO");
        panel.add(textoElfo);
        textoElfo.setBounds(320, 100, 100, 20);

        JButton textoHumano = new JButton("HUMANO");
        panel.add(textoHumano);
        textoHumano.setBounds(320, 150, 100, 20);

        JButton salir = new JButton("SALIR");
        panel.add(salir);
        salir.setBounds(320, 200, 100, 20);

        // Asignación de acciones a los botones al hacer click
        // Cada botón invoca a un método específico al ser presionado
        textoGato.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                txtGato();
            }
        });

        textoElfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                txtElfo();
            }
        });

        textoHumano.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                txtHumano();
            }
        });

        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });

        // Agrega el panel al contenido de la ventana
        getContentPane().add(panel);
		add(panel);
    }

    private void txtGato() { // Método para mostrar el contenido del archivo Gato.txt en el área de texto
    	// Lee y muestra el contenido del archivo Gato.txt en el área de texto
        // Manejo de excepciones en caso de no encontrar el archivo
        FileReader entrada = null;
        BufferedReader mibuffer = null;
    	try {
            entrada = new FileReader("./Archivo/Gato.txt");
            mibuffer = new BufferedReader(entrada);
    
            StringBuilder textoCompleto = new StringBuilder();
            String linea = "";
    
            while ((linea = mibuffer.readLine()) != null) {
                textoCompleto.append(linea).append("\n");
            }
    
            area.setText(textoCompleto.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo", "ERROR", JOptionPane.ERROR_MESSAGE);
        }finally{
            try {
                mibuffer.close();
                entrada.close();
            } catch (IOException e) {
            }
        }
    }

    private void txtElfo(){ // Método para mostrar el contenido del archivo Elfo.txt en el área de texto
    	// Lee y muestra el contenido del archivo Elfo.txt en el área de texto
        // Manejo de excepciones en caso de no encontrar el archivo
        FileReader entrada = null;
        BufferedReader mibuffer = null;
         try {
            entrada = new FileReader("./Archivo/Elfo.txt");
            mibuffer = new BufferedReader(entrada);
    
            StringBuilder textoCompleto = new StringBuilder();
            String linea = "";
    
            while ((linea = mibuffer.readLine()) != null) {
                textoCompleto.append(linea).append("\n");
            }
    
            area.setText(textoCompleto.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo", "ERROR", JOptionPane.ERROR_MESSAGE);
        }finally{
             try {
                mibuffer.close();
                entrada.close();
            } catch (IOException e) {
            }
        }
    }

    private void txtHumano(){ // Método para mostrar el contenido del archivo Humano.txt en el área de texto
    	// Lee y muestra el contenido del archivo Humano.txt en el área de texto
        // Manejo de excepciones en caso de no encontrar el archivo
        FileReader entrada = null;
        BufferedReader mibuffer = null;
         try {
            entrada = new FileReader("./Archivo/Humano.txt");
            mibuffer = new BufferedReader(entrada);
    
            StringBuilder textoCompleto = new StringBuilder();
            String linea = "";
    
            while ((linea = mibuffer.readLine()) != null) {
                textoCompleto.append(linea).append("\n");
            }
            area.setText(textoCompleto.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo", "ERROR", JOptionPane.ERROR_MESSAGE);
        }finally{
             try {
                mibuffer.close();
                entrada.close();
            } catch (IOException e) {
            }
        }
    }
    
}
//CREATED BY NAHUEL TELLECHEA FREIRE