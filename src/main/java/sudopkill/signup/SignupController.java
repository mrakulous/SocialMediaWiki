package sudopkill.signup;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sudopkill.support.web.MessageHelper;

import javax.validation.Valid;

/**
 * Created by tanzeelrana on 3/4/2017.
 */
@Controller
class SignupController {

    private static final String SIGNUP_VIEW_NAME = "signup/signup";

    @GetMapping("signup")
    String signup(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        model.addAttribute(new SignupForm());
        if(requestedWith != null) {
            return SIGNUP_VIEW_NAME.concat(" :: signupForm");
        }else{
            return SIGNUP_VIEW_NAME;
        }
    }

    @PostMapping("signup")
    String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return SIGNUP_VIEW_NAME;
        }
        MessageHelper.addSuccessAttribute(ra, "signup.success");
        return "signup/signup";
    }
}