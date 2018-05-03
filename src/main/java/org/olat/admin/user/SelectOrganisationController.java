package org.olat.admin.user;

import java.util.ArrayList;
import java.util.List;

import org.olat.core.gui.UserRequest;
import org.olat.core.gui.components.form.flexible.FormItemContainer;
import org.olat.core.gui.components.form.flexible.elements.SingleSelection;
import org.olat.core.gui.components.form.flexible.impl.FormBasicController;
import org.olat.core.gui.components.form.flexible.impl.FormLayoutContainer;
import org.olat.core.gui.control.Controller;
import org.olat.core.gui.control.Event;
import org.olat.core.gui.control.WindowControl;
import org.olat.core.id.Organisation;

/**
 * 
 * Initial date: 26 avr. 2018<br>
 * @author srosse, stephane.rosse@frentix.com, http://www.frentix.com
 *
 */
public class SelectOrganisationController extends FormBasicController {
	
	private SingleSelection organisationEl;
	
	private final List<Organisation> organisations;
	
	public SelectOrganisationController(UserRequest ureq, WindowControl wControl, List<Organisation> organisations) {
		super(ureq, wControl);
		this.organisations = new ArrayList<>(organisations);
		initForm(ureq);
	}

	@Override
	protected void initForm(FormItemContainer formLayout, Controller listener, UserRequest ureq) {
		List<String> theKeys = new ArrayList<>();
		List<String> theValues = new ArrayList<>();
		
		for(Organisation organisation:organisations) {
			theKeys.add(organisation.getKey().toString());
			theValues.add(organisation.getDisplayName());
		}
		organisationEl = uifactory.addDropdownSingleselect("select.organisation", formLayout,
				theKeys.toArray(new String[theKeys.size()]), theValues.toArray(new String[theValues.size()]));

		FormLayoutContainer buttonsCont = FormLayoutContainer.createButtonLayout("buttons", getTranslator());
		formLayout.add(buttonsCont);
		uifactory.addFormCancelButton("cancel", buttonsCont, ureq, getWindowControl());
		uifactory.addFormSubmitButton("save", buttonsCont);
	}

	@Override
	protected void doDispose() {
		//
	}
	
	public Organisation getSelectedOrganisation() {
		Organisation organisation = null;
		if(organisationEl.isOneSelected()) {
			String selectedKey = organisationEl.getSelectedKey();
			for(Organisation org:organisations) {
				if(org.getKey().toString().equals(selectedKey)) {
					organisation = org;
				}
			}
		}
		return organisation;
	}

	@Override
	protected boolean validateFormLogic(UserRequest ureq) {
		boolean allOk = super.validateFormLogic(ureq);
		
		organisationEl.clearError();
		if(!organisationEl.isOneSelected()) {
			organisationEl.setErrorKey("form.legende.mandatory", null);
			allOk &= false;
		}
		
		return allOk;
	}

	@Override
	protected void formOK(UserRequest ureq) {
		fireEvent(ureq, Event.DONE_EVENT);
	}

	@Override
	protected void formCancelled(UserRequest ureq) {
		fireEvent(ureq, Event.CANCELLED_EVENT);
	}
}
