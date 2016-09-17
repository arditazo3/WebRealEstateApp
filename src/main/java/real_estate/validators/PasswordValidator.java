package real_estate.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/** Validator of the password
 * should contain number and letters
 * minimum 7 and maximum 22 characters */

@FacesValidator(value="passwordValidator")
public class PasswordValidator implements Validator {

    int num = 0;
    int carac = 0;

    //Define the array with the special charactes
    char[] caracteresEspeciais={'=','|','!','@','#','$','%','^','&','*','(',')','{','}','[',']',';',':','.',',','<','>','?','~','+','-','_','\'','"'};
    
    @Override
    public void validate(FacesContext context,UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }      
        
        String password = (String)value;
        
        for(int i=0;i<password.length();i++) {
            if(password.charAt(i)>=48 && password.charAt(i)<=57) {
                num++;
            }
        }

        for(int i=0;i<password.length();i++) {
            for(int j=0;j<caracteresEspeciais.length;j++) {
                if(password.charAt(i)==caracteresEspeciais[j]) {
                    carac++;
                }
            }
        }

        // verify the password
        if(password == null || password.equals("")){
             throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Password: Required", ""));
             
        } else if(!(num > 0)){
             throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "The password should contain letters and numbers", ""));
             
        } else if(password.length() < 7){
             throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Password: The minimum 7 characters", ""));
        } else if(password.length() >= 22){
             throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Password: The maximum 22 characters", ""));
        } 
    }
}
