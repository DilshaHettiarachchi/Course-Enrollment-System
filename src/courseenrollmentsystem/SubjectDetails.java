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
public class SubjectDetails extends AbstractTableModel {
    private static final String[] COLUMN_NAMES ={"Subject Code","Subject Name","Type","Fees","lecturer"};
    private static ArrayList<Subject> list;
    
    SubjectDetails(ArrayList<Subject> subList){
        list = subList;
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
                return list.get(rowIndex).getSubjectCode();
            case 1:
                return list.get(rowIndex).getSubjectName();
            case 2:
                return list.get(rowIndex).getType();
            case 3:
                return list.get(rowIndex).getFees();
            case 4:
                return list.get(rowIndex).getLecturer();
            default:
                return "Error";
        }
    }
}
