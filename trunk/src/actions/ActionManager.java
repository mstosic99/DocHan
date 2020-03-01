package actions;

import actions.document.*;
import actions.page.*;
import actions.project.*;
import actions.save.SaveProjectAction;
import actions.save.SaveProjectAsAction;
import actions.save.SaveWorkspaceAction;
import actions.share.ChooseDestinationProjectAction;
import actions.share.ShareDocumentAction;
import actions.switchWsActions.BlankWorkspaceAction;
import actions.switchWsActions.ChooseWorkspaceAction;
import actions.switchWsActions.SwitchWorkspaceAction;

public class ActionManager {

	private static ActionManager instance;

	private CloseAllTabsAction closeAllTabsAction;
	private CloseTabAction closeTabAction;
	private InfoAction infoAction;
	private NewDocumentAction newDocumentAction;
	private NewPageAction newPageAction;
	private NewProjectAction newProjectAction;
	private RenameAction renameAction;
	private RemoveNodeAction removeNodeAction;

	private BlankWorkspaceAction blankWorkspaceAction;
	private SwitchWorkspaceAction switchWorkspaceAction;
	private ChooseWorkspaceAction chooseWorkspaceAction;
	private SaveWorkspaceAction saveWorkspaceAction;

	private SaveProjectAction saveProjectAction;
	private SaveProjectAsAction saveProjectAsAction;
	private OpenProjectAction openProjectAction;

	private RectangleAction rectangleAction;
	private CircleAction circleAction;
	private TriangleAction triangleAction;
	private RotateAction rotateAction;
	private ResizeAction resizeAction;
	private SelectAction selectAction;

	private ShareDocumentAction shareDocumentAction;
	private ChooseDestinationProjectAction chooseDestinationProjectAction;

	private UndoAction undoAction;
	private RedoAction redoAction;

	private CopyAction copyAction;
	private CutAction cutAction;
	private PasteAction pasteAction;
	private DeleteSlotAction deleteSlotAction;

	private ChooseProjectForStoringDocsAction chooseProjectForStoringDocsAction;

	private ActionManager() {
		closeAllTabsAction = new CloseAllTabsAction();
		closeTabAction = new CloseTabAction();
		infoAction = new InfoAction();
		newDocumentAction = new NewDocumentAction();
		newPageAction = new NewPageAction();
		newProjectAction = new NewProjectAction();
		renameAction = new RenameAction();
		setRemoveNodeAction(new RemoveNodeAction());

		rectangleAction = new RectangleAction();
		circleAction = new CircleAction();
		triangleAction = new TriangleAction();
		rotateAction = new RotateAction();
		resizeAction = new ResizeAction();
		selectAction = new SelectAction();

		undoAction = new UndoAction();
		redoAction = new RedoAction();

		copyAction = new CopyAction();
		cutAction = new CutAction();
		pasteAction = new PasteAction();

		deleteSlotAction = new DeleteSlotAction();

		saveProjectAction = new SaveProjectAction();
		saveProjectAsAction = new SaveProjectAsAction();
		openProjectAction = new OpenProjectAction();

		blankWorkspaceAction = new BlankWorkspaceAction();
		switchWorkspaceAction = new SwitchWorkspaceAction();
		chooseWorkspaceAction = new ChooseWorkspaceAction();
		saveWorkspaceAction = new SaveWorkspaceAction();

		shareDocumentAction = new ShareDocumentAction();
		chooseDestinationProjectAction = new ChooseDestinationProjectAction();

		chooseProjectForStoringDocsAction = new ChooseProjectForStoringDocsAction();
	}

	public static ActionManager getInstance() {
		if (instance == null) {
			instance = new ActionManager();
		}
		return instance;
	}

	public SelectAction getSelectAction() {
		return selectAction;
	}

	public void setSelectAction(SelectAction selectAction) {
		this.selectAction = selectAction;
	}

	public CopyAction getCopyAction() {
		return copyAction;
	}

	public void setCopyAction(CopyAction copyAction) {
		this.copyAction = copyAction;
	}

	public CutAction getCutAction() {
		return cutAction;
	}

	public void setCutAction(CutAction cutAction) {
		this.cutAction = cutAction;
	}

	public PasteAction getPasteAction() {
		return pasteAction;
	}

	public void setPasteAction(PasteAction pasteAction) {
		this.pasteAction = pasteAction;
	}

	public DeleteSlotAction getDeleteSlotAction() {
		return deleteSlotAction;
	}

	public void setDeleteSlotAction(DeleteSlotAction deleteSlotAction) {
		this.deleteSlotAction = deleteSlotAction;
	}

	public UndoAction getUndoAction() {
		return undoAction;
	}

	public void setUndoAction(UndoAction undoAction) {
		this.undoAction = undoAction;
	}

	public RedoAction getRedoAction() {
		return redoAction;
	}

	public void setRedoAction(RedoAction redoAction) {
		this.redoAction = redoAction;
	}

	public RotateAction getRotateAction() {
		return rotateAction;
	}

	public void setRotateAction(RotateAction rotateAction) {
		this.rotateAction = rotateAction;
	}

	public ResizeAction getResizeAction() {
		return resizeAction;
	}

	public void setResizeAction(ResizeAction resizeAction) {
		this.resizeAction = resizeAction;
	}

	public TriangleAction getTriangleAction() {
		return triangleAction;
	}

	public void setTriangleAction(TriangleAction triangleAction) {
		this.triangleAction = triangleAction;
	}

	public CircleAction getCircleAction() {
		return circleAction;
	}

	public void setCircleAction(CircleAction circleAction) {
		this.circleAction = circleAction;
	}

	public RectangleAction getRectangleAction() {
		return rectangleAction;
	}

	public void setRectangleAction(RectangleAction rectangleAction) {
		this.rectangleAction = rectangleAction;
	}

	public RenameAction getRenameAction() {
		return renameAction;
	}

	public void setRenameAction(RenameAction renameAction) {
		this.renameAction = renameAction;
	}

	public CloseAllTabsAction getCloseAllTabsAction() {
		return closeAllTabsAction;
	}

	public void setCloseAllTabsAction(CloseAllTabsAction closeAllTabsAction) {
		this.closeAllTabsAction = closeAllTabsAction;
	}

	public CloseTabAction getCloseTabAction() {
		return closeTabAction;
	}

	public void setCloseTabAction(CloseTabAction closeCurrentTabAction) {
		this.closeTabAction = closeCurrentTabAction;
	}

	public InfoAction getInfoAction() {
		return infoAction;
	}

	public void setInfoAction(InfoAction infoAction) {
		this.infoAction = infoAction;
	}

	public NewDocumentAction getNewDocumentAction() {
		return newDocumentAction;
	}

	public void setNewDocumentAction(NewDocumentAction newDocumentAction) {
		this.newDocumentAction = newDocumentAction;
	}

	public NewPageAction getNewPageAction() {
		return newPageAction;
	}

	public void setNewPageAction(NewPageAction newPageAction) {
		this.newPageAction = newPageAction;
	}

	public NewProjectAction getNewProjectAction() {
		return newProjectAction;
	}

	public void setNewProjectAction(NewProjectAction newProjectAction) {
		this.newProjectAction = newProjectAction;
	}

	public RemoveNodeAction getRemoveNodeAction() {
		return removeNodeAction;
	}

	public void setRemoveNodeAction(RemoveNodeAction removeNodeAction) {
		this.removeNodeAction = removeNodeAction;
	}

	public BlankWorkspaceAction getBlankWorkspaceAction() {
		return blankWorkspaceAction;
	}

	public void setBlankWorkspaceAction(BlankWorkspaceAction blankWorkspaceAction) {
		this.blankWorkspaceAction = blankWorkspaceAction;
	}

	public SwitchWorkspaceAction getSwitchWorkspaceAction() {
		return switchWorkspaceAction;
	}

	public void setSwitchWorkspaceAction(SwitchWorkspaceAction switchWorkspaceAction) {
		this.switchWorkspaceAction = switchWorkspaceAction;
	}

	public ChooseWorkspaceAction getChooseWorkspaceAction() {
		return chooseWorkspaceAction;
	}

	public void setChooseWorkspaceAction(ChooseWorkspaceAction chooseWorkspaceAction) {
		this.chooseWorkspaceAction = chooseWorkspaceAction;
	}

	public SaveProjectAction getSaveProjectAction() {
		return saveProjectAction;
	}

	public void setSaveProjectAction(SaveProjectAction saveProjectAction) {
		this.saveProjectAction = saveProjectAction;
	}

	public SaveProjectAsAction getSaveProjectAsAction() {
		return saveProjectAsAction;
	}

	public void setSaveProjectAsAction(SaveProjectAsAction saveProjectAsAction) {
		this.saveProjectAsAction = saveProjectAsAction;
	}

	public SaveWorkspaceAction getSaveWorkspaceAction() {
		return saveWorkspaceAction;
	}

	public void setSaveWorkspaceAction(SaveWorkspaceAction saveWorkspaceAction) {
		this.saveWorkspaceAction = saveWorkspaceAction;
	}

	public OpenProjectAction getOpenProjectAction() {
		return openProjectAction;
	}

	public void setOpenProjectAction(OpenProjectAction openProjectAction) {
		this.openProjectAction = openProjectAction;
	}

	public ShareDocumentAction getShareDocumentAction() {
		return shareDocumentAction;
	}

	public void setShareDocumentAction(ShareDocumentAction shareDocumentAction) {
		this.shareDocumentAction = shareDocumentAction;
	}

	public ChooseDestinationProjectAction getChooseDestinationProjectAction() {
		return chooseDestinationProjectAction;
	}

	public void setChooseDestinationProjectAction(ChooseDestinationProjectAction chooseDestinationProjectAction) {
		this.chooseDestinationProjectAction = chooseDestinationProjectAction;
	}

	public ChooseProjectForStoringDocsAction getChooseProjectForStoringDocsAction() {
		return chooseProjectForStoringDocsAction;
	}

	public void setChooseProjectForStoringDocsAction(
			ChooseProjectForStoringDocsAction chooseProjectForStoringDocsAction) {
		this.chooseProjectForStoringDocsAction = chooseProjectForStoringDocsAction;
	}

}
