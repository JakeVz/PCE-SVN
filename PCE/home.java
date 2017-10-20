import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.*;
import java.util.regex.*;

public class home {
	public static void main(String[] args) throws Exception
	{
		createFrame();
	}
	
	public static void createFrame() throws FileNotFoundException
	{
		//Frame
		JFrame frame = new JFrame("Home Page");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Constraints used for setting up main
		GridBagConstraints c = new GridBagConstraints();
		
		//Panels
		JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel main = new JPanel(new GridBagLayout());
		JPanel footer = new JPanel();
		
		//Everything for header below
		JLabel headText = new JLabel("Polyratings: Class Edition");
		headText.setForeground(Color.WHITE);
		headText.setFont (headText.getFont ().deriveFont (64.0f));
		header.setBackground(new Color(7,88,64));
		header.add(headText, FlowLayout.LEFT);
		
		//Everything for main below
		main.setBackground(new Color(255,238,190));
		
		//Department text
		JLabel deptText = new JLabel("Department: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		main.add(deptText,c);
		
		//Department drop down box
		ArrayList<String> deptArrayList = getDeparments();
		String[] deptArray = deptArrayList.toArray(new String[deptArrayList.size()]);
		JComboBox<String> deptList = new JComboBox <String> (deptArray);
		c.gridx = 1;
		c.gridy = 0;
		main.add(deptList,c);
		
		//Class # text
		JLabel classText = new JLabel("  Class #: ");
		c.gridx = 2;
		c.gridy = 0;
		main.add(classText,c);
	
		//Class search bar
		JTextField numSearchbar = new JTextField("Search", 3);
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		main.add(numSearchbar,c);
		
		//Class Name text
		JLabel searchText = new JLabel("Class Name: ");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		main.add(searchText,c);
		
		//Class search bar
		JTextField nameSearchbar = new JTextField("", 50);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 3;
		main.add(nameSearchbar,c);


		
		//Search button
		JButton button = new JButton("Search");
		button.setSize(new Dimension(50,50));
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 4;
		c.anchor = GridBagConstraints.CENTER;
		main.add(button,c);
		
		//Everything for footer below
		JLabel footText = new JLabel("Footer");
		footText.setForeground(Color.WHITE);
		footer.setBackground(new Color(7,88,64));
		footer.add(footText);
		
		//All panels into frame
		frame.setPreferredSize(new Dimension(1440,830));
		frame.getContentPane().add(header, BorderLayout.NORTH);
		frame.getContentPane().add(main, BorderLayout.CENTER);
		frame.getContentPane().add(footer, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}
	
	//Gets all departments for drop down box
	public static ArrayList<String> getDeparments() throws FileNotFoundException
	{
		File courses = new File("courses.txt");
		Scanner scan = new Scanner(courses);
		String department;
		ArrayList<String> list = new ArrayList<String>();
		list.add("");
		
		while(scan.hasNextLine())
		{
			department = scan.nextLine();
			Matcher match = Pattern
				.compile("\\W\\W\\W\\s([A-Z]+)\\s\\W\\W\\W")
				.matcher(department);
			if(match.find())
				list.add(match.group(1));
		}
		
		scan.close();
		return list;
	}
}
