package cheatsheet;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@org.springframework.stereotype.Controller
public class Controller {

    private DataRepository dataRepository;

    public Controller(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @GetMapping("/")
    public String getAllCommands(Model model) {
        // model.addAttribute("datas", dataRepository.findAll());
        model.addAttribute("languages", dataRepository.findAllLanguages());
        return "index";
    }

    @GetMapping("/data/{language}")
    public String getLanguage(@PathVariable String language,  Model model) {
        model.addAttribute("languages", dataRepository.findAllLanguages());
        model.addAttribute("datas", dataRepository.findDataByLanguage(language));
        model.addAttribute("subjects", dataRepository.findAllSubject(language));
        return "language";
    }

    @GetMapping("/add")
    public String addData(Model model) {
        model.addAttribute("languages", dataRepository.findAllLanguages());
        model.addAttribute("data", new Data());
        return "add";
    }

    @PostMapping("/add")
    public String postData(@Valid Data data, Errors errors) {
        if (errors.hasErrors()) {
            return "add";
        }
        dataRepository.save(data);
        return "redirect:/";
    }

    @GetMapping ("/delete/{id}")
    public String deleteData(@PathVariable String id) {
        Optional<Data> dataOptional = dataRepository.findById(UUID.fromString(id));
        if (dataOptional.isPresent()) dataRepository.delete(dataOptional.get());
        return "redirect:/";
    }

    @GetMapping ("edit/{id}")
    public String editData(@PathVariable String id, Model model) {
        Optional<Data> dataOptional = dataRepository.findById(UUID.fromString(id));
        if (dataOptional.isPresent()) model.addAttribute("data", dataOptional.get());
        return "edit";
    }

    @PostMapping ("/edit/{id}")
    public String postEditData(@Valid Data data, Errors errors) {
        if (errors.hasErrors()) {
            return "add";
        }
        dataRepository.save(data);
        return "redirect:/";
    }


}
