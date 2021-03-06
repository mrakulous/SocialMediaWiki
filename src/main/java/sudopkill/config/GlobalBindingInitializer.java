package sudopkill.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import sudopkill.account.Account;
import sudopkill.account.AccountRepository;
import sudopkill.page.PageService;

import java.security.Principal;

/**
 * Created by tanzeelrana on 3/11/2017.
 */

@ControllerAdvice
public class GlobalBindingInitializer {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PageService pageService;

    @ModelAttribute("currentUser")
    Account module(Principal principal) {
        System.out.println("principal : " + principal);
        if(principal != null) {
            Account currentUser = accountRepository.findOneByUsername( principal.getName() );
            System.out.println("currentUser : " + currentUser);
            return  currentUser;
        }else{
            return null;
        }
    }

    @ModelAttribute("pageService")
    PageService module() {
        return pageService;
    }

    @InitBinder
    public void registerCustomEditors(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

}
