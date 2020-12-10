package engine;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.*;


@Validated
@RestController
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;


    @PostMapping(value = "/api/register")
    @ResponseBody
    public User postUser(@Valid @RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @PostMapping(value = "/api/quizzes")
    @ResponseBody
    public Question postQuestion(@Valid @RequestBody Question question) {
        question.setID((int) quizRepository.count() + 1);
        quizRepository.save(question);
        return question;
    }

    @GetMapping(value = "/api/quizzes")
    public List<Question> getAllQuestion() {
        return quizRepository.findAll();
    }

    @GetMapping(value = "/api/quizzes/{id}")
    @ResponseBody
    public ResponseEntity<Question> getQuestion(@PathVariable("id") Integer id) {
       Question question = quizRepository.findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find question"));
       return ResponseEntity.ok().body(question);
    }

    @PostMapping(value = "/api/quizzes/{id}/solve")
    @ResponseBody
    public Feedback postAnswer(@RequestBody(required = false) Answer answer, @PathVariable("id") int id) {
        Question question = quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find question"));

        if (Arrays.equals(answer.getAnswer(), question.getAnswer()) || (answer.getAnswer().length == 0 && question.getAnswer() == null)) {
            return new Feedback(true, "Congratulations, you're right!");
        } else {
            return new Feedback(false, "Wrong answer! Please, try again.");
        }
    }

    @DeleteMapping(value = "/api/quizzes/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") int id) {
        Question question = quizRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find question"));

        quizRepository.delete(question);
        if (question == null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }

        return null;
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_GATEWAY);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

