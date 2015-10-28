
import javax.swing.AbstractCellEditor;
import javax.swing.table.TableCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class GridEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    public GridEditor() {
        
        //Set up the editor (from the table's point of view),
        //which is a button.
        //This button brings up the color chooser dialog,
        //which is the editor from the user's point of view.
        button = new JButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        this.button.setOpaque(true);
        this.button.setBackground(new Color(20,150,220));
        ed = new Editor();
       
    }

    /**
     * Handles events from the editor button and from
     * the dialog's OK button.
     */
    public void actionPerformed(ActionEvent e) {
        ed.setVisible(true);
        this.fireEditingStopped();
    }

    //Implement the one CellEditor method that AbstractCellEditor doesn't.
    public Object getCellEditorValue() {
    	//System.out.println(ed.getButtonValue());
        return ed.getButtonValue();
    }

    //Implement the one method defined by TableCellEditor.
    public Component getTableCellEditorComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 int row,
                                                 int column) {

        return this.button;
    }
    

    private static final long serialVersionUID = 1L;
    private JButton button;
    protected static final String EDIT = "edit";
    Editor ed;
    
}

