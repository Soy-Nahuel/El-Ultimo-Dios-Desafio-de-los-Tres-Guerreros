package bienvenida;

//Importo las librerias
import principal.Menu; // Importa la clase Menu desde el paquete principal
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BienvenidaJuego extends JFrame { // Creo la clase BienvenidaJuego que hereda de JFrame

	//Declaro variable tipo JLabel y JTextField
	private JLabel carga1;
	private JLabel carga2;
	private JLabel carga3;
	private JTextField porcentaje;

	public BienvenidaJuego() { // Constructor de la clase BienvenidaJuego
		setTitle("El Último Dios: Desafío de los Tres Guerreros"); // Establece el título de la ventana
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/imagen.png")); // Establece la imagen del ícono de la ventana
		setResizable(false); // Hace que la ventana no sea redimensionable
		setSize(502, 540); // Establece el tamaño de la ventana
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Establece la operación por defecto al cerrar la ventana, o sea, se detiene la ejecución al clickear la "X"
		setLocationRelativeTo(null); // Hace que la ventana aparezca en el centro de la pantalla
		componentes(); //Invoco el método
	}

	private void componentes() { // Método para configurar los componentes de la ventana
		JPanel panel = new JPanel(); // Crea un panel
		panel.setLayout(null); // Establece el diseño del panel como nulo (permite colocar componentes en coordenadas específicas)

		// Creación de etiquetas, botones, etc y configuración de sus propiedades (tamaño, posición, etc.)
		JLabel titulo = new JLabel("El Último Dios");
		panel.add(titulo); //Lo añado al panel
		titulo.setBounds(93, 110, 338, 114);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titulo.setForeground(Color.BLACK);
		
		JLabel titulo2 = new JLabel("Desafío de los Tres Guerreros");
		panel.add(titulo2);
		titulo2.setBounds(50, 150, 400, 114);
		titulo2.setHorizontalAlignment(SwingConstants.CENTER);
		titulo2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titulo2.setForeground(Color.BLACK);

		carga1 = new JLabel();
		panel.add(carga1);
		carga1.setBounds(33, 374, 132, 25);
		carga1.setOpaque(true);
		carga1.setBackground(new Color(0, 0, 0, 0));
		carga2 = new JLabel();
		panel.add(carga2);
		carga2.setBounds(162, 374, 132, 25);
		carga2.setOpaque(true);
		carga2.setBackground(new Color(0, 0, 0, 0));

		carga3 = new JLabel();
		panel.add(carga3);
		carga3.setBounds(292, 374, 156, 25);
		carga3.setOpaque(true);
		carga3.setBackground(new Color(0, 0, 0, 0));

		JLabel cargando = new JLabel("CARGANDO:");
		panel.add(cargando);
		cargando.setBounds(120, 335, 87, 29);
		cargando.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cargando.setForeground(Color.WHITE);

		porcentaje = new JTextField();
		panel.add(porcentaje);
		porcentaje.setBounds(206, 340, 96, 19);
		porcentaje.setOpaque(false);
		porcentaje.setBorder(null);
		porcentaje.setEditable(false);
		porcentaje.setForeground(Color.WHITE);
		porcentaje.setFont(new Font("Tahoma", Font.PLAIN, 14));

		ImageIcon imagen = new ImageIcon("./img/imagen.png"); //Carga una imagen

		JLabel fondo = new JLabel();
		panel.add(fondo);
		fondo.setBounds(0, 0, 492, 506);
		fondo.setIcon(imagen);

		addWindowListener(new WindowAdapter() { // Al abrir la ventana, se activa el método "cargaPorcentaje"
			@Override
			public void windowOpened(WindowEvent e) {
				cargaPorcentaje();
			}
		});
		 ((ImageIcon) imagen).getImage().flush(); //Libera los recursos con la imagen cargada

		// Agrega el panel a la ventana
		getContentPane().add(panel);
		add(panel);
	}

	private void cargaPorcentaje() { // Método para iniciar un hilo que maneje la carga de la barra de progreso
		Thread hilo = new Thread(new Runnable() {
			public void run() {
				cargas(); //Invoco al método
			}
		});
		hilo.start(); //Inicia el hilo
	}

	private void cargas() { // Método para simular la carga progresiva y actualización del porcentaje
		porcentaje.setText("0%"); // Establezco el porcentaje inicial
		pausa(); // Invoco el método para pausar la ejecución
		carga1.setBackground(Color.GRAY); // Simula la carga del componente
		porcentaje.setText("40%");
		pausa();
		carga2.setBackground(Color.GRAY);
		porcentaje.setText("80%");
		pausa();
		carga3.setBackground(Color.GRAY);
		porcentaje.setText("100%");
		pausa();
		Menu menu = new Menu(); // Crea un objeto de la clase Menu
		dispose(); // Cierra la ventana actual
	}

	private void pausa() { // Método para pausar la ejecución por un segundo
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
//CREATED BY NAHUEL TELLECHEA FREIRE