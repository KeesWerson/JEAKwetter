package jsf;

import domain.User;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 * Created by Kees on 03/04/2017.
 */
public class UserConverter {

    public UserConverter() {
    }

    public Object getAsObject(FacesContext facesContext, UIComponent uIComponent, String string) {
        if (string == null || string.trim().length() == 0) {
            return null;
        }

        UserManagedBean managedBean = (UserManagedBean) facesContext.getApplication().getVariableResolver().resolveVariable(
                facesContext, "user");

        final Long id = new Long(string);

        return managedBean.findEntity(id);
    }

    public String getAsString(FacesContext facesContext, UIComponent uIComponent, Object object) {
        if (object == null) return null;

        if (object instanceof User) {
            User entity = (User) object;

            final String pk = String.valueOf(entity.getId());

            return pk;
        } else {
            throw new IllegalArgumentException("Incorrect object type: " + object.getClass().getName() + "; must be: User");
        }
    }
}
