package controllers;


import play.mvc.*;
import play.db.jpa.Transactional;

import util.encryption.EncryptionFactory;
import views.html.*;

public class Application extends Controller {

    @Transactional
    public static Result index() {
       
        return ok(index.render("hello :)"));
    }
  
}
