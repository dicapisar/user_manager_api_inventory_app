package com.dicapisar.userManager.utils;

import com.dicapisar.userManager.exceptions.SessionErrorException;
import com.dicapisar.userManager.exceptions.SessionWithOutPermissionException;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import static com.dicapisar.userManager.commons.UserManagerConstants.*;

public class SessionUtils {
    public static void validateSessionExist(HttpSession session) throws SessionErrorException {
        if (session.getAttribute(ROL_NAME) == null) {
            throw new SessionErrorException();
        }
    }

    public static void validateSessionHavePermissions(HttpSession session, ArrayList<String> permissionList)
            throws SessionWithOutPermissionException {
      if (!permissionList.contains(session.getAttribute(ROL_NAME))){
          throw new SessionWithOutPermissionException();
      }
    }

    public static Long getIdUserSession(HttpSession session) {
        return (Long) session.getAttribute(ROL_ID);
    }

}
