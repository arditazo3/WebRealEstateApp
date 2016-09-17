package real_estate.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
 
public class CustomExceptionHandler extends ExceptionHandlerWrapper {
 
    private ExceptionHandler wrapped;
 
    // The instance of facesContext
    final FacesContext facesContext = FacesContext.getCurrentInstance();
 
    // Map of facesContext
    final Map requestMap = facesContext.getExternalContext().getRequestMap();
 
    final NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
 
    CustomExceptionHandler(ExceptionHandler exception) {
        this.wrapped = exception;
    }
 
    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }
 
    @Override
    public void handle() throws FacesException {
 
        final Iterator iterator = getUnhandledExceptionQueuedEvents().iterator();
        while (iterator.hasNext()) {
            ExceptionQueuedEvent event = (ExceptionQueuedEvent) iterator.next();
            ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
 
            Throwable exception = context.getException();
 
            try {
 
                StringWriter stringWriter = new StringWriter();
           		PrintWriter printWriter = new PrintWriter(stringWriter);
       			exception.printStackTrace(printWriter);
                String message = stringWriter.toString();

                exception.printStackTrace();
 
                requestMap.put("exceptionMessage", exception.getMessage());
 
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                    (FacesMessage.SEVERITY_ERROR, "The system has recovered from an unexpected error.", ""));
 
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
                    (FacesMessage.SEVERITY_INFO, "You can continue using the system normally!", ""));
 
                navigationHandler.handleNavigation(facesContext, null, "/restrict/home.faces");
 
                facesContext.renderResponse();
            } finally {
                iterator.remove();
            }
        }
        getWrapped().handle();
    }
}
