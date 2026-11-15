import java.util.ArrayList;
import java.util.List;

public class GradeManager {

    private List<Grade> grades;

  
    public GradeManager() {
        grades = new ArrayList<>();
    }

  
    public void addGrade(Grade aGrade) {
        if (aGrade == null) return;

      
        for (Grade d : grades) {
            if (d.getName().equalsIgnoreCase(aGrade.getName())) {
                return;
            }
        }

        grades.add(aGrade);
        
    }

    
    public void removeGrade(String name) {
        int removeIndex = -1;

        for (int i = 0; i < grades.size(); i++) {
            if (grades.get(i).getName().equalsIgnoreCase(name)) {
                removeIndex = i;
                break;
            }
        }

        if (removeIndex == -1) return;

        grades.remove(removeIndex);
    }

  
    public Grade searchGrade(String name) {
        for (Grade d : grades) {
            if (d.getName().equalsIgnoreCase(name)) {
                return d;
            }
        }
        return null;
    }

   
    public double calculateOverallGPA() {
        double totalPoints = 0;
        int totalCredits = 0;

        for (Grade g : grades) {
        	
        	if(g.getGradeLetter() == GradeLetter.NONE) {
        		continue;
        	}
            int value = g.getGradeValue();
            int credits = g.getCreditHours();

            totalPoints += value * credits;
            totalCredits += credits;
        }

        if (totalCredits == 0) return 0;

        return totalPoints / totalCredits;
    }
    
    public double calculateMajorGPA() {
        double totalPoints = 0;
        int totalCredits = 0;

        for (Grade g : grades) {
        	
            if (g.getClassType() == ClassType.MAJOR) {
            	
            	if(g.getGradeLetter() == GradeLetter.NONE) {
            		continue;
            	}
                int value = g.getGradeValue();
                int credits = g.getCreditHours();

                totalPoints += value * credits;
                totalCredits += credits;
            }
        }

        if (totalCredits == 0) return 0;

        return totalPoints / totalCredits;
    }
    public double calculateSemesterGPA(Semester semester, int year) {
        double totalPoints = 0;
        int totalCredits = 0;

        for (Grade g : grades) {
            if (g.getSemester() == semester && g.getYear() == year) {
            	if(g.getGradeLetter() == GradeLetter.NONE) {
            		continue;
            	}
                int value = g.getGradeValue();
                int credits = g.getCreditHours();

                totalPoints += value * credits;
                totalCredits += credits;
            }
        }

        if (totalCredits == 0) return 0;

        return totalPoints / totalCredits;
    }
    public void sortByGrade() {
        grades.sort((a, b) -> b.getGradeValue() - a.getGradeValue());
    }
    private int semesterOrder(Semester s) {
        switch (s) {
            case SPRING: return 1;
            case SUMMER: return 2;
            case FALL: return 3;
            default: return 0;
        }
    }
    public void sortBySemester() {
        grades.sort((b, a) -> {
            if (a.getYear() != b.getYear()) {
                return a.getYear() - b.getYear();
            }
            return semesterOrder(a.getSemester()) - semesterOrder(b.getSemester());
        });
    }

    public List<Grade> getAllGrades() {
        return grades;
    }
}