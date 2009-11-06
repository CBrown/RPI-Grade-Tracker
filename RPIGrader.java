import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class RPIGrader extends JApplet
{
	JPanel main = new JPanel(true), mainNorth = new JPanel(true), mainCenter = new JPanel(true), mainSouth= new JPanel(true);//True indicates double buffered
	JPanel addClass = new JPanel(true), addClassNorth = new JPanel(true), addClassCenter = new JPanel(true), addClassCenterHomework = new JPanel(true), addClassCenterTests = new JPanel(true), addClassCenterOther = new JPanel(true), addClassCenterFinal = new JPanel(true), addClassSouth = new JPanel(true);


	//Main Components
	JLabel lblStudentName = new JLabel("             Student Name            ");
	JComboBox cmbSemester = new JComboBox();
	JButton btnAddClass = new JButton("Add Class"), btnEditClass = new JButton("Configure Class");

	//List of Classes Bar
	JList lstClasses = new JList();
	DefaultListModel modelClasses = new DefaultListModel();
	JScrollPane scrollClasses;


	//Add Class Components
	JTextField txtClassName = new JTextField(), txtNumHomework = new JTextField(), txtHwPct = new JTextField(), txtHwDropped = new JTextField();//Homework text feild
	JTextField txtNumTests = new JTextField(), txtPctTests =new JTextField();//Tests Textfield
	JTextField txtNumOther = new JTextField(), txtPctOther = new JTextField();// Other Textfield
	JTextField txtFinalPct = new JTextField();//Final Textfield
	JCheckBox boxHomework = new JCheckBox(), boxTests = new JCheckBox(), boxOther = new JCheckBox(), boxFinal = new JCheckBox();//Checkboxs

	JLabel lblBoxHomework =  new JLabel("Homework"), lblBoxTests = new JLabel("Tests"), lblBoxOther = new JLabel("Other"), lblBoxFinal = new JLabel("Final");//Checkboxs labels
	JLabel lblTxtNumHomework = new JLabel("Num HW"), lblTxtHwPct =new JLabel("HW Percentage"), lblTxtHwDropped = new JLabel("Num Dropped");//Homework labels
	JLabel lblTxtNumTests = new JLabel("Num Tests"), lblTxtPctTests = new JLabel("Test %(CSV)");//Tests lables
	JLabel lblTxtNumOther = new JLabel("Num Other"), lblTxtPctOther = new JLabel("Other %(CSV)");
	JLabel lblTxtFinalPct = new JLabel("Final %");


	ButtonGroup cmbTests = new ButtonGroup(), cmbOther = new ButtonGroup(), cmbFinal = new ButtonGroup();//, cmbFinal = new JComboBox({"Test1", "test2"});
	JRadioButton[] radioTests = {new JRadioButton("As given, or equal"), new JRadioButton("Worst weighted least"), new JRadioButton("Best weighted least"), new JRadioButton("First weighted least"), new JRadioButton("Last weighted least")};
	JRadioButton[] radioOther = {new JRadioButton("As given, or equal"), new JRadioButton("Worst weighted least"), new JRadioButton("Best weighted least"), new JRadioButton("First weighted least"), new JRadioButton("Last weighted least")};
	JRadioButton[] radioFinal= {new JRadioButton("Replaces Tests"), new JRadioButton("Replaces Worst X Tests"), new JRadioButton("Replaces HW Grade"), new JRadioButton("Replaces Final Grade")};

	JButton btnSubmitClass = new JButton("Submit Class");


	Container cp;
	public void init()
	{
		cp = getContentPane();

		//MAIN FRAME
		//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		main.setLayout(new BorderLayout());
		mainNorth.setLayout(new FlowLayout());
		mainCenter.setLayout(new FlowLayout());
		mainSouth.setLayout(new FlowLayout());

		//Add list of available semesters - BorderLayout.NORTH
		cmbSemester.addItem("Fall 09");
		cmbSemester.addItem("Spring 10");

		//Initialize class list - BorderLayout.CENTER
		lstClasses = new JList(modelClasses);
		lstClasses.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		lstClasses.setVisibleRowCount(-1);
		scrollClasses = new JScrollPane(lstClasses);
		scrollClasses.setPreferredSize(new Dimension(400,400));


		//Initialize Buttons - BorderLayout.SOUTH
		btnAddClass.setPreferredSize(new Dimension(200,50));
		btnEditClass.setPreferredSize(new Dimension(200,50));
		btnAddClass.addActionListener(new ButtonHandler());

		//ADD TO MAIN Fraame
		mainNorth.add(lblStudentName);
		mainNorth.add(cmbSemester);
		mainCenter.add(scrollClasses);
		mainSouth.add(btnAddClass);
		mainSouth.add(btnEditClass);

		main.add(mainNorth,BorderLayout.NORTH);
		main.add(mainCenter, BorderLayout.CENTER);
		main.add(mainSouth, BorderLayout.SOUTH);
		main.setPreferredSize(new Dimension(800,600));
		//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		//END MAIN FRAME



		//ADD Class Frame
		//#############################################################################
		addClass.setLayout(new BorderLayout());
		addClassNorth.setLayout(null);
		addClassSouth.setLayout(new FlowLayout());
		addClassCenter.setLayout(new GridLayout(1,4));

		//Class Frame - NORTH
		txtClassName.setBounds(10,10,200,50);
		txtClassName.setText("Enter Class Name");
		addClassNorth.setPreferredSize(new Dimension(800,100));
		addClassNorth.add(txtClassName);

		//Class Frame - CENTER
		addClassCenterHomework.setPreferredSize(new Dimension(200,500));
		addClassCenterTests.setPreferredSize(new Dimension(200,500));
		addClassCenterOther.setPreferredSize(new Dimension(200,500));
		addClassCenterFinal.setPreferredSize(new Dimension(200,500));
		addClass.setPreferredSize(new Dimension(800,500));

		addClassCenterHomework.setLayout(null);
		addClassCenterTests.setLayout(null);
		addClassCenterOther.setLayout(null);
		addClassCenterFinal.setLayout(null);





		//Class Frame - CENTER - Homework
		boxHomework.setBounds(50,50,50,50);
		lblBoxHomework.setBounds(100,50,100,50);
		txtNumHomework.setBounds(95,100, 100,30);
		lblTxtNumHomework.setBounds(5,100,90,30);
		txtHwPct.setBounds(95,150, 100,30);
		lblTxtHwPct.setBounds(5,150,90,30);
		txtHwDropped.setBounds(95,200,100,30);
		lblTxtHwDropped.setBounds(5,200, 90,30);


		addClassCenterHomework.add(boxHomework);
		addClassCenterHomework.add(txtNumHomework);
		addClassCenterHomework.add(txtHwPct);
		addClassCenterHomework.add(txtHwDropped);
		addClassCenterHomework.add(lblBoxHomework);
		addClassCenterHomework.add(lblTxtHwPct);
		addClassCenterHomework.add(lblTxtHwDropped);
		addClassCenterHomework.add(lblTxtNumHomework);


		//Class Frame - CENTER - Tests
		boxTests.setBounds(50,50,50,50);
		lblBoxTests.setBounds(100,50,100,50);
		txtNumTests.setBounds(95,100,100,30);
		lblTxtNumTests.setBounds(20,100,90,30);
		txtPctTests.setBounds(95,150, 100,30);
		lblTxtPctTests.setBounds(20,150,90,30);


		addClassCenterTests.add(boxTests);
		addClassCenterTests.add(lblBoxTests);
		addClassCenterTests.add(txtNumTests);
		addClassCenterTests.add(txtPctTests);
		addClassCenterTests.add(lblTxtNumTests);
		addClassCenterTests.add(lblTxtPctTests);
		for(int i = 0 ;i<radioTests.length;i++)
		{
			cmbTests.add(radioTests[i]);
			radioTests[i].setBounds(20,180+i*30,200,30);
			addClassCenterTests.add(radioTests[i]);
		}

		//Class Frame - CENTER - Other
		boxOther.setBounds(50,50,50,50);
		lblBoxOther.setBounds(100,50,100,50);

		txtNumOther.setBounds(95,100,100,30);
		lblTxtNumOther.setBounds(20,100,90,30);
		txtPctOther.setBounds(95,150, 100,30);
		lblTxtPctOther.setBounds(20,150,90,30);

		addClassCenterOther.add(boxOther);
		addClassCenterOther.add(lblBoxOther);
		addClassCenterOther.add(txtNumOther);
		addClassCenterOther.add(lblTxtNumOther);
		addClassCenterOther.add(txtPctOther);
		addClassCenterOther.add(lblTxtPctOther);


		for(int i = 0 ;i<radioOther.length;i++)
		{
			cmbOther.add(radioOther[i]);
			radioOther[i].setBounds(20,180+i*30,200,30);
			addClassCenterOther.add(radioOther[i]);
		}

		//Class Frame - CNETER - Final


		boxFinal.setBounds(50,50,50,50);
		lblBoxFinal.setBounds(100,50,100,50);
		txtFinalPct.setBounds(95,100,100,30);
		lblTxtFinalPct.setBounds(20,100,90,30);

		addClassCenterFinal.add(boxFinal);
		addClassCenterFinal.add(lblBoxFinal);
		addClassCenterFinal.add(lblTxtFinalPct);
		addClassCenterFinal.add(txtFinalPct);
			for(int i = 0 ;i<radioFinal.length;i++)
			{
			cmbFinal.add(radioFinal[i]);
			radioFinal[i].setBounds(20,180+i*30,200,30);
			addClassCenterFinal.add(radioFinal[i]);
		}




		addClassCenter.add(addClassCenterHomework);
		addClassCenter.add(addClassCenterTests);
		addClassCenter.add(addClassCenterOther);
		addClassCenter.add(addClassCenterFinal);

		addClass.add(addClassNorth, BorderLayout.NORTH);
		addClass.add(addClassSouth, BorderLayout.SOUTH);
		addClass.add(addClassCenter,BorderLayout.CENTER);


		//Class Frame - SOUTH

		addClassSouth.add(btnSubmitClass);
		//#############################################################################
		//END Class Frame



		//ADD TO OVERALL CONTAINER
		cp.setLayout(new FlowLayout());
		cp.add(main);

		setSize(800,700);

	}
	public class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Object source = e.getSource();

			if(source == btnAddClass)
			{
				cp.removeAll();
				cp.add(addClass);
				setSize(801,700);//Redraw window

			}
		}
	}
}