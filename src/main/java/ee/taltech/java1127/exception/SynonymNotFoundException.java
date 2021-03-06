package ee.taltech.java1127.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Synonym not found!")
public class SynonymNotFoundException extends RuntimeException {
}