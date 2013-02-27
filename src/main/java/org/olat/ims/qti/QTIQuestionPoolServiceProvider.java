/**
 * <a href="http://www.openolat.org">
 * OpenOLAT - Online Learning and Training</a><br>
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); <br>
 * you may not use this file except in compliance with the License.<br>
 * You may obtain a copy of the License at the
 * <a href="http://www.apache.org/licenses/LICENSE-2.0">Apache homepage</a>
 * <p>
 * Unless required by applicable law or agreed to in writing,<br>
 * software distributed under the License is distributed on an "AS IS" BASIS, <br>
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. <br>
 * See the License for the specific language governing permissions and <br>
 * limitations under the License.
 * <p>
 * Initial code contributed and copyrighted by<br>
 * frentix GmbH, http://www.frentix.com
 * <p>
 */
package org.olat.ims.qti;

import java.io.File;

import org.olat.core.gui.UserRequest;
import org.olat.core.gui.control.Controller;
import org.olat.core.gui.control.WindowControl;
import org.olat.core.util.vfs.VFSLeaf;
import org.olat.ims.qti.fileresource.ItemFileResourceValidator;
import org.olat.modules.qpool.QuestionItem;
import org.olat.modules.qpool.QuestionPoolSPI;

/**
 * 
 * Initial date: 21.02.2013<br>
 * @author srosse, stephane.rosse@frentix.com, http://www.frentix.com
 *
 */
public class QTIQuestionPoolServiceProvider implements QuestionPoolSPI {

	@Override
	public int getPriority() {
		return 10;
	}

	@Override
	public String getFormat() {
		return QTIConstants.QTI_12_FORMAT;
	}

	@Override
	public boolean isCompatible(String filename, File file) {
		return new ItemFileResourceValidator().validate(filename, file);
	}
	@Override
	public boolean isCompatible(String filename, VFSLeaf file) {
		return new ItemFileResourceValidator().validate(filename, file);
	}

	@Override
	public Controller getPreviewController(UserRequest ureq, WindowControl wControl, QuestionItem item) {
		QTI12PreviewController previewCtrl = new QTI12PreviewController(ureq, wControl, item);
		return previewCtrl;
	}

	@Override
	public Controller getEditableController(UserRequest ureq, WindowControl wControl, QuestionItem item) {
		QTI12PreviewController previewCtrl = new QTI12PreviewController(ureq, wControl, item);
		return previewCtrl;
	}
}