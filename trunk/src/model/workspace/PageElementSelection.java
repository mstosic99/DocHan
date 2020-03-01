package model.workspace;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

import graf.elements.SlotDevice;

public class PageElementSelection implements Transferable, ClipboardOwner {

	static public DataFlavor elementFlavor;
	private DataFlavor[] supportedFlavors = { elementFlavor };
	public ArrayList<SlotDevice> slotDevices = new ArrayList<>();
	public Document document;

	public PageElementSelection(ArrayList<SlotDevice> elements) {
		slotDevices = new ArrayList<>(elements);
		try {

			elementFlavor = new DataFlavor(Class.forName("java.util.ArrayList"), "Elements");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void lostOwnership(Clipboard clipboard, Transferable contents) {
		// TODO Auto-generated method stub

	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		// TODO Auto-generated method stub
		return supportedFlavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		// TODO Auto-generated method stub
		return (flavor.equals(elementFlavor));
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		if (flavor.equals(elementFlavor))
			return (slotDevices);
		else
			throw new UnsupportedFlavorException(elementFlavor);
	}

}
