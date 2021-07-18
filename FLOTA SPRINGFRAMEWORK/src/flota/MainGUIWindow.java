package flota;

import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

@Component
public class MainGUIWindow {

	private JFrame frame;

	@Autowired
	CargoPortListPopulate cargoList;
	@Autowired
	PortListPopulate portList;
	@Autowired
	PortDischargeListPopulate portDisList;
	@Autowired
	TablePopulate shipInTable;
	@Autowired
	NewPort newPort;
	@Autowired
	NewShip newShip;
	@Autowired
	NewCargo newcargo;
	
	JTextField textIlosc;

	public Double deklarujIloscLadunku = 0.0;

	public JTextField textFieldnazwaNowegoPortuNazwa;
	public JTextField textFieldnazwaNowegoPortuKraj;
	public JTextField textFieldnazwaNowegoPortuPNazwa;
	public JTextField textFieldnazwaNowegoPortuSzerokosc;
	public JTextField textFieldnazwaNowegoPortuDlugosc;
	public JButton btnDodajNowyPort, btnDodajNowyStatek;
	public JTextField textNazwaLadunku;
	public JTextField textCenaLadunku;
	public JTextField textObjetoscLadunku;

	public JTextField textUwagi;
	public JTextField textNazwaNowegoStatku;
	public JTextField textLadownoscStatku;
	public JTextField textObjetoscStatku;
	public JTextField textKoszt;

	public JLabel lblZamowienieLadunek, lblZamowieniePort, lblZamowieniePWyladunkowy, lblZamowienieIlosc,
			lblZamowienieDataZal, lblZamowienieDataWy;

	public JComboBox comboBoxPortWyladunkowy, comboBoxPort, comboBoxLadunek;

	public Choice grainStandard;
	
	
	//private ArrayList<String> listaLadunek = new ArrayList<String>();
	//private ArrayList<String> listaPort = new ArrayList<String>();

	public String[] tabelaListaLadunek, tabelaListaPort;

	JDateChooser dateChooser, dateChooserZaladunek, dateChooserWyladunek;

	String wybranyLadunekWBTN, wybranyPort, wybranyPortWyladunkowy;
	String wartosZamowieniString;
	Double wartoscZamowienia;
	Double ilosc;
	Double iloscDouble;
	Double obliczonaWartoscZamowienia;
	Double wartoscZam;
	Date dataZaladunku, dataWyladunku;
	JLabel lblWartoscZamowienia;

	double lat1;
	double lon1;
	double lat2;
	double lon2;
	double dist;

	public JTable table;

	
	
	/**
	 * @throws SQLException 
	 * @wbp.parser.entryPoint
	 */
	public void initialize() throws SQLException {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 1600, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		
		
		Connection conn = null;
		/*
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {

		
			conn = DriverManager.getConnection("jdbc:sqlite:Flota.db");

		
			Statement stmtPort = conn.createStatement();
			ResultSet rsPort = stmtPort.executeQuery("SELECT NAZWA_PORTU FROM PORT");

			while (rsPort.next()) {
				String sPort = rsPort.getString(1);

				listaPort.add(sPort);
				tabelaListaPort = listaPort.toArray(new String[listaPort.size()]);

			}
			conn.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Problem with connection of database");

		}
*/
		JLabel dbLog = new JLabel("Po\u0142\u0105czono z baz\u0105 danych:");
		dbLog.setFont(new Font("Tahoma", Font.PLAIN, 10));
		dbLog.setBounds(50, 11, 312, 23);
		dbLog.setForeground(Color.BLACK);
		frame.getContentPane().add(dbLog);

		JPanel panelNowyPort = new JPanel();

		panelNowyPort.setBorder(new TitledBorder(null, "wprowadz nowy port do bazy danych", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelNowyPort.setBounds(50, 461, 324, 231);
		frame.getContentPane().add(panelNowyPort);
		panelNowyPort.setLayout(null);

		textFieldnazwaNowegoPortuNazwa = new JTextField();
		textFieldnazwaNowegoPortuNazwa.setToolTipText("Pole wymagane");
		textFieldnazwaNowegoPortuNazwa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldnazwaNowegoPortuNazwa.setBounds(228, 26, 86, 20);
		panelNowyPort.add(textFieldnazwaNowegoPortuNazwa);
		textFieldnazwaNowegoPortuNazwa.setColumns(10);

		JLabel nazwaNowegoPortuNazwa = new JLabel("Nazwa");
		nazwaNowegoPortuNazwa.setBounds(6, 25, 43, 19);
		panelNowyPort.add(nazwaNowegoPortuNazwa);

		nazwaNowegoPortuNazwa.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel nazwaNowegoPortuKraj = new JLabel("Kraj");
		nazwaNowegoPortuKraj.setBounds(6, 59, 26, 19);
		panelNowyPort.add(nazwaNowegoPortuKraj);

		nazwaNowegoPortuKraj.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel nazwaNowegoPortuPNazwa = new JLabel("Pe\u0142na nazwa kraju");
		nazwaNowegoPortuPNazwa.setBounds(6, 89, 119, 19);
		panelNowyPort.add(nazwaNowegoPortuPNazwa);

		nazwaNowegoPortuPNazwa.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel nazwaNowegoPortuSzerokosc = new JLabel("Szeroko\u015B\u0107 geograficzna");
		nazwaNowegoPortuSzerokosc.setBounds(6, 119, 152, 19);
		panelNowyPort.add(nazwaNowegoPortuSzerokosc);

		nazwaNowegoPortuSzerokosc.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel nazwaNowegoPortuDlugosc = new JLabel("Dlugo\u015B\u0107 geograficzna");
		nazwaNowegoPortuDlugosc.setBounds(6, 149, 137, 19);
		panelNowyPort.add(nazwaNowegoPortuDlugosc);

		nazwaNowegoPortuDlugosc.setFont(new Font("Tahoma", Font.PLAIN, 15));

		textFieldnazwaNowegoPortuKraj = new JTextField();
		textFieldnazwaNowegoPortuKraj.setToolTipText("Pole wymagane");
		textFieldnazwaNowegoPortuKraj.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldnazwaNowegoPortuKraj.setColumns(10);
		textFieldnazwaNowegoPortuKraj.setBounds(228, 57, 86, 20);
		panelNowyPort.add(textFieldnazwaNowegoPortuKraj);

		textFieldnazwaNowegoPortuPNazwa = new JTextField();
		textFieldnazwaNowegoPortuPNazwa.setToolTipText("Pole wymagane");
		textFieldnazwaNowegoPortuPNazwa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldnazwaNowegoPortuPNazwa.setColumns(10);
		textFieldnazwaNowegoPortuPNazwa.setBounds(228, 90, 86, 20);
		panelNowyPort.add(textFieldnazwaNowegoPortuPNazwa);

		textFieldnazwaNowegoPortuSzerokosc = new JTextField();
		textFieldnazwaNowegoPortuSzerokosc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldnazwaNowegoPortuSzerokosc.setColumns(10);
		textFieldnazwaNowegoPortuSzerokosc.setBounds(228, 120, 86, 20);
		panelNowyPort.add(textFieldnazwaNowegoPortuSzerokosc);

		textFieldnazwaNowegoPortuDlugosc = new JTextField();
		textFieldnazwaNowegoPortuDlugosc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldnazwaNowegoPortuDlugosc.setColumns(10);
		textFieldnazwaNowegoPortuDlugosc.setBounds(228, 150, 86, 20);
		panelNowyPort.add(textFieldnazwaNowegoPortuDlugosc);

		btnDodajNowyPort = new JButton("Dodaj");
		btnDodajNowyPort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					newPort.setNewPort();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		btnDodajNowyPort.setBounds(117, 197, 89, 23);
		panelNowyPort.add(btnDodajNowyPort);

		JPanel panelNowyLadunek = new JPanel();
		panelNowyLadunek.setLayout(null);
		panelNowyLadunek.setBorder(new TitledBorder(null, "wprowadz nowy �adunek do bazy danych", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		panelNowyLadunek.setBounds(718, 461, 807, 231);
		frame.getContentPane().add(panelNowyLadunek);

		textNazwaLadunku = new JTextField();
		textNazwaLadunku.setToolTipText("Pole wymagane");
		textNazwaLadunku.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textNazwaLadunku.setColumns(10);
		textNazwaLadunku.setBounds(228, 26, 86, 20);
		panelNowyLadunek.add(textNazwaLadunku);

		JLabel nazwaNowegoLadunku = new JLabel("Nazwa");

		nazwaNowegoLadunku.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nazwaNowegoLadunku.setBounds(6, 25, 43, 19);
		panelNowyLadunek.add(nazwaNowegoLadunku);

		JLabel nazwaCenaNowegoLaduku = new JLabel("Cena \u0142adunku");

		nazwaCenaNowegoLaduku.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nazwaCenaNowegoLaduku.setBounds(6, 59, 152, 19);
		panelNowyLadunek.add(nazwaCenaNowegoLaduku);

		JLabel nazwaObjetoscLadunku = new JLabel("Obj\u0119to\u015B\u0107 \u0142adunku");

		nazwaObjetoscLadunku.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nazwaObjetoscLadunku.setBounds(6, 89, 152, 19);
		panelNowyLadunek.add(nazwaObjetoscLadunku);

		JLabel nazwaGrainStaandard = new JLabel("Grain Standard");

		nazwaGrainStaandard.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nazwaGrainStaandard.setBounds(6, 119, 152, 19);
		panelNowyLadunek.add(nazwaGrainStaandard);

		JLabel nazwaUwqagiDotLadunku = new JLabel("Uwagi");
		nazwaUwqagiDotLadunku.setHorizontalAlignment(SwingConstants.TRAILING);
		nazwaUwqagiDotLadunku.setForeground(Color.BLACK);
		nazwaUwqagiDotLadunku.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nazwaUwqagiDotLadunku.setBounds(660, 27, 137, 19);
		panelNowyLadunek.add(nazwaUwqagiDotLadunku);

		textCenaLadunku = new JTextField();
		textCenaLadunku.setToolTipText("Cena za jedn\u0105 tone (USD)");
		textCenaLadunku.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textCenaLadunku.setColumns(10);
		textCenaLadunku.setBounds(228, 57, 86, 20);
		panelNowyLadunek.add(textCenaLadunku);

		textObjetoscLadunku = new JTextField();
		textObjetoscLadunku.setToolTipText("Obj\u0119to\u015B\u0107 1 tony \u0142adunku");
		textObjetoscLadunku.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textObjetoscLadunku.setColumns(10);
		textObjetoscLadunku.setBounds(228, 90, 86, 20);
		panelNowyLadunek.add(textObjetoscLadunku);

		grainStandard = new Choice();
		grainStandard.setFont(new Font("Tahoma", Font.PLAIN, 15));
		grainStandard.setBounds(228, 120, 86, 20);
		panelNowyLadunek.add(grainStandard);
		grainStandard.add("-----");
		grainStandard.add("Yes");
		grainStandard.add("No");

		textUwagi = new JTextField();
		textUwagi.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textUwagi.setColumns(10);
		textUwagi.setBounds(332, 58, 465, 162);
		panelNowyLadunek.add(textUwagi);

		JButton btnDodajNowyLadunek = new JButton("Dodaj");
		btnDodajNowyLadunek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					newcargo.setNewCargo();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		btnDodajNowyLadunek.setBounds(117, 197, 89, 23);
		panelNowyLadunek.add(btnDodajNowyLadunek);

		JPanel panelNowyStatek = new JPanel();
		panelNowyStatek.setLayout(null);
		// datePanel.add(jPanel, BorderLayout.WEST);
		panelNowyStatek.setBorder(new TitledBorder(null, "wprowadz nowy statek do bazy danych", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		panelNowyStatek.setBounds(384, 461, 324, 231);
		panelNowyStatek.setVisible(true);
		frame.getContentPane().add(panelNowyStatek);

		textNazwaNowegoStatku = new JTextField();
		textNazwaNowegoStatku.setToolTipText("Pole wymagane");
		textNazwaNowegoStatku.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textNazwaNowegoStatku.setColumns(10);
		textNazwaNowegoStatku.setBounds(228, 26, 86, 20);
		panelNowyStatek.add(textNazwaNowegoStatku);

		JLabel nazwaNowegoStatku = new JLabel("Nazwa");

		nazwaNowegoStatku.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nazwaNowegoStatku.setBounds(6, 25, 43, 19);
		panelNowyStatek.add(nazwaNowegoStatku);

		JLabel nazwaLadownosci = new JLabel("\u0141adowno\u015B\u0107 statku DWT");

		nazwaLadownosci.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nazwaLadownosci.setBounds(6, 59, 174, 19);
		panelNowyStatek.add(nazwaLadownosci);

		JLabel nazwaObjetosci = new JLabel("Obj\u0119to\u015B\u0107 \u0141adowni");

		nazwaObjetosci.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nazwaObjetosci.setBounds(6, 89, 119, 19);
		panelNowyStatek.add(nazwaObjetosci);

		JLabel nazwaDostepnosci = new JLabel("Data dost\u0119pno\u015Bci");

		nazwaDostepnosci.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nazwaDostepnosci.setBounds(6, 119, 152, 19);
		panelNowyStatek.add(nazwaDostepnosci);

		JLabel nazwaSpalanie = new JLabel("Dobowy koszt paliwa");

		nazwaSpalanie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nazwaSpalanie.setBounds(6, 149, 137, 19);
		panelNowyStatek.add(nazwaSpalanie);

		textLadownoscStatku = new JTextField();
		textLadownoscStatku.setToolTipText(
				"Wprowadz wartosc calkowita lub u\u017Cyj kropki \"  .  \"\r\nPoje nie rozpoznaje przecinka");
		textLadownoscStatku.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textLadownoscStatku.setColumns(10);
		textLadownoscStatku.setBounds(228, 57, 86, 20);
		panelNowyStatek.add(textLadownoscStatku);

		textObjetoscStatku = new JTextField();
		textObjetoscStatku.setToolTipText(
				"Wprowadz wartosc calkowita lub u\u017Cyj kropki \"  .  \"\r\nPoje nie rozpoznaje przecinka");
		textObjetoscStatku.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textObjetoscStatku.setColumns(10);
		textObjetoscStatku.setBounds(228, 90, 86, 20);
		panelNowyStatek.add(textObjetoscStatku);

		textKoszt = new JTextField();
		textKoszt.setToolTipText(
				"Wprowadz wartosc calkowita lub u\u017Cyj kropki \"  .  \"\r\nPoje nie rozpoznaje przecinka");
		textKoszt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textKoszt.setColumns(10);
		textKoszt.setBounds(228, 150, 86, 20);
		panelNowyStatek.add(textKoszt);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(228, 119, 86, 20);
		panelNowyStatek.add(dateChooser);

		

		btnDodajNowyStatek = new JButton("Dodaj");
		btnDodajNowyStatek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					newShip.setNewShip();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		btnDodajNowyStatek.setBounds(117, 197, 89, 23);
		panelNowyStatek.add(btnDodajNowyStatek);

		JPanel panelZamownienie = new JPanel();
		panelZamownienie.setBorder(
				new TitledBorder(null, "wprowadz nowe zam�wienie", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panelZamownienie.setBounds(50, 45, 476, 405);
		frame.getContentPane().add(panelZamownienie);
		panelZamownienie.setLayout(null);

		// tekst podpowaiadjący
		JLabel lbLadunek = new JLabel("Rodzaj \u0142adunku");
		lbLadunek.setBounds(40, 34, 132, 19);
		panelZamownienie.add(lbLadunek);
		lbLadunek.setFont(new Font("Tahoma", Font.PLAIN, 15));

		// tekst podpowaiadjący
		JLabel lbZaladunek = new JLabel("Port zaladunkowy");
		lbZaladunek.setBounds(40, 64, 132, 29);
		panelZamownienie.add(lbZaladunek);
		lbZaladunek.setFont(new Font("Tahoma", Font.PLAIN, 15));

		// tekst podpowaiadjący
		JLabel lbWyladunek = new JLabel("Port wyladukowy");
		lbWyladunek.setBounds(40, 104, 132, 29);
		panelZamownienie.add(lbWyladunek);

		lbWyladunek.setFont(new Font("Tahoma", Font.PLAIN, 15));

		// tekst podpowaiadjący
		JLabel lbIlosc = new JLabel("Podaj ilo\u015B\u0107 laduku");
		lbIlosc.setBounds(40, 144, 132, 29);
		panelZamownienie.add(lbIlosc);

		lbIlosc.setFont(new Font("Tahoma", Font.PLAIN, 15));

		// tekst podpowaiadjący
		JLabel lbData = new JLabel("data zaladunku");
		lbData.setBounds(40, 184, 132, 29);
		panelZamownienie.add(lbData);
		lbData.setForeground(Color.BLACK);
		lbData.setFont(new Font("Tahoma", Font.PLAIN, 15));

		textIlosc = new JTextField(String.valueOf(deklarujIloscLadunku));
		textIlosc.setBounds(182, 146, 119, 29);
		panelZamownienie.add(textIlosc);

		textIlosc.setColumns(10);
		textIlosc.setToolTipText("\u0142adunek w tonach");

		JLabel lblPodajDatWyladunku = new JLabel("data dostarczenia");

		lblPodajDatWyladunku.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPodajDatWyladunku.setBounds(40, 225, 132, 29);
		panelZamownienie.add(lblPodajDatWyladunku);

		dateChooserZaladunek = new JDateChooser();
		dateChooserZaladunek.setBounds(182, 184, 119, 29);
		panelZamownienie.add(dateChooserZaladunek);

		dateChooserWyladunek = new JDateChooser();
		dateChooserWyladunek.setBounds(182, 225, 119, 29);
		panelZamownienie.add(dateChooserWyladunek);

		lblZamowienieLadunek = new JLabel("-----");
		lblZamowienieLadunek.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblZamowienieLadunek.setVerticalAlignment(SwingConstants.TOP);
		lblZamowienieLadunek.setBounds(325, 39, 141, 19);
		panelZamownienie.add(lblZamowienieLadunek);

		lblZamowieniePort = new JLabel("-----");
		lblZamowieniePort.setVerticalAlignment(SwingConstants.TOP);
		lblZamowieniePort.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblZamowieniePort.setBounds(325, 74, 141, 19);
		panelZamownienie.add(lblZamowieniePort);

		lblZamowieniePWyladunkowy = new JLabel("-----");
		lblZamowieniePWyladunkowy.setVerticalAlignment(SwingConstants.TOP);
		lblZamowieniePWyladunkowy.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblZamowieniePWyladunkowy.setBounds(325, 114, 141, 19);
		panelZamownienie.add(lblZamowieniePWyladunkowy);

		lblZamowienieIlosc = new JLabel("-----");
		lblZamowienieIlosc.setVerticalAlignment(SwingConstants.TOP);
		lblZamowienieIlosc.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblZamowienieIlosc.setBounds(325, 154, 141, 19);
		panelZamownienie.add(lblZamowienieIlosc);

		lblZamowienieDataZal = new JLabel("-----");
		lblZamowienieDataZal.setVerticalAlignment(SwingConstants.TOP);
		lblZamowienieDataZal.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblZamowienieDataZal.setBounds(325, 194, 141, 19);
		panelZamownienie.add(lblZamowienieDataZal);

		lblZamowienieDataWy = new JLabel("-----");
		lblZamowienieDataWy.setVerticalAlignment(SwingConstants.TOP);
		lblZamowienieDataWy.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblZamowienieDataWy.setBounds(325, 235, 141, 19);
		panelZamownienie.add(lblZamowienieDataWy);

		lblWartoscZamowienia = new JLabel("Wartos zamowienia");
		lblWartoscZamowienia.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblWartoscZamowienia.setBounds(10, 265, 456, 71);
		panelZamownienie.add(lblWartoscZamowienia);

		JButton btnPodsumowanie = new JButton("Podsumowanie");

		btnPodsumowanie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				wybranyLadunekWBTN = (String) comboBoxLadunek.getSelectedItem();
				wybranyPort = (String) comboBoxPort.getSelectedItem();
				wybranyPortWyladunkowy = (String) comboBoxPortWyladunkowy.getSelectedItem();
				String iloscString = textIlosc.getText();
				iloscDouble = Double.parseDouble(iloscString);
				ilosc = Double.parseDouble(textIlosc.getText());
				dataZaladunku = dateChooserZaladunek.getDate();
				dataWyladunku = dateChooserWyladunek.getDate();

				String podsumowanieLadunek = wybranyLadunekWBTN;
				String podsumowaniePort = wybranyPort;
				String podsumowanieWyladunkowy = wybranyPortWyladunkowy;
				String podsumowanieIlosc = textIlosc.getText();
				String podsumowanieDataZa = dataZaladunku.toString();
				String podsumowanieDataWy = dataWyladunku.toString();

				lblZamowienieLadunek.setText(podsumowanieLadunek);
				lblZamowieniePort.setText(podsumowaniePort);
				lblZamowieniePWyladunkowy.setText(podsumowanieWyladunkowy);
				lblZamowienieIlosc.setText(podsumowanieIlosc);
				lblZamowienieDataZal.setText(podsumowanieDataZa);
				lblZamowienieDataWy.setText(podsumowanieDataWy);

				wartoscZam = Double.parseDouble(podsumowanieIlosc);

				Double cenaDouble;
				Connection conn = null;

				try {
					Class.forName("org.sqlite.JDBC");
				} catch (ClassNotFoundException e2) {
					e2.printStackTrace();
				}

				try {

					conn = DriverManager.getConnection("jdbc:sqlite:Flota.db");

					Statement stat = conn.createStatement();

					String command = ("Select CENA_ZA_TONE_USD FROM LADUNEK WHERE NAZWA_LADUNKU = '"
							+ wybranyLadunekWBTN + "'");
					// stat.executeQuery(command);
					ResultSet rs = stat.executeQuery(command);

					while (rs.next()) {
						cenaDouble = rs.getDouble(1);
						wartoscZamowienia = cenaDouble * ilosc;
						wartosZamowieniString = String.valueOf(wartoscZamowienia);

					}

				} catch (Exception e3) {
					JOptionPane.showMessageDialog(null, "Problem with connection of database");

				}

				Connection connDyst = null;

				try {
					Class.forName("org.sqlite.JDBC");
				} catch (ClassNotFoundException e4) {
					e4.printStackTrace();
				}

				try {

					// pobiera szerokosc geograficzna z bazydanych
					// nastepnie przypisuje do zmiennej
					// conn = DriverManager.getConnection("jdbc:sqlite:C:\\JAVA\\Moje bazy
					// danych\\Flota.db");
					connDyst = DriverManager.getConnection("jdbc:sqlite:Flota.db");

					Statement stat = connDyst.createStatement();
					String pobierzSzerokoscPortZaladunkowy = ("Select SZEROKOSC_GEOGRAFICZNA FROM PORT WHERE NAZWA_PORTU = '"
							+ wybranyPort + "'");
					ResultSet rsszer = stat.executeQuery(pobierzSzerokoscPortZaladunkowy);
					while (rsszer.next()) {
						lat1 = rsszer.getDouble(1);
					}

					// pobiera dlugosc geograficzna z bazy danycg
					// nastepnie przypisuje do zmiennej
					Statement stat2 = connDyst.createStatement();
					String pobierzDlugoscPortZaladunkowy = ("Select DLUGOSC_GEOGRAFICZNA FROM PORT WHERE NAZWA_PORTU = '"
							+ wybranyPort + "'");
					ResultSet rsdl = stat.executeQuery(pobierzDlugoscPortZaladunkowy);
					while (rsdl.next()) {
						lon1 = rsdl.getDouble(1);
					}

					// pobiera szerokosc geograficzna z bazydanych
					// nastepnie przypisuje do zmiennej
					Statement stat3 = connDyst.createStatement();
					String pobierzSzerokoscPortWyladunkowy = ("Select SZEROKOSC_GEOGRAFICZNA FROM PORT WHERE NAZWA_PORTU = '"
							+ wybranyPortWyladunkowy + "'");
					ResultSet rsszer2 = stat.executeQuery(pobierzSzerokoscPortWyladunkowy);
					while (rsszer2.next()) {
						lat2 = rsszer2.getDouble(1);
					}

					// pobiera dlugosc geograficzna z bazy danycg
					// nastepnie przypisuje do zmiennej
					Statement stat4 = connDyst.createStatement();
					String pobierzDlugoscPortWyladunkowy = ("Select DLUGOSC_GEOGRAFICZNA FROM PORT WHERE NAZWA_PORTU = '"
							+ wybranyPortWyladunkowy + "'");
					ResultSet rsdl2 = stat.executeQuery(pobierzDlugoscPortWyladunkowy);
					while (rsdl2.next()) {
						lon2 = rsdl2.getDouble(1);
					}

					// zamyka polaczenie z baza danych
					connDyst.close();

				} catch (Exception e4) {
					JOptionPane.showMessageDialog(null, "Problem with connection of database");
				}

				double theta = lon1 - lon2;
				dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1))
						* Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
				dist = Math.acos(dist);
				dist = Math.toDegrees(dist);

				dist = dist * 0.8684;

				lblWartoscZamowienia.setText("Warto�� zam�wienia wynosi: " + wartosZamowieniString + " USD"
						+ "\n Dystans do przep�yni�cia: " + dist);

			}

		});
		btnPodsumowanie.setBounds(10, 341, 132, 53);
		panelZamownienie.add(btnPodsumowanie);

		comboBoxLadunek = new JComboBox(cargoList.sqlStatementCargoList());
		
		comboBoxLadunek.setBounds(182, 31, 119, 29);
		panelZamownienie.add(comboBoxLadunek);

		comboBoxPort = new JComboBox(cargoList.sqlStatementPortList());
		comboBoxPort.setBounds(182, 66, 119, 29);
		panelZamownienie.add(comboBoxPort);

		comboBoxPortWyladunkowy = new JComboBox(cargoList.sqlStatementPortDisList());
		comboBoxPortWyladunkowy.setBounds(182, 106, 119, 29);
		panelZamownienie.add(comboBoxPortWyladunkowy);

		JButton btnAnalizuj = new JButton("Analizuj zamowienie");

		btnAnalizuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
/*
				wybranyLadunekWBTN = (String) comboBoxLadunek.getSelectedItem();
				wybranyPort = (String) comboBoxPort.getSelectedItem();
				wybranyPortWyladunkowy = (String) comboBoxPortWyladunkowy.getSelectedItem();
				String iloscString = textIlosc.getText();
				iloscDouble = Double.parseDouble(iloscString);
				ilosc = Double.parseDouble(textIlosc.getText());
				dataZaladunku = dateChooserZaladunek.getDate();
				dataWyladunku = dateChooserWyladunek.getDate();

				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

				String dataDostepnosci = format.format(new Date());

				/*
				 * SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				 * 
				 * dateFormat.format(new Date())
				 * 
				 */

				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

				String sd = dateFormat.format(dateChooserZaladunek.getDate());

				Double cenaDouble = 0.0;
				Double distance = dist;
				Date dostepnosc = null;
				Connection connAnalys = null;

				try {
					Class.forName("org.sqlite.JDBC");
				} catch (ClassNotFoundException e4) {
					e4.printStackTrace();
				}

				try {

					// conn6 = DriverManager.getConnection("jdbc:sqlite:C:\\JAVA\\Moje bazy
					// danych\\Flota.db");
					connAnalys = DriverManager.getConnection("jdbc:sqlite:Flota.db");

					Statement stat = connAnalys.createStatement();
					String command1 = ("Select CENA_ZA_TONE_USD FROM LADUNEK WHERE NAZWA_LADUNKU = '"
							+ wybranyLadunekWBTN + "'");
					ResultSet rs = stat.executeQuery(command1);
					while (rs.next()) {
						cenaDouble = rs.getDouble(1);

					}

					/*
					 * Statement statDostepnosc = connAnalys.createStatement(); String command2 =
					 * ("Select SZCOWANA_DATA_DOSTEPNOSCI FROM STATEK;"); ResultSet rsDost =
					 * statDostepnosc.executeQuery(command2); while(rsDost.next()) { dostepnosc =
					 * rs.getDate(1);
					 * 
					 * }
					 */
					String query = ("select \r\n" + "NAZWA_STATKU, '" + ilosc + "' *'" + cenaDouble
							+ "'as ZYSK_BRUTTO,\r\n" + "DOBOWY_KOSZY_PALIWA_USD *'" + distance + "'as KOSZT,\r\n"
							+ "DOBOWY_KOSZY_PALIWA_USD + LADOWNOSC_STATKU_DWT *'" + cenaDouble + "'as ZYSK_NETTO,\r\n"
							+ "SZCOWANA_DATA_DOSTEPNOSCI\r\n" + "\r\n" + "from STATEK\r\n" + "  \r\n"
							+ "where LADOWNOSC_STATKU_DWT >= '" + ilosc + "'  and SZCOWANA_DATA_DOSTEPNOSCI <= '" + sd
							+ "' \r\n" + "\r\n" + "ORDER BY ZYSK_NETTO DESC;");
					PreparedStatement pst = connAnalys.prepareStatement(query);
					ResultSet rs2 = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs2));

					/*
					 * WORKING SQL STATMENT AS EXAMPLE select NAZWA_STATKU, LADOWNOSC_STATKU_DWT
					 * *2000 as ZYSK_BRUTTO, DOBOWY_KOSZY_PALIWA_USD * 3 as KOSZT,
					 * DOBOWY_KOSZY_PALIWA_USD + LADOWNOSC_STATKU_DWT * 2000 as ZYSK_NETTO,
					 * SZCOWANA_DATA_DOSTEPNOSCI
					 * 
					 * from STATEK
					 * 
					 * where LADOWNOSC_STATKU_DWT >=30000 and SZCOWANA_DATA_DOSTEPNOSCI <=
					 * '11/07/2021'
					 * 
					 * ORDER BY ZYSK_NETTO DESC;
					 * 
					 */

					/*
					 * String query = "select NAZWA_STATKU, LADOWNOSC_STATKU_DWT; PreparedStatement
					 * pst = connAnalys.prepareStatement(query); ResultSet rs = pst.executeQuery();
					 * table.setModel(DbUtils.resultSetToTableModel(rs));
					 * 
					 */

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "B��d po��czenia z baz�");

				}

			}
		});
		
		
		btnAnalizuj.setBounds(334, 341, 132, 53);
		panelZamownienie.add(btnAnalizuj);

		JPanel panelPodsumowanie = new JPanel();
		panelPodsumowanie.setBorder(
				new TitledBorder(null, "podsumowanie zam�wnienia", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		panelPodsumowanie.setBounds(311, 21, 155, 237);
		panelZamownienie.add(panelPodsumowanie);

		JPanel panelWynikiBazy = new JPanel();
		panelWynikiBazy.setBorder(new TitledBorder(null, "wyniki zapytania bazy danych", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		panelWynikiBazy.setBounds(718, 45, 807, 405);
		frame.getContentPane().add(panelWynikiBazy);
		panelWynikiBazy.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(690, 51, 787, 402);

		panelWynikiBazy.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnShipsInTable = new JButton("Statki");
		btnShipsInTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					shipInTable.getShipLost();;
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnShipsInTable.setBounds(562, 48, 89, 23);
		panelWynikiBazy.add(btnShipsInTable);
		
		JButton btnPortsInTable = new JButton("Porty");
		btnPortsInTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					shipInTable.getPortList();
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
			}
		});
		btnPortsInTable.setBounds(562, 82, 89, 23);
		panelWynikiBazy.add(btnPortsInTable);
		
		JButton btnCargoInTable = new JButton("\u0141adunki");
		btnCargoInTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					shipInTable.getCargoList();
				} catch (SQLException e1) {
				
					e1.printStackTrace();
				}
				
			}
		});
		btnCargoInTable.setBounds(562, 116, 89, 23);
		panelWynikiBazy.add(btnCargoInTable);
		
		
	}
}
