package cheatsheet;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    DataRepository dataRepository;

    public RestController(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @GetMapping("")
    public List<Data> getAllCommands() {
        return dataRepository.findAll();
    }

    @GetMapping("/{language}")
    public List<Data> getLanguageCommands(@PathVariable String language) {
        System.out.println(language);
        return dataRepository.findDataByLanguage(language);
    }

    @DeleteMapping("/{id}")
    public Data deleteData(@PathVariable String id) {
        Optional<Data> dataOptional = dataRepository.findById(UUID.fromString(id));
        if (dataOptional.isPresent()) {
            Data data = dataOptional.get();
            dataRepository.delete(data);
            return data;
        }
        return new Data();
    }

    @PostMapping("/add")
    public Data addData(@RequestBody Data data) {
        dataRepository.save(data);
        return data;
    }

}
