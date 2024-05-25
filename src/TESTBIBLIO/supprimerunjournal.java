package TESTBIBLIO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class supprimerunjournal extends JFrame implements ActionListener {
	static Connection conction=null;
	static String databaseName="";
	static String url="jdbc:mysql://localhost:3306/"+databaseName;
	static String username="root";
	static String password="ertdfgcvb";
     
     //
	JLabel lnum = new  JLabel("ENTRER LE COTE DE DOCUMENT :") ;
	JTextField tnum=new  JTextField(30);
	JPanel contenant =new JPanel();
	JPanel contenu =new JPanel();
	JPanel BTNcon= new JPanel();
	JButton btnSupprimer = new JButton("Supprimerr",new ImageIcon("C:\\Users\\user\\eclipse-workspace\\TESTBIBLIO\\src\\TESTBIBLIO\\effacer-le-contact.png"));
	JButton btnannuler = new JButton("Annuler",new ImageIcon("C:\\Users\\user\\eclipse-workspace\\TESTBIBLIO\\src\\TESTBIBLIO\\annulation.png"));
	supprimerunjournal(){
		setSize(700,500);
		setTitle("ESPACE SUPPRESSION JOURNAL :");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lnum.setFont(new Font("Serif", Font.BOLD, 20));
		contenant.setBackground(Color.decode("#444f70") );
		BTNcon.setBackground(Color.decode("#444f70") );
		contenu.setBorder(new TitledBorder("SUPPRIMER UN JOURNAL "));
		GridLayout L =new GridLayout(1,2);
		L.setHgap(26);
		BTNcon.setLayout(L);
		
		btnannuler.addActionListener(this);
		btnSupprimer.addActionListener(this);
		
		BTNcon.add(btnSupprimer);
		BTNcon.add(btnannuler);
		add(contenant);
		
		contenu.setBackground(Color.decode("#1c8d93"));
		contenu.setPreferredSize(new Dimension(600,350));
		contenu.add(lnum);
		contenu.add(tnum);
	
		contenant.add(contenu);
		contenant.add(BTNcon);
		 this.setLocationRelativeTo(null);

	
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		supprimerunjournal su =new supprimerunjournal();
		su.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnannuler){
			Employe employe=new Employe();
			  dispose();
	    employe.setVisible(true);
		}
      if(e.getSource()==btnSupprimer){
	 try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conction=DriverManager.getConnection(url,username,password);
			int jml = Integer.parseInt(tnum.getText());
			String qr="select * FROM `bibliotheque`.`document` WHERE `cote`="+jml+";";
       PreparedStatement ps=conction.prepareStatement("DELETE FROM `bibliotheque`.`document` WHERE `cote`="+jml+" ;" );
			
			PreparedStatement ps1=conction.prepareStatement("DELETE FROM `bibliotheque`.`journal` WHERE `cote`="+jml+" ;" );
			
			java.sql.Statement rs=conction.createStatement();
			ResultSet resultat=rs.executeQuery(qr);
			
			if(resultat.next()) {
				ps.execute();
				ps1.execute();
				
		
			    JOptionPane.showMessageDialog(null,"le journal supprim�e");
			}
			else
			{
				 JOptionPane.showMessageDialog(null,"le journal n'�xiste pas");
			}
			conction.close();
		 }catch(Exception ev){
	JOptionPane.showConfirmDialog(null,"errors de remplissage ");
		//System.out.println(e.getMessage());
		 }
	
		}
	}
}
