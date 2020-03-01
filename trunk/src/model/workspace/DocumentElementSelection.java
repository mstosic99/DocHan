package model.workspace;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class DocumentElementSelection implements Transferable, ClipboardOwner {

	public static DataFlavor elementFlavour;
	private DataFlavor[] supportedFlavours = { elementFlavour };
	public Document document = null;

	public DocumentElementSelection(Document document) {
		this.document = document;
		try {
			elementFlavour = new DataFlavor(Class.forName("model.workspace.Document"), ("Document"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
		return supportedFlavours;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		// TODO Auto-generated method stub
		return (flavor.equals(elementFlavour));
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		if (flavor.equals(elementFlavour))
			return document;
		else
			throw new UnsupportedFlavorException(elementFlavour);
	}

}
