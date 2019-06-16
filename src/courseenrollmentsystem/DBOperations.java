/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseenrollmentsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Dilsha Hettiarachchi
 */
public class DBOperations {

    String url = "jdbc:mysql://localhost:3306/course_enrollment_db";
    String usrname = "root";
    String passwrd = "";
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    int checkUsernamePassword(String username, String password) {
        try {

            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "Select * from login where username=? and password=?";

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);

            rs = pst.executeQuery();
            if (rs.next()) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            System.out.print(e);
            return 2;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }
    boolean changePassword(String username, String newPassword) {
        try {

            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "UPDATE login SET password='"+newPassword+"' WHERE username='"+username+"'";

            pst = (PreparedStatement) con.prepareStatement(query);
            
            pst.executeUpdate();
            return true;
             
        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }
    //to fill the course combo box
    String[] fillCombo(String faculty, String ctype) {
        String[] dd = new String[20];
        int i = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, usrname, passwrd);
            String query = "Select cname from course where faculty = ? and ctype= ?";
            pst = con.prepareStatement(query);
            pst.setString(1, faculty);
            pst.setString(2, ctype);
            rs = pst.executeQuery();
            while (rs.next()) {
                dd[i] = rs.getString("cname");
                i++;
            }
            return dd;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }
    //to fill the subject combo box
    String[] fillSubjectCombo(String course, int year, int semester) {
        String[] dd = new String[20];
        int i = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, usrname, passwrd);
            String query = "Select distinct subjectname from subject where course = ? and year= ? and semester= ?";
            pst = con.prepareStatement(query);
            pst.setString(1, course);
            pst.setInt(2, year);
            pst.setInt(3, semester);
            rs = pst.executeQuery();
            while (rs.next()) {
                dd[i] = rs.getString("subjectname");
                i++;
            }
            return dd;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }
    //to fill the optional subject combo box
    String[] fillOptionalSubjectCombo(int regno, int year, int semester, String tablename) {
        String[] dd = new String[20];
        int i = 0;
        dd[i] = "none";
        i++;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, usrname, passwrd);
            String strQuery = "SELECT subject.subjectname FROM subject INNER JOIN $tablename ON subject.course = $tablename.course where $tablename.regno=? AND subject.year=? AND subject.semester=? AND subject.type='optional';";
            String query = strQuery.replace("$tablename", tablename);
            pst = con.prepareStatement(query);

            pst.setInt(1, regno);
            pst.setInt(2, year);
            pst.setInt(3, semester);
            rs = pst.executeQuery();
            while (rs.next()) {
                dd[i] = rs.getString("subject.subjectname");
                i++;
            }
            return dd;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    boolean addStudent(Student st) {
        try {

            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "INSERT INTO student VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, st.getRegNo());
            pst.setString(2, st.getFirstName());
            pst.setString(3, st.getLastName());
            pst.setInt(4, st.getAge());
            pst.setString(5, st.getGender());
            pst.setString(6, st.getAddress());
            pst.setInt(7, st.getContactNumber());
            pst.setString(8, st.getEmail());
            pst.setString(9, st.getFaculty());
            pst.setString(10, st.getCourse());
            pst.setInt(11, st.getYearOfReg());
            pst.setInt(12, st.getYear());
            pst.setString(13, st.getIntake());
            pst.setString(14, st.getAlStream());
            pst.setString(15, st.getAlSub1());
            pst.setString(16, st.getAlRes1());
            pst.setString(17, st.getAlSub2());
            pst.setString(18, st.getAlRes2());
            pst.setString(19, st.getAlSub3());
            pst.setString(20, st.getAlRes3());
            pst.setInt(21, st.getRank());

            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    ArrayList<Student> getStudent(String course, int year) {
        try {
            ArrayList<Student> list = new ArrayList<Student>();
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "SELECT * FROM student WHERE course = ? AND year = ?";

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, course);
            pst.setInt(2, year);
            rs = pst.executeQuery();

            while (rs.next()) {
                Student s = new Student();
                s.setRegNo(rs.getInt(1));
                s.setFirstName(rs.getString(2));
                s.setLastName(rs.getString(3));
                s.setAge(rs.getInt(4));
                s.setGender(rs.getString(5));
                s.setAddress(rs.getString(6));
                s.setContactNumber(rs.getInt(7));
                s.setEmail(rs.getString(8));
                s.setFaculty(rs.getString(9));
                s.setCourse(rs.getString(10));
                s.setYearOfReg(rs.getInt(11));
                s.setYear(rs.getInt(12));
                s.setIntake(rs.getString(13));
                s.setAlStream(rs.getString(14));
                s.setAlSub1(rs.getString(15));
                s.setAlRes1(rs.getString(16));
                s.setAlSub2(rs.getString(17));
                s.setAlRes2(rs.getString(18));
                s.setAlSub3(rs.getString(19));
                s.setAlRes3(rs.getString(20));
                s.setRank(rs.getInt(21));
                list.add(s);
            }
            return list;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public boolean updateStudent(Student st) {
        try {
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "UPDATE student SET firstname='" + st.getFirstName() + "',lastname='" + st.getLastName() + "',age=" + st.getAge() + ",address='" + st.getAddress() + "',gender='" + st.getGender() + "',contactnumber=" + st.getContactNumber() + ",email='" + st.getEmail() + "',faculty='" + st.getFaculty() + "',course='" + st.getCourse() + "',yearofreg=" + st.getYearOfReg() + ",year=" + st.getYear() + ",intake='" + st.getIntake() + "',alstream='" + st.getAlStream() + "',alsubject1='" + st.getAlSub1() + "',alresult1='" + st.getAlRes1() + "',alsubject2='" + st.getAlSub2() + "',alresult2='" + st.getAlRes2() + "',alsubject3='" + st.getAlSub3() + "',alresult3='" + st.getAlRes3() + "',rank=" + st.getRank() + " WHERE regno=" + st.getRegNo();

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.executeUpdate();
            System.out.println("Update student 2");
            return true;

        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public boolean deleteStudent(Student st) {
        try {
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "DELETE FROM student WHERE regno=" + st.getRegNo();

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.executeUpdate();
            System.out.println("delete student 2");
            return true;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    boolean addPostStudent(PostStudent st) {
        try {

            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "INSERT INTO poststudent VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, st.getRegNo());
            pst.setString(2, st.getFirstName());
            pst.setString(3, st.getLastName());
            pst.setInt(4, st.getAge());
            pst.setString(5, st.getGender());
            pst.setString(6, st.getAddress());
            pst.setInt(7, st.getContactNumber());
            pst.setString(8, st.getEmail());
            pst.setString(9, st.getFaculty());
            pst.setString(10, st.getCourse());
            pst.setInt(11, st.getYearOfReg());
            pst.setInt(12, st.getYear());
            pst.setString(13, st.getIntake());
            pst.setString(14, st.getQualiType());
            pst.setString(15, st.getInstitute());
            pst.setInt(16, st.getYearOfComp());

            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    ArrayList<PostStudent> getPostStudent(String course, int year) {
        try {
            ArrayList<PostStudent> list = new ArrayList<PostStudent>();
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "SELECT * FROM poststudent WHERE course = ? AND year = ?";

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, course);
            pst.setInt(2, year);
            rs = pst.executeQuery();

            while (rs.next()) {
                PostStudent s = new PostStudent();
                s.setRegNo(rs.getInt(1));
                s.setFirstName(rs.getString(2));
                s.setLastName(rs.getString(3));
                s.setAge(rs.getInt(4));
                s.setGender(rs.getString(5));
                s.setAddress(rs.getString(6));
                s.setContactNumber(rs.getInt(7));
                s.setEmail(rs.getString(8));
                s.setFaculty(rs.getString(9));
                s.setCourse(rs.getString(10));
                s.setYearOfReg(rs.getInt(11));
                s.setYear(rs.getInt(12));
                s.setIntake(rs.getString(13));
                s.setQualiType(rs.getString(14));
                s.setInstitute(rs.getString(15));
                s.setYearOfComp(rs.getInt(16));

                list.add(s);
            }
            return list;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public boolean updatePostStudent(PostStudent st) {
        try {
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "UPDATE poststudent SET firstname='" + st.getFirstName() + "',lastname='" + st.getLastName() + "',age=" + st.getAge() + ",address='" + st.getAddress() + "',gender='" + st.getGender() + "',contactnumber=" + st.getContactNumber() + ",email='" + st.getEmail() + "',faculty='" + st.getFaculty() + "',course='" + st.getCourse() + "',yearofreg=" + st.getYearOfReg() + ",year=" + st.getYear() + ",intake='" + st.getIntake() + "',qualitype='" + st.getQualiType() + "',institute='" + st.getInstitute() + "',yearofcomp=" + st.getYearOfComp() + " WHERE regno=" + st.getRegNo();

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.executeUpdate();
            System.out.println("Update student 2");
            return true;

        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public boolean deletePostStudent(PostStudent st) {
        try {
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "DELETE FROM poststudent WHERE regno=" + st.getRegNo();

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.executeUpdate();
            System.out.println("delete student 2");
            return true;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    boolean addLecturer(Lecturer lec) {
        try {

            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "INSERT INTO lecturer VALUES (?,?,?,?,?,?,?,?)";

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, lec.getLecID());
            pst.setString(2, lec.getLecName());
            pst.setInt(3, lec.getAge());
            pst.setString(4, lec.getGender());
            pst.setString(5, lec.getAddress());
            pst.setInt(6, lec.getContactNumber());
            pst.setString(7, lec.getEmail());
            pst.setString(8, lec.getRoomNo());
            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public boolean updateLecturer(Lecturer lec) {
        try {
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "UPDATE lecturer SET lecname='" + lec.getLecName() + "',age=" + lec.getAge() + ",address='" + lec.getAddress() + "',gender='" + lec.getGender() + "',contactnumber=" + lec.getContactNumber() + ",email='" + lec.getEmail() + "',roomno='"+lec.getRoomNo()+"' WHERE lecid=" + lec.getLecID();

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.executeUpdate();
            System.out.println("Update lecturer 2");
            return true;

        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    ArrayList<Lecturer> getLecturer() {
        try {
            ArrayList<Lecturer> list = new ArrayList<Lecturer>();
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "SELECT * FROM lecturer";

            pst = (PreparedStatement) con.prepareStatement(query);

            rs = pst.executeQuery();

            while (rs.next()) {
                Lecturer l = new Lecturer();
                l.setLecID(rs.getInt(1));
                l.setLecName(rs.getString(2));
                l.setAge(rs.getInt(3));
                l.setGender(rs.getString(4));
                l.setAddress(rs.getString(5));
                l.setContactNumber(rs.getInt(6));
                l.setEmail(rs.getString(7));
                l.setRoomNo(rs.getString(8));
                
                list.add(l);
            }
            return list;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public boolean deleteLecturer(Lecturer lec) {
        try {
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "DELETE FROM lecturer WHERE lecid=" + lec.getLecID();

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.executeUpdate();
            System.out.println("delete lecturer 2");
            return true;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    
    boolean addInstructor(Instructor ins) {
        try {

            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "INSERT INTO instructor VALUES (?,?,?,?,?,?,?,?,?,?)";

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, ins.getInstructorID());
            pst.setString(2, ins.getInstructorName());
            pst.setInt(3, ins.getAge());
            pst.setString(4, ins.getGender());
            pst.setString(5, ins.getAddress());
            pst.setInt(6, ins.getContactNumber());
            pst.setString(7, ins.getEmail());
            pst.setString(8, ins.getSubject());
            pst.setString(9, ins.getRoomNo());
            pst.setString(10, ins.getQualifications());

            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    
    public boolean updateInstructor(Instructor ins) {
        try {
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "UPDATE instructor SET instructorname='" + ins.getInstructorName() + "',age=" + ins.getAge() + ",address='" + ins.getAddress() + "',gender='" + ins.getGender() + "',contactnumber=" + ins.getContactNumber() + ",email='" + ins.getEmail() + "',subject='"+ins.getSubject()+"',roomno='"+ins.getRoomNo()+"',qualifications='"+ins.getQualifications()+"' WHERE instructorid=" + ins.getInstructorID();

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.executeUpdate();
            System.out.println("Update instructor");
            return true;

        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    ArrayList<Instructor> getInstructor() {
        try {
            ArrayList<Instructor> list = new ArrayList<Instructor>();
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "SELECT * FROM instructor";

            pst = (PreparedStatement) con.prepareStatement(query);

            rs = pst.executeQuery();

            while (rs.next()) {
                Instructor ins = new Instructor();
                ins.setInstructorID(rs.getInt(1));
                ins.setInstructorName(rs.getString(2));
                ins.setAge(rs.getInt(3));
                ins.setGender(rs.getString(4));
                ins.setAddress(rs.getString(5));
                ins.setContactNumber(rs.getInt(6));
                ins.setEmail(rs.getString(7));
                ins.setSubject(rs.getString(8));
                ins.setRoomNo(rs.getString(9));
                ins.setQualifications(rs.getString(10));
                

                list.add(ins);
            }
            return list;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    public boolean deleteInstructor(Instructor ins) {
        try {
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "DELETE FROM instructor WHERE instructorid=" + ins.getInstructorID();

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.executeUpdate();
            System.out.println("delete instructor");
            return true;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    

    boolean addSelectedSubject(SelectedSubject selSub) {
        try {

            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "INSERT INTO selectedsubject VALUES (?,?,?,?,?,?,?,?)";

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, selSub.getRegNo());
            pst.setInt(2, selSub.getYear());
            pst.setInt(3, selSub.getSemester());
            pst.setString(4, selSub.getCType());
            pst.setString(5, selSub.getSub1());
            pst.setString(6, selSub.getSub2());
            pst.setString(7, selSub.getSub3());
            pst.setString(8, selSub.getSub4());

            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    //get the credit value of the subject
    int getCreditValue(String subjectName) {
        try {
            int c = 0;
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "SELECT credits FROM subject WHERE subjectname = ?";

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, subjectName);

            rs = pst.executeQuery();

            while (rs.next()) {

                c = rs.getInt(1);

            }
            return c;
        } catch (Exception e) {
            System.out.print(e);
            return 0;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    //checking optional subject added for the relevant student already
    boolean checkOptionalAdded(int regNo, int year, int semester, String cType) {
        try {

            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "Select * from selectedsubject where regno=? AND year=? AND semester=? AND ctype=?";

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, regNo);
            pst.setInt(2, year);
            pst.setInt(3, semester);
            pst.setString(4, cType);

            rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    SelectedSubject getSelectedSubject(int regNo, int year, int semester, String cType) {
        try {

            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "SELECT * FROM selectedsubject WHERE regno = ? AND year = ? AND semester = ? AND ctype = ?";

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, regNo);
            pst.setInt(2, year);
            pst.setInt(3, semester);
            pst.setString(4, cType);
            rs = pst.executeQuery();

            while (rs.next()) {
                SelectedSubject s = new SelectedSubject();
                s.setRegNo(rs.getInt(1));
                s.setYear(rs.getInt(2));
                s.setSemester(rs.getInt(3));
                s.setCType(rs.getString(4));
                s.setSub1(rs.getString(5));
                s.setSub2(rs.getString(6));
                s.setSub3(rs.getString(7));
                s.setSub4(rs.getString(8));

                return s;
            }
            return null;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    boolean updateSelectedSubject(SelectedSubject selSub) {
        try {
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "UPDATE selectedsubject SET subject1='" + selSub.getSub1() + "',subject2='" + selSub.getSub2() + "',subject3='" + selSub.getSub3() + "',subject4='" + selSub.getSub4() + "' WHERE regno=" + selSub.getRegNo() + " AND year=" + selSub.getYear() + " AND semester=" + selSub.getSemester() + " AND ctype='" + selSub.getCType() + "'";

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    ArrayList<Payment> getCompulsorySubject(int regno, int year, int semester, String tablename) {
        try {
            ArrayList<Payment> list = new ArrayList<Payment>();
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String strQuery = "SELECT subject.subjectname,subject.fee FROM subject INNER JOIN $tablename ON subject.course = $tablename.course where $tablename.regno=? AND subject.year=? AND subject.semester=? AND subject.type='compulsory';";
            String query = strQuery.replace("$tablename", tablename);
            pst = con.prepareStatement(query);

            pst.setInt(1, regno);
            pst.setInt(2, year);
            pst.setInt(3, semester);
            rs = pst.executeQuery();

            while (rs.next()) {
                Payment p = new Payment();
                p.setSubject(rs.getString(1));
                p.setFee(rs.getInt(2));

                list.add(p);
            }
            return list;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    Payment getOptionalSubject1(int regno, int year, int semester, String tablename) {
        try {
            Payment p = new Payment();
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String strQuery = "SELECT subject.subjectname,subject.fee FROM subject INNER JOIN $tablename ON subject.course = $tablename.course INNER JOIN selectedsubject ON subject.subjectname=selectedsubject.subject1 where $tablename.regno=selectedsubject.regno AND $tablename.regno=? AND subject.year=? AND subject.semester=?;";
            String query = strQuery.replace("$tablename", tablename);
            pst = con.prepareStatement(query);

            pst.setInt(1, regno);
            pst.setInt(2, year);
            pst.setInt(3, semester);
            rs = pst.executeQuery();

            while (rs.next()) {

                p.setSubject(rs.getString(1));
                p.setFee(rs.getInt(2));

            }
            return p;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    Payment getOptionalSubject2(int regno, int year, int semester, String tablename) {
        try {
            Payment p = new Payment();
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String strQuery = "SELECT subject.subjectname,subject.fee FROM subject INNER JOIN $tablename ON subject.course = $tablename.course INNER JOIN selectedsubject ON subject.subjectname=selectedsubject.subject2 where $tablename.regno=selectedsubject.regno AND $tablename.regno=? AND subject.year=? AND subject.semester=?;";
            String query = strQuery.replace("$tablename", tablename);
            pst = con.prepareStatement(query);

            pst.setInt(1, regno);
            pst.setInt(2, year);
            pst.setInt(3, semester);
            rs = pst.executeQuery();

            while (rs.next()) {

                p.setSubject(rs.getString(1));
                p.setFee(rs.getInt(2));

            }
            return p;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    Payment getOptionalSubject3(int regno, int year, int semester, String tablename) {
        try {
            Payment p = new Payment();
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String strQuery = "SELECT subject.subjectname,subject.fee FROM subject INNER JOIN $tablename ON subject.course = $tablename.course INNER JOIN selectedsubject ON subject.subjectname=selectedsubject.subject3 where $tablename.regno=selectedsubject.regno AND $tablename.regno=? AND subject.year=? AND subject.semester=?;";
            String query = strQuery.replace("$tablename", tablename);
            pst = con.prepareStatement(query);

            pst.setInt(1, regno);
            pst.setInt(2, year);
            pst.setInt(3, semester);
            rs = pst.executeQuery();

            while (rs.next()) {

                p.setSubject(rs.getString(1));
                p.setFee(rs.getInt(2));

            }
            return p;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    Payment getOptionalSubject4(int regno, int year, int semester, String tablename) {
        try {
            Payment p = new Payment();
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String strQuery = "SELECT subject.subjectname,subject.fee FROM subject INNER JOIN $tablename ON subject.course = $tablename.course INNER JOIN selectedsubject ON subject.subjectname=selectedsubject.subject4 where $tablename.regno=selectedsubject.regno AND $tablename.regno=? AND subject.year=? AND subject.semester=?;";
            String query = strQuery.replace("$tablename", tablename);
            pst = con.prepareStatement(query);

            pst.setInt(1, regno);
            pst.setInt(2, year);
            pst.setInt(3, semester);
            rs = pst.executeQuery();

            while (rs.next()) {

                p.setSubject(rs.getString(1));
                p.setFee(rs.getInt(2));

            }
            return p;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    boolean addSubject(Subject sub) {
        try {

            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "INSERT INTO subject VALUES (?,?,?,?,?,?,?,?,?)";

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, sub.getSubjectCode());
            pst.setString(2, sub.getSubjectName());
            pst.setInt(3, sub.getYear());
            pst.setInt(4, sub.getSemester());
            pst.setString(5, sub.getType());
            pst.setInt(6, sub.getCredits());
            pst.setInt(7, sub.getFees());
            pst.setString(8, sub.getCourse());
            pst.setString(9, sub.getLecturer());

            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    //get the all courses of the university
    String[] fillCourseCombo() {
        String[] dd = new String[60];
        int i = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, usrname, passwrd);
            String query = "Select cname from course;";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                dd[i] = rs.getString("cname");
                i++;
            }
            return dd;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }
    //get the all lecturers of the university
    String[] fillLecturerCombo() {
        String[] dd = new String[20];
        int i = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, usrname, passwrd);
            String query = "Select lecname from lecturer;";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                dd[i] = rs.getString("lecname");
                i++;
            }
            return dd;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    ArrayList<Subject> getSubject(String course, int year, int semester) {
        try {
            ArrayList<Subject> list = new ArrayList<Subject>();
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "SELECT * FROM subject WHERE course = ? AND year = ? AND semester=?";

            pst = (PreparedStatement) con.prepareStatement(query);

            pst.setString(1, course);
            pst.setInt(2, year);
            pst.setInt(3, semester);
            rs = pst.executeQuery();

            while (rs.next()) {
                Subject s = new Subject();
                s.setSubjectCode(rs.getString(1));
                s.setSubjectName(rs.getString(2));
                s.setYear(rs.getInt(3));
                s.setSemester(rs.getInt(4));
                s.setType(rs.getString(5));
                s.setCredits(rs.getInt(6));
                s.setFees(rs.getInt(7));
                s.setCourse(rs.getString(8));
                s.setLecturer(rs.getString(9));

                list.add(s);
            }
            return list;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public boolean updateSubject(Subject sub) {
        try {
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "UPDATE subject SET subjectname='" + sub.getSubjectName() + "',year=" + sub.getYear() + ",semester=" + sub.getSemester() + ",type='" + sub.getType() + "',credits=" + sub.getCredits() + ",fee=" + sub.getFees() + ",course='" + sub.getCourse() + "',lecturer='" + sub.getLecturer() + "' WHERE subjectcode='" + sub.getSubjectCode() + "'";

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public boolean deleteSubject(Subject sub) {
        try {
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "DELETE FROM subject WHERE subjectcode='" + sub.getSubjectCode() + "'";

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    //get the all subjects
    String[] fillSubjectCombo() {
        String[] dd = new String[100];
        int i = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, usrname, passwrd);
            String query = "Select subjectname from subject";
            pst = con.prepareStatement(query);

            rs = pst.executeQuery();
            while (rs.next()) {
                dd[i] = rs.getString("subjectname");
                i++;
            }
            return dd;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    String[] fillAssignmentNamesCombo(String subject) {
        String[] dd = new String[20];
        int i = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, usrname, passwrd);
            String query = "Select distinct assignmentname from assignment where subject=?";
            pst = con.prepareStatement(query);
            pst.setString(1, subject);
            rs = pst.executeQuery();
            while (rs.next()) {
                dd[i] = rs.getString("assignmentname");
                i++;
            }
            return dd;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    boolean addAssignment(Assignment assign) {
        try {

            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "INSERT INTO assignment VALUES (?,?,?,?,?,?,?,?,?)";

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, assign.getRegNo());
            pst.setString(2, assign.getStudentType());
            pst.setInt(3, assign.getYear());
            pst.setInt(4, assign.getSemester());
            pst.setString(5, assign.getSubject());
            pst.setString(6, assign.getAssignmentName());
            pst.setFloat(7, assign.getWeight());
            pst.setFloat(8, assign.getResult());
            pst.setFloat(9, assign.getResultWithWeight());

            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    ArrayList<Assignment> getAssignment(String subject, String assignmentName) {
        try {
            ArrayList<Assignment> list = new ArrayList<Assignment>();
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "SELECT * FROM assignment WHERE subject = ? AND assignmentname = ?";

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, subject);
            pst.setString(2, assignmentName);
            rs = pst.executeQuery();

            while (rs.next()) {
                Assignment assign = new Assignment();
                assign.setRegNo(rs.getInt(1));
                assign.setStudentType(rs.getString(2));
                assign.setYear(rs.getInt(3));
                assign.setSemester(rs.getInt(4));
                assign.setSubject(rs.getString(5));
                assign.setAssignmentName(rs.getString(6));
                assign.setWeight(rs.getFloat(7));
                assign.setResult(rs.getFloat(8));
                assign.setResultWithWeight(rs.getFloat(9));

                list.add(assign);
            }
            return list;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    //get the course the relevant student follows
    String getCourse(int regNo, String tableName) {
        try {
            String c = null;
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String strQuery = "SELECT course FROM $tablename WHERE regno = ?";

            String query = strQuery.replace("$tablename", tableName);
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, regNo);

            rs = pst.executeQuery();

            while (rs.next()) {

                c = rs.getString(1);

            }
            return c;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    //count the final results of the subject
    float FindFinalResult(int regNo, String subject) {
        float total=0,x;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, usrname, passwrd);
            String query = "Select resultwithweight from assignment where regno=? and subject=?";
            pst = con.prepareStatement(query);
            pst.setInt(1, regNo);
            pst.setString(2, subject);
            rs = pst.executeQuery();
            while (rs.next()) {
                x = rs.getFloat("resultwithweight");
                total+=x;
            }
            return total;
        } catch (Exception ex) {
            System.out.println(ex);
            return 0;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }
    boolean addGrade(Grade grade) {
        try {

            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "INSERT INTO grade VALUES (?,?,?,?,?,?,?)";

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, grade.getRegNo());
            pst.setString(2, grade.getStudentType());
            pst.setInt(3, grade.getYear());
            pst.setInt(4, grade.getSemester());
            pst.setString(5, grade.getSubject());
            pst.setFloat(6, grade.getFinalResult());
            pst.setString(7, grade.getGrade());

            pst.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    ArrayList<Grade> getGrade(String subject) {
        try {
            ArrayList<Grade> list = new ArrayList<Grade>();
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "SELECT * FROM grade WHERE subject = ?";

            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setString(1, subject);
            
            rs = pst.executeQuery();

            while (rs.next()) {
                Grade grade = new Grade();
                grade.setRegNo(rs.getInt(1));
                grade.setStudentType(rs.getString(2));
                grade.setYear(rs.getInt(3));
                grade.setSemester(rs.getInt(4));
                grade.setSubject(rs.getString(5));
                grade.setFinalResult(rs.getFloat(6));
                grade.setGrade(rs.getString(7));

                list.add(grade);
            }
            return list;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    //get subject grades to genarate email message
    
    ArrayList<SubjectGrade> getSubjectGrades(int regno,String studentType, int year, int semester) {
        try {
            ArrayList<SubjectGrade> list = new ArrayList<SubjectGrade>();
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String query = "SELECT subject,grade FROM grade WHERE regNo = ? AND studenttype=? AND year=? AND semester=?";
            
            pst = con.prepareStatement(query);

            pst.setInt(1, regno);
            pst.setString(2, studentType);
            pst.setInt(3, year);
            pst.setInt(4, semester);
            rs = pst.executeQuery();

            while (rs.next()) {
                SubjectGrade sg = new SubjectGrade();
                sg.setSubject(rs.getString(1));
                sg.setGrade(rs.getString(2));

                list.add(sg);
            }
            return list;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    //get email address to send email to the relavant student
    String getEmail(int regNo, String tableName) {
        try {
            String c = null;
            con = (Connection) DriverManager.getConnection(url, usrname, passwrd);//get the connection
            String strQuery = "SELECT email FROM $tablename WHERE regno = ?";

            String query = strQuery.replace("$tablename", tableName);
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, regNo);

            rs = pst.executeQuery();

            while (rs.next()) {

                c = rs.getString(1);

            }
            return c;
        } catch (Exception e) {
            System.out.print(e);
            return null;
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
}
