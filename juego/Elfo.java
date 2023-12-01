package juego;

//Importo las librerias
import principal.Menu;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Elfo extends JFrame { //Creo la clase Elfo que hereda de JFrame

	// Variables que representan atributos del personaje y estadísticas del juego
	private int eleccion;
	private String nombre, lugar;
	private int salud = 130, grito = 4, fuerza = 80;
	private int saludEnemigo = 140, gritoEnemigo = 3, fuerzaEnemigo = 100;
	private int partidasGanadas = 0, partidasPerdidas = 0, gritosUsados = 0, cantFuerzaPerdida = 0, cantDefenderse = 0;
	private int numeroElfo[] = new int[5];
	private JTextArea tuPersonaje;
	private JTextArea enemigoPersonaje;

	// Constructor que inicializa la interfaz gráfica e invoca al método "componentes"
	public Elfo(String nombre, String lugar) {
		// Inicialización de atributos y configuración de la ventana
		this.nombre = nombre;
		this.lugar = lugar;

		//Configuración de la ventana: título, tamaño, ubicación, icono, etc
		setVisible(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage("./img/imagen.png"));
		setTitle("Elfo");
		setSize(741, 485);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		componentes();
	}

	private void componentes() {
		guardarDatosTemp();
		partidasGanadas = numeroElfo[0];
		partidasPerdidas = numeroElfo[1];
		gritosUsados = numeroElfo[2];
		cantFuerzaPerdida = numeroElfo[3];
		cantDefenderse = numeroElfo[4];
		
		// Creación de elementos visuales como botones, etiquetas, áreas de texto, etc.
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel sitio = new JLabel("Mundo: "+lugar);
		panel.add(sitio);
		sitio.setBounds(30, 80, 195, 20);

		JLabel titulo = new JLabel("¡A LUCHAR!");
		panel.add(titulo);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(277, 10, 102, 37);

		JLabel elige = new JLabel("Elige");
		panel.add(elige);
		elige.setBounds(27, 49, 85, 28);

		JButton Grito = new JButton("USAR GRITO");
		panel.add(Grito);
		Grito.setBounds(10, 237, 145, 21);

		JButton volver = new JButton("VOLVER");
		panel.add(volver);
		volver.setBounds(10, 302, 145, 21);
		
		JButton defender = new JButton("DEFENDER");
		panel.add(defender);
		defender.setBounds(10, 173, 145, 21);

		JButton atacar = new JButton("ATACAR");
		panel.add(atacar);
		atacar.setBounds(10, 110, 145, 21);

		JButton salir = new JButton("SALIR DEL JUEGO");
		panel.add(salir);
		salir.setBounds(10, 367, 145, 21);

		JLabel tu = new JLabel(nombre);
		panel.add(tu);
		tu.setHorizontalAlignment(SwingConstants.CENTER);
		tu.setBounds(308, 44, 100, 39);

		tuPersonaje = new JTextArea();
		panel.add(tuPersonaje);
		tuPersonaje.setEditable(false);
		tuPersonaje.setBounds(256, 86, 196, 352);

		JSeparator separator = new JSeparator();
		panel.add(separator);
		separator.setForeground(new Color(0, 0, 255));
		separator.setBackground(new Color(0, 0, 255));
		separator.setBounds(502, 86, -11, 352);

		JLabel enemigo = new JLabel("DIOS RICARDO");
		panel.add(enemigo);
		enemigo.setHorizontalAlignment(SwingConstants.CENTER);
		enemigo.setBounds(582, 44, 100, 39);

		enemigoPersonaje = new JTextArea();
		panel.add(enemigoPersonaje);
		enemigoPersonaje.setEditable(false);
		enemigoPersonaje.setBounds(521, 86, 196, 352);

		tuPersonaje.setText("Vida: " + salud + "\nGrito: " + grito + "\nFuerza: " + fuerza);
		enemigoPersonaje.setText("Vida: " + saludEnemigo + "\nGrito: " + gritoEnemigo + "\nFuerza: " + fuerzaEnemigo);

		// Acciones a los botones para ataques, defensas, gritos, volver, etc
		atacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ataque();
				accionEnemigo();
			}
		});

		Grito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				gritar();
				accionEnemigo();
			}
		});

		defender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				defenderse();
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
		
		salir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eleccion = JOptionPane.showConfirmDialog(null, "¿Seguro que deseas entrar a esta opción? Es posible que no se guarden los datos", "CONFIRMACIÓN", JOptionPane.OK_OPTION);
				
				if(eleccion == JOptionPane.OK_OPTION) {
					dispose();
					System.exit(0);
				}
			}
		});

		// Añade los componentes al panel principal y agrega este panel a la ventana
		getContentPane().add(panel);
		add(panel);
	}
	
	private void muerte() {
		if(salud <=15){
			tuPersonaje.setForeground(Color.RED);
		}else if(saludEnemigo <= 15){
			enemigoPersonaje.setForeground(Color.RED);
		}

		if(salud == 0 || salud < 0) {
			salud = 0;
			tuPersonaje.setText("Vida: " + salud + "\nGrito: " + grito + "\nFuerza: " + fuerza);
			enemigoPersonaje.setText("Vida: " + saludEnemigo + "\nGrito: " + gritoEnemigo + "\nFuerza: " + fuerzaEnemigo);
			JOptionPane.showMessageDialog(null, "¡UPS! EL DIOS RICARDO HA GANADO :(", "PERDEDOR", JOptionPane.INFORMATION_MESSAGE);
			
			partidasPerdidas++;
			archivoElfo();
			Menu menu = new Menu();
			dispose();
		}else if(saludEnemigo == 0 || saludEnemigo < 0) {
			saludEnemigo = 0;
			tuPersonaje.setText("Vida: " + salud + "\nGrito: " + grito + "\nFuerza: " + fuerza);
			enemigoPersonaje.setText("Vida: " + saludEnemigo + "\nGrito: " + gritoEnemigo + "\nFuerza: " + fuerzaEnemigo);
			JOptionPane.showMessageDialog(null, "¡YEAHH! HAS DERROTADO AL DIOS RICARDO :D", "GANADOR", JOptionPane.PLAIN_MESSAGE);
			
			partidasGanadas++;
			archivoElfo();
			Menu menu = new Menu();
			dispose();
		}
	}

	private void ataque(){
		muerte();
		int cantFuerza;

		cantFuerza = Integer.parseInt(JOptionPane.showInputDialog("Indique la cantidad de fuerza entre del 1 al 25"));
	
		if(cantFuerza >= 1 && cantFuerza <= 25){
			final int limiteFuerza = 25;
			if (cantFuerza <= limiteFuerza) {
				if (fuerza >= cantFuerza) {
					saludEnemigo -= cantFuerza;
					fuerza -= cantFuerza;
					tuPersonaje.setText("Vida: " + salud + "\nGrito: " + grito + "\nFuerza: " + fuerza);
					enemigoPersonaje.setText("Vida: " + saludEnemigo + "\nGrito: " + gritoEnemigo + "\nFuerza: " + fuerzaEnemigo);
				} else {
					mensajesAdvertencias(nombre+" no tiene suficiente fuerza");
					cantFuerzaPerdida++;
				}
			} else {
				mensajesAdvertencias("La fuerza ingresada supera el límite permitido");
			}
		} else {
			mensajesAdvertencias("La fuerza debe estar entre 1 y 25");
		}
	}

	private void gritar(){
		muerte();
		
		if(grito>0){
				grito--;
				gritosUsados++;
				saludEnemigo-=35;
				fuerzaEnemigo-=15;
				tuPersonaje.setText("Vida: " + salud + "\nGrito: " + grito + "\nFuerza: " + fuerza);
				enemigoPersonaje.setText("Vida: " + saludEnemigo + "\nGrito: " + gritoEnemigo + "\nFuerza: " + fuerzaEnemigo);
			}else{
				mensajesAdvertencias(nombre+" no puede usar más el Grito");
			}
	}

	private void defenderse(){
		muerte();
		int saludTemp, fuerzaTemp;

		saludTemp = salud;
		fuerzaTemp = fuerza;
		cantDefenderse++;
		
		accionEnemigo();

		salud = saludTemp;
		fuerza = fuerzaTemp;
		tuPersonaje.setText("Vida: " + salud + "\nGrito: " + grito + "\nFuerza: " + fuerza);
		enemigoPersonaje.setText("Vida: " + saludEnemigo + "\nGrito: " + gritoEnemigo + "\nFuerza: " + fuerzaEnemigo);
	}

	private void accionEnemigo(){
		muerte();
		Random random = new Random();

		final int limiteFuerza = 25;
		int numeroAleatorio = random.nextInt(2);

		int cantFuerzaEnemigo = random.nextInt(25)+1;

		switch(numeroAleatorio){
			//ATAQUE
			case 0:
				if (cantFuerzaEnemigo <= limiteFuerza) {
					if (fuerzaEnemigo >= cantFuerzaEnemigo) {
						salud -= cantFuerzaEnemigo;
						fuerzaEnemigo -= cantFuerzaEnemigo;
						tuPersonaje.setText("Vida: " + salud + "\nGrito: " + grito + "\nFuerza: " + fuerza);
						enemigoPersonaje.setText("Vida: " + saludEnemigo + "\nGrito: " + gritoEnemigo + "\nFuerza: " + fuerzaEnemigo);
					} else {
						mensajesAdvertencias("Dios Ricardo no tiene suficiente fuerza");
					}
				} else {
					mensajesAdvertencias("Dios Ricardo superó el límite permitido");
				}
			break;

			//GRITO
			case 1:
			if(gritoEnemigo>0){
				gritoEnemigo--;
				salud-=40;
				fuerza-=20;
				tuPersonaje.setText("Vida: " + salud + "\nGrito: " + grito + "\nFuerza: " + fuerza);
				enemigoPersonaje.setText("Vida: " + saludEnemigo + "\nGrito: " + gritoEnemigo + "\nFuerza: " + fuerzaEnemigo);
			}else{
				mensajesAdvertencias("Dios Ricardo no puede usar más el Grito");
			}
			break;
		}
	}
	
	// Métodos para manejar archivos y guardar estadísticas del personaje
	private void archivoElfo(){
		FileWriter elfoDatos = null;
		String datos = "Datos sobre el personaje Elfo:\n"
						+"\nPartidas ganadas: "+partidasGanadas
						+"\nPartidas perdidas: "+partidasPerdidas
						+"\nGritos usados: "+gritosUsados
						+"\nVeces que perdió fuerza: "+cantFuerzaPerdida
						+"\nVeces que se defendió: "+cantDefenderse; //En un String guardo los datos

		try{
			elfoDatos = new FileWriter("./Archivo/Elfo.txt"); //El FileWriter es para escribir un archivo

			for(int i=0; i<datos.length(); i++){ //Lo corro en un for la variable "datos"
				elfoDatos.write(datos.charAt(i)); //lo escribo en el .txt
			}
		}catch(IOException e){
		}finally{
			try {
				elfoDatos.close(); //libero recursos
			} catch (IOException e) {
			}
		}
		actualizarDatos(); //Invoco el método
	}
	
	private void guardarDatosTemp() {
		BufferedReader reader = null; //El BufferedReader es para leer un archivo, lo pongo nulo
			
			try {
				String lineas;
				int posicion = 0;

				reader = new BufferedReader(new FileReader("./Archivo/tempElfo.txt"));
				while ((lineas = reader.readLine()) != null && posicion < numeroElfo.length) { //lo corro en un while al archivo
					numeroElfo[posicion++] = Integer.parseInt(lineas); //lo guardo en un array
				}
			} catch(IOException | NumberFormatException e) {
			}finally{
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
	}
	
	private void actualizarDatos() {
		FileWriter elfo = null;
		String numeros = partidasGanadas
				+"\n"+partidasPerdidas
				+"\n"+gritosUsados
				+"\n"+cantFuerzaPerdida
				+"\n"+cantDefenderse;
		
		try {
		elfo = new FileWriter("./Archivo/tempElfo.txt");

		for(int i=0; i<numeros.length(); i++){
			elfo.write(numeros.charAt(i));
		}
		}catch(IOException | NumberFormatException e) {
		}finally{
			try {
				elfo.close();
			} catch (IOException e) {
			}
		}
	}

	// Método para mostrar mensajes de advertencia en ventanas emergentes
	private void mensajesAdvertencias(String mensaje){
		JOptionPane.showMessageDialog(null, mensaje, "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
	}
	
}
//CREATED BY NAHUEL TELLECHEA FREIRE