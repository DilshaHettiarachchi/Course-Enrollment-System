/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseenrollmentsystem;

/**
 *
 * @author Dilsha Hettiarachchi
 */
public class Grade {
    private int regNo;
    private String studentType;
    private int year;
    private int semester;
    private String subject;
    private float finalResult;
    private String grade;

    /**
     * @return the regNo
     */
    public int getRegNo() {
        return regNo;
    }

    /**
     * @param regNo the regNo to set
     */
    public void setRegNo(int regNo) {
        this.regNo = regNo;
    }

    /**
     * @return the studentType
     */
    public String getStudentType() {
        return studentType;
    }

    /**
     * @param studentType the studentType to set
     */
    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the semester
     */
    public int getSemester() {
        return semester;
    }

    /**
     * @param semester the semester to set
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the finalResult
     */
    public float getFinalResult() {
        return finalResult;
    }

    /**
     * @param finalResult the finalResult to set
     */
    public void setFinalResult(float finalResult) {
        this.finalResult = finalResult;
    }

    /**
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }
   
}
