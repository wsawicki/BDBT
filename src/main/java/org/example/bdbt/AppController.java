package org.example.bdbt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Controller
public class AppController implements WebMvcConfigurer {

    private final StatekDAO dao;

    public AppController(StatekDAO dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/index","/"})
    public String viewHomePage(Model model){
        List<Statek> listStatek = dao.list();
        model.addAttribute("listStatek",listStatek);

        return "index";
    }

    @RequestMapping(value = {"/admin_main"})
    public String viewAdminPage(Model model){
        List<Statek> listStatek = dao.list();
        model.addAttribute("listStatek",listStatek);

        Statek statek = new Statek();
        model.addAttribute("Statek",statek);

        return "admin/admin_main";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("Statek") Statek statek){
        dao.save(statek);
        return "redirect:/admin_main";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestAttribute("statek")Statek statek){
        dao.update(statek);
        return "redirect:/admin_main";
    }

    @RequestMapping(value = "/dalete/{nr_statku}")
    public String delete(@PathVariable int nr_statku){
        dao.delete(nr_statku);
        return "redirect:/damin_main";
    }


    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/perspectives").setViewName("perspectives");
        registry.addViewController("/admin_main").setViewName("admin/admin_main");
        registry.addViewController("/user_main").setViewName("user/user_main");
    }

    @Controller
    public static class DashboardController {
        @RequestMapping("/perspectives")
        public String defaultSuccessUrl(HttpServletRequest request) {
            if (request.isUserInRole("ADMIN")) {
                return "redirect:/admin_main";
            } else if (request.isUserInRole("USER")) {
                return "redirect:/user_main";
            } else {
                return "redirect:/index";
            }
        }
    }
}
