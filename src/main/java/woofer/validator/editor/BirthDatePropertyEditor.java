package woofer.validator.editor;

import org.apache.log4j.Logger;
import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.web.context.request.WebRequest;

public class BirthDatePropertyEditor extends PropertyEditorSupport {

    private static final Logger logger =
            Logger.getLogger(BirthDatePropertyEditor.class);
    private Date date;

    public BirthDatePropertyEditor(WebRequest request) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateString =
                request.getParameter("birthYear") + "-"
                + request.getParameter("birthMonth") + "-"
                + request.getParameter("birthDay");
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            date = null;
        }
        setValue(date);
    }

    @Override
    public void setAsText(String text) {
        setValue(date);
    }
}
