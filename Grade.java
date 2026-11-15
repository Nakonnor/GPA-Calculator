
public class Grade {
    private String name;
    private GradeLetter grade;
    private ClassType type;
    private int numericGrade;
    private int creditHours;
    private Semester semester;
    private int year;

    public Grade() {
        this.name = "None";
        this.grade = GradeLetter.NONE;
        this.type = ClassType.NON_MAJOR;
        this.numericGrade = 0;
        this.creditHours = 0;
        this.semester = Semester.FALL;
        this.year = 2026;
    }

    public Grade(String name, GradeLetter grade, ClassType type,int numericGrade, int creditHours,Semester semester, int year) throws InvalidCredit
    {
    	this.setName(name);
    	this.setGradeLetter(grade);
    	this.setClassType(type);
    	this.setNumericGrade(numericGrade);
    	this.setCreditHours(creditHours);
    	this.setSemester(semester);
    	this.setYear(year);
    }

    // GETTERS
    public String getName() 
    { 
    	return name;
    }
    public GradeLetter getGradeLetter() 
    { 
    	return grade; 
    }
    public ClassType getClassType() 
    { 
    	return type; 
    }
    public int getNumericGrade() 
    { 
    	return numericGrade; 
    }
    public int getCreditHours() 
    { 
    	return creditHours; 
    }
    public Semester getSemester() {
        return semester;
    }
    public int getYear() {
        return year;
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setGradeLetter(GradeLetter grade) {
        this.grade = grade;
    }

    public void setClassType(ClassType type) {
        this.type = type;
    }

    public void setNumericGrade(int numericGrade) throws InvalidCredit {
        if (numericGrade < 0 || numericGrade > 100) {
            throw new InvalidCredit("Grade must be from 0-100");
        }
        this.numericGrade = numericGrade;
    }

    public void setCreditHours(int creditHours) throws InvalidCredit {
        if (creditHours < 1 || creditHours > 4) {
            throw new InvalidCredit("Credit hours must be 1–4");
        }
        this.creditHours = creditHours;
    }
    public void setSemester(Semester semester) {
        this.semester = semester;
    }
    public void setYear(int year) {
    	if (year < 2000 || year > 2100) {
            this.year = 2026;
        } else {
            this.year = year;
        }
    }

    // Convert letter → number
    public int getGradeValue() {
    	switch (grade) {
        case A: return 4;
        case B: return 3;
        case C: return 2;
        case D: return 1;
        case F: return 0;
        default: return 0;
    }
    }

    public String toString() {
        return "Class Name: " + name + " | " + "Grade: " + grade + " | Credits: " + creditHours + " | Type: " + type + "| Term: " + semester + " " + year;
    }
}
