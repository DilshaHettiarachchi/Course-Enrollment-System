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
public class AssignmentDetails extends AbstractTableModel {
    private static final String[] COLUMN_NAMES = {"Reg No","Result"};
    private static ArrayList<Assignment> list;
    
    AssignmentDetails (ArrayList<Assignment> assignList){
        list= assignList;
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
                return list.get(rowIndex).getRegNo();
            case 1:
                return list.get(rowIndex).getResult();
            default:
                return "Error";
                
        }
    }
    
}
