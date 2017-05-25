package net.category.controller;

import net.category.model.User;
import net.category.service.file.FileService;
import net.category.service.user.UserService;
import net.category.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller for {@link net.category.model.User}'s pages.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "user/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,
                               Model model, @RequestParam("file_image") MultipartFile fileImage) {
        String path = fileService.writeUserImage(fileImage,userForm.getEmail());
        userForm.setImageUrl(path);
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "user/registration";
        }
        userService.save(userForm);
        return "success_registration";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "user/login";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model model)
    {
        User user = userService.getCurrentUser();
        if( user == null)
          return   "redirect:/books";
        else{
            model.addAttribute("user",user);
            model.addAttribute("isAdmin",userService.isAdmin());
            model.addAttribute("isLogin",userService.isLogin());
            return "user/user";
        }
    }

    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("user") User user,
                          @RequestParam("file_image") MultipartFile fileImage){
        User originUser = userService.getCurrentUser();
        user.setPassword(originUser.getPassword());
        if(!fileImage.isEmpty()) {
            fileService.deleteFile(originUser.getImageUrl());
            String content = fileService.writeUserImage(fileImage, originUser.getEmail());
            user.setImageUrl(content);
        }
        if(fileImage.isEmpty())
            user.setImageUrl(originUser.getImageUrl());
        user.setEmail(originUser.getEmail());
        user.setRole(originUser.getRole());
        userService.update(user);
        return "redirect:/user";
    }
}
