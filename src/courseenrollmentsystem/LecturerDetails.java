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
public class LecturerDetails extends AbstractTableModel{
    private static final String[] COLUMN_NAMES ={"Lecturer ID","Lecturer Name","Email","Contact No","Room No"};
    private static ArrayList<Lecturer> list;
    
    LecturerDetails(ArrayList<Lecturer> lecList){
        list = lecList;
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
                return list.get(rowIndex).getLecID();
            case 1:
                return list.get(rowIndex).getLecName();
            case 2:
                return list.get(rowIndex).getEmail();
            case 3:
                return list.get(rowIndex).getContactNumber();
            case 4:
                return list.get(rowIndex).getRoomNo();
            default:
                return "Error";
        }
    }
    
}
