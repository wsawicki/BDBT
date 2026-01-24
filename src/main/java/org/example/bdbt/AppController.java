package org.example.bdbt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.security.Principal;

@Controller
public class AppController implements WebMvcConfigurer {

    private final StatekDAO statekDAO;
    private final LadunekDAO ladunekDAO;
    private final TypLadunkuDAO typLadunkuDAO;
    private final KlientDAO klientDAO;

    public AppController(StatekDAO statekDAO, LadunekDAO ladunekDAO, TypLadunkuDAO typLadunkuDAO, KlientDAO klientDAO) {
        this.statekDAO = statekDAO;
        this.ladunekDAO = ladunekDAO;
        this.typLadunkuDAO = typLadunkuDAO;
        this.klientDAO = klientDAO;
    }

    @RequestMapping(value = {"/index", "/"})
    public String viewHomePage(Model model) {
        List<Statek> listStatek = statekDAO.list();
        model.addAttribute("listStatek", listStatek);
        return "index";
    }

    @RequestMapping(value = {"/admin_main"})
    public String viewAdminPage(Model model) {
        List<Statek> listStatek = statekDAO.list();
        model.addAttribute("listStatek", listStatek);

        List<Ladunek> listLadunki = ladunekDAO.list();
        model.addAttribute("listLadunki", listLadunki);

        double sumaWagiAdmin = 0.0;
        if (listLadunki != null && !listLadunki.isEmpty()) {
            sumaWagiAdmin = listLadunki.stream()
                    .filter(l -> l != null && l.getWaga_w_tonach() != null)
                    .mapToDouble(Ladunek::getWaga_w_tonach)
                    .sum();
        }
        model.addAttribute("sumaWagi", sumaWagiAdmin);

        Statek statek = new Statek();
        model.addAttribute("Statek", statek);
        return "admin/admin_main";
    }

    @RequestMapping(value = {"/user_main"})
    public String viewUserPage(Model model, Principal principal) {
        String username = principal.getName();
        Integer clientId = klientDAO.getIdByLogin(username);

        List<Statek> listStatek = statekDAO.list();
        model.addAttribute("listStatek", listStatek);

        List<Ladunek> listLadunki = (clientId != null) ? ladunekDAO.listByClientId(clientId) : ladunekDAO.list();
        model.addAttribute("listLadunki", listLadunki);

        List<TypLadunku> listTypow = typLadunkuDAO.list();
        model.addAttribute("listTypow", listTypow);

        double sumaWagiUser = (listLadunki != null) ? listLadunki.stream().filter(l -> l != null && l.getWaga_w_tonach() != null).mapToDouble(Ladunek::getWaga_w_tonach).sum() : 0.0;
        model.addAttribute("sumaWagi", sumaWagiUser);
        model.addAttribute("liczbaZamowien", (listLadunki != null) ? listLadunki.size() : 0);
        model.addAttribute("ladunek", new Ladunek());
        model.addAttribute("username", username);
        model.addAttribute("clientId", clientId);

        return "user/user_main";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("klient", new Klient());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("klient") Klient klient) {
        klientDAO.register(klient);
        return "redirect:/login?success";
    }

    // --- ZAPISYWANIE Z WALIDACJĄ IMO ---

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("Statek") Statek statek) {
        // Walidacja: Czy IMO ma dokładnie 7 cyfr?
        if (statek.getNrImo() == null || !statek.getNrImo().matches("\\d{7}")) {
            return "redirect:/admin_main?error=invalid_imo";
        }
        statekDAO.save(statek);
        return "redirect:/admin_main";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Statek statek) {
        // Walidacja przy edycji
        if (statek.getNrImo() == null || !statek.getNrImo().matches("\\d{7}")) {
            return "redirect:/admin_main?error=invalid_imo";
        }
        statekDAO.update(statek);
        return "redirect:/admin_main";
    }

    @RequestMapping(value = "/delete/{nr_statku}")
    public String delete(@PathVariable int nr_statku) {
        try {
            statekDAO.delete(nr_statku);
            return "redirect:/admin_main";
        } catch (Exception e) {
            return "redirect:/admin_main?error=delete_failed";
        }
    }

    @RequestMapping(value = "/save_ladunek", method = RequestMethod.POST)
    public String saveLadunek(@ModelAttribute("ladunek") Ladunek ladunek, Principal principal) {
        String username = principal.getName();
        Integer clientId = klientDAO.getIdByLogin(username);
        ladunek.setId_klienta(clientId != null ? clientId : 1);
        if (ladunek.getNr_rejsu() == null) ladunek.setNr_rejsu(501);
        ladunekDAO.save(ladunek);
        return "redirect:/user_main";
    }

    @RequestMapping(value = "/delete_ladunek_admin/{nr_ladunku}")
    public String deleteLadunekAdmin(@PathVariable int nr_ladunku) {
        ladunekDAO.delete(nr_ladunku);
        return "redirect:/admin_main";
    }

    @RequestMapping(value = "/delete_ladunek/{nr_ladunku}")
    public String deleteLadunekUser(@PathVariable int nr_ladunku) {
        ladunekDAO.delete(nr_ladunku);
        return "redirect:/user_main";
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/perspectives").setViewName("perspectives");
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