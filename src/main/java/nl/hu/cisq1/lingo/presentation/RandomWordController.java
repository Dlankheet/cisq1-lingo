package nl.hu.cisq1.lingo.presentation;

import nl.hu.cisq1.lingo.application.WordService;
import nl.hu.cisq1.lingo.domain.exception.WordLengthNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("words")
public class RandomWordController {
    private final WordService service;

    public RandomWordController(WordService service) {
        this.service = service;
    }

    @GetMapping("random")
    public String getRandomWord(@RequestParam Integer length) {
        try {
            return this.service.provideRandomWord(length);
        } catch (WordLengthNotSupportedException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }
    }
}
