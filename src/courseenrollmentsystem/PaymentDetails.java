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
public class PaymentDetails extends AbstractTableModel {
    private static final String[] COLUMN_NAMES ={"Subject","Fee"};
    private static ArrayList<Payment> list;
    
    PaymentDetails(ArrayList<Payment> pList){
        list = pList;
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
                return list.get(rowIndex).getSubject();
            case 1:
                return list.get(rowIndex).getFee();
            default:
                return "Error";
        }
    }
}
