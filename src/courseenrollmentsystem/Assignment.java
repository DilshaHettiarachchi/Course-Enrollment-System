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
public class Assignment {
    private int regNo;
    private String studentType;
    private int year;
    private int semester;
    private String subject;
    private String assignmentName;
    private float weight;
    private float result;
    private float resultWithWeight;

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
     * @return the assignmentName
     */
    public String getAssignmentName() {
        return assignmentName;
    }

    /**
     * @param assignmentName the assignmentName to set
     */
    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    /**
     * @return the result
     */
    public float getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(float result) {
        this.result = result;
    }

    /**
     * @return the weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }

    /**
     * @return the resultWithWeight
     */
    public float getResultWithWeight() {
        return resultWithWeight;
    }

    /**
     * @param resultWithWeight the resultWithWeight to set
     */
    public void setResultWithWeight(float resultWithWeight) {
        this.resultWithWeight = resultWithWeight;
    }

   
    
}
