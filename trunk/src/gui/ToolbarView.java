package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import actions.document.*;
import actions.page.*;
import actions.project.*;
import app.Main;

public class ToolbarView extends JToolBar {

	/*
	 * private JButton newProjectBTN; private JButton openBTN; private JButton
	 * saveBTN; private JButton saveAllBTN; private JButton infoBTN; private
	 * JComboBox<String> cmbLAF;
	 */

	public ToolbarView() {

		this.addSeparator();
		this.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
		this.add(MainFrame.getInstance().getActionManager().getNewDocumentAction());
		this.add(MainFrame.getInstance().getActionManager().getNewPageAction());

		this.addSeparator();

		this.add(MainFrame.getInstance().getActionManager().getRemoveNodeAction());

		this.addSeparator();
		this.add(MainFrame.getInstance().getActionManager().getRenameAction());

		this.addSeparator();

		this.add(MainFrame.getInstance().getActionManager().getCloseTabAction());
		this.add(MainFrame.getInstance().getActionManager().getCloseAllTabsAction());

		this.addSeparator();

		this.add(MainFrame.getInstance().getActionManager().getSwitchWorkspaceAction());
		this.add(MainFrame.getInstance().getActionManager().getSaveWorkspaceAction());
		this.add(MainFrame.getInstance().getActionManager().getSaveProjectAction());
		this.add(MainFrame.getInstance().getActionManager().getSaveProjectAsAction());
		this.add(MainFrame.getInstance().getActionManager().getOpenProjectAction());

		this.addSeparator();

		this.add(MainFrame.getInstance().getActionManager().getShareDocumentAction());

		this.addSeparator();

		this.add(MainFrame.getInstance().getActionManager().getUndoAction());
		this.add(MainFrame.getInstance().getActionManager().getRedoAction());

		this.addSeparator();

		this.add(MainFrame.getInstance().getActionManager().getCopyAction());
		this.add(MainFrame.getInstance().getActionManager().getCutAction());
		this.add(MainFrame.getInstance().getActionManager().getPasteAction());
		this.add(MainFrame.getInstance().getActionManager().getDeleteSlotAction());

		this.addSeparator();

		this.add(MainFrame.getInstance().getActionManager().getInfoAction());
		// this.add(saveBTN);
		// this.add(saveAllBTN);
		// this.add(infoBTN);

		/*
		 * UIManager.LookAndFeelInfo[] laf=UIManager.getInstalledLookAndFeels(); for
		 * (int i=0;i<laf.length;i++){ cmbLAF.addItem(laf[i].getClassName()); if
		 * (UIManager.getLookAndFeel().getName().equals(laf[i].getName())){
		 * System.out.println(i); cmbLAF.setSelectedIndex(i); } }
		 * 
		 * cmbLAF.addActionListener(new ActionListener(){
		 * 
		 * 
		 * public void actionPerformed(ActionEvent a) { JComboBox<String> cb =
		 * (JComboBox<String>)a.getSource(); try {
		 * 
		 * UIManager.setLookAndFeel((String)cb.getSelectedItem());
		 * SwingUtilities.updateComponentTreeUI(MainFrame.getInstance()); } catch
		 * (Exception e) { e.printStackTrace(); }
		 * 
		 * 
		 * }
		 * 
		 * 
		 * });
		 * 
		 * 
		 * add(cmbLAF);
		 */
		setFloatable(false);
		setBackground(new Color(255, 255, 204));

	}
}
