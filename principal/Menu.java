package principal; //Paquete donde se encuentra la clase

//Importo las librerías
import datos.MostrarDatos;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Menu extends JFrame { //Clase Menu que extiende de JFrame
	
	private int eleccion; //Variable para guardar la eleccion del usuario

	public Menu() { //Constructor de la clase Menu
		setVisible(true); //Hace la ventana visible
		setTitle("El Último Dios: Desafío de los Tres Guerreros"); //Establece el título de la ventana
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/imagen.png")); //Establece el ícono de la ventana
		setSize(445, 292); //Establece el tamaño de la ventana
		setLocationRelativeTo(null); //Centra la ventana
		setResizable(false); //Evita que la ventana sea redimensionable
		setDefaultCloseOperation(EXIT_ON_CLOSE); //Cuando se clickea la "X" en la ventana, se detiene la ejecución
		componentes(); //Invoco el método
	}
	
	private void componentes() { //Método para configurar los componentes de la ventana
		//Invoco los métodos
		carpeta();
		archivo();

		JPanel panel = new JPanel(); //Crea un panel
		panel.setLayout(null); //Establece el layout del panel como nulo (para posicionar los componentes manualmente)
		
		// Creación de etiquetas, botones y configuración de sus propiedades (tamaño, posición, etc.)
		JLabel titulo = new JLabel("El Último Dios: Desafío de los Tres Guerreros");
		// Lo añado al panel
		panel.add(titulo);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(87, 10, 260, 33);
		
		JLabel bienvenida = new JLabel("¡BIENVENIDO!");
		panel.add(bienvenida);
		bienvenida.setHorizontalAlignment(SwingConstants.CENTER);
		bienvenida.setBounds(137, 53, 157, 51);
		
		JButton jugar = new JButton("JUGAR");
		panel.add(jugar);
		jugar.setBounds(63, 133, 136, 21);
		jugar.setBorderPainted(false);
		
		JButton verDatos = new JButton("VER DATOS");
		panel.add(verDatos);
		verDatos.setBounds(223, 133, 136, 21);
		verDatos.setBorderPainted(false);
		
		JButton borrarDatos = new JButton("BORRAR DATOS");
		panel.add(borrarDatos);
		borrarDatos.setBounds(63, 182, 136, 21);
		borrarDatos.setBorderPainted(false);
		
		JButton salir = new JButton("SALIR");
		panel.add(salir);
		salir.setBounds(223, 182, 136, 21);
		salir.setBorderPainted(false);
		
		// Agrego eventos para los botones utilizando MouseListener y ActionListener
		jugar.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				jugar.setBackground(Color.GRAY);
				jugar.setForeground(Color.BLACK);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				jugar.setBackground(Color.GREEN);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		// Configuración de acciones al hacer clic en los botones utilizando ActionListener
		verDatos.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				verDatos.setBackground(Color.GRAY);
				verDatos.setForeground(Color.BLACK);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				verDatos.setBackground(Color.BLUE);
				verDatos.setForeground(Color.WHITE);
			}
		});
		
		borrarDatos.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				borrarDatos.setBackground(Color.GRAY);
				borrarDatos.setForeground(Color.BLACK);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				borrarDatos.setBackground(Color.RED);
				borrarDatos.setForeground(Color.WHITE);
			}
		});
		
		salir.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				salir.setBackground(Color.GRAY);
				salir.setForeground(Color.BLACK);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		verDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				MostrarDatos mostrar = new MostrarDatos();
			}
		});

		borrarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				eliminar();
				eliminarCarpeta();
			}
		});
		
		jugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Datos datos = new Datos();
				dispose();
			}
		});
		
		salir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Muestra un cuadro de diálogo con un mensaje de confirmación y opciones de Sí y No.
				// El resultado de la elección se almacena en la variable "eleccion".
				eleccion = JOptionPane.showConfirmDialog(null, "¿Seguro que deseas salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
				
				if(eleccion == JOptionPane.OK_OPTION) { // Comprueba si la elección del usuario fue "Sí"
					dispose(); // Cierra la ventana actual
					System.exit(0); // Termina la ejecución del programa con un código de salida 0 (sin errores)
				}
			}
		});
		
		// Agrega el panel a la ventana
		getContentPane().add(panel);
		add(panel);
	}

	private void carpeta(){
		 Path ruta = Paths.get("./Archivo");
		 try {
			if (!(Files.exists(ruta))) {
				Files.createDirectory(ruta);
            }
        } catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se pudo crear la carpeta", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
	}

	private void archivo(){ //Método para crear archivos
		try{
			// Creación de objetos File para representar archivos en ubicaciones específicas
			File elfo = new File("./Archivo/Elfo.txt");
			File humano = new File("./Archivo/Humano.txt");
			File gato = new File("./Archivo/Gato.txt");
			File tempElfo = new File("./Archivo/tempElfo.txt");
			File tempHumano = new File("./Archivo/tempHumano.txt");
			File tempGato = new File("./Archivo/tempGato.txt");
	
			 // Verifica si los archivos no existen y los crea en caso contrario
			if(!(elfo.exists())){
			elfo.createNewFile();
			}

			if(!(humano.exists())){
			humano.createNewFile();
			}

			if(!(gato.exists())){
			gato.createNewFile();
			}
			
			if(!(tempGato.exists())){
				tempGato.createNewFile();
			}
			
			if(!(tempHumano.exists())){
				tempHumano.createNewFile();
			}
			
			if(!(tempElfo.exists())){
				tempElfo.createNewFile();
				}
		} catch(IOException e){
			// Muestra un mensaje de error en caso de que ocurra una excepción al crear archivos, y evita que se detenga la ejecución
			JOptionPane.showMessageDialog(null, "No se pudo crear los archivos", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void eliminar(){ //Método para borrar archivos
		try{
			File elfo = new File("./Archivo/Elfo.txt");
			File humano = new File("./Archivo/Humano.txt");
			File gato = new File("./Archivo/Gato.txt");
			File tempElfo = new File("./Archivo/tempElfo.txt");
			File tempHumano = new File("./Archivo/tempHumano.txt");
			File tempGato = new File("./Archivo/tempGato.txt");

			//Eliminación de los archivos
			elfo.delete();
			humano.delete();
			gato.delete();
			tempElfo.delete();
			tempHumano.delete();
			tempGato.delete();

			JOptionPane.showMessageDialog(null, "Los datos se eliminaron con éxito", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
		} catch(Exception e){
			JOptionPane.showMessageDialog(null, "No se pudo eliminar los archivos", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void eliminarCarpeta(){
		Path ruta = Paths.get("./Archivo");
		 try {
				Files.deleteIfExists(ruta);
        } catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se pudo eliminar la carpeta", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
	}
}
//CREATED BY NAHUEL TELLECHEA FREIRE