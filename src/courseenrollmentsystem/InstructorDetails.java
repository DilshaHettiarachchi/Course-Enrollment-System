/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package courseenrollmentsystem;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dilsha Hettiarachchi
 */
public class InstructorDetails extends AbstractTableModel {
    private static final String[] COLUMN_NAMES ={"Instructor ID","Instructor Name","Email","Contact No","Subject","Room No"};
    private static ArrayList<Instructor> list;
    
    InstructorDetails(ArrayList<Instructor> instructorList){
        list = instructorList;
    }
    
    public String getColumnName(int columnIndex){
        return COLUMN_NAMES[columnIndex];
    }
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getInstructorID();
            case 1:
                return list.get(rowIndex).getInstructorName();
            case 2:
                return list.get(rowIndex).getEmail();
            case 3:
                return list.get(rowIndex).getContactNumber();
            case 4:
                return list.get(rowIndex).getSubject();
            case 5:
                return list.get(rowIndex).getRoomNo();
            default:
                return "Error";
        }
    }
    
}
