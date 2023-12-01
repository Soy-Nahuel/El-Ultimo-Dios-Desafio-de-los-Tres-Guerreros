package principal;

//Importo las librerias
import juego.Humano;
import juego.Elfo;
import juego.Gato;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Toolkit;

public class Datos extends JFrame { //Creo la clase "Datos" y hereda de JFrame
	
	// Variables para almacenar datos del jugador
	private String Nombre, Tipo, Lugar;
	private int eleccion;
	// Descripciones de los personajes
	private final  String desGato = "El Gato, cuyo origen se desconoce. \nEs un personaje bastante fuerte de \n155 puntos, puede utilizar sus \npoderes gritando 2 veces, "
									+ "además \nposee una vida de 150 puntos";


	private final String desHumano = "El Humano cuyo nombre se \ndesconoce. Tiene una fuerza de \n135 puntos, puede utilizar sus \npoderes gritando 1 única vez, "
									+ "\nademás posee una vida de 100 \npuntos";

	private final String desElfo = "El Elfo, una criatura nacida en el \nbosque con sus Ancestros. Tiene \nuna fuerza débil de 80 puntos, "
									+ "\npuede utilizar sus poderes gritando \n4 veces heredadas de sus Dioses \ny Ancestros. Además posee una \nvida de 130 puntos";

	// Constructor de la clase Datos
	public Datos() {
		// Configuración de la ventana
        // Creación y configuración de elementos de interfaz gráfica
        // Llamada al método componentes para agregar componentes a la ventana
		setVisible(true);
		setTitle("Jugador");
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/imagen.png"));
		setSize(470, 361);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		componentes();
	}
	
	private void componentes() { // Método para agregar componentes a la ventana
		// Creación y configuración de elementos de la interfaz gráfica:
        // Etiquetas, campos de texto, botones, áreas de texto, etc.
        // Configuración de listeners para eventos como teclas presionadas, clics de botones, etc.
        // Definición de acciones para los botones (borrar, volver, jugar, ver)
        // Agregar los componentes al panel y a la ventana
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel elige = new JLabel("ELIJE A TU JUGADOR");
		panel.add(elige);
		elige.setHorizontalAlignment(SwingConstants.CENTER);
		elige.setBounds(131, 10, 167, 30);
		
		JLabel nombre = new JLabel("Nombre");
		panel.add(nombre);
		nombre.setBounds(10, 69, 64, 27);
		
		JTextField entradaNombre = new JTextField();
		panel.add(entradaNombre);
		entradaNombre.setBounds(64, 73, 133, 19);
		
		JLabel tipo = new JLabel("Tipo");
		panel.add(tipo);
		tipo.setHorizontalAlignment(SwingConstants.LEFT);
		tipo.setBounds(10, 130, 64, 27);
		
		JLabel lugar = new JLabel("Lugar");
		panel.add(lugar);
		lugar.setHorizontalAlignment(SwingConstants.LEFT);
		lugar.setBounds(10, 189, 64, 27);
		
		final String[] tipos = {"Gato", "Humano", "Elfo"};
		
		JComboBox tipoEntrada = new JComboBox(tipos);
		panel.add(tipoEntrada);
		tipoEntrada.setBounds(64, 133, 133, 21);
		
		final String[] lugares = {"Ciudad Solitaria", "Ciudad de Hongos", "Ciudad de Carrera Blanca"};
		
		JComboBox lugarEntrada = new JComboBox(lugares);
		panel.add(lugarEntrada);
		lugarEntrada.setBounds(64, 192, 133, 21);
		
		JButton borrar = new JButton("BORRAR");
		panel.add(borrar);
		borrar.setBounds(117, 251, 95, 21);
		borrar.setBorderPainted(false);
		
		JTextArea datosPersonaje = new JTextArea();
		panel.add(datosPersonaje);
		datosPersonaje.setEditable(false);
		datosPersonaje.setBounds(245, 50, 200, 264);
		
		JButton jugar = new JButton("JUGAR");
		panel.add(jugar);
		jugar.setBounds(10, 251, 95, 21);
		jugar.setBorderPainted(false);
		
		JButton volver = new JButton("VOLVER");
		panel.add(volver);
		volver.setBounds(117, 282, 95, 21);
		volver.setBorderPainted(false);
		
		JButton ver = new JButton("VER");
		panel.add(ver);
		ver.setBounds(10, 282, 95, 21);
		ver.setBorderPainted(false);
		
		entradaNombre.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				entradaNombre.setBorder(new LineBorder(Color.ORANGE, 2));
			}
		
			@Override
			public void keyReleased(KeyEvent e) {
				entradaNombre.setBorder(new LineBorder(Color.WHITE, 2));
			}
		
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		
		borrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				entradaNombre.setText("");
				datosPersonaje.setText("");
			}
		});
		
		volver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eleccion = JOptionPane.showConfirmDialog(null, "¿Seguro que deseas entrar a esta opción? Es posible que no se guarden los datos", "CONFIRMACIÓN", JOptionPane.OK_OPTION);
				
				if(eleccion == JOptionPane.OK_OPTION) {
					Menu menu = new Menu();
					menu.setVisible(true);
					dispose();
				}
			}
		});
		
		jugar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Nombre = entradaNombre.getText();
				
				if(Nombre.equals("")) {
					JOptionPane.showMessageDialog(null, "El campo \"Nombre\" está vacío", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
				} else {
					Tipo = (String) tipoEntrada.getSelectedItem();
					Lugar = (String) lugarEntrada.getSelectedItem();
		
					establecerValores(Nombre, Tipo, Lugar);
					eleccion();
				}
			}
		});
		

		ver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Tipo = (String) tipoEntrada.getSelectedItem();

				switch(Tipo){
					case "Gato":
					datosPersonaje.setText(desGato);
					break;

					case "Humano":
					datosPersonaje.setText(desHumano);
					break;

					case "Elfo":
					datosPersonaje.setText(desElfo);
					break;
				}
			}
		});
		
		getContentPane().add(panel);
		add(panel);
	}

	public void establecerValores(String nombre, String tipo, String lugar) {  // Método para establecer los valores del jugador
		 // Asignación de valores a los atributos de la clase
		this.Nombre = nombre;
		this.Tipo = tipo;
		this.Lugar = lugar;
	}
	
	 // Método para gestionar la elección del personaje
	private void eleccion() {
		 // Creación de un objeto del tipo de personaje seleccionado
        // Según el tipo seleccionado, se crea un objeto de Gato, Humano o Elfo y se cierra la ventana actual
		switch(Tipo) {
		case "Gato":
			Gato gato = new Gato(Nombre, Lugar);
			dispose();
			break;
			
		case "Humano":
			Humano humano = new Humano(Nombre, Lugar);
			dispose();
			break;
			
		case "Elfo":
			Elfo elfo = new Elfo(Nombre, Lugar);
			dispose();
			break;
		}
	}
	
}
//CREATED BY NAHUEL TELLECHEA FREIRE