import javax.swing.*;
import javax.swing.border.TitledBorder;

public class GPACalculatorGUI {

	    private static GradeManager gradeManager = new GradeManager();
	    private static JTextArea displayArea;
	    private static JTextField nameField, numericField, creditField, yearField;
	    private static JComboBox<GradeLetter> gradeComboBox;
	    private static JComboBox<ClassType> classComboBox;
	    private static JComboBox<Semester> semesterComboBox;
	  

	    public static void main(String[] args) {
	        JFrame frame = new JFrame("Grade Calculator");
	        frame.setSize(760, 540);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLayout(null);

	      
	        JPanel inputPanel = new JPanel(null);
	        inputPanel.setBounds(10, 10, 315, 420);
	        inputPanel.setBorder(new TitledBorder("Class Information"));
	        frame.add(inputPanel);

	        JLabel nameLabel = new JLabel("Class Name:");
	        nameLabel.setBounds(15, 30, 100, 25);
	        inputPanel.add(nameLabel);

	        nameField = new JTextField();
	        nameField.setBounds(120, 30, 160, 25);
	        inputPanel.add(nameField);

	        JLabel letterLabel = new JLabel("Letter Grade:");
	        letterLabel.setBounds(15, 120, 100, 25);
	        inputPanel.add(letterLabel);

	     

	        JLabel numericLabel = new JLabel("Numeric Grade:");
	        numericLabel.setBounds(15, 180, 120, 25);
	        inputPanel.add(numericLabel);

	        gradeComboBox = new JComboBox<>(GradeLetter.values());
	        gradeComboBox.setBounds(120, 120, 160, 25);
	        inputPanel.add(gradeComboBox);
	        
	        JPanel actionPanel = new JPanel(null);
	        actionPanel.setBounds(330, 10, 410, 92);
	        actionPanel.setBorder(new TitledBorder("Actions"));
	        frame.add(actionPanel);

	        JButton addButton = new JButton("Add Class");
	        addButton.setBounds(10, 25, 130, 30);
	        actionPanel.add(addButton);

	        JButton removeButton = new JButton("Remove Class");
	        removeButton.setBounds(143, 25, 130, 30);
	        actionPanel.add(removeButton);

	        JButton searchButton = new JButton("Search Class");
	        searchButton.setBounds(275, 25, 130, 30);
	        actionPanel.add(searchButton);
	        
	        JLabel creditLabel = new JLabel("Credit Hours:");
	        creditLabel.setBounds(15, 150, 100, 25);
	        inputPanel.add(creditLabel);

	        creditField = new JTextField();
	        creditField.setBounds(120, 150, 160, 25);
	        inputPanel.add(creditField);

	        JLabel typeLabel = new JLabel("Class Type:");
	        typeLabel.setBounds(15, 60, 100, 25);
	        inputPanel.add(typeLabel);
	        
	        classComboBox = new JComboBox<>(ClassType.values());
	        classComboBox.setBounds(120, 60, 160, 25);
	        inputPanel.add(classComboBox);

	        numericField = new JTextField();
	        numericField.setBounds(120, 180, 160, 25);
	        inputPanel.add(numericField);
	        
	        JLabel semesterLabel = new JLabel("Semester:");
	        semesterLabel.setBounds(15, 235, 100, 25);
	        inputPanel.add(semesterLabel);

	        semesterComboBox = new JComboBox<>(Semester.values());
	        semesterComboBox.setBounds(120, 235, 160, 25);
	        inputPanel.add(semesterComboBox);
	        
	        JButton semesterGPAButton = new JButton("Semester GPA");
	        semesterGPAButton.setBounds(275, 60, 130, 30);
	        actionPanel.add(semesterGPAButton);

	        JLabel yearLabel = new JLabel("Year:");
	        yearLabel.setBounds(15, 265, 100, 25);
	        inputPanel.add(yearLabel);

	        yearField = new JTextField();
	        yearField.setBounds(120, 265, 160, 25);
	        inputPanel.add(yearField);
	        
	        JButton sortGradeButton = new JButton("Sort by Grade");
	        sortGradeButton.setBounds(10, 60, 130, 30);
	        actionPanel.add(sortGradeButton);

	        JButton sortSemesterButton = new JButton("Sort by Semester");
	        sortSemesterButton.setBounds(140, 60, 135, 30);
	        actionPanel.add(sortSemesterButton);
	        
	       

	        JPanel displayPanel = new JPanel(null);
	        displayPanel.setBounds(330, 100, 410, 330);
	        displayPanel.setBorder(new TitledBorder("Class Records"));
	        frame.add(displayPanel);

	        displayArea = new JTextArea();
	        displayArea.setEditable(false);
	        JScrollPane scroll = new JScrollPane(displayArea);
	        scroll.setBounds(15, 25, 385, 210);
	        displayPanel.add(scroll);

	        JButton clearButton = new JButton("Clear Input Fields");
	        clearButton.setBounds(115, 250, 190, 30);
	        displayPanel.add(clearButton);

	        
	        addButton.addActionListener(e -> addGrade());
	        removeButton.addActionListener(e -> removeClass());
	        searchButton.addActionListener(e -> searchClass());
	        clearButton.addActionListener(e -> clearFields());
	        sortGradeButton.addActionListener(e -> sortGrade());
	        sortSemesterButton.addActionListener(e -> sortSemester());
	        semesterGPAButton.addActionListener(e -> semesterGpa());
	        

	        frame.setVisible(true);
	    }

	    private static void addGrade() {
	        try {
	            String name = nameField.getText();
	            GradeLetter letter = (GradeLetter) gradeComboBox.getSelectedItem();
	            ClassType type = (ClassType) classComboBox.getSelectedItem();

	            int credits = Integer.parseInt(creditField.getText());
	            int numeric = 0;
	            if (!numericField.getText().isEmpty()) {
	                numeric = Integer.parseInt(numericField.getText());
	            }
	            
	            Semester semester = (Semester) semesterComboBox.getSelectedItem();
	            int year = Integer.parseInt(yearField.getText());

	            Grade g = new Grade(name, letter, type, numeric, credits, semester, year);
	            gradeManager.addGrade(g);

	            updateDisplay("Class added.");

	        } catch (Exception e) {
	            updateDisplay("Make sure all fields are properly filled out(Name, Hours, Year)");
	        }
	    }

	    private static void removeClass() {
	        String name = nameField.getText();
	        gradeManager.removeGrade(name);
	        updateDisplay("Class removed.");
	    }

	    private static void searchClass() {
	        String name = JOptionPane.showInputDialog(null, "Enter Class name:");
	        if (name == null || name.trim().isEmpty())
	        {
	        	return;
	        }

	        Grade d = gradeManager.searchGrade(name);
	        if (d == null) {
	        updateDisplay("Class not found.");
	        }
	        else {
	        	updateDisplay("Found: " + d.toString());
	        }
	    }


	    private static void updateDisplay(String message) {
	        displayArea.setText(message + "\n\n");

	        for (Grade g : gradeManager.getAllGrades()) {
	            displayArea.append(g.toString() + "\n");
	        }

	        displayArea.append("Overall GPA: " + gradeManager.calculateOverallGPA());
	        displayArea.append("\nMajor GPA: " + gradeManager.calculateMajorGPA());
	    }
	    private static void sortGrade()
	    { 
        	gradeManager.sortByGrade();
            updateDisplay("Sorted by grade.");
        }

        private static void sortSemester()
        {
            gradeManager.sortBySemester();
            updateDisplay("Sorted by semester.");
        }
        private static void semesterGpa()
        {
        	  if (yearField.getText().isEmpty()) {
        		  updateDisplay("Make sure all fields are properly filled out(Semester, Year)");
              }
        	Semester semester = (Semester) semesterComboBox.getSelectedItem();
            int year = Integer.parseInt(yearField.getText());
            
            double f = gradeManager.calculateSemesterGPA(semester, year);
            displayArea.append("\n\nGPA for " + semester + " " + year + ": " + f);
        }
          
	    

	    private static void clearFields() {
	        nameField.setText("");
	        creditField.setText("");
	        numericField.setText("");
	        yearField.setText("");
	        gradeComboBox.setSelectedIndex(0);
	        classComboBox.setSelectedIndex(0);
	        semesterComboBox.setSelectedIndex(0);
	    }
	}

