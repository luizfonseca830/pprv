/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.faces.utils.uploads;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.primefaces.model.NativeUploadedFile;

/**
 *
 * @author ioliveira
 */
@FacesValidator("uploadValidatorPDF")
public class UploadValidatorPDF implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        NativeUploadedFile file = (NativeUploadedFile) value;       

        if (file == null) {
            return;
        }

        if (!file.getFileName().isEmpty() && !file.getContentType().equals("application/pdf")) {
            String exception_msg = "Informe um arquivo no formato (PDF)";

            FacesMessage msg = new FacesMessage("Falha ao fazer upload.", exception_msg);
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(msg);
        }
    }

}
